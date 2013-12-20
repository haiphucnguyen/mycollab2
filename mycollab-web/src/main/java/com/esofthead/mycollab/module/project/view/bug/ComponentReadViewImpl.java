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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ui.components.AbstractPreviewItemComp;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class ComponentReadViewImpl extends
		AbstractPreviewItemComp<SimpleComponent> implements ComponentReadView {

	private static final long serialVersionUID = 1L;

	public ComponentReadViewImpl() {
		super(MyCollabResource.newResource("icons/22/project/component.png"));
	}

	@Override
	public SimpleComponent getItem() {
		return this.beanItem;
	}

	@Override
	public HasPreviewFormHandlers<SimpleComponent> getPreviewFormHandlers() {
		return this.previewForm;
	}

	@Override
	protected String initFormTitle() {
		return beanItem.getComponentname();
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new ComponentFormLayoutFactory();
	}

	@Override
	protected void initRelatedComponents() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onPreviewItem() {

	}

	@Override
	protected AdvancedPreviewBeanForm<SimpleComponent> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleComponent>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void showHistory() {
				final ComponentHistoryLogWindow historyLog = new ComponentHistoryLogWindow(
						ModuleNameConstants.PRJ, ProjectContants.BUG_COMPONENT,
						beanItem.getId());
				UI.getCurrent().addWindow(historyLog);
			}
		};
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<SimpleComponent> initBeanFormFieldFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ComponentContainer createButtonControls() {
		ProjectPreviewFormControlsGenerator<SimpleComponent> componentPreviewForm = new ProjectPreviewFormControlsGenerator<SimpleComponent>(
				previewForm);
		final HorizontalLayout topPanel = componentPreviewForm
				.createButtonControls(ProjectRolePermissionCollections.COMPONENTS);

		return topPanel;
	}

	@Override
	protected ComponentContainer createBottomPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	protected class ComponentFormFieldLayout extends
			AbstractBeanFieldGroupViewFieldFactory<SimpleComponent> {

		private static final long serialVersionUID = 1L;

		public ComponentFormFieldLayout(GenericBeanForm<SimpleComponent> form) {
			super(form);
		}

		@Override
		protected Field<?> onCreateField(final Object propertyId) {
			if (propertyId.equals("userlead")) {
				return new ProjectUserFormLinkField(beanItem.getUserlead(),
						beanItem.getUserLeadAvatarId(),
						beanItem.getUserLeadFullName());
			}
			return null;
		}
	}

	@Override
	public ComponentContainer getWidget() {
		return this;
	}

	@Override
	public void addViewListener(
			ApplicationEventListener<? extends ApplicationEvent> listener) {

	}

}
