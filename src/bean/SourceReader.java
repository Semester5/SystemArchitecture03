package bean;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.io.Serializable;
import java.util.Vector;

/**
 * Created by Christina on 30.10.2017.
 */
public class SourceReader implements Runnable, Serializable{

    private String imagePath;
    private PlanarImage planarImage;
    private transient Thread thread;
    private Vector listener;

    public SourceReader() {
        this.imagePath = "C:\\Users\\julia\\Documents\\Fachhochschule Vorarlberg\\Semester5\\Systemarchitekturen\\Übung\\Übung03\\loetstellen.jpg";
        planarImage = null;
        this.listener = new Vector();

        startThread();
    }

    private void startThread() {
        thread = new Thread(this);
        thread.start();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void addIFilterListener(IFilterListener filterListener) {
        listener.addElement(filterListener);
    }

    public void removeIFilterListener(IFilterListener filterListener) {
        listener.remove(filterListener);
    }

    @Override
    public void run() {
        try {
            while(true) {
                String input = imagePath;

                if(input.isEmpty()) {
                    Thread.sleep(1000);
                    continue;
                }
                this.planarImage =  JAI.create("fileload", input);

                fireFilterEvent();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected synchronized void fireFilterEvent() {
        Vector clonedListener;
        synchronized (this) {
            clonedListener = (Vector) listener.clone();
        }
        FilterEvent filterEvent = new FilterEvent(this, planarImage);

        for(int i = 0; i < clonedListener.size(); i++) {
            IFilterListener filterListener = (IFilterListener) clonedListener.elementAt(i);
            filterListener.filterValueChanged(filterEvent);
        }
    }
}