package com.mycollab.pro.module.crm.view.setting.customlayout.fieldinfo;

import com.mycollab.form.view.builder.type.DynaSection;
import com.vaadin.ui.ComboBox;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
// TODO
public class SectionSelectList extends ComboBox {
    private static final long serialVersionUID = 1L;

    public SectionSelectList(DynaSection[] sections) {
//        this.setItemCaptionMode(ItemCaptionMode.EXPLICIT);

        for (DynaSection section : sections) {
//            this.addItem(section);
//            this.setItemCaption(section, section.getHeader());
        }

        if (sections.length > 0) {
//            this.select(sections[0]);
        }
    }
}