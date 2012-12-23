package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultService;
import com.esofthead.mycollab.module.project.dao.RiskMapper;
import com.esofthead.mycollab.module.project.dao.RiskMapperExt;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.service.RiskService;

@Service
public class RiskServiceImpl extends
		DefaultService<Integer, Risk, RiskSearchCriteria> implements
		RiskService {

	@Autowired
	private RiskMapper riskMapper;

	@Autowired
	private RiskMapperExt riskMapperExt;

	@Override
	public ICrudGenericDAO<Integer, Risk> getCrudMapper() {
		return riskMapper;
	}

	@Override
	public ISearchableDAO<RiskSearchCriteria> getSearchMapper() {
		return riskMapperExt;
	}

	@Override
	public SimpleRisk findRiskById(int riskId) {
		return riskMapperExt.findRiskById(riskId);
	}
}
