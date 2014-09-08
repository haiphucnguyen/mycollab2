package com.esofthead.mycollab.mobile.module.project.view.message;

import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.0
 *
 */
public interface MessageAddView extends PageView {
	public HasEditFormHandlers<SimpleMessage> getEditFormHandlers();

	public void initView();
}
