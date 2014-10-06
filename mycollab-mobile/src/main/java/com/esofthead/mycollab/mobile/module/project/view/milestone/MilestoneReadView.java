package com.esofthead.mycollab.mobile.module.project.view.milestone;

import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
public interface MilestoneReadView extends IPreviewView<SimpleMilestone> {

	HasPreviewFormHandlers<SimpleMilestone> getPreviewFormHandlers();

}
