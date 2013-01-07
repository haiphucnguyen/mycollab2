package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Case;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.vaadin.events.EventBus;
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
public class CasePreview extends VerticalLayout {

	 	private SimpleCase cases;
	    private PreviewForm previewForm;
	    private boolean isControlEnable = false;
	    public CasePreview(Boolean enableButtonControl) {
	    	this.isControlEnable = enableButtonControl;
	    	constructUI();
	    }
	    
	    private void constructUI() {
			previewForm = new PreviewForm();

			this.addComponent(previewForm);
		}
	    
	    public void previewItem(SimpleCase item) {
	    	cases = item;
			previewForm.setItemDataSource(new BeanItem<SimpleCase>(cases));
		}
	    
	    public SimpleCase getCase() {
	    	return cases;
	    }
	    
	    public AdvancedPreviewBeanForm<Case> getPreviewForm() {
	    	return previewForm;
	    }
	    
	    public class PreviewForm extends AdvancedPreviewBeanForm<Case> {

	        private static final long serialVersionUID = 1L;

	        @Override
	        public void setItemDataSource(Item newDataSource) {
	            this.setFormLayoutFactory(new FormLayoutFactory());
	            this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
	                private static final long serialVersionUID = 1L;

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
	            });
	            super.setItemDataSource(newDataSource);
	        }
	        
	        protected void doPrint() {
				// Create a window that contains what you want to print
				Window window = new Window("Window to Print");

				CasePreview printView = new CasePreview(false);
				printView.previewItem(cases);
				window.addComponent(printView);

				// Add the printing window as a new application-level window
				getApplication().addWindow(window);

				// Open it as a popup window with no decorations
				getWindow().open(new ExternalResource(window.getURL()), "_blank",
						1100, 200, // Width and height
						Window.BORDER_NONE); // No decorations

				// Print automatically when the window opens.
				// This call will block until the print dialog exits!
				window.executeJavaScript("print();");

				// Close the window automatically after printing
				window.executeJavaScript("self.close();");
			}

			@Override
			protected void showHistory() {
				CaseHistoryLogWindow historyLog = new CaseHistoryLogWindow(
						ModuleNameConstants.CRM, CrmTypeConstants.CASE,
						cases.getId());

				getWindow().addWindow(historyLog);
			}

	        class FormLayoutFactory extends CaseFormLayoutFactory {

	            private static final long serialVersionUID = 1L;

	            public FormLayoutFactory() {
	                super(cases.getSubject());
	            }

	            @Override
	            protected Layout createTopPanel() {
	            	if (isControlEnable) {
	            		 return (new PreviewFormControlsGenerator<Case>(PreviewForm.this))
	                        .createButtonControls();
	            	} else {
	            		return new HorizontalLayout();
	            	}
	            }

	            @Override
	            protected Layout createBottomPanel() {
	                VerticalLayout relatedItemsPanel = new VerticalLayout();
	                relatedItemsPanel.addComponent(new NoteListItems(
	                        "Notes", "Case", cases.getId()));
	                return relatedItemsPanel;
	            }
	        }
	    }
}
