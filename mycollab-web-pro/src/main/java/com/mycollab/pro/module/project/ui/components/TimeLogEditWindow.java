package com.mycollab.pro.module.project.ui.components;

import com.mycollab.common.TableViewField;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.event.ProjectEvent;
import com.mycollab.module.project.fielddef.TimeTableFieldDef;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.service.ItemTimeLoggingService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.PopupDateFieldExt;
import com.mycollab.vaadin.web.ui.DoubleField;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.mycollab.vaadin.web.ui.table.DefaultPagedBeanTable;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 4.3.3
 */
// TODO
public abstract class TimeLogEditWindow<V extends ValuedBean> extends MWindow {
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
        content = new MVerticalLayout();
        withModal(true).withResizable(false).withCenter().withContent(content).withWidth("980px");

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
        headerPanel = new MHorizontalLayout().withFullWidth();
        content.addComponent(headerPanel);
        constructSpentTimeEntryPanel();
        constructRemainTimeEntryPanel();

        tableItem = new DefaultPagedBeanTable<>(AppContextUtil.getSpringBean(ItemTimeLoggingService.class), SimpleItemTimeLogging.class,
                Arrays.asList(TimeTableFieldDef.logUser, TimeTableFieldDef.logForDate, TimeTableFieldDef.logValue,
                        TimeTableFieldDef.billable, TimeTableFieldDef.overtime, new TableViewField(null, "id",
                                WebUIConstants.TABLE_CONTROL_WIDTH)));

//        gridItem.addGeneratedColumn("logUserFullName", (source, itemId, columnId) -> {
//            final SimpleItemTimeLogging timeLoggingItem = gridItem.getBeanByIndex(itemId);
//
//            return new ProjectUserLink(timeLoggingItem.getProjectid(), timeLoggingItem.getLoguser(),
//                    timeLoggingItem.getLogUserAvatarId(), timeLoggingItem.getLogUserFullName());
//        });
//
//        gridItem.addGeneratedColumn("logforday", (source, itemId, columnId) -> {
//            SimpleItemTimeLogging monitorItem = gridItem.getBeanByIndex(itemId);
//            return new Label(UserUIContext.formatDate(monitorItem.getLogforday()));
//        });
//
//        gridItem.addGeneratedColumn("logvalue", (source, itemId, columnId) -> {
//            SimpleItemTimeLogging itemTimeLogging = gridItem.getBeanByIndex(itemId);
//            return new Label(itemTimeLogging.getLogvalue() + "");
//        });
//
//        gridItem.addGeneratedColumn("isbillable", (source, itemId, columnId) -> {
//            SimpleItemTimeLogging monitorItem = gridItem.getBeanByIndex(itemId);
//            ELabel icon = (monitorItem.getIsbillable()) ? ELabel.fontIcon(VaadinIcons.CHECK) : ELabel.fontIcon(VaadinIcons.CLOSE);
//            icon.setStyleName(WebThemes.BUTTON_ICON_ONLY);
//            return icon;
//        });
//
//        gridItem.addGeneratedColumn("isovertime", (source, itemId, columnId) -> {
//            SimpleItemTimeLogging monitorItem = gridItem.getBeanByIndex(itemId);
//            ELabel icon = Boolean.TRUE.equals(monitorItem.getIsovertime()) ? ELabel.fontIcon(VaadinIcons.CHECK) : ELabel.fontIcon(VaadinIcons.CLOSE);
//            icon.setStyleName(WebThemes.BUTTON_ICON_ONLY);
//            return icon;
//        });
//
//        gridItem.addGeneratedColumn("id", (source, itemId, columnId) -> {
//            final SimpleItemTimeLogging itemTimeLogging = gridItem.getBeanByIndex(itemId);
//            MButton deleteBtn = new MButton("", clickEvent -> {
//                itemTimeLoggingService.removeWithSession(itemTimeLogging, UserUIContext.getUsername(), AppUI.getAccountId());
//                loadTimeValue();
//                hasTimeChange = true;
//            }).withIcon(VaadinIcons.TRASH).withStyleName(WebThemes.BUTTON_ICON_ONLY);
//            itemTimeLogging.setExtraData(deleteBtn);
//
//            deleteBtn.setVisible(CurrentProjectVariables.isAdmin() || UserUIContext.getUsername().equals(itemTimeLogging.getLoguser()));
//            return deleteBtn;
//        });

        tableItem.setWidth("100%");
        content.addComponent(tableItem);
    }

    private void constructSpentTimeEntryPanel() {
        MVerticalLayout spentTimePanel = new MVerticalLayout().withMargin(false);
        headerPanel.addComponent(spentTimePanel);

        final MVerticalLayout totalLayout = new MVerticalLayout().withStyleName(WebThemes.BOX);
        spentTimePanel.addComponent(totalLayout);
        Label lbTimeInstructTotal = new Label(UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_TOTAL_SPENT_HOURS));
        totalLayout.addComponent(lbTimeInstructTotal);
        totalSpentTimeLbl = new ELabel("_").withStyleName(ValoTheme.LABEL_LARGE, ValoTheme.LABEL_BOLD);
        totalLayout.addComponent(totalSpentTimeLbl);

        MHorizontalLayout addLayout = new MHorizontalLayout().withUndefinedWidth();
        addLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        spentTimePanel.addComponent(addLayout);

        newTimeInputField = new DoubleField();
        newTimeInputField.setWidth("80px");

        forDateField = new PopupDateFieldExt();
//        forDateField.setValue(new GregorianCalendar().getTime());

        isBillableField = new CheckBox(UserUIContext.getMessage(TimeTrackingI18nEnum.FORM_IS_BILLABLE), true);
        isOvertimeField = new CheckBox(UserUIContext.getMessage(TimeTrackingI18nEnum.FORM_IS_OVERTIME), false);

        addBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_ADD), clickEvent -> {
            double d = newTimeInputField.getValue();
            if (d > 0) {
                hasTimeChange = true;
                saveTimeInvest();
                loadTimeValue();
                newTimeInputField.setValue(0d);
            }
        }).withIcon(VaadinIcons.PLUS).withStyleName(WebThemes.BUTTON_ACTION).withVisible(isEnableAdd());
        addLayout.with(newTimeInputField, forDateField, isBillableField, isOvertimeField, addBtn);
    }

    private void constructRemainTimeEntryPanel() {
        MVerticalLayout remainTimePanel = new MVerticalLayout().withMargin(false);
        headerPanel.addComponent(remainTimePanel);

        final MVerticalLayout updateLayout = new MVerticalLayout().withStyleName(WebThemes.BOX);
        remainTimePanel.addComponent(updateLayout);

        final Label lbTimeInstructTotal = new Label(UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_REMAINING_WORK_HOURS));
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

        addBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_UPDATE_LABEL), clickEvent -> {
            double d = remainTimeInputField.getValue();
            if (d >= 0) {
                hasTimeChange = true;
                updateTimeRemain();
                remainTimeLbl.setValue(remainTimeInputField.getValue() + "");
                remainTimeInputField.setValue(0d);
            }
        }).withStyleName(WebThemes.BUTTON_ACTION).withVisible(isEnableAdd());
        addLayout.with(addBtn).withAlign(addBtn, Alignment.MIDDLE_LEFT);
    }

    private void loadTimeValue() {
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

    protected LocalDateTime forLogDate() {
//        Date date = forDateField.getValue();
//        return (date != null) ? date : new GregorianCalendar().getTime();
        return null;
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
