package bean;

import com.sun.media.jai.widget.DisplayJAI;

import javax.media.jai.PlanarImage;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.Serializable;
import java.util.Vector;

public class Display extends Canvas implements Serializable, IFilterListener {

    private Vector listeners;
    private PlanarImage image;

    public Display() {
        this.listeners = new Vector();
    }

    public PlanarImage getImage() {
        return image;
    }

    public void setImage(PlanarImage image) {
        this.image = image;
    }

    public void addIFilterListener(IFilterListener filterListener) {
        listeners.addElement(filterListener);
    }

    public void removeIFilterListener(IFilterListener filterListener) {
        listeners.remove(filterListener);
    }

    @Override
    public void filterValueChanged(FilterEvent filterEvent) {
        image = filterEvent.getValue();
        repaint();
    }

    public void paint(Graphics g) {
        // Create a frame for display.
        JFrame frame = new JFrame();
        frame.setTitle("DisplayJAI: loetstellen.jpg");

        // Get the JFrame ContentPane.
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        // Create an instance of DisplayJAI.
        DisplayJAI dj = new DisplayJAI(this.image);

        // Add to the JFrame ContentPane an instance of JScrollPane
        // containing the DisplayJAI instance.
        contentPane.add(new JScrollPane(dj),BorderLayout.CENTER);

        // Set the closing operation so the application is finished.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500); // adjust the frame size.
        frame.setVisible(true); // show the frame.
    }
}
