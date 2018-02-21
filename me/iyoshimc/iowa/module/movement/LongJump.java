package me.iyoshimc.iowa.module.movement;

import me.iyoshimc.iowa.event.EventTarget;
import me.iyoshimc.iowa.event.events.EventMove;
import me.iyoshimc.iowa.event.events.EventPostMotionUpdates;
import me.iyoshimc.iowa.module.Category;
import me.iyoshimc.iowa.module.Module;
import net.minecraft.potion.Potion;
import org.lwjgl.input.Keyboard;

public class LongJump extends Module{

    private double stage, moveSpeed, lastDistance;

    public LongJump() {
        super("LongJump", Keyboard.KEY_G, Category.MOVEMENT);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        moveSpeed = getBaseMoveSpeed();
        stage = 0;
    }

    private double getBaseMoveSpeed(){
        return mc.thePlayer.isPotionActive(Potion.moveSpeed) ? (2.873D * (1.0D + 0.2 * (mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() +1))) : 0.2873D;
    }

    @EventTarget
    public void onPost(EventPostMotionUpdates e){
        double diffX = mc.thePlayer.posX - mc.thePlayer.prevPosX;
        double diffZ = mc.thePlayer.posZ - mc.thePlayer.prevPosZ;
        lastDistance = Math.sqrt(diffX * diffX + diffZ * diffZ);
    }

    @EventTarget
    public void onMove(EventMove e)
    {
        float forward = mc.thePlayer.movementInput.moveForward;
        float strafe = mc.thePlayer.movementInput.moveStrafe;
        float yaw = mc.thePlayer.rotationYaw;

        if(mc.thePlayer.isInLiquid()){
            stage = 1;
            return;
        }

        if(stage == 1 && mc.thePlayer.isMoving()){
            stage++;
            moveSpeed = getBaseMoveSpeed();
        }
        else if(stage == 2){
            stage++;
            e.setY(mc.thePlayer.motionY = 0.42);
            moveSpeed *= 2;
        }else if(stage == 3){
            stage++;
            double dif = 0.66 * (lastDistance - getBaseMoveSpeed());
            moveSpeed = (lastDistance - dif) * 2.5;
        }else{
            if(mc.thePlayer.isCollidedVertically) stage = 1;
            moveSpeed = (lastDistance - lastDistance / 150);
        }

        moveSpeed = Math.max(moveSpeed, getBaseMoveSpeed());

        if(!mc.thePlayer.isMoving()){
            e.setX(0);
            e.setZ(0);
        }else if(forward != 0){
            if(strafe > 0){
                yaw += (forward > 0 ? -45 : 45);
            }else if(strafe < 0){
                yaw += (forward > 0 ? 45 : -45);
            }
            strafe = 0;
            if(forward > 0){
                forward = 1;
            }else if(forward < 0){
                forward = -1;
            }
        }
        double mx = Math.cos(Math.toRadians(yaw + 90.0F));
        double mz = Math.sin(Math.toRadians(yaw + 90.0F));

        e.setX(forward * moveSpeed * mx + strafe * moveSpeed * mz);
        e.setZ(forward * moveSpeed * mz - strafe * moveSpeed * mx);
    }
}
