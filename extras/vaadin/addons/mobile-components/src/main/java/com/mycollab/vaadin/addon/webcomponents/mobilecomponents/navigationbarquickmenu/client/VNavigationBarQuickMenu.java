package com.mycollab.vaadin.addon.webcomponents.mobilecomponents.navigationbarquickmenu.client;

import java.util.Date;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchCancelHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.touchkit.gwt.client.ui.Css3Propertynames;
import com.vaadin.addon.touchkit.gwt.client.ui.VNavigationView;

@SuppressWarnings("deprecation")
public class VNavigationBarQuickMenu extends SimplePanel implements
		TouchStartHandler, TouchMoveHandler, TouchEndHandler,
		TouchCancelHandler, ClickHandler {

	private final Element containerElement;

	private Widget parent;

	private static final String CLASSNAME = "v-navbar-quickmenu";

	private static final String EXPANDED = "expanded";

	private boolean expanded = false;

	private boolean enabled;
	static final long IGNORE_SIMULATED_CLICKS_THRESHOLD = 1500;
	private boolean touchStarted = false;
	private Date fastClickAt;

	private final Element overlayElement1;
	private final Element overlayElement2;

	public VNavigationBarQuickMenu() {
		setStylePrimaryName(CLASSNAME);
		this.addStyleName(CLASSNAME + "-button");
		this.addDomHandler(this, TouchStartEvent.getType());
		this.addDomHandler(this, TouchMoveEvent.getType());
		this.addDomHandler(this, TouchEndEvent.getType());
		this.addDomHandler(this, TouchCancelEvent.getType());
		this.addDomHandler(this, ClickEvent.getType());

		Style style = this.getElement().getStyle();
		style.setZIndex(2);

		containerElement = DOM.createDiv();
		containerElement.setClassName(CLASSNAME + "-content");
		Event.sinkEvents(containerElement, Event.ONCLICK);
		Event.setEventListener(containerElement, new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				if (Event.ONCLICK == event.getTypeInt()) {
					toggleMenu();
				}
			}
		});

		EventListener overlayListener = new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				switch (event.getTypeInt()) {
				case Event.ONTOUCHSTART:
					touchStarted = true;
					fastClickAt = null;
					getElement().focus();
					break;

				case Event.ONTOUCHMOVE:
					touchStarted = false;
					break;

				case Event.ONTOUCHCANCEL:
					touchStarted = false;
					;
					break;

				case Event.ONTOUCHEND:
					if (touchStarted) {
						event.preventDefault();
						event.stopPropagation();
						NativeEvent evt = Document.get().createClickEvent(1,
								event.getScreenX(), event.getScreenY(),
								event.getClientX(), event.getClientY(), false,
								false, false, false);
						getElement().dispatchEvent(evt);
						touchStarted = false;
						fastClickAt = new Date();
					}
					break;

				case Event.ONCLICK:
					if (enabled) {
						if (fastClickAt != null
								&& (new Date().getTime() - fastClickAt
										.getTime()) < IGNORE_SIMULATED_CLICKS_THRESHOLD) {
							fastClickAt = null;
							break;
						}
						toggleMenu(false);
					}
					break;
				}
			}
		};

		overlayElement1 = DOM.createDiv();
		overlayElement1.setClassName(CLASSNAME + "-overlay");
		Event.sinkEvents(overlayElement1, Event.ONCLICK | Event.TOUCHEVENTS);
		Event.setEventListener(overlayElement1, overlayListener);
		style = overlayElement1.getStyle();
		style.setBackgroundColor("transparent");
		style.setPosition(Position.ABSOLUTE);
		style.setTop(0, Unit.PX);
		style.setLeft(0, Unit.PX);
		style.setWidth(100, Unit.PCT);
		style.setHeight(100, Unit.PCT);
		style.setZIndex(0);
		style.setDisplay(Display.NONE);

		overlayElement2 = DOM.createDiv();
		overlayElement2.setClassName(CLASSNAME + "-overlay");
		Event.sinkEvents(overlayElement2, Event.ONCLICK | Event.TOUCHEVENTS);
		Event.setEventListener(overlayElement2, overlayListener);
		style = overlayElement2.getStyle();
		style.setBackgroundColor("transparent");
		style.setPosition(Position.ABSOLUTE);
		style.setTop(0, Unit.PX);
		style.setLeft(0, Unit.PX);
		style.setWidth(100, Unit.PCT);
		style.setHeight(100, Unit.PCT);
		style.setZIndex(1);
		style.setDisplay(Display.NONE);
	}

	private void showOverlays() {
		Style style = overlayElement1.getStyle();
		style.clearDisplay();

		style = overlayElement2.getStyle();
		style.clearDisplay();
	}

	private void hideOverlays() {
		Style style = overlayElement1.getStyle();
		style.setDisplay(Display.NONE);

		style = overlayElement2.getStyle();
		style.setDisplay(Display.NONE);
	}

	@Override
	protected com.google.gwt.user.client.Element getContainerElement() {
		return DOM.asOld(containerElement);
	}

	public void updateButtonCaption(String caption) {
		this.getElement().setInnerText(caption);
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		if (isAttached()) {
			parent = getParent();
			while (parent != null && !(parent instanceof VNavigationView)) {
				parent = parent.getParent();
			}
			if (parent == null)
				return;
			parent.getElement().appendChild(overlayElement1);
			getParent().getElement().appendChild(overlayElement2);
			parent.getElement().appendChild(containerElement);
			Style style = containerElement.getStyle();
			style.setOpacity(0);
			Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {

				@Override
				public void execute() {
					calculatePosition();
				}
			});
		} else {
			parent.getElement().removeChild(overlayElement1);
			getParent().getElement().removeChild(overlayElement2);
			parent.getElement().removeChild(containerElement);
			parent = null;
		}
	}

	@Override
	public void setWidget(final Widget w) {
		Style style = containerElement.getStyle();
		style.setOpacity(0);
		expanded = false;
		this.removeStyleName(EXPANDED);

		super.setWidget(w);

		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {

			@Override
			public void execute() {
				calculatePosition();
			}
		});
	}

	private void calculatePosition() {
		final Style style = containerElement.getStyle();
		if (getParent() == null)
			return;
		if (containerElement.getOffsetHeight() != 0) {
			style.setTop(getParent().getOffsetHeight(), Unit.PX);
			style.setProperty(Css3Propertynames.transition(), "none");
			toggleMenu(false);
			Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {

				@Override
				public void execute() {
					style.clearOpacity();
					style.setProperty(Css3Propertynames.transition(), "");
				}
			});
		} else {
			style.setTop(0, Unit.PX);
		}
	}

	@Override
	public void onTouchStart(TouchStartEvent event) {
		touchStarted = true;
		fastClickAt = null;
		getElement().focus();
	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		if (touchStarted) {
			event.preventDefault();
			event.stopPropagation();
			NativeEvent nativeEvent = event.getNativeEvent();
			NativeEvent evt = Document.get().createClickEvent(1,
					nativeEvent.getScreenX(), nativeEvent.getScreenY(),
					nativeEvent.getClientX(), nativeEvent.getClientY(), false,
					false, false, false);
			getElement().dispatchEvent(evt);
			touchStarted = false;
			fastClickAt = new Date();
		}
	}

	@Override
	public void onTouchMove(TouchMoveEvent event) {
		touchStarted = false;
	}

	@Override
	public void onTouchCancel(TouchCancelEvent event) {
		touchStarted = false;
	}

	@Override
	public void onClick(ClickEvent event) {
		if (enabled) {
			if (fastClickAt != null
					&& (new Date().getTime() - fastClickAt.getTime()) < IGNORE_SIMULATED_CLICKS_THRESHOLD) {
				fastClickAt = null;
				return;
			}
			// getElement().focus();
			toggleMenu();
		}
	}

	private void toggleMenu() {
		toggleMenu(!this.expanded);
	}

	private void toggleMenu(boolean value) {
		this.expanded = value;
		final Style style = containerElement.getStyle();
		if (this.expanded) {
			Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {

				@Override
				public void execute() {
					VNavigationBarQuickMenu.this.addStyleName(EXPANDED);
				}
			});

			style.setOpacity(1);
			style.setProperty(Css3Propertynames.transform(),
					"translate3d(0, 0, 0)");

			showOverlays();
		} else {
			Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {

				@Override
				public void execute() {
					VNavigationBarQuickMenu.this.removeStyleName(EXPANDED);
				}
			});

			style.setProperty(Css3Propertynames.transform(), "translate3d(0, -"
					+ containerElement.getOffsetHeight() + "px, 0)");

			// Workaround: Exposing menu while navigating between views in
			// Safari
			Timer timer = new Timer() {
				@Override
				public void run() {
					style.setOpacity(0);
				}
			};
			timer.schedule(250);

			hideOverlays();
		}
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		if (!this.enabled) {
			this.addStyleName("disabled");
		} else {
			this.removeStyleName("disabled");
		}
	}

	public boolean isEnabled() {
		return enabled;
	}

}