package com.esofthead.mycollab.module.tracker.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.common.interceptor.aspect.Auditable;
import com.esofthead.mycollab.common.interceptor.aspect.Traceable;
import com.esofthead.mycollab.common.interceptor.aspect.Watchable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.tracker.dao.BugMapper;
import com.esofthead.mycollab.module.tracker.dao.BugMapperExt;
import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.schedule.email.project.BugRelayEmailNotificationAction;

@Service
@Transactional
@Traceable(module = ModuleNameConstants.PRJ, nameField = "summary", type = ProjectContants.BUG, extraFieldName = "projectid")
@Auditable(module = ModuleNameConstants.PRJ, type = ProjectContants.BUG)
@Watchable(type = MonitorTypeConstants.PRJ_BUG, userFieldName = "assignuser", emailHandlerBean = BugRelayEmailNotificationAction.class)
public class BugServiceImpl extends
		DefaultService<Integer, BugWithBLOBs, BugSearchCriteria> implements
		BugService {
	@Autowired
	protected BugMapper bugMapper;
	@Autowired
	protected BugMapperExt bugMapperExt;

	@Override
	public ICrudGenericDAO<Integer, BugWithBLOBs> getCrudMapper() {
		return bugMapper;
	}

	@Override
	public ISearchableDAO<BugSearchCriteria> getSearchMapper() {
		return bugMapperExt;
	}

	@Override
	public int saveWithSession(BugWithBLOBs record, String username) {
		Integer maxKey = bugMapperExt.getMaxKey(record.getProjectid());
		if (maxKey == null) {
			record.setBugkey(1);
		} else {
			record.setBugkey(maxKey + 1);
		}

		return super.saveWithSession(record, username);
	}

	@Override
	public List<GroupItem> getStatusSummary(BugSearchCriteria criteria) {
		return bugMapperExt.getStatusSummary(criteria);
	}

	@Override
	public List<GroupItem> getPrioritySummary(BugSearchCriteria criteria) {
		return bugMapperExt.getPrioritySummary(criteria);
	}

	@Override
	public List<GroupItem> getAssignedDefectsSummary(BugSearchCriteria criteria) {
		return bugMapperExt.getAssignedDefectsSummary(criteria);
	}

	@Override
	public List<GroupItem> getReporterDefectsSummary(BugSearchCriteria criteria) {
		return bugMapperExt.getReporterDefectsSummary(criteria);
	}

	@Override
	public List<GroupItem> getResolutionDefectsSummary(
			BugSearchCriteria criteria) {
		return bugMapperExt.getResolutionDefectsSummary(criteria);
	}

	@Override
	public List<GroupItem> getComponentDefectsSummary(BugSearchCriteria criteria) {
		return bugMapperExt.getComponentDefectsSummary(criteria);
	}

	@Override
	public List<GroupItem> getVersionDefectsSummary(BugSearchCriteria criteria) {
		return bugMapperExt.getVersionDefectsSummary(criteria);
	}

	@Override
	public SimpleBug findById(int bugId, int sAccountId) {
		SimpleBug bug = bugMapperExt.getBugById(bugId);
		return bug;
	}

	@Override
	public List<GroupItem> getBugStatusTrendSummary(BugSearchCriteria criteria) {
		return bugMapperExt.getBugStatusTrendSummary(criteria);
	}
}
