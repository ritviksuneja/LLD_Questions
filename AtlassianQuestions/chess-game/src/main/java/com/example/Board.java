package com.example;

public class Board {
    private final int width;
    private final int height;

    public Board(int width, int height){
        this.width = width;
        this.height = height;
    }

    public boolean isInside(Position pos){
        return pos.getX() >= 0 && pos.getX() < width &&
                pos.getY() >= 0 && pos.getY() < height;
    }
}
