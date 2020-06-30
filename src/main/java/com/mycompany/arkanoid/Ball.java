package com.mycompany.arkanoid;

public class Ball extends BaseObject {
    
    private double speed;
    private double direction;
    private double dx, dy;
    private boolean isFrozen;
    
    public Ball(double x, double y, double radius) {
        super(x, y, radius);
    }

    public Ball(double x, double y, double speed, double direction){
        super(x, y, 1);
        this.speed = speed;
        this.direction = direction;
        this.isFrozen = true;
    }

            
    public double getSpeed() {
        return speed;
    }

    public double getDirection() {
        return direction;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public boolean isIsFrozen() {
        return isFrozen;
    }
    
    public void start(){
        this.isFrozen = false;
        this.setDirection(direction);
    }
    
    public void setDirection(Double direction){
        this.direction = direction;
        double angel = Math.toRadians(direction);
        dx = Math.cos(angel) * speed;
        dy = -Math.sin(angel) * speed;
    }
    
    public void checkRebound(int minx, int maxx, int miny, int maxy){
        if (x < minx) {
            x = minx + (minx - x);
            dx = -dx;
        }
        if (x > maxx) {
            x = maxx - (x - maxx);
            dx = -dx;
        }
        if (y < miny) {
            y = miny + (miny - y);
            dy = -dy;
        }
        if (y > maxy) {
            y = maxy - (y - maxy);
            dy = -dy;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.setPoint(x, y, 'O');
    }

    @Override
    public void move() {
        if (isIsFrozen()) return;
        x += dx;
        y += dy;
        checkRebound(1, Arkanoid.game.getWidth(), 1, Arkanoid.game.getHeight() + 5);
    }
    
}
