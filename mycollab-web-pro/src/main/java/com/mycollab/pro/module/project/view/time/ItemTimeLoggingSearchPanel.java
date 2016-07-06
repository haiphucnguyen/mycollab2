package com.mycollab.pro.module.project.view.time;

import com.mycollab.common.i18n.DayI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.Order;
import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.query.ConstantValueInjector;
import com.mycollab.db.query.DateParam;
import com.mycollab.core.utils.DateTimeUtils;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.ui.components.ComponentUtils;
import com.mycollab.module.project.view.settings.component.ProjectMemberListSelect;
import com.mycollab.module.user.accountsettings.localization.UserI18nEnum;
import com.mycollab.pro.module.project.ui.components.ItemOrderComboBox;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.HeaderWithFontAwesome;
import com.mycollab.vaadin.ui.PopupDateFieldExt;
import com.mycollab.vaadin.web.ui.DefaultGenericSearchPanel;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.mycollab.vaadin.web.ui.ValueComboBox;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 2.0
 */
class ItemTimeLoggingSearchPanel extends DefaultGenericSearchPanel<ItemTimeLoggingSearchCriteria> {
    private static final long serialVersionUID = 1L;

    private TimeLoggingBasicSearchLayout layout;
    private MButton createBtn;

    @Override
    protected SearchLayout<ItemTimeLoggingSearchCriteria> createBasicSearchLayout() {
        layout = new TimeLoggingBasicSearchLayout();
        return layout;
    }

    public Date getFromDate() {
        return layout.startDateField.getValue();
    }

    public Date getToDate() {
        return layout.endDateField.getValue();
    }

    @Override
    protected SearchLayout<ItemTimeLoggingSearchCriteria> createAdvancedSearchLayout() {
        return null;
    }

    @Override
    protected HeaderWithFontAwesome buildSearchTitle() {
        return ComponentUtils.headerH2(ProjectTypeConstants.TIME, AppContext.getMessage(TimeTrackingI18nEnum.SEARCH_TIME_TITLE));
    }

    @Override
    protected Component buildExtraControls() {
        createBtn = new MButton(AppContext.getMessage(TimeTrackingI18nEnum.BUTTON_LOG_TIME), clickEvent -> {
            AddTimeEntryWindow addTimeEntry = new AddTimeEntryWindow();
            UI.getCurrent().addWindow(addTimeEntry);
        }).withStyleName(UIConstants.BUTTON_ACTION).withIcon(FontAwesome.PLUS);
        createBtn.setEnabled(!CurrentProjectVariables.isProjectArchived() &&
                CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TIME));
        return createBtn;
    }

    public void addComponent(Component c, int index) {
        layout.bodyWrap.addComponent(c, index);
    }


    public String getGroupBy() {
        String groupBy = (String) layout.groupField.getValue();
        return groupBy == null ? AppContext.getMessage(DayI18nEnum.OPT_DATE) : groupBy;
    }

    public Order getOrderBy() {
        return (Order) layout.orderField.getValue();
    }

    private class TimeLoggingBasicSearchLayout extends BasicSearchLayout<ItemTimeLoggingSearchCriteria> {
        private PopupDateFieldExt startDateField, endDateField;

        private ProjectMemberListSelect userField;
        private ComboBox groupField, orderField;
        private MVerticalLayout bodyWrap;

        public TimeLoggingBasicSearchLayout() {
            super(ItemTimeLoggingSearchPanel.this);
        }

        @Override
        public ComponentContainer constructHeader() {
            return ItemTimeLoggingSearchPanel.this.constructHeader();
        }

        @Override
        public ComponentContainer constructBody() {
            bodyWrap = new MVerticalLayout();
            GridLayout gridLayout = new GridLayout(6, 2);
            gridLayout.setWidth("100%");
            gridLayout.setSpacing(true);
            gridLayout.setMargin(true);

            Date[] boundWeekDays = DateTimeUtils.getBounceDatesOfWeek(new Date());
            startDateField = new PopupDateFieldExt();
            startDateField.setResolution(Resolution.DAY);
            startDateField.setValue(boundWeekDays[0]);
            endDateField = new PopupDateFieldExt();
            endDateField.setResolution(Resolution.DAY);
            endDateField.setValue(boundWeekDays[1]);

            groupField = new ValueComboBox(false, AppContext.getMessage(DayI18nEnum.OPT_DATE),
                    AppContext.getMessage(UserI18nEnum.SINGLE));
            groupField.addValueChangeListener(valueChangeEvent -> callSearchAction());

            orderField = new ItemOrderComboBox();
            orderField.addValueChangeListener(valueChangeEvent -> callSearchAction());

            Label dateStartLb = new ELabel(AppContext.getMessage(DayI18nEnum.OPT_FROM)).withStyleName(UIConstants
                    .META_COLOR, UIConstants.TEXT_ALIGN_RIGHT);
            Label dateEndLb = new ELabel(AppContext.getMessage(DayI18nEnum.OPT_TO)).withStyleName(UIConstants.META_COLOR,
                    UIConstants.TEXT_ALIGN_RIGHT);

            Label groupLb = new ELabel(AppContext.getMessage(GenericI18Enum.OPT_GROUP)).withStyleName(UIConstants.META_COLOR,
                    UIConstants.TEXT_ALIGN_RIGHT);
            Label sortLb = new ELabel(AppContext.getMessage(GenericI18Enum.ACTION_SORT)).withStyleName(UIConstants.META_COLOR,
                    UIConstants.TEXT_ALIGN_RIGHT);

            gridLayout.addComponent(dateStartLb, 0, 0);
            gridLayout.addComponent(startDateField, 1, 0);
            gridLayout.addComponent(dateEndLb, 2, 0);
            gridLayout.addComponent(endDateField, 3, 0);
            gridLayout.addComponent(new ELabel(AppContext.getMessage(UserI18nEnum.SINGLE)).withStyleName(UIConstants.META_COLOR,
                    UIConstants.TEXT_ALIGN_RIGHT), 4, 0);

            gridLayout.addComponent(groupLb, 0, 1);
            gridLayout.addComponent(groupField, 1, 1);
            gridLayout.addComponent(sortLb, 2, 1);
            gridLayout.addComponent(orderField, 3, 1);

            userField = new ProjectMemberListSelect();
            gridLayout.addComponent(userField, 5, 0, 5, 1);

            MButton searchBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_SEARCH), clickEvent -> callSearchAction())
                    .withStyleName(UIConstants.BUTTON_ACTION).withIcon(FontAwesome.SEARCH);

            MButton clearBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_CLEAR), clickEvent -> userField.setValue(null))
                    .withStyleName(UIConstants.BUTTON_OPTION);

            MHorizontalLayout buttonControls = new MHorizontalLayout(searchBtn, clearBtn);
            bodyWrap.with(gridLayout, buttonControls).withAlign(buttonControls, Alignment.MIDDLE_CENTER);

            return bodyWrap;
        }

        @Override
        protected ItemTimeLoggingSearchCriteria fillUpSearchCriteria() {
            ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
            searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
            Date fDate = startDateField.getValue();
            Date tDate = endDateField.getValue();
            searchCriteria.addExtraField(DateParam.inRangeDate(ItemTimeLoggingSearchCriteria.p_logDates,
                    ConstantValueInjector.valueOf(Date.class, new Date[]{fDate, tDate})));
            Collection<String> selectedUsers = (Collection<String>) userField.getValue();
            if (CollectionUtils.isNotEmpty(selectedUsers)) {
                searchCriteria.setLogUsers(new SetSearchField(selectedUsers));
            }
            Order order = (Order) orderField.getValue();
            String sortDirection;
            if (Order.ASCENDING == order) {
                sortDirection = SearchCriteria.ASC;
            } else {
                sortDirection = SearchCriteria.DESC;
            }

            if (AppContext.getMessage(DayI18nEnum.OPT_DATE).equals(groupField.getValue())) {
                searchCriteria.setOrderFields(Collections.singletonList(new SearchCriteria.OrderField("logForDay", sortDirection)));
            } else if (AppContext.getMessage(UserI18nEnum.SINGLE).equals(groupField.getValue())) {
                searchCriteria.setOrderFields(Collections.singletonList(new SearchCriteria.OrderField("loguser", sortDirection)));
            }
            return searchCriteria;
        }
    }
}