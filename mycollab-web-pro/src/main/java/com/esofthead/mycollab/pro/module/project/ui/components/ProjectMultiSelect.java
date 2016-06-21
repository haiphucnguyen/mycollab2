package com.esofthead.mycollab.pro.module.project.ui.components;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import org.vaadin.addons.comboboxmultiselect.ComboBoxMultiselect;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
public class ProjectMultiSelect extends ComboBoxMultiselect {

    public ProjectMultiSelect(List<SimpleProject> projects) {
        this.setTextInputAllowed(false);
        for (SimpleProject project: projects) {
            this.addItem(project);
            this.setItemCaption(project, project.getName());
        }
        this.setItemStyleGenerator(new ItemStyleGenerator() {
            @Override
            public String getStyle(ComboBoxMultiselect comboBoxMultiselect, Object o) {
                return UIConstants.TEXT_ELLIPSIS;
            }
        });
    }
}
