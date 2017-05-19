package com.example.ellis.haxddesign.Model;

import com.backendless.BackendlessUser;

/**
 * Created by Ellis on 5/3/2017.
 */

public class Player {
    //Hack/Caught Ratio
    public int hcr;
    //private ArrayList<PowerUp> powers = new ArrayList<>();
    public boolean busy;
    public BackendlessUser user;
    public String name;
    public String objectId;
    public String ownerId;

    public Player() {
        hcr = 0;
        busy = false;
    }
    public Player(String name) {
        hcr = 0;
        this.name = name;
        busy = false;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
