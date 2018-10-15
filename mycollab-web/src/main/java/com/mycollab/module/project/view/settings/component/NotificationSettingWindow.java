/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.project.view.settings.component;

import com.mycollab.module.project.domain.ProjectNotificationSetting;
import com.mycollab.module.project.domain.SimpleProjectMember;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.service.ProjectNotificationSettingService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
// TODO
public class NotificationSettingWindow extends MWindow {
    public NotificationSettingWindow(SimpleProjectMember projectMember) {
        super(UserUIContext.getMessage(ProjectCommonI18nEnum.ACTION_EDIT_NOTIFICATION));
        withModal(true).withResizable(false).withWidth("600px").withCenter();
        ProjectNotificationSettingService prjNotificationSettingService = AppContextUtil.getSpringBean(ProjectNotificationSettingService.class);
        ProjectNotificationSetting notification = prjNotificationSettingService.findNotification(projectMember.getUsername(), projectMember.getProjectid(),
                projectMember.getSaccountid());

        MVerticalLayout body = new MVerticalLayout();

//        final OptionGroup optionGroup = new OptionGroup(null);
//        optionGroup.setItemCaptionMode(AbstractSelect.ItemCaptionMode.EXPLICIT);
//
//        optionGroup.addItem(NotificationType.Default.name());
//        optionGroup.setItemCaption(NotificationType.Default.name(), UserUIContext
//                .getMessage(ProjectSettingI18nEnum.OPT_DEFAULT_SETTING));
//
//        optionGroup.addItem(NotificationType.None.name());
//        optionGroup.setItemCaption(NotificationType.None.name(),
//                UserUIContext.getMessage(ProjectSettingI18nEnum.OPT_NONE_SETTING));
//
//        optionGroup.addItem(NotificationType.Minimal.name());
//        optionGroup.setItemCaption(NotificationType.Minimal.name(), UserUIContext
//                .getMessage(ProjectSettingI18nEnum.OPT_MINIMUM_SETTING));
//
//        optionGroup.addItem(NotificationType.Full.name());
//        optionGroup.setItemCaption(NotificationType.Full.name(), UserUIContext
//                .getMessage(ProjectSettingI18nEnum.OPT_MAXIMUM_SETTING));
//
//        optionGroup.setWidth("100%");
//
//        body.with(optionGroup).withAlign(optionGroup, Alignment.MIDDLE_LEFT);
//
//        String levelVal = notification.getLevel();
//        if (levelVal == null) {
//            optionGroup.select(NotificationType.Default.name());
//        } else {
//            optionGroup.select(levelVal);
//        }
//
//        MButton closeBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CLOSE), clickEvent -> close())
//                .withStyleName(WebThemes.BUTTON_OPTION);
//        MButton saveBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SAVE), clickEvent -> {
//            try {
//                notification.setLevel((String) optionGroup.getValue());
//                ProjectNotificationSettingService projectNotificationSettingService = AppContextUtil.getSpringBean(ProjectNotificationSettingService.class);
//
//                if (notification.getId() == null) {
//                    projectNotificationSettingService.saveWithSession(notification, UserUIContext.getUsername());
//                } else {
//                    projectNotificationSettingService.updateWithSession(notification, UserUIContext.getUsername());
//                }
//                NotificationUtil.showNotification(UserUIContext.getMessage(GenericI18Enum.OPT_CONGRATS),
//                        UserUIContext.getMessage(ProjectSettingI18nEnum.DIALOG_UPDATE_SUCCESS));
//                close();
//            } catch (Exception e) {
//                throw new MyCollabException(e);
//            }
//        }).withStyleName(WebThemes.BUTTON_ACTION).withIcon(VaadinIcons.CLIPBOARD);
//        MHorizontalLayout btnControls = new MHorizontalLayout(closeBtn, saveBtn);
//        body.with(btnControls).withAlign(btnControls, Alignment.TOP_RIGHT);
//
//        withContent(body);
    }
}
