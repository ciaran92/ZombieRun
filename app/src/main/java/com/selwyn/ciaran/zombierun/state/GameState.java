package com.selwyn.ciaran.zombierun.state;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.MotionEvent;

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
    private Buttons btnLeft, btnRight;

    boolean flag = false;
    DoWork doWork;
    int count = 0;

    @Override
    public void init() {
        Log.d("dimens", Assets.level.getHeight() + " " + Assets.level.getWidth() + "");
        btnLeft = new Buttons(24, GameMain.GAME_HEIGHT - 48, 32 + 24, 32 + (GameMain.GAME_HEIGHT - 48), Assets.btnLeft, Assets.btnLeft);
        btnRight = new Buttons(GameMain.GAME_WIDTH - 48, GameMain.GAME_HEIGHT - 48, 32 + (GameMain.GAME_WIDTH - 48), 32 + (GameMain.GAME_HEIGHT - 48), Assets.btnRight, Assets.btnRight);
        thePlayer = new ZombiePlayer();
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Drawer g) {
        g.drawImage(Assets.backdrop, 0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
        g.drawImage(Assets.pauseBtn, GameMain.GAME_WIDTH - 48, 5);

        loadLevel(Assets.level, g);
        btnLeft.render(g);
        btnRight.render(g);
        thePlayer.render(g);
        g.drawImage(Assets.sideTile1, 32, 32, 32, 32);
        g.drawImage(Assets.sideTile2, 0, 0, 32, 32);

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
                    g.drawImage(Assets.baseTile, x * 32 - (int) GameView.getGameCamera().getxOffset(), y * 32, 32, 32);
                }
                if (red == 64 && green == 64 && blue == 64) {
                    g.drawImage(Assets.surfaceTile, x * 32 - (int) GameView.getGameCamera().getxOffset(), y * 32, 32, 32);
                }
                if (red == 0 && green == 38 && blue == 255) {
                    g.drawImage(Assets.cornerTile2, x * 32 - (int) GameView.getGameCamera().getxOffset(), y * 32, 32, 32);
                }
                if (red == 255 && green == 0 && blue == 0) {
                    g.drawImage(Assets.cornerTile1, x * 32 - (int) GameView.getGameCamera().getxOffset(), y * 32, 32, 32);
                }
                if (red == 255 && green == 216 && blue == 0) {
                    g.drawImage(Assets.sideTile2, x * 32 - (int) GameView.getGameCamera().getxOffset(), y * 32, 32, 32);
                }
                if (red == 255 && green == 0 && blue == 220) {
                    g.drawImage(Assets.sideTile1, x * 32 - (int) GameView.getGameCamera().getxOffset(), y * 32, 32, 32);
                }
            }
        }
    }

    private void drawPlayer(Drawer g) {
        g.drawImage(Assets.player, 48, 48, 48, 64);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        String method;
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            btnLeft.onTouch(scaledX, scaledY);
            btnRight.onTouch(scaledX, scaledY);
            if(btnLeft.isBtnPressed(scaledX,scaledY)){
                flag = true;
                method = "left";
                doWork = new DoWork();
                doWork.execute(method);
            }
            if(btnRight.isBtnPressed(scaledX, scaledY)){
                flag = true;
                method = "right";
                doWork = new DoWork();
                doWork.execute(method);
            }
        }
        if(e.getAction() == MotionEvent.ACTION_UP){

                btnLeft.cancel();
                btnRight.cancel();
                flag = false;
                doWork.cancel(false);
        }

        return true;
    }


    class DoWork extends AsyncTask<String, Void, Void> {


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(String... params) {
            String method = params[0];

            if(method.equals("left"))
            while (flag) {
                synchronized (this){
                    this.sleep(13);
                    thePlayer.moveLeft();
                }
            }
            if(method.equals("right"))
                while (flag) {
                    synchronized (this){
                        this.sleep(13);
                        thePlayer.moveRight();
                    }
                }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values){

        }

        @Override
        protected void onPostExecute(Void result) {

        }

        private void sleep(int delay) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Log.e("TAG", e.toString());
            }
        }
    }

}