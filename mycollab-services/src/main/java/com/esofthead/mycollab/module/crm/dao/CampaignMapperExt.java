package com.esofthead.mycollab.module.crm.dao;

import com.esofthead.mycollab.core.persistence.IMassUpdateDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;

public interface CampaignMapperExt extends
		ISearchableDAO<CampaignSearchCriteria>,
		IMassUpdateDAO<CampaignWithBLOBs, CampaignSearchCriteria> {

	SimpleCampaign findById(int campaignId);
}
