package com.mycollab.module.project.view.settings;

import com.mycollab.vaadin.mvp.PageView;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface UserSettingView extends PageView {
    Component gotoSubView(String name);
}
