package com.mycollab.module.tracker.service.impl;


import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.service.DefaultCrudService;
import com.mycollab.module.project.i18n.OptionI18nEnum;
import com.mycollab.module.tracker.dao.RelatedBugMapper;
import com.mycollab.module.tracker.dao.RelatedBugMapperExt;
import com.mycollab.module.tracker.domain.RelatedBug;
import com.mycollab.module.tracker.domain.RelatedBugExample;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.module.tracker.domain.SimpleRelatedBug;
import com.mycollab.module.tracker.service.BugRelationService;
import com.mycollab.module.tracker.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0.0
 */
@Service
public class BugRelationServiceImpl extends DefaultCrudService<Integer, RelatedBug> implements BugRelationService {
    @Autowired
    private RelatedBugMapper relatedBugMapper;

    @Autowired
    private RelatedBugMapperExt relatedBugMapperExt;

    @Autowired
    private BugService bugService;

    @Override
    public ICrudGenericDAO<Integer, RelatedBug> getCrudMapper() {
        return relatedBugMapper;
    }

    public Integer saveWithSession(RelatedBug record, String username) {
        Integer bugId = record.getBugid();
        if (OptionI18nEnum.BugRelation.Duplicated.name().equals(record.getRelatetype())) {
            SimpleBug bug = bugService.findById(bugId, 0);
            if (bug != null) {
                bug.setStatus(OptionI18nEnum.BugStatus.Resolved.name());
                bug.setResolution(OptionI18nEnum.BugRelation.Duplicated.name());
                bugService.updateSelectiveWithSession(bug, username);
            }
        }
        return super.saveWithSession(record, username);
    }

    @Override
    public int removeDuplicatedBugs(Integer bugId) {
        RelatedBugExample ex = new RelatedBugExample();
        ex.createCriteria().andBugidEqualTo(bugId).andRelatetypeEqualTo(OptionI18nEnum.BugRelation.Duplicated.name());
        return relatedBugMapper.deleteByExample(ex);
    }

    @Override
    public List<SimpleRelatedBug> findRelatedBugs(Integer bugId) {
        return relatedBugMapperExt.findRelatedBugs(bugId);
    }
}
