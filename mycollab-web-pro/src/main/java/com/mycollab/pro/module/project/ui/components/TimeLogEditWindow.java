package com.mycollab.pro.module.project.ui.components;

import com.mycollab.common.TableViewField;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.events.ProjectEvent;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.service.ItemTimeLoggingService;
import com.mycollab.module.project.view.settings.component.ProjectUserLink;
import com.mycollab.module.project.view.time.TimeTableFieldDef;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.PopupDateFieldExt;
import com.mycollab.vaadin.web.ui.DoubleField;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.mycollab.vaadin.web.ui.table.DefaultPagedBeanTable;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author MyCollab Ltd.
 * @since 4.3.3
 */
public abstract class TimeLogEditWindow<V extends ValuedBean> extends Window {
    private static final long serialVersionUID = 1L;

    protected ItemTimeLoggingService itemTimeLoggingService;
    protected V bean;

    private DefaultPagedBeanTable<ItemTimeLoggingService, ItemTimeLoggingSearchCriteria, SimpleItemTimeLogging> tableItem;

    private MVerticalLayout content;
    private HorizontalLayout headerPanel;

    private boolean hasTimeChange = false;
    private MButton addBtn;
    private Label totalSpentTimeLbl;
    private DoubleField newTimeInputField;
    private CheckBox isBillableField, isOvertimeField;
    private PopupDateFieldExt forDateField;

    private DoubleField remainTimeInputField;
    private Label remainTimeLbl;

    protected TimeLogEditWindow(final V bean) {
        this.bean = bean;
        this.center();
        this.setResizable(false);
        this.setModal(true);
        content = new MVerticalLayout();
        this.setContent(content);

        this.itemTimeLoggingService = AppContextUtil.getSpringBean(ItemTimeLoggingService.class);

        this.initUI();
        this.loadTimeValue();
        this.addCloseListener(closeEvent -> {
            if (hasTimeChange) {
                EventBusFactory.getInstance().post(new ProjectEvent.TimeLoggingChangedEvent(TimeLogEditWindow.this));
            }
        });
    }

    private void initUI() {
        this.setWidth("980px");

        headerPanel = new MHorizontalLayout().withFullWidth();
        content.addComponent(headerPanel);
        constructSpentTimeEntryPanel();
        constructRemainTimeEntryPanel();

        tableItem = new DefaultPagedBeanTable<>(AppContextUtil.getSpringBean(ItemTimeLoggingService.class), SimpleItemTimeLogging.class,
                Arrays.asList(TimeTableFieldDef.logUser(), TimeTableFieldDef.logForDate(), TimeTableFieldDef.logValue(),
                        TimeTableFieldDef.billable(), TimeTableFieldDef.overtime(), new TableViewField(null, "id",
                                UIConstants.TABLE_CONTROL_WIDTH)));

        tableItem.addGeneratedColumn("logUserFullName", (source, itemId, columnId) -> {
            final SimpleItemTimeLogging timeLoggingItem = tableItem.getBeanByIndex(itemId);

            return new ProjectUserLink(timeLoggingItem.getLoguser(), timeLoggingItem.getLogUserAvatarId(),
                    timeLoggingItem.getLogUserFullName());
        });

        tableItem.addGeneratedColumn("logforday", (source, itemId, columnId) -> {
            SimpleItemTimeLogging monitorItem = tableItem.getBeanByIndex(itemId);
            return new Label(AppContext.formatDate(monitorItem.getLogforday()));
        });

        tableItem.addGeneratedColumn("logvalue", (source, itemId, columnId) -> {
            SimpleItemTimeLogging itemTimeLogging = tableItem.getBeanByIndex(itemId);
            return new Label(itemTimeLogging.getLogvalue() + "");
        });

        tableItem.addGeneratedColumn("isbillable", (source, itemId, columnId) -> {
            SimpleItemTimeLogging monitorItem = tableItem.getBeanByIndex(itemId);
            ELabel icon = (monitorItem.getIsbillable()) ? ELabel.fontIcon(FontAwesome.CHECK) : ELabel.fontIcon(FontAwesome.TIMES);
            icon.setStyleName(UIConstants.BUTTON_ICON_ONLY);
            return icon;
        });

        tableItem.addGeneratedColumn("isovertime", (source, itemId, columnId) -> {
            SimpleItemTimeLogging monitorItem = tableItem.getBeanByIndex(itemId);
            ELabel icon = Boolean.TRUE.equals(monitorItem.getIsovertime()) ? ELabel.fontIcon(FontAwesome.CHECK) : ELabel.fontIcon(FontAwesome.TIMES);
            icon.setStyleName(UIConstants.BUTTON_ICON_ONLY);
            return icon;
        });

        tableItem.addGeneratedColumn("id", (source, itemId, columnId) -> {
            final SimpleItemTimeLogging itemTimeLogging = tableItem.getBeanByIndex(itemId);
            MButton deleteBtn = new MButton("", clickEvent -> {
                itemTimeLoggingService.removeWithSession(itemTimeLogging, AppContext.getUsername(), AppContext.getAccountId());
                TimeLogEditWindow.this.loadTimeValue();
                hasTimeChange = true;
            }).withIcon(FontAwesome.TRASH_O).withStyleName(UIConstants.BUTTON_ICON_ONLY);
            itemTimeLogging.setExtraData(deleteBtn);

            deleteBtn.setVisible(CurrentProjectVariables.isAdmin() || AppContext.getUsername().equals(itemTimeLogging.getLoguser()));
            return deleteBtn;
        });

        tableItem.setWidth("100%");
        content.addComponent(tableItem);
    }

    private void constructSpentTimeEntryPanel() {
        MVerticalLayout spentTimePanel = new MVerticalLayout().withMargin(false);
        headerPanel.addComponent(spentTimePanel);

        final MVerticalLayout totalLayout = new MVerticalLayout().withStyleName(UIConstants.BOX);
        spentTimePanel.addComponent(totalLayout);
        Label lbTimeInstructTotal = new Label(AppContext.getMessage(TimeTrackingI18nEnum.OPT_TOTAL_SPENT_HOURS));
        totalLayout.addComponent(lbTimeInstructTotal);
        totalSpentTimeLbl = new Label("_");
        totalSpentTimeLbl.addStyleName(ValoTheme.LABEL_LARGE);
        totalSpentTimeLbl.addStyleName(ValoTheme.LABEL_BOLD);
        totalLayout.addComponent(totalSpentTimeLbl);

        MHorizontalLayout addLayout = new MHorizontalLayout();
        addLayout.setSizeUndefined();
        addLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        spentTimePanel.addComponent(addLayout);

        newTimeInputField = new DoubleField();
        newTimeInputField.setWidth("80px");

        forDateField = new PopupDateFieldExt();
        forDateField.setValue(new GregorianCalendar().getTime());

        isBillableField = new CheckBox(AppContext.getMessage(TimeTrackingI18nEnum.FORM_IS_BILLABLE), true);
        isOvertimeField = new CheckBox(AppContext.getMessage(TimeTrackingI18nEnum.FORM_IS_OVERTIME), false);

        addBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_ADD), clickEvent -> {
            double d = newTimeInputField.getValue();
            if (d > 0) {
                hasTimeChange = true;
                saveTimeInvest();
                loadTimeValue();
                newTimeInputField.setValue(0d);
            }
        }).withIcon(FontAwesome.PLUS).withStyleName(UIConstants.BUTTON_ACTION).withVisible(isEnableAdd());
        addLayout.with(newTimeInputField, forDateField, isBillableField, isOvertimeField, addBtn);
    }

    private void constructRemainTimeEntryPanel() {
        MVerticalLayout remainTimePanel = new MVerticalLayout().withMargin(false);
        headerPanel.addComponent(remainTimePanel);

        final MVerticalLayout updateLayout = new MVerticalLayout().withStyleName(UIConstants.BOX);
        remainTimePanel.addComponent(updateLayout);

        final Label lbTimeInstructTotal = new Label(AppContext.getMessage(TimeTrackingI18nEnum.OPT_REMAINING_WORK_HOURS));
        updateLayout.addComponent(lbTimeInstructTotal);
        remainTimeLbl = new Label("_");
        remainTimeLbl.addStyleName(ValoTheme.LABEL_LARGE);
        remainTimeLbl.addStyleName(ValoTheme.LABEL_BOLD);
        updateLayout.addComponent(remainTimeLbl);

        MHorizontalLayout addLayout = new MHorizontalLayout();
        addLayout.setSizeUndefined();
        remainTimePanel.addComponent(addLayout);

        remainTimeInputField = new DoubleField();
        remainTimeInputField.setWidth("80px");
        addLayout.addComponent(remainTimeInputField);
        addLayout.setComponentAlignment(remainTimeInputField, Alignment.MIDDLE_LEFT);

        addBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_UPDATE_LABEL), clickEvent -> {
            double d = remainTimeInputField.getValue();
            if (d >= 0) {
                hasTimeChange = true;
                updateTimeRemain();
                remainTimeLbl.setValue(remainTimeInputField.getValue() + "");
                remainTimeInputField.setValue(0d);
            }
        }).withStyleName(UIConstants.BUTTON_ACTION).withVisible(isEnableAdd());
        addLayout.with(addBtn).withAlign(addBtn, Alignment.MIDDLE_LEFT);
    }

    public void loadTimeValue() {
        ItemTimeLoggingSearchCriteria searchCriteria = getItemSearchCriteria();
        tableItem.setSearchCriteria(searchCriteria);
        setTotalTimeValue();
        setUpdateTimeValue();
    }

    private double getTotalInvest() {
        ItemTimeLoggingSearchCriteria searchCriteria = getItemSearchCriteria();
        return itemTimeLoggingService.getTotalHoursByCriteria(searchCriteria);
    }

    private void setUpdateTimeValue() {
        if (getEstimateRemainTime() > -1) {
            remainTimeLbl.setValue(getEstimateRemainTime() + "");
        }
    }

    private void setTotalTimeValue() {
        if (getTotalInvest() > 0) {
            totalSpentTimeLbl.setValue(getTotalInvest() + "");
        }
    }

    protected double getInvestValue() {
        return newTimeInputField.getValue();
    }

    protected Boolean isBillableHours() {
        return isBillableField.getValue();
    }

    protected Boolean isOvertimeHours() {
        return isOvertimeField.getValue();
    }

    protected Date forLogDate() {
        Date date = forDateField.getValue();
        return (date != null) ? date : new GregorianCalendar().getTime();
    }

    protected abstract void saveTimeInvest();

    protected abstract void updateTimeRemain();

    protected abstract ItemTimeLoggingSearchCriteria getItemSearchCriteria();

    protected abstract double getEstimateRemainTime();

    protected abstract boolean isEnableAdd();

    protected double getUpdateRemainTime() {
        return remainTimeInputField.getValue();
    }
}
