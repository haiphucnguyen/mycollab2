package com.esofthead.mycollab.mobile.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.0
 */
public interface TaskReadView extends IPreviewView<SimpleTask> {
	HasPreviewFormHandlers<SimpleTask> getPreviewFormHandlers();
}
