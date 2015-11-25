package com.esofthead.vaadin.mobilecomponent.client;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HumanInputEvent;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.touchkit.gwt.client.ui.VNavigationView;
import com.vaadin.client.BrowserInfo;
import com.vaadin.client.Util;

@SuppressWarnings("rawtypes")
public class VMobileNavigationView extends VNavigationView {

	private static Logger log = Logger.getLogger(VMobileNavigationView.class
			.getName());

	private final VDrawerButton toggleNavBtn;
	private VMobileNavigationManager viewNavigationManager;

	private static final double SPEED_THRESHOLD = 0.35;
	private int dragstartX;
	private int dragstartY;
	private boolean dragging;
	private boolean swiping;
	private long lastTs;
	private int lastX;
	private double lastSpeed;
	private boolean touchDrag;
	private final Element scrollElement;
	protected TouchStartEvent dragStartEvent;

	// private final TouchScrollDelegate touchScrollDelegate;

	public VMobileNavigationView() {
		super();

		addStyleName("mobilenavview");
		getElement().getStyle().setProperty("webkitUserSelect", "none");
		getElement().getStyle().setProperty("MozUserSelect", "none");
		getElement().getStyle().setProperty("MsUserSelect", "none");

		toggleNavBtn = GWT.create(VDrawerButton.class);

		scrollElement = getElement();
		Style style = scrollElement.getStyle();
		style.setOverflow(Overflow.AUTO);
		style.setHeight(100, Unit.PCT);
		style.setPosition(Position.ABSOLUTE);
		DOM.sinkEvents(scrollElement, Event.ONSCROLL);
		// touchScrollDelegate = new TouchScrollDelegate(scrollElement);
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		Widget parent = getParent();
		while (parent != null) {
			if (parent instanceof VMobileNavigationManager) {
				viewNavigationManager = (VMobileNavigationManager) parent;
				break;
			}
			parent = parent.getParent();
		}
	}

	public VMobileNavigationBar getNavigationBar() {
		if (getWidget(0) instanceof VMobileNavigationBar) {
			return (VMobileNavigationBar) getWidget(0);
		}
		return null;
	}

	public void addToggleButton() {
		if (getNavigationBar() != null) {
			getNavigationBar().setLeftWidget(toggleNavBtn);
		}

	}

	public void removeToggleButton() {
		if (getNavigationBar() != null
				&& getNavigationBar().hasChildComponent(toggleNavBtn)) {
			getNavigationBar().setLeftWidget(null);
		}
	}

	@Override
	public void setContent(Widget child) {
		super.setContent(child);

		child.sinkEvents(Event.MOUSEEVENTS);
		child.sinkEvents(Event.TOUCHEVENTS);
		initHandlers(child);
	}

	protected void initHandlers(Widget content) {
		toggleNavBtn.addTouchStartHandler(new TouchStartHandler() {

			@Override
			public void onTouchStart(TouchStartEvent event) {
				event.preventDefault();
				event.stopPropagation();
				if (viewNavigationManager != null) {
					viewNavigationManager.toggleMenu();
				}
			}
		});

		content.addHandler(new TouchStartHandler() {
			@Override
			public void onTouchStart(TouchStartEvent event) {
				dragStartEvent = event;
				dragStart(event);
			}
		}, TouchStartEvent.getType());

		content.addHandler(new MouseDownHandler() {

			@Override
			public void onMouseDown(MouseDownEvent event) {
				if (event.getNativeButton() == NativeEvent.BUTTON_LEFT) {
					dragStart(event);
				}
			}
		}, MouseDownEvent.getType());

		content.addHandler(new MouseMoveHandler() {

			@Override
			public void onMouseMove(MouseMoveEvent event) {
				dragMove(event);
			}
		}, MouseMoveEvent.getType());

		content.addHandler(new TouchMoveHandler() {

			@Override
			public void onTouchMove(TouchMoveEvent event) {
				dragMove(event);
			}
		}, TouchMoveEvent.getType());

		content.addHandler(new MouseUpHandler() {

			@Override
			public void onMouseUp(MouseUpEvent event) {
				dragEnd(event);
			}
		}, MouseUpEvent.getType());

		content.addHandler(new TouchEndHandler() {

			@Override
			public void onTouchEnd(TouchEndEvent event) {
				dragEnd(event);
			}
		}, TouchEndEvent.getType());

	}

	protected void dragStart(HumanInputEvent event) {
		NativeEvent ne = event.getNativeEvent();
		log.log(Level.INFO, "Drag start" + ne.getType());
		if (!dragging && viewNavigationManager != null) {
			dragging = true;
			touchDrag = Event.as(ne).getTypeInt() == Event.ONTOUCHSTART;
			dragstartX = Util.getTouchOrMouseClientX(ne);
			if (dragstartX < 10 && !viewNavigationManager.getMenuVisibility()) {
				dragging = false;
				ne.preventDefault();
				return;
			}
			dragstartY = Util.getTouchOrMouseClientY(ne);
			if (!BrowserInfo.get().isTouchDevice()) {
				// avoid drag start on images
				// FIXME shouln't be this way, but disables dragstart on images
				// in demo with desktop browsers and this way makes development
				// easier
				Element el = ne.getEventTarget().cast();
				String msg = el.getParentElement().getClassName();
				if (msg.contains("embedded")) {
					ne.preventDefault();
				}
			}
			new Timer() {
				@Override
				public void run() {
					// Swipe must start soon or drag start will be ignored
					if (!swiping) {
						dragging = false;
					}
				}
			}.schedule(200);
			;
		}
	}

	protected void dragMove(HumanInputEvent event) {
		if (viewNavigationManager != null) {
			NativeEvent ne = event.getNativeEvent();
			if (touchDrag && Event.as(ne).getTypeInt() != Event.ONTOUCHMOVE) {
				return;
			}
			int x = Util.getTouchOrMouseClientX(ne);
			int y = Util.getTouchOrMouseClientY(ne);

			long time = new Date().getTime();
			// screens per second
			double screenwidths = (x - lastX) / (double) getOffsetWidth();
			double seconds = (time - lastTs) / 100d;
			lastSpeed = screenwidths / seconds;
			lastX = x;
			lastTs = time;
			int deltaX = x - dragstartX;
			if (deltaX > 0 && viewNavigationManager.getMenuVisibility())
				return;
			if (swiping) {
				viewNavigationManager.setHorizontalOffsetExt(deltaX, false);
				ne.preventDefault(); // prevent page scroll
			} else if (dragging) {
				Event.setCapture(getContent().getElement());
				int dragY = dragstartY - y;

				if (Math.abs(deltaX / (double) dragY) > 2) {
					swiping = true;
					viewNavigationManager.setHorizontalOffsetExt(deltaX, false);
					ne.preventDefault(); // prevent page scroll
				}

				// if (BrowserInfo.get().requiresTouchScrollDelegate()) {
				// if (Math.abs(deltaX / (double) dragY) < 0.5) {
				// if (Event.as(event.getNativeEvent()).getTypeInt() ==
				// Event.ONTOUCHMOVE) {
				//
				// // We'll "lazyly" activate touchScrollDelegate if
				// // the direction
				// // is enough down.
				//
				// dragStartEvent.setNativeEvent(event
				// .getNativeEvent()); //
				// touchScrollDelegate.onTouchStart(dragStartEvent);
				// dragging = false;
				// }
				// }
				// }

			}
		}
	}

	protected void dragEnd(HumanInputEvent event) {
		if (dragging) {
			Event.releaseCapture(getContent().getElement());
			dragging = false;
			if (swiping) {
				if (viewNavigationManager != null) {
					NativeEvent ne = event.getNativeEvent();
					int x = Util.getTouchOrMouseClientX(ne);
					int deltaX = x - dragstartX;
					if (deltaX < viewNavigationManager.getNavigationMenuWidth() / 2
							|| lastSpeed < -SPEED_THRESHOLD) {
						// navigate backward
						viewNavigationManager.toggleMenu(false);
					} else if (deltaX >= viewNavigationManager
							.getNavigationMenuWidth() / 2
							|| lastSpeed > SPEED_THRESHOLD) {
						// navigate forward
						viewNavigationManager.toggleMenu(true);
					} else {
						viewNavigationManager.toggleMenu(false);
					}
				}
				swiping = false;
			}
		}
	}

}