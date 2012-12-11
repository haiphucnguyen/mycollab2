package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface ProjectMessageView extends View {
	void setCriteria(MessageSearchCriteria criteria);
}
