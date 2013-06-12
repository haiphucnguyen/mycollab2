package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.localization.AccountI18nEnum;
import com.esofthead.mycollab.module.crm.view.account.AccountEditFormFieldFactory;
import com.esofthead.mycollab.module.crm.view.account.AccountListPresenter;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MassUpdateWindow;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class MassUpdateContactWindow extends MassUpdateWindow<Contact> {
	private static final long serialVersionUID = 1L;

	private Contact contact;
	private final EditForm updateForm;
	private ReadViewLayout contactAddLayout;
	private VerticalLayout layout;

	public MassUpdateContactWindow(String title, ContactListPresenter presenter) {
		super(title, presenter);
		
		this.setWidth("1000px");
		
		this.setIcon(new ThemeResource("icons/18/crm/contact.png"));
		
		contactAddLayout = new ReadViewLayout(null);

		contact = new Contact();

		layout = getLayout();

		updateForm = new EditForm();

		updateForm.setItemDataSource(new BeanItem<Contact>(contact));

		contactAddLayout.addComponent(updateForm);

		this.addComponent(contactAddLayout);
	}

	private class EditForm extends AdvancedEditBeanForm<Contact> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			setFormLayoutFactory(new MassUpdateContactFormLayoutFactory());
			setFormFieldFactory(new ContactEditFormFieldFactory(contact));
			super.setItemDataSource(newDataSource);
		}

		private class MassUpdateContactFormLayoutFactory implements
				IFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			private GridFormLayoutHelper informationLayout;
			private GridFormLayoutHelper addressLayout;

			@Override
			public Layout getLayout() {
				VerticalLayout formLayout = new VerticalLayout();

				Label organizationHeader = new Label("Contact Information");
				organizationHeader.setStyleName("h2");
				formLayout.addComponent(organizationHeader);

				informationLayout = new GridFormLayoutHelper(2, 6, "100%",
						"167px", Alignment.MIDDLE_LEFT);

				informationLayout.getLayout().setWidth("100%");
				informationLayout.getLayout().setMargin(false);
				informationLayout.getLayout().setSpacing(false);
				informationLayout.getLayout()
						.addStyleName("colored-gridlayout");
				formLayout.addComponent(informationLayout.getLayout());

				addressLayout = new GridFormLayoutHelper(2, 6, "100%", "167px",
						Alignment.MIDDLE_LEFT);
				Label addressHeader = new Label("Address Information");
				addressHeader.setStyleName("h2");
				formLayout.addComponent(addressHeader);
				addressLayout.getLayout().setWidth("100%");
				addressLayout.getLayout().setMargin(false);
				addressLayout.getLayout().setSpacing(false);
				addressLayout.getLayout().addStyleName("colored-gridlayout");
				formLayout.addComponent(addressLayout.getLayout());

				formLayout.addComponent(layout);
				formLayout.addStyleName("v-csslayout v-csslayout-readview-layout-body readview-layout-body");

				return formLayout;
			}

			@Override
			public void attachField(final Object propertyId, final Field field) {
				if (propertyId.equals("accountId")) {
					informationLayout.addComponent(field, "Account", 0, 1);
				} else if (propertyId.equals("title")) {
					informationLayout.addComponent(field, "Title", 1, 1);
				} else if (propertyId.equals("leadsource")) {
					informationLayout.addComponent(field, "Leader Source", 0, 2);
				} else if (propertyId.equals("iscallable")) {
					informationLayout.addComponent(field, "Callable", 1, 2);
				} else if (propertyId.equals("assignuser")) {
					informationLayout.addComponent(field, "Assign User", 0, 3);
				}else if (propertyId.equals("primcity")) {
					addressLayout.addComponent(field, "City", 0, 1);
				} else if (propertyId.equals("primstate")) {
					addressLayout.addComponent(field, "State", 1, 1);
				} else if (propertyId.equals("primpostalcode")) {
					addressLayout.addComponent(field, "Postal Code", 0, 2);
				} else if (propertyId.equals("primcountry")) {
					addressLayout.addComponent(field, "Country", 1, 2);
				} else if (propertyId.equals("othercity")) {
					addressLayout.addComponent(field, "Other City", 0, 3);
				} else if (propertyId.equals("otherstate")) {
					addressLayout.addComponent(field, "Other State", 1, 3);
				} else if (propertyId.equals("otherpostalcode")) {
					addressLayout.addComponent(field, "Other Postal Code", 0, 4);
				} else if (propertyId.equals("othercountry")) {
					addressLayout.addComponent(field, "Other Country", 1, 4);
				}
			}
		}
	}

	@Override
	protected Contact getItem() {
		return contact;
	}
}
