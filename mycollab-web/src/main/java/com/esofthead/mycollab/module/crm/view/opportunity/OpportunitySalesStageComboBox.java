package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.CrmDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

@SuppressWarnings("serial")
public class OpportunitySalesStageComboBox extends ValueComboBox {

    public OpportunitySalesStageComboBox() {
        this.loadData(CrmDataTypeFactory.getOpportunitySalesStageList());
        this.setNullSelectionAllowed(false);
    }
}
