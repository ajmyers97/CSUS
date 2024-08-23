package org.cs133.a1;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;

public class RefuelingBlimp extends Fixed{
    private static Random random = new Random();
    private int fuelLevel;

    public RefuelingBlimp() {
        super((10 + random.nextInt(40)), (ColorUtil.GREEN));
        fuelLevel = super.getSize();
    }
    public int getFuelLevel() {
        return fuelLevel;
    }
    public void collide() {
        fuelLevel = 0;
        super.setColor(100,65,0);
    }
}
