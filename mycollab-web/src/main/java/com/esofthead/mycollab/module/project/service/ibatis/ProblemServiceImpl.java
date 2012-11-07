package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.project.ChangeLogAction;
import com.esofthead.mycollab.module.project.ChangeLogSource;
import com.esofthead.mycollab.module.project.dao.ProblemMapperExt;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.service.ChangeLogService;
import com.esofthead.mycollab.module.project.service.ProblemService;

public class ProblemServiceImpl extends DefaultCrudService<Integer, Problem>
		implements ProblemService {

	private ProblemMapperExt problemExtDAO;

	private ChangeLogService changeLogService;

	public void setProblemExtDAO(ProblemMapperExt problemExtDAO) {
		this.problemExtDAO = problemExtDAO;
	}

	public void setChangeLogService(ChangeLogService changeLogService) {
		this.changeLogService = changeLogService;
	}

	@Override
	protected void internalSaveWithSession(Problem record, String username) {
		problemExtDAO.insertAndReturnKey(record);
		int recordid = record.getId();
		changeLogService.saveChangeLog(record.getProjectid(), username,
				ChangeLogSource.PROBLEM, recordid, ChangeLogAction.CREATE,
				record.getIssuename());
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
	public List findPagableListByCriteria(ProblemSearchCriteria criteria,
			int skipNum, int maxResult) {
		return problemExtDAO.findPagableList(criteria, new RowBounds(skipNum,
				maxResult));
	}

	@Override
	public int getTotalCount(ProblemSearchCriteria criteria) {
		return problemExtDAO.getTotalCount(criteria);
	}

}
