package com.esofthead.mycollab.mobile.module.project.view.settings;

import com.esofthead.mycollab.mobile.module.project.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.mobile.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.mobile.ui.AbstractPreviewItemComp;
import com.esofthead.mycollab.mobile.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.mobile.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.i18n.ProjectMemberI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.2
 */
@ViewComponent
public class ProjectMemberReadViewImpl extends
		AbstractPreviewItemComp<SimpleProjectMember> implements
		ProjectMemberReadView {

	private static final long serialVersionUID = 364308373821870384L;

	@Override
	protected void afterPreviewItem() {
	}

	@Override
	protected String initFormTitle() {
		return this.beanItem.getDisplayName();
	}

	@Override
	protected AdvancedPreviewBeanForm<SimpleProjectMember> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleProjectMember>();
	}

	@Override
	protected void initRelatedComponents() {
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new ProjectMemberFormLayoutFactory();
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<SimpleProjectMember> initBeanFormFieldFactory() {
		return new ProjectMemberBeanFormFieldFactory(this.previewForm);
	}

	@Override
	protected ComponentContainer createButtonControls() {
		return new ProjectPreviewFormControlsGenerator<SimpleProjectMember>(
				this.previewForm)
				.createButtonControls(
						ProjectPreviewFormControlsGenerator.EDIT_BTN_PRESENTED
								| ProjectPreviewFormControlsGenerator.DELETE_BTN_PRESENTED,
						ProjectRolePermissionCollections.USERS);
	}

	@Override
	protected ComponentContainer createBottomPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	private class ProjectMemberBeanFormFieldFactory extends
			AbstractBeanFieldGroupViewFieldFactory<SimpleProjectMember> {

		public ProjectMemberBeanFormFieldFactory(
				GenericBeanForm<SimpleProjectMember> form) {
			super(form);
		}

		private static final long serialVersionUID = 5269043189285551214L;

		@Override
		protected Field<?> onCreateField(Object propertyId) {
			if (propertyId.equals("memberFullName")) {
				return new DefaultFormViewFieldFactory.FormViewField(
						beanItem.getDisplayName());
			} else if (propertyId.equals("roleName")) {
				String memberRole = "";
				if (beanItem.getIsadmin() != null
						&& beanItem.getIsadmin() == Boolean.TRUE
						|| beanItem.getProjectroleid() == null) {
					memberRole = AppContext
							.getMessage(ProjectMemberI18nEnum.M_FORM_PROJECT_ADMIN);
				} else {
					memberRole = beanItem.getRoleName();
				}
				return new DefaultFormViewFieldFactory.FormViewField(memberRole);
			} else if (propertyId.equals("numOpenBugs")) {
				return new DefaultFormViewFieldFactory.FormViewField(
						beanItem.getNumOpenBugs() + "");
			} else if (propertyId.equals("numOpenTasks")) {
				return new DefaultFormViewFieldFactory.FormViewField(
						beanItem.getNumOpenTasks() + "");
			}
			return null;
		}

	}

	@Override
	public HasPreviewFormHandlers<SimpleProjectMember> getPreviewFormHandlers() {
		return this.previewForm;
	}

}
