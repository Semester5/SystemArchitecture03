package bean;

import javax.media.jai.PlanarImage;
import java.util.EventObject;

public class FilterEvent extends EventObject {
    private PlanarImage value;

    public FilterEvent(Object source, PlanarImage value) {
        super(source);
        this.value = value;
    }

    public PlanarImage getValue() {
        return value;
    }

    public void setValue(PlanarImage value) {
        this.value = value;
    }
}
