package com.esofthead.mycollab.premium.module.project.view.time;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.*;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.esofthead.mycollab.module.project.ui.components.ItemOrderComboBox;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberListSelect;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.*;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.maddon.layouts.MHorizontalLayout;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 2.0
 */
class ItemTimeLoggingSearchPanel extends
        GenericSearchPanel<ItemTimeLoggingSearchCriteria> {

    private static final long serialVersionUID = 1L;

    protected ItemTimeLoggingSearchCriteria searchCriteria;
    private final TimeLoggingAdvancedSearchLayout layout;

    public ItemTimeLoggingSearchPanel() {
        layout = new TimeLoggingAdvancedSearchLayout();
        this.setCompositionRoot(layout);
    }

    public void addClickListener(Button.ClickListener listener) {
        layout.createBtn.addClickListener(listener);
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

    @SuppressWarnings({"serial", "rawtypes"})
    private class TimeLoggingAdvancedSearchLayout extends AdvancedSearchLayout {

        private DateFieldExt dateStart, dateEnd;

        private ProjectMemberListSelect userField;
        private ComboBox groupField, orderField;
        private MHorizontalLayout buttonControls;
        private Button createBtn;
        private VerticalLayout bodyWrap;

        @SuppressWarnings("unchecked")
        public TimeLoggingAdvancedSearchLayout() {
            super(ItemTimeLoggingSearchPanel.this);
            this.setStyleName("time-tracking-logging");
        }

        @Override
        public ComponentContainer constructHeader() {
            Image titleIcon = new Image(null,
                    MyCollabResource
                            .newResource(WebResourceIds._22_project_time_selected));
            Label headerText = new Label(
                    AppContext
                            .getMessage(TimeTrackingI18nEnum.SEARCH_TIME_TITLE));
            headerText.setStyleName(UIConstants.HEADER_TEXT);

            createBtn = new Button(
                    AppContext.getMessage(TimeTrackingI18nEnum.BUTTON_LOG_TIME));
            createBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
            createBtn.setIcon(MyCollabResource
                    .newResource(WebResourceIds._16_project_add_time));
            createBtn.setEnabled(!CurrentProjectVariables.isProjectArchived());
            createBtn.addStyleName("v-button-caption-bool");

            return new MHorizontalLayout()
                    .withStyleName(UIConstants.HEADER_VIEW).withWidth("100%")
                    .withSpacing(true)
                    .withMargin(new MarginInfo(true, false, true, false))
                    .with(titleIcon, headerText, createBtn)
                    .withAlign(titleIcon, Alignment.MIDDLE_LEFT)
                    .withAlign(headerText, Alignment.MIDDLE_LEFT)
                    .withAlign(createBtn, Alignment.MIDDLE_RIGHT)
                    .expand(headerText);
        }

        @Override
        public ComponentContainer constructBody() {
            bodyWrap = new VerticalLayout();

            GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(3, 1,
                    "300px", "50px");

            String nameFieldWidth = "300px";

            gridLayout.getLayout().setWidth("100%");
            gridLayout.getLayout().setSpacing(true);
            gridLayout.getLayout().setMargin(true);

            GridLayout grid = new GridLayout(4, 2);
            grid.setWidth(nameFieldWidth);
            grid.setSpacing(true);

            this.dateStart = new DateFieldExt();
            this.dateEnd = new DateFieldExt();

            setDateFormat(AppContext.getUserDateFormat());

            setDateWidth(100);
            setDefaultValue();

            this.groupField = new ValueComboBox(false, "Date", "User");
            this.groupField.setWidth("100px");

            this.orderField = new ItemOrderComboBox();
            this.orderField.setWidth("100px");

            Label dateStartLb = new Label("From:");
            Label dateEndLb = new Label("To:");

            Label groupLb = new Label("Group:");
            groupLb.setWidth("40px");
            Label sortLb = new Label("Sort:");
            sortLb.setWidth("40px");

            grid.addComponent(dateStartLb, 0, 0);
            grid.addComponent(dateEndLb, 2, 0);
            grid.addComponent(groupLb, 0, 1);
            grid.addComponent(sortLb, 2, 1);

            grid.addComponent(this.dateStart, 1, 0);
            grid.addComponent(this.dateEnd, 3, 0);
            grid.addComponent(this.groupField, 1, 1);
            grid.addComponent(this.orderField, 3, 1);

            gridLayout.addComponent(grid, null, 0, 0);

            this.userField = new ProjectMemberListSelect();
            gridLayout.addComponent(userField, "User", 1, 0);
            this.userField.setWidth(nameFieldWidth);

            buttonControls = new MHorizontalLayout().withSpacing(true).withMargin(false);

            final Button searchBtn = new Button(
                    AppContext.getMessage(GenericI18Enum.BUTTON_SEARCH),
                    new Button.ClickListener() {
                        @Override
                        public void buttonClick(final ClickEvent event) {
                            TimeLoggingAdvancedSearchLayout.this
                                    .callSearchAction();
                        }
                    });

            searchBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
            searchBtn.setIcon(MyCollabResource
                    .newResource(WebResourceIds._16_search));

            final Button clearBtn = new Button(
                    AppContext.getMessage(GenericI18Enum.BUTTON_CLEAR),
                    new Button.ClickListener() {
                        @Override
                        public void buttonClick(final ClickEvent event) {
                            TimeLoggingAdvancedSearchLayout.this.userField
                                    .setValue(null);
                            setDefaultValue();
                        }
                    });
            clearBtn.setStyleName(UIConstants.THEME_GRAY_LINK);

            buttonControls.with(searchBtn, clearBtn)
                    .alignAll(Alignment.MIDDLE_LEFT);

            gridLayout.addComponent(buttonControls, null, 2, 0);

            bodyWrap.addComponent(gridLayout.getLayout());

            return bodyWrap;
        }

        @Override
        public ComponentContainer constructFooter() {
            CssLayout c = new CssLayout();
            c.setStyleName("empty-compnent");
            return c;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected SearchCriteria fillupSearchCriteria() {
            searchCriteria = new ItemTimeLoggingSearchCriteria();
            searchCriteria
                    .setProjectIds(new SetSearchField<>(
                            CurrentProjectVariables.getProjectId()));

            searchCriteria
                    .setRangeDate(getRangeSearchValue());

            final Collection<String> selectedUsers = (Collection<String>) this.userField
                    .getValue();

            if (CollectionUtils.isNotEmpty(selectedUsers)) {
                searchCriteria
                        .setLogUsers(new SetSearchField(SearchField.AND,
                                selectedUsers));
            }

            return ItemTimeLoggingSearchPanel.this.searchCriteria;
        }

        private RangeDateSearchField getRangeSearchValue() {
            Date fDate = dateStart.getValue();
            Date tDate = dateEnd.getValue();

            if (fDate == null || tDate == null)
                return null;

            return new RangeDateSearchField(fDate, tDate);
        }

        private void setDateFormat(String dateFormat) {
            dateStart.setDateFormat(dateFormat);
            dateEnd.setDateFormat(dateFormat);
        }

        private void setDefaultValue() {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

            Date fDate = c.getTime();
            Date tDate = DateTimeUtils.subtractOrAddDayDuration(fDate, 7);

            dateStart.setValue(fDate);
            dateEnd.setValue(tDate);
        }

        private void setDateWidth(float width) {
            dateStart.setWidth(width, Unit.PIXELS);
            dateEnd.setWidth(width, Unit.PIXELS);
            dateStart.setResolution(Resolution.DAY);
            dateEnd.setResolution(Resolution.DAY);
        }
    }
}
