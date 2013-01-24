/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.mvp;

/**
 *
 * @author haiphucnguyen
 */
public class PageAction {
    private ScreenData screenData;
    
    public PageAction(ScreenData screenData) {
        this.screenData = screenData;
    }

    public ScreenData getScreenData() {
        return screenData;
    }
}
