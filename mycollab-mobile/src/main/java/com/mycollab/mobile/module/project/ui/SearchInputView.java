package com.mycollab.mobile.module.project.ui;

import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.mobile.ui.AbstractMobilePageView;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.ui.Component;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public abstract class SearchInputView<S extends SearchCriteria> extends AbstractMobilePageView {
    private NavigationManager.NavigationListener listener;

    @Override
    protected void onBecomingVisible() {
        listener = (NavigationManager.NavigationListener) navigationEvent -> {
            if (navigationEvent.getDirection() == NavigationManager.NavigationEvent.Direction.BACK) {
                Component currentComponent = getNavigationManager().getCurrentComponent();
                fillSearchCriteria();
            }
        };
        getNavigationManager().addNavigationListener(listener);
    }

    abstract protected S buildSearchCriteria();

    private S fillSearchCriteria() {
        S criteria = buildSearchCriteria();
        getNavigationManager().removeListener(listener);
        return criteria;
    }
}
