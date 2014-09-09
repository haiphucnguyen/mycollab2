package com.esofthead.mycollab.mobile.module.project.view.message;

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.mobile.module.project.ui.ProjectGenericPresenter;
import com.esofthead.mycollab.mobile.shell.events.ShellEvent;
import com.esofthead.mycollab.module.project.ProjectLinkGenerator;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.i18n.MessageI18nEnum;
import com.esofthead.mycollab.module.project.service.MessageService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.0
 *
 */
public class MessageAddPresenter extends
		ProjectGenericPresenter<MessageAddView> {

	private static final long serialVersionUID = -6518878184021039341L;

	public MessageAddPresenter() {
		super(MessageAddView.class);
	}

	@Override
	protected void postInitView() {
		super.postInitView();
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<SimpleMessage>() {

					private static final long serialVersionUID = 2381946253040633727L;

					@Override
					public void onSaveAndNew(SimpleMessage bean) {
						// Do nothing
					}

					@Override
					public void onSave(SimpleMessage bean) {
						MessageService messageService = ApplicationContextUtil
								.getSpringBean(MessageService.class);
						messageService.saveWithSession(bean,
								AppContext.getUsername());
						EventBusFactory.getInstance().post(
								new ShellEvent.NavigateBack(this, null));
					}

					@Override
					public void onCancel() {
						// Do nothing
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer navigator, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.MESSAGES)) {
			super.onGo(navigator, data);
			AppContext.addFragment(ProjectLinkGenerator
					.generateMessageAddLink(CurrentProjectVariables
							.getProjectId()), AppContext
					.getMessage(MessageI18nEnum.M_VIEW_ADD_TITLE));
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
		super.onGo(navigator, data);
	}

}
