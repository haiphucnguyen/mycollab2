package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.RelayEmailNotification;
import com.esofthead.mycollab.common.interceptor.aspect.Auditable;
import com.esofthead.mycollab.common.interceptor.aspect.Traceable;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.dao.RiskMapper;
import com.esofthead.mycollab.module.project.dao.RiskMapperExt;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.schedule.email.project.ProjectProblemRelayEmailNotificationAction;

@Service
@Transactional
@Traceable(module = ModuleNameConstants.PRJ, nameField = "riskname", type = ProjectContants.RISK, extraFieldName = "projectid")
@Auditable(module = ModuleNameConstants.PRJ, type = ProjectContants.RISK)
public class RiskServiceImpl extends
		DefaultService<Integer, Risk, RiskSearchCriteria> implements
		RiskService {

	@Autowired
	private RiskMapper riskMapper;

	@Autowired
	private RiskMapperExt riskMapperExt;

	@Autowired
	private RelayEmailNotificationService relayEmailNotificationService;

	@Override
	public ICrudGenericDAO<Integer, Risk> getCrudMapper() {
		return riskMapper;
	}

	@Override
	public ISearchableDAO<RiskSearchCriteria> getSearchMapper() {
		return riskMapperExt;
	}

	@Override
	public SimpleRisk findById(int riskId, int sAccountId) {
		return riskMapperExt.findRiskById(riskId);
	}

	@Override
	public int saveWithSession(Risk record, String username) {
		int recordId = super.saveWithSession(record, username);
		relayEmailNotificationService.saveWithSession(
				createNotification(record, username, recordId,
						MonitorTypeConstants.CREATE_ACTION), username);
		return recordId;
	}

	@Override
	public int updateWithSession(Risk record, String username) {
		relayEmailNotificationService.saveWithSession(
				createNotification(record, username, record.getId(),
						MonitorTypeConstants.UPDATE_ACTION), username);
		return super.updateWithSession(record, username);
	}

	private RelayEmailNotification createNotification(Risk record,
			String username, int recordId, String action) {
		RelayEmailNotification relayNotification = new RelayEmailNotification();
		relayNotification.setChangeby(username);
		relayNotification.setChangecomment("");
		int sAccountId = record.getSaccountid();
		relayNotification.setSaccountid(sAccountId);
		relayNotification.setType(MonitorTypeConstants.PRJ_RISK);
		relayNotification.setAction(action);
		relayNotification
				.setEmailhandlerbean(ProjectProblemRelayEmailNotificationAction.class
						.getName());
		relayNotification.setTypeid(recordId);
		relayNotification.setExtratypeid(record.getProjectid());
		return relayNotification;
	}
}
