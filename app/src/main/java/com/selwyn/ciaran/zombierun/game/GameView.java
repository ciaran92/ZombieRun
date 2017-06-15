package com.selwyn.ciaran.zombierun.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.selwyn.ciaran.zombierun.state.LoadingState;
import com.selwyn.ciaran.zombierun.state.State;
import com.selwyn.ciaran.zombierun.utilities.Camera;
import com.selwyn.ciaran.zombierun.utilities.Drawer;
import com.selwyn.ciaran.zombierun.utilities.Handler;

/**
 * Created by Ciaran on 24/05/2017.
 */
public class GameView extends SurfaceView{

    private MainThread mainThread;
    private State currentState;
    private Bitmap gameImage;
    private Rect gameImageSrc;
    private Rect gameImageDst;
    private Canvas gameCanvas;
    private Drawer drawer;
    private Handler inputHandler;
    public static Camera gameCamera;

    public GameView(Context context, int gameWidth, int gameHeight){
        super(context);

        gameImage = Bitmap.createBitmap(gameWidth, gameHeight, Bitmap.Config.RGB_565);
        gameImageSrc = new Rect(0,0,gameImage.getWidth(),gameImage.getHeight());
        gameImageDst = new Rect();
        gameCanvas = new Canvas(gameImage);
        drawer = new Drawer(gameCanvas);


        SurfaceHolder holder = getHolder();
        mainThread = new MainThread(holder, this);
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.d("GameView", "Surface Created");
                initInput();
                if(currentState == null){
                    setCurrentState(new LoadingState());
                }
                init();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d("GameView", "Surface destroyed");
                pauseGame();
            }
        });
    }


    public void update(){
        //Log.d("GameView", "ive been called");
        if(currentState.paused){
            try {
                mainThread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            currentState.update();

        }
        currentState.render(drawer);
        render();


    }

    private void render(){
        Canvas screen = getHolder().lockCanvas();
        if(screen != null){
            screen.getClipBounds(gameImageDst);
            screen.drawBitmap(gameImage, gameImageSrc, gameImageDst, null);
            getHolder().unlockCanvasAndPost(screen);
        }

    }

    public void init(){

        mainThread = new MainThread(getHolder(), this);
        mainThread.setRunning(true);
        mainThread.start();
        gameCamera = new Camera(0,0);
    }

    public void pauseGame(){
        mainThread.setRunning(false);
        while(mainThread.isAlive()){
            try {
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCurrentState(State newState){
        System.gc();
        newState.init();
        currentState = newState;
        inputHandler.setState(currentState);
    }

    public void initInput(){
        if(inputHandler == null){
            inputHandler = new Handler();
        }
        setOnTouchListener(inputHandler);
    }

    public static Camera getGameCamera(){
        return gameCamera;
    }




}
