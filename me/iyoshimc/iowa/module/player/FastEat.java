package me.iyoshimc.iowa.module.player;

import me.iyoshimc.iowa.event.EventTarget;
import me.iyoshimc.iowa.event.events.EventUpdate;
import me.iyoshimc.iowa.module.Category;
import me.iyoshimc.iowa.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class FastEat extends Module{

    public FastEat() {
        super("FastEat", 0, Category.PLAYER);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        if(mc.thePlayer.getFoodStats().getFoodLevel() < 20 && mc.thePlayer.isEating()){
            for(int i = 0; i < 100; i++){
                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
            }
        }
    }
}
