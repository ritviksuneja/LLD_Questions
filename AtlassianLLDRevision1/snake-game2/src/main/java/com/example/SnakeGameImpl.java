package com.example;

public class SnakeGameImpl {
    private final Snake snake;
    private final Board board;
    private int moveCounter;
    private boolean gameOver;

    public SnakeGameImpl(int width, int height){
        snake = new Snake();
        board = new Board(width, height);
        moveCounter = 0;
        gameOver = false;
        snake.grow(new Position(width / 2, height / 2 - 2));
        snake.grow(new Position(width / 2, height / 2 - 1));
        snake.grow(new Position(width / 2, height / 2));
    }

    public void moveSnake(SnakeDirection dir){
        if(isGameOver()){
            return;
        }

        Position snakeHead = snake.getHead();
        Position newHead = snakeHead.move(dir);

        if(isSnakeCollidingWithBoundary(newHead)){
            gameOver = true;
            throw new SnakeCollisionException("Snake collided with the boundary.");
        }

        snake.move(newHead);
        if(snake.checkCollisionWithSelf()){
            gameOver = true;
            throw new SnakeCollisionException("Snake collided with itself.");
        }

        moveCounter++;
        if(moveCounter % 5 == 0){
            Position movedHead = newHead.move(dir);

            if(isSnakeCollidingWithBoundary(movedHead)){
                gameOver = true;
                throw new SnakeCollisionException("Snake collided with the boundary.");
            }

            snake.grow(movedHead);

            if(snake.checkCollisionWithSelf()){
                gameOver = true;
                throw new SnakeCollisionException("Snake collided with itself.");
            }
        }
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public boolean isSnakeCollidingWithBoundary(Position newHead){
        return !board.isPointInside(newHead.getX(), newHead.getY());
    }

    public void printSnake() {
        for (Position pos : snake.getSnake()) {
            System.out.printf(pos.getX() + " " + pos.getY() + ", ");
        }
        System.out.println();
    }
}
