package bean;

import javax.media.jai.PlanarImage;
import java.awt.*;
import java.io.Serializable;

public class Display extends Canvas implements Serializable, IFilterListener {

    private PlanarImage planarImage;

    public Display() {
        this.setSize(600, 100);
        this.setBackground(Color.DARK_GRAY);
        planarImage = null;
    }

    @Override
    public void filterValueChanged(FilterEvent filterEvent) {
        planarImage = filterEvent.getValue();
        if (planarImage != null) {
            this.repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        if (planarImage != null) {
            Image img = planarImage.getAsBufferedImage();
            this.setSize(planarImage.getWidth(), planarImage.getHeight());
            Dimension dimension = this.getSize();
            g.drawImage(img, 0, 0, dimension.width, dimension.height, null);
        }
    }
}
