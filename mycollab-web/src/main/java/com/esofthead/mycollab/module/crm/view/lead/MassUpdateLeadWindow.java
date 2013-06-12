package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MassUpdateWindow;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

public class MassUpdateLeadWindow extends MassUpdateWindow<Lead> {
	private static final long serialVersionUID = 1L;
	private Lead lead;
	private final EditForm updateForm;
	private ReadViewLayout leadAddLayout;
	private VerticalLayout layout;

	public MassUpdateLeadWindow(String title,LeadListPresenter presenter) {
		super(title, presenter);
		
		this.setWidth("1000px");
		
		this.setIcon(new ThemeResource("icons/18/crm/lead.png"));
		
		leadAddLayout = new ReadViewLayout(null);

		lead = new Lead();

		layout = getLayout();

		updateForm = new EditForm();

		updateForm.setItemDataSource(new BeanItem<Lead>(lead));

		leadAddLayout.addComponent(updateForm);

		this.addComponent(leadAddLayout);
	}

	private class EditForm extends AdvancedEditBeanForm<Contact> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			setFormLayoutFactory(new MassUpdateContactFormLayoutFactory());
			setFormFieldFactory(new LeadEditFormFieldFactory(lead));
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
				Label leadMoreInfo = new Label("More Information");
				leadMoreInfo.setStyleName("h2");
				formLayout.addComponent(leadMoreInfo);
				addressLayout.getLayout().setWidth("100%");
				addressLayout.getLayout().setMargin(false);
				addressLayout.getLayout().setSpacing(false);
				addressLayout.getLayout().addStyleName("colored-gridlayout");
				formLayout.addComponent(addressLayout.getLayout());

				formLayout.addComponent(layout);
				formLayout.addStyleName("v-csslayout v-csslayout-readview-layout-body readview-layout-body");

				return formLayout;
			}
//			 Title, Account Name, Lead Source, Industry,  Status, Assign User,
			//primary/other city,  primary/other state, primary/other  postal code, primary/other country
			@Override
			public void attachField(final Object propertyId, final Field field) {
			
	            informationLayout.addComponent(propertyId.equals("title"), field,
	                    "Title", 0, 0);
	            informationLayout.addComponent(propertyId.equals("accountname"), field,
	                    "Account Name", 1, 0);
	            informationLayout.addComponent(propertyId.equals("source"), field,
	                    "Lead Source", 0, 1);
	            informationLayout.addComponent(propertyId.equals("industry"), field,
	                    "Industry", 1, 1);

	            informationLayout.addComponent(propertyId.equals("status"), field,
	                    "Status", 0, 2);
	            informationLayout.addComponent(propertyId.equals("assignuser"), field,
	                    "Assigned User", 1, 2);

	            addressLayout.addComponent(propertyId.equals("primcity"), field,
	                    "City", 0, 0);
	            addressLayout.addComponent(propertyId.equals("primstate"), field,
	                    "State", 1, 0);
	            addressLayout.addComponent(propertyId.equals("primpostalcode"), field,
	                    "Postal Code", 0, 1);
	            addressLayout.addComponent(propertyId.equals("primcountry"), field,
	                    "Country", 1, 1);

	            addressLayout.addComponent(propertyId.equals("othercity"), field,
	                    "Other City", 0, 2);
	            addressLayout.addComponent(propertyId.equals("otherstate"), field,
	                    "Other State", 1, 2);
	            addressLayout.addComponent(propertyId.equals("otherpostalcode"), field,
	                    "Other Postal Code", 0, 3);
	            addressLayout.addComponent(propertyId.equals("othercountry"), field,
	                    "Other Country", 1, 3);
			}
		}
	}

	@Override
	protected Lead getItem() {
		return lead;
	}
}
