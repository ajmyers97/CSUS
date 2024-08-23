package org.cs133.a1;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;

public class Helicopter extends Movable implements isSteerable {
    private Random random = new Random();
    private int steeringDirection = 0;
    private int fuelLevel = 100;
    private int energyConsumptionRate = 5;
    private int damageLevel = 0;
    private int lastSkyScraperReached;
    private int maximumSpeed = 40;
    private static int maxDamage = 10;

    public Helicopter() {                                                               // basic Helicopter constructor
        super(40, ColorUtil.rgb(255, 0, 0));
        super.setSpeed(5 + random.nextInt(5));
        super.setHeading(random.nextInt(359));
    }
    public Helicopter(float x, float y) {                                           //constructor for player Helicopter
        super(40, ColorUtil.rgb(255, 0, 0), x, y);                      //to specify start location
        super.setSpeed(5);
        super.setHeading(0);
    }
    public int getSteeringDirection() { return steeringDirection; }         //getters and setters for Helicopter vars
    public void setSteeringDirection(int sd) { steeringDirection = sd; }
    public int getFuelLevel() { return fuelLevel; }
    public void setFuelLevel(int el) { fuelLevel = el; }
    public int getDamageLevel() { return damageLevel; }
    public void setDamageLevel(int dl) { damageLevel = dl; }
    public int getMaximumSpeed() { return maximumSpeed; }
    public void setMaximumSpeed(int ms) { maximumSpeed = ms; }
    public int getLastSkyScraperReached() { return lastSkyScraperReached; }
    public void setLastSkyScraperReached(int ls) { lastSkyScraperReached = ls; }
    public void reduceFuelLevel() {                                       //reduces energy level of Helicopter
        fuelLevel -= energyConsumptionRate;
    }

    @Override                                                        //set speed of Helicopter if within maxSpeed limit
    public void setSpeed(int spd) {
        if(spd <= maximumSpeed) {
            super.setSpeed(spd);
        }
    }

    //method to move Helicopter. keeps Helicopter between 0 and 1000 on x and y coordinates
    @Override
    public void move() {

        if(fuelLevel > 0 && damageLevel < maxDamage) {
            int head = super.getHeading() + steeringDirection;
            int deltaX = (int) (Math.cos(Math.toRadians(90 - head))*super.getSpeed());
            int deltaY = (int) (Math.sin(Math.toRadians(90 - head))*super.getSpeed());

            int newX = (int) (super.getLocation().getX() + deltaX);
            int newY = (int) (super.getLocation().getY() + deltaY);

            //keeping Helicopter within 0 and 1000 boundaries
            if(newX > 1000) { newX = 1000; }
            else if(newX < 0) { newX = 0; }
            if(newY > 1000) { newY = 1000; }
            else if(newY < 0) { newY = 0; }

            super.setLocation(newX, newY);
        }
        else if(fuelLevel <= 0 || damageLevel >=maxDamage) {          //at max damage or no fuel, speed is 0
            super.setSpeed(0);
        }
    }
    public void turnLeft() {                                            //helicopter turns 5 deg left
        steeringDirection -= 5;
        // check if steer direction < -40, if so then = -40
        if(steeringDirection < -40) {steeringDirection = -40;}
        super.setHeading(super.getHeading()+steeringDirection);
    }
    public void turnRight() {                                            //helicopter turns 5 deg right
        steeringDirection += 5;
        //check if steer direction > 40, if so then = 40
        if(steeringDirection > 40) {steeringDirection = 40;}
        super.setHeading(super.getHeading() + steeringDirection);
    }
    public void collide(GameObject g) {                                 //if collisions occur, do things based on object
        if(g instanceof Bird) {                                        //if player hits bird, cause damage
            damageLevel += 1;                                           //set new speed, change color
            maximumSpeed = (int) (maximumSpeed*(1-damageLevel*0.1));
            if(super.getSpeed() > getMaximumSpeed()) {
                setSpeed(maximumSpeed);
            }
            fadeColor();
        }
        else if(g instanceof Helicopter) {                                  //if player hits self, cause damage,
            damageLevel += 2;                                               //set new speed, change color
            maximumSpeed = (int) (maximumSpeed*(1-damageLevel*0.1));
            if(super.getSpeed() > getMaximumSpeed()) {
                setSpeed(maximumSpeed);
            }
            fadeColor();
        }
        else if(g instanceof Skyscraper) {                                  //check for collision, if collided,
            Skyscraper Skyscraper = (Skyscraper) g;                         //check for skyscraper number
            if(lastSkyScraperReached + 1 == Skyscraper.getSeqNum()){
                lastSkyScraperReached = Skyscraper.getSeqNum();
            }
            if(lastSkyScraperReached == Skyscraper.getObjCount()) {        //if last skyscraper, player wins.
                GameWorld.gameWin();
            }
        }
        else if(g instanceof RefuelingBlimp) {                         //if collides with Blimp, add fuel to helicopter
            fuelLevel += ((RefuelingBlimp) g).getFuelLevel();
        }
    }
    public void accelerate() {                                                         //increase Helicopter speed by 2
        setSpeed(super.getSpeed() + 2);
    }
    public void brake() {                                                              //decrease Helicopter speed by 2
        if(super.getSpeed()-2 >= 0) {
            setSpeed(super.getSpeed()-2);
        }
    }
    //fade color of Helicopter based on damage level
    public void fadeColor() {
        int newRed;
        int newBlue;
        int newGreen;
        if(getSpeed() == 0) {
            newRed = 0;
            newBlue = 0;
            newGreen = 0;
        }
        else {
            newRed = (int) (ColorUtil.red(getColor()) * (1-damageLevel*0.1));
            newBlue = (int) (ColorUtil.blue(getColor()) * (1-damageLevel*0.1));
            newGreen = (int) (ColorUtil.green(getColor()) * (1-damageLevel*0.1));
        }
        setColor(newRed, newGreen, newBlue);
    }

}
