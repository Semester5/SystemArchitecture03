package bean;

import java.util.EventListener;

public interface IFilterListener extends EventListener{
    public abstract void filterValueChanged(FilterEvent filterEvent);
}
