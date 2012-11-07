package com.esofthead.mycollab.module.tracker.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;

public interface ComponentMapperExt {
	int getTotalCount(ComponentSearchCriteria criteria);

	List<SimpleComponent> findPagableList(ComponentSearchCriteria criteria,
			RowBounds rowBounds);

	List<Component> getComponentByRefKey(String refkey);
}
