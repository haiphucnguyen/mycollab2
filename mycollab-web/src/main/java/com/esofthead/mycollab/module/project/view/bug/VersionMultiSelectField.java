/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.VersionService;
import com.esofthead.mycollab.web.AppContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class VersionMultiSelectField extends MultiSelectComp {

    private HashMap<String, Version> hashMapVersion;

    public VersionMultiSelectField() {
        super();
    }

    @Override
    protected void initData() {
        VersionSearchCriteria searchCriteria = new VersionSearchCriteria();

        SimpleProject project = (SimpleProject) AppContext
                .getVariable("project");
        searchCriteria.setProjectId(new NumberSearchField(SearchField.AND,
                project.getId()));

        VersionService versionService = AppContext.getSpringBean(VersionService.class);
        List<Version> lstVersion = versionService.findPagableListByCriteria(new SearchRequest<VersionSearchCriteria>(searchCriteria, 0, Integer.MAX_VALUE));
        List<String> lstVersionName = new ArrayList<String>();


        for (int i = 0; i < lstVersion.size(); i++) {
            Version version = lstVersion.get(i);
            lstVersionName.add(version.getVersionname());
        }
    }

    @Override
    protected String getDisplaySelectedItemsString() {
        StringBuilder str = new StringBuilder();
        for (Object obj : selectedItems) {
            Version version = (Version) obj;
            str.append(version.getVersionname());
        }
        return str.toString();
    }
}
