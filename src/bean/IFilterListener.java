package bean;

import java.util.EventListener;

public interface IFilterListener extends EventListener{
    void filterValueChanged(FilterEvent filterEvent);
}
