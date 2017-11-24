package bean;

import javax.media.jai.PlanarImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public abstract class BaseFilter implements IFilterListener, Serializable {
    protected Vector listener;

    protected PlanarImage planarImage;

    @Override
    public void filterValueChanged(FilterEvent filterEvent) {
        this.planarImage = filterEvent.getValue();
        updatePlanarImage();
    }

    protected abstract void updatePlanarImage();

    protected void fireFilterEvent(PlanarImage planarImage) {
        Vector clonedVector =  (Vector) listener.clone();
        FilterEvent filterEvent = new FilterEvent(this, planarImage);
        for(int i = 0; i < clonedVector.size(); i++) {
            IFilterListener filterListener = (IFilterListener)clonedVector.elementAt(i);
            filterListener.filterValueChanged(filterEvent);
        }
    }

    protected void fireFilterEvent(ArrayList<Coordinate> coordinates) {
        Vector clonedVector =  (Vector) listener.clone();
        CoordinateEvent coordinateEvent = new CoordinateEvent(this, coordinates);

        for(int i = 0; i < clonedVector.size(); i++) {
            ICoordinateListener coordinateListener = (ICoordinateListener) clonedVector.elementAt(i);
            coordinateListener.filterValueChanged(coordinateEvent);
        }
    }
}
