package com.mycollab.pro.module.project.view.time;

import com.mycollab.common.i18n.DayI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.domain.ProjectTicket;
import com.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.mycollab.module.project.domain.SimpleProjectMember;
import com.mycollab.module.project.event.TimeTrackingEvent;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.service.ItemTimeLoggingService;
import com.mycollab.module.project.view.settings.component.ProjectMemberSelectionBox;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.PopupDateFieldExt;
import com.mycollab.vaadin.web.ui.DoubleField;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class TimeTrackingEditViewWindow extends MWindow implements AssignmentSelectableComp {
    private static final long serialVersionUID = 1L;

    private CheckBox isBillableCheckBox;
    private CheckBox isOvertimeCheckBox;
    private ProjectMemberSelectionBox projectMemberSelectionBox;
    private RichTextArea descArea;
    private ProjectTicket selectedTicket;
    private MHorizontalLayout ticketLayout;
    private PopupDateFieldExt dateField;
    private DoubleField timeField;
    private SimpleItemTimeLogging timeLogging;

    public TimeTrackingEditViewWindow(TimeTrackingListView view, SimpleItemTimeLogging timeLogging) {
        super(UserUIContext.getMessage(TimeTrackingI18nEnum.DIALOG_LOG_TIME_ENTRY_TITLE));
        this.timeLogging = timeLogging;
        this.withWidth("800px").withModal(true).withResizable(false).withCenter();

        dateField = new PopupDateFieldExt(timeLogging.getLogforday());
        dateField.setCaption(UserUIContext.getMessage(DayI18nEnum.OPT_DATE));

        timeField = new DoubleField();
        timeField.setCaption(UserUIContext.getMessage(DayI18nEnum.OPT_HOURS));
        timeField.setValue(timeLogging.getLogvalue());

        projectMemberSelectionBox = new ProjectMemberSelectionBox(false);
        projectMemberSelectionBox.setValue(timeLogging.getLoguser());
        projectMemberSelectionBox.setCaption(UserUIContext.getMessage(TimeTrackingI18nEnum.FORM_WHO));

        isBillableCheckBox = new CheckBox(UserUIContext.getMessage(TimeTrackingI18nEnum.FORM_IS_BILLABLE));
        isBillableCheckBox.setValue(timeLogging.getIsbillable());

        isOvertimeCheckBox = new CheckBox(UserUIContext.getMessage(TimeTrackingI18nEnum.FORM_IS_OVERTIME));
        isOvertimeCheckBox.setValue(timeLogging.getIsovertime());

        MHorizontalLayout grid = new MHorizontalLayout(projectMemberSelectionBox, dateField, timeField, isBillableCheckBox, isOvertimeCheckBox);

        MVerticalLayout content = new MVerticalLayout(grid, new Label(UserUIContext.getMessage(GenericI18Enum.FORM_DESCRIPTION)));

        descArea = new RichTextArea();
        descArea.setValue(timeLogging.getNote());
        descArea.setWidth("100%");
        content.addComponent(descArea);

        HorizontalLayout footer = new HorizontalLayout();
        ticketLayout = new MHorizontalLayout();
        ticketLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        createTicketLinkButton();

        if (timeLogging.getType() != null && timeLogging.getTypeid() != null) {
            ProjectTicket tmpSelectedTicket = new ProjectTicket();
            tmpSelectedTicket.setType(timeLogging.getType());
            tmpSelectedTicket.setTypeId(timeLogging.getTypeid());
            String name = new GenericTaskDetailMapper(tmpSelectedTicket.getType(), tmpSelectedTicket.getTypeId()).getName();

            tmpSelectedTicket.setName(name);
            updateTicketLink(tmpSelectedTicket);
        }

        footer.addComponent(ticketLayout);


        MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                .withStyleName(WebUIConstants.BUTTON_OPTION);

        MButton saveBtn = new MButton(UserUIContext.getMessage(TimeTrackingI18nEnum.BUTTON_LOG_TIME), clickEvent -> {
            saveTimeLoggingItems();
            close();
        }).withIcon(FontAwesome.SAVE).withStyleName(WebUIConstants.BUTTON_ACTION);

        MHorizontalLayout controlsLayout = new MHorizontalLayout(cancelBtn, saveBtn);

        footer.addComponent(controlsLayout);
        footer.setSizeFull();
        footer.setComponentAlignment(controlsLayout, Alignment.TOP_RIGHT);
        content.addComponent(footer);
        this.setContent(content);
    }

    @Override
    public void updateTicketLink(ProjectTicket ticket) {
        this.selectedTicket = ticket;
        if (ticket != null) {
            ticketLayout.removeAllComponents();

            MButton detachTaskBtn = new MButton(UserUIContext.getMessage(TimeTrackingI18nEnum.ACTION_UNLINK_TICKET), clickEvent -> {
                createTicketLinkButton();
                updateTicketLink(null);
            }).withIcon(FontAwesome.UNLINK).withStyleName(WebUIConstants.BUTTON_DANGER);
            ticketLayout.addComponent(detachTaskBtn);

            ELabel linkTicketBtn = new ELabel(StringUtils.trim(ticket.getName(), 60, true))
                    .withDescription(new ProjectGenericItemTooltipGenerator(ticket.getType(),
                            ticket.getTypeId()).getContent());
            ticketLayout.addComponent(linkTicketBtn);
        }
    }

    private void createTicketLinkButton() {
        ticketLayout.removeAllComponents();
        MButton linkTicketBtn = new MButton(UserUIContext.getMessage(TimeTrackingI18nEnum.ACTION_LINK_TICKET), clickEvent -> {
            ProjectTicketSelectionWindow selectionTicketWindow = new ProjectTicketSelectionWindow(
                    TimeTrackingEditViewWindow.this);
            UI.getCurrent().addWindow(selectionTicketWindow);
        }).withStyleName(WebUIConstants.BUTTON_ACTION).withIcon(FontAwesome.LINK);

        ticketLayout.addComponent(linkTicketBtn);
    }

    private void saveTimeLoggingItems() {
        SimpleProjectMember user = (SimpleProjectMember) projectMemberSelectionBox.getValue();
        timeLogging.setCreateduser(UserUIContext.getUsername());
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
        timeLogging.setSaccountid(MyCollabUI.getAccountId());
        if (selectedTicket != null) {
            timeLogging.setType(selectedTicket.getType());
            timeLogging.setTypeid(selectedTicket.getTypeId());
            timeLogging.setName(selectedTicket.getName());
        } else {
            timeLogging.setType(null);
            timeLogging.setTypeid(null);
            timeLogging.setName(null);
        }

        ItemTimeLoggingService itemTimeLoggingService = AppContextUtil.getSpringBean(ItemTimeLoggingService.class);
        itemTimeLoggingService.updateWithSession(timeLogging, UserUIContext.getUsername());
        EventBusFactory.getInstance().post(new TimeTrackingEvent.TimeLoggingEntryChange(TimeTrackingEditViewWindow.this));
    }
}
