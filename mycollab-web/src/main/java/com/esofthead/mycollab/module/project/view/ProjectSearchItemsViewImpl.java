package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
@ViewComponent
public class ProjectSearchItemsViewImpl extends AbstractPageView implements ProjectSearchItemsView {
    @Override
    public void displayResults(String value) {
        this.addComponent(new Label("Helo"));
    }
}
