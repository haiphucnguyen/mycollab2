package com.esofthead.mycollab.module.crm.ui;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.ui.components.AccountSelectionField;
import com.esofthead.mycollab.module.crm.ui.components.CampaignSelectionField;
import com.esofthead.mycollab.module.crm.ui.components.CampaignSelectionWindow;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@Component
public class OpportunityAddViewImpl extends AbstractView implements
		OpportunityAddView {
	private static final long serialVersionUID = 1L;

	@Override
	protected ComponentContainer initMainLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		return layout;
	}

	@Override
	public void addNewItem() {
		compContainer.removeAllComponents();
		Form formItem = AppContext.getSpringBean(EditForm.class);
		formItem.setItemDataSource(new BeanItem<Opportunity>(new Opportunity()));
		compContainer.addComponent(formItem);
	}

	@Override
	public void editItem(Opportunity account) {
		compContainer.removeAllComponents();
		Form formItem = AppContext.getSpringBean(EditForm.class);
		formItem.setItemDataSource(new BeanItem<Opportunity>(account));
		compContainer.addComponent(formItem);
	}

	@Override
	public void viewItem(Opportunity item) {
		compContainer.removeAllComponents();
		Form formItem = AppContext.getSpringBean(ViewForm.class);
		formItem.setItemDataSource(new BeanItem<Opportunity>(item));
		compContainer.addComponent(formItem);
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
						eventBus.fireEvent(new OpportunityEvent.Save(this,
								opportunity));
					}
				} else if (caption.equals(CANCEL_ACTION)) {
					eventBus.fireEvent(new OpportunityEvent.GotoList(this,
							opportunity));
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
					eventBus.fireEvent(new OpportunityEvent.GotoEdit(this,
							opportunity));
				} else if (caption.equals(CANCEL_ACTION)) {
					eventBus.fireEvent(new OpportunityEvent.GotoList(this,
							opportunity));
				}
			}
		}

	}

	public static abstract class GenericForm extends
			AdvancedBeanForm<Opportunity> {
		private static final long serialVersionUID = 1L;

		protected GridFormLayoutHelper informationLayout;

		protected GridFormLayoutHelper descriptionLayout;

		public GenericForm() {
			super();

			VerticalLayout layout = new VerticalLayout();
			layout.setSpacing(true);

			layout.addComponent(createButtonControls());

			Label organizationHeader = new Label("Account Information");
			organizationHeader.setStyleName(Reindeer.LABEL_H2);
			layout.addComponent(organizationHeader);

			informationLayout = new GridFormLayoutHelper(2, 6);
			layout.addComponent(informationLayout.getLayout());

			descriptionLayout = new GridFormLayoutHelper(2, 1);
			Label descHeader = new Label("Description");
			descHeader.setStyleName(Reindeer.LABEL_H2);
			layout.addComponent(descHeader);
			layout.addComponent(descriptionLayout.getLayout());

			this.setWriteThrough(true);
			this.setInvalidCommitted(false);

			layout.addComponent(createButtonControls());
			setLayout(layout);
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
		}
	}
}
