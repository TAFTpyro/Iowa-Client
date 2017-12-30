package me.iyoshimc.iowa.ui.tab;

import me.iyoshimc.iowa.Iowa;
import me.iyoshimc.iowa.event.EventTarget;
import me.iyoshimc.iowa.event.events.Event2D;
import me.iyoshimc.iowa.event.events.EventKey;
import me.iyoshimc.iowa.module.Category;
import me.iyoshimc.iowa.module.Module;
import me.iyoshimc.iowa.ui.font.FontManager;
import me.iyoshimc.iowa.utils.R2DUtils;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Tabgui {

    private Panels panels;
    private ArrayList<Category> categories;
    int categorySelector, categoryRect, inc, modRect, modSelector;

    public Tabgui(){
        panels = Panels.CATEGORIES;
        categories = new ArrayList<>();
        categorySelector = 0;
        modSelector = 0;
        categoryRect = 40;
        modRect = 40;
        inc = 15;
        registerCategories();
        Iowa.theClient.eventManager.register(this);
    }

    @EventTarget
    public void onRender(Event2D e){
        int categoryY = 40, categoryNames = 40;
        for(Category c : categories){
            R2DUtils.rectagle(2, categoryY, getWidestCategory() + inc, categoryY+inc, Iowa.theClient.blackColor.getRGB());
            categoryY+=inc;
        }
        R2DUtils.rectagle(3, categoryRect + 1, getWidestCategory() + inc -1, categoryRect + inc -1, Iowa.theClient.clientColor.getRGB());

        for(Category c : categories){
            FontManager.regular.drawStringWithShadow(c.name().substring(0, 1).toUpperCase()+c.name().substring(1, c.name().length()).toLowerCase(), 4, categoryNames, -1);
            categoryNames += inc;
        }

        if(panels == Panels.MODULES){
            int modY = 40, modNames = 40;
            for(Module m : Iowa.theClient.moduleManager.getModules()){
                if(m.getCategory() == categories.get(categorySelector)){
                    R2DUtils.rectagle(getWidestCategory() +inc + 2, modY, getWidestCategory()+inc+2+getWidestModule()+inc, modY + inc, Iowa.theClient.blackColor.getRGB());
                    modY += inc;
                }
            }
            R2DUtils.rectagle(getWidestCategory()+inc+3, modRect+1, getWidestCategory()+inc+1+getWidestModule()+inc, modRect +inc -1, Iowa.theClient.clientColor.getRGB());
            for(Module m : Iowa.theClient.moduleManager.getModules()){
                if(m.getCategory() == categories.get(categorySelector)){
                    FontManager.regular.drawStringWithShadow(m.getName(), getWidestCategory()+inc+4, modNames, m.isToggled() ? -1 :  new Color(102, 102, 102).getRGB());
                    modNames += inc;
                }
            }
        }
    }

    @EventTarget
    public void onKey(EventKey e){
        switch (e.getKey()){
            case Keyboard.KEY_UP: {
                if(panels == Panels.CATEGORIES) {
                    if (categorySelector == 0) {
                        categorySelector = categories.size()-1;
                        categoryRect = categoryRect + categorySelector*inc;
                    }else{
                        categorySelector--;
                        categoryRect-=inc;
                    }
                }
                if(panels == Panels.MODULES){
                    if(modSelector == 0){
                        modSelector = getModuleInCurrentCategory().size()-1;
                        modRect = modRect + modSelector *inc;
                    }else{
                        modSelector--;
                        modRect = modRect -inc;
                    }
                }
                break;
            }
            case Keyboard.KEY_DOWN:{
                if(panels == Panels.CATEGORIES){
                    if(categorySelector == categories.size()-1){
                        categorySelector = 0;
                        categoryRect = 40;
                    }else{
                        categorySelector++;
                        categoryRect+=inc;
                    }
                }
                if(panels == Panels.MODULES){
                    if(modSelector == getModuleInCurrentCategory().size()-1){
                        modSelector = 0;
                        modRect = 40;
                    }else{
                        modSelector++;
                        modRect+=inc;
                    }
                }
                break;
            }
            case Keyboard.KEY_RIGHT: {
                if(panels == Panels.CATEGORIES) {
                    panels = Panels.MODULES;
                    modSelector = 0;
                    modRect = 40;
                }
                else if(panels == Panels.MODULES)
                    getModuleInCurrentCategory().get(modSelector).toggle();
                break;
            }
            case Keyboard.KEY_LEFT: {
                if(panels == Panels.MODULES) {
                    panels = Panels.CATEGORIES;
                    modSelector = 0;
                    modRect = 40;
                }
            }
        }
    }

    private void registerCategories(){
        Arrays.stream(Category.values()).filter(c -> c != Category.NONE).filter(c -> !(categories.contains(c))).forEach(categories::add);
    }

    private int getWidestCategory(){
        int wide = 0;
        for(Category c : categories){
            String name = c.name();
            int category = FontManager.regular.getWidthInt(name);
            if(wide < category)
                wide = category;
        }
        return wide;
    }

    private int getWidestModule(){
        int wide = 0;
        for(Module m : Iowa.theClient.moduleManager.getModules()){
            String name = m.getName();
            int mod = FontManager.regular.getWidthInt(name);
            if(wide < mod)
                wide = mod;
        }
        return wide;
    }

    private ArrayList<Module> getModuleInCurrentCategory(){
        ArrayList<Module> mods = new ArrayList<>();
        Iowa.theClient.moduleManager.getModules().stream().filter(m -> m.getCategory().equals(categories.get(categorySelector))).forEach(mods::add);
        return mods;
    }
}
