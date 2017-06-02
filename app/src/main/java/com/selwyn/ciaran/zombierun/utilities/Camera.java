package com.selwyn.ciaran.zombierun.utilities;

import com.selwyn.ciaran.zombierun.entities.ZombiePlayer;
import com.selwyn.ciaran.zombierun.game.GameMain;

/**
 * Created by Ciaran on 26/05/2017.
 */
public class Camera {

    double xOffset, yOffset;

    public Camera(double xOffset, double yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void centerOnPlayer(ZombiePlayer player){
        xOffset = player.getX() - GameMain.GAME_WIDTH/4;
    }

    public double getxOffset(){
        return xOffset;
    }

    public void setxOffset(double xOffset){
        this.xOffset = xOffset;
    }
}
