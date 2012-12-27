/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;

/**
 *
 * @author haiphucnguyen
 */
public interface MilestoneListView extends View {

    void enableActionControls(int numOfSelectedItem);

    void disableActionControls();

    HasSearchHandlers<MilestoneSearchCriteria> getSearchHandlers();

    HasSelectionOptionHandlers getOptionSelectionHandlers();

    HasPopupActionHandlers getPopupActionHandlers();

    HasSelectableItemHandlers<SimpleMilestone> getSelectableItemHandlers();

    IPagedBeanTable<MilestoneService, MilestoneSearchCriteria, SimpleMilestone> getPagedBeanTable();
}
