package com.esofthead.mycollab.module.crm.view.campaign;

import java.util.Arrays;
import java.util.Collection;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.DateTimeSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateTimeSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.ui.components.UserListSelect;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.DateSelectionComboBox;
import com.esofthead.mycollab.vaadin.ui.DateSelectionField;
import com.esofthead.mycollab.vaadin.ui.DefaultAdvancedSearchLayout;
import com.esofthead.mycollab.vaadin.ui.DefaultGenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.Separator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class CampaignSearchPanel extends
        DefaultGenericSearchPanel<CampaignSearchCriteria> {

    protected CampaignSearchCriteria searchCriteria;

    public CampaignSearchPanel() {
        this.searchCriteria = new CampaignSearchCriteria();
    }

    private HorizontalLayout createSearchTopPanel() {
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("100%");
        layout.setSpacing(true);

        final Embedded titleIcon = new Embedded();
        titleIcon.setSource(MyCollabResource
                .newResource("icons/22/crm/campaign.png"));
        layout.addComponent(titleIcon);
        layout.setComponentAlignment(titleIcon, Alignment.MIDDLE_LEFT);

        final Label searchtitle = new Label("Campaigns");
        searchtitle.setStyleName(Reindeer.LABEL_H2);
        layout.addComponent(searchtitle);
        layout.setExpandRatio(searchtitle, 1.0f);
        layout.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);

        final Button createAccountBtn = new Button("Create",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(final ClickEvent event) {
                        EventBus.getInstance().fireEvent(
                                new CampaignEvent.GotoAdd(this, null));

                    }
                });
        createAccountBtn.setIcon(MyCollabResource
                .newResource("icons/16/addRecord.png"));
        createAccountBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        createAccountBtn.setEnabled(AppContext
                .canWrite(RolePermissionCollections.CRM_CAMPAIGN));
        UiUtils.addComponent(layout, createAccountBtn, Alignment.MIDDLE_RIGHT);

        return layout;
    }

    @SuppressWarnings("rawtypes")
    private class CampaignBasicSearchLayout extends BasicSearchLayout {

        private TextField nameField;
        private CheckBox myItemCheckbox;

        @SuppressWarnings("unchecked")
        public CampaignBasicSearchLayout() {
            super(CampaignSearchPanel.this);
        }

        @Override
        public ComponentContainer constructHeader() {
            return CampaignSearchPanel.this.createSearchTopPanel();
        }

        @Override
        public ComponentContainer constructBody() {
            final HorizontalLayout layout = new HorizontalLayout();
            layout.setSpacing(false);
            this.nameField = this.createSeachSupportTextField(new TextField(),
                    "NameFieldOfBasicSearch");

            this.nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
            UiUtils.addComponent(layout, this.nameField,
                    Alignment.MIDDLE_CENTER);

            final Button searchBtn = new Button();
            searchBtn.setStyleName("search-icon-button");
            searchBtn.setIcon(MyCollabResource
                    .newResource("icons/16/search_white.png"));
            searchBtn.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(final ClickEvent event) {
                    CampaignBasicSearchLayout.this.callSearchAction();
                }
            });
            UiUtils.addComponent(layout, searchBtn, Alignment.MIDDLE_LEFT);

            this.myItemCheckbox = new CheckBox(
                    LocalizationHelper
                            .getMessage(CrmCommonI18nEnum.SEARCH_MYITEMS_CHECKBOX));
            this.myItemCheckbox.setWidth("75px");
            UiUtils.addComponent(layout, this.myItemCheckbox,
                    Alignment.MIDDLE_CENTER);

            final Separator separator1 = new Separator();

            UiUtils.addComponent(layout, separator1, Alignment.MIDDLE_LEFT);

            final Button cancelBtn = new Button(
                    LocalizationHelper
                            .getMessage(CrmCommonI18nEnum.BUTTON_CLEAR));
            cancelBtn.setStyleName(UIConstants.THEME_LINK);
            cancelBtn.addStyleName("cancel-button");
            cancelBtn.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(final ClickEvent event) {
                    CampaignBasicSearchLayout.this.nameField.setValue("");
                }
            });
            UiUtils.addComponent(layout, cancelBtn, Alignment.MIDDLE_CENTER);

            final Separator separator2 = new Separator();

            UiUtils.addComponent(layout, separator2, Alignment.MIDDLE_LEFT);

            final Button advancedSearchBtn = new Button("Advanced Search",
                    new Button.ClickListener() {
                        @Override
                        public void buttonClick(final ClickEvent event) {
                            CampaignSearchPanel.this
                                    .moveToAdvancedSearchLayout();
                        }
                    });
            advancedSearchBtn.setStyleName("link");
            UiUtils.addComponent(layout, advancedSearchBtn,
                    Alignment.MIDDLE_CENTER);
            return layout;
        }

        @Override
        protected SearchCriteria fillupSearchCriteria() {
            CampaignSearchPanel.this.searchCriteria = new CampaignSearchCriteria();
            CampaignSearchPanel.this.searchCriteria
                    .setSaccountid(new NumberSearchField(SearchField.AND,
                            AppContext.getAccountId()));

            if (StringUtil.isNotNullOrEmpty(this.nameField.getValue()
                    .toString().trim())) {
                CampaignSearchPanel.this.searchCriteria
                        .setCampaignName(new StringSearchField(SearchField.AND,
                                (String) this.nameField.getValue()));
            }

            if (this.myItemCheckbox.booleanValue()) {
                CampaignSearchPanel.this.searchCriteria
                        .setAssignUsers(new SetSearchField<String>(
                                SearchField.AND, new String[] { AppContext
                                        .getUsername() }));
            } else {
                CampaignSearchPanel.this.searchCriteria.setAssignUsers(null);
            }
            return CampaignSearchPanel.this.searchCriteria;
        }
    }

    private class CampaignAdvancedSearchLayout extends
            DefaultAdvancedSearchLayout<CampaignSearchCriteria> {

        private TextField nameField;
        private DateSelectionField startDateField;
        private DateSelectionField endDateField;
        private CampaignTypeListSelect typeField;
        private CampaignStatusListSelect statusField;
        private UserListSelect assignUserField;

        public CampaignAdvancedSearchLayout() {
            super(CampaignSearchPanel.this, CrmTypeConstants.CAMPAIGN);
        }

        @Override
        public ComponentContainer constructHeader() {
            return CampaignSearchPanel.this.createSearchTopPanel();
        }

        @Override
        public ComponentContainer constructBody() {

            GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(3, 3,
                    "100%", "90px");
            gridLayout.getLayout().setWidth("100%");
            gridLayout.getLayout().setMargin(true, true, true, false);

            // if (ScreenSize.hasSupport1024Pixels()) {
            // gridLayout = new GridFormLayoutHelper(3, 3,
            // UIConstants.DEFAULT_CONTROL_WIDTH_1024_RESOLUTION,
            // "90px");
            // } else if (ScreenSize.hasSupport1280Pixels()) {
            // gridLayout = new GridFormLayoutHelper(3, 3, "90px");
            // }

            this.nameField = (TextField) gridLayout.addComponent(
                    new TextField(), "Name", 0, 0);
            this.startDateField = (DateSelectionField) gridLayout.addComponent(
                    new DateSelectionField(), "Start Date", 1, 0);
            this.startDateField.setDateFormat(AppContext.getDateFormat());

            this.endDateField = (DateSelectionField) gridLayout.addComponent(
                    new DateSelectionField(), "End Date", 2, 0);
            this.endDateField.setDateFormat(AppContext.getDateFormat());

            this.typeField = (CampaignTypeListSelect) gridLayout.addComponent(
                    new CampaignTypeListSelect(), "Type", 0, 1);
            this.statusField = (CampaignStatusListSelect) gridLayout
                    .addComponent(new CampaignStatusListSelect(), "Status", 1,
                            1);
            this.assignUserField = (UserListSelect) gridLayout.addComponent(
                    new UserListSelect(), "Assign User", 2, 1);

            gridLayout.getLayout().setSpacing(true);

            return gridLayout.getLayout();
        }

        @Override
        protected CampaignSearchCriteria fillupSearchCriteria() {
            CampaignSearchPanel.this.searchCriteria = new CampaignSearchCriteria();
            CampaignSearchPanel.this.searchCriteria
                    .setSaccountid(new NumberSearchField(SearchField.AND,
                            AppContext.getAccountId()));

            if (StringUtil.isNotNullOrEmpty((String) this.nameField.getValue())) {
                CampaignSearchPanel.this.searchCriteria
                        .setCampaignName(new StringSearchField(SearchField.AND,
                                ((String) this.nameField.getValue()).trim()));
            }

            final SearchField startDate = this.startDateField.getValue();
            if (startDate != null && (startDate instanceof DateSearchField)) {
                CampaignSearchPanel.this.searchCriteria
                        .setStartDate((DateSearchField) startDate);
            } else if (startDate != null
                    && (startDate instanceof RangeDateSearchField)) {
                CampaignSearchPanel.this.searchCriteria
                        .setStartDateRange((RangeDateSearchField) startDate);
            }

            final SearchField endDate = this.endDateField.getValue();
            if (endDate != null && (endDate instanceof DateSearchField)) {
                CampaignSearchPanel.this.searchCriteria
                        .setEndDate((DateSearchField) endDate);
            } else if (endDate != null
                    && (endDate instanceof RangeDateSearchField)) {
                CampaignSearchPanel.this.searchCriteria
                        .setEndDateRange((RangeDateSearchField) endDate);
            }

            final Collection<String> types = (Collection<String>) this.typeField
                    .getValue();
            if (types != null && types.size() > 0) {
                CampaignSearchPanel.this.searchCriteria
                        .setTypes(new SetSearchField<String>(SearchField.AND,
                                types));
            }

            final Collection<String> statuses = (Collection<String>) this.statusField
                    .getValue();
            if (statuses != null && statuses.size() > 0) {
                CampaignSearchPanel.this.searchCriteria
                        .setStatuses(new SetSearchField<String>(
                                SearchField.AND, statuses));
            }

            final Collection<String> assignUsers = (Collection<String>) this.assignUserField
                    .getValue();
            if (assignUsers != null && assignUsers.size() > 0) {
                CampaignSearchPanel.this.searchCriteria
                        .setAssignUsers(new SetSearchField<String>(
                                SearchField.AND, assignUsers));
            }
            return CampaignSearchPanel.this.searchCriteria;
        }

        @Override
        protected void clearFields() {
            this.nameField.setValue("");
            this.startDateField.setDefaultSelection();
            this.endDateField.setDefaultSelection();
            this.typeField.setValue(null);
            this.statusField.setValue(null);
            this.assignUserField.setValue(null);
        }

        private void loadDateTimeField(final DateTimeSearchField dateField,
                final DateSelectionField selectDateField) {
            if (dateField.getComparision().equals(
                    DateTimeSearchField.GREATERTHAN)) {
                selectDateField.getDateSelectionBox().setValue(
                        DateSelectionComboBox.AFTER);
            } else if (dateField.getComparision().equals(
                    DateTimeSearchField.EQUAL)) {
                selectDateField.getDateSelectionBox().setValue(
                        DateSelectionComboBox.EQUAL);
            } else if (dateField.getComparision().equals(
                    DateTimeSearchField.LESSTHAN)) {
                selectDateField.getDateSelectionBox().setValue(
                        DateSelectionComboBox.BEFORE);
            } else if (dateField.getComparision().equals(
                    DateTimeSearchField.NOTEQUAL)) {
                selectDateField.getDateSelectionBox().setValue(
                        DateSelectionComboBox.NOTON);
            }
            selectDateField.setImmediate(true);
            selectDateField.setRows(2);
            final DateField strDate = new DateField();
            strDate.setImmediate(true);
            strDate.setValue(dateField.getValue());
            strDate.setDateFormat(AppContext.getDateFormat());
            selectDateField.removeAllDatefield();
            selectDateField.addComponent(strDate, 0, 1);
        }

        private void loadDateTimeRangeField(
                final RangeDateTimeSearchField rangeDateField,
                final DateSelectionField selectDateField) {
            selectDateField.removeAllDatefield();
            selectDateField.addRangeDate();
            selectDateField.getDateSelectionBox().setValue(
                    DateSelectionComboBox.ISBETWEEN);
            selectDateField.getDateStart().setValue(rangeDateField.getFrom());
            selectDateField.getDateStart().setDateFormat(
                    AppContext.getDateFormat());
            selectDateField.getDateEnd().setValue(rangeDateField.getTo());
            selectDateField.getDateEnd().setDateFormat(
                    AppContext.getDateFormat());
        }

        @Override
        protected void loadSaveSearchToField(final CampaignSearchCriteria value) {
            if (value.getCampaignName() != null) {
                this.nameField.setValue(value.getCampaignName().getValue());
            }

            // Here Load DateField ------------------
            if (value.getStartDateRange() != null) {
                this.loadDateTimeRangeField(value.getStartDateRange(),
                        this.startDateField);
            } else if (value.getStartDate() != null) {
                this.loadDateTimeField(value.getStartDate(),
                        this.startDateField);
            } else {
                this.startDateField.getDateSelectionBox().setValue(
                        DateSelectionComboBox.EQUAL);
                this.startDateField.removeAllDatefield();
            }
            if (value.getEndDateRange() != null) {
                this.loadDateTimeRangeField(value.getEndDateRange(),
                        this.endDateField);
            } else if (value.getEndDate() != null) {
                this.loadDateTimeField(value.getEndDate(), this.endDateField);
            } else {
                this.endDateField.getDateSelectionBox().setValue(
                        DateSelectionComboBox.EQUAL);
                this.endDateField.removeAllDatefield();
            }

            if (value.getTypes() != null) {
                final Object[] typeF = value.getTypes().values;
                this.typeField.setValue(Arrays.asList(typeF));
            }
            if (value.getStatuses() != null) {
                final Object[] statusF = value.getStatuses().values;
                this.statusField.setValue(Arrays.asList(statusF));
            }

            if (value.getAssignUsers() != null) {
                final Object[] assign = value.getAssignUsers().values;
                this.assignUserField.setValue(Arrays.asList(assign));
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected BasicSearchLayout<CampaignSearchCriteria> createBasicSearchLayout() {
        return new CampaignBasicSearchLayout();
    }

    @Override
    protected SearchLayout<CampaignSearchCriteria> createAdvancedSearchLayout() {
        return new CampaignAdvancedSearchLayout();
    }
}
