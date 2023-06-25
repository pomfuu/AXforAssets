package com.example.ux_project;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Game {
    String gameTitle, gameDesc;
    int idx;
    Drawable img;

    public Game(String gameTitle, String gameDesc, int idx, Drawable img) {
        this.gameTitle = gameTitle;
        this.gameDesc = gameDesc;
        this.idx = idx;
        this.img = img;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getGameDesc() {
        return gameDesc;
    }

    public void setGameDesc(String gameDesc) {
        this.gameDesc = gameDesc;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }
}
