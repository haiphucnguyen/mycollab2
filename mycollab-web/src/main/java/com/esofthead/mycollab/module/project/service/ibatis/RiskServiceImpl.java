package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.project.ChangeLogAction;
import com.esofthead.mycollab.module.project.ChangeLogSource;
import com.esofthead.mycollab.module.project.dao.RiskMapperExt;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ChangeLogService;
import com.esofthead.mycollab.module.project.service.RiskService;

public class RiskServiceImpl extends DefaultCrudService<Integer, Risk>
		implements RiskService {

	private RiskMapperExt riskExtDAO;

	private ChangeLogService changeLogService;

	public void setRiskExtDAO(RiskMapperExt riskExtDAO) {
		this.riskExtDAO = riskExtDAO;
	}

	public void setChangeLogService(ChangeLogService changeLogService) {
		this.changeLogService = changeLogService;
	}

	@Override
	public List findPagableListByCriteria(RiskSearchCriteria criteria,
			int skipNum, int maxResult) {
		return riskExtDAO.findPagableList(criteria, new RowBounds(skipNum,
				maxResult));
	}

	@Override
	public int getTotalCount(RiskSearchCriteria criteria) {
		return riskExtDAO.getTotalCount(criteria);
	}

	@Override
	protected void internalSaveWithSession(Risk risk, String username) {
		riskExtDAO.insertAndReturnKey(risk);
		int riskid = risk.getId();
		changeLogService.saveChangeLog(risk.getProjectid(), username,
				ChangeLogSource.RISK, riskid, ChangeLogAction.CREATE,
				risk.getRiskname());
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
