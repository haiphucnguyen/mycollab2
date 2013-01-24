package com.esofthead.mycollab.vaadin.mvp;

import com.vaadin.ui.ComponentContainer;
import java.io.Serializable;

public interface Presenter extends Serializable {
    
    void handleChain(ComponentContainer container, PageActionChain pageActionChain);

    void go(ComponentContainer container, ScreenData<?> data);

    void go(ComponentContainer container, ScreenData<?> data, boolean isHistoryTrack);
}
