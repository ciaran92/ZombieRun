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
            sideTile1, sideTile2, buttons, btnLeft, btnRight, voidTile, playerTile, pauseTileset, pauseMenu, exit, exitHover, play, playHover, restart, restartHover,
            run1, run2, run3, run4, run5, run6, weaponAK47;

    public static Bitmap[] player1, playerJump;

    public static void load(){
        homeScreen = loadBitmap("menuScreen.png", false);
        playBtn = loadBitmap("play.png", false);
        playBtnPressed = loadBitmap("playPressed.png", false);
        level = loadBitmap("level.png", false);
        tileset = loadBitmap("tiles.png", false);
        player = loadBitmap("player.png", false);
        grass = loadBitmap("grass.png", false);
        backdrop = loadBitmap("backdrop.png", false);
        pauseBtn = loadBitmap("pauseBtn.png", false);
        buttons = loadBitmap("buttons.png", false);
        voidTile = loadBitmap("voidtile.png", false);
        playerTile = loadBitmap("mvSatyr.png", false);
        pauseMenu = loadBitmap("pauseMenu.png", false);
        pauseTileset = loadBitmap("pausebtns.png", false);
        run1 = loadBitmap("Run1.png", false);
        run2 = loadBitmap("Run2.png", false);
        run3 = loadBitmap("Run3.png", false);
        run4 = loadBitmap("Run4.png", false);
        run5 = loadBitmap("Run5.png", false);
        run6 = loadBitmap("Run6.png", false);

        player1 = new Bitmap[6];
        weaponAK47 = Bitmap.createBitmap((loadBitmap("AK47.png", false)), 0, 0, 64, 32);
        player1[0] = Bitmap.createBitmap(playerTile, 32, 0, 32, 64);
        player1[1] = Bitmap.createBitmap(playerTile, 64, 0, 32, 64);
        player1[2] = Bitmap.createBitmap(playerTile, 96, 0, 32, 64);
        player1[3] = Bitmap.createBitmap(playerTile, 128, 0, 32, 64);
        player1[4] = Bitmap.createBitmap(playerTile, 160, 0, 32, 64);
        player1[5] = Bitmap.createBitmap(playerTile, 192, 0, 32, 64);

        playerJump = new Bitmap[3];
        playerJump[0] = Bitmap.createBitmap(playerTile, 224, 64, 32, 64);
        playerJump[1] = Bitmap.createBitmap(playerTile, 256, 64, 32, 64);
        playerJump[2] = Bitmap.createBitmap(playerTile, 288, 64, 32, 64);

        baseTile = Bitmap.createBitmap(tileset, 32, 32, 32, 32);
        surfaceTile = Bitmap.createBitmap(tileset, 32, 0, 32, 32);
        cornerTile1 = Bitmap.createBitmap(tileset, 64, 0, 32, 32);
        cornerTile2 = Bitmap.createBitmap(tileset, 0, 0, 32, 32);
        sideTile1 = Bitmap.createBitmap(tileset, 0, 32, 32, 32);
        sideTile2 = Bitmap.createBitmap(tileset, 64, 32, 32, 32);

        exit = Bitmap.createBitmap(pauseTileset, 0, 0, 145, 42);
        exitHover = Bitmap.createBitmap(pauseTileset, 145, 0, 145, 42);
        play = Bitmap.createBitmap(pauseTileset, 0, 42, 145, 42);
        playHover = Bitmap.createBitmap(pauseTileset, 145, 42, 145, 42);
        restart = Bitmap.createBitmap(pauseTileset, 0, 84, 60, 42);
        restartHover = Bitmap.createBitmap(pauseTileset, 60, 84, 60, 42);

        btnLeft = Bitmap.createBitmap(buttons, 0, 0, 96, 96);
        btnRight = Bitmap.createBitmap(buttons, 96, 0, 96, 96);
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
