package com.esofthead.vaadin.mobilecomponent.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public class VMobileViewToolbar extends ComplexPanel {
	private static final String CLASSNAME = "v-mobileview-toolbar";

	private final DivElement prevBtn;
	private final DivElement nextBtn;
	private int overflowWidth = 0;
	private Widget onlyChild;
	private final Element wrapper;
	public JavaScriptObject scrollHandler;

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
		prevBtn.addClassName("icon-chevron-left");
		getElement().appendChild(prevBtn);

		nextBtn = Document.get().createDivElement();
		nextBtn.addClassName(CLASSNAME + "-nextBtn");
		nextBtn.addClassName("icon-chevron-right");
		getElement().appendChild(nextBtn);

		DOM.sinkEvents(getElement(), Event.ONCLICK);
		DOM.sinkEvents(getWrapper(), Event.ONSCROLL);
		initHandler(getWrapper());
	}

	protected native void initHandler(Element el) /*-{
		var self = this;
		this.@com.esofthead.vaadin.mobilecomponent.client.VMobileViewToolbar::scrollHandler = function() {
			self.@com.esofthead.vaadin.mobilecomponent.client.VMobileViewToolbar::onScroll()();
		};
	}-*/;

	private native boolean isRTL(Element elem) /*-{
	    var style = elem.ownerDocument.defaultView.getComputedStyle(elem, null);
	    return style.direction == 'rtl';
	}-*/;

	@Override
	protected void onAttach() {
		super.onAttach();
		attachListener(getWrapper());
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		removeListener(getWrapper());
	}

	private native void attachListener(Element el) /*-{
		var self = this;
		el.addEventListener('scroll', self.@com.esofthead.vaadin.mobilecomponent.client.VMobileViewToolbar::scrollHandler);
	}-*/;

	private native void removeListener(Element el) /*-{
		var self = this;
		el.removeEventListener('scroll', self.@com.esofthead.vaadin.mobilecomponent.client.VMobileViewToolbar::scrollHandler);
	}-*/;

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
			overflowWidth = 0;
			nextBtn.getStyle().setDisplay(Display.NONE);
		}
	}

	public void onScroll() {
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
				getWrapper().setScrollLeft(0);
			} else if (DOM.eventGetTarget(event) == nextBtn.cast()) {
				getWrapper().setScrollLeft(overflowWidth);
			}
		}
	}

}
