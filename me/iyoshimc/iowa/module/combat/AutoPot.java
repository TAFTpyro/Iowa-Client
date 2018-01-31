package me.iyoshimc.iowa.module.combat;

import me.iyoshimc.iowa.event.EventTarget;
import me.iyoshimc.iowa.event.events.EventPostMotionUpdates;
import me.iyoshimc.iowa.event.events.EventPreMotionUpdates;
import me.iyoshimc.iowa.module.Category;
import me.iyoshimc.iowa.module.Module;
import me.iyoshimc.iowa.utils.PlayerUtils;
import me.iyoshimc.iowa.utils.Timer;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.util.Objects;

public class AutoPot extends Module{

    private float health;

    private boolean potted;

    private int delay;


    public AutoPot() {
        super("AutoPot", 0, Category.COMBAT);
        health = 3.0F;

    }

    private int getPotsInInv(){
        int pot = 0;
        for(int i = 0; i < 45; i++){
            ItemStack item = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            if(Objects.nonNull(item) && item.getItem() instanceof ItemPotion){
                ItemPotion potion = (ItemPotion)item.getItem();
                if(potion.getEffects(item) != null){
                    for(PotionEffect effect : potion.getEffects(item)){
                        if(effect.getPotionID() == Potion.heal.id && ItemPotion.isSplash(item.getItemDamage())){
                            pot++;
                        }
                    }
                }
            }
        }
        return pot;
    }

    private int getPotSlot(){
        int pot = 69;
        for(int i = 0; i < 45; i++){
            ItemStack item = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            if(Objects.nonNull(item) && item.getItem() instanceof ItemPotion){
                ItemPotion potion = (ItemPotion)item.getItem();
                if(potion.getEffects(item) != null){
                    for(PotionEffect effect : potion.getEffects(item)){
                        if(effect.getPotionID() == Potion.heal.id && ItemPotion.isSplash(item.getItemDamage())){
                           pot = i;
                        }
                    }
                }
            }
        }
        return pot;
    }

        @EventTarget
        public void onPre(EventPreMotionUpdates e){
        this.setDisplayName("AutoPot §7"+getPotsInInv());
        if(mc.thePlayer.getHealth() <= health){
            if(getPotSlot() != 69){
                delay++;
                e.setPitch(120);
                PlayerUtils.swap(getPotSlot(), 8);
                potted = true;

            }
        }
    }

    @EventTarget
    public void onPost(EventPostMotionUpdates e){
        if(potted){
            if(delay >= 5) {
                mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(8));
                mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.getHeldItem()));
                mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
                potted = false;
               delay = 0;
           }
        }
    }


}
