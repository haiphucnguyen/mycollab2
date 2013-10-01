/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.esofthead.mycollab.module.project.dao.MilestoneMapper;
import com.esofthead.mycollab.module.project.dao.MilestoneMapperExt;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.schedule.email.project.ProjectMilestoneRelayEmailNotificationAction;

/**
 * 
 * @author haiphucnguyen
 */
@Service
@Transactional
@Traceable(module = ModuleNameConstants.PRJ, type = ProjectContants.MILESTONE, nameField = "name", extraFieldName = "projectid")
@Auditable(module = ModuleNameConstants.PRJ, type = ProjectContants.MILESTONE)
public class MilestoneServiceImpl extends
		DefaultService<Integer, Milestone, MilestoneSearchCriteria> implements
		MilestoneService {

	@Autowired
	protected MilestoneMapper milestoneMapper;
	
	@Autowired
	protected MilestoneMapperExt milestoneMapperExt;
	
	@Autowired
	private RelayEmailNotificationService relayEmailNotificationService;

	@Override
	public ICrudGenericDAO<Integer, Milestone> getCrudMapper() {
		return milestoneMapper;
	}

	@Override
	public ISearchableDAO<MilestoneSearchCriteria> getSearchMapper() {
		return milestoneMapperExt;
	}

	@Override
	public SimpleMilestone findById(int milestoneId, int sAccountId) {
		return milestoneMapperExt.findById(milestoneId);
	}

	@Override
	public int saveWithSession(Milestone record, String username) {
		int recordId = super.saveWithSession(record, username);
		relayEmailNotificationService.saveWithSession(
				createNotification(record, username, recordId,
						MonitorTypeConstants.CREATE_ACTION), username);
		return recordId;
	}

	@Override
	public int updateWithSession(Milestone record, String username) {
		relayEmailNotificationService.saveWithSession(
				createNotification(record, username, record.getId(),
						MonitorTypeConstants.UPDATE_ACTION), username);
		return super.updateWithSession(record, username);
	}

	private RelayEmailNotification createNotification(Milestone record,
			String username, int recordId, String action) {
		RelayEmailNotification relayNotification = new RelayEmailNotification();
		relayNotification.setChangeby(username);
		relayNotification.setChangecomment("");
		relayNotification.setAction(action);
		relayNotification.setSaccountid(record.getSaccountid());
		relayNotification.setType(MonitorTypeConstants.PRJ_MILESTONE);
		relayNotification
				.setEmailhandlerbean(ProjectMilestoneRelayEmailNotificationAction.class
						.getName());
		relayNotification.setTypeid(recordId);
		relayNotification.setExtratypeid(record.getProjectid());
		return relayNotification;
	}
}
