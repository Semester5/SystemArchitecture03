package bean;

import java.beans.*;
import java.lang.reflect.Method;

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

    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            EventSetDescriptor esd1;
            Class c = Display.class;
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

    public MethodDescriptor[] getMethodDescriptors() {
        try {
            Class c = Display.class;
            Class parameterTypes[] = new Class[1];
            parameterTypes[0] = FilterEvent.class;
            String name = "filterValueChanged";
            Method method1 = c.getMethod(name, parameterTypes);
            ParameterDescriptor pds[] = new ParameterDescriptor[1];
            pds[0] = new ParameterDescriptor();
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
