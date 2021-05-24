package project.pbo.account;

import java.io.Serializable;

public class Player implements Serializable {
    private int gold = 0, health = 11, highestStep = 0;
    private int healthlvl = 1,dmglvl =1, defendlvl = 1;
    private int maxdmg = 12, maxdef = 12;

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

    public int getMaxdmg() {
        return maxdmg;
    }

    public void setMaxdmg(int maxdmg) {
        this.maxdmg = maxdmg;
    }

    public int getMaxdef() {
        return maxdef;
    }

    public void setMaxdef(int maxdef) {
        this.maxdef = maxdef;
    }

    public int getHealthlvl() {
        return healthlvl;
    }

    public void setHealthlvl(int healthlvl) {
        this.healthlvl = healthlvl;
    }

    public int getDmglvl() {
        return dmglvl;
    }

    public void setDmglvl(int dmglvl) {
        this.dmglvl = dmglvl;
    }

    public int getDefendlvl() {
        return defendlvl;
    }

    public void setDefendlvl(int defendlvl) {
        this.defendlvl = defendlvl;
    }
}
