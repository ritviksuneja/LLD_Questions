package com.example;

// Position

import java.util.Objects;

//     -x: int
//     -y: int
//     +constuctor()
//     +getters()
//     +overriden equals method
//     +overriden hashcode
//     +move(dir: SnakeDirection): Position

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position move(SnakeDirection dir){
        switch (dir) {
            case UP -> {
                return new Position(x - 1, y);
            }
            case DOWN -> {
                return new Position(x + 1, y);
            }
            case LEFT -> {
                return new Position(x, y - 1);
            }
            case RIGHT -> {
                return new Position(x, y + 1);
            }
            default -> throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof Position)){
            return false;
        }
        
        Position that = (Position)o;
        return (this.x == that.x) && (this.y == that.y);
    }

    @Override
    public int hashCode(){
        return Objects.hash(x, y);
    }
}
