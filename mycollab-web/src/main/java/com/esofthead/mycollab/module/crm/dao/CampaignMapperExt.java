package com.esofthead.mycollab.module.crm.dao;

import org.apache.ibatis.annotations.Param;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.CampaignWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;

public interface CampaignMapperExt extends
		ISearchableDAO<CampaignSearchCriteria> {

	public abstract SimpleCampaign findCampaignById(int campaignId);
	public abstract void upateCampaignBySearchCriteria(@Param("record") CampaignWithBLOBs record, @Param("searchCriteria")SearchCriteria searchCriteria);
}
