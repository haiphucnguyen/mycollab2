/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

/**
 *
 * @author haiphucnguyen
 */
public class BugResolutionComboBox extends ValueComboBox {
    public BugResolutionComboBox() {
        super(false, ProjectDataTypeFactory.getBugResolutionList());
        
    }
}
