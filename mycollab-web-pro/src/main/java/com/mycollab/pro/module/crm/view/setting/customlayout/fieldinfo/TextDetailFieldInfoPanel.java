package com.mycollab.pro.module.crm.view.setting.customlayout.fieldinfo;

import com.mycollab.form.view.builder.type.DynaSection;
import com.mycollab.form.view.builder.type.TextDynaField;
import com.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.ui.TextField;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class TextDetailFieldInfoPanel extends DetailFieldInfoPanel<TextDynaField> {
    private static final long serialVersionUID = 1L;

    private TextField labelField = new TextField();
    private SectionSelectList sectionList;

    public TextDetailFieldInfoPanel(String candidateFieldName, DynaSection[] activeSections) {
        super(candidateFieldName, activeSections);

        GridFormLayoutHelper layoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 3);
        layoutHelper.getLayout().setWidth("100%");
        layoutHelper.addComponent(labelField, "Label", 0, 0);
        sectionList = new SectionSelectList(activeSections);
        layoutHelper.addComponent(sectionList, "Section", 0, 1);
        TextField lengthField = new TextField();
        layoutHelper.addComponent(lengthField, "Length", 0, 2);
        this.addComponent(layoutHelper.getLayout());
    }

    @Override
    public DynaSection updateCustomField() {
        String displayName = labelField.getValue();
        DynaSection ownSection = (DynaSection) sectionList.getValue();

        TextDynaField customField = new TextDynaField();
        customField.setCustom(true);
//        customField.setDisplayName(displayName);
        customField.setMandatory(false);
        customField.setRequired(false);
        customField.setFieldName(candidateFieldName);
        if (ownSection.getFieldCount() > 0) {
            customField.setFieldIndex(ownSection.getField(
                    ownSection.getFieldCount() - 1).getFieldIndex() + 1);
        } else {
            customField.setFieldIndex(0);
        }

        ownSection.fields(customField);
        return ownSection;
    }
}
