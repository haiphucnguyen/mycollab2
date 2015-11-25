package com.esofthead.vaadin.floatingcomponent;

import com.esofthead.vaadin.floatingcomponent.client.FloatingComponentState;
import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.AbstractComponent;

/**
 * @author MyCollab Ltd.
 *
 */
public class FloatingComponent extends AbstractExtension {
	private static final long serialVersionUID = -8223318682178724199L;

	protected FloatingComponent(AbstractComponent c) {
		extend(c);
	}

	public static FloatingComponent floatThis(AbstractComponent c) {
		return new FloatingComponent(c);
	}

	public void setContainerId(String id) {
		getState().containerId = id;
	}

	@Override
	protected FloatingComponentState getState() {
		return (FloatingComponentState) super.getState();
	}

}
