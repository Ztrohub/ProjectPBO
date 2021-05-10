package project.pbo.game.enemy;

import project.pbo.game.Card;
import java.util.Random;

public abstract class Enemy extends Card {

    private int health;

    public Enemy(String symbol, int stage, int minHealth, int maxHealth) {
        super(symbol);
        int a = (stage / 10) + 1;
        maxHealth *= a;
        minHealth *= a;
        Random rand = new Random();
        this.health = rand.nextInt(maxHealth-minHealth) + minHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
