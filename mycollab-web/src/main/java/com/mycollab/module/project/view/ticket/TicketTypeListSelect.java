package com.mycollab.module.project.view.ticket;

import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.i18n.BugI18nEnum;
import com.mycollab.module.project.i18n.RiskI18nEnum;
import com.mycollab.module.project.i18n.TaskI18nEnum;
import com.mycollab.vaadin.UserUIContext;
import com.vaadin.ui.ListSelect;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
class TicketTypeListSelect extends ListSelect {
    TicketTypeListSelect() {
        this.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
        this.setNullSelectionAllowed(false);
        this.setMultiSelect(true);
        this.setRows(3);

        this.addItem(ProjectTypeConstants.INSTANCE.getTASK());
        this.setItemCaption(ProjectTypeConstants.INSTANCE.getTASK(), UserUIContext.getMessage(TaskI18nEnum.SINGLE));
        this.addItem(ProjectTypeConstants.INSTANCE.getBUG());
        this.setItemCaption(ProjectTypeConstants.INSTANCE.getBUG(), UserUIContext.getMessage(BugI18nEnum.SINGLE));
        if (!SiteConfiguration.isCommunityEdition()) {
            this.addItem(ProjectTypeConstants.INSTANCE.getRISK());
            this.setItemCaption(ProjectTypeConstants.INSTANCE.getRISK(), UserUIContext.getMessage(RiskI18nEnum.SINGLE));
        }
    }
}
