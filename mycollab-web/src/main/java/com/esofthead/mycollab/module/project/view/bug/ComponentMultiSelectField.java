/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import com.esofthead.mycollab.web.AppContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class ComponentMultiSelectField extends MultiSelectComp {

    private HashMap<String, SimpleComponent> hashMapComponent;

    public ComponentMultiSelectField() {
        super();
    }

    @Override
    void initData() {
        ComponentSearchCriteria searchCriteria = new ComponentSearchCriteria();

        SimpleProject project = (SimpleProject) AppContext
                .getVariable("project");
        searchCriteria.setProjectid(new NumberSearchField(SearchField.AND,
                project.getId()));

        ComponentService versionService = AppContext
                .getSpringBean(ComponentService.class);
        List<SimpleComponent> lstVersion = versionService
                .findPagableListByCriteria(new SearchRequest<ComponentSearchCriteria>(
                searchCriteria, 0, Integer.MAX_VALUE));
        List<String> lstComponentName = new ArrayList<String>();
        hashMapComponent = new HashMap<String, SimpleComponent>();

        for (int i = 0; i < lstVersion.size(); i++) {
            SimpleComponent component = lstVersion.get(i);
            lstComponentName.add(component.getComponentname());
            hashMapComponent.put(component.getComponentname(), component);
        }

        this.loadData(lstComponentName);
    }

    public List<Component> getSelectedComponents() {
        List<String> lstStr = getSelectedItem();
        List<Component> lstValues = new ArrayList<Component>();

        for (int i = 0; i < lstStr.size(); i++) {
            if (hashMapComponent.containsKey(lstStr.get(i))) {
                lstValues.add(hashMapComponent.get(lstStr.get(i)));
            }
        }

        return lstValues;
    }
}
