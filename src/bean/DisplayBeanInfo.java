package bean;

import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class DisplayBeanInfo extends SimpleBeanInfo {

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor min, max;
            min = new PropertyDescriptor("image", Display.class);
            PropertyDescriptor pd[] = { min };
            return pd;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
