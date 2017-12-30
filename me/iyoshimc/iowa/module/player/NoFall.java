package me.iyoshimc.iowa.module.player;

import me.iyoshimc.iowa.event.EventTarget;
import me.iyoshimc.iowa.event.events.EventPreMotionUpdates;
import me.iyoshimc.iowa.module.Category;
import me.iyoshimc.iowa.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Module{

    public NoFall() {
        super("NoFall", 0, Category.PLAYER);
    }

    @EventTarget
    public void onPre(EventPreMotionUpdates e){
        if(mc.thePlayer.fallDistance > 2f)
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
    }
}
