package com.esofthead.mycollab.mobile.module.project.view.message;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.mobile.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.mobile.module.project.ui.ProjectGenericPresenter;
import com.esofthead.mycollab.module.project.ProjectLinkGenerator;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.i18n.BreadcrumbI18nEnum;
import com.esofthead.mycollab.module.project.service.MessageService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.4.0
 *
 */
public class MessageReadPresenter extends
		ProjectGenericPresenter<MessageReadView> {

	private static final long serialVersionUID = 334720221360960772L;

	public MessageReadPresenter() {
		super(MessageReadView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.MESSAGES)) {
			if (data.getParams() instanceof Integer) {
				MessageService messageService = ApplicationContextUtil
						.getSpringBean(MessageService.class);
				SimpleMessage message = messageService.findMessageById(
						(Integer) data.getParams(), AppContext.getAccountId());
				super.onGo(container, data);
				view.previewItem(message);

				AppContext.addFragment(ProjectLinkGenerator
						.generateMessagePreviewLink(
								CurrentProjectVariables.getProjectId(),
								message.getId()),
						AppContext.getMessage(
								BreadcrumbI18nEnum.FRA_MESSAGE_READ,
								message.getTitle()));
			} else {
				throw new MyCollabException("Unhanddle this case yet");
			}
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}

}
