package bean;

import javax.media.jai.PlanarImage;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.Serializable;
import java.util.Vector;

public class ROI implements IFilterListener, Serializable {

    private int x;
    private int y;
    private int width;
    private int height;
    private Vector listener;

    public ROI () {
        x = 0;
        y = 0;
        width = 200;
        height = 50;
        listener = new Vector();
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

    public void addIFilterListener(IFilterListener filterListener) {
        listener.addElement(filterListener);
    }

    public void removeIFilterListener(IFilterListener filterListener) {
        listener.remove(filterListener);
    }

    @Override
    public void filterValueChanged(FilterEvent filterEvent) {
        PlanarImage planarImage = filterEvent.getValue();
        Rectangle rectangle = new Rectangle(x, y, width, height);
        planarImage = PlanarImage.wrapRenderedImage((RenderedImage) planarImage.getAsBufferedImage(rectangle, planarImage.getColorModel()));
        planarImage.setProperty("offsetX", x);
        planarImage.setProperty("offsetY", y);
        fireFilterEvent(planarImage);
    }

    protected  void fireFilterEvent(PlanarImage planarImage) {
        Vector vector =  (Vector) listener.clone();
        FilterEvent filterEvent = new FilterEvent(this, planarImage);

        for(int i = 0; i < vector.size(); i++) {
            IFilterListener filterListener = (IFilterListener)vector.elementAt(i);
            filterListener.filterValueChanged(filterEvent);
        }
    }
}