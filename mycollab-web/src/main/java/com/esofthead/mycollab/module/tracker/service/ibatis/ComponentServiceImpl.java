package com.esofthead.mycollab.module.tracker.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.interceptor.service.Auditable;
import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.tracker.dao.ComponentMapper;
import com.esofthead.mycollab.module.tracker.dao.ComponentMapperExt;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.ComponentExample;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.ComponentService;

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
    public SimpleComponent findById(int componentId) {
        return componentMapperExt.findComponentById(componentId);
    }

    @Override
    public int saveWithSession(Component record, String username) {
        //check whether there is exiting record
        ComponentExample ex = new ComponentExample();
        
        ex.createCriteria().andComponentnameEqualTo(record.getComponentname()).andProjectidEqualTo(record.getProjectid());
        
        int count = componentMapper.countByExample(ex);
        if (count > 0) {
            throw new MyCollabException("There is an existing record has name " + record.getComponentname());
        } else {
            return super.saveWithSession(record, username);
        }
        
    }
    
    
}
