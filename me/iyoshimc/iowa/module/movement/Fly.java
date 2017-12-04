package me.iyoshimc.iowa.module.movement;

import me.iyoshimc.iowa.event.EventTarget;
import me.iyoshimc.iowa.event.events.EventUpdate;
import me.iyoshimc.iowa.module.Category;
import me.iyoshimc.iowa.module.Module;
import org.lwjgl.input.Keyboard;

public class Fly extends Module{

    public Fly(){
        super("Fly", Keyboard.KEY_F, Category.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        this.setDisplayName("Fly §7Vanilla");
        mc.thePlayer.capabilities.isFlying = true;
        mc.thePlayer.capabilities.allowFlying = true;
        mc.thePlayer.capabilities.setFlySpeed(0.2f);
    }

   @Override
    public void onDisable(){
        super.onDisable();
        mc.thePlayer.capabilities.isFlying = false;
        mc.thePlayer.capabilities.allowFlying = false;
    }
}
