package com.esofthead.mycollab.mobile.module.project.view.message;

import com.esofthead.mycollab.mobile.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.mobile.ui.ListPresenter;
import com.esofthead.mycollab.module.project.ProjectLinkGenerator;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.MessageI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.4.0
 *
 */
public class MessageListPresenter extends
		ListPresenter<MessageListView, MessageSearchCriteria, SimpleMessage> {

	private static final long serialVersionUID = -4299885147378046501L;

	public MessageListPresenter() {
		super(MessageListView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		super.onGo(container, data);
		AppContext.addFragment(ProjectLinkGenerator
				.generateMessagesLink(CurrentProjectVariables.getProjectId()),
				AppContext.getMessage(MessageI18nEnum.VIEW_LIST_TITLE));
	}

}
