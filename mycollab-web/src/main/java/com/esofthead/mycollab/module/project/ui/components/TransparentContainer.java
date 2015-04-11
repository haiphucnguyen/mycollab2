package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;

/**
 * @author MyCollab Ltd
 * @since 5.0.4
 */
public class TransparentContainer extends AbstractPageView {
    private ProjectView host;

    public ProjectView getHost() {
        return host;
    }

    public void setHost(ProjectView host) {
        this.host = host;
    }

    public TransparentContainer navigateToContainer(String viewId) {
        return (TransparentContainer) host.gotoSubView(viewId);
    }
}
