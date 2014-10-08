package com.esofthead.mycollab.mobile.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
public interface BugReadView extends IPreviewView<SimpleBug> {

	HasPreviewFormHandlers<SimpleBug> getPreviewFormHandlers();
}
