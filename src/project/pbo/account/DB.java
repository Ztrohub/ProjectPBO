package project.pbo.account;

import java.io.Serializable;
import java.util.ArrayList;

public class DB implements Serializable {
    private Generic<User> users;
    private double vol;

    public DB() {
        users = new Generic<>();
        vol = 0.1;
    }

    public Generic <User> getUsers() {
        return users;
    }

    public void setUsers(Generic<User> users) {
        this.users = users;
    }

    public double getVol() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol = vol;
    }
}
