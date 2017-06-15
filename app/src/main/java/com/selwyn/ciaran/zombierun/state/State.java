package com.selwyn.ciaran.zombierun.state;

import android.view.MotionEvent;

import com.selwyn.ciaran.zombierun.game.GameMain;
import com.selwyn.ciaran.zombierun.utilities.Drawer;

/**
 * Created by Ciaran on 24/05/2017.
 */
public abstract class State {

    public boolean paused = false;

    public void setCurrentState(State newState){
        GameMain.gameView.setCurrentState(newState);
    }

    public abstract void init();
    public abstract void update();
    public abstract void render(Drawer g);
    public abstract boolean onTouch(MotionEvent e, int scaledX, int scaledY);
}
