package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.ui.components.RelatedReadItemField;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
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
	protected NoteListItems noteListItems;

	protected void initRelatedComponent() {
		noteListItems = new NoteListItems("Notes");
	}

	public void previewItem(SimpleMeeting meeting) {
		this.meeting = meeting;
		previewForm.setItemDataSource(new BeanItem<Meeting>(meeting));

		displayNotes();
	}

	private void displayNotes() {
		noteListItems.showNotes(CrmTypeConstants.MEETING, meeting.getId());
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
	 * @author haiphucnguyen
	 * 
	 */
	public static class ReadView extends MeetingPreviewBuilder {

		private final TabSheet tabContainer;
		private final VerticalLayout meetingInformation;
		private final AddViewLayout meetingAddLayout;

		public ReadView() {
			meetingAddLayout = new AddViewLayout("", new ThemeResource(
					"icons/48/crm/meeting.png"));
			meetingAddLayout.addStyleName("preview");
			this.addComponent(meetingAddLayout);

			initRelatedComponent();

			tabContainer = new TabSheet();
			tabContainer.setStyleName(UIConstants.WHITE_TABSHEET);

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
					MeetingHistoryLogWindow historyLog = new MeetingHistoryLogWindow(
							ModuleNameConstants.CRM, CrmTypeConstants.MEETING,
							meeting.getId());
					getWindow().addWindow(historyLog);
				}
			};

			meetingInformation = new VerticalLayout();
			meetingInformation.setMargin(true);
			meetingInformation.setMargin(true);
			Layout actionControls = new PreviewFormControlsGenerator<Meeting>(
					previewForm)
					.createButtonControls(RolePermissionCollections.CRM_MEETING);
			meetingInformation.addComponent(actionControls);
			meetingInformation.addComponent(previewForm);
			meetingInformation.addComponent(noteListItems);

			tabContainer.addTab(meetingInformation, "Meeting Information");

			meetingAddLayout.addBody(tabContainer);
		}
	}

	/**
	 * 
	 * @author haiphucnguyen
	 * 
	 */
	public static class PrintView extends MeetingPreviewBuilder {

		public PrintView() {
			initRelatedComponent();
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
				VerticalLayout relatedItemsPanel = new VerticalLayout();
				relatedItemsPanel.setWidth("100%");

				relatedItemsPanel.addComponent(noteListItems);
				return relatedItemsPanel;
			}
		}
	}

}
