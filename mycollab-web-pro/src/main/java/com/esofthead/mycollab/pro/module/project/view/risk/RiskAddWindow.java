package com.esofthead.mycollab.pro.module.project.view.risk;

import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.vaadin.ui.Window;

/**
 * @author MyCollab Ltd
 * @since 5.2.7
 */
public class RiskAddWindow extends Window {
    public RiskAddWindow(SimpleRisk risk) {
        if (risk.getId() == null) {
            setCaption("New risk");
        } else {
            setCaption("Edit risk");
        }
        this.setWidth("800px");
        this.setModal(true);
        this.setResizable(false);
    }
}
