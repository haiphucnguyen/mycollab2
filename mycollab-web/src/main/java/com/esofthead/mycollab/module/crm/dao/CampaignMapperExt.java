package com.esofthead.mycollab.module.crm.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;

public interface CampaignMapperExt {

	List<SimpleCampaign> findPagableList(
			CampaignSearchCriteria criteria, RowBounds rowBounds);

	int getTotalCount(CampaignSearchCriteria criteria);

	SimpleCampaign findCampaignById(int campaignId);
}
