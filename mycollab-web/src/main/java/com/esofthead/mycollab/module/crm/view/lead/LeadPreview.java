package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
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
public class LeadPreview extends VerticalLayout {
	
	private PreviewForm previewForm;
    private SimpleLead lead;
    private boolean isControlEnable = false;
    
    public LeadPreview(boolean enableButtonControls) {
    	isControlEnable = enableButtonControls;
    	constructUI();
    }
    
    private void constructUI() {
        previewForm = new PreviewForm();
        this.addComponent(previewForm);
    }
    
    public void previewItem(SimpleLead lead) {
        this.lead = lead;
        previewForm.setItemDataSource(new BeanItem<Lead>(lead));
    }
    
    public SimpleLead getLead() {
    	return lead;
    }
    
    public AdvancedPreviewBeanForm<Lead> getPreviewForm() {
    	return previewForm;
    }
    
    private class PreviewForm extends AdvancedPreviewBeanForm<Lead> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
                @Override
                protected Field onCreateField(Item item, Object propertyId,
                        Component uiContext) {
                    if (propertyId.equals("firstname")) {
                        if (lead.getTitle() == null) {
                            return new FormViewField(lead.getFirstname());
                        } else {
                            return new FormViewField(lead.getTitle() + " "
                                    + lead.getFirstname());
                        }
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
            });
            super.setItemDataSource(newDataSource);
        }
        
        @Override
        protected void doPrint() {
            // Create a window that contains what you want to print
            Window window = new Window("Window to Print");

            LeadPreview printView = new LeadPreview(false);
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

        class FormLayoutFactory extends LeadFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super("Edit Lead");
            }
            
            @Override
            protected Layout createTopPanel() {
            	if (isControlEnable) {
            		return (new PreviewFormControlsGenerator<Lead>(PreviewForm.this))
                    .createButtonControls();
            	} else {
            		return new HorizontalLayout();
            	}
            }

            @Override
            protected Layout createBottomPanel() {
                VerticalLayout relatedItemsPanel = new VerticalLayout();

                relatedItemsPanel.addComponent(new NoteListItems(
                        "Notes", "Lead", lead.getId()));
                return relatedItemsPanel;
            }
        }
    }

}
