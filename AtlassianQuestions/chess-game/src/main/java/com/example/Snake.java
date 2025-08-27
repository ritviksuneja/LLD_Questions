package com.example;

import java.util.LinkedList;

public class Snake {
    private LinkedList<Position> body;

    public Snake(Position start){
        body.addFirst(start);
        int headX = start.getX();
        int headY = start.getY();
        body.addLast(new Position(headX - 1, headY));
        body.addLast(new Position(headX - 2, headY));
    }

    public Position getHead(){
        return body.getFirst();
    }

    public Position getTail(){
        return body.getLast();
    }

    public LinkedList<Position> getBody(){
        return body;
    }

    public void move(Position newHead){
        body.addFirst(newHead);
        body.removeLast();
    }

    public void grow(Position newHead){
        body.addFirst(newHead);
    }

    public boolean checkCollisionWith(Position head){
        for(Position pos : body){
            if((pos.getX() == head.getX()) && (pos.getY() == head.getY())){
                return true;
            }
        }

        return false;
    }
}
