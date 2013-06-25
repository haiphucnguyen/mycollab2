/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public abstract class UserFormLayoutFactory implements IFormLayoutFactory {

	private final String title;
	private UserInformationLayout userInformationLayout;

	public UserFormLayoutFactory(final String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		final AddViewLayout userAddLayout = new AddViewLayout(this.title,
				MyCollabResource.newResource("icons/48/user/user.png"));

		final Layout topPanel = this.createTopPanel();
		if (topPanel != null) {
			userAddLayout.addTopControls(topPanel);
		}

		this.userInformationLayout = new UserInformationLayout();
		this.userInformationLayout.getLayout().setWidth("100%");
		userAddLayout.addBody(this.userInformationLayout.getLayout());

		final Layout bottomPanel = this.createBottomPanel();
		if (bottomPanel != null) {
			userAddLayout.addBottomControls(bottomPanel);
		}

		return userAddLayout;
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

	@Override
	public void attachField(final Object propertyId, final Field field) {
		this.userInformationLayout.attachField(propertyId, field);
	}

	public static class UserInformationLayout implements IFormLayoutFactory {

		private GridFormLayoutHelper informationLayout;

		@Override
		public Layout getLayout() {
			final VerticalLayout layout = new VerticalLayout();
			final Label organizationHeader = new Label("User Information");
			organizationHeader.setStyleName("h2");
			layout.addComponent(organizationHeader);

			this.informationLayout = new GridFormLayoutHelper(2, 6, "100%",
					"167px", Alignment.MIDDLE_LEFT);
			this.informationLayout.getLayout().setWidth("100%");
			this.informationLayout.getLayout().setMargin(false);
			this.informationLayout.getLayout().addStyleName(
					UIConstants.COLORED_GRIDLAYOUT);

			layout.addComponent(this.informationLayout.getLayout());
			return layout;
		}

		@Override
		public void attachField(final Object propertyId, final Field field) {
			if (propertyId.equals("firstname")) {
				this.informationLayout.addComponent(field, "First Name", 0, 0);
			} else if (propertyId.equals("lastname")) {
				this.informationLayout.addComponent(field, "Last Name", 0, 1);
			} else if (propertyId.equals("nickname")) {
				this.informationLayout.addComponent(field, "Nick Name", 1, 0);
			} else if (propertyId.equals("dateofbirth")) {
				this.informationLayout.addComponent(field, "Birthday", 1, 1);
			} else if (propertyId.equals("email")) {
				this.informationLayout.addComponent(field, "Email", 0, 2);
			} else if (propertyId.equals("isadmin")) {
				this.informationLayout.addComponent(field, "Is Admin", 1, 2);
			} else if (propertyId.equals("company")) {
				this.informationLayout.addComponent(field, "Company", 0, 3);
			} else if (propertyId.equals("website")) {
				this.informationLayout.addComponent(field, "Website", 1, 3);
			}
		}
	}
}
