package com.mycollab.pro.module.project.view.finance;

import com.hp.gagawa.java.elements.A;
import com.mycollab.common.i18n.DayI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.module.project.ProjectLinkGenerator;
import com.mycollab.module.project.domain.ProjectTicket;
import com.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.mycollab.module.project.domain.SimpleProjectMember;
import com.mycollab.module.project.event.TimeTrackingEvent;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.service.ItemTimeLoggingService;
import com.mycollab.module.project.view.finance.ITimeTrackingContainer;
import com.mycollab.module.project.view.settings.component.ProjectMemberSelectionBox;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.DoubleField;
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
    private DateField dateField;
    private DoubleField timeField;
    private SimpleItemTimeLogging timeLogging;

    TimeTrackingEditViewWindow(ITimeTrackingContainer view, SimpleItemTimeLogging timeLogging) {
        super(UserUIContext.getMessage(TimeTrackingI18nEnum.DIALOG_LOG_TIME_ENTRY_TITLE));
        this.timeLogging = timeLogging;
        this.withWidth("800px").withModal(true).withResizable(false).withCenter();

        dateField = new DateField(UserUIContext.getMessage(DayI18nEnum.OPT_DATE), timeLogging.getLogforday());

        timeField = new DoubleField();
        timeField.setCaption(UserUIContext.getMessage(DayI18nEnum.OPT_HOURS));
        timeField.setValue(timeLogging.getLogvalue());

        projectMemberSelectionBox = new ProjectMemberSelectionBox(false);
        projectMemberSelectionBox.setSelectedUser(timeLogging.getLoguser());
        projectMemberSelectionBox.setCaption(UserUIContext.getMessage(TimeTrackingI18nEnum.FORM_WHO));

        isBillableCheckBox = new CheckBox(UserUIContext.getMessage(TimeTrackingI18nEnum.FORM_IS_BILLABLE));
        isBillableCheckBox.setValue((timeLogging.getIsbillable() == null) ? false : timeLogging.getIsbillable());

        isOvertimeCheckBox = new CheckBox(UserUIContext.getMessage(TimeTrackingI18nEnum.FORM_IS_OVERTIME));
        isOvertimeCheckBox.setValue((timeLogging.getIsovertime() == null) ? false : timeLogging.getIsovertime());

        MHorizontalLayout grid = new MHorizontalLayout(projectMemberSelectionBox, dateField, timeField, isBillableCheckBox, isOvertimeCheckBox);

        MVerticalLayout content = new MVerticalLayout(grid, new Label(UserUIContext.getMessage(GenericI18Enum.FORM_DESCRIPTION)));

        descArea = new RichTextArea();
        descArea.setValue((timeLogging.getNote() == null) ? "" : timeLogging.getNote());
        descArea.setWidth("100%");
        content.addComponent(descArea);

        ticketLayout = new MHorizontalLayout();
        ticketLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        createTicketLinkButton();

        if (timeLogging.getType() != null && timeLogging.getTypeid() != null) {
            ProjectTicket tmpSelectedTicket = GenericTicketDetailMapper.getTicket(timeLogging.getType(), timeLogging.getTypeid());
            updateTicketLink(tmpSelectedTicket);
        }

        MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                .withStyleName(WebThemes.BUTTON_OPTION);

        MButton saveBtn = new MButton(UserUIContext.getMessage(TimeTrackingI18nEnum.BUTTON_LOG_TIME), clickEvent -> {
            saveTimeLoggingItems();
            close();
        }).withIcon(VaadinIcons.CLIPBOARD).withStyleName(WebThemes.BUTTON_ACTION);

        MHorizontalLayout footer = new MHorizontalLayout(ticketLayout, new MHorizontalLayout(cancelBtn, saveBtn)).expand(ticketLayout);
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
            }).withIcon(VaadinIcons.UNLINK).withStyleName(WebThemes.BUTTON_DANGER);

            A ticketLink = new A(ProjectLinkGenerator.generateProjectItemLink(ticket.getProjectShortName(), ticket.getProjectId(), ticket.getType(), ticket.getTypeId() + ""))
                    .appendText(StringUtils.trim(ticket.getName(), 60, true));

            ELabel linkTicketBtn = ELabel.html(ticketLink.write())
                    .withDescription(new ProjectGenericItemTooltipGenerator(ticket.getType(),
                            ticket.getTypeId()).getContent()).withFullWidth();
            ticketLayout.with(detachTaskBtn, linkTicketBtn).expand(linkTicketBtn);
        }
    }

    private void createTicketLinkButton() {
        ticketLayout.removeAllComponents();
        MButton linkTicketBtn = new MButton(UserUIContext.getMessage(TimeTrackingI18nEnum.ACTION_LINK_TICKET), clickEvent -> {
            ProjectTicketSelectionWindow selectionTicketWindow = new ProjectTicketSelectionWindow(
                    TimeTrackingEditViewWindow.this);
            UI.getCurrent().addWindow(selectionTicketWindow);
        }).withStyleName(WebThemes.BUTTON_ACTION).withIcon(VaadinIcons.LINK);

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
        timeLogging.setSaccountid(AppUI.getAccountId());
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
