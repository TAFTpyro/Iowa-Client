package me.iyoshimc.iowa.module;

import me.iyoshimc.iowa.module.combat.AutoPot;
import me.iyoshimc.iowa.module.combat.AutoSoup;
import me.iyoshimc.iowa.module.combat.KillAura;
import me.iyoshimc.iowa.module.movement.Fly;
import me.iyoshimc.iowa.module.movement.NoSlowDown;
import me.iyoshimc.iowa.module.movement.Sprint;
import me.iyoshimc.iowa.module.movement.Timer;
import me.iyoshimc.iowa.module.player.NoFall;
import me.iyoshimc.iowa.module.player.Velocity;
import me.iyoshimc.iowa.module.render.Fullbright;

import java.util.ArrayList;

public class ModuleManager {

    private ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager(){

        modules.add(new Fly());
        modules.add(new NoFall());
        modules.add(new Timer());
        modules.add(new Fullbright());
        modules.add(new KillAura());
        modules.add(new Sprint());
        modules.add(new NoSlowDown());
        modules.add(new AutoSoup());
        modules.add(new Velocity());
        modules.add(new AutoPot());
    }


    public ArrayList<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name){
        return modules.stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

}
