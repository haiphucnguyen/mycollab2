package com.esofthead.vaadin.mobilecomponent.client;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class VMobileViewToolbar extends ScrollPanel implements ScrollHandler {
	private static final String CLASSNAME = "v-mobileview-toolbar";

	private final Element prevBtn;
	private final Element nextBtn;
	private int overflowWidth = 0;

	protected HandlerRegistration handler;

	public VMobileViewToolbar() {
		super();
		addStyleName(CLASSNAME);

		prevBtn = Document.get().createDivElement().cast();
		prevBtn.addClassName(CLASSNAME + "-prevBtn");
		prevBtn.addClassName("icon-chevron-left");
		getElement().appendChild(prevBtn);

		nextBtn = Document.get().createDivElement().cast();
		nextBtn.addClassName(CLASSNAME + "-nextBtn");
		nextBtn.addClassName("icon-chevron-right");
		getElement().appendChild(nextBtn);

		Style style = getContainerElement().getStyle();
		style.setOverflow(Overflow.AUTO);
		style.setHeight(100, Unit.PCT);

		DOM.sinkEvents(getElement(), Event.ONSCROLL);
	}

	@Override
	protected Element getScrollableElement() {
		return getContainerElement();
	}

	@Override
	protected void onAttach() {
		super.onAttach();

		handler = addScrollHandler(this);
		attachClickListeners(prevBtn, nextBtn);
	}

	@Override
	protected void onDetach() {
		super.onDetach();

		handler.removeHandler();
		removeClickListeners(prevBtn, nextBtn);
	}

	private native void attachClickListeners(Element prevBtn, Element nextBtn) /*-{
		var self = this;
	    prevBtn.onclick = $entry(function (){
	    	self.@com.esofthead.vaadin.mobilecomponent.client.VMobileViewToolbar::scrollToLeft()();
	    });
	    nextBtn.onclick = $entry(function (){
	    	self.@com.esofthead.vaadin.mobilecomponent.client.VMobileViewToolbar::scrollToRight()();
	    });
    }-*/;

	private native void removeClickListeners(Element prevBtn, Element nextBtn) /*-{
		prevBtn.onclick = null;
		nextBtn.onclick = null;
	}-*/;

	@Override
	public void setWidget(Widget w) {
		super.setWidget(w);

		Scheduler.get().scheduleFinally(new Scheduler.ScheduledCommand() {
			@Override
			public void execute() {
				checkWidth();
			}
		});
	}

	public void checkWidth() {
		if (getMaximumHorizontalScrollPosition() > 0) {
			overflowWidth = getMaximumHorizontalScrollPosition();
			nextBtn.getStyle().setDisplay(Display.BLOCK);
		} else {
			overflowWidth = 0;
			nextBtn.getStyle().setDisplay(Display.NONE);
		}
	}

	@Override
	public void onScroll(ScrollEvent event) {
		int scrollLeft = getHorizontalScrollPosition();
		Style leftStyle = prevBtn.getStyle();
		Style rightStyle = nextBtn.getStyle();

		if (scrollLeft == 0) {
			leftStyle.setDisplay(Display.NONE);
			rightStyle.setDisplay(Display.BLOCK);
		} else {
			leftStyle.setDisplay(Display.BLOCK);
			if (scrollLeft == overflowWidth)
				rightStyle.setDisplay(Display.NONE);
			else
				rightStyle.setDisplay(Display.BLOCK);
		}
	}

	@Override
	public Element getContainerElement() {
		return super.getContainerElement();
	}

}
