package bean;

import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.DilateDescriptor;
import javax.media.jai.operator.ErodeDescriptor;
import java.io.Serializable;
import java.util.Vector;

public class Opening implements IFilterListener, Serializable {

    private int matrixSize;
    private int erodeLoops;
    private int dilateLoops;
    private Vector listener;

    public Opening() {
        matrixSize = 5;
        erodeLoops = 2;
        dilateLoops = 2;
        listener = new Vector();
    }

    public int getMatrixSize() {
        return matrixSize;
    }

    public void setMatrixSize(int matrixSize) {
        this.matrixSize = matrixSize;
    }

    public int getErodeLoops() {
        return erodeLoops;
    }

    public void setErodeLoops(int erodeLoops) {
        this.erodeLoops = erodeLoops;
    }

    public int getDilateLoops() {
        return dilateLoops;
    }

    public void setDilateLoops(int dilateLoops) {
        this.dilateLoops = dilateLoops;
    }

    public void addIFilterListener(IFilterListener filterListener) {
        listener.addElement(filterListener);
    }

    public void removeIFilterListener(IFilterListener filterListener) {
        listener.remove(filterListener);
    }

    @Override
    public void filterValueChanged(FilterEvent filterEvent) {
        float[] kernelMatrix = new float[matrixSize * matrixSize];
        for(int i = 0; i < matrixSize * matrixSize; i++) {
            kernelMatrix[i] = 1;
        }

        PlanarImage image = filterEvent.getValue();
        KernelJAI kernel = new KernelJAI(matrixSize, matrixSize, kernelMatrix);

        for(int i = 0; i < erodeLoops; i++) {
            image = ErodeDescriptor.create(image, kernel, null);
        }

        for(int i = 0; i < dilateLoops; i++) {
            image = DilateDescriptor.create(image, kernel, null);
        }
        fireFilterEvent(image);
    }

    protected  void fireFilterEvent(PlanarImage image) {
        Vector clonedVector =  (Vector) listener.clone();
        FilterEvent filterEvent = new FilterEvent(this, image);
        for(int i = 0; i < clonedVector.size(); i++) {
            IFilterListener filterListener = (IFilterListener)clonedVector.elementAt(i);
            filterListener.filterValueChanged(filterEvent);
        }
    }
}
