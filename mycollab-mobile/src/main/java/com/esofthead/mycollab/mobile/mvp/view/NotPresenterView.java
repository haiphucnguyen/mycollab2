package com.esofthead.mycollab.mobile.mvp.view;

import com.esofthead.mycollab.mobile.ui.AbstractMobilePageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.Label;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
@ViewComponent
public class NotPresenterView extends AbstractMobilePageView {
    void display() {
        setContent(new MVerticalLayout().with(new Label("A")));
    }
}
