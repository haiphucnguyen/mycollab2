package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.PageView;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;

/**
 * @author MyCollab Ltd
 * @since 5.0.4
 */
public abstract class ProjectGenericPresenter<V extends PageView> extends AbstractPresenter<V> {
    public ProjectGenericPresenter(Class<V> viewClass) {
        super(viewClass);
    }
}
