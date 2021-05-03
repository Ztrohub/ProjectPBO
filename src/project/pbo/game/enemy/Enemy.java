package project.pbo.game.enemy;

import project.pbo.game.Character;

import java.util.Random;

public abstract class Enemy extends Character{

    public Enemy(String symbol, int stage, int minHealth, int maxHealth) {
        super(symbol);
        int a = (stage / 10) + 1;
        maxHealth *= a;
        minHealth *= a;
        Random rand = new Random();
        int temp = rand.nextInt(maxHealth-minHealth) + minHealth;
        this.setHealth(temp);
    }
}
