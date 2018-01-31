package me.iyoshimc.iowa.utils;

import net.minecraft.client.Minecraft;

public class PlayerUtils {

    public static void swap(int slot, int hotbar){
        Minecraft.getMinecraft().playerController.windowClick(Minecraft.getMinecraft().thePlayer.inventoryContainer.windowId, slot, hotbar, 2, Minecraft.getMinecraft().thePlayer);
    }
}
