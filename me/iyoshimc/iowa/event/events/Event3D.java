package me.iyoshimc.iowa.event.events;

import me.iyoshimc.iowa.event.Event;

public class Event3D extends Event{

    private float partcialTicks;

    public Event3D(float partcialTicks){
        this.partcialTicks = partcialTicks;
    }

    public float getPartcialTicks() {
        return partcialTicks;
    }
}
