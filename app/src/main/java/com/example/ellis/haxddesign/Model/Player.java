package com.example.ellis.haxddesign.Model;

import com.backendless.BackendlessUser;

import java.util.ArrayList;

/**
 * Created by Ellis on 5/3/2017.
 */

public class Player {
    //Hack/Caught Ratio
    private int hcr;
    private ArrayList<PowerUp> powers = new ArrayList<>();
    private boolean busy;
    private BackendlessUser user;
    public String objectId;
    public String ownerId;

    public Player() {
        hcr = 0;
        getLocation();
    }

    private void getLocation() {
        //Get Location
    }

    public int getHcr() {
        return hcr;
    }

    public void setHcr(int hcr) {
        this.hcr = hcr;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public BackendlessUser getUser() {
        return user;
    }

    public void setUser(BackendlessUser user) {
        this.user = user;
    }
}
