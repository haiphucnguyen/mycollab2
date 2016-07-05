package com.esofthead.mycollab.pro.module.project.view.assignments.gantt;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.domain.*;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.esofthead.mycollab.pro.module.project.events.GanttEvent;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AbstractFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class QuickEditGanttItemWindow extends Window {
    private GanttExt gantt;
    private GanttItemWrapper ganttItem;

    public QuickEditGanttItemWindow(GanttExt gantt, GanttItemWrapper ganttItem) {
        super("Quick Edit Task");
        this.gantt = gantt;
        this.ganttItem = ganttItem;
        this.setWidth("800px");
        this.setModal(true);
        this.setResizable(false);
        this.setClosable(true);
        this.center();

        EditForm editForm = new EditForm();
        editForm.setBean(ganttItem.getTask());
        this.setContent(editForm);
    }

    private class EditForm extends AdvancedEditBeanForm<AssignWithPredecessors> {
        @Override
        public void setBean(final AssignWithPredecessors item) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setBeanFormFieldFactory(new EditFormFieldFactory(EditForm.this));
            super.setBean(item);
        }

        class FormLayoutFactory extends AbstractFormLayoutFactory {
            private static final long serialVersionUID = 1L;
            private GridFormLayoutHelper informationLayout;

            @Override
            public ComponentContainer getLayout() {
                VerticalLayout layout = new VerticalLayout();
                informationLayout = GridFormLayoutHelper.defaultFormLayoutHelper(2, 3);
                informationLayout.getLayout().setMargin(false);
                informationLayout.getLayout().setSpacing(false);
                layout.addComponent(informationLayout.getLayout());

                MButton updateAllBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_UPDATE_OTHER_FIELDS), clickEvent -> {
                    if (bean instanceof TaskGanttItem) {
                        ProjectTaskService taskService = AppContextUtil.getSpringBean(ProjectTaskService.class);
                        SimpleTask task = taskService.findById(bean.getId(), AppContext.getAccountId());
                        EventBusFactory.getInstance().post(new TaskEvent.GotoEdit(QuickEditGanttItemWindow.this, task));
                    } else if (bean instanceof MilestoneGanttItem) {
                        MilestoneService milestoneService = AppContextUtil.getSpringBean(MilestoneService.class);
                        SimpleMilestone milestone = milestoneService.findById(bean.getId(), AppContext.getAccountId());
                        EventBusFactory.getInstance().post(new MilestoneEvent.GotoEdit(QuickEditGanttItemWindow.this, milestone));
                    } else {
                        throw new MyCollabException("Do not support gantt item type " + bean);
                    }
                    close();
                }).withStyleName(UIConstants.BUTTON_LINK);

                MButton updateBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_UPDATE_LABEL), clickEvent -> {
                    if (EditForm.this.validateForm()) {
                        ganttItem.setTask(bean);
                        gantt.markStepDirty(ganttItem.getStep());
                        gantt.calculateMaxMinDates(ganttItem);
                        EventBusFactory.getInstance().post(new GanttEvent.AddGanttItemUpdateToQueue(QuickEditGanttItemWindow.this, ganttItem));
                        EventBusFactory.getInstance().post(new GanttEvent.UpdateGanttItem(QuickEditGanttItemWindow.this, ganttItem));
                        close();
                    }
                }).withStyleName(UIConstants.BUTTON_ACTION);

                MButton cancelBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                        .withStyleName(UIConstants.BUTTON_OPTION);

                MHorizontalLayout buttonControls = new MHorizontalLayout(updateAllBtn, cancelBtn, updateBtn).withMargin(new MarginInfo(true, true, true, false));

                layout.addComponent(buttonControls);
                layout.setComponentAlignment(buttonControls, Alignment.MIDDLE_RIGHT);
                return layout;
            }

            @Override
            protected Component onAttachField(Object propertyId, Field<?> field) {
                if ("name".equals(propertyId)) {
                    return informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_NAME), 0, 0, 2, "100%");
                } else if ("startDate".equals(propertyId)) {
                    return informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_START_DATE), 0, 1);
                } else if ("endDate".equals(propertyId)) {
                    return informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_END_DATE), 1, 1);
                } else if ("deadline".equals(propertyId)) {
                    return informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_DUE_DATE), 0, 2);
                } else if ("assignUser".equals(propertyId)) {
                    return informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE), 1, 2);
                }
                return null;
            }
        }

        private class EditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<AssignWithPredecessors> {
            private static final long serialVersionUID = 1L;

            public EditFormFieldFactory(GenericBeanForm<AssignWithPredecessors> form) {
                super(form);
            }

            @Override
            protected Field<?> onCreateField(final Object propertyId) {
                if ("assignUser".equals(propertyId)) {
                    return new ProjectMemberSelectionField();
                }
                return null;
            }

            @Override
            protected void postCreateField(Object propertyId, Field<?> field) {
                if ("startDate".equals(propertyId) || "endDate".equals(propertyId)) {
                    if (bean.hasSubAssignments()) {
                        field.setEnabled(false);
                        ((AbstractComponent) field).setDescription("Because this row has sub-tasks, this cell " +
                                "is a summary value and can not be edited directly. You can edit cells " +
                                "beneath this row to change its value");
                    }
                }
            }
        }
    }
}
