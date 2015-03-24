package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.module.project.view.risk.RiskFormatter;
import com.esofthead.mycollab.utils.FieldGroupFormatter;
import com.esofthead.mycollab.vaadin.ui.HistoryLogComponent;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class RiskHistoryList extends HistoryLogComponent {
    private static final long serialVersionUID = 1L;

    public RiskHistoryList(String module, String type) {
        super(module, type);
    }

    @Override
    protected FieldGroupFormatter buildFormatter() {
        return RiskFormatter.instance;
    }

}
