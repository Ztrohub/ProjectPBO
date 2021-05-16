package project.pbo.game;

import project.pbo.gfx.Assets;

import java.awt.*;

public class PlayerCard extends Card {


    private int ctrEnemy;
    private int timer;

    private int health, defend, damage, maxHealth;
    public PlayerCard(int health, int defend, int damage, int maxHealth) {
        super( "Hero");
        this.health = health;
        this.defend = defend;
        this.damage = damage;
        this.maxHealth = maxHealth;
    }

    @Override
    protected void cetak(Graphics g, int x, int y) {
        g.drawImage(Assets.player[ctrEnemy], (x*184)+57+this.x, (y*177)+90+this.y, 140, 130, null);

        if(timer == 0) {
            ctrEnemy = (ctrEnemy == Assets.player.length - 1) ? 0 : ++ctrEnemy;
            this.timer++;
        } else {
            if(timer == 7) this.timer = 0;
            else this.timer++;
        }
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
