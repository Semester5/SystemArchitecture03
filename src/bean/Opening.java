package bean;

import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.DilateDescriptor;
import javax.media.jai.operator.ErodeDescriptor;
import java.io.Serializable;
import java.util.Vector;

public class Opening extends BaseFilter {

    private int matrixSize;
    private int erodeLoops;
    private int dilateLoops;

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
        updatePlanarImage();
    }

    public int getErodeLoops() {
        return erodeLoops;
    }

    public void setErodeLoops(int erodeLoops) {
        this.erodeLoops = erodeLoops;
        updatePlanarImage();
    }

    public int getDilateLoops() {
        return dilateLoops;
    }

    public void setDilateLoops(int dilateLoops) {
        this.dilateLoops = dilateLoops;
        updatePlanarImage();
    }

    @Override
    protected void updatePlanarImage() {
        float[] kernelMatrix = new float[matrixSize * matrixSize];
        for(int i = 0; i < matrixSize * matrixSize; i++) {
            kernelMatrix[i] = 1;
        }

        PlanarImage newPlanarImage = planarImage;
        KernelJAI kernel = new KernelJAI(matrixSize, matrixSize, kernelMatrix);

        for(int i = 0; i < erodeLoops; i++) {
            newPlanarImage = ErodeDescriptor.create(newPlanarImage, kernel, null);
        }

        for(int i = 0; i < dilateLoops; i++) {
            newPlanarImage = DilateDescriptor.create(newPlanarImage, kernel, null);
        }
        fireFilterEvent(newPlanarImage);
    }
}