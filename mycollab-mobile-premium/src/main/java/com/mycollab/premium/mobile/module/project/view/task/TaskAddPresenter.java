package com.mycollab.premium.mobile.module.project.view.task;

import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.mobile.module.project.ui.form.field.ProjectFormAttachmentUploadField;
import com.mycollab.mobile.module.project.view.AbstractProjectPresenter;
import com.mycollab.mobile.module.project.view.task.ITaskAddPresenter;
import com.mycollab.mobile.module.project.view.task.TaskAddView;
import com.mycollab.mobile.shell.events.ShellEvent;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleTask;
import com.mycollab.module.project.service.ProjectTaskService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.DefaultEditFormHandler;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 4.5.0
 */
public class TaskAddPresenter extends AbstractProjectPresenter<TaskAddView> implements ITaskAddPresenter {
    private static final long serialVersionUID = -1243069642966773053L;

    public TaskAddPresenter() {
        super(TaskAddView.class);
    }

    @Override
    protected void postInitView() {
        getView().getEditFormHandlers().addFormHandler(new DefaultEditFormHandler<SimpleTask>() {
            private static final long serialVersionUID = 9034340428921755073L;

            @Override
            public void onSave(SimpleTask bean) {
                saveTask(bean);
                EventBusFactory.getInstance().post(new ShellEvent.NavigateBack(this, null));
            }
        });
    }

    @Override
    protected void onGo(HasComponents navigator, ScreenData<?> data) {
        if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS)) {
            SimpleTask task = (SimpleTask) data.getParams();
            getView().editItem(task);
            super.onGo(navigator, data);
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }
    }

    private void saveTask(SimpleTask task) {
        ProjectTaskService taskService = AppContextUtil.getSpringBean(ProjectTaskService.class);

        task.setSaccountid(AppUI.getAccountId());
        task.setProjectid(CurrentProjectVariables.getProjectId());
        if (task.getPercentagecomplete() == null) {
            task.setPercentagecomplete(0d);
        } else if (task.getPercentagecomplete() == 100d) {
            task.setStatus(StatusI18nEnum.Closed.name());
        }

        if (task.getId() == null) {
            task.setCreateduser(UserUIContext.getUsername());
            int taskId = taskService.saveWithSession(task, UserUIContext.getUsername());
            ProjectFormAttachmentUploadField uploadField = getView().getAttachUploadField();
            uploadField.saveContentsToRepo(CurrentProjectVariables.getProjectId(), ProjectTypeConstants.TASK, taskId);
        } else {
            taskService.updateWithSession(task, UserUIContext.getUsername());
            ProjectFormAttachmentUploadField uploadField = getView().getAttachUploadField();
            uploadField.saveContentsToRepo();
        }
    }
}
