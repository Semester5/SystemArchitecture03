package bean;

import bean.FilterEvent;

import java.util.EventListener;

public interface IFilterListener extends EventListener {
    void filterValueChanged(FilterEvent filterEvent);
}