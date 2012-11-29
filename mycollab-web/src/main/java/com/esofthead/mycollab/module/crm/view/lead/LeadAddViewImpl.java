package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.ui.components.IndustryComboBox;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.FormAddView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.CountryComboBox;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

public class LeadAddViewImpl extends FormAddView<Lead> implements LeadAddView {

	private static final long serialVersionUID = 1L;

	private EditForm editForm;

	public LeadAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	protected void onNewItem() {
		editForm.setItemDataSource(new BeanItem<Lead>(new Lead()));
	}

	@Override
	protected void onEditItem(Lead item) {
		editForm.setItemDataSource(new BeanItem<Lead>(item));
	}

	public class EditForm extends AdvancedEditBeanForm<Lead> {
		private static final long serialVersionUID = 1L;

		public EditForm() {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
		}

		class FormLayoutFactory extends LeadFormLayoutFactory {

			@Override
			protected Layout createButtonControls() {
				return (new EditFormControlsGenerator<Lead>(EditForm.this))
						.createButtonControls();
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {
			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {

				if (propertyId.equals("primcountry")
						|| propertyId.equals("othercountry")) {
					CountryComboBox countryComboBox = new CountryComboBox();
					return countryComboBox;
				} else if (propertyId.equals("status")) {
					LeadStatusComboBox statusComboBox = new LeadStatusComboBox();
					return statusComboBox;
				} else if (propertyId.equals("industry")) {
					IndustryComboBox industryComboBox = new IndustryComboBox();
					return industryComboBox;
				} else if (propertyId.equals("assignuser")) {
					UserComboBox userComboBox = new UserComboBox();
					return userComboBox;
				} else if (propertyId.equals("source")) {
					LeadSourceComboBox statusComboBox = new LeadSourceComboBox();
					return statusComboBox;
				}

				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<Lead> getEditFormHandlers() {
		return editForm;
	}

}
