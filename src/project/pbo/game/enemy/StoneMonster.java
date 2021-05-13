package project.pbo.game.enemy;

import project.pbo.gfx.Assets;

import java.awt.*;

public class StoneMonster extends Enemy {
    private int ctrEnemy;
    private int timer;

    public StoneMonster(int stage) {
        super("Monster Tree", stage, 5, 10);
    }

    @Override
    protected void cetak(Graphics g, int x, int y) {
        g.drawImage(Assets.stoneMonsterEnemy[ctrEnemy], (x*184)+64+this.x, (y*177)+100+this.y, 120, 110, null);

        if(timer == 0) {
            ctrEnemy = (ctrEnemy == Assets.stoneMonsterEnemy.length - 1) ? 0 : ++ctrEnemy;
            this.timer++;
        } else {
            if(timer == 7) this.timer = 0;
            else this.timer++;
        }
    }
}
