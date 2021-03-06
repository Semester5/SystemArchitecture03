package bean;

import java.beans.*;
import java.lang.reflect.Method;

public class MedianBeanInfo extends SimpleBeanInfo {

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor maskSize;
            Class cls = Median.class;
            maskSize = new PropertyDescriptor("maskSize", cls);
            PropertyDescriptor pd[] = { maskSize};
            return pd;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            EventSetDescriptor esd1;
            Class c = Median.class;
            String es = "median";
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

    public MethodDescriptor[] getMethodDescriptors() {
        try {
            Class c = Median.class;
            Class parameterTypes[] = { FilterEvent.class };
            String name = "filterValueChanged";
            Method method1 = c.getMethod(name, parameterTypes);
            ParameterDescriptor pds[] = { new ParameterDescriptor() };
            MethodDescriptor md1 = new MethodDescriptor(method1, pds);
            MethodDescriptor mds[] = { md1 };
            return mds;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}