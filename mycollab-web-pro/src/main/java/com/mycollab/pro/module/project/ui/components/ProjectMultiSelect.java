package com.mycollab.pro.module.project.ui.components;

import com.mycollab.module.project.domain.SimpleProject;
import com.mycollab.vaadin.web.ui.UIConstants;
import org.vaadin.addons.comboboxmultiselect.ComboBoxMultiselect;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
public class ProjectMultiSelect extends ComboBoxMultiselect {

    public ProjectMultiSelect(List<SimpleProject> projects) {
        this.setWidth("200px");
        this.setTextInputAllowed(false);
        for (SimpleProject project : projects) {
            this.addItem(project);
            this.setItemCaption(project, project.getName());
        }

        this.setItemStyleGenerator(((comboBoxMultiselect, o) -> UIConstants.TEXT_ELLIPSIS));
    }
}
