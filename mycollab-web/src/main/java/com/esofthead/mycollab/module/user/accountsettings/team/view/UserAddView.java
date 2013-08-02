/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import java.util.Date;

import com.esofthead.mycollab.core.utils.TimezoneMapper.TimezoneExt;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;

/**
 * 
 * @author haiphucnguyen
 */
public interface UserAddView extends IFormAddView<SimpleUser> {

	HasEditFormHandlers<SimpleUser> getEditFormHandlers();

	Date getBirthday();

	TimezoneExt getTimezone();
}
