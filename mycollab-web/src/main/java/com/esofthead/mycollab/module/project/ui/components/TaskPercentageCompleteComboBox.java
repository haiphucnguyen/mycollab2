/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

/**
 *
 * @author haiphucnguyen
 */
public class TaskPercentageCompleteComboBox extends ValueComboBox {
    public TaskPercentageCompleteComboBox() {
        this.loadData(new String[]{"0", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100"});
    }
}
