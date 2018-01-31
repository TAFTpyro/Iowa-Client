package me.iyoshimc.iowa.module.movement;

import me.iyoshimc.iowa.event.EventTarget;
import me.iyoshimc.iowa.event.events.EventUpdate;
import me.iyoshimc.iowa.module.Category;
import me.iyoshimc.iowa.module.Module;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class NoSlowDown extends Module{

    public NoSlowDown() {
        super("NoSlowDown", 0, Category.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        if(mc.thePlayer.isBlocking() && mc.thePlayer.isMoving()){
            mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, new BlockPos(0, 0,0 ), EnumFacing.UP));
        }
    }
}
