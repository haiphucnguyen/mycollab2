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
package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.server.Resource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 * @param <B>
 */
public abstract class AbstractEditItemComp<B> extends AbstractPageView
implements IFormAddView<B> {
	private static final long serialVersionUID = 1L;

	protected B beanItem;
	protected AdvancedEditBeanForm<B> editForm;

	public AbstractEditItemComp() {
		super();
		this.editForm = new AdvancedEditBeanForm<B>();
		this.addComponent(this.editForm);
		//this.setMargin(false);
	}

	@Override
	public void editItem(final B item) {
		this.beanItem = item;
		this.editForm.setFormLayoutFactory(new FormLayoutFactory());
		this.editForm.setBeanFormFieldFactory(initBeanFormFieldFactory());
		this.editForm.setBean(item);
	}

	@Override
	public HasEditFormHandlers<B> getEditFormHandlers() {
		return this.editForm;
	}

	class FormLayoutFactory implements IFormLayoutFactory {
		private static final long serialVersionUID = 1L;

		private IFormLayoutFactory informationLayout;

		@Override
		public Layout getLayout() {
			final AddViewLayout formAddLayout = new AddViewLayout(
					initFormHeader(), initFormIconResource());

			final ComponentContainer topLayout = createButtonControls();
			if (topLayout != null) {
				formAddLayout.addHeaderRight(topLayout);
			}

			formAddLayout.setTitle(initFormTitle());

			informationLayout = initFormLayoutFactory();

			formAddLayout.addBody(informationLayout.getLayout());

			final ComponentContainer bottomPanel = createBottomPanel();
			if (bottomPanel != null) {
				formAddLayout.addBottomControls(bottomPanel);
			}

			return formAddLayout;
		}

		@Override
		public boolean attachField(Object propertyId, Field<?> field) {
			return informationLayout.attachField(propertyId, field);
		}
	}

	protected ComponentContainer createBottomPanel() {
		return null;
	}

	abstract protected String initFormHeader();

	abstract protected String initFormTitle();

	abstract protected Resource initFormIconResource();

	abstract protected ComponentContainer createButtonControls();

	abstract protected AdvancedEditBeanForm<B> initPreviewForm();

	abstract protected IFormLayoutFactory initFormLayoutFactory();

	abstract protected AbstractBeanFieldGroupEditFieldFactory<B> initBeanFormFieldFactory();
}
