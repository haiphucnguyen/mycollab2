package com.esofthead.vaadin.mobilecomponent;

import com.esofthead.vaadin.mobilecomponent.client.shared.MobileNavigationManagerState;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.ui.Component;

import java.util.ArrayList;
import java.util.Iterator;

public class MobileNavigationManager extends NavigationManager {

	private static final long serialVersionUID = -7035128774991015577L;
	
	private Component navMenu;

    public MobileNavigationManager() {
        super();
    }

    public void setNavigationMenu(Component navMenu) {
        addComponent(navMenu);
        this.navMenu = navMenu;
        getState().navigationMenu = navMenu;
        markAsDirty();
    }

    public Component getNavigationMenu() {
        return this.navMenu;
    }

    @Override
    public MobileNavigationManagerState getState() {
        return (MobileNavigationManagerState) super.getState();
    }

    @Override
    public int getComponentCount() {
        return super.getComponentCount() + 1;
    }

    @Override
    public Iterator<Component> getComponentIterator() {
        Iterator<Component> iterator = super.getComponentIterator();
        ArrayList<Component> newCompList = new ArrayList<Component>();
        if (navMenu != null)
            newCompList.add(navMenu);

        while (iterator.hasNext()) {
            newCompList.add(iterator.next());
        }
        return newCompList.iterator();
    }
}
