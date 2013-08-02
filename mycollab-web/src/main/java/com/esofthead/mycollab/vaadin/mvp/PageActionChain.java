/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.mvp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author haiphucnguyen
 */
public class PageActionChain {
	private static Logger log = LoggerFactory.getLogger(PageActionChain.class);
	
    private List<ScreenData> chains = new ArrayList<ScreenData>();
    
    public PageActionChain(ScreenData... pageActionArr) {
        chains.addAll(Arrays.asList(pageActionArr));
        log.debug("Chain size: " + this + "---" + chains.size());
    }
    
    public PageActionChain add(ScreenData pageAction) {
        chains.add(pageAction);
        log.debug("Chain size: " + this + "---" + chains.size());
        return this;
    }
    
    public ScreenData pop() {
    	log.debug("Pop pageActionChain " + this + "---" + chains.size());
    	if (chains.size() > 0) {
    		ScreenData pageAction = chains.get(0);
            chains.remove(0);
            return pageAction;
    	} else {
    		return null;
    	}
    	
    }
    
    public ScreenData peek() {
    	ScreenData pageAction = chains.get(0);
        return pageAction;
    }
    
    public boolean hasNext() {
        return chains.size() > 0;
    }
}
