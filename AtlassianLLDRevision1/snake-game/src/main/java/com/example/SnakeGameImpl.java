package com.example;

public class SnakeGameImpl implements SnakeGame{
    private final Snake snake;
    private final Board board;
    private boolean gameOver;
    private int moveCounter;

    public SnakeGameImpl(int width, int height){
        snake = new Snake(new Position(width / 2, height / 2));
        board = new Board(width, height);
        gameOver = false;
        moveCounter = 0;
    }

    @Override
    public boolean isGameOver(){
        return gameOver;
    }

    @Override
    public void moveSnake(SnakeDirection dir) {
        Position head = snake.getHead();
        int newX = head.getX();
        int newY = head.getY();

        switch (dir) {
            case UP -> newX = newX - 1;
            case DOWN -> newX = newX + 1;
            case LEFT -> newY = newY - 1;
            case RIGHT -> newY = newY + 1;
            default -> throw new AssertionError();
        }

        Position newHead = new Position(newX, newY);

        if(!board.isPointInside(newX, newY)){
            gameOver = true;
            throw new SnakeOutOfBoundsException("Game over! The snake has hit the wall.");
        }

        moveCounter++;

        if(moveCounter % 5 == 0){
            snake.growSnake(newHead);
            
            if(snake.checkCollisionWithSelf()){
                gameOver = true;
                throw new SnakeCollidedException("Game over! The snake has collided with itself");
            }
        }
        else{
            snake.moveSnake(newHead);
        
            if(snake.checkCollisionWithSelf()){
                gameOver = true;
                throw new SnakeCollidedException("Game over! The snake has collided with itself");
            }
        }
    }
}
