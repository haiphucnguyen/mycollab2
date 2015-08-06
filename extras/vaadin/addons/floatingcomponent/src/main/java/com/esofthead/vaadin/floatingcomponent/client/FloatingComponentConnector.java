package com.esofthead.vaadin.floatingcomponent.client;

import com.esofthead.vaadin.floatingcomponent.FloatingComponent;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;

/**
 * @author MyCollab Ltd.
 */

@Connect(FloatingComponent.class)
public class FloatingComponentConnector extends AbstractExtensionConnector implements ScrollHandler {
    private static final long serialVersionUID = -5478923080638016956L;

    private int originalOffset;

    private int containerOffset;

    private Widget container;

    private HandlerRegistration handlerRegistration;

    private Widget targetWidget;

    private Style currentStyle;

    @Override
    protected void extend(ServerConnector target) {
        targetWidget = ((ComponentConnector) target).getWidget();
        targetWidget.addAttachHandler(new AttachEvent.Handler() {

            @Override
            public void onAttachOrDetach(AttachEvent event) {
                if (!event.isAttached() && handlerRegistration != null) {
                    handlerRegistration.removeHandler();
                }
            }
        });
        currentStyle = targetWidget.getElement().getStyle();
    }

    private Widget getWidget(Element el) {
        EventListener listener = DOM.getEventListener(el);

        if (listener == null) {
            return null;
        }

        if (listener instanceof Widget)
            return (Widget) listener;

        return null;
    }

    @Override
    public void onScroll(ScrollEvent event) {
        if (container.getElement().getScrollTop() > originalOffset) {
            currentStyle.setPosition(Position.FIXED);
            currentStyle.setTop(containerOffset, Unit.PX);
        } else {
            currentStyle.setPosition(Position.RELATIVE);
            currentStyle.clearTop();
        }
    }

    @Override
    public FloatingComponentState getState() {
        return (FloatingComponentState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        if (stateChangeEvent.hasPropertyChanged("containerId")) {
            if (getState().containerId != null)
                container = getWidget(DOM.getElementById(getState().containerId));
            else
                container = RootPanel.get();

            if (handlerRegistration != null)
                handlerRegistration.removeHandler();

            handlerRegistration = (container != null ? container : RootPanel
                    .get()).addDomHandler(FloatingComponentConnector.this,
                    ScrollEvent.getType());
        }

        containerOffset = container.getElement().getAbsoluteTop();

        originalOffset = getAbsoluteTopFromContainer(targetWidget.getElement(),
                container.getElement());
    }

    private native int getAbsoluteTopFromContainer(Element target,
                                                   Element container) /*-{
        var top = 0;
		while (target != container && target != null) {
			top += target.offsetTop;
			target = target.offsetParent;
		}
		return top | 0;
	}-*/;
}
