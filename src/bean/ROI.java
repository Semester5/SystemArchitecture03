package bean;

import javax.media.jai.PlanarImage;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.Serializable;
import java.util.Vector;

public class ROI implements Serializable, IFilterListener {

    private int x;
    private int y;
    private int width;
    private int height;
    private Vector listeners;

    public ROI () {
        listeners = new Vector();
        x = 0;
        y = 0;
        width = 200;
        height = 50;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void filterValueChanged(FilterEvent filterEvent) {
        PlanarImage image = filterEvent.getValue();
        Rectangle rectangle = new Rectangle(x, y, width, height);
        image = PlanarImage.wrapRenderedImage((RenderedImage) image.getAsBufferedImage(rectangle, image.getColorModel()));
        image.setProperty("offsetX", x);
        image.setProperty("offsetY", y);
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
