package me.iyoshimc.iowa.ui;

import me.iyoshimc.iowa.Iowa;
import me.iyoshimc.iowa.module.Category;
import me.iyoshimc.iowa.module.Module;
import me.iyoshimc.iowa.ui.font.FontManager;
import me.iyoshimc.iowa.utils.R2DUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class Hud {

    private Minecraft mc = Minecraft.getMinecraft();

    public void registerHud(){
        renderHud();
    }

    /**
     * Hud stuff
     */
    private void renderHud(){
        ScaledResolution sr = new ScaledResolution(mc);
        FontManager.logo.drawStringWithShadow(Iowa.theClient.name, 2, 2, Iowa.theClient.clientColor.getRGB());
        renderArrayList(sr);
    }

    private void renderArrayList(ScaledResolution sr){
        int y = 2;
        Iowa.theClient.moduleManager.getModules().sort((m1, m2) -> FontManager.regular.getWidthInt(m2.getDisplayName()) - FontManager.regular.getWidthInt(m1.getDisplayName()));
        for(Module m : Iowa.theClient.moduleManager.getModules()){
            int x = sr.getScaledWidth() - FontManager.regular.getWidthInt(m.getDisplayName()) - 2;
            if(m.isToggled() && m.getCategory() != Category.NONE) {
                R2DUtils.rectagle(x - 2, y - 2, sr.getScaledWidth(), y + 12, Iowa.theClient.blackColor.getRGB());
                R2DUtils.rectagle(sr.getScaledWidth() - 1, y - 2, sr.getScaledWidth(), y + 12, Iowa.theClient.clientColor.getRGB());
                FontManager.regular.drawStringWithShadow(m.getDisplayName(), x, y, Iowa.theClient.clientColor.getRGB());
                y += 10;
            }
        }
    }
}
