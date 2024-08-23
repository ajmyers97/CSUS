package org.cs133.a1;
import com.codename1.charts.util.ColorUtil;

public class Skyscraper extends Fixed {
    private static int objCount = 0;
    private static int sequenceNumber;

    public Skyscraper() {
        super(10, ColorUtil.BLUE);
        objCount++;
        sequenceNumber = objCount;
    }
    public Skyscraper(float x, float y) {                                 //constructor to specify Skyscraper locations
        super(10, ColorUtil.BLUE, x, y);                              //and color
        objCount++;
        sequenceNumber = objCount;
    }

    public static int getSeqNum() {                                                 //returns the sequence of skyscrapers
        return sequenceNumber;
    }
    @Override                                                                       //Skyscrapers cant change color
    public void setColor(int r, int g, int b){}
    //used to determine last Skyscraper object
    public static int getObjCount() {
        return objCount;
    }
}
