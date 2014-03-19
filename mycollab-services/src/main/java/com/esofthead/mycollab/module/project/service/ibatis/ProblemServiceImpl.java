/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.cache.CacheUtils;
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
import com.esofthead.mycollab.module.project.service.ProjectActivityStreamService;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.schedule.email.project.ProjectProblemRelayEmailNotificationAction;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
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
		CacheUtils.cleanCaches(record.getSaccountid(), ProjectService.class,
				ProjectGenericTaskService.class,
				ProjectActivityStreamService.class);
		return recordId;
	}

	@Override
	public int updateWithSession(Problem record, String username) {
		relayEmailNotificationService.saveWithSession(
				createNotification(record, username, record.getId(),
						MonitorTypeConstants.UPDATE_ACTION), username);
		CacheUtils.cleanCaches(record.getSaccountid(),
				ProjectActivityStreamService.class);
		return super.updateWithSession(record, username);
	}

	@Override
	public int removeWithSession(Integer primaryKey, String username,
			int accountId) {
		CacheUtils.cleanCaches(accountId, ProjectService.class,
				ProjectGenericTaskService.class,
				ProjectActivityStreamService.class);
		return super.removeWithSession(primaryKey, username, accountId);
	}

	@Override
	public void removeByCriteria(ProblemSearchCriteria criteria, int accountId) {
		CacheUtils.cleanCaches(accountId, ProjectService.class,
				ProjectGenericTaskService.class,
				ProjectActivityStreamService.class);
		super.removeByCriteria(criteria, accountId);
	}

	@Override
	public void massRemoveWithSession(List<Integer> primaryKeys,
			String username, int accountId) {
		CacheUtils.cleanCaches(accountId, ProjectService.class,
				ProjectGenericTaskService.class,
				ProjectActivityStreamService.class);
		super.massRemoveWithSession(primaryKeys, username, accountId);
	}

	@Override
	public void massUpdateWithSession(Problem record,
			List<Integer> primaryKeys, int accountId) {
		CacheUtils.cleanCaches(accountId, ProjectActivityStreamService.class);
		super.massUpdateWithSession(record, primaryKeys, accountId);
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
