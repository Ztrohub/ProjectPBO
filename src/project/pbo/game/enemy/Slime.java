package project.pbo.game.enemy;

import project.pbo.gfx.Assets;

import java.awt.*;

public class Slime extends Enemy{

    public Slime(int stage) {
        super("Slime", stage, 1, 3);
    }

    @Override
    protected void cetak(Graphics g, int x, int y) {
        g.drawImage(Assets.card, (x*184)+40+this.x, (y*177)+80+this.y, 184, 177, null);
    }
}
