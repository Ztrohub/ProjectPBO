package project.pbo.game;

public class PlayerCard extends Card {

    private int health, defend, damage, maxHealth;
    public PlayerCard(int health, int defend, int damage, int maxHealth) {
        super( "P");
        this.health = health;
        this.defend = defend;
        this.damage = damage;
        this.maxHealth = maxHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefend() {
        return defend;
    }

    public void setDefend(int defend) {
        this.defend = defend;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
