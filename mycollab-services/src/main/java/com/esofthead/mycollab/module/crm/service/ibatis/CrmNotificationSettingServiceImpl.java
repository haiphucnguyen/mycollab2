package com.esofthead.mycollab.module.crm.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.module.crm.dao.CrmNotificationSettingMapper;
import com.esofthead.mycollab.module.crm.domain.CrmNotificationSetting;
import com.esofthead.mycollab.module.crm.domain.CrmNotificationSettingExample;
import com.esofthead.mycollab.module.crm.service.CrmNotificationSettingService;

@Service
public class CrmNotificationSettingServiceImpl extends
		DefaultCrudService<Integer, CrmNotificationSetting> implements
		CrmNotificationSettingService {

	@Autowired
	private CrmNotificationSettingMapper crmNotificationSettingMapper;

	@Override
	@Cacheable
	public CrmNotificationSetting findNotification(String username,
			@CacheKey Integer sAccountId) {
		CrmNotificationSettingExample ex = new CrmNotificationSettingExample();
		ex.createCriteria().andUsernameEqualTo(username)
				.andSaccountidEqualTo(sAccountId);
		List<CrmNotificationSetting> lst = crmNotificationSettingMapper
				.selectByExample(ex);
		if (lst != null && lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public ICrudGenericDAO<Integer, CrmNotificationSetting> getCrudMapper() {
		return crmNotificationSettingMapper;
	}

	@Override
	@Cacheable
	public List<CrmNotificationSetting> findNotifications(
			@CacheKey Integer sAccountId) {
		CrmNotificationSettingExample ex = new CrmNotificationSettingExample();
		ex.createCriteria().andSaccountidEqualTo(sAccountId);
		return crmNotificationSettingMapper.selectByExample(ex);
	}

}
