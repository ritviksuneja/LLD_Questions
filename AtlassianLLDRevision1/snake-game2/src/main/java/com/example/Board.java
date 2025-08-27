package com.example;

public class Board {
    private final int width;
    private final  int height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isPointInside(int x, int y){
        return x >= 0 && x < width && y >=0 && y < height;
    }
}
