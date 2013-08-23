package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class BugReadPresenter extends AbstractPresenter<BugReadView> {

	private static final long serialVersionUID = 1L;

	public BugReadPresenter() {
		super(BugReadView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {

		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.BUGS)) {
			if (data.getParams() instanceof Integer) {
				BugService bugService = AppContext
						.getSpringBean(BugService.class);
				SimpleBug bug = bugService.findById((Integer) data.getParams(),
						AppContext.getAccountId());
				if (bug != null) {
					BugContainer bugContainer = (BugContainer) container;
					bugContainer.removeAllComponents();
					bugContainer.addComponent(view.getWidget());
					view.previewItem(bug);

					ProjectBreadcrumb breadcrumb = ViewManager
							.getView(ProjectBreadcrumb.class);
					breadcrumb.gotoBugRead(bug);
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
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}
}
