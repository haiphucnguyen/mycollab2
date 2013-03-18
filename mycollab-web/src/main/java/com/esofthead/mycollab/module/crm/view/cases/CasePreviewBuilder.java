package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Case;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.account.AccountFormLayoutFactory;
import com.esofthead.mycollab.module.crm.view.account.AccountHistoryLogWindow;
import com.esofthead.mycollab.module.crm.view.activity.EventRelatedItemListComp;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class CasePreviewBuilder extends VerticalLayout {

    protected SimpleCase cases;
    protected AdvancedPreviewBeanForm<Case> previewForm;
    protected CaseContactListComp associateContactList;
    protected NoteListItems noteListItems;
    protected EventRelatedItemListComp associateActivityList;

    protected void initRelatedComponent() {
        associateContactList = new CaseContactListComp();
        associateActivityList = new EventRelatedItemListComp(true);
        noteListItems = new NoteListItems("Notes");
    }

    public void previewItem(SimpleCase item) {
        cases = item;
        previewForm.setItemDataSource(new BeanItem<SimpleCase>(cases));
        displayActivities();
        displayContacts();
    }
    
    public void displayActivities() {
        EventSearchCriteria criteria = new EventSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
        criteria.setType(new StringSearchField(SearchField.AND, CrmTypeConstants.CASE));
        criteria.setTypeid(new NumberSearchField(cases.getId()));
        associateActivityList.setSearchCriteria(criteria);
    }
    
    private void displayContacts() {
        associateContactList.displayContacts(cases);
    }

    public EventRelatedItemListComp getAssociateActivityList() {
        return associateActivityList;
    }

    public CaseContactListComp getAssociateContactList() {
        return associateContactList;
    }

    public SimpleCase getCase() {
        return cases;
    }

    public AdvancedPreviewBeanForm<Case> getPreviewForm() {
        return previewForm;
    }

    protected class CaseFormFieldFactory extends DefaultFormViewFieldFactory {

        @Override
        protected Field onCreateField(Item item, Object propertyId,
                Component uiContext) {
            if (propertyId.equals("accountid")) {
                return new FormLinkViewField(cases.getAccountName(),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(ClickEvent event) {
                                EventBus.getInstance().fireEvent(
                                        new AccountEvent.GotoRead(this,
                                        cases.getAccountid()));

                            }
                        });
            } else if (propertyId.equals("email")) {
                return new FormEmailLinkViewField(cases.getEmail());
            } else if (propertyId.equals("assignuser")) {
                return new FormLinkViewField(cases.getAssignUserFullName(), new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        // TODO Auto-generated method stub
                    }
                });
            }

            return null;
        }
    }
    
    public static class ReadView extends CasePreviewBuilder {

        private TabSheet tabContainer;
        private VerticalLayout caseInformationLayout;
        private VerticalLayout relatedItemsContainer;
        private AddViewLayout caseAddLayout;

        public ReadView() {
            caseAddLayout = new AddViewLayout("", new ThemeResource("icons/48/crm/case.png"));
            caseAddLayout.addStyleName("preview");
            this.addComponent(caseAddLayout);

            tabContainer = new TabSheet();
            tabContainer.setStyleName(UIConstants.WHITE_TABSHEET);
            initRelatedComponent();

            previewForm = new AdvancedPreviewBeanForm<Case>() {
                @Override
                public void setItemDataSource(Item newDataSource) {
                    this.setFormLayoutFactory(new CaseFormLayoutFactory.CaseInformationLayout());
                    this.setFormFieldFactory(new CaseFormFieldFactory());
                    super.setItemDataSource(newDataSource);
                    caseAddLayout.setTitle(cases.getSubject());
                }

                @Override
                protected void doPrint() {
                    // Create a window that contains what you want to print
                    Window window = new Window("Window to Print");

                    CasePreviewBuilder printView = new CasePreviewBuilder.PrintView();
                    printView.previewItem(cases);
                    window.addComponent(printView);

                    // Add the printing window as a new application-level window
                    getApplication().addWindow(window);

                    // Open it as a popup window with no decorations
                    getWindow().open(new ExternalResource(window.getURL()),
                            "_blank", 1100, 200, // Width and height 
                            Window.BORDER_NONE); // No decorations

                    // Print automatically when the window opens.
                    // This call will block until the print dialog exits!
                    window.executeJavaScript("print();");

                    // Close the window automatically after printing
                    window.executeJavaScript("self.close();");
                }

                @Override
                protected void showHistory() {
                    AccountHistoryLogWindow historyLog = new AccountHistoryLogWindow(ModuleNameConstants.CRM, CrmTypeConstants.CASE, cases.getId());
                    getWindow().addWindow(historyLog);
                }
            };

            caseInformationLayout = new VerticalLayout();
            caseInformationLayout.setMargin(true);
            Layout actionControls = new PreviewFormControlsGenerator<Case>(
                    previewForm).createButtonControls(RolePermissionCollections.CRM_CASE);
            caseInformationLayout.addComponent(actionControls);
            caseInformationLayout.addComponent(previewForm);
            caseInformationLayout.addComponent(noteListItems);

            tabContainer.addTab(caseInformationLayout, "Case Information");

            relatedItemsContainer = new VerticalLayout();
            relatedItemsContainer.setMargin(true);
            relatedItemsContainer.addComponent(associateActivityList);
            relatedItemsContainer.addComponent(associateContactList);
            tabContainer.addTab(relatedItemsContainer, "More Information");

            caseAddLayout.addBody(tabContainer);
        }
    }

    /**
     *
     */
    public static class PrintView extends CasePreviewBuilder {

        public PrintView() {
            previewForm = new AdvancedPreviewBeanForm<Case>() {
                @Override
                public void setItemDataSource(Item newDataSource) {
                    this.setFormLayoutFactory(new FormLayoutFactory());
                    this.setFormFieldFactory(new CaseFormFieldFactory());
                    super.setItemDataSource(newDataSource);
                }
            };
            initRelatedComponent();

            this.addComponent(previewForm);
        }

        class FormLayoutFactory extends AccountFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super(cases.getSubject());
            }

            @Override
            protected Layout createTopPanel() {
                return null;
            }

            @Override
            protected Layout createBottomPanel() {
                VerticalLayout relatedItemsPanel = new VerticalLayout();
                relatedItemsPanel.setWidth("100%");

                relatedItemsPanel.addComponent(noteListItems);

                relatedItemsPanel.addComponent(associateActivityList);
                relatedItemsPanel.addComponent(associateContactList);

                return relatedItemsPanel;
            }
        }
    }
}
