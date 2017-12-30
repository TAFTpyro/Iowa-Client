package me.iyoshimc.iowa.module.render;

import me.iyoshimc.iowa.event.EventTarget;
import me.iyoshimc.iowa.event.events.EventUpdate;
import me.iyoshimc.iowa.module.Category;
import me.iyoshimc.iowa.module.Module;

public class Fullbright extends Module{

    public Fullbright() {
        super("Fullbright", 0, Category.RENDER);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        mc.gameSettings.gammaSetting = 100f;
    }

    public void onDisable(){
        super.onDisable();
        mc.gameSettings.gammaSetting = 0f;
    }
}
