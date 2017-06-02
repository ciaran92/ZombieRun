package com.selwyn.ciaran.zombierun.utilities;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by Ciaran on 24/05/2017.
 */
public class Buttons {

    private Rect rectButton;
    private boolean btnPressed = false;
    private Bitmap imageButton, buttonPressedImage;

    public Buttons(int left, int top, int right, int bottom, Bitmap imageButton, Bitmap pressedButtonImage){
        rectButton = new Rect(left, top, right, bottom);
        this.imageButton = imageButton;
        this.buttonPressedImage = pressedButtonImage;
    }

    public void render(Drawer drawer){
        Bitmap currentButtonImage = btnPressed ? buttonPressedImage : imageButton;
        drawer.drawImage(currentButtonImage, rectButton.left, rectButton.top, rectButton.width(), rectButton.height());
    }

    public void onTouch(int touchX, int touchY){
        if(rectButton.contains(touchX, touchY)){
            btnPressed = true;
        }else{
            btnPressed = false;
        }
    }

    public void cancel(){
        btnPressed = false;
    }

    public boolean isBtnPressed(int touchX, int touchY){
        return btnPressed && rectButton.contains(touchX, touchY);
    }
}
