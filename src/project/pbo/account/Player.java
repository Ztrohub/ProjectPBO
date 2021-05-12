package project.pbo.account;

import java.io.Serializable;

public class Player implements Serializable {
    private int gold = 0, health = 11, highestStep = 0;
    private int damage = 0, defend = 0;


    public int getHighestStep() {
        return highestStep;
    }

    public void setHighestStep(int highestStep) {
        this.highestStep = highestStep;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefend() {
        return defend;
    }

    public void setDefend(int defend) {
        this.defend = defend;
    }


}
