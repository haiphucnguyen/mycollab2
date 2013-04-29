package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.SimpleTask;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class AssignmentReadViewImpl extends AbstractView implements
		AssignmentReadView {
	private static final long serialVersionUID = 1L;
	
	private AssignmentPreviewBuilder assignmentPreview;

	public AssignmentReadViewImpl() {
		super();
		assignmentPreview = new AssignmentPreviewBuilder.ReadView();
		this.addComponent(assignmentPreview);
	}

	@Override
	public void previewItem(SimpleTask task) {
		assignmentPreview.task = task;
		assignmentPreview.previewItem(task);
	}

	@Override
	public HasPreviewFormHandlers<SimpleTask> getPreviewFormHandlers() {
		return assignmentPreview.getPreviewForm();
	}

	@Override
	public SimpleTask getItem() {
		return assignmentPreview.getAssignment();
	}
}
