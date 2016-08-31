package com.mycollab.module.project.view.settings.component;

import com.mycollab.common.NotificationType;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.MyCollabException;
import com.mycollab.module.project.domain.ProjectNotificationSetting;
import com.mycollab.module.project.domain.SimpleProjectMember;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.i18n.ProjectSettingI18nEnum;
import com.mycollab.module.project.service.ProjectNotificationSettingService;
import com.mycollab.module.project.view.settings.ProjectNotificationSettingViewComponent;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.OptionGroup;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
public class NotificationSettingWindow extends MWindow {
    public NotificationSettingWindow(SimpleProjectMember projectMember) {
        super(AppContext.getMessage(ProjectCommonI18nEnum.ACTION_EDIT_NOTIFICATION));
        withModal(true).withResizable(false).withWidth("600px").withCenter();
        ProjectNotificationSettingService prjNotificationSettingService = AppContextUtil.getSpringBean(ProjectNotificationSettingService.class);
        ProjectNotificationSetting notification = prjNotificationSettingService.findNotification(projectMember.getUsername(), projectMember.getProjectid(),
                projectMember.getSaccountid());

        MVerticalLayout body = new MVerticalLayout();

        final OptionGroup optionGroup = new OptionGroup(null);
        optionGroup.setItemCaptionMode(AbstractSelect.ItemCaptionMode.EXPLICIT);

        optionGroup.addItem(NotificationType.Default.name());
        optionGroup.setItemCaption(NotificationType.Default.name(), AppContext
                .getMessage(ProjectSettingI18nEnum.OPT_DEFAULT_SETTING));

        optionGroup.addItem(NotificationType.None.name());
        optionGroup.setItemCaption(NotificationType.None.name(),
                AppContext.getMessage(ProjectSettingI18nEnum.OPT_NONE_SETTING));

        optionGroup.addItem(NotificationType.Minimal.name());
        optionGroup.setItemCaption(NotificationType.Minimal.name(), AppContext
                .getMessage(ProjectSettingI18nEnum.OPT_MINIMUM_SETTING));

        optionGroup.addItem(NotificationType.Full.name());
        optionGroup.setItemCaption(NotificationType.Full.name(), AppContext
                .getMessage(ProjectSettingI18nEnum.OPT_MAXIMUM_SETTING));

        optionGroup.setWidth("100%");

        body.with(optionGroup).withAlign(optionGroup, Alignment.MIDDLE_LEFT);

        String levelVal = notification.getLevel();
        if (levelVal == null) {
            optionGroup.select(NotificationType.Default.name());
        } else {
            optionGroup.select(levelVal);
        }

        MButton closeBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_CLOSE), clickEvent -> close())
                .withStyleName(WebUIConstants.BUTTON_OPTION);
        MButton saveBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_SAVE), clickEvent -> {
            try {
                notification.setLevel((String) optionGroup.getValue());
                ProjectNotificationSettingService projectNotificationSettingService = AppContextUtil.getSpringBean(ProjectNotificationSettingService.class);

                if (notification.getId() == null) {
                    projectNotificationSettingService.saveWithSession(notification, AppContext.getUsername());
                } else {
                    projectNotificationSettingService.updateWithSession(notification, AppContext.getUsername());
                }
                NotificationUtil.showNotification(AppContext.getMessage(GenericI18Enum.OPT_CONGRATS),
                        AppContext.getMessage(ProjectSettingI18nEnum.DIALOG_UPDATE_SUCCESS));
                close();
            } catch (Exception e) {
                throw new MyCollabException(e);
            }
        }).withStyleName(WebUIConstants.BUTTON_ACTION);
        MHorizontalLayout btnControls = new MHorizontalLayout(closeBtn, saveBtn);
        body.with(btnControls).withAlign(btnControls, Alignment.TOP_RIGHT);

        withContent(body);
    }
}
