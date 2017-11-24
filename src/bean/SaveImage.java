package bean;

import javax.imageio.ImageIO;
import javax.media.jai.PlanarImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

public class SaveImage extends BaseFilter {

    private String pathName;
    private String formatName;

    public SaveImage() {
        pathName = "C:\\Systemarchitekturen\\Outputfiles\\SavedPlanarImage.png";
        formatName = "png";
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
        updatePlanarImage();
    }

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
        updatePlanarImage();
    }

    @Override
    protected void updatePlanarImage() {
        try {
            if(!pathName.isEmpty() && !formatName.isEmpty()) {
                // retrieve image
                BufferedImage bufferedImage = planarImage.getAsBufferedImage();
                File outputfile = new File(pathName);
                ImageIO.write(bufferedImage, formatName, outputfile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}