package bean;

import bean.CoordinateEvent;

import java.util.EventListener;

public interface ICoordinateListener extends EventListener {
     void filterValueChanged(CoordinateEvent coordinateEvent);
}
