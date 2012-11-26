package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.ui.components.AccountSelectionField;
import com.esofthead.mycollab.module.crm.ui.components.CampaignSelectionField;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.FormAddView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

public class OpportunityAddViewImpl extends FormAddView<Opportunity> implements
		OpportunityAddView {
	private static final long serialVersionUID = 1L;

	private EditForm editForm;

	public OpportunityAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void addNewItem() {
		editForm.setItemDataSource(new BeanItem<Opportunity>(new Opportunity()));
	}

	@Override
	public void editItem(Opportunity item) {
		editForm.setItemDataSource(new BeanItem<Opportunity>(item));
	}

	public static class EditForm extends AdvancedEditBeanForm<Opportunity> {
		private static final long serialVersionUID = 1L;

		public EditForm() {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
		}

		class FormLayoutFactory extends OpportunityFormLayoutFactory {

			@Override
			protected Layout createButtonControls() {
				return (new EditFormControlsGenerator<Opportunity>(
						EditForm.this)).createButtonControls();
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {
			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {
				if (propertyId.equals("campaignid")) {
					CampaignSelectionField campaignField = new CampaignSelectionField();
					return campaignField;
				} else if (propertyId.equals("accountid")) {
					AccountSelectionField accountField = new AccountSelectionField();
					return accountField;
				}
				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<Opportunity> getEditFormHandlers() {
		return editForm;
	}
}
