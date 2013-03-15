package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;
import com.esofthead.mycollab.module.project.service.MessageService;
import com.esofthead.mycollab.vaadin.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ProjectMessageListComponent extends Depot {
	private static final long serialVersionUID = 1L;

	private DefaultBeanPagedList<MessageService, MessageSearchCriteria, SimpleMessage> messageList;

	public ProjectMessageListComponent() {
		super("Latest News", new VerticalLayout());

		messageList = new DefaultBeanPagedList<MessageService, MessageSearchCriteria, SimpleMessage>(
				AppContext.getSpringBean(MessageService.class),
				MessageRowDisplayHandler.class, 5);
		this.bodyContent.addComponent(new LazyLoadWrapper(messageList));
		this.addStyleName("activity-panel");
		((VerticalLayout) this.bodyContent).setMargin(false);
	}

	public void showLatestMessages() {
		MessageSearchCriteria searchCriteria = new MessageSearchCriteria();
		searchCriteria.setProjectids(new SetSearchField<Integer>(
				CurrentProjectVariables.getProjectId()));

		messageList.setSearchCriteria(searchCriteria);
	}

	public static class MessageRowDisplayHandler implements
			DefaultBeanPagedList.RowDisplayHandler<SimpleMessage> {

		@Override
		public Component generateRow(SimpleMessage obj, int rowIndex) {
			CssLayout layout = new CssLayout();
			layout.setWidth("100%");
			layout.setStyleName("activity-stream");

			CssLayout header = new CssLayout();
			header.setStyleName("stream-content");
			header.addComponent(new Label(obj.getTitle()));
			layout.addComponent(header);

			CssLayout body = new CssLayout();
			body.setStyleName("activity-date");
			Label dateLbl = new Label("From "
					+ DateTimeUtils.getStringDateFromNow(obj.getPosteddate()));
			body.addComponent(dateLbl);

			layout.addComponent(body);
			return layout;
		}

	}
}
