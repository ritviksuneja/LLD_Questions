package com.example;

public class SnakeGameImpl implements SnakeGame{
    private final Board board;
    private final Snake snake;
    private SnakeDirection direction;
    private int moveCounter;
    private boolean gameOver;

    public SnakeGameImpl(int width, int height){
        this.board = new Board(width, height);
        this.snake = new Snake(new Position(width / 2, height / 2));
        this.direction = SnakeDirection.RIGHT;
        this.moveCounter = 0;
        this.gameOver = false;
    }

    @Override
    public void moveSnake(SnakeDirection newDirection) {
        if(gameOver){
            return;
        }
        
        this.direction = newDirection;

        Position newHead = snake.getHead().move(direction);
        
        if(!board.isInside(newHead)){
            gameOver = true;
            throw new SnakeOutOfBoundsException("Snake hit the wall!");
        }

        if(!snake.checkCollisionWith(newHead)){
            gameOver = true;
            throw new SnakeCollisionException("Snake collided with itself!");
        }

        moveCounter++;
        if(moveCounter % 5 == 0){
            snake.grow(newHead);
        }
        else{
            snake.move(newHead);
        }
        
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public Snake getSnake() {
        return snake;
    }    
}
