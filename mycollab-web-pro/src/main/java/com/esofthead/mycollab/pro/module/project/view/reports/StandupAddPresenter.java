package com.esofthead.mycollab.pro.module.project.view.reports;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.StandupReportWithBLOBs;
import com.esofthead.mycollab.module.project.events.StandUpEvent;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.module.project.view.reports.IReportContainer;
import com.esofthead.mycollab.pro.module.project.view.ReportBreadcrumb;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.IEditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.LoadPolicy;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewScope;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

import java.util.GregorianCalendar;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
public class StandupAddPresenter extends AbstractPresenter<StandupAddView> {
    private static final long serialVersionUID = 1L;

    public StandupAddPresenter() {
        super(StandupAddView.class);
    }

    @Override
    protected void postInitView() {
        view.getEditFormHandlers().addFormHandler(new IEditFormHandler<StandupReportWithBLOBs>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSave(StandupReportWithBLOBs standupReport) {
                saveStandupReport(standupReport);
                EventBusFactory.getInstance().post(new StandUpEvent.GotoList(this, null));
            }

            @Override
            public void onCancel() {
                EventBusFactory.getInstance().post(new StandUpEvent.GotoList(this, null));
            }

            @Override
            public void onSaveAndNew(StandupReportWithBLOBs standupReport) {
                throw new MyCollabException("Do not support this feature");
            }
        });
    }

    public void saveStandupReport(StandupReportWithBLOBs standupReport) {
        StandupReportService standupReportService = ApplicationContextUtil.getSpringBean(StandupReportService.class);
        standupReport.setProjectid(CurrentProjectVariables.getProjectId());
        standupReport.setSaccountid(AppContext.getAccountId());
        standupReport.setForday(new GregorianCalendar().getTime());
        standupReport.setLogby(AppContext.getUsername());

        if (standupReport.getId() == null) {
            standupReportService.saveWithSession(standupReport, AppContext.getUsername());
        } else {
            standupReportService.updateWithSession(standupReport, AppContext.getUsername());
        }
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        IReportContainer reportContainer = (IReportContainer) container;
        reportContainer.addView(view);
        StandupReportWithBLOBs standupReport = (StandupReportWithBLOBs) data.getParams();
        view.editItem(standupReport);

        ReportBreadcrumb breadCrumb = ViewManager.getCacheComponent(ReportBreadcrumb.class);
        breadCrumb.gotoStandupAdd(new GregorianCalendar().getTime());
    }
}
