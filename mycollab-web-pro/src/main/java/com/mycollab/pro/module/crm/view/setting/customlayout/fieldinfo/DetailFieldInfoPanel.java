package com.mycollab.pro.module.crm.view.setting.customlayout.fieldinfo;

import com.mycollab.form.view.builder.type.AbstractDynaField;
import com.mycollab.form.view.builder.type.DynaSection;
import com.vaadin.ui.VerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public abstract class DetailFieldInfoPanel<F extends AbstractDynaField> extends VerticalLayout {
    private static final long serialVersionUID = 1L;

    private DynaSection[] activeSections;
    protected String candidateFieldName;
    protected F field;

    public DetailFieldInfoPanel(String candidateFieldName, DynaSection[] activeSections) {
        this.activeSections = activeSections;
        this.candidateFieldName = candidateFieldName;
    }

    public abstract DynaSection updateCustomField();
}
