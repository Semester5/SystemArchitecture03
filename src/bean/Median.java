package bean;

import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;
import java.io.Serializable;
import java.util.Vector;

public class Median  implements IFilterListener, Serializable {

    private int maskSize;
    private Vector listener;


    public Median() {
        maskSize = 6 ;
        listener = new Vector();
    }

    public int getMaskSize() {
        return maskSize;
    }

    public void setMaskSize(int maskSize) {
        this.maskSize = maskSize;
    }

    public void addIFilterListener(IFilterListener filterListener) {
        listener.addElement(filterListener);
    }

    public void removeIFilterListener(IFilterListener filterListener) {
        listener.remove(filterListener);
    }

    @Override
    public void filterValueChanged(FilterEvent filterEvent) {
        PlanarImage planarImage = filterEvent.getValue();
        planarImage = MedianFilterDescriptor.create(planarImage, MedianFilterDescriptor.MEDIAN_MASK_SQUARE, maskSize, null);
        fireFilterEvent(planarImage);
    }

    protected  void fireFilterEvent(PlanarImage planarImage) {
        Vector clonedVector =  (Vector) listener.clone();
        FilterEvent filterEvent = new FilterEvent(this, planarImage);

        for(int i = 0; i < clonedVector.size(); i++) {
            IFilterListener filterListener = (IFilterListener)clonedVector.elementAt(i);
            filterListener.filterValueChanged(filterEvent);
        }
    }
}
