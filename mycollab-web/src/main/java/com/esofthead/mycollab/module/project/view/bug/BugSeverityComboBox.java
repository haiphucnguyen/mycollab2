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
public class BugSeverityComboBox  extends ValueComboBox {

    private static final long serialVersionUID = 1L;

    public BugSeverityComboBox() {
        super();
        setCaption(null);
        loadData(ProjectDataTypeFactory.getBugSeverityList());
    }
    
}
