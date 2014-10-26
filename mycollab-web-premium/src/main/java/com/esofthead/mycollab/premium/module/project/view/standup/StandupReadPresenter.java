package com.esofthead.mycollab.premium.module.project.view.standup;

import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class StandupReadPresenter extends AbstractPresenter<StandupReadView> {
	private static final long serialVersionUID = 1L;

	public StandupReadPresenter() {
		super(StandupReadView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (data.getParams() instanceof Integer) {
			StandupReportService standupService = ApplicationContextUtil
					.getSpringBean(StandupReportService.class);
			SimpleStandupReport standupReport = standupService
					.findById((Integer) data.getParams(),
							AppContext.getAccountId());
			if (standupReport != null) {
				StandupContainer standupContainer = (StandupContainer) container;
				standupContainer.removeAllComponents();
				standupContainer.addComponent(view.getWidget());
			} else {
				NotificationUtil.showRecordNotExistNotification();
				return;
			}
		}
	}

}
