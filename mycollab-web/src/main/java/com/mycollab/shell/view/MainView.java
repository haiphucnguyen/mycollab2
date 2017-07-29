package com.mycollab.shell.view;

import com.mycollab.vaadin.mvp.PageView;
import com.mycollab.web.IDesktopModule;

/**
 * @author MyCollab Ltd
 * @since 5.0.8
 */
public interface MainView extends PageView {
    void display();

    void addModule(IDesktopModule module);
}
