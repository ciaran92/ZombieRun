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
    double velocityX, velocityY;
    double gravity = 0.0;
    boolean jumping = false;
    boolean falling = true;

    Animation moving;
    private Rect entityBounds;

    public ZombiePlayer(){
        positionX = 160+48;
        positionY = 352-64;

        moving = new Animation(50, Assets.player1);
        entityBounds = new Rect(positionX, positionY, positionX+48, positionY+64);
    }

    public void update(){

        velocityY += gravity;
        positionY += velocityY;

        if(positionY > 288){
            positionY = 288;
            velocityY = 0.0;
            //onGround = true;
        }


    }

    public void startJump(){
        /*if(onGround){
            velocityY = -12.0;
            onGround = false;
        }*/
    }

    public void endJump(){
        if(velocityY < -6.0){
            velocityY = -6.0;
        }
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
        g.drawImage(getCurrentAnimationFrame(), (int)(positionX - GameView.getGameCamera().getxOffset()), positionX, 48, 64);
        g.drawRect(entityBounds);
    }

    public int getX(){
        return positionX;
    }



    public Bitmap getCurrentAnimationFrame(){
        return moving.getCurrentFrame();
    }


}
