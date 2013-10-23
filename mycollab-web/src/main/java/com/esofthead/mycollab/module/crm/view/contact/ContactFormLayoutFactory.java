package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout2;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

public abstract class ContactFormLayoutFactory implements IFormLayoutFactory {
	private static final long serialVersionUID = 1L;
	private final String title;

	private IFormLayoutFactory informationLayout;

	public ContactFormLayoutFactory(final String title) {
		this.title = title;
	}

	@Override
	public void attachField(final Object propertyId, final Field field) {
		informationLayout.attachField(propertyId, field);
	}

	protected abstract Layout createBottomPanel();

	protected abstract Layout createTopPanel();

	@Override
	public Layout getLayout() {
		final AddViewLayout2 contactAddLayout = new AddViewLayout2(title,
				MyCollabResource.newResource("icons/22/crm/contact.png"));

		final Layout topPanel = createTopPanel();
		if (topPanel != null) {
			contactAddLayout.addControlButtons(topPanel);
		}

		informationLayout = new DynaFormLayout(
				ContactDefaultDynaFormLayoutFactory.getForm());
		contactAddLayout.addBody(informationLayout.getLayout());

		return contactAddLayout;
	}
}
