/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.service.mybatis;

import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.module.user.dao.UserPreferenceMapper;
import com.esofthead.mycollab.module.user.domain.UserPreference;
import com.esofthead.mycollab.module.user.domain.UserPreferenceExample;
import com.esofthead.mycollab.module.user.service.UserPreferenceService;

/**
 * 
 * @author haiphucnguyen
 */
@Service
@Transactional
public class UserPreferenceServiceImpl extends
		DefaultCrudService<Integer, UserPreference> implements
		UserPreferenceService {

	@Autowired
	protected UserPreferenceMapper userPreferenceMapper;

	@Override
	public ICrudGenericDAO<Integer, UserPreference> getCrudMapper() {
		return userPreferenceMapper;
	}

	@Override
	public UserPreference getPreferenceOfUser(String username, int accountId) {
		UserPreferenceExample ex = new UserPreferenceExample();
		ex.createCriteria().andUsernameEqualTo(username)
				.andSaccountidEqualTo(accountId);
		List<UserPreference> userPreferences = userPreferenceMapper
				.selectByExample(ex);
		UserPreference pref = null;

		if (userPreferences != null && userPreferences.size() > 0) {
			pref = userPreferences.get(0);
		} else {
			// create default user preference then save to database
			pref = new UserPreference();
			pref.setLastaccessedtime(new GregorianCalendar().getTime());
			pref.setUsername(username);
			pref.setSaccountid(accountId);
			userPreferenceMapper.insert(pref);
		}

		return pref;
	}

}
