package com.esofthead.mycollab.vaadin.ui.grid;

import com.esofthead.mycollab.vaadin.ui.MultiSelectComp;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.0.8
 */
class GridCellWrapper extends HorizontalLayout {
    private static final long serialVersionUID = 1L;

    public GridCellWrapper() {
        this.setStyleName("gridform-field");
        this.setMargin(true);
        this.setWidth("100%");
    }

    public void addComponent(Component component) {
        if (!(component instanceof Button))
            component.setCaption(null);

        if (component instanceof MultiSelectComp) {
            component.setWidth("200px");
        } else {
            component.setWidth("100%");
        }
        super.addComponent(component);
    }
}
