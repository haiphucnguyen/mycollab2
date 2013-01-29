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
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.VersionService;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.CheckBox;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
//TODO: Refactor code for simpler
public class VersionMultiSelectField extends MultiSelectComp {

    private HashMap<String, CheckBox> componentPoupMap = new HashMap<String, CheckBox>();

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

        VersionService versionService = AppContext
                .getSpringBean(VersionService.class);
        dataList = versionService
                .findPagableListByCriteria(new SearchRequest<VersionSearchCriteria>(
                searchCriteria, 0, Integer.MAX_VALUE));

        for (int i = 0; i < dataList.size(); i++) {

            final CheckBox chkItem = new CheckBox(
                    ((Version) dataList.get(i)).getVersionname());
            chkItem.setImmediate(true);
            chkItem.addListener(new ValueChangeListener() {
                @Override
                public void valueChange(
                        com.vaadin.data.Property.ValueChangeEvent event) {
                    Boolean value = (Boolean) chkItem.getValue();
                    Version version = getVersionInListByName(chkItem
                            .getCaption());
                    if (version != null) {
                        if (isClicked) {
                            removeElementByName(version.getVersionname());
                            if (value) {
                                if (!selectedItemsList.contains(version)) {
                                    selectedItemsList.add(version);
                                }
                            }
                            setSelectedItems(selectedItemsList);
                        }
                    }
                }
            });
            if (!componentPoupMap.containsKey(chkItem.getCaption())) {
                componentPoupMap.put(chkItem.getCaption(), chkItem);
                addItemToComponent(chkItem);
            }
        }

    }

    private Version getVersionInListByName(String name) {
        Version versionReturn = null;
        for (int i = 0; i < dataList.size(); i++) {
            Version version = (Version) dataList.get(i);
            if (version.getVersionname().equals(name)) {
                versionReturn = version;
            }
        }
        return versionReturn;
    }

    private void removeElementByName(String name) {
        for (int i = 0; i < selectedItemsList.size(); i++) {
            Version version = (Version) selectedItemsList.get(i);
            if (version.getVersionname().equals(name)) {
                selectedItemsList.remove(i);
                break;
            }
        }
    }

    protected void setSelectedComponentsDisplay() {
        for (int i = 0; i < selectedItemsList.size(); i++) {
            Version comp = (Version) selectedItemsList.get(i);
            if (componentPoupMap.containsKey(comp.getVersionname())) {
                CheckBox chk = componentPoupMap.get(comp.getVersionname());
                chk.setValue(true);
            }
        }
    }

    @Override
    protected String getDisplaySelectedItemsString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < selectedItemsList.size(); i++) {
            Version version = (Version) selectedItemsList.get(i);
            if (i == selectedItemsList.size() - 1) {
                str.append(version.getVersionname());
            } else {
                str.append(version.getVersionname() + ", ");
            }
        }
        return str.toString();
    }
}
