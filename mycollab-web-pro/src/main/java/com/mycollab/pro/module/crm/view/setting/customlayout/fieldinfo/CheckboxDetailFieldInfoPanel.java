package com.mycollab.pro.module.crm.view.setting.customlayout.fieldinfo;

import com.mycollab.form.view.builder.type.BooleanDynaField;
import com.mycollab.form.view.builder.type.DynaSection;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class CheckboxDetailFieldInfoPanel extends DetailFieldInfoPanel<BooleanDynaField> {
    private static final long serialVersionUID = 1L;

    public CheckboxDetailFieldInfoPanel(String candidateFieldName, DynaSection[] activeSections) {
        super(candidateFieldName, activeSections);
    }

    @Override
    public DynaSection updateCustomField() {
        return null;
    }

}
