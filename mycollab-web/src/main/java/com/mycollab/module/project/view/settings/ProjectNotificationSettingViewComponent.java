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
package com.mycollab.module.project.view.settings;

import com.mycollab.module.project.domain.ProjectNotificationSetting;
import com.mycollab.module.project.i18n.ProjectSettingI18nEnum;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.BlockWidget;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
// TODO
public class ProjectNotificationSettingViewComponent extends BlockWidget {
    private static final long serialVersionUID = 1L;

    public ProjectNotificationSettingViewComponent(final ProjectNotificationSetting bean) {
        super(UserUIContext.getMessage(ProjectSettingI18nEnum.VIEW_TITLE));

        MVerticalLayout body = new MVerticalLayout().withMargin(new MarginInfo(true, false, false, false));
        body.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

//        final OptionGroup optionGroup = new OptionGroup(null);
//        optionGroup.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
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
//        body.with(optionGroup);
//
//        String levelVal = bean.getLevel();
//        if (levelVal == null) {
//            optionGroup.select(NotificationType.Default.name());
//        } else {
//            optionGroup.select(levelVal);
//        }
//
//        MButton saveBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SAVE), clickEvent -> {
//            try {
//                bean.setLevel((String) optionGroup.getValue());
//                ProjectNotificationSettingService projectNotificationSettingService = AppContextUtil.getSpringBean(ProjectNotificationSettingService.class);
//
//                if (bean.getId() == null) {
//                    projectNotificationSettingService.saveWithSession(bean, UserUIContext.getUsername());
//                } else {
//                    projectNotificationSettingService.updateWithSession(bean, UserUIContext.getUsername());
//                }
//                NotificationUtil.showNotification(UserUIContext.getMessage(GenericI18Enum.OPT_CONGRATS),
//                        UserUIContext.getMessage(ProjectSettingI18nEnum.DIALOG_UPDATE_SUCCESS));
//            } catch (Exception e) {
//                throw new MyCollabException(e);
//            }
//        }).withIcon(VaadinIcons.CLIPBOARD).withStyleName(WebThemes.BUTTON_ACTION);
//        body.addComponent(saveBtn);
//
//        this.addComponent(body);
    }
}