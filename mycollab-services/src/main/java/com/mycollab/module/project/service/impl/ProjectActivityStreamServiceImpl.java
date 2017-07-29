package com.mycollab.module.project.service.impl;

import com.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.module.project.dao.ProjectMapperExt;
import com.mycollab.module.project.domain.ProjectActivityStream;
import com.mycollab.module.project.service.ProjectActivityStreamService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectActivityStreamServiceImpl implements ProjectActivityStreamService {

    @Autowired
    private ProjectMapperExt projectMapperExt;

    @Override
    public int getTotalActivityStream(ActivityStreamSearchCriteria criteria) {
        return projectMapperExt.getTotalActivityStream(criteria);
    }

    @Override
    public List<ProjectActivityStream> getProjectActivityStreams(BasicSearchRequest<ActivityStreamSearchCriteria> searchRequest) {
        return projectMapperExt.getProjectActivityStreams(searchRequest.getSearchCriteria(),
                new RowBounds((searchRequest.getCurrentPage() - 1) * searchRequest.getNumberOfItems(),
                        searchRequest.getNumberOfItems()));
    }
}
