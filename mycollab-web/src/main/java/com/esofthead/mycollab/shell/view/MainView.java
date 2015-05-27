package com.esofthead.mycollab.shell.view;

import com.esofthead.mycollab.vaadin.mvp.IModule;
import com.esofthead.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
public interface MainView extends PageView {
    void display();

    void addModule(IModule module);
}
