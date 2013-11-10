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
package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.BeanItemCustomExt;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

@ViewComponent
public class AccountAddViewImpl extends AbstractView implements AccountAddView {
	private static final long serialVersionUID = 1L;
	private final EditForm editForm;

	private SimpleAccount account;

	public AccountAddViewImpl() {
		super();
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
	}

	@Override
	public void editItem(final SimpleAccount account) {
		this.account = account;
		this.editForm.setItemDataSource(new BeanItemCustomExt<SimpleAccount>(
				account));
	}

	@Override
	public HasEditFormHandlers<SimpleAccount> getEditFormHandlers() {
		return this.editForm;
	}

	private class EditForm extends AdvancedEditBeanForm<SimpleAccount> {

		class FormLayoutFactory extends AccountFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(
						(AccountAddViewImpl.this.account.getId() == null) ? "Create Account"
								: AccountAddViewImpl.this.account
										.getAccountname());
			}

			@Override
			protected Layout createBottomPanel() {
				return this.createButtonControls();
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<SimpleAccount>(
						EditForm.this)).createButtonControls();
				controlButtons.setSizeUndefined();
				controlPanel.addComponent(controlButtons);
				controlPanel.setWidth("100%");
				controlPanel.setComponentAlignment(controlButtons,
						Alignment.MIDDLE_CENTER);
				return controlPanel;
			}

			@Override
			protected Layout createTopPanel() {
				return this.createButtonControls();
			}
		}

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new AccountEditFormFieldFactory(
					AccountAddViewImpl.this.account));
			super.setItemDataSource(newDataSource);
		}
	}
}
