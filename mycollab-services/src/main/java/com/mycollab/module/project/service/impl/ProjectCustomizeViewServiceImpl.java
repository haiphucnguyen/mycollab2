package com.mycollab.module.project.service.impl;

import com.mycollab.db.persistence.ICrudGenericDAO;
import com.mycollab.db.persistence.service.DefaultCrudService;
import com.mycollab.module.project.dao.ProjectCustomizeViewMapper;
import com.mycollab.module.project.domain.ProjectCustomizeView;
import com.mycollab.module.project.service.ProjectCustomizeViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd.
 * @since 4.4.0
 */
@Service
public class ProjectCustomizeViewServiceImpl extends DefaultCrudService<Integer, ProjectCustomizeView> implements ProjectCustomizeViewService {

    @Autowired
    private ProjectCustomizeViewMapper projectCustomizeMapper;

    @Override
    public ICrudGenericDAO<Integer, ProjectCustomizeView> getCrudMapper() {
        return projectCustomizeMapper;
    }

}
