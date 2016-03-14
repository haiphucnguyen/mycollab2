package com.esofthead.mycollab.mobile.module.project.view;

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.module.project.events.ProjectEvent;
import com.esofthead.mycollab.mobile.mvp.AbstractPresenter;
import com.esofthead.mycollab.mobile.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
public abstract class ProjectGenericPresenter<V extends PageView> extends AbstractPresenter<V> {
    public ProjectGenericPresenter(Class<V> view) {
        super(view);
    }

    @Override
    protected void onErrorStopChain(Throwable throwable) {
        super.onErrorStopChain(throwable);
        if (this instanceof ProjectViewPresenter) {
            EventBusFactory.getInstance().post(new ShellEvent.GotoProjectModule(this, new String[]{"dashboard"}));
        } else {
            EventBusFactory.getInstance().post(new ProjectEvent.GotoDashboard(this, null));
        }
    }
}
