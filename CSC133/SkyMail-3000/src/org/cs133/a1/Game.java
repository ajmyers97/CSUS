package org.cs133.a1;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class Game extends Form {
    private GameWorld gw;

    public Game() {
        gw = new GameWorld();
        gw.init();
        play();
    }

    private void play() {
        Label commandInput = new Label("Enter a Command:");
        this.addComponent(commandInput);
        final TextField commandInputField = new TextField();
        this.addComponent(commandInputField);
        this.show();
        commandInputField.addActionListener(new ActionListener() {
              boolean wantToExit = false;
              public void actionPerformed(ActionEvent evt) {
                  String commandString = commandInputField.getText().toString();
                  commandInputField.clear();
                  int lastSkyScraperReached = gw.getObjsOfType("Skyscraper", gw.getObjectList()).size();
                  if (commandString.length() != 0)
                      switch (commandString.charAt(0)) {
                          case 'a':                                         //accelerating helicopter
                              System.out.println("Accelerating Helicopter");
                              gw.getPlayer().accelerate();
                              break;
                          case 'b':                                         //applying brakes
                              System.out.println("Braking Immediately");
                              gw.getPlayer().brake();
                              break;
                          case 'l':                                         //change stickAngle left
                              System.out.println("turning Helicopter left");
                              gw.getPlayer().turnLeft();
                              break;
                          case 'r':                                         //change stickAngle right
                              System.out.println("turning Helicopter right");
                              gw.getPlayer().turnRight();
                              break;
                          case 'c':                                         //collided with another helicopter(self)
                              System.out.println("helicopter has collided with another helicopter");
                              gw.getPlayer().collide(gw.getPlayer());
                              if (gw.getPlayer().getDamageLevel() >= 10) {
                                  gw.loseLife();
                              }
                              break;
                          case 'e':                                         //collided with refuelingBlimp
                              System.out.println("helicopter has gained more fuel");
                              gw.getPlayer().collide(gw.collideRefuelingBlimp());
                              break;
                          case 'g':                                         //collided with bird
                              System.out.println("helicopter has collided with a bird");
                              gw.getPlayer().collide(gw.getObjsOfType("Bird", gw.getObjectList()).get(0));
                              if (gw.getPlayer().getDamageLevel() >= 10) {
                                  gw.loseLife();
                              }
                              break;
                          case 't':                                         //increase ticks on gameClock
                              System.out.println("increased game clock");
                              gw.tick();
                              break;
                          case 'd':                                         //generates display of stats
                              System.out.println("displaying game world stats");
                              gw.display();
                              break;
                          case 'm':                                         //prints current world map
                              gw.printMap();
                              break;

                          //helicopter has collided with any one of the 9 skyscrapers.
                          case '1':
                              if (lastSkyScraperReached >= 1) {
                                  System.out.println("collided with SkyScraper 1");
                                  gw.getPlayer().collide(gw.getObjsOfType("Skyscraper", gw.getObjectList()).get(0));
                              } else {
                                  System.out.println("there are only " + lastSkyScraperReached + " SkyScrapers left to collide with");
                              }
                              break;
                          case '2':
                              if (lastSkyScraperReached >= 2) {
                                  System.out.println("collided with Skyscraper 2");
                                  gw.getPlayer().collide(gw.getObjsOfType("Skyscraper", gw.getObjectList()).get(1));
                              } else {
                                  System.out.println("there are only " + lastSkyScraperReached + " SkyScrapers  to collide with");
                              }
                              break;
                          case '3':
                              if (lastSkyScraperReached >= 3) {
                                  System.out.println("collided with Skyscraper 3");
                                  gw.getPlayer().collide(gw.getObjsOfType("Skyscraper", gw.getObjectList()).get(2));
                              } else {
                                  System.out.println("there are only " + lastSkyScraperReached + " SkyScrapers  to collide with");
                              }
                              break;
                          case '4':
                              if (lastSkyScraperReached >= 4) {
                                  System.out.println("collided with Skyscraper 4");
                                  gw.getPlayer().collide(gw.getObjsOfType("Skyscraper", gw.getObjectList()).get(3));
                              } else {
                                  System.out.println("there are only " + lastSkyScraperReached + " SkyScrapers  to collide with");
                              }
                              break;
                          case '5':
                              if (lastSkyScraperReached >= 5) {
                                  System.out.println("collided with Skyscraper 5");
                                  gw.getPlayer().collide(gw.getObjsOfType("Skyscraper", gw.getObjectList()).get(4));
                              } else {
                                  System.out.println("there are only " + lastSkyScraperReached + " SkyScrapers  to collide with");
                              }
                              break;
                          case '6':
                              if (lastSkyScraperReached >= 6) {
                                  System.out.println("collided with Skyscraper 6");
                                  gw.getPlayer().collide(gw.getObjsOfType("Skyscraper", gw.getObjectList()).get(5));
                              } else {
                                  System.out.println("there are only " + lastSkyScraperReached + " SkyScrapers  to collide with");
                              }
                              break;
                          case '7':
                              if (lastSkyScraperReached >= 7) {
                                  System.out.println("collided with SkyScraper 7");
                                  gw.getPlayer().collide(gw.getObjsOfType("Skyscraper", gw.getObjectList()).get(6));
                              } else {
                                  System.out.println("there are only " + lastSkyScraperReached + " SkyScrapers  to collide with");
                              }
                              break;
                          case '8':
                              if (lastSkyScraperReached >= 8) {
                                  System.out.println("collided with SkyScraper 8");
                                  gw.getPlayer().collide(gw.getObjsOfType("Skyscraper", gw.getObjectList()).get(7));
                              } else {
                                  System.out.println("there are only " + lastSkyScraperReached + " SkyScrapers  to collide with");
                              }
                              break;
                          case '9':
                              if (lastSkyScraperReached >= 9) {
                                  System.out.println("collided with SkyScraper 9");
                                  gw.getPlayer().collide(gw.getObjsOfType("Skyscraper", gw.getObjectList()).get(8));
                              } else {
                                  System.out.println("there are no more SkyScrapers to collide with");
                              }
                              break;
                          case 'x':                                          //user has requested to terminate
                              commandInput.setText("Exit? y/n");
                              wantToExit = true;
                              break;
                          case 'y':                                          //user has confirmed to exit game
                              if (wantToExit) {
                                  gw.exit();
                              }
                              break;
                          case 'n':                                         //user has not confirmed the exit
                              wantToExit = false;
                              commandInput.setText("Enter a Command:");
                              break;

                          default:                                         //if user inputs a wrong command
                              System.out.println("Improper input");
                              break;
                      } //switch
              } //actionPerformed
          } //new ActionListener()
        ); //addActionListener
    } //play
}