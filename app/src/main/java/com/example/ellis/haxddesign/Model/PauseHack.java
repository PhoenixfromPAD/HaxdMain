package com.example.ellis.haxddesign.Model;

/**
 * Created by Ellis on 5/11/2017.
 */

public class PauseHack extends PowerUp {

    public PauseHack(){
    }

    public void onUse(HackTimer hackTimer){
        try {
            hackTimer.pauseTimer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
