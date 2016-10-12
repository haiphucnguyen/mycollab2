package com.mycollab.vaadin.touchkit.client.ui;

import com.mycollab.vaadin.touchkit.NavigationMenuButton;
import com.mycollab.vaadin.touchkit.client.VMobileNavigationManager;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;

@Connect(NavigationMenuButton.class)
public class NavigationMenuButtonConnector extends AbstractExtensionConnector {
	private static final long serialVersionUID = -5864361196690770202L;

	@Override
	protected void extend(ServerConnector target) {
		final Widget w = ((ComponentConnector) target).getWidget();
		w.addDomHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (!w.getElement().equals(
						Element.as(event.getNativeEvent().getEventTarget())))
					return;

				Widget parent = w.getParent();
				while (parent != null) {
					if (parent instanceof VMobileNavigationManager) {
						((VMobileNavigationManager) parent).toggleMenu(false);
						break;
					}
					parent = parent.getParent();
				}
			}
		}, ClickEvent.getType());
	}

}
