/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.mobile.module.crm.view.account;

import com.esofthead.mycollab.mobile.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.mobile.ui.DefaultFormViewFieldFactory.FormViewField;
import com.esofthead.mycollab.mobile.ui.GenericBeanForm;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.vaadin.ui.Field;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class AccountReadFormFieldFactory extends
		AbstractBeanFieldGroupViewFieldFactory<SimpleAccount> {
	private static final long serialVersionUID = 1L;

	public AccountReadFormFieldFactory(GenericBeanForm<SimpleAccount> form) {
		super(form);
	}

	@Override
	protected Field<?> onCreateField(Object propertyId) {
		if (propertyId.equals("email")) {
			return new FormViewField(
					attachForm.getBean().getEmail());
		} else if (propertyId.equals("assignuser")) {
			return new FormViewField(attachForm
							.getBean().getAssignUserFullName());
		} else if (propertyId.equals("website")) {
			return new FormViewField(
					attachForm.getBean().getWebsite());
		}

		return null;
	}

}
