/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.MeetingWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.ui.components.RelatedReadItemField;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator2;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class MeetingPreviewBuilder extends VerticalLayout {

	protected SimpleMeeting meeting;

	protected AdvancedPreviewBeanForm<MeetingWithBLOBs> previewForm;
	protected NoteListItems noteListItems;

	protected void initRelatedComponent() {
		noteListItems = new NoteListItems("Notes");
	}

	public void previewItem(SimpleMeeting meeting) {
		this.meeting = meeting;
		previewForm.setItemDataSource(new BeanItem<SimpleMeeting>(meeting));

		displayNotes();
	}

	private void displayNotes() {
		noteListItems.showNotes(CrmTypeConstants.MEETING, meeting.getId());
	}

	public AdvancedPreviewBeanForm<MeetingWithBLOBs> getPreviewForm() {
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
			} else if (propertyId.equals("startdate")) {
				if (meeting.getStartdate() == null)
					return null;
				return new DateFieldWithUserTimeZone(meeting.getStartdate(),
						"DATETIME_FIELD");
			} else if (propertyId.equals("enddate")) {
				if (meeting.getEnddate() == null)
					return null;
				return new DateFieldWithUserTimeZone(meeting.getEnddate(),
						"DATETIME_FIELD");
			} else if (propertyId.equals("isrecurrence")) {
				return null;
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

		private final VerticalLayout meetingInformation;
		private final ReadViewLayout meetingAddLayout;

		public ReadView() {
			meetingAddLayout = new ReadViewLayout(
					MyCollabResource.newResource("icons/22/crm/meeting.png"));
			this.addComponent(meetingAddLayout);

			initRelatedComponent();

			previewForm = new AdvancedPreviewBeanForm<MeetingWithBLOBs>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new DynaFormLayout(
							CrmTypeConstants.MEETING,
							MeetingDefaultFormLayoutFactory.getForm()));
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

			final Layout optionalActionControls = PreviewFormControlsGenerator2
					.createFormOptionalControls(previewForm,
							RolePermissionCollections.CRM_MEETING);

			meetingAddLayout.addControlButtons(optionalActionControls);

			meetingInformation = new VerticalLayout();
			meetingInformation.addStyleName("main-info");
			final Layout actionControls = PreviewFormControlsGenerator2
					.createFormControls(previewForm,
							RolePermissionCollections.CRM_MEETING);
			actionControls.addStyleName("control-buttons");
			meetingInformation.addComponent(actionControls);

			// meetingInformation.setMargin(true);

			meetingInformation.addComponent(previewForm);
			meetingInformation.addComponent(noteListItems);

			meetingAddLayout.addTab(meetingInformation, "Meeting Information");

			this.addComponent(meetingAddLayout);
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
			previewForm = new AdvancedPreviewBeanForm<MeetingWithBLOBs>() {
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
