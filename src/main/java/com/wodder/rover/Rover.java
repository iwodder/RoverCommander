package com.wodder.rover;


import com.wodder.gui.MarsPanel;

import java.util.Random;

public class Rover {

    private int xPos;
    private int yPos;
    private int lastX;
    private int lastY;
    private int height;
    private int width;

    public Rover(int size) {
        height = size;
        width = size;
        setStartingPos();
    }

    private void setStartingPos() {
        Random r = new Random();
        xPos = r.nextInt(25) * MarsPanel.cellSize();
        yPos = r.nextInt(25) * MarsPanel.cellSize();
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.lastX = this.xPos;
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.lastY = this.yPos;
        this.yPos = yPos;
    }

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
