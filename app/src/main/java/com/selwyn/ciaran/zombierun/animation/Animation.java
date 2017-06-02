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

    public Animation(int speed, Bitmap[] frames){
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed){
            index++;
            timer = 0;
            if(index >= frames.length){
                index = 0;
            }
        }
    }

    public Bitmap getCurrentFrame(){
        tick();
        return frames[index];
    }
}
