package com.esofthead.mycollab.module.project.dao;

import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.project.domain.ProjectActivityStream;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import java.util.List;
import org.apache.ibatis.session.RowBounds;

public interface ProjectMapperExt extends ISearchableDAO<ProjectSearchCriteria> {

    List<SimpleProject> getInvolvedProjectOfUser(String username);

    List<String> getUserMailsOfProject(int projectid);

    int getTotalActivityStream(ActivityStreamSearchCriteria criteria);

    List<ProjectActivityStream> getProjectActivityStreams(ActivityStreamSearchCriteria criteria, RowBounds rowBounds);
}
