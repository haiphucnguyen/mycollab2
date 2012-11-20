package com.esofthead.mycollab.module.tracker.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.tracker.dao.RelatedItemMapper;
import com.esofthead.mycollab.module.tracker.domain.RelatedItem;
import com.esofthead.mycollab.module.tracker.service.RelatedItemService;

@Service
public class RelatedItemServiceImpl extends
		DefaultCrudService<Integer, RelatedItem> implements RelatedItemService {

	@Autowired
	private RelatedItemMapper relatedItemMapper;
	
	@Override
	public ICrudGenericDAO<Integer, RelatedItem> getCrudMapper() {
		return relatedItemMapper;
	}

}
