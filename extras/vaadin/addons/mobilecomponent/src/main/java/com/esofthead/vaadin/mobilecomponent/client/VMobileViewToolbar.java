package com.esofthead.vaadin.mobilecomponent.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public class VMobileViewToolbar extends ComplexPanel implements ScrollHandler {
	private static final String CLASSNAME = "v-mobileview-toolbar";

	private static Logger log = Logger.getLogger(VMobileViewToolbar.class.getName());

	private final DivElement prevBtn;
	private final DivElement nextBtn;
	private int overflowWidth = 0;
	private Widget onlyChild;
	private final Element wrapper;

	public VMobileViewToolbar() {
		setElement(Document.get().createDivElement());
		setStylePrimaryName(CLASSNAME);

		wrapper = Document.get().createDivElement().cast();
		wrapper.addClassName(CLASSNAME + "-wrap");
		wrapper.getStyle().setWidth(100, Unit.PCT);
		wrapper.getStyle().setHeight(100, Unit.PCT);
		getElement().appendChild(wrapper);

		prevBtn = Document.get().createDivElement();
		prevBtn.addClassName(CLASSNAME + "-prevBtn");
		prevBtn.setInnerText("<<");
		getElement().appendChild(prevBtn);

		nextBtn = Document.get().createDivElement();
		nextBtn.addClassName(CLASSNAME + "-nextBtn");
		nextBtn.setInnerText(">>");
		getElement().appendChild(nextBtn);

		DOM.sinkEvents(getElement(), Event.ONSCROLL | Event.ONCLICK);
		/*touchScrollHandler = TouchScrollDelegate
				.enableTouchScrolling(this, getElement());*/
		addHandler(this, ScrollEvent.getType());
	}

	@Override
	public void add(Widget w) {
		add(w, getWrapper());
		checkWidth();
	}

	public void setWidget(Widget w) {
		if (onlyChild != null && onlyChild != w) {
			remove(onlyChild);
		}
		onlyChild = w;
		if (onlyChild.getParent() != this) {
			add(onlyChild, getWrapper());
			Scheduler.get().scheduleFinally(new Scheduler.ScheduledCommand()
			{
				@Override
				public void execute() {
					checkWidth();
				}
			});
		}
	}

	@Override
	public boolean remove(Widget w) {
		boolean removed = super.remove(w);
		if (removed) {
			if (onlyChild == w) {
				onlyChild = null;
			}
		}
		return removed;
	}

	public Element getContainerElement() {
		if (onlyChild != null) {
			return onlyChild.getElement();
		}
		return null;
	}

	public Element getWrapper() {
		return this.wrapper;
	}

	public void checkWidth() {

		if (onlyChild != null && getContainerElement().getOffsetWidth() > getWrapper().getOffsetWidth()) {
			overflowWidth = getContainerElement().getOffsetWidth() - getWrapper().getOffsetWidth();
			nextBtn.getStyle().setDisplay(Display.BLOCK);
		} else {
			log.log(Level.INFO, "content's width: " + getContainerElement().getOffsetWidth());
			overflowWidth = 0;
			nextBtn.getStyle().setDisplay(Display.NONE);
		}
	}

	@Override
	public void onScroll(ScrollEvent event) {
		int scrollLeft = getWrapper().getScrollLeft();
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
	public void onBrowserEvent(Event event) {
		if (event.getTypeInt() == Event.ONCLICK) {
			if(DOM.eventGetTarget(event) == prevBtn.cast())
			{
				getElement().setScrollLeft(0);
			} else if (DOM.eventGetTarget(event) == nextBtn.cast()) {
				getElement().setScrollLeft(overflowWidth);
			}
		}
	}
}
