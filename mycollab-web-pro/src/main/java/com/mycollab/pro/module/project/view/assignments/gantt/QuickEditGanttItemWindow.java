package com.mycollab.pro.module.project.view.assignments.gantt;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.MyCollabException;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.module.project.domain.*;
import com.mycollab.module.project.event.BugEvent;
import com.mycollab.module.project.event.MilestoneEvent;
import com.mycollab.module.project.event.TaskEvent;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.i18n.TicketI18nEnum;
import com.mycollab.module.project.service.MilestoneService;
import com.mycollab.module.project.service.ProjectTaskService;
import com.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.module.tracker.service.BugService;
import com.mycollab.pro.module.project.event.GanttEvent;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.AbstractFormLayoutFactory;
import com.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MWindow;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class QuickEditGanttItemWindow extends MWindow {
    private GanttExt gantt;
    private GanttItemWrapper ganttItem;

    public QuickEditGanttItemWindow(GanttExt gantt, GanttItemWrapper ganttItem) {
        super(UserUIContext.getMessage(TicketI18nEnum.EDIT));
        this.gantt = gantt;
        this.ganttItem = ganttItem;

        EditForm editForm = new EditForm();
        editForm.setBean(ganttItem.getTask());
        withWidth("800px").withModal(true).withResizable(false).withClosable(true).withContent(editForm).withCenter();
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
            public AbstractComponent getLayout() {
                VerticalLayout layout = new VerticalLayout();
                informationLayout = GridFormLayoutHelper.defaultFormLayoutHelper(2, 3);
                layout.addComponent(informationLayout.getLayout());

                MButton updateAllBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_UPDATE_OTHER_FIELDS), clickEvent -> {
                    if (bean instanceof TaskGanttItem) {
                        ProjectTaskService taskService = AppContextUtil.getSpringBean(ProjectTaskService.class);
                        SimpleTask task = taskService.findById(bean.getId(), AppUI.getAccountId());
                        EventBusFactory.getInstance().post(new TaskEvent.GotoEdit(QuickEditGanttItemWindow.this, task));
                    } else if (bean instanceof MilestoneGanttItem) {
                        MilestoneService milestoneService = AppContextUtil.getSpringBean(MilestoneService.class);
                        SimpleMilestone milestone = milestoneService.findById(bean.getId(), AppUI.getAccountId());
                        EventBusFactory.getInstance().post(new MilestoneEvent.GotoEdit(QuickEditGanttItemWindow.this, milestone));
                    } else if (bean instanceof BugGanttItem) {
                        BugService bugService = AppContextUtil.getSpringBean(BugService.class);
                        SimpleBug bug = bugService.findById(bean.getId(), AppUI.getAccountId());
                        EventBusFactory.getInstance().post(new BugEvent.GotoEdit(QuickEditGanttItemWindow.this, bug));
                    } else {
                        throw new MyCollabException("Do not support gantt item type " + bean);
                    }
                    close();
                }).withStyleName(WebThemes.BUTTON_LINK);

                MButton updateBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_UPDATE_LABEL), clickEvent -> {
                    if (EditForm.this.validateForm()) {
                        ganttItem.setTask(bean);
                        gantt.markStepDirty(ganttItem.getStep());
                        gantt.calculateMaxMinDates(ganttItem);
                        EventBusFactory.getInstance().post(new GanttEvent.AddGanttItemUpdateToQueue(QuickEditGanttItemWindow.this, ganttItem));
                        EventBusFactory.getInstance().post(new GanttEvent.UpdateGanttItem(QuickEditGanttItemWindow.this, ganttItem));
                        close();
                    }
                }).withStyleName(WebThemes.BUTTON_ACTION).withIcon(FontAwesome.SAVE);

                MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                        .withStyleName(WebThemes.BUTTON_OPTION);

                MHorizontalLayout buttonControls = new MHorizontalLayout(updateAllBtn, cancelBtn, updateBtn).withMargin(new MarginInfo(true, true, true, false));

                layout.addComponent(buttonControls);
                layout.setComponentAlignment(buttonControls, Alignment.MIDDLE_RIGHT);
                return layout;
            }

            @Override
            protected Component onAttachField(Object propertyId, Field<?> field) {
                if ("name".equals(propertyId)) {
                    return informationLayout.addComponent(field, UserUIContext.getMessage(GenericI18Enum.FORM_NAME), 0, 0, 2, "100%");
                } else if ("startDate".equals(propertyId)) {
                    return informationLayout.addComponent(field, UserUIContext.getMessage(GenericI18Enum.FORM_START_DATE), 0, 1);
                } else if ("endDate".equals(propertyId)) {
                    return informationLayout.addComponent(field, UserUIContext.getMessage(GenericI18Enum.FORM_END_DATE), 1, 1);
                } else if ("deadline".equals(propertyId)) {
                    return informationLayout.addComponent(field, UserUIContext.getMessage(GenericI18Enum.FORM_DUE_DATE), 0, 2);
                } else if ("assignUser".equals(propertyId)) {
                    return informationLayout.addComponent(field, UserUIContext.getMessage(GenericI18Enum.FORM_ASSIGNEE), 1, 2);
                }
                return null;
            }
        }

        private class EditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<AssignWithPredecessors> {
            private static final long serialVersionUID = 1L;

            EditFormFieldFactory(GenericBeanForm<AssignWithPredecessors> form) {
                super(form);
            }

            @Override
            protected AbstractField<?> onCreateField(final Object propertyId) {
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
                        ((AbstractComponent) field).setDescription(UserUIContext.getMessage(ProjectCommonI18nEnum.ERROR_NOT_EDIT_CELL_IN_GANTT_HELP));
                    }
                }
            }
        }
    }
}
