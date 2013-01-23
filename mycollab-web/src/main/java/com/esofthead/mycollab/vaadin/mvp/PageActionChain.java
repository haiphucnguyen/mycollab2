/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.mvp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author haiphucnguyen
 */
public class PageActionChain {
    private List<PageAction> chains = new ArrayList<PageAction>();
    
    public PageActionChain add(PageAction pageAction) {
        chains.add(pageAction);
        return this;
    }
    
    public PageAction get() {
        PageAction pageAction = chains.get(0);
        chains.remove(0);
        return pageAction;
    }
    
    public boolean hasNext() {
        return chains.size() > 0;
    }
}
