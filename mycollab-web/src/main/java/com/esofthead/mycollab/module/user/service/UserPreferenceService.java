/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.service;

import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.module.user.domain.UserPreference;

/**
 *
 * @author haiphucnguyen
 */
public interface UserPreferenceService extends ICrudService<Integer, UserPreference> {
    UserPreference getPreferenceOfUser(String username);
}
