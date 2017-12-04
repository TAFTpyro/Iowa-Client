package me.iyoshimc.iowa.module;

import me.iyoshimc.iowa.module.movement.Fly;

import java.util.ArrayList;

public class ModuleManager {

    private ArrayList<Module> modules = new ArrayList<>();;

    public ModuleManager(){

        modules.add(new Fly());
    }


    public ArrayList<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name){
        return modules.stream().filter(module -> module.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

}
