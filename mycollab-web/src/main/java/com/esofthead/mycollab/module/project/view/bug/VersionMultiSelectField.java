/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.VersionService;
import com.esofthead.mycollab.web.AppContext;

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
	void initData() {
		VersionSearchCriteria searchCriteria = new VersionSearchCriteria();

        SimpleProject project = (SimpleProject) AppContext
                .getVariable("project");
        searchCriteria.setProjectId(new NumberSearchField(SearchField.AND,
                project.getId()));

        VersionService versionService = AppContext.getSpringBean(VersionService.class);
        List<Version> lstVersion = versionService.findPagableListByCriteria(new SearchRequest<VersionSearchCriteria>(searchCriteria, 0, Integer.MAX_VALUE));
        List<String> lstVersionName = new ArrayList<String>();
        hashMapVersion = new HashMap<String, Version>();

        for (int i = 0; i < lstVersion.size(); i++) {
            Version version = lstVersion.get(i);
            lstVersionName.add(version.getVersionname());
            hashMapVersion.put(version.getVersionname(), version);
        }

        this.loadData(lstVersionName);
	}
	
	public List<Version> getListSelectedVersion() {
		List<String> lstStr =  getSelectedItem();
		List<Version> lstValues = new ArrayList<Version>();
		
		for (int i = 0; i < lstStr.size(); i++) {
			if (hashMapVersion.containsKey(lstStr.get(i))) {
				lstValues.add(hashMapVersion.get(lstStr.get(i)));
			}
		}
		
		return lstValues;
	}
}
