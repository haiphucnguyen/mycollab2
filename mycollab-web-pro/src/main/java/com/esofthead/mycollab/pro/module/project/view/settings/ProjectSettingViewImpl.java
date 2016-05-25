package com.esofthead.mycollab.pro.module.project.view.settings;

import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting;
import com.esofthead.mycollab.module.project.view.settings.ProjectNotificationSettingViewComponent;
import com.esofthead.mycollab.module.project.view.settings.ProjectSettingView;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
@ViewComponent
public class ProjectSettingViewImpl extends AbstractPageView implements ProjectSettingView {
    private static final long serialVersionUID = 1L;

    private final MHorizontalLayout mainBody;

    public ProjectSettingViewImpl() {
        this.setWidth("100%");
        this.setSpacing(true);
        this.addStyleName("readview-layout");

        mainBody = new MHorizontalLayout().withMargin(true).withFullWidth();
        this.addComponent(mainBody);
    }

    @Override
    public void showNotificationSettings(ProjectNotificationSetting notification) {
        mainBody.removeAllComponents();

        if (notification == null) {
            notification = new ProjectNotificationSetting();
        }
        ProjectNotificationSettingViewComponent component = new ProjectNotificationSettingViewComponent(notification);
        mainBody.addComponent(component);
        mainBody.addComponent(new CustomizeFeatureComponent());
    }
}
