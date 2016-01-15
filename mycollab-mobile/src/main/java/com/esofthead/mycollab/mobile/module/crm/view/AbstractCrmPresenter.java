package com.esofthead.mycollab.mobile.module.crm.view;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.mobile.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PageView;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
public class AbstractCrmPresenter<V extends PageView> extends AbstractPresenter<V> {
    public AbstractCrmPresenter(Class<V> viewClass) {
        super(viewClass);
    }

    @Override
    protected void onGo(ComponentContainer navigator, ScreenData<?> data) {
        if (navigator instanceof NavigationManager) {
            ((NavigationManager) navigator).navigateTo(view.getWidget());
        } else {
            throw new MyCollabException("Invalid flow");
        }
    }
}
