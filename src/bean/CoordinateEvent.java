package bean;

import CalcCentroidsFilterPkg.Coordinate;

import java.util.ArrayList;
import java.util.EventObject;

public class CoordinateEvent extends EventObject {
    private ArrayList<Coordinate> value;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CoordinateEvent(Object source , ArrayList<Coordinate> coordinates) {
        super(source);
        this.value = coordinates;
    }

    public ArrayList<Coordinate> getValue() {
        return value;
    }

    public void setValue(ArrayList<Coordinate> value) {
        this.value = value;
    }
}
