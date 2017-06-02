package com.selwyn.ciaran.zombierun.state;

import android.util.Log;
import android.view.MotionEvent;

import com.selwyn.ciaran.zombierun.game.Assets;
import com.selwyn.ciaran.zombierun.utilities.Buttons;
import com.selwyn.ciaran.zombierun.utilities.Drawer;

/**
 * Created by Ciaran on 24/05/2017.
 */
public class MenuState extends State{

    private Buttons playBtn;

    @Override
    public void init() {
        playBtn = new Buttons(316, 277, 484, 336, Assets.playBtn, Assets.playBtnPressed);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Drawer g) {
        g.drawImage(Assets.homeScreen, 0, 0);
        playBtn.render(g);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {

        if(e.getAction() == MotionEvent.ACTION_DOWN){
            playBtn.onTouch(scaledX, scaledY);
        }

        if(e.getAction() == MotionEvent.ACTION_UP){
            if(playBtn.isBtnPressed(scaledX, scaledY)){
                playBtn.cancel();
                setCurrentState(new GameState());
                Log.d("menu state", "play btn");
            }else{
                playBtn.cancel();
            }
        }
        return true;
    }
}
