package com.selwyn.ciaran.zombierun.game;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class GameMain extends Activity {

    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 448;
    public static GameView gameView;
    public static AssetManager assets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assets = getAssets();
        gameView = new GameView(this, GAME_WIDTH, GAME_HEIGHT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(gameView);
    }
}
