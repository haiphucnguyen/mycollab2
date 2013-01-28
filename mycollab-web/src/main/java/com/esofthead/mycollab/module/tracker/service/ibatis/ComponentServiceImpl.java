package com.esofthead.mycollab.module.tracker.service.ibatis;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.interceptor.service.Auditable;
import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.tracker.dao.ComponentMapper;
import com.esofthead.mycollab.module.tracker.dao.ComponentMapperExt;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Traceable(module = ModuleNameConstants.PRJ, nameField = "componentname", type = ProjectContants.BUG_COMPONENT, extraFieldName = "projectid")
@Auditable(module = ModuleNameConstants.PRJ, type =  ProjectContants.BUG_COMPONENT)
public class ComponentServiceImpl extends DefaultService<Integer, Component, ComponentSearchCriteria> implements
        ComponentService {

    @Autowired
    private ComponentMapper componentMapper;
    @Autowired
    private ComponentMapperExt componentMapperExt;

    @Override
    public ICrudGenericDAO<Integer, Component> getCrudMapper() {
        return componentMapper;
    }

    @Override
    public ISearchableDAO<ComponentSearchCriteria> getSearchMapper() {
        return componentMapperExt;
    }

    @Override
    public SimpleComponent findComponentById(int componentId) {
        return componentMapperExt.findComponentById(componentId);
    }
}
