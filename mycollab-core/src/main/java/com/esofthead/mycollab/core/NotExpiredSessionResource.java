package com.esofthead.mycollab.core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 */
public class NotExpiredSessionResource {
    private String userSessionId;

    private Set<String> appIds;

    public NotExpiredSessionResource(String userSessionId) {
        this.userSessionId = userSessionId;
        appIds = new HashSet<String>();
    }

    public String getUserSessionId() {
        return userSessionId;
    }

    public Collection<String> getAppIds() {
        return appIds;
    }
}
