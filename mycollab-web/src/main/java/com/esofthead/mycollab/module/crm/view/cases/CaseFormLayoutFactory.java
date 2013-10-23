package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout2;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

public abstract class CaseFormLayoutFactory implements IFormLayoutFactory {

	private static final long serialVersionUID = 1L;
	private String title;
	private IFormLayoutFactory caseInformationLayout;

	public CaseFormLayoutFactory(String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		AddViewLayout2 caseAddLayout = new AddViewLayout2(title,
				MyCollabResource.newResource("icons/22/crm/case.png"));

		Layout topPanel = createTopPanel();
		if (topPanel != null) {
			caseAddLayout.addControlButtons(topPanel);
		}

		caseInformationLayout = new DynaFormLayout(
				CasesDefaultFormLayoutFactory.getForm());
		caseAddLayout.addBody(caseInformationLayout.getLayout());

		return caseAddLayout;
	}

	@Override
	public void attachField(Object propertyId, Field field) {
		caseInformationLayout.attachField(propertyId, field);
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();
}
