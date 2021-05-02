package project.pbo.account;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private final Player player;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        player = new Player();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Player getPlayer() {
        return player;
    }
}
