package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.core.persistence.service.IService;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
public interface ProjectTemplateService extends IService {
    void markProjectAsTemplate(Integer projectId);
}
