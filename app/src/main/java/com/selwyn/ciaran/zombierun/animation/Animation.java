package com.selwyn.ciaran.zombierun.animation;

import android.graphics.Bitmap;

import java.io.BufferedInputStream;

/**
 * Created by Ciaran on 26/05/2017.
 */
public class Animation {

    int speed, index;
    long lastTime, timer;
    Bitmap[] frames;
    boolean jumping;

    public Animation(int speed, Bitmap[] frames, boolean jumping){
        this.speed = speed;
        this.frames = frames;
        this.jumping = jumping;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(!jumping){
            if(timer > speed){
                index++;
                timer = 0;
                if(index >= frames.length){
                    index = 0;
                }
            }
        }else{
            index = 0;
            timer = 0;
            if(timer > speed){
                index++;
                timer = 0;

                if(index == frames.length){
                    System.out.println("length " + index);
                    return;
                }
            }
        }

    }

    public Bitmap getCurrentFrame(){
        tick();

        return frames[index];
    }
}
