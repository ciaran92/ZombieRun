package com.selwyn.ciaran.zombierun.state;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.selwyn.ciaran.zombierun.entities.Bullet;
import com.selwyn.ciaran.zombierun.entities.Controller;
import com.selwyn.ciaran.zombierun.entities.World;
import com.selwyn.ciaran.zombierun.entities.ZombiePlayer;
import com.selwyn.ciaran.zombierun.game.Assets;
import com.selwyn.ciaran.zombierun.game.GameMain;
import com.selwyn.ciaran.zombierun.game.GameView;
import com.selwyn.ciaran.zombierun.utilities.Buttons;
import com.selwyn.ciaran.zombierun.utilities.Drawer;

/**
 * Created by Ciaran on 25/05/2017.
 */
public class GameState extends State {

    ZombiePlayer thePlayer;
    private Buttons btnLeft, btnShoot, btnPause, btnExit, btnContinue, btnRestart;

    boolean flag = false;
    //DoWork doWork;
    int count = 0;
    Controller c = new Controller();
    double time = 0;
    int fireRate = 0;
    boolean playerShooting = false;
    boolean playerJumping = false;

    @Override
    public void init() {
        Log.d("dimens", Assets.level.getHeight() + " " + Assets.level.getWidth() + "");
        btnLeft = new Buttons(24, GameMain.GAME_HEIGHT - 120, 96 + 24, GameMain.GAME_HEIGHT - 24, Assets.btnRight, Assets.btnRight);
        btnShoot = new Buttons(GameMain.GAME_WIDTH - 120, GameMain.GAME_HEIGHT - 120, GameMain.GAME_WIDTH - 24, GameMain.GAME_HEIGHT - 24, Assets.btnLeft, Assets.btnLeft);
        btnPause = new Buttons(GameMain.GAME_WIDTH - 53, 5, GameMain.GAME_WIDTH - 5, 53, Assets.pauseBtn, Assets.pauseBtn);

        btnExit = new Buttons((GameMain.GAME_WIDTH/2)-200, (GameMain.GAME_HEIGHT/2) - 40, (GameMain.GAME_WIDTH/2)-55, (GameMain.GAME_HEIGHT/2) + 2, Assets.exit, Assets.exitHover);
        btnContinue = new Buttons((GameMain.GAME_WIDTH/2)+50, (GameMain.GAME_HEIGHT/2) - 40, (GameMain.GAME_WIDTH/2)+195, (GameMain.GAME_HEIGHT/2) + 2, Assets.play, Assets.playHover);
        btnRestart = new Buttons((GameMain.GAME_WIDTH/2)-30, (GameMain.GAME_HEIGHT/2) + 40, (GameMain.GAME_WIDTH/2)+30, (GameMain.GAME_HEIGHT/2) + 92, Assets.restart, Assets.restartHover);
        thePlayer = new ZombiePlayer();
    }

    @Override
    public void update() {
        if(fireRate > 0) fireRate--;
        thePlayer.update();
        if(playerShooting){
            shoot();
        }
        if(playerJumping){
            jump();
        }
        c.update();
    }

    @Override
    public void render(Drawer g) {

        if(paused){
            g.drawImage(Assets.pauseMenu, (GameMain.GAME_WIDTH/2) - 250, (GameMain.GAME_HEIGHT/2) - 150);
            btnExit.render(g);
            btnContinue.render(g);
            btnRestart.render(g);
        }else{

            g.drawImage(Assets.backdrop, 0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
            loadLevel(Assets.level, g);
            btnLeft.render(g);
            btnShoot.render(g);
            btnPause.render(g);
            thePlayer.render(g);
            c.draw(g);
        }




    }


    private void loadLevel(Bitmap image, Drawer g) {
        int w = image.getWidth();
        //System.out.println("width: " + w);
        int h = image.getHeight();

        int xStart = (int) Math.max(0, GameView.getGameCamera().getxOffset() / 32);
        int xEnd = (int) Math.min(w, (GameView.getGameCamera().getxOffset() + GameMain.GAME_WIDTH) / 32 + 1);
        int yStart = 0;
        int yEnd = GameMain.GAME_HEIGHT;

        for (int y = yStart; y < h; y++) {
            for (int x = xStart; x < xEnd; x++) {
                int pixel = image.getPixel(x, y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 0 && green == 0 && blue == 0) {
                    World.level[x][y] = 0;
                    g.drawImage(Assets.baseTile, x * 32 - (int) GameView.getGameCamera().getxOffset(), y * 32, 32, 32);
                }
                if (red == 64 && green == 64 && blue == 64) {
                    World.level[x][y] = 1;
                    g.drawImage(Assets.surfaceTile, x * 32 - (int) GameView.getGameCamera().getxOffset(), y * 32, 32, 32);
                }
                if (red == 0 && green == 38 && blue == 255) {
                    World.level[x][y] = 2;
                    g.drawImage(Assets.cornerTile2, x * 32 - (int) GameView.getGameCamera().getxOffset(), y * 32, 32, 32);
                }
                if (red == 255 && green == 0 && blue == 0) {
                    World.level[x][y] = 3;
                    g.drawImage(Assets.cornerTile1, x * 32 - (int) GameView.getGameCamera().getxOffset(), y * 32, 32, 32);
                }
                if (red == 255 && green == 216 && blue == 0) {
                    World.level[x][y] = 4;
                    g.drawImage(Assets.sideTile2, x * 32 - (int) GameView.getGameCamera().getxOffset(), y * 32, 32, 32);
                }
                if (red == 255 && green == 0 && blue == 220) {
                    World.level[x][y] = 5;
                    g.drawImage(Assets.sideTile1, x * 32 - (int) GameView.getGameCamera().getxOffset(), y * 32, 32, 32);

                }

            }
        }
    }

    private void drawPlayer(Drawer g) {
        g.drawImage(Assets.player, 48, 48, 48, 64);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event, int scaledX, int scaledY) {

        int pointerIndex = event.getActionIndex();
        int pointerId = event.getPointerId(pointerIndex);
        int action = event.getActionMasked();
        int scaledX2 = (int) ((event.getX(pointerIndex) / v.getWidth()) * GameMain.GAME_WIDTH);
        int scaledY2 = (int) ((event.getY(pointerIndex) / v.getHeight()) * GameMain.GAME_HEIGHT);
        if(!paused) {
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN: {
                    btnLeft.onTouch(scaledX2, scaledY2);
                    btnShoot.onTouch(scaledX2, scaledY2);
                    btnPause.onTouch(scaledX2, scaledY2);

                    if (thePlayer.running == true) {
                        if (btnLeft.isBtnPressed(scaledX2, scaledY2)) {
                            //System.out.println("Down id = " + pointerId + " " + scaledX2 + ", " + scaledY2 + " jump touched");
                            //time = System.currentTimeMillis();
                            thePlayer.jumping = true;
                            thePlayer.setGravity(10);
                        }
                    }
                    if (btnShoot.isBtnPressed(scaledX2, scaledY2)) {
                        playerShooting = true;
                    }
                    break;
                }


                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_UP: {
                    btnLeft.onTouch(scaledX2, scaledY2);
                    btnShoot.onTouch(scaledX2, scaledY2);
                    btnPause.onTouch(scaledX2, scaledY2);

                    if (btnLeft.isBtnPressed(scaledX2, scaledY2)) {
                        thePlayer.jumping = false;
                    }

                    if (btnShoot.isBtnPressed(scaledX2, scaledY2)) {
                        playerShooting = false;
                    }
                    if(paused == false){
                        if(btnPause.isBtnPressed(scaledX, scaledY)){
                            btnPause.cancel();
                            paused = true;
                        }
                    }
                    break;
                }

            }
        }

        if(paused){
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                btnContinue.onTouch(scaledX, scaledY);
                btnExit.onTouch(scaledX, scaledY);
            }

            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (btnContinue.isBtnPressed(scaledX, scaledY)) {
                    btnContinue.cancel();
                    paused = false;
                }
                if(btnExit.isBtnPressed(scaledX, scaledY)){
                    btnExit.cancel();
                    setCurrentState(new MenuState());
                }
                else {
                    btnLeft.cancel();
                }
            }
        }
        return true;
    }

    private void shoot(){
        if(fireRate <= 0){
            c.addBullet(new Bullet(thePlayer.getX(), thePlayer.getY()));
            fireRate = Bullet.FIRE_RATE;
        }
    }

    private void jump() {

    }

    private int getIndex (MotionEvent event){
        int idx = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) & MotionEvent.ACTION_POINTER_INDEX_SHIFT;

        return idx;
    }
}