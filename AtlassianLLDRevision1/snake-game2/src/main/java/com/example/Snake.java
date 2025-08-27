package com.example;

// Snake:

import java.util.LinkedList;

//     -body: LinkedList<Position>
//     +constuctor()
//     +getHead(): Position
//     +getTail(): Position
//     +move(Position newHead): void
//     +grow(Position newHead): void
//     +checkCollisionWithSelf(): boolean

public class Snake {
    private final LinkedList<Position> body;
    
    public Snake(){
        body = new LinkedList<>();
    }

    public Position getHead(){
        return body.getFirst();
    }

    public Position gedTail(){
        return body.getLast();
    }

    public int getSize(){
        return body.size();
    }

    public void move(Position newHead){
        body.addFirst(newHead);
        body.removeLast();
    }

    public void grow(Position newHead){
        body.addFirst(newHead);
    }

    public boolean checkCollisionWithSelf(){
        if(body.size() <= 1){
            return false;
        }

        Position head = getHead();

        for(Position bodyPos : body.subList(1, body.size())){
            if(bodyPos.equals(head)){
                return true;
            }
        }

        return false;
    }

    public LinkedList<Position> getSnake(){
        return body;
    }
}
