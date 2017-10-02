package com.mycollab.module.project.view.task;

import com.mycollab.common.domain.MonitorItem;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.service.MonitorItemService;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.module.file.AttachmentUtils;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleTask;
import com.mycollab.module.project.domain.Task;
import com.mycollab.module.project.event.TaskEvent;
import com.mycollab.module.project.event.TicketEvent;
import com.mycollab.module.project.service.ProjectTaskService;
import com.mycollab.module.project.ui.components.ProjectSubscribersComp;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.AbstractFormLayoutFactory;
import com.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.mycollab.vaadin.ui.IFormLayoutFactory;
import com.mycollab.vaadin.ui.UIUtils;
import com.mycollab.vaadin.web.ui.DefaultDynaFormLayout;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.field.AttachmentUploadField;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.jouni.restrain.Restrain;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public class TaskEditForm extends AdvancedEditBeanForm<SimpleTask> {

    @Override
    public void setBean(final SimpleTask item) {
        this.setFormLayoutFactory(new FormLayoutFactory());
        this.setBeanFormFieldFactory(new TaskEditFormFieldFactory(this, CurrentProjectVariables.getProjectId()));
        super.setBean(item);
    }

    protected void postExecution() {

    }

    class FormLayoutFactory extends AbstractFormLayoutFactory {
        private IFormLayoutFactory formLayoutFactory;

        @Override
        public AbstractComponent getLayout() {
            VerticalLayout layout = new VerticalLayout();
            formLayoutFactory = new DefaultDynaFormLayout(ProjectTypeConstants.TASK, TaskDefaultFormLayoutFactory.getForm(),
                    Task.Field.parenttaskid.name());
            AbstractComponent gridLayout = formLayoutFactory.getLayout();
            gridLayout.addStyleName(WebThemes.SCROLLABLE_CONTAINER);
            new Restrain(gridLayout).setMaxHeight((UIUtils.getBrowserHeight() - 180) + "px");
            layout.addComponent(gridLayout);
            layout.setExpandRatio(gridLayout, 1.0f);

            MButton saveBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SAVE), clickEvent -> {
                if (validateForm()) {
                    ProjectTaskService taskService = AppContextUtil.getSpringBean(ProjectTaskService.class);
                    Integer taskId;
                    if (bean.getId() == null) {
                        taskId = taskService.saveWithSession(bean, UserUIContext.getUsername());
                    } else {
                        taskService.updateWithSession(bean, UserUIContext.getUsername());
                        taskId = bean.getId();
                    }

                    TaskEditFormFieldFactory taskEditFormFieldFactory = (TaskEditFormFieldFactory) fieldFactory;

                    AttachmentUploadField uploadField = taskEditFormFieldFactory.getAttachmentUploadField();
                    String attachPath = AttachmentUtils.getProjectEntityAttachmentPath(AppUI.getAccountId(), bean.getProjectid(),
                            ProjectTypeConstants.TASK, "" + taskId);
                    uploadField.saveContentsToRepo(attachPath);

                    ProjectSubscribersComp subscribersComp = taskEditFormFieldFactory.getSubscribersComp();
                    List<String> followers = subscribersComp.getFollowers();
                    if (followers.size() > 0) {
                        List<MonitorItem> monitorItems = new ArrayList<>();
                        for (String follower : followers) {
                            MonitorItem monitorItem = new MonitorItem();
                            monitorItem.setMonitorDate(new GregorianCalendar().getTime());
                            monitorItem.setSaccountid(AppUI.getAccountId());
                            monitorItem.setType(ProjectTypeConstants.TASK);
                            monitorItem.setTypeid(taskId);
                            monitorItem.setUser(follower);
                            monitorItem.setExtratypeid(bean.getProjectid());
                            monitorItems.add(monitorItem);
                        }
                        MonitorItemService monitorItemService = AppContextUtil.getSpringBean(MonitorItemService.class);
                        monitorItemService.saveMonitorItems(monitorItems);
                    }

                    postExecution();
                    EventBusFactory.getInstance().post(new TaskEvent.NewTaskAdded(TaskEditForm.this, taskId));
                    EventBusFactory.getInstance().post(new TicketEvent.NewTicketAdded(TaskEditForm.this,
                            ProjectTypeConstants.TASK, taskId));
                }
            }).withStyleName(WebThemes.BUTTON_ACTION).withIcon(FontAwesome.SAVE);

            MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> postExecution())
                    .withStyleName(WebThemes.BUTTON_OPTION);

            MHorizontalLayout buttonControls = new MHorizontalLayout(cancelBtn, saveBtn)
                    .withMargin(new MarginInfo(true, false, false, false)).alignAll(Alignment.MIDDLE_RIGHT);

            layout.addComponent(buttonControls);
            layout.setComponentAlignment(buttonControls, Alignment.MIDDLE_RIGHT);
            return layout;
        }

        @Override
        protected Component onAttachField(Object propertyId, Field<?> field) {
            return formLayoutFactory.attachField(propertyId, field);
        }
    }
}
