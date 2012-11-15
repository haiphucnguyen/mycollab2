package com.esofthead.mycollab.module.crm.ui;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.ui.components.AddViewLayout;
import com.esofthead.mycollab.module.crm.ui.components.IndustryComboBox;
import com.esofthead.mycollab.module.crm.ui.components.LeadSourceComboBox;
import com.esofthead.mycollab.module.crm.ui.components.LeadStatusComboBox;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedBeanForm;
import com.esofthead.mycollab.vaadin.ui.CountryComboBox;
import com.esofthead.mycollab.vaadin.ui.DefaultFormEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@Component
public class LeadAddViewImpl extends AbstractView implements LeadAddView {

	private static final long serialVersionUID = 1L;

	@Override
	protected void initializeLayout() {
	}

	@Override
	public void addNewItem() {
		this.removeAllComponents();
		Form formItem = AppContext.getSpringBean(EditForm.class);
		formItem.setItemDataSource(new BeanItem<Lead>(new Lead()));
		this.addComponent(formItem);
	}

	@Override
	public void editItem(Lead item) {
		this.removeAllComponents();
		Form formItem = AppContext.getSpringBean(EditForm.class);
		formItem.setItemDataSource(new BeanItem<Lead>(item));
		this.addComponent(formItem);
	}

	@Override
	public void viewItem(Lead item) {
		this.removeAllComponents();
		Form formItem = AppContext.getSpringBean(ViewForm.class);
		formItem.setItemDataSource(new BeanItem<Lead>(item));
		this.addComponent(formItem);
	}

	@Scope("prototype")
	@Component
	public static class EditForm extends GenericForm {
		private static final long serialVersionUID = 1L;

		@Override
		protected HorizontalLayout createButtonControls() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);
			FormActionListener formActionListener = new FormActionListener();
			Button saveBtn = new Button(SAVE_ACTION, formActionListener);
			Button cancelBtn = new Button(CANCEL_ACTION, formActionListener);

			layout.addComponent(saveBtn);
			layout.setComponentAlignment(saveBtn, Alignment.MIDDLE_CENTER);

			layout.addComponent(cancelBtn);
			layout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_CENTER);
			return layout;
		}

		@PostConstruct
		private void initFieldFactory() {
			this.setFormFieldFactory(new EditFormFieldFactory());
		}

		private class EditFormFieldFactory extends DefaultFormEditFieldFactory {
			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {

				if (propertyId.equals("primcountry")
						|| propertyId.equals("othercountry")) {
					CountryComboBox countryComboBox = AppContext
							.getSpringBean(CountryComboBox.class);
					return countryComboBox;
				} else if (propertyId.equals("status")) {
					LeadStatusComboBox statusComboBox = AppContext
							.getSpringBean(LeadStatusComboBox.class);
					return statusComboBox;
				} else if (propertyId.equals("industry")) {
					IndustryComboBox industryComboBox = AppContext
							.getSpringBean(IndustryComboBox.class);
					return industryComboBox;
				} else if (propertyId.equals("assignuser")) {
					UserComboBox userComboBox = AppContext
							.getSpringBean(UserComboBox.class);
					return userComboBox;
				} else if (propertyId.equals("source")) {
					LeadSourceComboBox statusComboBox = AppContext
							.getSpringBean(LeadSourceComboBox.class);
					return statusComboBox;
				}

				return null;
			}
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
				Lead lead = ((BeanItem<Lead>) EditForm.this.getItemDataSource())
						.getBean();
				if (caption.equals(SAVE_ACTION)) {
					if (validateForm(lead)) {
						eventBus.fireEvent(new LeadEvent.Save(this, lead));
					}
				} else if (caption.equals(CANCEL_ACTION)) {
					eventBus.fireEvent(new LeadEvent.GotoList(this, null));
				}
			}
		}
	}

	@Scope("prototype")
	@Component
	public static class ViewForm extends GenericForm {
		private static final long serialVersionUID = 1L;

		@Override
		protected HorizontalLayout createButtonControls() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);
			layout.setStyleName("addNewControl");
			FormActionListener formActionListener = new FormActionListener();
			Button saveBtn = new Button(EDIT_ACTION, formActionListener);
			Button cancelBtn = new Button(CANCEL_ACTION, formActionListener);

			layout.addComponent(saveBtn);
			layout.addComponent(cancelBtn);
			return layout;
		}

		@PostConstruct
		private void initFieldFactory() {
			this.setFormFieldFactory(new DefaultFormViewFieldFactory());
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
				Lead contact = ((BeanItem<Lead>) ViewForm.this
						.getItemDataSource()).getBean();
				if (caption.equals(EDIT_ACTION)) {
					eventBus.fireEvent(new LeadEvent.GotoEdit(this, contact));
				} else if (caption.equals(CANCEL_ACTION)) {
					eventBus.fireEvent(new LeadEvent.GotoList(this, null));
				}
			}
		}

	}

	public static abstract class GenericForm extends AdvancedBeanForm<Lead> {
		private static final long serialVersionUID = 1L;

		protected GridFormLayoutHelper informationLayout;

		protected GridFormLayoutHelper addressLayout;

		protected GridFormLayoutHelper descLayout;

		public GenericForm() {
			super();

			AddViewLayout leadAddLayout = new AddViewLayout("Account");
			leadAddLayout.addTopControls(createButtonControls());

			VerticalLayout layout = new VerticalLayout();
			layout.setSpacing(true);

			Label organizationHeader = new Label("Contact Information");
			organizationHeader.setStyleName("h2");
			layout.addComponent(organizationHeader);

			informationLayout = new GridFormLayoutHelper(2, 7);
			informationLayout.getLayout().setWidth("900px");
			layout.addComponent(informationLayout.getLayout());

			Label addressHeader = new Label("Address Information");
			addressHeader.setStyleName("h2");
			layout.addComponent(addressHeader);
			addressLayout = new GridFormLayoutHelper(2, 5);
			addressLayout.getLayout().setWidth("900px");
			layout.addComponent(addressLayout.getLayout());

			Label descriptionHeader = new Label("Description");
			descriptionHeader.setStyleName("h2");
			layout.addComponent(descriptionHeader);
			descLayout = new GridFormLayoutHelper(1, 1);
			descLayout.getLayout().setWidth("900px");
			layout.addComponent(descLayout.getLayout());

			this.setWriteThrough(true);
			this.setInvalidCommitted(false);

			leadAddLayout.addBody(layout);
			leadAddLayout.addBottomControls(createButtonControls());
			setLayout(leadAddLayout);
		}

		abstract protected HorizontalLayout createButtonControls();

		/*
		 * Override to get control over where fields are placed.
		 */
		@Override
		protected void attachField(Object propertyId, Field field) {
			informationLayout.addComponent(propertyId.equals("firstname"),
					field, "First Name", 0, 0);
			informationLayout.addComponent(propertyId.equals("lastname"),
					field, "Last Name", 0, 1);
			informationLayout.addComponent(propertyId.equals("title"), field,
					"Title", 0, 2);
			informationLayout.addComponent(propertyId.equals("department"),
					field, "Department", 0, 3);
			informationLayout.addComponent(propertyId.equals("accountname"),
					field, "Account Name", 0, 4);
			informationLayout.addComponent(propertyId.equals("source"), field,
					"Lead Source", 0, 5);
			informationLayout.addComponent(propertyId.equals("industry"),
					field, "Industry", 0, 6);

			informationLayout.addComponent(propertyId.equals("officephone"),
					field, "Office Phone", 1, 0);
			informationLayout.addComponent(propertyId.equals("mobile"), field,
					"Mobile", 1, 1);
			informationLayout.addComponent(propertyId.equals("otherphone"),
					field, "Other Phone", 1, 2);
			informationLayout.addComponent(propertyId.equals("fax"), field,
					"Fax", 1, 3);
			informationLayout.addComponent(propertyId.equals("website"), field,
					"Web Site", 1, 4);
			informationLayout.addComponent(propertyId.equals("status"), field,
					"Status", 1, 5);
			informationLayout.addComponent(propertyId.equals("assignuser"),
					field, "Assigned User", 1, 6);

			addressLayout.addComponent(propertyId.equals("primaddress"), field,
					"Address", 0, 0);
			addressLayout.addComponent(propertyId.equals("primcity"), field,
					"City", 0, 1);
			addressLayout.addComponent(propertyId.equals("primstate"), field,
					"State", 0, 2);
			addressLayout.addComponent(propertyId.equals("primpostalcode"),
					field, "Postal Code", 0, 3);
			addressLayout.addComponent(propertyId.equals("primcountry"), field,
					"Country", 0, 4);

			addressLayout.addComponent(propertyId.equals("otheraddress"),
					field, "Other Address", 1, 0);
			addressLayout.addComponent(propertyId.equals("othercity"), field,
					"Other City", 1, 1);
			addressLayout.addComponent(propertyId.equals("otherstate"), field,
					"Other State", 1, 2);
			addressLayout.addComponent(propertyId.equals("otherpostalcode"),
					field, "Other Postal Code", 1, 3);
			addressLayout.addComponent(propertyId.equals("othercountry"),
					field, "Other Country", 1, 4);

			descLayout.addComponent(propertyId.equals("description"), field,
					"Description", 0, 0);
		}
	}

}
