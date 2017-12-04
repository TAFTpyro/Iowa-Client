package me.iyoshimc.iowa.event.events;

import me.iyoshimc.iowa.event.Event;

public class EventPreMotionUpdates extends Event{

    private float yaw, pitch;
    private boolean ground;

    public EventPreMotionUpdates(float yaw, float pitch, boolean ground){
        this.yaw = yaw;
        this.pitch = pitch;
        this.ground = ground;
    }

    public float getYaw() {
        return yaw;
    }
    public float getPitch() {
        return pitch;
    }
    public boolean onGround() {
        return ground;
    }
    public void setYaw(float yaw){
        this.yaw = yaw;
    }
    public void setPitch(float pitch){
        this.pitch = pitch;
    }
    public void setGround(boolean ground){
        this.ground = ground;
    }
}
