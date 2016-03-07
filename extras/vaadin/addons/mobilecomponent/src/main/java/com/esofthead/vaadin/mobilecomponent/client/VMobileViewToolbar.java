package com.esofthead.vaadin.mobilecomponent.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.touchkit.gwt.client.ui.TouchButton;

public class VMobileViewToolbar extends ScrollPanel implements ScrollHandler {
	private static final String CLASSNAME = "v-mobileview-toolbar";

	private final TouchButton prevBtn;
	private final TouchButton nextBtn;
	private int overflowWidth = 0;

	protected HandlerRegistration handler;

	public VMobileViewToolbar() {
		super();
		addStyleName(CLASSNAME);

		prevBtn = GWT.create(TouchButton.class);
		prevBtn.addStyleName(CLASSNAME + "-prevBtn");
		prevBtn.addStyleName("icon-chevron-left");
		DOM.appendChild(getElement(), prevBtn.getElement());

		nextBtn = GWT.create(TouchButton.class);
		nextBtn.addStyleName(CLASSNAME + "-nextBtn");
		nextBtn.addStyleName("icon-chevron-right");
		DOM.appendChild(getElement(), nextBtn.getElement());

		Style style = getContainerElement().getStyle();
		style.setOverflow(Overflow.AUTO);
		style.setHeight(100, Unit.PCT);
		style.setTextAlign(TextAlign.CENTER);

		DOM.sinkEvents(getElement(), Event.ONSCROLL);
	}

	@Override
	protected com.google.gwt.user.client.Element getScrollableElement() {
		return getContainerElement();
	}

	@Override
	protected void onAttach() {
		super.onAttach();

		handler = addScrollHandler(this);
		attachClickListeners(prevBtn.getElement(), nextBtn.getElement());
	}

	@Override
	protected void onDetach() {
		super.onDetach();

		handler.removeHandler();
		removeClickListeners(prevBtn.getElement(), nextBtn.getElement());
	}

	private native void attachClickListeners(Element prevBtn, Element nextBtn)
	/*-{
		var self = this;
		prevBtn.onclick = $entry(function (){
			self.@com.esofthead.vaadin.mobilecomponent.client.VMobileViewToolbar::scrollToLeft()();
		});
		nextBtn.onclick = $entry(function (){
			self.@com.esofthead.vaadin.mobilecomponent.client.VMobileViewToolbar::scrollToRight()();
		});
	}-*/;

	private native void removeClickListeners(Element prevBtn, Element nextBtn)
	/*-{
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
			nextBtn.getElement().getStyle().setDisplay(Display.BLOCK);
		} else {
			overflowWidth = 0;
			nextBtn.getElement().getStyle().setDisplay(Display.NONE);
		}
	}

	@Override
	public void onScroll(ScrollEvent event) {
		int scrollLeft = getHorizontalScrollPosition();
		Style leftStyle = prevBtn.getElement().getStyle();
		Style rightStyle = nextBtn.getElement().getStyle();

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
	public com.google.gwt.user.client.Element getContainerElement() {
		return super.getContainerElement();
	}

}
