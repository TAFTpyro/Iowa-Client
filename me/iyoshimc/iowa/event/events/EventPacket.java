package me.iyoshimc.iowa.event.events;

import me.iyoshimc.iowa.event.Event;
import net.minecraft.network.Packet;

public class EventPacket extends Event{

    private Packet packet;

    private boolean sent;

    public EventPacket(Packet packet, boolean sent) {
        this.packet = packet;
        this.sent = sent;
    }

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}
