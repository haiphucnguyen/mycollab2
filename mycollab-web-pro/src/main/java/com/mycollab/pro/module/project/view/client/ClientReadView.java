package com.mycollab.pro.module.project.view.client;

import com.mycollab.common.domain.SimpleClient;
import com.mycollab.vaadin.event.HasPreviewFormHandlers;
import com.mycollab.vaadin.mvp.IPreviewView;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public interface ClientReadView extends IPreviewView<SimpleClient> {
    HasPreviewFormHandlers<SimpleClient> getPreviewFormHandlers();
}
