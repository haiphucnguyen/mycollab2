package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;

public class UiUtils {
	public static void addComponent(AbstractOrderedLayout parent,
			Component child, Alignment alignment) {
		parent.addComponent(child);
		parent.setComponentAlignment(child, alignment);
	}
}
