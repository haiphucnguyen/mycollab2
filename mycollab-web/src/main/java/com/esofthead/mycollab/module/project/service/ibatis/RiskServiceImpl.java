package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultService;
import com.esofthead.mycollab.module.project.ChangeLogAction;
import com.esofthead.mycollab.module.project.ChangeLogSource;
import com.esofthead.mycollab.module.project.dao.RiskMapper;
import com.esofthead.mycollab.module.project.dao.RiskMapperExt;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ChangeLogService;
import com.esofthead.mycollab.module.project.service.RiskService;

@Service
public class RiskServiceImpl extends DefaultService<Integer, Risk, RiskSearchCriteria>
		implements RiskService {
	
	@Autowired
	private RiskMapper riskMapper;

	@Autowired
	private RiskMapperExt riskMapperExt;

	@Autowired
	private ChangeLogService changeLogService;

	@Override
	public ICrudGenericDAO<Integer, Risk> getCrudMapper() {
		return riskMapper;
	}

	@Override
	public ISearchableDAO<RiskSearchCriteria> getSearchMapper() {
		return riskMapperExt;
	}

	@Override
	protected int internalSaveWithSession(Risk risk, String username) {
		riskMapperExt.insertAndReturnKey(risk);
		int riskid = risk.getId();
		changeLogService.saveChangeLog(risk.getProjectid(), username,
				ChangeLogSource.RISK, riskid, ChangeLogAction.CREATE,
				risk.getRiskname());
		return riskid;
	}

	@Override
	protected int internalUpdateWithSession(Risk risk, String username) {
		changeLogService.saveChangeLog(risk.getProjectid(), username,
				ChangeLogSource.RISK, risk.getId(), ChangeLogAction.UPDATE,
				risk.getRiskname());
		return super.internalUpdateWithSession(risk, username);
	}

	@Override
	protected int internalRemoveWithSession(Integer primaryKey, String username) {
		Risk risk = this.findByPrimaryKey(primaryKey);
		changeLogService.saveChangeLog(risk.getProjectid(), username,
				ChangeLogSource.RISK, risk.getId(), ChangeLogAction.DELETE,
				risk.getRiskname());
		return super.internalRemoveWithSession(primaryKey, username);
	}
}
