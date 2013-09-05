/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.ListView;

/**
 * 
 * @author haiphucnguyen
 */
public interface UserListView extends ListView<UserSearchCriteria, SimpleUser> {
}
