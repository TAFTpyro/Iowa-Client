package me.iyoshimc.iowa.module.combat;

import me.iyoshimc.iowa.event.EventTarget;
import me.iyoshimc.iowa.event.events.EventPreMotionUpdates;
import me.iyoshimc.iowa.module.Category;
import me.iyoshimc.iowa.module.Module;
import me.iyoshimc.iowa.utils.PlayerUtils;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;

import java.util.Objects;

public class AutoSoup extends Module{

    private float health;

    public AutoSoup() {
        super("AutoSoup", 0, Category.COMBAT);
        health = 3.0F;
    }

    private int getSoupsInInv(){
        int soup = 0;
        for(int i = 0; i < 45; i++){
            ItemStack item = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            if(Objects.nonNull(item) && item.getItem() instanceof ItemSoup){
                soup++;
            }
        }
        return soup;
    }

    private int getSoupSlot(){
        int soup = 69;
        for(int i = 0; i < 45; i++){
            ItemStack item = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            if(Objects.nonNull(item) && item.getItem() instanceof ItemSoup){
                soup = i;
            }
        }
        return soup;
    }

    @EventTarget
    public void onPre(EventPreMotionUpdates e){
        this.setDisplayName("AutoSoup §7"+getSoupsInInv());
        if(mc.thePlayer.getHealth() <= health){
            if(getSoupSlot() != 69){
                PlayerUtils.swap(getSoupSlot(), 8);
                mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(8));
                mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.getHeldItem()));
                mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
            }
        }
    }
}
