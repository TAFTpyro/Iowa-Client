package me.iyoshimc.iowa.module;

import me.iyoshimc.iowa.module.movement.Fly;
import me.iyoshimc.iowa.module.movement.Timer;
import me.iyoshimc.iowa.module.player.NoFall;
import me.iyoshimc.iowa.module.render.Fullbright;

import java.util.ArrayList;

public class ModuleManager {

    private ArrayList<Module> modules = new ArrayList<>();;

    public ModuleManager(){

        modules.add(new Fly());
        modules.add(new NoFall());
        modules.add(new Timer());
        modules.add(new Fullbright());
    }


    public ArrayList<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name){
        return modules.stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

}
