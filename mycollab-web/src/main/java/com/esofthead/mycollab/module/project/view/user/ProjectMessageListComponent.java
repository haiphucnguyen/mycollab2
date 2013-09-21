package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkUtils;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;
import com.esofthead.mycollab.module.project.localization.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.service.MessageService;
import com.esofthead.mycollab.module.project.view.ProjectLinkBuilder;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ProjectMessageListComponent extends Depot {
	public static class MessageRowDisplayHandler implements
			DefaultBeanPagedList.RowDisplayHandler<SimpleMessage> {

		@Override
		public Component generateRow(final SimpleMessage message,
				final int rowIndex) {
			final CssLayout layout = new CssLayout();
			layout.setWidth("100%");
			layout.setStyleName("activity-stream");

			if ((rowIndex + 1) % 2 != 0) {
				layout.addStyleName("odd");
			}

			final CssLayout header = new CssLayout();
			header.setStyleName("stream-content");

			final String content = LocalizationHelper.getMessage(
					ProjectCommonI18nEnum.FEED_PROJECT_MESSAGE_TITLE,
					UserAvatarControlFactory.getAvatarLink(
							message.getPostedUserAvatarId(), 16),
					ProjectLinkBuilder.generateProjectMemberFullLink(
							message.getProjectid(), message.getPosteduser()),
					message.getFullPostedUserName(), MyCollabResource
							.newResourceLink("icons/16/project/message.png"),
					ProjectLinkBuilder.generateMessagePreviewFullLink(
							message.getProjectid(), message.getId(),
							ProjectLinkUtils.DEFAULT_PREFIX_PARAM), message
							.getTitle());
			final Label actionLbl = new Label(content, Label.CONTENT_XHTML);

			header.addComponent(actionLbl);

			layout.addComponent(header);

			final CssLayout body = new CssLayout();
			body.setStyleName("activity-date");
			final Label dateLbl = new Label("From "
					+ DateTimeUtils.getStringDateFromNow(message
							.getPosteddate()));
			body.addComponent(dateLbl);

			layout.addComponent(body);
			return layout;
		}
	}

	private static final long serialVersionUID = 1L;

	private final DefaultBeanPagedList<MessageService, MessageSearchCriteria, SimpleMessage> messageList;

	public ProjectMessageListComponent() {
		super("Latest News", new VerticalLayout());

		messageList = new DefaultBeanPagedList<MessageService, MessageSearchCriteria, SimpleMessage>(
				ApplicationContextUtil.getSpringBean(MessageService.class),
				new MessageRowDisplayHandler(), 5);
		addStyleName("activity-panel");
		((VerticalLayout) bodyContent).setMargin(false);
	}

	public void showLatestMessages() {
		bodyContent.removeAllComponents();
		bodyContent.addComponent(new LazyLoadWrapper(messageList));
		final MessageSearchCriteria searchCriteria = new MessageSearchCriteria();
		searchCriteria.setProjectids(new SetSearchField<Integer>(
				CurrentProjectVariables.getProjectId()));

		messageList.setSearchCriteria(searchCriteria);
	}
}
