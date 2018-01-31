package me.iyoshimc.iowa.module.player;

import me.iyoshimc.iowa.event.EventTarget;
import me.iyoshimc.iowa.event.events.EventPacket;
import me.iyoshimc.iowa.module.Category;
import me.iyoshimc.iowa.module.Module;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;

public class Velocity extends Module{
    public Velocity() {
        super("Velocity", 0, Category.PLAYER);
    }

    @EventTarget
    public void onPacket(EventPacket e){
        if(e.getPacket() instanceof S12PacketEntityVelocity || e.getPacket() instanceof S27PacketExplosion){
            e.setCancelled(true);
        }
    }
}
