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
public abstract class RoleFormLayoutFactory implements IFormLayoutFactory {
	private static final long serialVersionUID = 1L;
	private final String title;
	private RoleInformationLayout userInformationLayout;

	public RoleFormLayoutFactory(final String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		final AddViewLayout userAddLayout = new AddViewLayout(this.title,
				MyCollabResource.newResource("icons/48/user/group.png"));

		final Layout topPanel = this.createTopPanel();
		if (topPanel != null) {
			userAddLayout.addTopControls(topPanel);
		}

		this.userInformationLayout = new RoleInformationLayout();
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

	public static class RoleInformationLayout implements IFormLayoutFactory {
		private static final long serialVersionUID = 1L;
		private GridFormLayoutHelper informationLayout;

		@Override
		public Layout getLayout() {
			final VerticalLayout layout = new VerticalLayout();
			final Label organizationHeader = new Label("Role Information");
			organizationHeader.setStyleName("h2");
			layout.addComponent(organizationHeader);

			this.informationLayout = new GridFormLayoutHelper(6, 2, "100%",
					"167px", Alignment.MIDDLE_LEFT);
			this.informationLayout.getLayout().setWidth("100%");
			this.informationLayout.getLayout().addStyleName(
					UIConstants.COLORED_GRIDLAYOUT);
			this.informationLayout.getLayout().setMargin(false);

			layout.addComponent(this.informationLayout.getLayout());
			return layout;
		}

		@Override
		public void attachField(final Object propertyId, final Field field) {
			if (propertyId.equals("rolename")) {
				this.informationLayout.addComponent(field, "Role Name", 0, 0);
			} else if (propertyId.equals("description")) {
				this.informationLayout.addComponent(field, "Description", 0, 1);
			}
		}
	}
}
