package com.mycollab.pro.module.project.view.risk;

import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.i18n.RiskI18nEnum;
import com.mycollab.vaadin.UserUIContext;
import org.vaadin.viritin.layouts.MWindow;

/**
 * @author MyCollab Ltd
 * @since 5.2.7
 */
public class RiskAddWindow extends MWindow {
    public RiskAddWindow(SimpleRisk risk) {
        if (risk.getId() == null) {
            setCaption(UserUIContext.getMessage(RiskI18nEnum.NEW));
        } else {
            setCaption(UserUIContext.getMessage(RiskI18nEnum.EDIT));
        }

        RiskEditForm editForm = new RiskEditForm() {
            @Override
            protected void postExecution() {
                close();
            }
        };
        editForm.setBean(risk);
        this.withWidth("1200px").withModal(true).withResizable(false).withContent(editForm);
    }
}
