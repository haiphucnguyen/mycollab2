package com.esofthead.mycollab.mobile.ui;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.mobile.MobileApplication;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.PageView;
import com.esofthead.mycollab.vaadin.mvp.ViewEvent;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

import java.io.Serializable;

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
public abstract class AbstractMobilePageView extends NavigationView implements PageView, Serializable {
    private static final long serialVersionUID = 1L;

    public AbstractMobilePageView() {
        super();
        if (this.getLeftComponent() != null && this.getLeftComponent() instanceof NavigationButton) {
            this.getLeftComponent().setCaption(AppContext.getMessage(GenericI18Enum.M_BUTTON_BACK));
        }
    }

    @Override
    public ComponentContainer getWidget() {
        return this;
    }

    @Override
    public void addViewListener(ViewListener listener) {
        addListener(ViewEvent.VIEW_IDENTIFIER(), ViewEvent.class, listener, ViewListener.viewInitMethod);
    }

    @Override
    public NavigationManager getNavigationManager() {
        UI ui = UI.getCurrent();
        if (ui instanceof MobileApplication) {
            return (NavigationManager) (ui.getContent());
        }
        return null;
    }
}
