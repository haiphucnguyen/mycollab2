package com.esofthead.mycollab.mobile.module.project.view.settings;

import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IPreviewView;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.2
 */
public interface ProjectMemberReadView extends
		IPreviewView<SimpleProjectMember> {

	HasPreviewFormHandlers<SimpleProjectMember> getPreviewFormHandlers();
}
