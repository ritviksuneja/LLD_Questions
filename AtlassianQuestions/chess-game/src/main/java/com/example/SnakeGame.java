package com.example;

public interface SnakeGame {
    void moveSnake(SnakeDirection direction);
    boolean isGameOver();
    Snake getSnake();
}
