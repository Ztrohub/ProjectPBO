package project.pbo.game;

import java.awt.*;

public class PlayerCard extends Character{

    int damage, defend;

    public PlayerCard(int health, int x, int y, int damage, int defend) {
        super( "P", health, x, y);
        this.damage = damage;
        this.defend = defend;
    }
}
