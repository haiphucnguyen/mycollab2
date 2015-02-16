package com.esofthead.mycollab.module.tracker.service.ibatis;


import com.esofthead.mycollab.module.tracker.dao.RelatedBugMapper;
import com.esofthead.mycollab.module.tracker.dao.RelatedBugMapperExt;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BugRelationServiceImpl implements BugRelationService {
    @Autowired
    private RelatedBugMapper relatedBugMapper;

    @Autowired
    private RelatedBugMapperExt relatedBugMapperExt;

    @Override
    public void saveRelation(String relation, SimpleBug bug, SimpleBug relatedBug) {

    }
}
