package com.mycollab.vaadin.touchkit.client.shared;

import com.vaadin.addon.touchkit.gwt.client.vcom.navigation.NavigationManagerSharedState;
import com.vaadin.shared.Connector;

public class MobileNavigationManagerState extends NavigationManagerSharedState {

    private static final long serialVersionUID = -1996222963005761969L;

    public Connector navigationMenu;
    public boolean showMenu = false;

}