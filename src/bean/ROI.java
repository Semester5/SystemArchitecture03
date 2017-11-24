package bean;

import javax.media.jai.PlanarImage;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.Serializable;
import java.util.Vector;

public class ROI extends BaseFilter {

    private int x;
    private int y;
    private int width;
    private int height;

    public ROI () {
        x = 0;
        y = 50;
        width = 500;
        height = 70;
        listener = new Vector();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        updatePlanarImage();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        updatePlanarImage();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        updatePlanarImage();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        updatePlanarImage();
    }

    public void addIFilterListener(IFilterListener filterListener) {
        listener.addElement(filterListener);
    }

    public void removeIFilterListener(IFilterListener filterListener) {
        listener.remove(filterListener);
    }

    @Override
    public void filterValueChanged(FilterEvent filterEvent) {
        this.planarImage = filterEvent.getValue();
        updatePlanarImage();
    }

    @Override
    protected void updatePlanarImage() {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        PlanarImage newPlanarImage = PlanarImage.wrapRenderedImage((RenderedImage) planarImage.getAsBufferedImage(rectangle, planarImage.getColorModel()));
        newPlanarImage.setProperty("offsetX", x);
        newPlanarImage.setProperty("offsetY", y);
        fireFilterEvent(newPlanarImage);
    }
}