package com.esofthead.mycollab.mobile.module.project.view.bug;

import com.esofthead.mycollab.common.GenericLinkUtils;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.mobile.module.project.events.BugEvent;
import com.esofthead.mycollab.mobile.ui.AbstractMobilePresenter;
import com.esofthead.mycollab.mobile.ui.ConfirmDialog;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 * 
 */
public class BugReadPresenter extends AbstractMobilePresenter<BugReadView> {

	private static final long serialVersionUID = -1031817390942006096L;

	public BugReadPresenter() {
		super(BugReadView.class);
	}

	@Override
	protected void postInitView() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<SimpleBug>() {
					@Override
					public void onEdit(SimpleBug data) {
						EventBusFactory.getInstance().post(
								new BugEvent.GotoEdit(this, data));
					}

					@Override
					public void onAdd(SimpleBug data) {
						EventBusFactory.getInstance().post(
								new BugEvent.GotoAdd(this, null));
					}

					@Override
					public void onDelete(final SimpleBug data) {
						ConfirmDialog.show(
								UI.getCurrent(),
								AppContext
										.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
								AppContext
										.getMessage(GenericI18Enum.BUTTON_YES),
								AppContext.getMessage(GenericI18Enum.BUTTON_NO),
								new ConfirmDialog.CloseListener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(
											final ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											final BugService bugService = ApplicationContextUtil
													.getSpringBean(BugService.class);
											bugService.removeWithSession(
													data.getId(),
													AppContext.getUsername(),
													AppContext.getAccountId());
											EventBusFactory.getInstance().post(
													new BugEvent.GotoList(this,
															null));
										}
									}
								});
					}

					@Override
					public void onClone(SimpleBug data) {
						SimpleBug cloneData = (SimpleBug) data.copy();
						cloneData.setId(null);
						EventBusFactory.getInstance().post(
								new BugEvent.GotoEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						// Do nothing
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {

		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.BUGS)) {
			if (data.getParams() instanceof Integer) {
				BugService bugService = ApplicationContextUtil
						.getSpringBean(BugService.class);
				SimpleBug bug = bugService.findById((Integer) data.getParams(),
						AppContext.getAccountId());
				if (bug != null) {
					super.onGo(container, data);
					view.previewItem(bug);
					AppContext.addFragment(
							"project/bug/preview/"
									+ GenericLinkUtils
											.encodeParam(new Object[] {
													CurrentProjectVariables
															.getProjectId(),
													bug.getId() }), bug
									.getSummary());
				} else {
					NotificationUtil.showRecordNotExistNotification();
					return;
				}
			}
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}
}
