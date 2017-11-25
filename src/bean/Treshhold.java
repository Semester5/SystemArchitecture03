package bean;

import javax.media.jai.PlanarImage;
import javax.media.jai.operator.ThresholdDescriptor;
import java.io.Serializable;
import java.util.Vector;

public class Treshhold extends BaseFilter {

    private double low;
    private double high;
    private double constant;

    public Treshhold() {
        low = 0;
        high = 30;
        constant = 255;
        listener = new Vector();
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
        updatePlanarImage();
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
        updatePlanarImage();
    }

    public double getConstant() {
        return constant;
    }

    public void setConstant(double constant) {
        this.constant = constant;
        updatePlanarImage();
    }

    @Override
    protected void updatePlanarImage() {
        double[] lows = {low};
        double[] highs = {high};
        double[] constants = {constant};

        PlanarImage newPlanarImage = ThresholdDescriptor.create(planarImage, lows, highs, constants, null);
        fireFilterEvent(newPlanarImage);
    }
}