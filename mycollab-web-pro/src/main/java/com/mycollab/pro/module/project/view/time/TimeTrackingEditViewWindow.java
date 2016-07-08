package com.mycollab.pro.module.project.view.time;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.domain.ProjectGenericTask;
import com.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.mycollab.module.project.domain.SimpleProjectMember;
import com.mycollab.module.project.events.TimeTrackingEvent;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.service.ItemTimeLoggingService;
import com.mycollab.module.project.view.settings.component.ProjectMemberSelectionBox;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.ui.PopupDateFieldExt;
import com.mycollab.vaadin.web.ui.DoubleField;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class TimeTrackingEditViewWindow extends Window implements AssignmentSelectableComp {
    private static final long serialVersionUID = 1L;

    private CheckBox isBillableCheckBox;
    private CheckBox isOvertimeCheckBox;
    private ProjectMemberSelectionBox projectMemberSelectionBox;
    private RichTextArea descArea;
    private ProjectGenericTask selectionTask;
    private MHorizontalLayout taskLayout;
    private PopupDateFieldExt dateField;
    private DoubleField timeField;
    private SimpleItemTimeLogging timeLogging;

    public TimeTrackingEditViewWindow(TimeTrackingListView view, SimpleItemTimeLogging timeLogging) {
        this.timeLogging = timeLogging;
        this.setWidth("800px");
        this.setModal(true);
        this.setResizable(false);

        this.setCaption(AppContext.getMessage(TimeTrackingI18nEnum.DIALOG_LOG_TIME_ENTRY_TITLE));

        dateField = new PopupDateFieldExt(timeLogging.getLogforday());
        dateField.setCaption("Select date");

        timeField = new DoubleField();
        timeField.setCaption("Hours");
        timeField.setValue(timeLogging.getLogvalue());

        projectMemberSelectionBox = new ProjectMemberSelectionBox(false);
        projectMemberSelectionBox.setValue(timeLogging.getLoguser());
        projectMemberSelectionBox.setCaption(AppContext.getMessage(TimeTrackingI18nEnum.FORM_WHO));

        isBillableCheckBox = new CheckBox(AppContext.getMessage(TimeTrackingI18nEnum.FORM_IS_BILLABLE));
        isBillableCheckBox.setValue(timeLogging.getIsbillable());

        isOvertimeCheckBox = new CheckBox(AppContext.getMessage(TimeTrackingI18nEnum.FORM_IS_OVERTIME));
        isOvertimeCheckBox.setValue(timeLogging.getIsovertime());

        MHorizontalLayout grid = new MHorizontalLayout();
        grid.with(projectMemberSelectionBox, dateField, timeField, isBillableCheckBox, isOvertimeCheckBox);

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


        MButton cancelBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                .withStyleName(UIConstants.BUTTON_OPTION);

        MButton saveBtn = new MButton(AppContext.getMessage(TimeTrackingI18nEnum.BUTTON_LOG_TIME), clickEvent -> {
            saveTimeLoggingItems();
            close();
        }).withIcon(FontAwesome.SAVE).withStyleName(UIConstants.BUTTON_ACTION);

        MHorizontalLayout controlsLayout = new MHorizontalLayout(cancelBtn, saveBtn);

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

            MButton detachTaskBtn = new MButton(AppContext.getMessage(TimeTrackingI18nEnum.BUTTON_DETACH_TASK), clickEvent -> {
                createLinkTaskButton();
                updateLinkTask(null);
            }).withIcon(FontAwesome.UNLINK).withStyleName(UIConstants.BUTTON_DANGER);
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
        MButton attachTaskBtn = new MButton(AppContext.getMessage(TimeTrackingI18nEnum.BUTTON_LINK_TASK), clickEvent -> {
            ProjectGenericTaskSelectionWindow selectionTaskWindow = new ProjectGenericTaskSelectionWindow(
                    TimeTrackingEditViewWindow.this);
            UI.getCurrent().addWindow(selectionTaskWindow);
        }).withStyleName(UIConstants.BUTTON_ACTION);

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
        timeLogging.setLogvalue(timeField.getValue());
        timeLogging.setIsbillable(isBillableCheckBox.getValue());
        timeLogging.setIsovertime(isOvertimeCheckBox.getValue());
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

        ItemTimeLoggingService itemTimeLoggingService = AppContextUtil.getSpringBean(ItemTimeLoggingService.class);
        itemTimeLoggingService.updateWithSession(timeLogging, AppContext.getUsername());
        EventBusFactory.getInstance().post(new TimeTrackingEvent.TimeLoggingEntryChange(TimeTrackingEditViewWindow.this));
    }
}