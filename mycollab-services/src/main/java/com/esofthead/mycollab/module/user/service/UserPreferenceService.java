/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.service;

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.module.user.domain.UserPreference;

/**
 * 
 * @author haiphucnguyen
 */
public interface UserPreferenceService extends
		ICrudService<Integer, UserPreference> {
	@Cacheable
	UserPreference getPreferenceOfUser(String username, @CacheKey int accountId);
}
