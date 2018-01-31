package me.iyoshimc.iowa.module.movement;

import me.iyoshimc.iowa.event.EventTarget;
import me.iyoshimc.iowa.event.events.EventUpdate;
import me.iyoshimc.iowa.module.Category;
import me.iyoshimc.iowa.module.Module;

public class Sprint extends Module{

    public Sprint() {
        super("Sprint", 0, Category.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        if(mc.thePlayer.isMoving()){
            mc.thePlayer.setSprinting(true);
        }
    }
}
