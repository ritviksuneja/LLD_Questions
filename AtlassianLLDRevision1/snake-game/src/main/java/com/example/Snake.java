package com.example;

import java.util.LinkedList;

public class Snake {
    private final LinkedList<Position> body;

    public Snake(Position start){
        body = new LinkedList<>();
        int x = start.getX();
        int y = start.getY();
        body.addFirst(start);
        body.addLast(new Position(x - 1, y));
        body.addLast(new Position(x - 2, y));
    }

    public Position getHead(){
        return body.getFirst();
    }

    public Position getTail(){
        return body.getLast();
    }

    public int getSize(){
        return body.size();
    }

    public boolean checkCollisionWithPosition(Position newHead){
        return body.contains(newHead);
    }

    public boolean checkCollisionWithSelf(){
        Position head = getHead();
        for(Position pos : body.subList(1, body.size())){
            if(pos.equals(head)){
                return true;
            }
        }
        return false;
    }

    public void moveSnake(Position newHead){
        body.addFirst(newHead);
        body.removeLast();
    }

    public void growSnake(Position newHead){
        body.addFirst(newHead);
    }
}
