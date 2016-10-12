package com.mycollab.vaadin.touchkit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mycollab.vaadin.touchkit.client.shared.MobileViewToolbarState;
import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.Component;

public class MobileViewToolbar extends AbstractComponentContainer {
	private static final long serialVersionUID = 9058695350522095481L;

	private Component onlyChild;

	public MobileViewToolbar() {
		setSizeFull();
	}

	@Override
	public MobileViewToolbarState getState() {
		return (MobileViewToolbarState) super.getState();
	}

	@Override
	public void addComponent(Component child) {
		setComponent(child);
	}

	public void setComponent(Component child) {
		if (onlyChild != null)
			super.removeComponent(onlyChild);
		onlyChild = child;
		super.addComponent(onlyChild);
	}

	@Override
	public void replaceComponent(Component oldComponent, Component newComponent) {
		throw new UnsupportedOperationException("This component have only one child component, use replaceChild to replace.");
	}

	public void replaceChild(Component newComponent) {
		if (newComponent == onlyChild)
			return;

		setComponent(newComponent);		
	}

	@Override
	public int getComponentCount() {
		return (onlyChild != null) ? 1 : 0;
	}

	@Override
	public Iterator<Component> iterator() {
		List<Component> childList = new ArrayList<Component>();
		if(onlyChild != null)
			childList.add(onlyChild);

		return childList.iterator();
	}

}
