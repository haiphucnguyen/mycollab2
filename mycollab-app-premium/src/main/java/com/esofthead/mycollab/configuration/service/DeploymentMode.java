package com.esofthead.mycollab.configuration.service;

import com.esofthead.mycollab.configuration.IDeploymentMode;
import com.esofthead.mycollab.core.persistence.service.IService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@Service
@Order(value = 1)
public class DeploymentMode implements IDeploymentMode, IService {
    @Override
    public boolean isDemandEdition() {
        return false;
    }

    @Override
    public boolean isCommunityEdition() {
        return false;
    }
}
