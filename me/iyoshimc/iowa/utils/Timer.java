package me.iyoshimc.iowa.utils;

public class Timer {
    private long lastMillisecond;

    public Timer(){
        lastMillisecond = getCurrentMillisecond();
    }
    public long getCurrentMillisecond(){
        return  System.nanoTime() / 1000000L;
    }
    public boolean hasTimerReached(long millisecond){
        return  getCurrentMillisecond() - lastMillisecond >= millisecond;
    }
    public void reset(){
        lastMillisecond = getCurrentMillisecond();
    }
}
