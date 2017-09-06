package com.mycollab.module.crm.service.impl;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.db.persistence.service.DefaultService;
import com.mycollab.module.crm.dao.CrmTaskMapper;
import com.mycollab.module.crm.dao.TargetMapper;
import com.mycollab.module.crm.dao.TargetMapperExt;
import com.mycollab.module.crm.domain.SimpleTarget;
import com.mycollab.module.crm.domain.Target;
import com.mycollab.module.crm.domain.criteria.TargetSearchCriteria;
import com.mycollab.module.crm.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TargetServiceImpl extends DefaultService<Integer, Target, TargetSearchCriteria> implements TargetService {

    @Autowired
    private TargetMapper targetMapper;

    @Autowired
    private TargetMapperExt targetMapperExt;

    @Autowired
    private CrmTaskMapper taskMapper;

    @Override
    public ICrudGenericDAO<Integer, Target> getCrudMapper() {
        return targetMapper;
    }

    @Override
    public ISearchableDAO<TargetSearchCriteria> getSearchMapper() {
        return targetMapperExt;
    }

    public SimpleTarget findTargetById(int targetId) {
        return targetMapperExt.findTargetById(targetId);
    }

}
