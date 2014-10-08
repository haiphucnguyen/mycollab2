package com.esofthead.mycollab.mobile.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 * 
 */

/*
 * TODO: Add support for Attachments, Components, Versions when they're ready
 */
public interface BugAddView extends IFormAddView<SimpleBug> {

	HasEditFormHandlers<SimpleBug> getEditFormHandlers();

	/*
	 * ProjectFormAttachmentUploadField getAttachUploadField();
	 * 
	 * List<Component> getComponents();
	 * 
	 * List<Version> getAffectedVersions();
	 * 
	 * List<Version> getFixedVersion();
	 */
}
