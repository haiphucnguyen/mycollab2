package com.mycollab.pro.module.project.view.client;

import com.mycollab.common.domain.SimpleClient;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.vaadin.web.ui.AdvancedPreviewBeanForm;
import com.mycollab.vaadin.web.ui.DefaultDynaFormLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class ClientPreviewForm extends AdvancedPreviewBeanForm<SimpleClient> {
    @Override
    public void setBean(SimpleClient bean) {
        this.setFormLayoutFactory(new DefaultDynaFormLayout(ProjectTypeConstants.CLIENT, ClientDefaultDynaFormLayoutFactory.getForm()));
        this.setBeanFormFieldFactory(new ClientReadFormFieldFactory(this));
        super.setBean(bean);
    }
}
