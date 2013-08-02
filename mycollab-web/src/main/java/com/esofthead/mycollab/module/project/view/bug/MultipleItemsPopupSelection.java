package com.esofthead.mycollab.module.project.view.bug;

import org.vaadin.hene.popupbutton.PopupButton;

import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class MultipleItemsPopupSelection extends PopupButton {

    private Panel panel;

    public MultipleItemsPopupSelection() {
        panel = new Panel();
        panel.setStyleName(Reindeer.PANEL_LIGHT);
        panel.setHeight("200px");
        panel.setWidth("300px");
        this.addComponent(panel);

    }

    public void addItemComponent(Component itemName) {
        panel.addComponent(itemName);
    }
}
