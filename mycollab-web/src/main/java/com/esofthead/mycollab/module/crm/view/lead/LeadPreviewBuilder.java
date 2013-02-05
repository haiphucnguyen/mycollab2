package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.view.activity.EventRelatedItemListComp;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
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
public class LeadPreviewBuilder extends VerticalLayout {

    protected AdvancedPreviewBeanForm<Lead> previewForm;
    protected SimpleLead lead;
    protected LeadCampaignListComp associateCampaignList;
    protected EventRelatedItemListComp associateActivityList;
    protected NoteListItems noteListItems;

    protected void initRelatedComponent() {
        associateCampaignList = new LeadCampaignListComp();
        noteListItems = new NoteListItems("Notes");
        associateActivityList = new EventRelatedItemListComp(true);
    }

    public void previewItem(SimpleLead lead) {
        this.lead = lead;
        previewForm.setItemDataSource(new BeanItem<Lead>(lead));
        displayActivities();
        displayNotes();
    }

    public SimpleLead getLead() {
        return lead;
    }
    
    private void displayNotes() {
        noteListItems.showNotes(CrmTypeConstants.LEAD, lead.getId());
    }
    
    public void displayActivities() {
        EventSearchCriteria criteria = new EventSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(AppContext.getAccountId()));
        criteria.setType(new StringSearchField(SearchField.AND, CrmTypeConstants.LEAD));
        criteria.setTypeid(new NumberSearchField(lead.getId()));
        associateActivityList.setSearchCriteria(criteria);
    }

    public EventRelatedItemListComp getAssociateActivityList() {
        return associateActivityList;
    }

    public AdvancedPreviewBeanForm<Lead> getPreviewForm() {
        return previewForm;
    }

    protected class LeadFormFieldFactory extends DefaultFormViewFieldFactory {

        @Override
        protected Field onCreateField(Item item, Object propertyId,
                Component uiContext) {
            if (propertyId.equals("firstname")) {
                if (lead.getTitle() == null) {
                    return new FormViewField(lead.getFirstname());
                } else {
                    return new FormViewField(lead.getTitle()
                            + lead.getFirstname());
                }
            } else if (propertyId.equals("website")) {
            	return new DefaultFormViewFieldFactory.FormUrlLinkViewField(lead.getWebsite());
            } else if (propertyId.equals("email")) {
                return new FormEmailLinkViewField(lead.getEmail());
            } else if (propertyId.equals("accountid")) {
                FormLinkViewField field = new FormLinkViewField(lead
                        .getAccountname(), new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                    }
                });

                return field;
            } else if (propertyId.equals("assignuser")) {
                return new FormLinkViewField(lead.getAssignUserFullName(), new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        // TODO Auto-generated method stub
                    }
                });
            }

            return super.onCreateField(item, propertyId, uiContext);
        }
    }

    public static class ReadView extends LeadPreviewBuilder {

        private static final long serialVersionUID = 1L;
        private TabSheet tabContainer;
        private VerticalLayout campaignInformationLayout;
        private VerticalLayout relatedItemsContainer;
        private AddViewLayout campaignAddLayout;

        public ReadView() {
            campaignAddLayout = new AddViewLayout("", new ThemeResource("icons/48/crm/lead.png"));
            campaignAddLayout.addStyleName("preview");
            this.addComponent(campaignAddLayout);

            tabContainer = new TabSheet();
            initRelatedComponent();

            previewForm = new AdvancedPreviewBeanForm<Lead>() {
                @Override
                public void setItemDataSource(Item newDataSource) {
                    this.setFormLayoutFactory(new LeadFormLayoutFactory.LeadInformationLayout());
                    this.setFormFieldFactory(new LeadFormFieldFactory());
                    super.setItemDataSource(newDataSource);
                    campaignAddLayout.setTitle(lead.getLeadName());
                }

                @Override
                protected void doPrint() {
                    // Create a window that contains what you want to print
                    Window window = new Window("Window to Print");

                    LeadPreviewBuilder printView = new LeadPreviewBuilder.PrintView();
                    printView.previewItem(lead);
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
                    LeadHistoryLogWindow historyLog = new LeadHistoryLogWindow(ModuleNameConstants.CRM, CrmTypeConstants.LEAD, lead.getId());
                    getWindow().addWindow(historyLog);
                }
            };

            campaignInformationLayout = new VerticalLayout();
            campaignInformationLayout.setMargin(true);
            Layout actionControls = new PreviewFormControlsGenerator<Lead>(
                    previewForm).createButtonControls(RolePermissionCollections.CRM_LEAD);
            campaignInformationLayout.addComponent(actionControls);
            campaignInformationLayout.addComponent(previewForm);
            campaignInformationLayout.addComponent(noteListItems);

            tabContainer.addTab(campaignInformationLayout, "Lead Information");



            relatedItemsContainer = new VerticalLayout();
            relatedItemsContainer.setMargin(true);
            relatedItemsContainer.addComponent(associateActivityList);
            relatedItemsContainer.addComponent(associateCampaignList);
            tabContainer.addTab(relatedItemsContainer, "More Information");

            campaignAddLayout.addBody(tabContainer);
        }
    }

    public static class PrintView extends LeadPreviewBuilder {

        public PrintView() {
            previewForm = new AdvancedPreviewBeanForm<Lead>() {
                @Override
                public void setItemDataSource(Item newDataSource) {
                    this.setFormLayoutFactory(new FormLayoutFactory());
                    this.setFormFieldFactory(new LeadFormFieldFactory());
                    super.setItemDataSource(newDataSource);
                }
            };
            initRelatedComponent();

            this.addComponent(previewForm);
        }

        class FormLayoutFactory extends LeadFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super(lead.getLeadName());
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
                relatedItemsPanel.addComponent(associateCampaignList);

                return relatedItemsPanel;
            }
        }
    }
}
