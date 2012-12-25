package com.esofthead.mycollab.module.project.view.message;

import com.esofthead.mycollab.module.project.domain.Message;
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface MessageListView extends View {
	void setCriteria(MessageSearchCriteria criteria);
	
	HasEditFormHandlers<Message> getEditFormHandlers();
}
