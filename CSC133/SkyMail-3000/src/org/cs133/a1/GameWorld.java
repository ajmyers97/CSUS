package org.cs133.a1;

import java.util.ArrayList;
import java.util.Random;

public class GameWorld {
        private static int gameClock = 0;
        private Random random = new Random();
        private ArrayList<GameObject> gameObjectList;
        private int livesRemaining = 3;
        private Helicopter player;

        public void init() {                                                        //initialize the gameWorld Objects
            gameObjectList = new ArrayList<GameObject>();
            Skyscraper Skyscraper1 = new Skyscraper(50, 200);
            Skyscraper Skyscraper2 = new Skyscraper(200, 275);
            Skyscraper Skyscraper3 = new Skyscraper(500, 500);
            Skyscraper Skyscraper4 = new Skyscraper(750, 750);
            Bird bird1 = new Bird();
            Bird bird2 = new Bird();
            RefuelingBlimp RefuelingBlimp1 = new RefuelingBlimp();
            RefuelingBlimp RefuelingBlimp2 = new RefuelingBlimp();
            player = new Helicopter(50, 200);

            gameObjectList.add(Skyscraper1);
            gameObjectList.add(Skyscraper2);
            gameObjectList.add(Skyscraper3);
            gameObjectList.add(Skyscraper4);
            gameObjectList.add(player);
            gameObjectList.add(bird1);
            gameObjectList.add(bird2);
            gameObjectList.add(RefuelingBlimp1);
            gameObjectList.add(RefuelingBlimp2);
        }

        //move all objects, reduce Helicopter energy, increase gameClock, check if Helicopter out of energy
        public void tick() {
            moveAll(gameObjectList);
            getPlayer().reduceFuelLevel();
            System.out.println("Helicopter location=" + player.getLocation().getX() + "," + player.getLocation().getY()
                    + " steerDirection=" + player.getSteeringDirection()
                    + " speed=" + player.getSpeed()
                    + " heading=" + player.getHeading()
                    + " fuelLevel=" + player.getFuelLevel()
                    + " lastSkyscraper=" + player.getLastSkyScraperReached());
            gameClock++;
            System.out.println("gameClock increase to " + gameClock);
            if(getPlayer().getFuelLevel() <= 0) {
                System.out.println("Helicopter has ran out of Fuel!");
                loseLife();
            }
        }
        public void moveAll(ArrayList<GameObject> go) {                              //move all objects in GameWorld
            for(int i = 0; i < go.size(); i++) {
                if(go.get(i) instanceof Movable) {
                    Movable mObj = (Movable)go.get(i);
                    mObj.move();
                }
            }
        }
        public ArrayList<GameObject> getObjectList() {                              //returns list of all GameObjects
            return gameObjectList;
        }

        //method returns list with all GameObjects of the specified type
        public ArrayList<GameObject> getObjsOfType(String type, ArrayList<GameObject> go){
            ArrayList<GameObject> allObjOfType = new ArrayList<GameObject>();
            switch(type) {
                case "Bird":
                    for(int i = 0; i < go.size(); i++) {
                        if(go.get(i) instanceof Bird) {
                            allObjOfType.add((Bird)go.get(i));
                        }
                    }
                    break;
                case "Helicopter":
                    for(int i = 0; i < go.size(); i++) {
                        if(go.get(i) instanceof Helicopter) {
                            allObjOfType.add((Helicopter)go.get(i));
                        }
                    }
                    break;
                case "RefuelingBlimp":
                    for(int i = 0; i < go.size(); i++) {
                        if (go.get(i) instanceof RefuelingBlimp) {
                            allObjOfType.add((RefuelingBlimp) go.get(i));
                        }
                    }
                    break;
                case "Skyscraper":
                    for(int i = 0; i < go.size(); i++) {
                        if(go.get(i) instanceof Skyscraper) {
                            allObjOfType.add((Skyscraper)go.get(i));
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid Input. Valid inputs are 'Helicopter', 'Bird', 'Skyscraper', or 'RefuelingBlimp'");
            }
            return allObjOfType;
        }
        //display game stats
        public void display() {
            System.out.println("livesRemaining=" + getLivesRemaining());
            System.out.println("gameClock=" + getGameClock());
            System.out.println("lastSkyscraperReached=" + getPlayer().getLastSkyScraperReached());
            System.out.println("fuelLevel=" + getPlayer().getFuelLevel());
            System.out.println("damageLevel=" + getPlayer().getDamageLevel());
        }
        //print game map
        public void printMap() {
            System.out.println("Printing Map");
            for(int i = 0; i < gameObjectList.size(); i++) {
                if(gameObjectList.get(i) instanceof Skyscraper) {
                    Skyscraper SkyscraperObject = (Skyscraper)gameObjectList.get(i);
                    System.out.println("Skyscraper: loc=" + SkyscraperObject.getLocation().getX() + ", "
                            +  SkyscraperObject.getLocation().getY()
                            + " color=" + SkyscraperObject.printColor()
                            + " size=" + SkyscraperObject.getSize()
                            + " seqNum=" + SkyscraperObject.getSeqNum());
                }
                else if(gameObjectList.get(i) instanceof Bird) {
                    Bird birdObject = (Bird)gameObjectList.get(i);
                    System.out.println("Bird: loc=" + birdObject.getLocation().getX() + ", "
                            + birdObject.getLocation().getY() + " color=" + birdObject.printColor()
                            + " heading=" + birdObject.getHeading()
                            + " speed=" + birdObject.getSpeed()
                            + " size=" + birdObject.getSize());

                }
                else if(gameObjectList.get(i) instanceof Helicopter) {
                    Helicopter heliObject = (Helicopter)gameObjectList.get(i);
                    System.out.println("Helicopter: loc=" + heliObject.getLocation().getX()
                            + ", " + heliObject.getLocation().getY() + " color="
                            + heliObject.printColor()
                            + " heading=" + heliObject.getHeading()
                            + " speed=" + heliObject.getSpeed()
                            + " size=" + heliObject.getSize()
                            + " maxSpeed=" + heliObject.getMaximumSpeed()
                            + " steeringDirection=" + heliObject.getSteeringDirection()
                            + " fuelLevel=" + heliObject.getFuelLevel()
                            + " damageLevel=" + heliObject.getDamageLevel());
                }
                else if(gameObjectList.get(i) instanceof RefuelingBlimp) {
                    RefuelingBlimp fuelBlimpObject = (RefuelingBlimp)gameObjectList.get(i);
                    System.out.println("RefuelingBlimp: loc=" + fuelBlimpObject.getLocation().getX()
                            + ", " + fuelBlimpObject.getLocation().getY()
                            + " color=" + fuelBlimpObject.printColor()
                            + " size=" + fuelBlimpObject.getSize()
                            + " Fuel Level=" + fuelBlimpObject.getFuelLevel());
                }
            }
        }
        public void addRefuelingBlimp() {                                              //add RefuelingBlimp to gameworld
            RefuelingBlimp newRefuelingBlimp = new RefuelingBlimp();
            getObjectList().add(newRefuelingBlimp);
        }
        public RefuelingBlimp collideRefuelingBlimp() {                               //helicopter collides with blimp
            int size = getObjsOfType("RefuelingBlimp", getObjectList()).size();  //new fueling blimp created
            int randomInt = random.nextInt(size);
            RefuelingBlimp randomRefuelingBlimp = (RefuelingBlimp) getObjsOfType("RefuelingBlimp", getObjectList()).get(randomInt);
            randomRefuelingBlimp.collide();
            addRefuelingBlimp();
            return randomRefuelingBlimp;
        }
        //return number of lives remaining
        public int getLivesRemaining() {
            return livesRemaining;
        }

        //decrements remaining lives, re-inits GameWorld,  ends game if none left
        public void loseLife() {
            livesRemaining--;
            System.out.println("You lost a life! " + getLivesRemaining() + " lives remaining.");
            if(livesRemaining > 0) {
                init();
            }
            else {
                System.out.println("Game Over, you failed!");
                exit();
            }
        }
        //player wins game
        public static void gameWin() {
            System.out.println("Game over, you win! Time Elapsed: " + gameClock);
            exit();
        }
        //get the current gameClock
        public int getGameClock() {
            return gameClock;
        }

        //gets player Helicopter
        public Helicopter getPlayer() {
            return player;
        }
        //exit the game
        public static void exit() {
            System.exit(0);
        }
}
