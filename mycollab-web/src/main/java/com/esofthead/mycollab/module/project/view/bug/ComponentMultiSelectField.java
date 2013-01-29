/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import java.util.HashMap;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.CheckBox;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class ComponentMultiSelectField extends MultiSelectComp {
	
	private HashMap<String, CheckBox> componentPoupMap = new HashMap<String, CheckBox>();

	public ComponentMultiSelectField() {
		super();
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

		for (int i = 0; i < dataList.size(); i++) {

			final CheckBox chkItem = new CheckBox(
					((Component) dataList.get(i)).getComponentname());
			chkItem.addListener(new ValueChangeListener() {
				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent event) {
					Boolean value = (Boolean) chkItem.getValue();
					Component component = getComponentInListByName(chkItem
							.getCaption());
					if (component != null) {
						removeElementByName(component.getComponentname());
						if (value) {
							if (!selectedItemsList.contains(component)) {
								selectedItemsList.add(component);
							}
						}
						setSelectedItems(selectedItemsList);
					}
				}
			});
			if (!componentPoupMap.containsKey(chkItem.getCaption())) {
	    		componentPoupMap.put(chkItem.getCaption(), chkItem);
	    		addItemToComponent(chkItem);
	    	}
		}
	}
	
	private void removeElementByName(String name) {
		for (int i = 0; i < selectedItemsList.size(); i++) {
			Component component = (Component) selectedItemsList.get(i);
			if (component.getComponentname().equals(name)) {
				selectedItemsList.remove(i);
				break;
			}
		}
	}

	private Component getComponentInListByName(String name) {
		Component componentReturn = null;
		for (int i = 0; i < dataList.size(); i++) {
			Component component = (Component) dataList.get(i);
			if (component.getComponentname().equals(name)) {
				componentReturn = component;
			}
		}
		return componentReturn;
	}

	protected void setSelectedComponentsDisplay() {
		for (int i = 0; i < selectedItemsList.size(); i++) {
			Component comp = (Component)selectedItemsList.get(i);
			System.out.println("aaaa " + comp.getId());
			if (componentPoupMap.containsKey(comp.getComponentname())) {
				CheckBox chk = componentPoupMap.get(comp.getComponentname());
				chk.setValue(true);
			}
		}
	}

	@Override
	protected String getDisplaySelectedItemsString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < selectedItemsList.size(); i++) {
			Component component = (Component) selectedItemsList.get(i);
			if (i == selectedItemsList.size() - 1) {
				str.append(component.getComponentname());
			} else {
				str.append(component.getComponentname() + ", ");
			}
		}
		return str.toString();
	}

}
