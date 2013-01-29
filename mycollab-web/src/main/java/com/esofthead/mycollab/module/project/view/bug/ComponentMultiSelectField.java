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
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import com.esofthead.mycollab.web.AppContext;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class ComponentMultiSelectField extends MultiSelectComp {

    private HashMap<String, Component> hashMapComponent;

    public ComponentMultiSelectField() {
        super();
    }
    
    public void setComponentsDisplay(List<Component> lstComponent) {
    	for (int i = 0; i < lstComponent.size(); i++) {
			String item = lstComponent.get(i).getComponentname();
			if (lstComponent.get(i).getId() != null) {
				setSelectedItem(item);
			}
		}
    	initMapComponentData(lstComponent);
    }
    
    private void initMapComponentData(List<Component> lstComponent) {
    	hashMapComponent = new HashMap<String, Component>();
    	 for (int i = 0; i < lstComponent.size(); i++) {
    		 Component component = lstComponent.get(i);
    		 if (!hashMapComponent.containsKey(component.getComponentname()) && component.getId() != null) {
    			 hashMapComponent.put(component.getComponentname(), component);
    		 }
         }
    }
    

    @Override
    void initData() {
        ComponentSearchCriteria searchCriteria = new ComponentSearchCriteria();

        SimpleProject project = (SimpleProject) AppContext
                .getVariable("project");
        searchCriteria.setProjectid(new NumberSearchField(SearchField.AND,
                project.getId()));

        ComponentService componentService = AppContext
                .getSpringBean(ComponentService.class);
        List<Component> lstComponent = componentService
                .findPagableListByCriteria(new SearchRequest<ComponentSearchCriteria>(
                searchCriteria, 0, Integer.MAX_VALUE));
        List<String> lstComponentName = new ArrayList<String>();

        for (int i = 0; i < lstComponent.size(); i++) {
        	Component component = lstComponent.get(i);
            lstComponentName.add(component.getComponentname());
        }

        initMapComponentData(lstComponent);
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
