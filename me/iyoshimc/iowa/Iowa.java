package me.iyoshimc.iowa;

import me.iyoshimc.iowa.event.EventManager;
import me.iyoshimc.iowa.event.EventTarget;
import me.iyoshimc.iowa.event.events.EventKey;
import me.iyoshimc.iowa.module.Module;
import me.iyoshimc.iowa.module.ModuleManager;
import me.iyoshimc.iowa.ui.Hud;
import org.lwjgl.opengl.Display;

import java.awt.*;
import java.io.File;
import java.io.InputStream;

public class Iowa {

    /**
     * Strings
     */
    public final String name = "Iowa", version = "0.1", developer = "iYoshiMC";

    /**
     * Instance
     */
    public static final Iowa theClient = new Iowa();

    /**
     * Client stuff
     */
    public EventManager eventManager;
    public ModuleManager moduleManager;
    public Font regular = null;
    public Hud hud;
    public Color clientColor = new Color(22, 255, 231);
    public Color blackColor = new Color(0, 0,0 , 127);

    public void startClient(){
        try{
            InputStream stream = getClass().getResourceAsStream("/assets/minecraft/iowa/Comfortaa_Regular.ttf");
            regular = Font.createFont(0, stream).deriveFont(0, 16);
        }catch (Exception e){
            e.printStackTrace();
        }
        eventManager = new EventManager();
        moduleManager = new ModuleManager();
        hud = new Hud();
        Display.setTitle(name + " " + version);
        System.out.println("Developed by "+developer);
        eventManager.register(this);
    }

    public void endClient(){
        eventManager.unregister(this);
    }

    @EventTarget
    public void onKey(EventKey e){
       moduleManager.getModules().stream().filter(module -> module.getKey() == e.getKey()).forEach(module -> module.toggle());
    }
}
