package com.selwyn.ciaran.zombierun.utilities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Ciaran on 24/05/2017.
 */
public class Drawer {

    private Canvas canvas;
    private Paint paint;
    private Rect srcRect;
    private Rect dstRect;

    public Drawer(Canvas canvas){
        this.canvas = canvas;
        paint = new Paint();
        srcRect = new Rect();
        dstRect = new Rect();
    }

    public void setColour(int colour){
        paint.setColor(colour);
    }

    public void fillRect(int x, int y, int width, int height){
        dstRect.set(x,y,x + width, y + height);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(dstRect, paint);
    }

    public void drawImage(Bitmap image, int x, int y){
        canvas.drawBitmap(image, x, y, paint);
    }

    public void drawImage(Bitmap image, int x, int y, int width, int height){
        srcRect.set(0, 0, image.getWidth(), image.getHeight());
        dstRect.set(x, y, x + width, y + height);
        canvas.drawBitmap(image, srcRect, dstRect, paint);
    }

    public void drawRect(Rect rect){
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect, paint);
    }
    public void drawRect(int x, int y, int width, int height){

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Paint getPaint() {
        return paint;
    }
}
