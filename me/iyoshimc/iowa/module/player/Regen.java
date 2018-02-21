package me.iyoshimc.iowa.module.player;

import me.iyoshimc.iowa.event.EventTarget;
import me.iyoshimc.iowa.event.events.EventUpdate;
import me.iyoshimc.iowa.module.Category;
import me.iyoshimc.iowa.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Regen extends Module{

    public Regen() {
        super("Regen", 0, Category.PLAYER);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        for(int i = 0; i < 20; i++){
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
        }
    }
}
