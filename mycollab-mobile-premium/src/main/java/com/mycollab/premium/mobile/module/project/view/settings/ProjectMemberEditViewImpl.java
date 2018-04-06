package com.mycollab.premium.mobile.module.project.view.settings;

import com.mycollab.common.i18n.SecurityI18nEnum;
import com.mycollab.mobile.module.project.view.settings.ProjectMemberEditView;
import com.mycollab.mobile.module.project.view.settings.ProjectRoleListSelect;
import com.mycollab.mobile.ui.AbstractEditItemComp;
import com.mycollab.mobile.ui.FormSectionBuilder;
import com.mycollab.mobile.ui.grid.GridFormLayoutHelper;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectLinkBuilder;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.domain.ProjectMember;
import com.mycollab.module.project.domain.SimpleProjectMember;
import com.mycollab.module.project.domain.SimpleProjectRole;
import com.mycollab.module.project.i18n.ProjectMemberI18nEnum;
import com.mycollab.module.project.i18n.ProjectRoleI18nEnum;
import com.mycollab.module.project.i18n.RolePermissionI18nEnum;
import com.mycollab.module.project.service.ProjectRoleService;
import com.mycollab.security.PermissionMap;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.AbstractFormLayoutFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.ui.IFormLayoutFactory;
import com.mycollab.vaadin.ui.field.DefaultViewField;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Property;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
@ViewComponent
public class ProjectMemberEditViewImpl extends AbstractEditItemComp<SimpleProjectMember> implements ProjectMemberEditView {
    private static final long serialVersionUID = 1483479851089277052L;

    private VerticalComponentGroup permissionGroup;

    public ProjectMemberEditViewImpl() {
        this.permissionGroup = new VerticalComponentGroup();
        this.permissionGroup.setWidth("100%");
    }

    @Override
    protected String initFormTitle() {
        return beanItem.getDisplayName();
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new ProjectMemberEditFormLayoutFactory();
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<SimpleProjectMember> initBeanFormFieldFactory() {
        return new ProjectMemberEditFieldGroupFactory(this.editForm);
    }

    private void displayRolePermission(Integer roleId) {
        permissionGroup.removeAllComponents();
        if (roleId != null && roleId > 0) {
            ProjectRoleService roleService = AppContextUtil.getSpringBean(ProjectRoleService.class);
            SimpleProjectRole role = roleService.findById(roleId, AppUI.getAccountId());
            if (role != null) {
                final PermissionMap permissionMap = role.getPermissionMap();
                for (int i = 0; i < ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length; i++) {
                    final String permissionPath = ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i];
                    Label permissionLbl = new Label(UserUIContext.getPermissionCaptionValue(permissionMap, permissionPath));
                    permissionLbl.setCaption(UserUIContext.getMessage(RolePermissionI18nEnum.valueOf(permissionPath)));
                    permissionGroup.addComponent(permissionLbl);
                }
            }
        } else {
            for (int i = 0; i < ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length; i++) {
                final String permissionPath = ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i];
                Label permissionLbl = new Label(UserUIContext.getMessage(SecurityI18nEnum.ACCESS));
                permissionLbl.setCaption(permissionPath);
                permissionGroup.addComponent(permissionLbl);
            }
        }
    }

    private class ProjectMemberEditFormLayoutFactory extends AbstractFormLayoutFactory {
        private static final long serialVersionUID = -6204799792781581979L;
        private GridFormLayoutHelper informationLayout;

        @Override
        public AbstractComponent getLayout() {
            final MVerticalLayout layout = new MVerticalLayout().withMargin(false).withSpacing(false).withFullWidth();
            layout.addComponent(FormSectionBuilder.build(UserUIContext.getMessage(ProjectMemberI18nEnum.FORM_INFORMATION_SECTION)));

            informationLayout = GridFormLayoutHelper.defaultFormLayoutHelper(1, 2);
            layout.addComponent(informationLayout.getLayout());
            layout.addComponent(FormSectionBuilder.build(UserUIContext.getMessage(ProjectRoleI18nEnum.SECTION_PERMISSIONS)));
            layout.addComponent(permissionGroup);

            return layout;
        }

        @Override
        protected Component onAttachField(Object propertyId, Field<?> field) {
            if (ProjectMember.Field.username.equalTo(propertyId)) {
                return informationLayout.addComponent(field, UserUIContext.getMessage(ProjectMemberI18nEnum.FORM_USER), 0, 0);
            } else if (ProjectMember.Field.projectroleid.equalTo(propertyId)) {
                return informationLayout.addComponent(field, UserUIContext.getMessage(ProjectMemberI18nEnum.FORM_ROLE), 0, 1);
            }
            return null;
        }
    }

    private class ProjectMemberEditFieldGroupFactory extends AbstractBeanFieldGroupEditFieldFactory<SimpleProjectMember> {
        private static final long serialVersionUID = 1490026787891513129L;

        public ProjectMemberEditFieldGroupFactory(GenericBeanForm<SimpleProjectMember> form) {
            super(form);
        }

        @Override
        protected AbstractField<?> onCreateField(Object propertyId) {
            if (ProjectMember.Field.username.equalTo(propertyId)) {
                return new DefaultViewField(ProjectLinkBuilder.generateProjectMemberHtmlLink(CurrentProjectVariables
                        .getProjectId(), beanItem.getUsername(), beanItem.getDisplayName(), beanItem
                        .getMemberAvatarId(), false), ContentMode.HTML);
            } else if (ProjectMember.Field.projectroleid.equalTo(propertyId)) {
                return new ProjectRoleSelectionField();
            }
            return null;
        }

    }

    private class ProjectRoleSelectionField extends CustomField<Integer> {
        private static final long serialVersionUID = 1L;
        private ProjectRoleListSelect roleComboBox;

        public ProjectRoleSelectionField() {
            roleComboBox = new ProjectRoleListSelect();
            roleComboBox.addValueChangeListener(new Property.ValueChangeListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void valueChange(final Property.ValueChangeEvent event) {
                    displayRolePermission((Integer) roleComboBox.getValue());
                }
            });
            roleComboBox.setWidth("100%");
        }

        @Override
        public void commit() throws SourceException, InvalidValueException {
            Integer roleId = (Integer) roleComboBox.getValue();
            if (roleId == -1) {
                beanItem.setIsadmin(Boolean.TRUE);
                this.setInternalValue(null);
            } else {
                this.setInternalValue((Integer) roleComboBox.getValue());
                beanItem.setIsadmin(Boolean.FALSE);
            }

            super.commit();
        }

        @Override
        public void setPropertyDataSource(@SuppressWarnings("rawtypes") Property newDataSource) {
            Object value = newDataSource.getValue();
            if (value instanceof Integer) {
                roleComboBox.setValue(value);
                displayRolePermission((Integer) roleComboBox.getValue());
            } else if (value == null) {
                if (Boolean.TRUE == beanItem.getIsadmin()) {
                    roleComboBox.setValue(-1);
                    displayRolePermission(null);
                }
            }
            super.setPropertyDataSource(newDataSource);
        }

        @Override
        public Class<Integer> getType() {
            return Integer.class;
        }

        @Override
        protected Component initContent() {
            return roleComboBox;
        }
    }
}
