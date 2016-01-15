package com.esofthead.mycollab.mobile.module.project.view;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.mobile.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PageView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
public abstract class AbstractProjectPresenter<V extends PageView> extends AbstractPresenter<V> {
    public AbstractProjectPresenter(Class<V> viewClass) {
        super(viewClass);
    }

    @Override
    protected void onGo(ComponentContainer navigator, ScreenData<?> data) {
        if (navigator instanceof NavigationManager) {
            NavigationManager navManager = ((NavigationManager) navigator);
            navManager.navigateTo(view.getWidget());
            if (navManager.getPreviousComponent() == null) {
                UserProjectListPresenter projectListPresenter = PresenterResolver.getPresenter(UserProjectListPresenter.class);
                navManager.setPreviousComponent(projectListPresenter.getView());
            }
        } else {
            throw new MyCollabException("Invalid flow");
        }
    }
}
