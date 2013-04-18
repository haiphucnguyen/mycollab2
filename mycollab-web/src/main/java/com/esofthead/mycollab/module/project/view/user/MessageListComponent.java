package com.esofthead.mycollab.module.project.view.user;

import java.util.List;

import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;
import com.esofthead.mycollab.module.project.localization.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.service.MessageService;
import com.esofthead.mycollab.module.project.view.ProjectLinkGenerator;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.ui.CommonUIFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class MessageListComponent extends Depot {
	private static final long serialVersionUID = 1L;

	private DefaultBeanPagedList<MessageService, MessageSearchCriteria, SimpleMessage> messageList;

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
			String content = "<img src=\"%s\" alt=\"\"> <a href=\"#\">%s</a> added news <a href=\"%s\">%s</a> to project <a href=\"%s\">%s</a>";
			content = String.format(content, UserAvatarControlFactory.getLink(
					AppContext.getAccountId(), message.getPosteduser(), 16),
					message.getFullPostedUserName(), ProjectLinkGenerator
							.generateMessagePreviewFullLink(
									message.getProjectid(), message.getId()),
					message.getTitle(), ProjectLinkGenerator
							.generateProjectFullLink(message.getProjectid()),
					message.getProjectName());
			System.out.println("Message content: " + content);
			Label messageLink = new Label(content, Label.CONTENT_XHTML);

			return messageLink;
			// CssLayout layout = new CssLayout();
			// layout.setWidth("100%");
			// layout.setStyleName("activity-stream");
			//
			// CssLayout header = new CssLayout();
			// header.setStyleName("stream-content");
			// header.addComponent(new ProjectUserLink(obj.getPosteduser(), obj
			// .getFullPostedUserName(), true));
			//
			// Label actionLbl = new Label(
			// LocalizationHelper
			// .getMessage(ProjectCommonI18nEnum.FEED_ADD_NEWS_ACTION));
			// actionLbl.setWidth(Sizeable.SIZE_UNDEFINED, 0);
			// header.addComponent(actionLbl);
			//
			// Button messageLink = new Button(handleText(obj.getTitle()),
			// new Button.ClickListener() {
			// private static final long serialVersionUID = 1L;
			//
			// @Override
			// public void buttonClick(ClickEvent event) {
			// EventBus.getInstance()
			// .fireEvent(
			// new ProjectEvent.GotoMyProject(
			// this,
			// new PageActionChain(
			// new ProjectScreenData.Goto(
			// obj.getProjectid()),
			// new MessageScreenData.Read(
			// obj.getId()))));
			// }
			// });
			// messageLink.setStyleName("link");
			// messageLink.setIcon(ProjectResources
			// .getIconResource16size(ProjectContants.MESSAGE));
			// header.addComponent(messageLink);
			//
			// Label projectLbl = new Label(
			// LocalizationHelper
			// .getMessage(ProjectCommonI18nEnum.FEED_IN_PROJECT_LABEL));
			// projectLbl.setWidth(Sizeable.SIZE_UNDEFINED, 0);
			// header.addComponent(projectLbl);
			//
			// Button projectLink = generateProjectLink(obj.getProjectName(),
			// new Button.ClickListener() {
			// private static final long serialVersionUID = 1L;
			//
			// @Override
			// public void buttonClick(ClickEvent event) {
			// EventBus.getInstance()
			// .fireEvent(
			// new ProjectEvent.GotoMyProject(
			// this,
			// new PageActionChain(
			// new ProjectScreenData.Goto(
			// obj.getProjectid()))));
			// }
			// });
			// projectLink.setIcon(ProjectResources
			// .getIconResource16size(ProjectContants.PROJECT));
			// projectLink.setStyleName("link");
			// header.addComponent(projectLink);
			//
			// layout.addComponent(header);
			//
			// CssLayout body = new CssLayout();
			// body.setStyleName("activity-date");
			// Label dateLbl = new Label("From "
			// + DateTimeUtils.getStringDateFromNow(obj.getPosteddate()));
			// body.addComponent(dateLbl);
			//
			// layout.addComponent(body);
			// return layout;
		}

	}

	private static Button generateProjectLink(String linkname,
			Button.ClickListener listener) {
		return CommonUIFactory.createButtonTooltip(handleText(linkname),
				linkname, listener);
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
