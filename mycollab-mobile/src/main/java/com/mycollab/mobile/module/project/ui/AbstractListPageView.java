package com.mycollab.mobile.module.project.ui;

import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.mobile.ui.AbstractPagedBeanList;
import com.mycollab.mobile.ui.IListView;
import com.mycollab.mobile.ui.SearchInputField;
import com.mycollab.vaadin.events.HasSearchHandlers;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.4.0
 */
public abstract class AbstractListPageView<S extends SearchCriteria, B> extends ProjectMobileMenuPageView implements IListView<S, B> {
    private static final long serialVersionUID = 3603608419228750094L;

    protected AbstractPagedBeanList<S, B> itemList;

    public AbstractListPageView() {
        itemList = createBeanList();
        setContent(itemList);
    }

    @Override
    public AbstractPagedBeanList<S, B> getPagedBeanTable() {
        return itemList;
    }

    protected void doSearch() {
        if (getPagedBeanTable().getSearchRequest() != null) {
            getPagedBeanTable().refresh();
        }
    }

    @Override
    public void onBecomingVisible() {
        super.onBecomingVisible();
        doSearch();

        Component rightComponent = buildRightComponent();
        if (rightComponent != null) {
            setRightComponent(rightComponent);
        }

        Component toolbar = buildToolbar();
        if (toolbar != null) {
            setToolbar(toolbar);
        }
    }

    abstract protected AbstractPagedBeanList<S, B> createBeanList();

    abstract protected SearchInputField<S> createSearchField();

    protected Component buildRightComponent() {
        return null;
    }

    protected Component buildToolbar() {
        return null;
    }
}
