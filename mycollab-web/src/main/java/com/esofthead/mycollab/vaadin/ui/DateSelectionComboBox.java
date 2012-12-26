/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

/**
 *
 * @author haiphucnguyen
 */
public class DateSelectionComboBox extends ValueComboBox{
    public DateSelectionComboBox() {
        super();
        this.loadData("Equals", "Not On", "After", "Before", "This month", "Is Between");
    }
}
