package bean;

import javax.media.jai.PlanarImage;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.nio.Buffer;
import java.util.Vector;

public class Display extends Canvas implements IFilterListener {
    private PlanarImage image;
    private Vector listeners;

    public Display() {
        listeners = new Vector();
        image = null;
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
