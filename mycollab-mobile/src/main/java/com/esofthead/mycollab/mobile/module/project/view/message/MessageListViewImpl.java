package com.esofthead.mycollab.mobile.module.project.view.message;

import com.esofthead.mycollab.mobile.module.project.ui.AbstractListViewComp;
import com.esofthead.mycollab.mobile.ui.AbstractPagedBeanList;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.ui.Component;

@ViewComponent
public class MessageListViewImpl extends
		AbstractListViewComp<MessageSearchCriteria, SimpleMessage> implements
		MessageListView {

	private static final long serialVersionUID = -5340014066758050437L;

	public MessageListViewImpl() {
		super();
		setCaption(AppContext.getMessage(ProjectCommonI18nEnum.VIEW_MESSAGE));
		setStyleName("message-list-view");

	}

	@Override
	protected AbstractPagedBeanList<MessageSearchCriteria, SimpleMessage> createBeanTable() {
		MessageListDisplay messageListDisplay = new MessageListDisplay();
		return messageListDisplay;
	}

	@Override
	protected Component createRightComponent() {
		// TODO Auto-generated method stub
		return null;
	}

}
