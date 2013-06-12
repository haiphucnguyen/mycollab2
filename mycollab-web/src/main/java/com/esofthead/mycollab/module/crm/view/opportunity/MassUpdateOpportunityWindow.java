package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
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

public class MassUpdateOpportunityWindow extends MassUpdateWindow<Opportunity>{
	private static final long serialVersionUID = 1L;

	private Opportunity opportunity;
	private final EditForm updateForm;
	private ReadViewLayout opportunityAddLayout;
	private VerticalLayout layout;

	public MassUpdateOpportunityWindow(String title, OpportunityListPresenter presenter) {
		super(title, presenter);
		this.setWidth("1000px");
		
		this.setIcon(new ThemeResource("icons/18/crm/opportunity.png"));
		
		opportunityAddLayout = new ReadViewLayout(null);

		opportunity = new Opportunity();

		layout = getLayout();

		updateForm = new EditForm();

		updateForm.setItemDataSource(new BeanItem<Opportunity>(opportunity));

		opportunityAddLayout.addComponent(updateForm);

		this.addComponent(opportunityAddLayout);
	}

	private class EditForm extends AdvancedEditBeanForm<Account> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			setFormLayoutFactory(new MassUpdateAccountFormLayoutFactory());
			setFormFieldFactory(new OpportunityEditFormFieldFactory(opportunity));
			super.setItemDataSource(newDataSource);
		}

		private class MassUpdateAccountFormLayoutFactory implements
				IFormLayoutFactory {
			private static final long serialVersionUID = 1L;

			private GridFormLayoutHelper informationLayout;

			@Override
			public Layout getLayout() {
				VerticalLayout formLayout = new VerticalLayout();

				Label organizationHeader = new Label("Account Information");
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

				formLayout.addComponent(layout);
				formLayout.addStyleName("v-csslayout v-csslayout-readview-layout-body readview-layout-body");

				return formLayout;
			}

			@Override
			public void attachField(Object propertyId, Field field) {
				if (propertyId.equals("opportunityname")) {
	                informationLayout.addComponent(field, "Opportunity Name", 0, 0);
	            } else if (propertyId.equals("currencyid")) {
	                informationLayout.addComponent(field, "Currency", 0, 1);
	            } else if (propertyId.equals("amount")) {
	                informationLayout.addComponent(field, "Amount", 0, 2);
	            } else if (propertyId.equals("salesstage")) {
	                informationLayout.addComponent(field, "Sales Stage", 0, 3);
	            } else if (propertyId.equals("probability")) {
	                informationLayout.addComponent(field, "Probability (%)", 0, 4);
	            } else if (propertyId.equals("nextstep")) {
	                informationLayout.addComponent(field, "Next Step", 0, 5);
	            } else if (propertyId.equals("accountid")) {
	                informationLayout.addComponent(field, "Account Name", 1, 0);
	            } else if (propertyId.equals("expectedcloseddate")) {
	                informationLayout.addComponent(field, "Expected Close Date", 1, 1);
	            } else if (propertyId.equals("opportunitytype")) {
	                informationLayout.addComponent(field, "Type", 1, 2);
	            } else if (propertyId.equals("source")) {
	                informationLayout.addComponent(field, "Lead Source", 1, 3);
	            } else if (propertyId.equals("campaignid")) {
	                informationLayout.addComponent(field, "Campaign", 1, 4);
	            } else if (propertyId.equals("assignuser"))
	                informationLayout.addComponent(field, "Assigned User", 1, 5);
	            }
			}
	}

	@Override
	protected Opportunity getItem() {
		return opportunity;
	}
}
