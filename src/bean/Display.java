package bean;
import javax.media.jai.PlanarImage;
import java.awt.*;
import java.io.Serializable;
import java.util.Vector;

public class Display extends Canvas implements Serializable, IFilterListener {

    private Vector listeners;
    private PlanarImage image;

    public Display() {
        this.setSize(100, 100);
        this.listeners = new Vector();
        image = null;
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
        if (image != null) {
            Image img = image.getAsBufferedImage();
            this.setSize(image.getWidth(), image.getHeight());
            Dimension dimension = this.getSize();
            g.drawImage(img, 0, 0, dimension.width, dimension.height, null);
        }
    }
}
