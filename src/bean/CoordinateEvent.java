package bean;

import java.util.ArrayList;
import java.util.EventObject;

public class CoordinateEvent extends EventObject {

    private ArrayList<Coordinate> value;

    public CoordinateEvent(Object source, ArrayList<Coordinate> coordinates) {
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