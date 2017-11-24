package bean;

import java.beans.MethodDescriptor;
import java.beans.ParameterDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.lang.reflect.Method;

public class QualityControlBeanInfo extends SimpleBeanInfo {

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor xTolerance, yTolerance, outputFilePath;
            Class cls = QualityControl.class;
            xTolerance = new PropertyDescriptor("xTolerance", cls);
            yTolerance = new PropertyDescriptor("yTolerance", cls);
            outputFilePath = new PropertyDescriptor("outputFilePath", cls);
            PropertyDescriptor pd[] = { xTolerance, yTolerance, outputFilePath };
            return pd;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //getEventSetDescriptors is not necessary!

    public MethodDescriptor[] getMethodDescriptors() {
        try {
            Class c = QualityControl.class;
            Class parameterTypes[] = { CoordinateEvent.class };
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
