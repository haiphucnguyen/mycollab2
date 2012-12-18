package com.esofthead.mycollab.vaadin.mvp;

import com.vaadin.ui.ComponentContainer;

public interface View extends ComponentContainer{
	ComponentContainer getWidget();
}
