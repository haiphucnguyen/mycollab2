package com.esofthead.vaadin.mobilecomponent.client;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.SimplePanel;
import com.vaadin.client.ui.TouchScrollDelegate;
import com.vaadin.client.ui.TouchScrollDelegate.TouchScrollHandler;

public class VMobileViewToolbar extends SimplePanel {
	private static final String CLASSNAME = "v-mobileview-toolbar";

	private final DivElement prevBtn;
	private final DivElement nextBtn;
	private final TouchScrollHandler touchScrollHandler;

	public VMobileViewToolbar() {
		setElement(Document.get().createDivElement());
		setStylePrimaryName(CLASSNAME);

		prevBtn = Document.get().createDivElement();
		prevBtn.addClassName(CLASSNAME + "-prevBtn");
		getContainerElement().appendChild(prevBtn);

		nextBtn = Document.get().createDivElement();
		nextBtn.addClassName(CLASSNAME + "-nextBtn");
		getContainerElement().appendChild(nextBtn);

		touchScrollHandler = TouchScrollDelegate
				.enableTouchScrolling(this, getElement());
	}

	protected TouchScrollHandler getTouchScrollHanlder() {
		return this.touchScrollHandler;
	}
}
