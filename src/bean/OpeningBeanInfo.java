package bean;

import java.beans.*;
import java.lang.reflect.Method;

public class OpeningBeanInfo extends SimpleBeanInfo {
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor matrixSize, erodeLoops, dilateLoops;
            Class cls = Opening.class;
            matrixSize = new PropertyDescriptor("matrixSize", cls);
            erodeLoops = new PropertyDescriptor("erodeLoops", cls);
            dilateLoops = new PropertyDescriptor("dilateLoops", cls);
            PropertyDescriptor pd[] = { matrixSize, erodeLoops, dilateLoops};
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
            Class c = Opening.class;
            String es = "opening";
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
            Class c = Opening.class;
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
