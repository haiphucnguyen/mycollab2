package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class OpportunityPreview extends VerticalLayout {

	private PreviewForm previewForm;
    private SimpleOpportunity opportunity;
    private boolean isControlEnable = false;
    
    public OpportunityPreview(boolean enableButtonControls) {
    	isControlEnable = enableButtonControls;
    	constructUI();
    }
    
    private void constructUI() {
        previewForm = new PreviewForm();
        this.addComponent(previewForm);
    }
    
    public void previewItem(SimpleOpportunity item) {
        this.opportunity = item;
        previewForm.setItemDataSource(new BeanItem<Opportunity>(opportunity));
    }
    
    public SimpleOpportunity getOpportunity() {
    	return opportunity;
    }

    public AdvancedPreviewBeanForm<Opportunity> getPreviewForm() {
    	return previewForm;
    }
    
    private class PreviewForm extends AdvancedPreviewBeanForm<Opportunity> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
                private static final long serialVersionUID = 1L;

                @Override
                protected Field onCreateField(Item item, Object propertyId,
                        Component uiContext) {
                    Field field = null;
                    if (propertyId.equals("accountid")) {
                        field = new FormLinkViewField(opportunity
                                .getAccountName(), new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(ClickEvent event) {
                                EventBus.getInstance().fireEvent(
                                        new AccountEvent.GotoRead(this,
                                        opportunity.getAccountid()));
                            }
                        });
                    } else if (propertyId.equals("campaignid")) {
                        field = new FormLinkViewField(opportunity
                                .getCampaignName(), new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(ClickEvent event) {
                                EventBus.getInstance().fireEvent(
                                        new CampaignEvent.GotoRead(this,
                                        opportunity.getCampaignid()));

                            }
                        });
                    } else if (propertyId.equals("assignuser")) {
                        field = new FormLinkViewField(opportunity
                                .getAssignUserFullName(),
                                new Button.ClickListener() {
                                    private static final long serialVersionUID = 1L;

                                    @Override
                                    public void buttonClick(ClickEvent event) {
                                        // TODO Auto-generated method stub
                                    }
                                });
                    } else if (propertyId.equals("expectedcloseddate")) {
                        field = new FormViewField(
                                AppContext.formatDate(opportunity
                                .getExpectedcloseddate()));
                    }
                    return field;
                }
            });
            super.setItemDataSource(newDataSource);
        }
        
        @Override
        protected void doPrint() {
            // Create a window that contains what you want to print
            Window window = new Window("Window to Print");

            OpportunityPreview printView = new OpportunityPreview(false);
            printView.previewItem(opportunity);
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
            OpportunityHistoryLogWindow historyLog = new OpportunityHistoryLogWindow(ModuleNameConstants.CRM, CrmTypeConstants.OPPORTUNITY, opportunity.getId());
            getWindow().addWindow(historyLog);
        }

        class FormLayoutFactory extends OpportunityFormLayoutFactory {

            private static final long serialVersionUID = 1L;
            
            public FormLayoutFactory() {
                super("Edit Opportunity");
            }

            @Override
            protected Layout createTopPanel() {
            	if (isControlEnable) {
            		return (new PreviewFormControlsGenerator<Opportunity>(
                            PreviewForm.this)).createButtonControls();
            	} else {
            		return new HorizontalLayout();
            	}
            }

            @Override
            protected Layout createBottomPanel() {
                VerticalLayout relatedItemsPanel = new VerticalLayout();

                relatedItemsPanel.addComponent(new NoteListItems("Notes",
                        "Opportunity", opportunity.getId()));
                return relatedItemsPanel;
            }
        }
    }
	
}
