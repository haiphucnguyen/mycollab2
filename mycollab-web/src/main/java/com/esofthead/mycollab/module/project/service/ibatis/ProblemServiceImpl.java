package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultService;
import com.esofthead.mycollab.module.project.ChangeLogAction;
import com.esofthead.mycollab.module.project.ChangeLogSource;
import com.esofthead.mycollab.module.project.dao.ProblemMapper;
import com.esofthead.mycollab.module.project.dao.ProblemMapperExt;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.service.ChangeLogService;
import com.esofthead.mycollab.module.project.service.ProblemService;

@Service
public class ProblemServiceImpl extends
		DefaultService<Integer, Problem, ProblemSearchCriteria> implements
		ProblemService {

	@Autowired
	private ProblemMapper problemMapper;

	@Autowired
	private ProblemMapperExt problemMapperExt;

	@Autowired
	private ChangeLogService changeLogService;

	@Override
	public ICrudGenericDAO<Integer, Problem> getCrudMapper() {
		return problemMapper;
	}

	@Override
	public ISearchableDAO<ProblemSearchCriteria> getSearchMapper() {
		return problemMapperExt;
	}

	@Override
	protected int internalSaveWithSession(Problem record, String username) {
		problemMapperExt.insertAndReturnKey(record);
		int recordid = record.getId();
		changeLogService.saveChangeLog(record.getProjectid(), username,
				ChangeLogSource.PROBLEM, recordid, ChangeLogAction.CREATE,
				record.getIssuename());
		return recordid;
	}

	@Override
	protected int internalUpdateWithSession(Problem record, String username) {
		changeLogService.saveChangeLog(record.getProjectid(), username,
				ChangeLogSource.PROBLEM, record.getId(),
				ChangeLogAction.UPDATE, record.getIssuename());
		return super.internalUpdateWithSession(record, username);
	}

	@Override
	protected int internalRemoveWithSession(Integer primaryKey, String username) {
		Problem record = findByPrimaryKey(primaryKey);
		changeLogService.saveChangeLog(record.getProjectid(), username,
				ChangeLogSource.PROBLEM, record.getId(),
				ChangeLogAction.DELETE, record.getIssuename());
		return super.internalRemoveWithSession(primaryKey, username);
	}

	@Override
	public SimpleProblem findProblemById(Integer problemId) {
		return problemMapperExt.findProblemById(problemId);
	}

}
