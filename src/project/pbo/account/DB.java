package project.pbo.account;

import java.io.Serializable;
import java.util.ArrayList;

public class DB implements Serializable {
    private ArrayList<User> users;

    public DB() {
        users = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
