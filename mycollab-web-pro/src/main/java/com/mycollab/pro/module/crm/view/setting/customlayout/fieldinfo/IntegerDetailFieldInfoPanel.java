package com.mycollab.pro.module.crm.view.setting.customlayout.fieldinfo;

import com.mycollab.form.view.builder.type.DynaSection;
import com.mycollab.form.view.builder.type.IntDynaField;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class IntegerDetailFieldInfoPanel extends DetailFieldInfoPanel<IntDynaField> {
    private static final long serialVersionUID = 1L;

    public IntegerDetailFieldInfoPanel(String candidateFieldName, DynaSection[] activeSections) {
        super(candidateFieldName, activeSections);
    }

    @Override
    public DynaSection updateCustomField() {
        return null;
    }

}
