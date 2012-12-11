package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;
import com.esofthead.mycollab.module.project.service.MessageService;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.PagedBeanList;
import com.esofthead.mycollab.vaadin.ui.PagedBeanList.RowDisplayHandler;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

public class ProjectMessageViewImpl extends AbstractView implements
		ProjectMessageView {
	private static final long serialVersionUID = 8433776359091397422L;

	private PagedBeanList<MessageService, MessageSearchCriteria, SimpleMessage> tableItem;

	public ProjectMessageViewImpl() {
		super();

		tableItem = new PagedBeanList<MessageService, MessageSearchCriteria, SimpleMessage>(
				AppContext.getSpringBean(MessageService.class),
				new MessageRowDisplayHandler());
		this.addComponent(tableItem);
	}

	@Override
	public void setCriteria(MessageSearchCriteria criteria) {
	}

	private class MessageRowDisplayHandler implements
			RowDisplayHandler<SimpleMessage> {

		@Override
		public Component generateRow(SimpleMessage obj) {
			return new Label("Message");
		}

	}
}
