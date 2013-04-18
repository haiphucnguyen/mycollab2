package com.esofthead.mycollab.module.project.view.user;

import java.util.List;

import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;
import com.esofthead.mycollab.module.project.localization.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.service.MessageService;
import com.esofthead.mycollab.module.project.view.ProjectLinkGenerator;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class MessageListComponent extends Depot {
	private static final long serialVersionUID = 1L;

	private final DefaultBeanPagedList<MessageService, MessageSearchCriteria, SimpleMessage> messageList;

	public MessageListComponent() {
		super(LocalizationHelper.getMessage(ProjectCommonI18nEnum.NEWS_TITLE),
				new VerticalLayout());

		messageList = new DefaultBeanPagedList<MessageService, MessageSearchCriteria, SimpleMessage>(
				AppContext.getSpringBean(MessageService.class),
				MessageRowDisplayHandler.class, 5);
		this.bodyContent.addComponent(new LazyLoadWrapper(messageList));
		this.addStyleName("activity-panel");
		((VerticalLayout) this.bodyContent).setMargin(false);
	}

	public void showLatestMessages(List<Integer> prjKeys) {
		MessageSearchCriteria searchCriteria = new MessageSearchCriteria();

		messageList.setSearchCriteria(searchCriteria);
	}

	public static class MessageRowDisplayHandler implements
			DefaultBeanPagedList.RowDisplayHandler<SimpleMessage> {

		@Override
		public Component generateRow(final SimpleMessage message, int rowIndex) {
			CssLayout layout = new CssLayout();
			layout.setWidth("100%");
			layout.setStyleName("activity-stream");

			CssLayout header = new CssLayout();
			header.setStyleName("stream-content");
			String action = LocalizationHelper
					.getMessage(ProjectCommonI18nEnum.FEED_ADD_NEWS_ACTION);
			String destination = LocalizationHelper
					.getMessage(ProjectCommonI18nEnum.FEED_IN_PROJECT_LABEL);
			String content = "<a href=\"#\">%s</a>&nbsp;" + action
					+ "<a title=\"%s\" href=\"%s\">%s</a>&nbsp;" + destination
					+ "<a title=\"%s\" href=\"%s\">%s</a>";
			content = String.format(content, message.getFullPostedUserName(),
					message.getTitle(), ProjectLinkGenerator
							.generateMessagePreviewFullLink(
									message.getProjectid(), message.getId()),
					message.getTitle(), message.getProjectName(),
					ProjectLinkGenerator.generateProjectFullLink(message
							.getProjectid()), message.getProjectName());
			Label messageLink = new Label(content, Label.CONTENT_XHTML);

			header.addComponent(messageLink);
			layout.addComponent(header);

			CssLayout body = new CssLayout();
			body.setStyleName("activity-date");
			Label dateLbl = new Label("From "
					+ DateTimeUtils.getStringDateFromNow(message
							.getPosteddate()));
			body.addComponent(dateLbl);

			layout.addComponent(body);

			return layout;
		}

	}

	private static String handleText(String value) {
		int limitValue = 45;
		if (ScreenSize.hasSupport1024Pixels()) {
			limitValue = 35;
		}
		if (value.length() > limitValue) {
			return value.substring(0, limitValue) + "...";
		}
		return value;
	}

}
