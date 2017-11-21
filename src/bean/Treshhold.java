package bean;

import javax.media.jai.PlanarImage;
import javax.media.jai.operator.ThresholdDescriptor;
import java.io.Serializable;
import java.util.Vector;

public class Treshhold  implements Serializable, IFilterListener {

    private double low;
    private double high;
    private double constant;
    private Vector listeners;

    public Treshhold() {
        low = 0;
        high = 30;
        constant = 255;
        listeners = new Vector();
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getConstant() {
        return constant;
    }

    public void setConstant(double constant) {
        this.constant = constant;
    }

    @Override
    public void filterValueChanged(FilterEvent filterEvent) {
        PlanarImage image = filterEvent.getValue();
        double[] lows = {low};
        double[] highs = {high};
        double[] constants = {constant};
        image = ThresholdDescriptor.create(image, lows, highs, constants, null);
        fireFilterEvent(image);
    }

    protected  void fireFilterEvent(PlanarImage image) {
        Vector v =  (Vector)listeners.clone();
        FilterEvent fe = new FilterEvent(this, image);
        for(int i = 0; i < v.size(); i++) {
            IFilterListener fl = (IFilterListener)v.elementAt(i);
            fl.filterValueChanged(fe);
        }
    }

    public void addIFilterListener(IFilterListener filterListener) {
        listeners.addElement(filterListener);
    }

    public void removeIFilterListener(IFilterListener filterListener) {
        listeners.remove(filterListener);
    }
}
