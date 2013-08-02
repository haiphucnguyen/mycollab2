/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.criteria.RoleSearchCriteria;
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
public interface RoleListView extends View {

    void enableActionControls(int numOfSelectedItem);

    void disableActionControls();

    HasSearchHandlers<RoleSearchCriteria> getSearchHandlers();

    HasSelectionOptionHandlers getOptionSelectionHandlers();

    HasPopupActionHandlers getPopupActionHandlers();

    HasSelectableItemHandlers<Role> getSelectableItemHandlers();

    IPagedBeanTable<RoleSearchCriteria, Role> getPagedBeanTable();
}
