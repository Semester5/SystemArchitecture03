package bean;

import java.beans.EventSetDescriptor;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class SourceBeanInfo extends SimpleBeanInfo{

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor imagePath;
            imagePath = new PropertyDescriptor("imagePath", SourceReader.class);
            PropertyDescriptor pd[] = { imagePath };
            return pd;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            EventSetDescriptor esd1;
            Class c = SourceReader.class;
            String es = "filter";
            Class lc = IFilterListener.class;
            String names[] = { "filterValueChanged" };
            String al = "addIFilterListener";
            String rl  = "removeIFilterListener";
            esd1 = new EventSetDescriptor(c, es, lc, names, al, rl);
            EventSetDescriptor esd[] = { esd1 };
            return esd;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        MethodDescriptor mds[] = {};
        return mds;
    }
}
