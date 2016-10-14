package com.mycollab.mobile.module.project.ui;

import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.server.FontAwesome;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public abstract class SearchNavigationButton extends NavigationButton {
    public SearchNavigationButton() {
        super();
        setIcon(FontAwesome.SEARCH);
        this.addClickListener(navigationButtonClickEvent -> getNavigationManager().navigateTo(getSearchInputView()));
    }

    abstract protected SearchInputView getSearchInputView();
}
