package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class Hr extends Label {
    private static final long serialVersionUID = 1L;

    public Hr() {
        super("<hr/>", ContentMode.HTML);
        this.addStyleName("hr");
    }
}
