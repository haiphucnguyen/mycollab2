/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import com.esofthead.mycollab.web.AppContext;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class ComponentMultiSelectField extends MultiSelectComp {

    public ComponentMultiSelectField() {
        super("componentname");
    }
    
    public ComponentMultiSelectField(String width) {
    	super("componentname", width);
    }

    @Override
    protected void initData() {
        ComponentSearchCriteria searchCriteria = new ComponentSearchCriteria();

        SimpleProject project = (SimpleProject) AppContext
                .getVariable("project");
        searchCriteria.setProjectid(new NumberSearchField(SearchField.AND,
                project.getId()));

        ComponentService componentService = AppContext
                .getSpringBean(ComponentService.class);
        dataList = componentService
                .findPagableListByCriteria(new SearchRequest<ComponentSearchCriteria>(
                searchCriteria, 0, Integer.MAX_VALUE));

        
    }
}
