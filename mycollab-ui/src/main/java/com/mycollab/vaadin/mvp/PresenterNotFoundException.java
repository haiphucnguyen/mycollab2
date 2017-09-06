package com.mycollab.vaadin.mvp;

import com.mycollab.core.MyCollabException;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
public class PresenterNotFoundException extends MyCollabException {
    public PresenterNotFoundException(String message) {
        super(message);
    }
}
