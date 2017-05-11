package com.example.ellis.haxddesign.Model;

import android.util.Log;

/**
 * Created by Ellis on 5/11/2017.
 */

public class HackTimer {

    private Thread thread;
    private int progress, currentTime;

    public HackTimer() {
        progress = 0;
        currentTime = 0;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = currentTime; i < 100; i++){
                        thread.sleep(1000);
                        progress++;
                        currentTime++;
                    }
                    Log.d("ASD", "Hack done");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public int getProgress() {
        return progress;
    }

    public void pauseTimer() throws InterruptedException {
        thread.interrupt();
        thread.sleep(5000);
        thread.start();
    }
}
