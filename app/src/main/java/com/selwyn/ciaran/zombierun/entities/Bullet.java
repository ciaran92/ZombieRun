package com.selwyn.ciaran.zombierun.entities;

import com.selwyn.ciaran.zombierun.game.GameView;
import com.selwyn.ciaran.zombierun.utilities.Drawer;

/**
 * Created by Ciaran on 16/09/2017.
 */
public class Bullet {

    private int x;
    private int y;
    public static final int FIRE_RATE = 15;

    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void update(){
        x+=10;
    }

    public void render(Drawer g){
        g.drawRect((int)(x - GameView.getGameCamera().getxOffset()), y, 32, 32);
    }
}
