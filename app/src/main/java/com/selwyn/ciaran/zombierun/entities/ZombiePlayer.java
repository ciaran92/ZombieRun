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
    public boolean running = true;
    public int velX, velY;
    public double gravity = 0.0;

    Animation moving;
    Animation playerJumpAnim;
    private Rect entityBounds;

    public ZombiePlayer(){
        positionX = 50;
        positionY = 288;

        moving = new Animation(100, Assets.player1, false);
        playerJumpAnim = new Animation(100, Assets.playerJump, true);
        //entityBounds = new Rect(positionX - (int)GameView.getGameCamera().getxOffset(), positionY, 48 + (positionX - (int)GameView.getGameCamera().getxOffset()), positionY+64);
        entityBounds = new Rect();
    }

    public void update(){
        positionY += velY;
        //positionX += velX;
        //System.out.println("bottom: " + World.level[positionX/32][(positionY + (entityBounds.bottom - entityBounds.top))/32]);
        //System.out.println("world: " + (positionY + (entityBounds.bottom - entityBounds.top)));
        if(World.level[positionX/32][(positionY + (entityBounds.bottom - entityBounds.top))/32] == 1 || World.level[positionX/32][(positionY + (entityBounds.bottom - entityBounds.top))/32] == 2 || World.level[positionX/32][(positionY + (entityBounds.bottom - entityBounds.top))/32] == 3
                || World.level[(positionX + (entityBounds.right-entityBounds.left))/32][(positionY + (entityBounds.bottom - entityBounds.top))/32] == 1 || World.level[(positionX + (entityBounds.right-entityBounds.left))/32][(positionY + (entityBounds.bottom - entityBounds.top))/32] == 2
                || World.level[(positionX + (entityBounds.right-entityBounds.left))/32][(positionY + (entityBounds.bottom - entityBounds.top))/32] == 3){
            //System.out.println("echo");
            velY = 0;
            if(falling) falling = false;
            running = true;
        }else{
            if(!falling && !jumping){
                gravity = 0.0;
                falling = true;
            }
        }
        if(jumping){
            gravity -= 0.3;
            //System.out.println("gravity " + gravity);
            setVelY ((int)-gravity);
            if(gravity <= 0.0){
                jumping = false;
                falling = true;
            }
        }
        if(falling){
            gravity += 0.4;
            running = false;
            setVelY((int)gravity);
        }
        positionX += 5;
        //positionY += velY;
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
        entityBounds.left = (int)(positionX - GameView.getGameCamera().getxOffset());
        entityBounds.right = 48 + entityBounds.left;
        entityBounds.top = positionY;
        entityBounds.bottom = 64 + entityBounds.top;
        g.drawRect(entityBounds);
    }

    public int getX(){
        return positionX;
    }



    public Bitmap getCurrentAnimationFrame(){

        if(jumping){
            return playerJumpAnim.getCurrentFrame();
        }else{
            return moving.getCurrentFrame();
        }

    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public void setGravity(double gravity){
        this.gravity = gravity;
    }
}
