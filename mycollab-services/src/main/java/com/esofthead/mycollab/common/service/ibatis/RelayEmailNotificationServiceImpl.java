package com.esofthead.mycollab.common.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.dao.RelayEmailNotificationMapper;
import com.esofthead.mycollab.common.dao.RelayEmailNotificationMapperExt;
import com.esofthead.mycollab.common.domain.RelayEmailNotification;
import com.esofthead.mycollab.common.domain.criteria.RelayEmailNotificationSearchCriteria;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;

@Service
public class RelayEmailNotificationServiceImpl
		extends
		DefaultService<Integer, RelayEmailNotification, RelayEmailNotificationSearchCriteria>
		implements RelayEmailNotificationService {

	@Autowired
	private RelayEmailNotificationMapper relayEmailNotificationMapper;
	@Autowired
	private RelayEmailNotificationMapperExt relayEmailNotificationMapperExt;

	@Override
	public ICrudGenericDAO getCrudMapper() {
		return relayEmailNotificationMapper;
	}

	@Override
	public ISearchableDAO<RelayEmailNotificationSearchCriteria> getSearchMapper() {
		return relayEmailNotificationMapperExt;
	}
}
