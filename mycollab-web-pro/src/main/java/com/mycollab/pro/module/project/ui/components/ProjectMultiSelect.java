package com.mycollab.pro.module.project.ui.components;

import com.mycollab.module.project.domain.SimpleProject;
import com.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.ItemCaptionGenerator;
import org.vaadin.addons.ComboBoxMultiselect;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
public class ProjectMultiSelect extends ComboBoxMultiselect<SimpleProject> {

    public ProjectMultiSelect(List<SimpleProject> projects) {
        this.setWidth("200px");
        this.setTextInputAllowed(false);
        this.setItems(projects);
        this.setItemCaptionGenerator((ItemCaptionGenerator<SimpleProject>) project -> project.getName());
        this.setStyleGenerator(project -> UIConstants.TEXT_ELLIPSIS);
    }
}
