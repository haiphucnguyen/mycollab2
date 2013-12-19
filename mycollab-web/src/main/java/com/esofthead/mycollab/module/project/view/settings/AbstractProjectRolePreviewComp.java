package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.module.project.ui.components.AbstractPreviewItemComp;
import com.esofthead.mycollab.security.AccessPermissionFlag;
import com.esofthead.mycollab.security.PermissionMap;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
abstract class AbstractProjectRolePreviewComp extends
		AbstractPreviewItemComp<SimpleProjectRole> {
	private static final long serialVersionUID = 1L;

	protected GridFormLayoutHelper projectFormHelper;

	public AbstractProjectRolePreviewComp() {
		super(MyCollabResource.newResource("icons/22/user/group.png"));
	}

	@Override
	protected void initRelatedComponents() {

	}

	@Override
	protected void onPreviewItem() {
		final PermissionMap permissionMap = beanItem.getPermissionMap();
		for (int i = 0; i < ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length; i++) {
			final String permissionPath = ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i];
			projectFormHelper.addComponent(
					new Label(this.getValueFromPerPath(permissionMap,
							permissionPath)), permissionPath, 0, i);
		}

		projectFormHelper.getLayout().setWidth("100%");
		projectFormHelper.getLayout().setMargin(false);
		projectFormHelper.getLayout().addStyleName("colored-gridlayout");
	}

	@Override
	protected String initFormTitle() {
		return beanItem.getRolename();
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new ProjectRoleFormLayoutFactory();
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<SimpleProjectRole> initBeanFormFieldFactory() {
		return new AbstractBeanFieldGroupViewFieldFactory<SimpleProjectRole>(
				previewForm) {
			private static final long serialVersionUID = 1L;

			@Override
			protected Field<?> onCreateField(Object propertyId) {
				return null;
			}
		};
	}

	private String getValueFromPerPath(final PermissionMap permissionMap,
			final String permissionItem) {
		final Integer perVal = permissionMap.get(permissionItem);
		if (perVal == null) {
			return "No Access";
		} else {
			return AccessPermissionFlag.toString(perVal);
		}
	}

}
