package com.selwyn.ciaran.zombierun.utilities;

import android.view.MotionEvent;
import android.view.View;

import com.selwyn.ciaran.zombierun.game.GameMain;
import com.selwyn.ciaran.zombierun.state.State;

/**
 * Created by Ciaran on 25/05/2017.
 */
public class Handler implements View.OnTouchListener{

    private State currentState;

    public void setState(State currentState){
        this.currentState = currentState;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int scaledX = (int) ((event.getX() / v.getWidth()) * GameMain.GAME_WIDTH);
        int scaledY = (int) ((event.getY() / v.getHeight()) * GameMain.GAME_HEIGHT);
        return currentState.onTouch(v, event, scaledX, scaledY);
    }
}
