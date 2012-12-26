/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.VerticalLayout;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author haiphucnguyen
 */
public class PrintPreview extends VerticalLayout{
    private Set<PrintListener> printListeners;
    
    public void addPrintListener(PrintListener listener) {
        if (printListeners == null) {
            printListeners = new HashSet<PrintListener>();
        }
        
        printListeners.add(listener);
    }
    
    protected void firePrintListeners() {
        if (printListeners != null) {
            for (PrintListener listener : printListeners) {
                listener.doPrint();
            }
        }
    }
    
    public static interface PrintListener {
        void doPrint();
    }
}
