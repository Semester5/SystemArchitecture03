package bean;

import java.util.EventListener;

public interface ICoordinateListener extends EventListener {
     void filterValueChanged(CoordinateEvent filterEvent);
}
