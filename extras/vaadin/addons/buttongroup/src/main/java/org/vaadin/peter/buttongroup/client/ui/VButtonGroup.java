package org.vaadin.peter.buttongroup.client.ui;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Container;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.RenderSpace;
import com.vaadin.terminal.gwt.client.UIDL;
import com.vaadin.terminal.gwt.client.ui.VButton;

/**
 * VButtonGroup is client side implementation of the ButtonGroup widget.
 * 
 * @author Peter Lehto / Vaadin Oy
 */
public class VButtonGroup extends HorizontalPanel implements Paintable,
		Container {

	protected String paintableId;
	protected ApplicationConnection client;
	private final List<VButton> buttons;

	public VButtonGroup() {
		setStyleName("v-buttongroup");

		buttons = new LinkedList<VButton>();
	}

	public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
		if (client.updateComponent(this, uidl, true)) {
			return;
		}

		this.client = client;
		this.paintableId = uidl.getId();

		UIDL buttonsUIDL = uidl.getChildByTagName("buttons");
		Iterator<Object> childIterator = buttonsUIDL.getChildIterator();

		checkRemovedButtons(buttonsUIDL.getChildIterator());

		while (childIterator.hasNext()) {
			UIDL child = (UIDL) childIterator.next();

			VButton button = (VButton) client.getPaintable(child);
			button.updateFromUIDL(child, client);

			add(button);
			buttons.add(button);
		}
	}

	private void checkRemovedButtons(Iterator<Object> childIterator) {
		List<VButton> buttonsToRemove = new LinkedList<VButton>(this.buttons);

		while (childIterator.hasNext()) {
			UIDL child = (UIDL) childIterator.next();
			VButton button = (VButton) client.getPaintable(child);
			buttonsToRemove.remove(button);
		}

		for (VButton button : buttonsToRemove) {
			remove(button);
		}
	}

	public void replaceChildComponent(Widget oldComponent, Widget newComponent) {

	}

	public boolean hasChildComponent(Widget component) {
		return component.getParent() == this;
	}

	public void updateCaption(Paintable component, UIDL uidl) {

	}

	public boolean requestLayout(Set<Paintable> children) {
		return false;
	}

	public RenderSpace getAllocatedSpace(Widget child) {
		return null;
	}
}
