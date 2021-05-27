package project.pbo.account;

import java.io.Serializable;
import java.util.ArrayList;

public class DB implements Serializable {
    private ArrayList<User> users;
    private double vol;

    public DB() {
        users = new ArrayList<>();
        vol = 0.1;
    }

    public ArrayList <User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public double getVol() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol = vol;
    }
}
