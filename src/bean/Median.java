package bean;

import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;
import javax.media.jai.operator.ThresholdDescriptor;
import java.io.Serializable;
import java.util.Vector;

public class Median extends BaseFilter {

    private int maskSize;

    public Median() {
        maskSize = 6 ;
        listener = new Vector();
    }

    public int getMaskSize() {
        return maskSize;
    }

    public void setMaskSize(int maskSize) {
        this.maskSize = maskSize;
        updatePlanarImage();
    }

    @Override
    protected void updatePlanarImage() {
        PlanarImage newPlanarImage = MedianFilterDescriptor.create(planarImage, MedianFilterDescriptor.MEDIAN_MASK_SQUARE, maskSize, null);
        fireFilterEvent(newPlanarImage);
    }
}
