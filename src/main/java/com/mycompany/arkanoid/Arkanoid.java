package com.mycompany.arkanoid;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Arkanoid {
    private int width, height;
    private Ball ball;
    private Stand stand;
    private ArrayList<Brick> bricks;
    private boolean isGameOver;
    
    public static Arkanoid game;
    
    public Arkanoid(int width, int height){
        this.height = height;
        this.width = width;
    }
    
    public void setWidth(int width){
        this.width = width;
    }
    
    public int getWidth(){
        return width;
    }
    
    public void setHeight(int height){
        this.height = height;
    }
    
    public int getHeight(){
        return height;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(ArrayList<Brick> bricks) {
        this.bricks = bricks;
    }
    
    public void run() throws Exception {
        //Создаем холст для отрисовки.
        Canvas canvas = new Canvas(width, height);
        
        //Создаем объект "наблюдатель за клавиатурой" и стартуем его.
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();
        
        //Исполняем цикл, пока игра не окончека
        while (!isGameOver) {
            //отрисовываем все объекты
            canvas.clear();
            draw(canvas);
            canvas.print();

            //"наблюдатель" содержит события о нажатии клавиш?
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        stand.moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        stand.moveRight();
                        break;
                    case KeyEvent.VK_SPACE:  //Если "пробел" - запускаем шарик
                        ball.start();
                        break;
                    default:
                        break;
                }
            }
            //двигаем все объекты
            move();
            //проверяем столкновения
            checkBricksBump();
            checkStandBump();
            //проверяем, что шарик мог улететь через дно.
            checkEndGame();
            //пауза
            Thread.sleep(300);
        }
        //Выводим сообщение "Game Over"
        System.out.println("Game Over!");
    }
    
    public void move(){
        ball.move();
        stand.move();
    }
    
    public void draw(Canvas canvas){
        ball.draw(canvas);
        stand.draw(canvas);
        for(Brick brick: bricks){
            brick.draw(canvas);
        }
    }
    
    public void checkBricksBump(){
        for(Brick brick: bricks){
            if (ball.isIntersec(brick)){
                double angel = Math.random() * 360;
                ball.setDirection(angel);
                
                bricks.remove(brick);
            }
        }
    }
    
    public void checkStandBump(){
        if (ball.isIntersec(stand)){
            double angel = 80 + Math.random()*20;
            ball.setDirection(angel);
        }
    }
    
    public void checkEndGame(){
        if (ball.getY() > this.height) isGameOver = true;
    }
    
     // Рисуем на холсте границы
    private void drawBoders(Canvas canvas) {
        //draw game
        for (int i = 0; i < width + 2; i++) {
            for (int j = 0; j < height + 2; j++) {
                canvas.setPoint(i, j, '.');
            }
        }
        for (int i = 0; i < width + 2; i++) {
            canvas.setPoint(i, 0, '-');
            canvas.setPoint(i, height + 1, '-');
        }
        for (int i = 0; i < height + 2; i++) {
            canvas.setPoint(0, i, '|');
            canvas.setPoint(width + 1, i, '|');
        }
    }
    
    public static void main(String[] args) {
        game = new Arkanoid(20, 30);
        Ball ball = new Ball(10, 29, 2, 100);
        game.setBall(ball);
        Stand stand = new Stand(10, 30);
        game.setStand(stand);
        game.bricks = new ArrayList<>();
        game.getBricks().add(new Brick(4, 3));
        game.getBricks().add(new Brick(8, 5));
        game.getBricks().add(new Brick(14, 5));
        game.getBricks().add(new Brick(16, 3));
        game.isGameOver = false;
        try {
            game.run();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
        
    }
}
