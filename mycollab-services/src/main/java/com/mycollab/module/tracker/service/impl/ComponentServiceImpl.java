package com.mycollab.module.tracker.service.impl;

import com.mycollab.aspect.ClassInfo;
import com.mycollab.aspect.ClassInfoMap;
import com.mycollab.aspect.Traceable;
import com.mycollab.common.ModuleNameConstants;
import com.mycollab.core.MyCollabException;
import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.ISearchableDAO;
import com.mycollab.db.persistence.service.DefaultService;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.tracker.dao.ComponentMapper;
import com.mycollab.module.tracker.dao.ComponentMapperExt;
import com.mycollab.module.tracker.domain.Component;
import com.mycollab.module.tracker.domain.ComponentExample;
import com.mycollab.module.tracker.domain.SimpleComponent;
import com.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.mycollab.module.tracker.service.ComponentService;
import com.google.common.eventbus.AsyncEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author MyCollab Ltd.
 * @since 1.0.0
 */
@Service
@Transactional
@Traceable(nameField = "name", extraFieldName = "projectid")
public class ComponentServiceImpl extends DefaultService<Integer, Component, ComponentSearchCriteria> implements ComponentService {
    static {
        ClassInfoMap.put(ComponentServiceImpl.class, new ClassInfo(ModuleNameConstants.PRJ, ProjectTypeConstants.BUG_COMPONENT));
    }

    @Autowired
    private ComponentMapper componentMapper;
    @Autowired
    private ComponentMapperExt componentMapperExt;
    @Autowired
    private AsyncEventBus asyncEventBus;

    @Override
    public ICrudGenericDAO<Integer, Component> getCrudMapper() {
        return componentMapper;
    }

    @Override
    public ISearchableDAO<ComponentSearchCriteria> getSearchMapper() {
        return componentMapperExt;
    }

    @Override
    public SimpleComponent findById(Integer componentId, Integer sAccountId) {
        return componentMapperExt.findComponentById(componentId);
    }

    @Override
    public Integer saveWithSession(Component record, String username) {
        // check whether there is exiting record
        ComponentExample ex = new ComponentExample();
        ex.createCriteria().andNameEqualTo(record.getName()).andProjectidEqualTo(record.getProjectid());

        Long count = componentMapper.countByExample(ex);
        if (count > 0) {
            throw new MyCollabException("There is an existing record has name " + record.getName());
        } else {
            return super.saveWithSession(record, username);
        }
    }
}