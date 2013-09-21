package com.esofthead.mycollab.module.project.view.standup;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

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
					.findStandupReportById((Integer) data.getParams(),
							AppContext.getAccountId());
			if (standupReport != null) {
				StandupContainer standupContainer = (StandupContainer) container;
				standupContainer.removeAllComponents();
				standupContainer.addComponent(view.getWidget());
			} else {
				AppContext
						.getApplication()
						.getMainWindow()
						.showNotification(
								LocalizationHelper
										.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
								LocalizationHelper
										.getMessage(GenericI18Enum.INFORMATION_RECORD_IS_NOT_EXISTED_MESSAGE),
								Window.Notification.TYPE_HUMANIZED_MESSAGE);
				return;
			}
		}
	}

}
