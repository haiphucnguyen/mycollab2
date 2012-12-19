package com.esofthead.mycollab.vaadin.mvp;

import java.io.Serializable;

import com.vaadin.ui.ComponentContainer;

public interface Presenter extends Serializable{
	void go(ComponentContainer container);

	void go(ComponentContainer container, ScreenData<?> data);
	
	void go(ComponentContainer container, ScreenData<?> data, boolean isHistoryTrack);
}
