package com.esofthead.mycollab.module.tracker.service.ibatis;

import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.tracker.RelatedItemConstants;
import com.esofthead.mycollab.module.tracker.dao.ComponentMapper;
import com.esofthead.mycollab.module.tracker.dao.ComponentMapperExt;
import com.esofthead.mycollab.module.tracker.dao.RelatedItemMapper;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.RelatedItemExample;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Traceable(module = "Project", nameField = "componentname", type = "Component")
public class ComponentServiceImpl extends DefaultService<Integer, Component, ComponentSearchCriteria> implements
        ComponentService {

    @Autowired
    private ComponentMapper componentMapper;
    @Autowired
    private ComponentMapperExt componentMapperExt;
    @Autowired
    private RelatedItemMapper relatedItemMapper;

    @Override
    public ICrudGenericDAO<Integer, Component> getCrudMapper() {
        return componentMapper;
    }

    @Override
    public ISearchableDAO<ComponentSearchCriteria> getSearchMapper() {
        return componentMapperExt;
    }
    
    

    @Override
    public int remove(Integer primaryKey) {
        RelatedItemExample ex = new RelatedItemExample();
        ex.createCriteria().andTypeEqualTo(RelatedItemConstants.COMPONENT)
                .andTypeidEqualTo(primaryKey);
        relatedItemMapper.deleteByExample(ex);

        return super.remove(primaryKey);
    }

    @Override
    public SimpleComponent findComponentById(int componentId) {
        return componentMapperExt.findComponentById(componentId);
    }
}
