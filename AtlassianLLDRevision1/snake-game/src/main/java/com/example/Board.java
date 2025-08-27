package com.example;

public class Board {
    private final int width;
    private final int height;

    public Board(int width, int height){
        this.width = width;
        this.height = height;
    }

    public boolean isPointInside(int row, int col){
        return row < width && col < height;
    }
}
