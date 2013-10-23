package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout2;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

public abstract class AssignmentFormLayoutFactory implements IFormLayoutFactory {

	private static final long serialVersionUID = 1L;
	private IFormLayoutFactory informationLayout;
	private String title;

	public AssignmentFormLayoutFactory(String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		AddViewLayout2 taskAddLayout = new AddViewLayout2(title,
				MyCollabResource.newResource("icons/18/crm/task.png"));

		Layout topPanel = createTopPanel();
		if (topPanel != null) {
			taskAddLayout.addControlButtons(topPanel);
		}

		informationLayout = new DynaFormLayout(
				AssignmentDefaultFormLayoutFactory.getForm());
		taskAddLayout.addBody(informationLayout.getLayout());

		return taskAddLayout;
	}

	@Override
	public void attachField(Object propertyId, Field field) {
		informationLayout.attachField(propertyId, field);
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();
}
