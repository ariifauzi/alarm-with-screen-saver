package com.example.harukaedu.alarmashari.animation;

import android.content.Context;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by mzennis on 4/21/16.
 */
public class Timer {

    protected Context context;
    protected java.util.Timer timer;

    public interface TimeOutListener {
        void timeOut();
    }

    public Timer(Context context){
        this.context = context;
    }

    public Long getTimer(){
        return TimeUnit.MINUTES.toMillis(1);
    }

    public synchronized void start(final TimeOutListener listener) {
        Long timeout = getTimer();
        if (timer == null) {
            if (timeout != null) {
                timer = new java.util.Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        timer.cancel();
                        timer = null;
                        listener.timeOut();
                    }
                }, timeout);
            }
        }
    }

    public void stopTimer() {
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}