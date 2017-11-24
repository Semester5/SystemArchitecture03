package bean;

import javax.media.jai.PlanarImage;
import java.awt.*;
import java.io.Serializable;

public class Display extends Canvas implements Serializable, IFilterListener {

    private PlanarImage image;

    public Display() {
        this.setSize(200, 200);
        this.setBackground(Color.DARK_GRAY);
        image = null;
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
