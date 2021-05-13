package project.pbo.game.enemy;

import project.pbo.gfx.Assets;

import java.awt.*;

public class Necromancer extends Enemy{
    private int ctrEnemy;
    private int timer;

    public Necromancer(int stage) {
        super("Necromancer", stage, 10, 20);
    }

    @Override
    protected void cetak(Graphics g, int x, int y) {
        g.drawImage(Assets.necroEnemy[ctrEnemy], (x*184)+80+this.x, (y*177)+120+this.y, 105, 95, null);

        if(timer == 0) {
            ctrEnemy = (ctrEnemy == Assets.necroEnemy.length - 1) ? 0 : ++ctrEnemy;
            this.timer++;
        } else {
            if(timer == 7) this.timer = 0;
            else this.timer++;
        }
    }
}
