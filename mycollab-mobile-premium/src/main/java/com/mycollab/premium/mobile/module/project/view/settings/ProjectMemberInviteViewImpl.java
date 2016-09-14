package com.mycollab.premium.mobile.module.project.view.settings;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.i18n.SecurityI18nEnum;
import com.mycollab.mobile.module.project.events.ProjectMemberEvent;
import com.mycollab.mobile.module.project.view.settings.ProjectMemberInviteView;
import com.mycollab.mobile.module.project.view.settings.ProjectRoleComboBox;
import com.mycollab.mobile.ui.AbstractMobilePageView;
import com.mycollab.mobile.ui.FormSectionBuilder;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.domain.SimpleProjectRole;
import com.mycollab.module.project.i18n.ProjectMemberI18nEnum;
import com.mycollab.module.project.i18n.ProjectRoleI18nEnum;
import com.mycollab.module.project.i18n.RolePermissionI18nEnum;
import com.mycollab.module.project.service.ProjectRoleService;
import com.mycollab.security.PermissionMap;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.mvp.ViewEvent;
import com.vaadin.addon.touchkit.ui.EmailField;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

import java.util.Collections;

/**
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
@ViewComponent
public class ProjectMemberInviteViewImpl extends AbstractMobilePageView implements ProjectMemberInviteView {
    private static final long serialVersionUID = 6319585054784302576L;
    private Integer roleId = 0;

    private EmailField inviteEmailField;
    private ProjectRoleComboBox roleComboBox;
    private TextArea messageArea;
    private VerticalComponentGroup permissionsPanel;

    public ProjectMemberInviteViewImpl() {
        super();
        this.setCaption(UserUIContext.getMessage(ProjectMemberI18nEnum.NEW));
        constructUI();
    }

    private void constructUI() {
        this.roleComboBox = new ProjectRoleComboBox();
        this.roleComboBox.addValueChangeListener(new ValueChangeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void valueChange(ValueChangeEvent event) {
                Integer roleId = (Integer) roleComboBox.getValue();
                displayRolePermission(roleId);
            }
        });
        roleComboBox.setCaption(UserUIContext.getMessage(ProjectMemberI18nEnum.FORM_ROLE));

        final VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setWidth("100%");

        VerticalComponentGroup inviteFormLayout = new VerticalComponentGroup();
        inviteFormLayout.setWidth("100%");

        inviteEmailField = new EmailField();
        inviteEmailField.setCaption(UserUIContext.getMessage(GenericI18Enum.FORM_EMAIL));
        inviteFormLayout.addComponent(inviteEmailField);

        messageArea = new TextArea();
        messageArea.setValue(UserUIContext.getMessage(ProjectMemberI18nEnum.MSG_DEFAULT_INVITATION_COMMENT));
        messageArea.setCaption(UserUIContext.getMessage(ProjectMemberI18nEnum.FORM_MESSAGE));
        inviteFormLayout.addComponent(messageArea);

        inviteFormLayout.addComponent(roleComboBox);
        mainLayout.addComponent(inviteFormLayout);

        mainLayout.addComponent(FormSectionBuilder.build(UserUIContext.getMessage(ProjectRoleI18nEnum.SECTION_PERMISSIONS)));

        permissionsPanel = new VerticalComponentGroup();
        mainLayout.addComponent(permissionsPanel);

        Button inviteBtn = new Button(UserUIContext.getMessage(ProjectMemberI18nEnum.BUTTON_NEW_INVITEE), clickEvent -> {
            if ("".equals(inviteEmailField.getValue())) {
                return;
            }
            roleId = (Integer) roleComboBox.getValue();
            fireEvent(new ViewEvent<>(ProjectMemberInviteViewImpl.this,
                    new ProjectMemberEvent.InviteProjectMembers(Collections.singletonList(inviteEmailField.getValue()),
                            ProjectMemberInviteViewImpl.this.roleId, messageArea.getValue())));
        });
        this.setRightComponent(inviteBtn);
        this.setContent(mainLayout);
    }

    private void displayRolePermission(Integer roleId) {
        permissionsPanel.removeAllComponents();
        if (roleId != null && roleId > 0) {
            ProjectRoleService roleService = AppContextUtil.getSpringBean(ProjectRoleService.class);
            SimpleProjectRole role = roleService.findById(roleId,
                    MyCollabUI.getAccountId());
            if (role != null) {
                final PermissionMap permissionMap = role.getPermissionMap();
                for (int i = 0; i < ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length; i++) {
                    final String permissionPath = ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i];
                    Label permissionLbl = new Label(UserUIContext.getPermissionCaptionValue(permissionMap, permissionPath));
                    permissionLbl.setCaption(UserUIContext.getMessage(RolePermissionI18nEnum.valueOf(permissionPath)));
                    permissionsPanel.addComponent(permissionLbl);
                }
            }
        } else {
            for (int i = 0; i < ProjectRolePermissionCollections.PROJECT_PERMISSIONS.length; i++) {
                final String permissionPath = ProjectRolePermissionCollections.PROJECT_PERMISSIONS[i];
                Label permissionLbl = new Label(UserUIContext.getMessage(SecurityI18nEnum.ACCESS));
                permissionLbl.setCaption(permissionPath);
                permissionsPanel.addComponent(permissionLbl);
            }
        }

    }

    @Override
    public void display() {
        roleId = 0;
        displayRolePermission(roleId);
        inviteEmailField.setValue("");
        messageArea.setValue(UserUIContext.getMessage(ProjectMemberI18nEnum.MSG_DEFAULT_INVITATION_COMMENT));
    }
}
