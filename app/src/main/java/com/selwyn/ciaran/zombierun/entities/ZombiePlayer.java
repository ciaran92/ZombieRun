package com.selwyn.ciaran.zombierun.entities;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.widget.GridView;

import com.selwyn.ciaran.zombierun.animation.Animation;
import com.selwyn.ciaran.zombierun.game.Assets;
import com.selwyn.ciaran.zombierun.game.GameView;
import com.selwyn.ciaran.zombierun.utilities.Drawer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ciaran on 25/05/2017.
 */
public class ZombiePlayer {

    public int positionX, positionY;
    public boolean jumping = false;
    public boolean falling = true;
    public int velX, velY;
    public double gravity = 0.0;

    Animation moving;
    private Rect entityBounds;

    public ZombiePlayer(){
        positionX = 160+48;
        positionY = 352-64;

        moving = new Animation(50, Assets.player1);
        entityBounds = new Rect(positionX, positionY, positionX+48, positionY+64);
    }

    public void update(){
        if(jumping){
            gravity -= 0.1;
            setVelY((int)-gravity);
            if(gravity <= 0.0){
                jumping = false;
                falling = true;
            }
        }
        if(falling){
            gravity += 0.1;
            setVelY((int)gravity);
        }else{
            if(!falling && !jumping){
                gravity = 0.0;
                falling = true;
            }
        }
        positionX += 5;
        positionY += velY;

        GameView.getGameCamera().centerOnPlayer(ZombiePlayer.this);
    }

    public void moveLeft(){
        positionX-=5;
        GameView.getGameCamera().centerOnPlayer(ZombiePlayer.this);
    }

    public void moveRight(){
        positionX+=5;
        GameView.getGameCamera().centerOnPlayer(ZombiePlayer.this);
    }

    public void render(Drawer g){
        g.drawImage(getCurrentAnimationFrame(), (int)(positionX - GameView.getGameCamera().getxOffset()), positionY, 48, 64);
        g.drawRect(entityBounds);
    }

    public int getX(){
        return positionX;
    }



    public Bitmap getCurrentAnimationFrame(){
        return moving.getCurrentFrame();
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
}
