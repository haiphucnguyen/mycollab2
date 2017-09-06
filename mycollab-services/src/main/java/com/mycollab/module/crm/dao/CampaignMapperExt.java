package com.mycollab.module.crm.dao;

import com.mycollab.db.persistence.IMassUpdateDAO;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.mycollab.module.crm.domain.SimpleCampaign;
import com.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public interface CampaignMapperExt extends ISearchableDAO<CampaignSearchCriteria>, IMassUpdateDAO<CampaignWithBLOBs, CampaignSearchCriteria> {

    SimpleCampaign findById(Integer campaignId);
}
