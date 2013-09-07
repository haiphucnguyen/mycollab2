/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ListView;

/**
 * 
 * @author haiphucnguyen
 */
public interface ComponentListView extends
		ListView<ComponentSearchCriteria, SimpleComponent> {

}
