package bean;

import java.beans.*;
import java.lang.reflect.Method;

public class DisplayBeanInfo extends SimpleBeanInfo {

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor pd[] = { };
            return pd;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public MethodDescriptor[] getMethodDescriptors() {
        try {
            Class c = Display.class;
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
