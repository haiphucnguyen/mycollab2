package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.ui.components.RelatedReadItemField;
import com.esofthead.mycollab.module.crm.view.account.AccountHistoryLogWindow;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class MeetingPreviewBuilder extends VerticalLayout {
	
	 protected SimpleMeeting meeting;
	 protected AdvancedPreviewBeanForm<Meeting> previewForm;
	 
	public void previewItem(SimpleMeeting meeting) {
        this.meeting = meeting;
        previewForm.setItemDataSource(new BeanItem<Meeting>(meeting));
    }
	
	public AdvancedPreviewBeanForm<Meeting> getPreviewForm() {
		return previewForm;
	}
	
	public SimpleMeeting getMeeting() {
		return meeting;
	}

	protected class MeetingFormFieldFactory extends DefaultFormViewFieldFactory {

		private static final long serialVersionUID = 1L;

		@Override
		protected Field onCreateField(Item item, Object propertyId,
				Component uiContext) {
			 if (propertyId.equals("type")) {
                 return new RelatedReadItemField(meeting);
             }
			return null;
		}
	}
	
	    /**
	     *
	     */
	    public static class ReadView extends MeetingPreviewBuilder {
	        
	        private final TabSheet tabContainer;
	        private final VerticalLayout meetingInformation;
	        private final VerticalLayout relatedItemsContainer;
	        private final AddViewLayout meetingAddLayout;
	        
	        public ReadView() {
	        	meetingAddLayout = new AddViewLayout("", new ThemeResource(
	                    "icons/48/crm/meeting.png"));
	            this.addComponent(meetingAddLayout);
	            
	            tabContainer = new TabSheet();
	            
	            previewForm = new AdvancedPreviewBeanForm<Meeting>() {
	                @Override
	                public void setItemDataSource(Item newDataSource) {
	                    this.setFormLayoutFactory(new MeetingFormLayoutFactory.MeetingInformationLayout());
	                    this.setFormFieldFactory(new MeetingFormFieldFactory());
	                    super.setItemDataSource(newDataSource);
	                    meetingAddLayout.setTitle(meeting.getSubject());
	                }
	                
	                @Override
	                protected void doPrint() {
	                    // Create a window that contains what you want to print
	                    Window window = new Window("Window to Print");
	                    
	                    MeetingPreviewBuilder printView = new MeetingPreviewBuilder.PrintView();
	                    printView.previewItem(meeting);
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
	                    AccountHistoryLogWindow historyLog = new AccountHistoryLogWindow(
	                            ModuleNameConstants.CRM, CrmTypeConstants.MEETING,
	                            meeting.getId());
	                    getWindow().addWindow(historyLog);
	                }
	            };
	            
	            meetingInformation = new VerticalLayout();
	            meetingInformation.setMargin(true);
	            Layout actionControls = new PreviewFormControlsGenerator<Meeting>(
	                    previewForm).createButtonControls();
	            meetingInformation.addComponent(actionControls);
	            meetingInformation.addComponent(previewForm);
	            
	            tabContainer.addTab(meetingInformation, "Meeting Information");
	            
	            relatedItemsContainer = new VerticalLayout();
	            relatedItemsContainer.setMargin(true);
	            tabContainer.addTab(relatedItemsContainer, "More Information");
	            
	            meetingAddLayout.addBody(tabContainer);
	        }
	    }

	    /**
	     *
	     */
	    public static class PrintView extends MeetingPreviewBuilder {
	        
	        public PrintView() {
	            previewForm = new AdvancedPreviewBeanForm<Meeting>() {
	                @Override
	                public void setItemDataSource(Item newDataSource) {
	                    this.setFormLayoutFactory(new FormLayoutFactory());
	                    this.setFormFieldFactory(new MeetingFormFieldFactory());
	                    super.setItemDataSource(newDataSource);
	                }
	            };
	            this.addComponent(previewForm);
	        }
	        
	        class FormLayoutFactory extends MeetingFormLayoutFactory {
	            
	            private static final long serialVersionUID = 1L;
	            
	            public FormLayoutFactory() {
	                super(meeting.getSubject());
	            }
	            
	            @Override
	            protected Layout createTopPanel() {
	                return null;
	            }
	            
	            @Override
	            protected Layout createBottomPanel() {
	                return null;
	            }
	        }
	    }

}
