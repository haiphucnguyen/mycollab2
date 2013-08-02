/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class ProjectMemberReadViewImpl extends AbstractView implements
		ProjectMemberReadView {

	private static final long serialVersionUID = 1L;
	protected ProjectMemberPreviewBuilder.ReadView projectMemberPreview;

	public ProjectMemberReadViewImpl() {
		super();
		this.setMargin(false, false, true, false);
		this.projectMemberPreview = new ProjectMemberPreviewBuilder.ReadView();
		this.addComponent(this.projectMemberPreview);
	}

	@Override
	public void previewItem(final SimpleProjectMember item) {
		this.projectMemberPreview.previewItem(item);
	}

	@Override
	public SimpleProjectMember getItem() {
		return this.projectMemberPreview.getProjectMember();
	}

	@Override
	public HasPreviewFormHandlers<ProjectMember> getPreviewFormHandlers() {
		return this.projectMemberPreview.getPreviewForm();
	}
}
