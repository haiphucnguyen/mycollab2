/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTaskCount;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;

/**
 * 
 * @author haiphucnguyen
 */
public interface ProjectGenericTaskMapper extends
		ISearchableDAO<ProjectGenericTaskSearchCriteria> {
	int getTotalCountFromProblem(
			@Param("searchCriteria") ProjectGenericTaskSearchCriteria criteria);

	int getTotalCountFromRisk(
			@Param("searchCriteria") ProjectGenericTaskSearchCriteria criteria);

	int getTotalCountFromBug(
			@Param("searchCriteria") ProjectGenericTaskSearchCriteria criteria);

	int getTotalCountFromTask(
			@Param("searchCriteria") ProjectGenericTaskSearchCriteria criteria);

	List<ProjectGenericTaskCount> findPagableTaskCountListByCriteria(
			@Param("searchCriteria") ProjectGenericTaskSearchCriteria criteria,
			RowBounds rowBounds);
}
