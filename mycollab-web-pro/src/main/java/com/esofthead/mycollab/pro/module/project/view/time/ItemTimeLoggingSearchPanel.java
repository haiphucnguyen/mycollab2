package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.Order;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.db.query.DateParam;
import com.esofthead.mycollab.core.db.query.DateRangeInjector;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.ui.components.ComponentUtils;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberListSelect;
import com.esofthead.mycollab.pro.module.project.ui.components.ItemOrderComboBox;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.*;
import com.esofthead.mycollab.vaadin.web.ui.DefaultGenericSearchPanel;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.ValueComboBox;
import com.vaadin.data.Property;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.apache.commons.collections.CollectionUtils;
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
    private Button createBtn;

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
    protected void buildExtraControls() {
        createBtn = new Button(AppContext.getMessage(TimeTrackingI18nEnum.BUTTON_LOG_TIME));
        createBtn.setStyleName(UIConstants.BUTTON_ACTION);
        createBtn.setIcon(FontAwesome.PLUS);
        createBtn.setEnabled(!CurrentProjectVariables.isProjectArchived() && CurrentProjectVariables.canWrite
                (ProjectRolePermissionCollections.TIME));
        addHeaderRight(createBtn);
    }

    public void addClickListener(Button.ClickListener listener) {
        createBtn.addClickListener(listener);
    }

    public void addComponent(Component c, int index) {
        layout.bodyWrap.addComponent(c, index);
    }


    public String getGroupBy() {
        String groupBy = (String) layout.groupField.getValue();
        return groupBy == null ? "Date" : groupBy;
    }

    public Order getOrderBy() {
        return (Order) layout.orderField.getValue();
    }

    private class TimeLoggingBasicSearchLayout extends BasicSearchLayout {
        private DateFieldExt startDateField, endDateField;

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

            Date[] boundWeekDays = DateTimeUtils.getBounceDateofWeek(new Date());
            startDateField = new DateFieldExt();
            startDateField.setDateFormat(AppContext.getUserDateFormat().getDateFormat());
            startDateField.setResolution(Resolution.DAY);
            startDateField.setValue(boundWeekDays[0]);
            endDateField = new DateFieldExt();
            endDateField.setDateFormat(AppContext.getUserDateFormat().getDateFormat());
            endDateField.setResolution(Resolution.DAY);
            endDateField.setValue(boundWeekDays[1]);

            groupField = new ValueComboBox(false, "Date", "User");
            groupField.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    callSearchAction();
                }
            });

            orderField = new ItemOrderComboBox();
            orderField.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    callSearchAction();
                }
            });

            Label dateStartLb = new Label("From:");
            Label dateEndLb = new Label("To:");

            Label groupLb = new Label("Group:");
            Label sortLb = new Label("Sort:");

            gridLayout.addComponent(dateStartLb, 0, 0);
            gridLayout.addComponent(startDateField, 1, 0);
            gridLayout.addComponent(dateEndLb, 2, 0);
            gridLayout.addComponent(endDateField, 3, 0);
            gridLayout.addComponent(new Label("User:"), 4, 0);

            gridLayout.addComponent(groupLb, 0, 1);
            gridLayout.addComponent(groupField, 1, 1);
            gridLayout.addComponent(sortLb, 2, 1);
            gridLayout.addComponent(orderField, 3, 1);

            userField = new ProjectMemberListSelect();
            gridLayout.addComponent(userField, 5, 0, 5, 1);

            MHorizontalLayout buttonControls = new MHorizontalLayout();

            Button searchBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_SEARCH), new Button.ClickListener() {
                @Override
                public void buttonClick(final ClickEvent event) {
                    callSearchAction();
                }
            });

            searchBtn.setStyleName(UIConstants.BUTTON_ACTION);
            searchBtn.setIcon(FontAwesome.SEARCH);

            Button clearBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CLEAR), new Button.ClickListener() {
                @Override
                public void buttonClick(final ClickEvent event) {
                    userField.setValue(null);
                }
            });
            clearBtn.setStyleName(UIConstants.BUTTON_OPTION);

            buttonControls.with(searchBtn, clearBtn).alignAll(Alignment.MIDDLE_LEFT);
            bodyWrap.with(gridLayout, buttonControls).withAlign(buttonControls, Alignment.MIDDLE_CENTER);

            return bodyWrap;
        }

        @Override
        protected SearchCriteria fillUpSearchCriteria() {
            ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
            searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
            Date fDate = startDateField.getValue();
            Date tDate = endDateField.getValue();
            searchCriteria.addExtraField(DateParam.inRangeDate(ItemTimeLoggingSearchCriteria.p_logDates,
                    new DateRangeInjector(fDate, tDate)));
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

            if ("Date".equals(groupField.getValue())) {
                searchCriteria.setOrderFields(Collections.singletonList(new SearchCriteria.OrderField("logForDay", sortDirection)));
            } else if ("User".equals(groupField.getValue())) {
                searchCriteria.setOrderFields(Collections.singletonList(new SearchCriteria.OrderField("loguser", sortDirection)));
            }
            return searchCriteria;
        }
    }
}