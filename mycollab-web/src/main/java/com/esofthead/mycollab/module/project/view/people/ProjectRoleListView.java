/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.module.project.domain.ProjectRole;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;

/**
 *
 * @author haiphucnguyen
 */
public interface ProjectRoleListView extends View {
	void enableActionControls(int numOfSelectedItem);

    void disableActionControls();

    HasSearchHandlers<ProjectRoleSearchCriteria> getSearchHandlers();

    HasSelectionOptionHandlers getOptionSelectionHandlers();

    HasPopupActionHandlers getPopupActionHandlers();

    HasSelectableItemHandlers<ProjectRole> getSelectableItemHandlers();

    IPagedBeanTable<ProjectRoleSearchCriteria, ProjectRole> getPagedBeanTable();
}
