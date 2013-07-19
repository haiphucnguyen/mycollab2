package com.esofthead.mycollab.module.crm.view.cases;

import java.util.Arrays;
import java.util.Collection;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionField;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.module.user.ui.components.UserListSelect;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.DefaultAdvancedSearchLayout;
import com.esofthead.mycollab.vaadin.ui.DefaultGenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.Separator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Reindeer;

public class CaseSearchPanel extends
        DefaultGenericSearchPanel<CaseSearchCriteria> {

    private static final long serialVersionUID = 1L;
    private CaseSearchCriteria searchCriteria;

    public CaseSearchPanel() {
        super();
    }

    private class CaseAdvancedSearchLayout extends
            DefaultAdvancedSearchLayout<CaseSearchCriteria> {

        public CaseAdvancedSearchLayout() {
            super(CaseSearchPanel.this, CrmTypeConstants.CASE);
        }

        private static final long serialVersionUID = 1L;
        private TextField numberField;
        private TextField subjectField;
        private AccountSelectionField accountField;
        private CaseStatusListSelect statusField;
        private UserListSelect userField;
        private CasePriorityListSelect priorityField;

        @Override
        public ComponentContainer constructHeader() {
            return CaseSearchPanel.this.createSearchTopPanel();
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

            this.numberField = (TextField) gridLayout.addComponent(
                    new TextField(), "Number", 0, 0);
            this.subjectField = (TextField) gridLayout.addComponent(
                    new TextField(), "Subject", 1, 0);
            this.accountField = (AccountSelectionField) gridLayout
                    .addComponent(new AccountSelectionField(), "Account", 2, 0);

            this.statusField = (CaseStatusListSelect) gridLayout.addComponent(
                    new CaseStatusListSelect(), "Status", 0, 1);
            this.userField = (UserListSelect) gridLayout.addComponent(
                    new UserListSelect(), "Assigned to", 1, 1);
            this.priorityField = (CasePriorityListSelect) gridLayout
                    .addComponent(new CasePriorityListSelect(), "Priority", 2,
                            1);

            gridLayout.getLayout().setSpacing(true);
            return gridLayout.getLayout();
        }

        @Override
        protected CaseSearchCriteria fillupSearchCriteria() {
            CaseSearchPanel.this.searchCriteria = new CaseSearchCriteria();
            CaseSearchPanel.this.searchCriteria
                    .setSaccountid(new NumberSearchField(SearchField.AND,
                            AppContext.getAccountId()));

            if (StringUtil.isNotNullOrEmpty((String) this.subjectField
                    .getValue())) {
                CaseSearchPanel.this.searchCriteria
                        .setSubject(new StringSearchField(SearchField.AND,
                                ((String) this.subjectField.getValue()).trim()));
            }

            final SimpleAccount account = this.accountField.getAccount();
            if (StringUtil.isNotNullOrEmpty(account.getAccountname())) {
                CaseSearchPanel.this.searchCriteria
                        .setAccountName(new StringSearchField(SearchField.AND,
                                account.getAccountname()));
            }

            final Collection<String> statuses = (Collection<String>) this.statusField
                    .getValue();
            if (statuses != null && statuses.size() > 0) {
                CaseSearchPanel.this.searchCriteria
                        .setStatuses(new SetSearchField<String>(
                                SearchField.AND, statuses));
            }

            final Collection<String> assignUsers = (Collection<String>) this.userField
                    .getValue();
            if (assignUsers != null && assignUsers.size() > 0) {
                CaseSearchPanel.this.searchCriteria
                        .setAssignUsers(new SetSearchField<String>(
                                SearchField.AND, assignUsers));
            }

            final Collection<String> priorities = (Collection<String>) this.priorityField
                    .getValue();
            if (priorities != null && priorities.size() > 0) {
                CaseSearchPanel.this.searchCriteria
                        .setPriorities(new SetSearchField<String>(
                                SearchField.AND, priorities));
            }
            return CaseSearchPanel.this.searchCriteria;
        }

        @Override
        protected void clearFields() {
            this.numberField.setValue("");
            this.subjectField.setValue("");
            this.accountField.clearValue();
            this.statusField.setValue(null);
            this.userField.setValue(null);
            this.priorityField.setValue(null);
        }

        @Override
        protected void loadSaveSearchToField(final CaseSearchCriteria value) {
            // case thieu numberField
            if (value.getSubject() != null) {
                this.subjectField.setValue(value.getSubject().getValue());
            }
            if (value.getAccountName() != null) {
                this.accountField.setValue(value.getAccountName().getValue());
            }
            if (value.getStatuses() != null) {
                this.statusField.setValue(Arrays.asList((Object[]) value
                        .getStatuses().values));
            }

            if (value.getAssignUsers() != null) {
                this.userField.setValue(Arrays.asList((Object[]) value
                        .getAssignUsers().values));
            }
            if (value.getPriorities() != null) {
                this.priorityField.setValue(Arrays.asList((Object[]) value
                        .getPriorities().values));
            }
        }
    }

    private HorizontalLayout createSearchTopPanel() {
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("100%");
        layout.setSpacing(true);

        final Embedded titleIcon = new Embedded();
        titleIcon.setSource(MyCollabResource
                .newResource("icons/22/crm/case.png"));
        layout.addComponent(titleIcon);
        layout.setComponentAlignment(titleIcon, Alignment.MIDDLE_LEFT);

        final Label searchtitle = new Label("Cases");
        searchtitle.setStyleName(Reindeer.LABEL_H2);
        layout.addComponent(searchtitle);
        layout.setExpandRatio(searchtitle, 1.0f);
        layout.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);

        final Button createAccountBtn = new Button("Create",
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(final ClickEvent event) {
                        EventBus.getInstance().fireEvent(
                                new CaseEvent.GotoAdd(this, null));
                    }
                });
        createAccountBtn.setIcon(MyCollabResource
                .newResource("icons/16/addRecord.png"));
        createAccountBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        createAccountBtn.setEnabled(AppContext
                .canWrite(RolePermissionCollections.CRM_CASE));
        UiUtils.addComponent(layout, createAccountBtn, Alignment.MIDDLE_RIGHT);

        return layout;
    }

    @SuppressWarnings("rawtypes")
    private class CaseBasicSearchLayout extends BasicSearchLayout {

        private static final long serialVersionUID = 1L;
        private TextField subjectField;
        private CheckBox myItemCheckbox;

        @SuppressWarnings("unchecked")
        public CaseBasicSearchLayout() {
            super(CaseSearchPanel.this);
        }

        @Override
        public ComponentContainer constructHeader() {
            return CaseSearchPanel.this.createSearchTopPanel();
        }

        @SuppressWarnings("serial")
        @Override
        public ComponentContainer constructBody() {
            final HorizontalLayout basicSearchBody = new HorizontalLayout();
            basicSearchBody.setSpacing(false);

            this.subjectField = this.createSeachSupportTextField(
                    new TextField(), "subjectFieldName");
            this.subjectField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
            UiUtils.addComponent(basicSearchBody, this.subjectField,
                    Alignment.MIDDLE_CENTER);
            this.subjectField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
            UiUtils.addComponent(basicSearchBody, this.subjectField,
                    Alignment.MIDDLE_CENTER);

            final Button searchBtn = new Button();
            searchBtn.setStyleName("search-icon-button");
            searchBtn.setIcon(MyCollabResource
                    .newResource("icons/16/search_white.png"));

            searchBtn.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(final ClickEvent event) {
                    CaseBasicSearchLayout.this.callSearchAction();
                }
            });
            UiUtils.addComponent(basicSearchBody, searchBtn,
                    Alignment.MIDDLE_LEFT);

            this.myItemCheckbox = new CheckBox(
                    LocalizationHelper
                            .getMessage(CrmCommonI18nEnum.SEARCH_MYITEMS_CHECKBOX));
            this.myItemCheckbox.setWidth("75px");
            UiUtils.addComponent(basicSearchBody, this.myItemCheckbox,
                    Alignment.MIDDLE_CENTER);

            final Separator separator1 = new Separator();

            UiUtils.addComponent(basicSearchBody, separator1,
                    Alignment.MIDDLE_LEFT);

            final Button cancelBtn = new Button(
                    LocalizationHelper
                            .getMessage(CrmCommonI18nEnum.BUTTON_CLEAR));
            cancelBtn.setStyleName(UIConstants.THEME_LINK);
            cancelBtn.addStyleName("cancel-button");
            cancelBtn.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(final ClickEvent event) {
                    CaseBasicSearchLayout.this.subjectField.setValue("");
                }
            });
            UiUtils.addComponent(basicSearchBody, cancelBtn,
                    Alignment.MIDDLE_CENTER);

            final Separator separator2 = new Separator();

            UiUtils.addComponent(basicSearchBody, separator2,
                    Alignment.MIDDLE_LEFT);

            final Button advancedSearchBtn = new Button("Advanced Search",
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(final ClickEvent event) {
                            CaseSearchPanel.this.moveToAdvancedSearchLayout();
                        }
                    });
            advancedSearchBtn.setStyleName("link");
            UiUtils.addComponent(basicSearchBody, advancedSearchBtn,
                    Alignment.MIDDLE_CENTER);
            return basicSearchBody;
        }

        @Override
        protected SearchCriteria fillupSearchCriteria() {
            CaseSearchPanel.this.searchCriteria = new CaseSearchCriteria();
            CaseSearchPanel.this.searchCriteria
                    .setSaccountid(new NumberSearchField(SearchField.AND,
                            AppContext.getAccountId()));

            if (StringUtil.isNotNullOrEmpty(this.subjectField.getValue()
                    .toString().trim())) {
                CaseSearchPanel.this.searchCriteria
                        .setSubject(new StringSearchField(SearchField.AND,
                                ((String) this.subjectField.getValue()).trim()));
            }

            if (this.myItemCheckbox.booleanValue()) {
                CaseSearchPanel.this.searchCriteria
                        .setAssignUsers(new SetSearchField<String>(
                                SearchField.AND, new String[] { AppContext
                                        .getUsername() }));
            } else {
                CaseSearchPanel.this.searchCriteria.setAssignUsers(null);
            }
            return CaseSearchPanel.this.searchCriteria;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected BasicSearchLayout<CaseSearchCriteria> createBasicSearchLayout() {
        return new CaseBasicSearchLayout();
    }

    @Override
    protected SearchLayout<CaseSearchCriteria> createAdvancedSearchLayout() {
        return new CaseAdvancedSearchLayout();
    }
}
