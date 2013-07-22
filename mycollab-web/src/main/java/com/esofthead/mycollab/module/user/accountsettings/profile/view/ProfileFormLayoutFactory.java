/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.profile.view;

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
public abstract class ProfileFormLayoutFactory implements IFormLayoutFactory {

	private final String title;
	private UserInformationLayout userInformationLayout;

	public ProfileFormLayoutFactory(final String title) {
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

		private GridFormLayoutHelper basicInformationLayout;
		private GridFormLayoutHelper advancedInformationLayout;
		private GridFormLayoutHelper contactInformationLayout;

		@Override
		public Layout getLayout() {
			final VerticalLayout layout = new VerticalLayout();
			final Label organizationHeader = new Label("Basic User Information");
			organizationHeader.setStyleName("h2");
			layout.addComponent(organizationHeader);

			this.basicInformationLayout = new GridFormLayoutHelper(2, 7,
					"100%", "167px", Alignment.MIDDLE_LEFT);
			this.basicInformationLayout.getLayout().setWidth("100%");
			this.basicInformationLayout.getLayout().setMargin(false);
			this.basicInformationLayout.getLayout().addStyleName(
					UIConstants.COLORED_GRIDLAYOUT);

			layout.addComponent(this.basicInformationLayout.getLayout());

			final Label contactHeader = new Label("Contact User Information");
			contactHeader.setStyleName("h2");
			layout.addComponent(contactHeader);

			this.contactInformationLayout = new GridFormLayoutHelper(2, 3,
					"100%", "167px", Alignment.MIDDLE_LEFT);
			this.contactInformationLayout.getLayout().setWidth("100%");
			this.contactInformationLayout.getLayout().setMargin(false);
			this.contactInformationLayout.getLayout().addStyleName(
					UIConstants.COLORED_GRIDLAYOUT);

			layout.addComponent(this.contactInformationLayout.getLayout());

			final Label advancedHeader = new Label("Advanced User Information");
			advancedHeader.setStyleName("h2");
			layout.addComponent(advancedHeader);

			this.advancedInformationLayout = new GridFormLayoutHelper(2, 2,
					"100%", "167px", Alignment.MIDDLE_LEFT);
			this.advancedInformationLayout.getLayout().setWidth("100%");
			this.advancedInformationLayout.getLayout().setMargin(false);
			this.advancedInformationLayout.getLayout().addStyleName(
					UIConstants.COLORED_GRIDLAYOUT);

			layout.addComponent(this.advancedInformationLayout.getLayout());
			return layout;
		}

		@Override
		public void attachField(final Object propertyId, final Field field) {
			if (propertyId.equals("firstname")) {
				this.basicInformationLayout.addComponent(field, "First Name",
						0, 0);
			} else if (propertyId.equals("lastname")) {
				this.basicInformationLayout.addComponent(field, "Last Name", 0,
						1);
			} else if (propertyId.equals("nickname")) {
				this.basicInformationLayout.addComponent(field, "Nick Name", 1,
						0);
			} else if (propertyId.equals("dateofbirth")) {
				this.basicInformationLayout.addComponent(field, "Birthday", 1,
						1);
			} else if (propertyId.equals("email")) {
				this.basicInformationLayout.addComponent(field, "Email", 0, 2);
			} else if (propertyId.equals("timezone")) {
				this.basicInformationLayout.addComponent(field, "Timezone", 0,
						3, 2, "262px", Alignment.MIDDLE_LEFT);
			} else if (propertyId.equals("isAdmin")) {
				this.basicInformationLayout.addComponent(field, "Role", 1, 2);
			} else if (propertyId.equals("company")) {
				this.advancedInformationLayout.addComponent(field, "Company",
						0, 0);
			} else if (propertyId.equals("country")) {
				this.advancedInformationLayout.addComponent(field, "Country",
						0, 1, 2, "262px", Alignment.MIDDLE_LEFT);
			} else if (propertyId.equals("website")) {
				this.advancedInformationLayout.addComponent(field, "Website",
						1, 0);
			} else if (propertyId.equals("workphone")) {
				this.contactInformationLayout.addComponent(field, "Work phone",
						0, 0);
			} else if (propertyId.equals("homephone")) {
				this.contactInformationLayout.addComponent(field, "Home phone",
						0, 1);
			} else if (propertyId.equals("facebookaccount")) {
				this.contactInformationLayout.addComponent(field, "Facebook",
						1, 0);
			} else if (propertyId.equals("twitteraccount")) {
				this.contactInformationLayout.addComponent(field, "Twitter", 1,
						1);
			} else if (propertyId.equals("skypecontact")) {
				this.contactInformationLayout.addComponent(field, "Skype", 0,
						2, 2, "262px", Alignment.MIDDLE_LEFT);
			}
		}
	}
}
