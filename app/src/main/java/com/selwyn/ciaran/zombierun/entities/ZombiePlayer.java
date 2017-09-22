package com.selwyn.ciaran.zombierun.entities;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
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
    public double velY = 0;
    public double gravity = 0.0;
    private final double MIN_HEIGHT = 220;
    public double jumpTime;

    Animation moving;
    Animation playerJumpAnim;
    private Rect entityBounds;

    public ZombiePlayer(){
        positionX = 50;
        positionY = 258;

        moving = new Animation(100, Assets.player1, false);
        playerJumpAnim = new Animation(100, Assets.playerJump, true);
        //entityBounds = new Rect(positionX - (int)GameView.getGameCamera().getxOffset(), positionY, 48 + (positionX - (int)GameView.getGameCamera().getxOffset()), positionY+64);
        entityBounds = new Rect();
    }

    public void update(){
        System.out.println("posY  " + positionY);
        positionY += velY;
        //positionX += velX;
        //System.out.println("bottom: " + World.level[positionX/32][(positionY + (entityBounds.bottom - entityBounds.top))/32]);
        //System.out.println("world: " + (positionY + (entityBounds.bottom - entityBounds.top)));
        //below statement checks the points on the bounding box in order: top left, top right, bottom left, bottom right
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
            gravity -= 0.35;
            setVelY(-gravity);

            if(gravity<=0){
                //System.out.println("working");
                jumping = false;
                falling = true;
            }
        }

        if(falling){
            gravity += 0.35;
            running = false;
            setVelY(gravity);
        }

        positionX += 5;
        //positionY += velY;
        GameView.getGameCamera().centerOnPlayer(ZombiePlayer.this);
    }

    public void render(Drawer g){

        g.drawImage(getCurrentAnimationFrame(), (int)(positionX - GameView.getGameCamera().getxOffset()), positionY, 64, 96);
        entityBounds.left = (int)(positionX - GameView.getGameCamera().getxOffset());
        entityBounds.right = 64 + entityBounds.left;
        entityBounds.top = positionY;
        entityBounds.bottom = 96 + entityBounds.top;
        g.drawRect(entityBounds);
        //System.out.println("posY " + positionY);

    }

    public int getX(){
        return positionX;
    }

    public int getY(){
        return positionY;
    }



    public Bitmap getCurrentAnimationFrame(){

        if(jumping){
            return playerJumpAnim.getCurrentFrame();
        }else{
            return moving.getCurrentFrame();
        }

    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public void setGravity(double gravity){
        this.gravity = gravity;
    }
}
