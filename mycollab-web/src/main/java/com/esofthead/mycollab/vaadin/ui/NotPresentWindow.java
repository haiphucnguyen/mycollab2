package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Window;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
@ViewComponent
public class NotPresentWindow extends Window {
    public NotPresentWindow() {
        super("We are sorry");
        this.setModal(true);
        this.setResizable(false);

        Component content = new NotPresentedView().withMargin(true);
        this.setContent(content);
    }
}
