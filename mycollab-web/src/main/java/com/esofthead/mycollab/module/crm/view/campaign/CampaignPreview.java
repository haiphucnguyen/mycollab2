package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.ui.components.HistoryLogWindow;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
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
public class CampaignPreview extends VerticalLayout {
	
	private PreviewForm previewForm;
    private SimpleCampaign campaign;
    private boolean isControlEnable = false;
    
    public CampaignPreview(boolean enableButtonControls) {
    	isControlEnable = enableButtonControls;
    	constructUI();
    }
    
    private void constructUI() {
        previewForm = new PreviewForm();
        this.addComponent(previewForm);
    }
    
    public void previewItem(SimpleCampaign campaign) {
        this.campaign = campaign;
        previewForm.setItemDataSource(new BeanItem<Campaign>(campaign));
    }
    
    public SimpleCampaign getCampaign() {
    	return campaign;
    }
    
    public AdvancedPreviewBeanForm<Campaign> getPreviewForm() {
    	return previewForm;
    }
    
    private class PreviewForm extends AdvancedPreviewBeanForm<Campaign> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
                @Override
                protected Field onCreateField(Item item, Object propertyId,
                        Component uiContext) {
                    if (propertyId.equals("assignuser")) {
                        return new FormLinkViewField(campaign
                                .getAssignUserFullName(),
                                new Button.ClickListener() {
                                    @Override
                                    public void buttonClick(ClickEvent event) {
                                    }
                                });
                    } else if (propertyId.equals("startdate")) {
                        return new FormViewField(AppContext.formatDate(campaign
                                .getStartdate()));
                    } else if (propertyId.equals("enddate")) {
                        return new FormViewField(AppContext.formatDate(campaign
                                .getEnddate()));
                    }

                    return null;
                }
            });
            super.setItemDataSource(newDataSource);
        }
        
        @Override
        protected void doPrint() {
            // Create a window that contains what you want to print
            Window window = new Window("Window to Print");

            CampaignPreview printView = new CampaignPreview(false);
            printView.previewItem(campaign);
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
            HistoryLogWindow historyLog = new HistoryLogWindow(ModuleNameConstants.CRM, CrmTypeConstants.CAMPAIGN, campaign.getId());
            getWindow().addWindow(historyLog);
        }

        class FormLayoutFactory extends CampaignFormLayoutFactory {

            private static final long serialVersionUID = 1L;
            
            public FormLayoutFactory() {
                super(campaign.getCampaignname());
            }

            @Override
            protected Layout createTopPanel() {
            	if (isControlEnable) {
            		 return (new PreviewFormControlsGenerator<Campaign>(
                             PreviewForm.this)).createButtonControls();
            	} else {
            		return new HorizontalLayout();
            	}
            }

            @Override
            protected Layout createBottomPanel() {
                VerticalLayout relatedItemsPanel = new VerticalLayout();

                relatedItemsPanel.addComponent(new NoteListItems(
                        "Notes", "Campaign", campaign.getId()));

                return relatedItemsPanel;

            }
        }
    }


}
