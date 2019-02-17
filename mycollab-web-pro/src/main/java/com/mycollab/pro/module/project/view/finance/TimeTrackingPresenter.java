package com.mycollab.pro.module.project.view.finance;

import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.view.ProjectBreadcrumb;
import com.mycollab.module.project.view.ProjectView;
import com.mycollab.module.project.view.finance.ITimeTrackingContainer;
import com.mycollab.module.project.view.finance.ITimeTrackingPresenter;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.mycollab.vaadin.web.ui.TabSheetDecorator;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd
 * @since 2.0
 */
public class TimeTrackingPresenter extends AbstractPresenter<ITimeTrackingContainer> implements ITimeTrackingPresenter {
    private static final long serialVersionUID = 1L;

    public TimeTrackingPresenter() {
        super(ITimeTrackingContainer.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        ProjectView projectView = (ProjectView) container;
        projectView.gotoSubView(ProjectView.TIME_TRACKING_ENTRY, view);
        view.setSearchCriteria((ItemTimeLoggingSearchCriteria) data.getParams());

        ProjectBreadcrumb breadCrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
        breadCrumb.gotoTimeTrackingList();
    }
}