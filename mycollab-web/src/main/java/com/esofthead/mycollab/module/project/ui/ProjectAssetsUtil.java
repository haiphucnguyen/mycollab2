package com.esofthead.mycollab.module.project.ui;

import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.vaadin.server.FontAwesome;

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
public class ProjectAssetsUtil {
    public static FontAwesome getPhaseIcon(String status) {
        if (OptionI18nEnum.MilestoneStatus.Closed.equals(status)) {
            return FontAwesome.MINUS;
        } else if (OptionI18nEnum.MilestoneStatus.Future.equals(status)) {
            return FontAwesome.CLOCK_O;
        } else {
            return FontAwesome.SPINNER;
        }
    }
}
