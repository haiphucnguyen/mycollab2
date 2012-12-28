/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.UserPreference;
import com.esofthead.mycollab.core.persistence.service.ICrudService;

/**
 *
 * @author haiphucnguyen
 */
public interface UserPreferenceService extends ICrudService<Integer, UserPreference> {
    UserPreference getPreferenceOfUser(String username);
    
    void updateLastTimeAccessed(UserPreference pref);
}
