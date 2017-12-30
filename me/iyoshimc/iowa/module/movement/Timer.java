package me.iyoshimc.iowa.module.movement;

import me.iyoshimc.iowa.event.EventTarget;
import me.iyoshimc.iowa.event.events.EventUpdate;
import me.iyoshimc.iowa.module.Category;
import me.iyoshimc.iowa.module.Module;

public class Timer extends Module{

    public Timer() {
        super("Timer", 0, Category.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        mc.timer.timerSpeed = 2.0F;
    }

    public void onDisable(){
        super.onDisable();
        mc.timer.timerSpeed = 1.0F;
    }
}
