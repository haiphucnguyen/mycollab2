package com.esofthead.mycollab.module.tracker.service.ibatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.tracker.RelatedItemConstants;
import com.esofthead.mycollab.module.tracker.dao.ComponentMapperExt;
import com.esofthead.mycollab.module.tracker.dao.RelatedItemMapper;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.RelatedItemExample;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.ComponentService;

public class ComponentServiceImpl extends
		DefaultCrudService<Integer, Component> implements ComponentService {

	private ComponentMapperExt componentExtDAO;

	private RelatedItemMapper relatedItemDAO;

	public void setRelatedItemDAO(RelatedItemMapper relatedItemDAO) {
		this.relatedItemDAO = relatedItemDAO;
	}

	public void setComponentExtDAO(ComponentMapperExt componentExtDAO) {
		this.componentExtDAO = componentExtDAO;
	}

	@Override
	public int remove(Integer primaryKey) {
		RelatedItemExample ex = new RelatedItemExample();
		ex.createCriteria().andTypeEqualTo(RelatedItemConstants.COMPONENT)
				.andRelateitemidEqualTo(primaryKey);
		relatedItemDAO.deleteByExample(ex);

		return super.remove(primaryKey);
	}

	@Override
	public int getTotalCount(ComponentSearchCriteria criteria) {
		return componentExtDAO.getTotalCount(criteria);
	}

	@Override
	public List findPagableListByCriteria(ComponentSearchCriteria criteria,
			int skipNum, int maxResult) {
		return componentExtDAO.findPagableList(criteria, new RowBounds(skipNum,
				maxResult));
	}

}
