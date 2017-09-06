package com.mycollab.common.service;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
public interface AppPropertiesService {
    String getSysId();

    Date getStartDate();

    String getEdition();
}
