package com.selwyn.ciaran.zombierun.entities;

import com.selwyn.ciaran.zombierun.utilities.Drawer;

import java.util.LinkedList;

/**
 * Created by Ciaran on 26/05/2017.
 */
public class Controller {

    public LinkedList<Tile> tile = new LinkedList<Tile>();
    Tile tempTile;

    public void update(){
        for(int i = 0; i < tile.size(); i++){
            tempTile = tile.get(i);
            tempTile.update();
        }
    }

    public void draw(Drawer drawer){
        for(int i = 0; i < tile.size(); i++){
            tempTile = tile.get(i);
            tempTile.draw(drawer);
        }
    }

    public void addTile(Tile block){
        tile.add(block);
    }

    public void removeTile(Tile block){
        tile.remove(block);
    }

}

