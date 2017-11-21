package bean;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.io.Serializable;
import java.util.Vector;

/**
 * Created by Christina on 30.10.2017.
 */
public class SourceReader implements  Runnable, Serializable{

    private Vector listeners;
    private String imagePath;
    private PlanarImage image;
    private transient Thread t;

    public SourceReader() {
        this.imagePath = "";
        this.listeners = new Vector();
        image = null;
        startThread();
    }

    private void startThread() {
        t = new Thread(this);
        t.start();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void addIFilterListener(IFilterListener filterListener) {
        listeners.addElement(filterListener);
    }

    public void removeIFilterListener(IFilterListener filterListener) {
        listeners.remove(filterListener);
    }

    @Override
    public void run() {
        try {
            while(true) {
                String input = imagePath;

                if(input.equals("")) {
                    Thread.sleep(1000);
                    continue;
                }

                this.image =  JAI.create("fileload", input);

                fireFilterEvent();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected synchronized void fireFilterEvent() {
        Vector v;
        synchronized (this) {
            v = (Vector)listeners.clone();
        }
        FilterEvent fe = new FilterEvent(this, image);
        for(int i = 0; i < v.size(); i++) {
            IFilterListener fl = (IFilterListener)v.elementAt(i);
            fl.filterValueChanged(fe);
        }
    }
}