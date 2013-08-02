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
        super(false, 0d, 10d, 20d, 30d, 40d, 50d, 60d, 70d, 80d, 90d, 100d);
    }
}
