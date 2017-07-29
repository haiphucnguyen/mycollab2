package com.mycollab.web;

import com.mycollab.vaadin.mvp.IModule;
import com.vaadin.ui.SingleComponentContainer;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
public interface IDesktopModule extends IModule, SingleComponentContainer {
    MHorizontalLayout buildMenu();
}
