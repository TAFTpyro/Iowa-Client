package me.iyoshimc.iowa.module.combat;

import me.iyoshimc.iowa.event.EventTarget;
import me.iyoshimc.iowa.event.events.EventPostMotionUpdates;
import me.iyoshimc.iowa.event.events.EventPreMotionUpdates;
import me.iyoshimc.iowa.event.events.EventUpdate;
import me.iyoshimc.iowa.module.Category;
import me.iyoshimc.iowa.module.Module;
import me.iyoshimc.iowa.utils.Timer;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Objects;

public class KillAura extends Module{

    ArrayList<Entity> targets;

    boolean block;

    double reach, cps, delay;

    Timer timer;

    public KillAura() {
        super("KillAura", Keyboard.KEY_R, Category.COMBAT);
        targets = new ArrayList<>();
        reach = 7.0;
        cps = 11;
        delay = (1000/cps);
        block = true;
        timer = new Timer();
    }
    public void onEnable(){
        super.onEnable();
        timer.reset();
        targets.clear();
    }
    public void onDisable(){
        super.onDisable();
        timer.reset();
        targets.clear();
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        delay = (1000/cps);
    }

    @EventTarget
    public void onPre(EventPreMotionUpdates e){
        mc.theWorld.loadedEntityList.stream().filter(entity -> isValidEntity(entity)).filter(entity -> !(targets.contains(entity))).forEach(targets::add);

        targets.sort((e1, e2) -> Math.round(mc.thePlayer.getDistanceToEntity(e1) - mc.thePlayer.getDistanceToEntity(e2)));

        float[] rots = getRotations(getCurrentTarget());
        e.setYaw(rots[0]);
        e.setPitch(rots[1]);

    }

    @EventTarget
    public void onPost(EventPostMotionUpdates e){
        if(!isValidEntity(getCurrentTarget()))return;
        blockItem(false);
        if(!timer.hasTimerReached((long)delay))return;
        blockItem(true);
        attackEntity(getCurrentTarget());
        blockItem(false);
        timer.reset();
    }

    private float[] getRotations(Entity e){
        double x = e.posX, y = e.posY + e.getEyeHeight() -4.0F, z = e.posZ;
        return getRotationsFromPosition(x, z, y);
    }
    private float[] getRotationsFromPosition(double x, double z, double y){
        double diffX = x - mc.thePlayer.posX, diffZ = z - mc.thePlayer.posZ, diffY = y - mc.thePlayer.posY+mc.thePlayer.getEyeHeight(), distance = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
        float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F, pitch = (float) -(Math.atan2(diffY, distance) * 180.0D / Math.PI);
        return new float[] {yaw, pitch};
    }

    private void attackEntity(Entity e){
        mc.thePlayer.swingItem();
        mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(e, C02PacketUseEntity.Action.ATTACK));

    }
    private Entity getCurrentTarget(){
        return targets.get(0);
    }

    private boolean isValidEntity(Entity e){
        return e instanceof EntityOtherPlayerMP && mc.thePlayer.getDistanceToEntity(e) <= reach;
    }
    private void blockItem(boolean release){
        if(!block || Objects.isNull(mc.thePlayer.getHeldItem())) return;
        if(release){
            mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, new BlockPos(0, 0, 0), EnumFacing.UP));
        }else{
            mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.getHeldItem()));
            mc.thePlayer.setItemInUse(mc.thePlayer.getHeldItem(), mc.thePlayer.getHeldItem().getMaxItemUseDuration());
        }
    }
}
