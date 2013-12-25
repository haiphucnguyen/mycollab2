/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.ui.components.IndustryComboBox;
import com.esofthead.mycollab.module.user.ui.components.ActiveUserComboBox;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.CompoundCustomField;
import com.esofthead.mycollab.vaadin.ui.CountryComboBox;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.vaadin.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 * @param <B>
 */
class LeadEditFormFieldFactory<B extends Lead> extends
		AbstractBeanFieldGroupEditFieldFactory<B> {
	private static final long serialVersionUID = 1L;

	private LeadFirstNamePrefixField firstNamePrefixField;

	public LeadEditFormFieldFactory(GenericBeanForm<B> form) {
		super(form);

		firstNamePrefixField = new LeadFirstNamePrefixField();
	}

	@Override
	protected Field<?> onCreateField(Object propertyId) {
		if (propertyId.equals("firstname") || propertyId.equals("prefixname")) {
			return firstNamePrefixField;
		} else if (propertyId.equals("primcountry")
				|| propertyId.equals("othercountry")) {
			CountryComboBox otherCountryComboBox = new CountryComboBox();
			return otherCountryComboBox;
		} else if (propertyId.equals("status")) {
			LeadStatusComboBox statusComboBox = new LeadStatusComboBox();
			return statusComboBox;
		} else if (propertyId.equals("industry")) {
			IndustryComboBox industryComboBox = new IndustryComboBox();
			return industryComboBox;
		} else if (propertyId.equals("assignuser")) {
			ActiveUserComboBox userComboBox = new ActiveUserComboBox();
			return userComboBox;
		} else if (propertyId.equals("source")) {
			LeadSourceComboBox statusComboBox = new LeadSourceComboBox();
			return statusComboBox;
		} else if (propertyId.equals("lastname")) {
			TextField tf = new TextField();
			tf.setNullRepresentation("");
			tf.setRequired(true);
			tf.setRequiredError("Last name must not be null");
			return tf;
		} else if (propertyId.equals("description")) {
			TextArea descArea = new TextArea();
			descArea.setNullRepresentation("");
			return descArea;
		}

		return null;
	}

	class LeadFirstNamePrefixField extends CompoundCustomField<Lead> {
		private static final long serialVersionUID = 1L;

		@Override
		protected Component initContent() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setWidth("100%");
			layout.setSpacing(true);

			final PrefixListSelect prefixSelect = new PrefixListSelect();
			prefixSelect.setValue(attachForm.getBean().getPrefixname());
			layout.addComponent(prefixSelect);

			prefixSelect
					.addValueChangeListener(new Property.ValueChangeListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void valueChange(Property.ValueChangeEvent event) {
							attachForm.getBean().setPrefixname(
									(String) prefixSelect.getValue());

						}
					});

			TextField firstnameTxtField = new TextField();
			firstnameTxtField.setWidth("100%");
			firstnameTxtField.setNullRepresentation("");
			layout.addComponent(firstnameTxtField);
			layout.setExpandRatio(firstnameTxtField, 1.0f);

			// binding field group
			fieldGroup.bind(prefixSelect, "prefixname");
			fieldGroup.bind(firstnameTxtField, "firstname");

			return layout;
		}

		@Override
		public Class<? extends Lead> getType() {
			return Lead.class;
		}

	}

	static class PrefixListSelect extends ValueComboBox {

		private static final long serialVersionUID = 1L;

		public PrefixListSelect() {
			super();
			this.setWidth("50px");
			setCaption(null);
			this.loadData(new String[] { "Mr.", "Ms.", "Mrs.", "Dr.", "Prof." });
		}
	}
}
