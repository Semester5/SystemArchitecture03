package bean;

import java.beans.*;
import java.lang.reflect.Method;

public class TreshholdBeanInfo extends SimpleBeanInfo{

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor low, high, constant;
            Class cls = Treshhold.class;
            low = new PropertyDescriptor("low", cls);
            high = new PropertyDescriptor("high", cls);
            constant = new PropertyDescriptor("constant", cls);
            PropertyDescriptor pd[] = { low, high, constant};
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
            Class c = Treshhold.class;
            String es = "treshhold";
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
            Class c = Treshhold.class;
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
