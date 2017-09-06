package com.mycollab.pro.module.project.view;

import com.mycollab.module.project.view.SettingView;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd
 * @since 5.2.4
 */
@ViewComponent
public class SettingViewImpl extends AbstractVerticalPageView implements SettingView {
    @Override
    public void display() {
        removeAllComponents();
        this.addComponent(new Label("AAA"));
    }
}
