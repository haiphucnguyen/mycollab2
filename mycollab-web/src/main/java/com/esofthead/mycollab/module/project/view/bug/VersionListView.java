/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.SimpleVersion;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ListView;

/**
 * 
 * @author haiphucnguyen
 */
public interface VersionListView extends
		ListView<VersionSearchCriteria, SimpleVersion> {

}
