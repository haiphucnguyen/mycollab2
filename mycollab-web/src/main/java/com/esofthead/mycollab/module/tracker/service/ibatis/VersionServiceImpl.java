package com.esofthead.mycollab.module.tracker.service.ibatis;

import java.util.List;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.tracker.RelatedItemConstants;
import com.esofthead.mycollab.module.tracker.dao.RelatedItemMapper;
import com.esofthead.mycollab.module.tracker.dao.VersionMapper;
import com.esofthead.mycollab.module.tracker.domain.RelatedItemExample;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.VersionExample;
import com.esofthead.mycollab.module.tracker.service.VersionService;

public class VersionServiceImpl extends DefaultCrudService<Integer, Version>
		implements VersionService {

	private RelatedItemMapper relatedItemDAO;

	public void setRelatedItemDAO(RelatedItemMapper relatedItemDAO) {
		this.relatedItemDAO = relatedItemDAO;
	}

	@Override
	public List<Version> getVersionsOfProject(int projectid) {
		VersionExample ex = new VersionExample();
		ex.createCriteria().andProjectidEqualTo(projectid);
		return ((VersionMapper) daoObj).selectByExample(ex);
	}

	@Override
	public int remove(Integer primaryKey) {
		RelatedItemExample ex = new RelatedItemExample();
		ex.createCriteria()
				.andTypeEqualTo(RelatedItemConstants.AFFECTED_VERSION)
				.andRelateitemidEqualTo(primaryKey);
		ex.createCriteria().andTypeEqualTo(RelatedItemConstants.FIXED_VERSION)
				.andRelateitemidEqualTo(primaryKey);
		relatedItemDAO.deleteByExample(ex);

		return super.remove(primaryKey);
	}
}
