package me.iyoshimc.iowa.event.events;

import me.iyoshimc.iowa.event.Event;

public class EventKey extends Event{

    private int key;

    public EventKey(int key){
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
