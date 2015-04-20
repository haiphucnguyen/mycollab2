package com.esofthead.mycollab.premium.module.project.view.standup;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.StandupReportWithBLOBs;
import com.esofthead.mycollab.module.project.events.StandUpEvent;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.*;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
public class StandupAddPresenter extends AbstractPresenter<StandupAddView> {
	private static final long serialVersionUID = 1L;

	public StandupAddPresenter() {
		super(StandupAddView.class);
	}

	@Override
	protected void postInitView() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<StandupReportWithBLOBs>() {
					private static final long serialVersionUID = 1L;

					@Override
					public void onSave(
							final StandupReportWithBLOBs standupReport) {
						saveStandupReport(standupReport);
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBusFactory.getInstance().post(
									new StandUpEvent.GotoList(this, null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBusFactory.getInstance().post(
									new StandUpEvent.GotoList(this, null));
						}
					}

					@Override
					public void onSaveAndNew(
							final StandupReportWithBLOBs standupReport) {
						throw new MyCollabException(
								"Do not support this feature");
					}
				});
	}

	public void saveStandupReport(StandupReportWithBLOBs standupReport) {
		StandupReportService standupReportService = ApplicationContextUtil
				.getSpringBean(StandupReportService.class);
		standupReport.setProjectid(CurrentProjectVariables.getProjectId());
		standupReport.setSaccountid(AppContext.getAccountId());
		standupReport.setForday(new GregorianCalendar().getTime());
		standupReport.setLogby(AppContext.getUsername());

		if (standupReport.getId() == null) {
			standupReportService.saveWithSession(standupReport,
					AppContext.getUsername());
		} else {
			standupReportService.updateWithSession(standupReport,
					AppContext.getUsername());
		}

	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		StandupContainer standupContainer = (StandupContainer) container;
		standupContainer.removeAllComponents();
		standupContainer.addComponent(view.getWidget());
		StandupReportWithBLOBs standupReport = (StandupReportWithBLOBs) data
				.getParams();
		view.editItem(standupReport);

		ProjectBreadcrumb breadCrumb = ViewManager
				.getCacheComponent(ProjectBreadcrumb.class);
		breadCrumb.gotoStandupAdd(new GregorianCalendar().getTime());
	}

}
