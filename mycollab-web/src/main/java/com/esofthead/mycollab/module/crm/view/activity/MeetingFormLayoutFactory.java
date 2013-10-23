package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout2;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public abstract class MeetingFormLayoutFactory implements IFormLayoutFactory {

	private static final long serialVersionUID = 1L;
	private String title;
	private IFormLayoutFactory informationLayout;

	public MeetingFormLayoutFactory(String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		AddViewLayout2 meetingLayout = new AddViewLayout2(title,
				MyCollabResource.newResource("icons/22/crm/meeting.png"));

		Layout topPanel = createTopPanel();
		if (topPanel != null) {
			meetingLayout.addControlButtons(topPanel);
		}
		informationLayout = new DynaFormLayout(
				MeetingDefaultFormLayoutFactory.getForm());
		meetingLayout.addBody(informationLayout.getLayout());

		return meetingLayout;
	}

	@Override
	public void attachField(Object propertyId, Field field) {
		informationLayout.attachField(propertyId, field);
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();
}
