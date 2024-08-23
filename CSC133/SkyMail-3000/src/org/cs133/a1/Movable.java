package org.cs133.a1;

public abstract class Movable extends GameObject {
    private int heading;
    private int speed;

    //constructor for objects with random locations
    public Movable(int sz, int clr) {super(sz, clr);}

    //constructor for objects with specified locations
    public Movable(int sz, int clr, float x, float y) {
        super(sz, clr, x, y);
    }
    //move the objects as outlined in spec for left/right
    public void move() {
        int deltaX = (int) (Math.cos(Math.toRadians(90 - heading))*speed);
        int deltaY = (int) (Math.sin(Math.toRadians(90 - heading))*speed);
        int newX = (int) (super.getLocation().getX() + deltaX);
        int newY = (int) (super.getLocation().getY() + deltaY);
        super.setLocation(newX, newY);
    }
    //get the heading of the movable object
    public int getHeading() {
        return heading;
    }
    //set the heading of the movable object
    public void setHeading(int head) {
        heading = head;
    }
    //get the speed of the movable object
    public int getSpeed() {
        return speed;
    }
    //set the speed of the movable object
    public void setSpeed(int spd) {
        speed = spd;
    }
}
