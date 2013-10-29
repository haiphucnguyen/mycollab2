/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.View;

/**
 * 
 * @author haiphucnguyen
 */
public interface UserListView extends View {
	void setSearchCriteria(UserSearchCriteria searchCriteria);
}
