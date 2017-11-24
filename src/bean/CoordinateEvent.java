package bean;

import CalcCentroidsFilterPkg.Coordinate;

import java.util.ArrayList;
import java.util.EventObject;

public class CoordinateEvent extends EventObject {

    private ArrayList<Coordinate> coordinates;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CoordinateEvent(Object source , ArrayList<Coordinate> coordinates) {
        super(source);
        this.coordinates = coordinates;
    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
}