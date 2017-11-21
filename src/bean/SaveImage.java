package bean;

import javax.imageio.ImageIO;
import javax.media.jai.PlanarImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

public class SaveImage  implements Serializable, IFilterListener  {

    private String pathName;
    private String formatName;

    public SaveImage() {
        pathName = "C:\\export.png";
        formatName = "png";
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    @Override
    public void filterValueChanged(FilterEvent filterEvent) {
        PlanarImage image = filterEvent.getValue();
        if(!pathName.equals("") && !formatName.equals("")) {
            try {
                // retrieve image
                BufferedImage bi = image.getAsBufferedImage();
                File outputfile = new File(pathName);
                ImageIO.write(bi, formatName, outputfile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
