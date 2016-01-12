package com.esofthead.mycollab.premium.module.project.view.time;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.events.TimeTrackingEvent;
import com.esofthead.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberSelectionBox;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.DateFieldExt;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class TimeTrackingEditViewWindow extends Window implements AssignmentSelectableComp {
    private static final long serialVersionUID = 1L;

    private CheckBox isBillableCheckBox;
    private ProjectMemberSelectionBox projectMemberSelectionBox;
    private RichTextArea descArea;
    private ProjectGenericTask selectionTask;
    private MHorizontalLayout taskLayout;
    private DateFieldExt dateField;
    private SimpleItemTimeLogging timeLogging;
    private ObjectProperty<Double> property;

    public TimeTrackingEditViewWindow(TimeTrackingListView view, SimpleItemTimeLogging timeLogging) {
        this.timeLogging = timeLogging;
        this.setWidth("800px");
        this.setModal(true);
        this.setResizable(false);

        this.setCaption(AppContext.getMessage(TimeTrackingI18nEnum.DIALOG_LOG_TIME_ENTRY_TITLE));

        dateField = new DateFieldExt("Select date:", timeLogging.getLogforday());

        property = new ObjectProperty<>(timeLogging.getLogvalue());
        TextField timeField = new TextField("Hours:", property);

        projectMemberSelectionBox = new ProjectMemberSelectionBox(false);
        projectMemberSelectionBox.setValue(timeLogging.getLoguser());
        projectMemberSelectionBox.setCaption(AppContext.getMessage(TimeTrackingI18nEnum.FORM_WHO));

        isBillableCheckBox = new CheckBox();
        isBillableCheckBox.setValue(timeLogging.getIsbillable());

        MHorizontalLayout isBillableBox = new MHorizontalLayout();
        isBillableBox.setDefaultComponentAlignment(Alignment.BOTTOM_LEFT);
        Label billableTitle = new Label(AppContext.getMessage(TimeTrackingI18nEnum.FORM_IS_BILLABLE));
        isBillableBox.addComponent(billableTitle);
        isBillableBox.addComponent(isBillableCheckBox);

        MHorizontalLayout grid = new MHorizontalLayout();
        grid.with(projectMemberSelectionBox, dateField, timeField, isBillableBox);

        MVerticalLayout content = new MVerticalLayout();
        content.addComponent(grid);

        content.addComponent(new Label(AppContext.getMessage(GenericI18Enum.FORM_DESCRIPTION)));

        descArea = new RichTextArea();
        descArea.setValue(timeLogging.getNote());
        descArea.setWidth("100%");
        content.addComponent(descArea);

        HorizontalLayout footer = new HorizontalLayout();
        taskLayout = new MHorizontalLayout();
        taskLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        createLinkTaskButton();

        if (timeLogging.getType() != null && timeLogging.getTypeid() != null) {
            ProjectGenericTask tempSelectionTask = new ProjectGenericTask();
            tempSelectionTask.setType(timeLogging.getType());
            tempSelectionTask.setTypeId(timeLogging.getTypeid());
            String name = new GenericTaskDetailMapper(tempSelectionTask.getType(), tempSelectionTask.getTypeId()).getName();

            tempSelectionTask.setName(name);
            updateLinkTask(tempSelectionTask);
        }

        footer.addComponent(taskLayout);

        MHorizontalLayout controlsLayout = new MHorizontalLayout();

        Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                TimeTrackingEditViewWindow.this.close();
            }
        });
        cancelBtn.addStyleName(UIConstants.THEME_GRAY_LINK);


        Button saveBtn = new Button(AppContext.getMessage(TimeTrackingI18nEnum.BUTTON_LOG_TIME), new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                saveTimeLoggingItems();
                TimeTrackingEditViewWindow.this.close();
            }
        });
        saveBtn.setIcon(FontAwesome.SAVE);
        saveBtn.addStyleName(UIConstants.BUTTON_ACTION);

        controlsLayout.with(cancelBtn, saveBtn);

        footer.addComponent(controlsLayout);
        footer.setSizeFull();
        footer.setComponentAlignment(controlsLayout, Alignment.TOP_RIGHT);
        content.addComponent(footer);
        this.setContent(content);
        this.center();
    }

    @Override
    public void updateLinkTask(ProjectGenericTask selectionTask) {
        this.selectionTask = selectionTask;
        if (selectionTask != null) {
            final String taskName = selectionTask.getName();
            taskLayout.removeAllComponents();

            Button detachTaskBtn = new Button(AppContext.getMessage(TimeTrackingI18nEnum.BUTTON_DETACH_TASK), new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(ClickEvent event) {
                    createLinkTaskButton();
                    updateLinkTask(null);
                }
            });
            detachTaskBtn.setStyleName(UIConstants.BUTTON_DANGER);
            detachTaskBtn.setIcon(FontAwesome.UNLINK);
            taskLayout.addComponent(detachTaskBtn);

            Label attachTaskBtn = new Label(StringUtils.trim(taskName, 40, true));

            attachTaskBtn.addStyleName("task-attached");
            attachTaskBtn.setWidth("300px");
            attachTaskBtn.setDescription(new ProjectGenericTaskTooltipGenerator(selectionTask.getType(),
                    selectionTask.getTypeId()).getContent());
            taskLayout.addComponent(attachTaskBtn);
        }
    }

    private void createLinkTaskButton() {
        taskLayout.removeAllComponents();
        Button attachTaskBtn = new Button(AppContext.getMessage(TimeTrackingI18nEnum.BUTTON_LINK_TASK), new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                ProjectGenericTaskSelectionWindow selectionTaskWindow = new ProjectGenericTaskSelectionWindow(
                        TimeTrackingEditViewWindow.this);
                TimeTrackingEditViewWindow.this.getUI().addWindow(selectionTaskWindow);
            }
        });
        attachTaskBtn.addStyleName(UIConstants.BUTTON_ACTION);

        taskLayout.addComponent(attachTaskBtn);
    }

    private void saveTimeLoggingItems() {
        SimpleProjectMember user = (SimpleProjectMember) projectMemberSelectionBox.getValue();
        timeLogging.setCreateduser(AppContext.getUsername());
        timeLogging.setLoguser(user.getUsername());
        timeLogging.setLogUserFullName(user.getMemberFullName());
        if (user.getMemberAvatarId() != null) {
            timeLogging.setLogUserAvatarId(user.getMemberAvatarId());
        }
        timeLogging.setLogforday(dateField.getValue());
        timeLogging.setLogvalue(property.getValue());
        timeLogging.setIsbillable(isBillableCheckBox.getValue());
        timeLogging.setNote(descArea.getValue());
        timeLogging.setSaccountid(AppContext.getAccountId());
        if (selectionTask != null) {
            timeLogging.setType(selectionTask.getType());
            timeLogging.setTypeid(selectionTask.getTypeId());
            timeLogging.setSummary(selectionTask.getName());
        } else {
            timeLogging.setType(null);
            timeLogging.setTypeid(null);
            timeLogging.setSummary(null);
        }

        ItemTimeLoggingService itemTimeLoggingService = ApplicationContextUtil.getSpringBean(ItemTimeLoggingService.class);
        itemTimeLoggingService.updateWithSession(timeLogging, AppContext.getUsername());
        EventBusFactory.getInstance().post(new TimeTrackingEvent.TimeLoggingEntryChange(TimeTrackingEditViewWindow.this));
    }
}
