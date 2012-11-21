package com.esofthead.mycollab.module.crm.view;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.ui.components.AccountSelectionField;
import com.esofthead.mycollab.module.crm.ui.components.AddViewLayout;
import com.esofthead.mycollab.module.crm.ui.components.CampaignSelectionField;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
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

public class OpportunityAddViewImpl extends AbstractView implements
		OpportunityAddView {
	private static final long serialVersionUID = 1L;

	@Override
	public void addNewItem() {
		this.removeAllComponents();
		Form formItem = AppContext.getSpringBean(EditForm.class);
		formItem.setItemDataSource(new BeanItem<Opportunity>(new Opportunity()));
		this.addComponent(formItem);
	}

	@Override
	public void editItem(Opportunity account) {
		this.removeAllComponents();
		Form formItem = AppContext.getSpringBean(EditForm.class);
		formItem.setItemDataSource(new BeanItem<Opportunity>(account));
		this.addComponent(formItem);
	}

	
	public void viewItem(Opportunity item) {
		this.removeAllComponents();
		Form formItem = AppContext.getSpringBean(ViewForm.class);
		formItem.setItemDataSource(new BeanItem<Opportunity>(item));
		this.addComponent(formItem);
	}

	
	public static class EditForm extends GenericForm {
		private static final long serialVersionUID = 1L;

		@Override
		protected HorizontalLayout createButtonControls() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);
			layout.setStyleName("addNewControl");
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
				if (propertyId.equals("campaignid")) {
					CampaignSelectionField campaignField = AppContext
							.getSpringBean(CampaignSelectionField.class);
					return campaignField;
				} else if (propertyId.equals("accountid")) {
					AccountSelectionField accountField = AppContext
							.getSpringBean(AccountSelectionField.class);
					return accountField;
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
				Opportunity opportunity = ((BeanItem<Opportunity>) EditForm.this
						.getItemDataSource()).getBean();
				if (caption.equals(SAVE_ACTION)) {
					if (validateForm(opportunity)) {
						
					}
				} else if (caption.equals(CANCEL_ACTION)) {
					
				}
			}
		}
	}


	public static class ViewForm extends GenericForm {
		private static final long serialVersionUID = 1L;

		@Override
		protected HorizontalLayout createButtonControls() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);
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
				Opportunity opportunity = ((BeanItem<Opportunity>) ViewForm.this
						.getItemDataSource()).getBean();
				if (caption.equals(EDIT_ACTION)) {
					
				} else if (caption.equals(CANCEL_ACTION)) {
					
				}
			}
		}

	}

	public static abstract class GenericForm extends
			AdvancedEditBeanForm<Opportunity> {
		private static final long serialVersionUID = 1L;

		protected GridFormLayoutHelper informationLayout;

		protected GridFormLayoutHelper descriptionLayout;

		public GenericForm() {
			super();
			AddViewLayout opportunityAddLayout = new AddViewLayout(
					"Opportunity");
			opportunityAddLayout.addTopControls(createButtonControls());

			VerticalLayout layout = new VerticalLayout();
			layout.setSpacing(true);

			Label organizationHeader = new Label("Account Information");
			organizationHeader.setStyleName("h2");
			layout.addComponent(organizationHeader);

			informationLayout = new GridFormLayoutHelper(2, 6);
			informationLayout.getLayout().setWidth("900px");
			layout.addComponent(informationLayout.getLayout());

			descriptionLayout = new GridFormLayoutHelper(2, 1);
			descriptionLayout.getLayout().setWidth("900px");
			Label descHeader = new Label("Description");
			descHeader.setStyleName("h2");
			layout.addComponent(descHeader);
			layout.addComponent(descriptionLayout.getLayout());

			this.setWriteThrough(true);
			this.setInvalidCommitted(false);

			opportunityAddLayout.addBody(layout);
			opportunityAddLayout.addBottomControls(createButtonControls());
			setLayout(opportunityAddLayout);
		}

		abstract protected HorizontalLayout createButtonControls();

		/*
		 * Override to get control over where fields are placed.
		 */
		@Override
		protected void attachField(Object propertyId, Field field) {
			informationLayout.addComponent(
					propertyId.equals("opportunityname"), field,
					"Opportunity Name", 0, 0);
			informationLayout.addComponent(propertyId.equals("currencyid"),
					field, "Currency", 0, 1);
			informationLayout.addComponent(propertyId.equals("amount"), field,
					"Amount", 0, 2);
			informationLayout.addComponent(propertyId.equals("salesstage"),
					field, "Sales Stage", 0, 3);
			informationLayout.addComponent(propertyId.equals("probability"),
					field, "Probability", 0, 4);
			informationLayout.addComponent(propertyId.equals("nextstep"),
					field, "Next Step", 0, 5);

			informationLayout.addComponent(propertyId.equals("accountid"),
					field, "Account Name", 1, 0);
			informationLayout.addComponent(
					propertyId.equals("expectedcloseddate"), field,
					"Expected Close Date", 1, 1);
			informationLayout.addComponent(
					propertyId.equals("opportunitytype"), field, "Type", 1, 2);
			informationLayout.addComponent(propertyId.equals("source"), field,
					"Source", 1, 3);
			informationLayout.addComponent(propertyId.equals("campaignid"),
					field, "Campaign", 1, 4);

			if (propertyId.equals("description")) {
				descriptionLayout.addComponent(field, "Description", 0, 0);
			}
		}
	}
}
