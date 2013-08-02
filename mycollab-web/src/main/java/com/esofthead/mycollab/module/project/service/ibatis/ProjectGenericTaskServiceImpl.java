/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultSearchService;
import com.esofthead.mycollab.module.project.dao.ProjectGenericTaskMapper;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTaskCount;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;

/**
 * 
 * @author haiphucnguyen
 */
@Service
public class ProjectGenericTaskServiceImpl extends
		DefaultSearchService<ProjectGenericTaskSearchCriteria> implements
		ProjectGenericTaskService {

	@Autowired
	protected ProjectGenericTaskMapper projectGenericTaskMapper;

	@Override
	public ISearchableDAO<ProjectGenericTaskSearchCriteria> getSearchMapper() {
		return projectGenericTaskMapper;
	}

	@Override
	public int getTotalCount(ProjectGenericTaskSearchCriteria criteria) {
		return projectGenericTaskMapper.getTotalCountFromProblem(criteria)
				+ projectGenericTaskMapper.getTotalCountFromRisk(criteria)
				+ projectGenericTaskMapper.getTotalCountFromBug(criteria)
				+ projectGenericTaskMapper.getTotalCountFromTask(criteria);
	}

	@Override
	public List<ProjectGenericTaskCount> findPagableTaskCountListByCriteria(
			SearchRequest<ProjectGenericTaskSearchCriteria> searchRequest) {
		return projectGenericTaskMapper.findPagableTaskCountListByCriteria(
				searchRequest.getSearchCriteria(),
				new RowBounds((searchRequest.getCurrentPage() - 1)
						* searchRequest.getNumberOfItems(), searchRequest
						.getNumberOfItems()));
	}
}
