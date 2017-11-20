package bean;

import com.sun.media.jai.widget.DisplayJAI;

import javax.media.jai.PlanarImage;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.Serializable;
import java.util.Vector;

public class Display extends Canvas implements Serializable, IFilterListener {

    private Vector listeners;
    private PlanarImage image;

    public Display() {
        this.listeners = new Vector();
    }

    public PlanarImage getImage() {
        return image;
    }

    public void setImage(PlanarImage image) {
        this.image = image;
    }

    public void addIFilterListener(IFilterListener filterListener) {
        listeners.addElement(filterListener);
    }

    public void removeIFilterListener(IFilterListener filterListener) {
        listeners.remove(filterListener);
    }

    @Override
    public void filterValueChanged(FilterEvent filterEvent) {
        image = filterEvent.getValue();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        BufferedImage img = image.getAsBufferedImage();
        if (img != null) {
            int x = (getWidth() - img.getWidth()) / 2;
            int y = (getHeight() - img.getHeight()) / 2;
            g.drawImage(img, x, y, this);
        }
    }
}
