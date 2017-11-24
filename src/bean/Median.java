package bean;

import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;
import java.io.Serializable;
import java.util.Vector;

public class Median  implements Serializable, IFilterListener {

    private int maskSize;
    private Vector listeners;


    public Median() {
        maskSize = 6 ;
        listeners = new Vector();
    }

    public int getMaskSize() {
        return maskSize;
    }

    public void setMaskSize(int maskSize) {
        this.maskSize = maskSize;
    }

    @Override
    public void filterValueChanged(FilterEvent filterEvent) {
        PlanarImage image = filterEvent.getValue();
        image = MedianFilterDescriptor.create(image, MedianFilterDescriptor.MEDIAN_MASK_SQUARE, maskSize, null);
        fireFilterEvent(image);
    }

    protected  void fireFilterEvent(PlanarImage image) {
        Vector v =  (Vector)listeners.clone();
        FilterEvent fe = new FilterEvent(this, image);
        for(int i = 0; i < v.size(); i++) {
            IFilterListener fl = (IFilterListener)v.elementAt(i);
            fl.filterValueChanged(fe);
        }
    }


    public void addIFilterListener(IFilterListener filterListener) {
        listeners.addElement(filterListener);
    }

    public void removeIFilterListener(IFilterListener filterListener) {
        listeners.remove(filterListener);
    }
}
