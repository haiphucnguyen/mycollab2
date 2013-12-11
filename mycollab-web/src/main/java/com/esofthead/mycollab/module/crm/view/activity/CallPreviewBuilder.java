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
import com.esofthead.mycollab.module.crm.domain.CallWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleCall;
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
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class CallPreviewBuilder extends VerticalLayout {

	protected AdvancedPreviewBeanForm<SimpleCall> previewForm;
	protected NoteListItems noteListItems;

	protected SimpleCall call;

	protected void initRelatedComponent() {
		this.noteListItems = new NoteListItems("Notes");
	}

	public void previewItem(final SimpleCall item) {
		this.call = item;
		this.previewForm.setItemDataSource(new BeanItem<CallWithBLOBs>(
				this.call));

		this.displayNotes();
	}

	private void displayNotes() {
		this.noteListItems.showNotes(CrmTypeConstants.CALL, this.call.getId());
	}

	public AdvancedPreviewBeanForm<SimpleCall> getPreviewForm() {
		return this.previewForm;
	}

	public SimpleCall getCall() {
		return this.call;
	}

	protected class CallFormFieldFactory extends DefaultFormViewFieldFactory {

		private static final long serialVersionUID = 1L;

		@Override
		protected Field onCreateField(final Item item, final Object propertyId,
				final Component uiContext) {
			if (propertyId.equals("assignuser")) {
				return new UserLinkViewField(
						CallPreviewBuilder.this.call.getAssignuser(),
						CallPreviewBuilder.this.call.getAssignUserAvatarId(),
						CallPreviewBuilder.this.call.getAssignUserFullName());
			} else if (propertyId.equals("type")) {
				return new RelatedReadItemField(CallPreviewBuilder.this.call);
			} else if (propertyId.equals("status")) {
				final String value = CallPreviewBuilder.this.call.getStatus()
						+ " " + CallPreviewBuilder.this.call.getCalltype();
				final FormViewField field = new FormViewField(value);
				return field;
			} else if (propertyId.equals("durationinseconds")) {
				final Integer duration = CallPreviewBuilder.this.call
						.getDurationinseconds();
				if (duration != null && duration != 0) {
					final int hours = duration / 3600;
					final int minutes = (duration % 3600) / 60;
					final StringBuffer value = new StringBuffer();
					if (hours == 1) {
						value.append("1 hour ");
					} else if (hours >= 2) {
						value.append(hours + " hours ");
					}

					if (minutes > 0) {
						value.append(minutes + " minutes");
					}

					return new FormViewField(value.toString());
				} else {
					return new FormViewField("");
				}
			} else if (propertyId.equals("startdate")) {
				if (call.getStartdate() == null)
					return new FormViewField("");
				return new DateFieldWithUserTimeZone(call.getStartdate(),
						"DATETIME_FIELD");
			}

			return null;
		}
	}

	public static class ReadView extends CallPreviewBuilder {

		private final VerticalLayout callInformation;
		private final ReadViewLayout callAddLayout;

		public ReadView() {
			this.callAddLayout = new ReadViewLayout(
					MyCollabResource.newResource("icons/22/crm/call.png"));
			this.addComponent(this.callAddLayout);
			this.initRelatedComponent();

			this.previewForm = new AdvancedPreviewBeanForm<SimpleCall>() {
				@Override
				public void setItemDataSource(final Item newDataSource) {
					this.setFormLayoutFactory(new DynaFormLayout(
							CrmTypeConstants.CALL, CallDefaultFormLayoutFactory
									.getForm()));
					this.setFormFieldFactory(new CallFormFieldFactory());
					super.setItemDataSource(newDataSource);
					ReadView.this.callAddLayout.setTitle(ReadView.this.call
							.getSubject());
				}

				@Override
				protected void doPrint() {
					// Create a window that contains what you want to print
					final Window window = new Window("Window to Print");

					final CallPreviewBuilder printView = new CallPreviewBuilder.PrintView();
					printView.previewItem(ReadView.this.call);
					window.setContent(printView);

					UI.getCurrent().addWindow(window);

					// Print automatically when the window opens
					JavaScript.getCurrent().execute(
							"setTimeout(function() {"
									+ "  print(); self.close();}, 0);");
				}

				@Override
				protected void showHistory() {
					final CallHistoryLogWindow historyLog = new CallHistoryLogWindow(
							ModuleNameConstants.CRM, CrmTypeConstants.CALL,
							ReadView.this.call.getId());
					UI.getCurrent().addWindow(historyLog);
				}
			};

			final Layout optionalActionControls = PreviewFormControlsGenerator2
					.createFormOptionalControls(this.previewForm,
							RolePermissionCollections.CRM_CALL);

			this.callAddLayout.addControlButtons(optionalActionControls);

			this.callInformation = new VerticalLayout();
			this.callInformation.addStyleName("main-info");

			final Layout actionControls = PreviewFormControlsGenerator2
					.createFormControls(this.previewForm,
							RolePermissionCollections.CRM_CALL);
			actionControls.addStyleName("control-buttons");
			this.callInformation.addComponent(actionControls);

			this.callInformation.addComponent(this.previewForm);
			this.callInformation.addComponent(this.noteListItems);

			this.callAddLayout.addTab(this.callInformation, "Call Information");
		}
	}

	/**
	 * 
	 * @author MyCollab Ltd.
	 * 
	 */
	public static class PrintView extends CallPreviewBuilder {

		public PrintView() {
			this.initRelatedComponent();
			this.previewForm = new AdvancedPreviewBeanForm<SimpleCall>() {
				@Override
				public void setItemDataSource(final Item newDataSource) {
					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new CallFormFieldFactory());
					super.setItemDataSource(newDataSource);
				}
			};

			this.addComponent(this.previewForm);
		}

		class FormLayoutFactory extends CallFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(PrintView.this.call.getSubject());
			}

			@Override
			protected Layout createTopPanel() {
				return null;
			}

			@Override
			protected Layout createBottomPanel() {
				final VerticalLayout relatedItemsPanel = new VerticalLayout();
				relatedItemsPanel.setWidth("100%");

				relatedItemsPanel.addComponent(PrintView.this.noteListItems);
				return relatedItemsPanel;
			}
		}
	}

}
