package com.esofthead.mycollab.module.project.ui;

import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.vaadin.server.FontAwesome;

import java.util.Properties;

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
public class ProjectAssetsUtil {


    public static FontAwesome getPhaseIcon(String status) {
        if (OptionI18nEnum.MilestoneStatus.Closed.name().equals(status)) {
            return FontAwesome.MINUS;
        } else if (OptionI18nEnum.MilestoneStatus.Future.name().equals(status)) {
            return FontAwesome.CLOCK_O;
        } else {
            return FontAwesome.SPINNER;
        }
    }

    public static void main(String[] args) {
        System.out.println(FontAwesome.ARROW_DOWN.getHtml());
    }
}
