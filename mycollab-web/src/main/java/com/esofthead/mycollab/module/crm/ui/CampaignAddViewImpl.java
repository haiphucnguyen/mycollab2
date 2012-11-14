package com.esofthead.mycollab.module.crm.ui;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.ui.components.CampaignStatusComboBox;
import com.esofthead.mycollab.module.crm.ui.components.CampaignTypeComboBox;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
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
import com.vaadin.ui.themes.Reindeer;

@Component
public class CampaignAddViewImpl extends AbstractView implements
		CampaignAddView {
	private static final long serialVersionUID = 1L;

	@Override
	protected void initializeLayout() {
	}

	@Override
	public void addNewItem() {
		this.removeAllComponents();
		Form formItem = AppContext.getSpringBean(EditForm.class);
		formItem.setItemDataSource(new BeanItem<Campaign>(new Campaign()));
		this.addComponent(formItem);
	}

	@Override
	public void editItem(Campaign account) {
		this.removeAllComponents();
		Form formItem = AppContext.getSpringBean(EditForm.class);
		formItem.setItemDataSource(new BeanItem<Campaign>(account));
		this.addComponent(formItem);
	}

	@Override
	public void viewItem(Campaign item) {
		this.removeAllComponents();
		Form formItem = AppContext.getSpringBean(ViewForm.class);
		formItem.setItemDataSource(new BeanItem<Campaign>(item));
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

				if ("type".equals(propertyId)) {
					CampaignTypeComboBox typeCombo = AppContext
							.getSpringBean(CampaignTypeComboBox.class);
					typeCombo.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
					return typeCombo;
				} else if ("status".equals(propertyId)) {
					CampaignStatusComboBox statusCombo = AppContext
							.getSpringBean(CampaignStatusComboBox.class);
					statusCombo.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
					return statusCombo;
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
				Campaign item = ((BeanItem<Campaign>) EditForm.this
						.getItemDataSource()).getBean();
				if (caption.equals(SAVE_ACTION)) {
					if (validateForm(item)) {
						eventBus.fireEvent(new CampaignEvent.Save(this, item));
					}
				} else if (caption.equals(CANCEL_ACTION)) {
					eventBus.fireEvent(new CampaignEvent.GotoList(this, item));
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
				Campaign item = ((BeanItem<Campaign>) ViewForm.this
						.getItemDataSource()).getBean();
				if (caption.equals(EDIT_ACTION)) {
					eventBus.fireEvent(new CampaignEvent.GotoEdit(this, item));
				} else if (caption.equals(CANCEL_ACTION)) {
					eventBus.fireEvent(new CampaignEvent.GotoList(this, item));
				}
			}
		}

	}

	public static abstract class GenericForm extends AdvancedBeanForm<Campaign> {
		private static final long serialVersionUID = 1L;

		protected GridFormLayoutHelper informationLayout;

		protected GridFormLayoutHelper campaignGoal;

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

			campaignGoal = new GridFormLayoutHelper(2, 4);
			Label addressHeader = new Label("Address Information");
			addressHeader.setStyleName(Reindeer.LABEL_H2);
			layout.addComponent(addressHeader);
			layout.addComponent(campaignGoal.getLayout());

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
			informationLayout.addComponent(propertyId.equals("campaignname"),
					field, "Name", 0, 0);
			informationLayout.addComponent(propertyId.equals("startdate"),
					field, "Start Date", 0, 1);
			informationLayout.addComponent(propertyId.equals("enddate"), field,
					"End Date", 0, 2);
			informationLayout.addComponent(propertyId.equals("status"), field,
					"Status", 1, 0);
			informationLayout.addComponent(propertyId.equals("type"), field,
					"Type", 1, 1);

			campaignGoal.addComponent(propertyId.equals("currencyid"), field,
					"Currency", 0, 0);
			campaignGoal.addComponent(propertyId.equals("budget"), field,
					"Budget", 1, 0);
			campaignGoal.addComponent(propertyId.equals("expectedcost"), field,
					"Expected Cost", 0, 1);
			campaignGoal.addComponent(propertyId.equals("actualcost"), field,
					"Actual Cost", 1, 1);
			campaignGoal.addComponent(propertyId.equals("expectedrevenue"),
					field, "Expected Revenue", 0, 2);

			descriptionLayout.addComponent(propertyId.equals("description"),
					field, "Description", 0, 0);

		}
	}

}
