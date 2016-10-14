package com.mycollab.mobile.module.project.ui;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.mobile.ui.AbstractMobilePageView;
import com.mycollab.mobile.ui.IListView;
import com.mycollab.vaadin.UserUIContext;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.ui.Component;
import org.vaadin.viritin.button.MButton;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public abstract class SearchInputView<S extends SearchCriteria> extends AbstractMobilePageView {
    private NavigationManager.NavigationListener listener;

    public SearchInputView() {
        setCaption(UserUIContext.getMessage(GenericI18Enum.BUTTON_SEARCH));
    }

    @Override
    protected void onBecomingVisible() {
        listener = (NavigationManager.NavigationListener) navigationEvent -> {
            if (navigationEvent.getDirection() == NavigationManager.NavigationEvent.Direction.BACK) {
                Component currentComponent = getNavigationManager().getCurrentComponent();
                if (currentComponent instanceof IListView) {
                    S criteria = fillSearchCriteria();
                    ((IListView) currentComponent).getPagedBeanTable().search(criteria);
                }
            }
        };
        getNavigationManager().addNavigationListener(listener);
        setRightComponent(new MButton(UserUIContext.getMessage(GenericI18Enum.ACTION_DONE), clickEvent ->
                getNavigationManager().navigateBack()));
    }

    abstract protected S buildSearchCriteria();

    private S fillSearchCriteria() {
        S criteria = buildSearchCriteria();
        getNavigationManager().removeListener(listener);
        return criteria;
    }
}
