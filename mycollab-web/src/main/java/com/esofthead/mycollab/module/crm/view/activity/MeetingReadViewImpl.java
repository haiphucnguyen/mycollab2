package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class MeetingReadViewImpl extends AbstractView implements
		MeetingReadView {

	private static final long serialVersionUID = 1L;
	private MeetingPreviewBuilder previewForm;

	public MeetingReadViewImpl() {
		super();
		previewForm = new MeetingPreviewBuilder.ReadView();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleMeeting meeting) {
		previewForm.previewItem(meeting);
	}

	@Override
	public HasPreviewFormHandlers<Meeting> getPreviewFormHandlers() {
		return previewForm.getPreviewForm();
	}

	@Override
	public SimpleMeeting getItem() {
		return previewForm.getMeeting();
	}
}
