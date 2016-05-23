/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.pro.module.crm.view.setting.customlayout.fieldinfo;

import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.TextDynaField;
import com.esofthead.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
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
        customField.setDisplayName(displayName);
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
