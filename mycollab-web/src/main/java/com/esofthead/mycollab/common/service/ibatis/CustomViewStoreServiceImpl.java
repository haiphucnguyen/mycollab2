package com.esofthead.mycollab.common.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.dao.CustomViewStoreMapper;
import com.esofthead.mycollab.common.domain.CustomViewStore;
import com.esofthead.mycollab.common.service.CustomViewStoreService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;

@Service
public class CustomViewStoreServiceImpl extends
		DefaultCrudService<Integer, CustomViewStore> implements
		CustomViewStoreService {
	@Autowired
	private CustomViewStoreMapper customViewStoreMapper;

	@Override
	public ICrudGenericDAO<Integer, CustomViewStore> getCrudMapper() {
		return customViewStoreMapper;
	}

}
