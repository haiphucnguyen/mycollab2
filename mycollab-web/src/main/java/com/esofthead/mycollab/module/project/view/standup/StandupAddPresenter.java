package com.esofthead.mycollab.module.project.view.standup;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.StandupReport;
import com.esofthead.mycollab.module.project.events.StandUpEvent;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class StandupAddPresenter extends AbstractPresenter<StandupAddView> {
	private static final long serialVersionUID = 1L;

	public StandupAddPresenter() {
		super(StandupAddView.class);
		bind();
	}
	
	private void bind() {
		view.getEditFormHandlers().addFormHandler(new EditFormHandler<StandupReport>() {
            @Override
            public void onSave(final StandupReport standupReport) {
                saveStandupReport(standupReport);
                ViewState viewState = HistoryViewManager.back();
                if (viewState instanceof NullViewState) {
                    EventBus.getInstance().fireEvent(
                            new StandUpEvent.GotoList(this, null));
                }
            }

            @Override
            public void onCancel() {
                ViewState viewState = HistoryViewManager.back();
                if (viewState instanceof NullViewState) {
                    EventBus.getInstance().fireEvent(
                            new StandUpEvent.GotoList(this, null));
                }
            }

            @Override
            public void onSaveAndNew(final StandupReport standupReport) {
                throw new MyCollabException("Do not support this feature");
            }
        });
	}
	
	 public void saveStandupReport(StandupReport standupReport) {
	        StandupReportService standupReportService = AppContext.getSpringBean(StandupReportService.class);
	        SimpleProject project = (SimpleProject) AppContext
	                .getVariable(ProjectContants.PROJECT_NAME);
	        standupReport.setProjectid(project.getId());
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
		StandupContainer standupContainer = (StandupContainer) container;
        standupContainer.removeAllComponents();
        standupContainer.addComponent(view.getWidget());
        StandupReport standupReport = (StandupReport) data.getParams();
        view.editItem(standupReport);

	}

}
