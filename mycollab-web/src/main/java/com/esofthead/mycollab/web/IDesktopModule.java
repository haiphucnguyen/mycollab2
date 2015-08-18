package com.esofthead.mycollab.web;

import com.esofthead.mycollab.vaadin.mvp.IModule;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
public interface IDesktopModule extends IModule {
    MHorizontalLayout buildMenu();
}
