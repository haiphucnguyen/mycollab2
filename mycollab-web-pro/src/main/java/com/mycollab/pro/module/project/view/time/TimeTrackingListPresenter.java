package com.mycollab.pro.module.project.view.time;

import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.view.ProjectBreadcrumb;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.mycollab.vaadin.web.ui.TabSheetDecorator;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 2.0
 */
public class TimeTrackingListPresenter extends AbstractPresenter<TimeTrackingListView> {
    private static final long serialVersionUID = 1L;

    public TimeTrackingListPresenter() {
        super(TimeTrackingListView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        FinanceContainer timeContainer = (FinanceContainer) container;
        TabSheetDecorator.WrappedTab wrappedTab = (TabSheetDecorator.WrappedTab)timeContainer.gotoSubView(AppContext.getMessage(ProjectCommonI18nEnum.VIEW_TIME));
        wrappedTab.addView(view);
        view.setSearchCriteria((ItemTimeLoggingSearchCriteria) data.getParams());

        ProjectBreadcrumb breadCrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
        breadCrumb.gotoTimeTrackingList();
    }
}