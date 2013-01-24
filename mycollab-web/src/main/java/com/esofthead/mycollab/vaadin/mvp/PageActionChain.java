/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.mvp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author haiphucnguyen
 */
public class PageActionChain {
    private List<PageAction> chains = new ArrayList<PageAction>();
    
    public PageActionChain(PageAction... pageActionArr) {
        chains.addAll(Arrays.asList(pageActionArr));
    }
    
    public PageActionChain add(PageAction pageAction) {
        chains.add(pageAction);
        return this;
    }
    
    public PageAction pop() {
        PageAction pageAction = chains.get(0);
        chains.remove(0);
        return pageAction;
    }
    
    public PageAction peek() {
        PageAction pageAction = chains.get(0);
        return pageAction;
    }
    
    public boolean hasNext() {
        return chains.size() > 0;
    }
}
