package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MassUpdateWindow;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class MassUpdateContactWindow extends MassUpdateWindow<Contact> {
	private static final long serialVersionUID = 1L;

	private final Contact contact;
	private final EditForm updateForm;
	private final ReadViewLayout contactAddLayout;
	private final VerticalLayout layout;

	public MassUpdateContactWindow(final String title,
			final ContactListPresenter presenter) {
		super(title, presenter);

		this.setWidth("1000px");

		this.setIcon(MyCollabResource
				.newResource("icons/18/crm/contact.png"));

		this.contactAddLayout = new ReadViewLayout(null, false);

		this.contact = new Contact();

		this.layout = this.getLayout();

		this.updateForm = new EditForm();

		this.updateForm.setItemDataSource(new BeanItem<Contact>(this.contact));

		this.contactAddLayout.addBody(this.updateForm);

		this.addComponent(this.contactAddLayout);
	}

	private class EditForm extends AdvancedEditBeanForm<Contact> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new MassUpdateContactFormLayoutFactory());
			this.setFormFieldFactory(new ContactEditFormFieldFactory(
					MassUpdateContactWindow.this.contact));
			super.setItemDataSource(newDataSource);
		}

		private class MassUpdateContactFormLayoutFactory implements
				IFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			private GridFormLayoutHelper informationLayout;
			private GridFormLayoutHelper addressLayout;

			@Override
			public Layout getLayout() {
				final VerticalLayout formLayout = new VerticalLayout();

				final Label organizationHeader = new Label(
						"Contact Information");
				organizationHeader.setStyleName("h2");
				formLayout.addComponent(organizationHeader);

				this.informationLayout = new GridFormLayoutHelper(2, 6, "100%",
						"167px", Alignment.MIDDLE_LEFT);

				this.informationLayout.getLayout().setWidth("100%");
				this.informationLayout.getLayout().setMargin(false);
				this.informationLayout.getLayout().setSpacing(false);
				this.informationLayout.getLayout().addStyleName(
						"colored-gridlayout");
				formLayout.addComponent(this.informationLayout.getLayout());

				this.addressLayout = new GridFormLayoutHelper(2, 6, "100%",
						"167px", Alignment.MIDDLE_LEFT);
				final Label addressHeader = new Label("Address Information");
				addressHeader.setStyleName("h2");
				formLayout.addComponent(addressHeader);
				this.addressLayout.getLayout().setWidth("100%");
				this.addressLayout.getLayout().setMargin(false);
				this.addressLayout.getLayout().setSpacing(false);
				this.addressLayout.getLayout().addStyleName(
						"colored-gridlayout");
				formLayout.addComponent(this.addressLayout.getLayout());

				formLayout.addComponent(MassUpdateContactWindow.this.layout);

				return formLayout;
			}

			@Override
			public void attachField(final Object propertyId, final Field field) {
				if (propertyId.equals("accountId")) {
					this.informationLayout.addComponent(field, "Account", 0, 0);
				} else if (propertyId.equals("title")) {
					this.informationLayout.addComponent(field, "Title", 1, 0);
				} else if (propertyId.equals("leadsource")) {
					this.informationLayout.addComponent(field, "Leader Source",
							0, 1);
				} else if (propertyId.equals("assignuser")) {
					this.informationLayout.addComponent(field, "Assign User",
							1, 1);
				} else if (propertyId.equals("iscallable")) {
					this.informationLayout.addComponent(field, "Callable", 0,
							2, 2, "100%", Alignment.TOP_LEFT);
				} else if (propertyId.equals("primcity")) {
					this.addressLayout.addComponent(field, "City", 0, 0);
				} else if (propertyId.equals("primstate")) {
					this.addressLayout.addComponent(field, "State", 1, 0);
				} else if (propertyId.equals("primpostalcode")) {
					this.addressLayout.addComponent(field, "Postal Code", 0, 1);
				} else if (propertyId.equals("primcountry")) {
					this.addressLayout.addComponent(field, "Country", 1, 1);
				} else if (propertyId.equals("othercity")) {
					this.addressLayout.addComponent(field, "Other City", 0, 2);
				} else if (propertyId.equals("otherstate")) {
					this.addressLayout.addComponent(field, "Other State", 1, 2);
				} else if (propertyId.equals("otherpostalcode")) {
					this.addressLayout.addComponent(field, "Other Postal Code",
							0, 3);
				} else if (propertyId.equals("othercountry")) {
					this.addressLayout.addComponent(field, "Other Country", 1,
							3);
				}
			}
		}
	}

	@Override
	protected Contact getItem() {
		return this.contact;
	}
}
