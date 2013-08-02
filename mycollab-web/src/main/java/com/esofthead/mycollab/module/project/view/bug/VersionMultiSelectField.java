/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.ui.components.MultiSelectComp;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.VersionService;
import com.esofthead.mycollab.web.AppContext;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class VersionMultiSelectField extends MultiSelectComp {

    public VersionMultiSelectField() {
        super("versionname");
    }
    
    public VersionMultiSelectField(String width) {
        super("versionname", width);
    }

    @Override
    protected void initData() {
        VersionSearchCriteria searchCriteria = new VersionSearchCriteria();

        SimpleProject project = (SimpleProject) AppContext
                .getVariable("project");
        searchCriteria.setProjectId(new NumberSearchField(SearchField.AND,
                project.getId()));

        VersionService versionService = AppContext
                .getSpringBean(VersionService.class);
        dataList = versionService
                .findPagableListByCriteria(new SearchRequest<VersionSearchCriteria>(
                searchCriteria, 0, Integer.MAX_VALUE));

    }
}
