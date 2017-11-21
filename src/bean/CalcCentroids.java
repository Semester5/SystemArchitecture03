package bean;

import CalcCentroidsFilterPkg.Coordinate;

import javax.media.jai.PlanarImage;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.*;

public class CalcCentroids  implements Serializable, IFilterListener   {

    private HashMap<Coordinate, Boolean> _general = new HashMap<Coordinate, Boolean>();
    private LinkedList<ArrayList<Coordinate>> _figures = new LinkedList<ArrayList<Coordinate>>();
    private Vector listeners;

    public CalcCentroids() {
        listeners = new Vector();
    }

    @Override
    public void filterValueChanged(FilterEvent filterEvent) {
        PlanarImage image = filterEvent.getValue();
        BufferedImage bi = image.getAsBufferedImage();

        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                int p = bi.getRaster().getSample(x, y, 0);
                if (p == 255 && _general.containsKey(new Coordinate(x, y)) == false) {
                    getNextFigure(bi, x, y);        //if there is a not visited white pixel, save all pixels belonging to the same figure
                }
            }
        }

        ArrayList<Coordinate> coordinates = calculateCentroids(image);

    }
    protected  void fireFilterEvent(ArrayList<Coordinate> coordinates) {
        Vector v =  (Vector)listeners.clone();
        CoordinateEvent fe = new CoordinateEvent(this, coordinates);
        for(int i = 0; i < v.size(); i++) {
            ICoordinateListener fl = (ICoordinateListener)v.elementAt(i);
            fl.filterValueChanged(fe);
        }
    }

    public void addIFilterListener(IFilterListener filterListener) {
        listeners.addElement(filterListener);
    }

    public void removeIFilterListener(IFilterListener filterListener) {
        listeners.remove(filterListener);
    }

    private void getNextFigure(BufferedImage img, int x, int y) {
        ArrayList<Coordinate> figure = new ArrayList<Coordinate>();
        _general.put(new Coordinate(x, y), true);
        figure.add(new Coordinate(x, y));

        addConnectedComponents(img, figure, x, y);

        _figures.add(figure);
    }

    private void addConnectedComponents(BufferedImage img, ArrayList<Coordinate> figure, int x, int y) {
        if (x - 1 >= 0 && _general.containsKey((new Coordinate(x - 1, y))) == false && img.getRaster().getSample(x - 1, y, 0) == 255) {
            _general.put(new Coordinate(x - 1, y), true);
            figure.add(new Coordinate(x - 1, y));
            addConnectedComponents(img, figure, x - 1, y);
        }
        if (x + 1 < img.getWidth() && _general.containsKey((new Coordinate(x + 1, y))) == false && img.getRaster().getSample(x + 1, y, 0) == 255) {
            _general.put(new Coordinate(x + 1, y), true);
            figure.add(new Coordinate(x + 1, y));
            addConnectedComponents(img, figure, x + 1, y);
        }
        if (y - 1 >= 0 && _general.containsKey((new Coordinate(x, y - 1))) == false && img.getRaster().getSample(x, y - 1, 0) == 255) {
            _general.put(new Coordinate(x, y - 1), true);
            figure.add(new Coordinate(x, y - 1));
            addConnectedComponents(img, figure, x, y - 1);
        }
        if (y + 1 < img.getHeight() && _general.containsKey((new Coordinate(x, y + 1))) == false && img.getRaster().getSample(x, y + 1, 0) == 255) {
            _general.put(new Coordinate(x, y + 1), true);
            figure.add(new Coordinate(x, y + 1));
            addConnectedComponents(img, figure, x, y + 1);
        }
    }

    private ArrayList<Coordinate> calculateCentroids(PlanarImage image) {
        ArrayList<Coordinate> centroids = new ArrayList<Coordinate>();
        int i = 0;
        for (ArrayList<Coordinate> figure : _figures) {
            LinkedList<Integer> xValues = new LinkedList<Integer>();
            LinkedList<Integer> yValues = new LinkedList<Integer>();

            for (Coordinate c : figure) {
                xValues.add(c._x);
                yValues.add(c._y);
            }

            Collections.sort(xValues);
            Collections.sort(yValues);

            int xMedian = xValues.get(xValues.size() / 2);
            int yMedian = yValues.get(yValues.size() / 2);

            centroids.add(new Coordinate(xMedian + (Integer) image.getProperty("offsetX"), yMedian + (Integer) image.getProperty("offsetY")));
            i++;
        }
        return centroids;
    }

}