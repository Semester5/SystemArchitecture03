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
    private BufferedImage img;

    public Display() {
        this.setSize(100, 100);
        this.listeners = new Vector();
        img = null;
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
        if (image != null) {
            this.repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        if (img != null) {
            this.setSize(image.getWidth(), image.getHeight());
            Dimension dimension = this.getSize();
            img = image.getAsBufferedImage();
            g.drawImage(img, 0, 0, dimension.width, dimension.height, null);
        }
    }
}
