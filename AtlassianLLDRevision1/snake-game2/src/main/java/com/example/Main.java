package com.example;

public class Main {
    public static void main(String[] args) {
        SnakeGameImpl game = new SnakeGameImpl(10, 10);
        game.printSnake();
        SnakeDirection[] moves = {
                SnakeDirection.RIGHT,
                SnakeDirection.RIGHT,
                SnakeDirection.RIGHT,
                SnakeDirection.RIGHT,
                SnakeDirection.DOWN,
                SnakeDirection.LEFT,
                SnakeDirection.UP,
        };

        for (SnakeDirection dir : moves) {
            game.moveSnake(dir);
            game.printSnake();
            if (game.isGameOver()) {
                System.out.println("Game Over");
                break;
            }
        }
    }
}
