package com.esofthead.mycollab.module.user.ui.accountsettings;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedBeanForm;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Component
public class UserInformationViewImpl extends AbstractView implements
		UserInformationView {

	@Override
	protected void initializeLayout() {
		this.removeAllComponents();
		User currentUser = AppContext.getSession();
		Form formItem = AppContext.getSpringBean(EditForm.class);
		formItem.setItemDataSource(new BeanItem<User>(currentUser));
		this.addComponent(formItem);
		this.setStyleName("userInfoContainer");
	}

	@Component
	public static class EditForm extends GenericForm {
		private static final long serialVersionUID = 1L;

		@Override
		protected HorizontalLayout createButtonControls() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);
			layout.setStyleName("editInfoControl");
			FormActionListener formActionListener = new FormActionListener();
			Button saveBtn = new Button(SAVE_ACTION, formActionListener);

			layout.addComponent(saveBtn);
			layout.setComponentAlignment(saveBtn, Alignment.MIDDLE_CENTER);

			return layout;
		}

		private class FormActionListener implements Button.ClickListener {
			private static final long serialVersionUID = 1L;

			// ==================================================
			// Actions
			// ==================================================
			@Override
			public void buttonClick(ClickEvent event) {
				String caption = event.getButton().getCaption();
				@SuppressWarnings("unchecked")
				User user = ((BeanItem<User>) EditForm.this.getItemDataSource())
						.getBean();
				if (caption.equals(SAVE_ACTION)) {
					if (validateForm(user)) {

					}
				}
			}
		}
	}

	public static abstract class GenericForm extends AdvancedBeanForm<User> {
		private static final long serialVersionUID = 1L;

		protected GridFormLayoutHelper informationLayout;

		public GenericForm() {
			super();

			VerticalLayout layout = new VerticalLayout();

			Label informationHeader = new Label("User Informations");
			informationHeader.setStyleName("h1");
			layout.addComponent(informationHeader);

			informationLayout = new GridFormLayoutHelper(1, 9);
			layout.addComponent(informationLayout.getLayout());
			layout.setComponentAlignment(informationLayout.getLayout(),
					Alignment.MIDDLE_LEFT);

			this.setWriteThrough(true);
			this.setInvalidCommitted(false);

			HorizontalLayout userInfoControls = createButtonControls();
			layout.addComponent(userInfoControls);
			layout.setComponentAlignment(userInfoControls,
					Alignment.BOTTOM_CENTER);

			setLayout(layout);
		}

		abstract protected HorizontalLayout createButtonControls();

		/*
		 * Override to get control over where fields are placed.
		 */
		@Override
		protected void attachField(Object propertyId, Field field) {

			if (propertyId.equals("firstname")) {
				field.setSizeUndefined();
				informationLayout.addComponent(propertyId.equals("firstname"),
						field, "First Name", 0, 0);
			} else if (propertyId.equals("lastname")) {
				field.setSizeUndefined();
				informationLayout.addComponent(propertyId.equals("lastname"),
						field, "Last Name", 0, 1);
			} else if (propertyId.equals("middlename")) {
				field.setSizeUndefined();
				informationLayout.addComponent(propertyId.equals("middlename"),
						field, "Middle Name", 0, 2);
			} else if (propertyId.equals("nickname")) {
				field.setSizeUndefined();
				informationLayout.addComponent(propertyId.equals("nickname"),
						field, "Nickname", 0, 3);
			} else if (propertyId.equals("dateofbirth")) {
				field.setSizeUndefined();
				informationLayout.addComponent(
						propertyId.equals("dateofbirth"), field, "Birthday", 0,
						4);
			} else if (propertyId.equals("email")) {
				field.setSizeUndefined();
				informationLayout.addComponent(propertyId.equals("email"),
						field, "Email", 0, 5);
			} else if (propertyId.equals("website")) {
				field.setSizeUndefined();
				informationLayout.addComponent(propertyId.equals("website"),
						field, "Website", 0, 6);
			} else if (propertyId.equals("displayname")) {
				field.setSizeUndefined();
				informationLayout.addComponent(
						propertyId.equals("displayname"), field,
						"Display Name", 0, 7);
			} else if (propertyId.equals("company")) {
				field.setSizeUndefined();
				informationLayout.addComponent(propertyId.equals("company"),
						field, "Company", 0, 8);
			}

		}
	}

}
