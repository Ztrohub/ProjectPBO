package project.pbo.game.enemy;

import project.pbo.gfx.Assets;

import java.awt.*;

public class Skeleton extends Enemy {
    public Skeleton( int stage) {
        super("Skeleton", stage, 1, 5);
    }

    @Override
    protected void cetak(Graphics g, int x, int y) {
        g.drawImage(Assets.card, (x*184)+40+this.x, (y*177)+80+this.y, 184, 177, null);
    }
}
