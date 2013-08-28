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
import com.esofthead.mycollab.module.project.dao.ProblemMapper;
import com.esofthead.mycollab.module.project.dao.ProblemMapperExt;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProblemService;
import com.esofthead.mycollab.schedule.email.project.ProjectProblemRelayEmailNotificationAction;

@Service
@Transactional
@Traceable(module = ModuleNameConstants.PRJ, nameField = "issuename", type = ProjectContants.PROBLEM, extraFieldName = "projectid")
@Auditable(module = ModuleNameConstants.PRJ, type = ProjectContants.PROBLEM)
public class ProblemServiceImpl extends
		DefaultService<Integer, Problem, ProblemSearchCriteria> implements
		ProblemService {

	@Autowired
	private ProblemMapper problemMapper;

	@Autowired
	private ProblemMapperExt problemMapperExt;

	@Autowired
	private RelayEmailNotificationService relayEmailNotificationService;

	@Override
	public ICrudGenericDAO<Integer, Problem> getCrudMapper() {
		return problemMapper;
	}

	@Override
	public ISearchableDAO<ProblemSearchCriteria> getSearchMapper() {
		return problemMapperExt;
	}

	@Override
	public SimpleProblem findById(Integer problemId, int sAccountId) {
		return problemMapperExt.findProblemById(problemId);
	}

	@Override
	public int saveWithSession(Problem record, String username) {
		int recordId = super.saveWithSession(record, username);
		relayEmailNotificationService.saveWithSession(
				createNotification(record, username, recordId,
						MonitorTypeConstants.CREATE_ACTION), username);
		return recordId;
	}

	@Override
	public int updateWithSession(Problem record, String username) {
		relayEmailNotificationService.saveWithSession(
				createNotification(record, username, record.getId(),
						MonitorTypeConstants.UPDATE_ACTION), username);
		return super.updateWithSession(record, username);
	}

	private RelayEmailNotification createNotification(Problem record,
			String username, int recordId, String action) {
		RelayEmailNotification relayNotification = new RelayEmailNotification();
		relayNotification.setChangeby(username);
		relayNotification.setChangecomment("");
		int sAccountId = record.getSaccountid();
		relayNotification.setSaccountid(sAccountId);
		relayNotification.setType(MonitorTypeConstants.PRJ_PROBLEM);
		relayNotification.setAction(action);
		relayNotification
				.setEmailhandlerbean(ProjectProblemRelayEmailNotificationAction.class
						.getName());
		relayNotification.setTypeid(recordId);
		relayNotification.setExtratypeid(record.getProjectid());
		return relayNotification;
	}
}
