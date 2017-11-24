package bean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;

public class QualityControl implements ICoordinateListener, Serializable {

    private int xTolerance;
    private int yTolerance;
    private String outputFilePath;

    private ArrayList<Coordinate> coordinates;
    private ArrayList<Coordinate> expectedCoordinates = new ArrayList<Coordinate>();

    public QualityControl() {
        xTolerance = 3;
        yTolerance = 3;
        outputFilePath = "C:\\Systemarchitekturen\\Outputfiles\\tolerances.txt";

        expectedCoordinates.add(new Coordinate(73,77));
        expectedCoordinates.add(new Coordinate(110,80));
        expectedCoordinates.add(new Coordinate(202,80));
        expectedCoordinates.add(new Coordinate(265,79));
        expectedCoordinates.add(new Coordinate(330,81));
        expectedCoordinates.add(new Coordinate(396,81));
    }

    public int getXTolerance() {
        return xTolerance;
    }

    public void setXTolerance(int xTolerance) {
        this.xTolerance = xTolerance;
        updateOutputfile();
    }

    public int getYTolerance() {
        return yTolerance;
    }

    public void setYTolerance(int yTolerance) {
        this.yTolerance = yTolerance;
        updateOutputfile();
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
        updateOutputfile();
    }

    @Override
    public void filterValueChanged(CoordinateEvent coordinateEvent) {
        coordinates = coordinateEvent.getValue();
        updateOutputfile();
    }

    private void updateOutputfile() {
        if(coordinates == null) {
            return;
        }

        File outputFile = new File(outputFilePath);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write("Auswertung Bildverarbeitung" + System.lineSeparator() + System.lineSeparator());
            bw.write("Es wurden " + coordinates.size() + " Scheiben gefunden."  + System.lineSeparator());
            bw.write("Gefundene Zentren: "  + System.lineSeparator());

            for(Coordinate coordinate : coordinates) {
                bw.write("x: " + coordinate._x + "\ty: " + coordinate._y + System.lineSeparator());
            }

            bw.write(System.lineSeparator() + System.lineSeparator());
            bw.write("Max. x-Toleranz: " + xTolerance + System.lineSeparator());
            bw.write("Max. y-Toleranz: " + yTolerance + System.lineSeparator() + System.lineSeparator());

            boolean xValid = false;
            boolean yValid = false;
            for(int i = 0; i < coordinates.size(); i++) {

                if(Math.abs(expectedCoordinates.get(i)._x - coordinates.get(i)._x) > xTolerance) {
                    xValid = false;
                }else {
                    xValid = true;
                }

                if(Math.abs(expectedCoordinates.get(i)._y - coordinates.get(i)._y) > yTolerance) {
                    yValid = false;
                }else {
                    yValid = true;
                }

                bw.write(i+1 + ". Scheibe:\tx-Soll: " + expectedCoordinates.get(i)._x + "\tx-Ist: " + coordinates.get(i)._x + "\t"+ (xValid?"OK" : "FAIL") + System.lineSeparator());
                bw.write(i+1 + ". Scheibe:\ty-Soll: " + expectedCoordinates.get(i)._y + "\ty-Ist: " + coordinates.get(i)._y + "\t"+ (yValid?"OK" : "FAIL") + System.lineSeparator());
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}