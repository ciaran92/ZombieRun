package com.selwyn.ciaran.zombierun.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import android.graphics.BitmapFactory.Options;

/**
 * Created by Ciaran on 24/05/2017.
 */
public class Assets {

    public static Bitmap homeScreen, playBtn, playBtnPressed, level, player, grass, backdrop, pauseBtn, tileset, baseTile, surfaceTile, cornerTile1, cornerTile2,
            sideTile1, sideTile2, buttons, btnLeft, btnRight;

    public static Bitmap[] player1;

    public static void load(){
        homeScreen = loadBitmap("menuScreen.png", false);
        playBtn = loadBitmap("play.png", false);
        playBtnPressed = loadBitmap("playPressed.png", false);
        level = loadBitmap("level.png", false);
        tileset = loadBitmap("platformertiles.png", false);
        player = loadBitmap("player.png", false);
        grass = loadBitmap("grass.png", false);
        backdrop = loadBitmap("backdrop.png", false);
        pauseBtn = loadBitmap("pauseBtn.png", false);
        buttons = loadBitmap("buttons.png", false);

        player1 = new Bitmap[11];

        player1[0] = loadBitmap("walk0.png", false);
        player1[1] = loadBitmap("walk1.png", false);
        player1[2] = loadBitmap("walk2.png", false);
        player1[3] = loadBitmap("walk3.png", false);
        player1[4] = loadBitmap("walk4.png", false);
        player1[5] = loadBitmap("walk5.png", false);
        player1[6] = loadBitmap("walk6.png", false);
        player1[7] = loadBitmap("walk7.png", false);
        player1[8] = loadBitmap("walk8.png", false);
        player1[9] = loadBitmap("walk9.png", false);
        player1[10] = loadBitmap("walk10.png", false);

        baseTile = Bitmap.createBitmap(tileset, 32, 32, 32, 32);
        surfaceTile = Bitmap.createBitmap(tileset, 32, 0, 32, 32);
        cornerTile1 = Bitmap.createBitmap(tileset, 64, 0, 32, 32);
        cornerTile2 = Bitmap.createBitmap(tileset, 0, 0, 32, 32);
        sideTile1 = Bitmap.createBitmap(tileset, 0, 32, 32, 32);
        sideTile2 = Bitmap.createBitmap(tileset, 64, 32, 32, 32);

        btnLeft = Bitmap.createBitmap(buttons, 0, 0, 32, 32);
        btnRight = Bitmap.createBitmap(buttons, 32, 0, 32, 32);
    }

    private static Bitmap loadBitmap(String filename, boolean transparency){
        InputStream inputStream = null;
        try{
            inputStream = GameMain.assets.open(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Options options = new Options();
        if(transparency){
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        }else{
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }

        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, new Options());
        return bitmap;
    }
}
