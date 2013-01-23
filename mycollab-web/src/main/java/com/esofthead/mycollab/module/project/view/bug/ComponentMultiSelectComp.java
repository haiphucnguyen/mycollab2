/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import com.esofthead.mycollab.web.AppContext;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class ComponentMultiSelectComp extends MultiSelectComp {
    
    public ComponentMultiSelectComp() {
super();
		
		ComponentSearchCriteria searchCriteria = new ComponentSearchCriteria();
		
		SimpleProject project = (SimpleProject) AppContext.getVariable("project");
		searchCriteria.setProjectid(new NumberSearchField(
                 SearchField.AND, project.getId()));
		 
		ComponentService versionService = AppContext.getSpringBean(ComponentService.class);
		List<SimpleComponent> lstVersion = versionService.findPagableListByCriteria(new SearchRequest<ComponentSearchCriteria>(searchCriteria, 0, Integer.MAX_VALUE));
		List<String> lstComponentName = new ArrayList<String>();
		
		for (int i = 0; i < lstVersion.size(); i++) {
			SimpleComponent version = lstVersion.get(i);
			lstComponentName.add(version.getComponentname());
		}
		
		this.loadData(lstComponentName);
    }
}
