package com.selwyn.ciaran.zombierun.entities;

import com.selwyn.ciaran.zombierun.utilities.Drawer;

import java.util.LinkedList;

/**
 * Created by Ciaran on 26/05/2017.
 */
public class Controller {

    public LinkedList<Bullet> b = new LinkedList<Bullet>();
    Bullet tempBullet;

    public void update(){
        for(int i = 0; i < b.size(); i++){
            tempBullet = b.get(i);
            tempBullet.update();
        }
    }

    public void draw(Drawer drawer){
        for(int i = 0; i < b.size(); i++){
            tempBullet = b.get(i);
            tempBullet.render(drawer);
        }
    }

    public void addBullet(Bullet block){
        b.add(block);
    }

    public void removeBullet(Bullet block){
        b.remove(block);
    }

}

