package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.CallWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.module.crm.ui.components.RelatedReadItemField;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator2;
import com.esofthead.mycollab.vaadin.ui.ReadViewLayout;
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
public class CallPreviewBuilder extends VerticalLayout {

	protected AdvancedPreviewBeanForm<SimpleCall> previewForm;
	protected NoteListItems noteListItems;

	protected SimpleCall call;

	protected void initRelatedComponent() {
		noteListItems = new NoteListItems("Notes");
	}

	public void previewItem(SimpleCall item) {
		call = item;
		previewForm.setItemDataSource(new BeanItem<CallWithBLOBs>(call));

		displayNotes();
	}

	private void displayNotes() {
		noteListItems.showNotes(CrmTypeConstants.CALL, call.getId());
	}

	public AdvancedPreviewBeanForm<SimpleCall> getPreviewForm() {
		return previewForm;
	}

	public SimpleCall getCall() {
		return call;
	}

	protected class CallFormFieldFactory extends DefaultFormViewFieldFactory {

		private static final long serialVersionUID = 1L;

		@Override
		protected Field onCreateField(Item item, Object propertyId,
				Component uiContext) {
			if (propertyId.equals("assignuser")) {
				return new FormLinkViewField(call.getAssignUserFullName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
							}
						});
			} else if (propertyId.equals("type")) {
				return new RelatedReadItemField(call);
			} else if (propertyId.equals("status")) {
				String value = call.getStatus() + " " + call.getCalltype();
				FormViewField field = new FormViewField(value);
				return field;
			} else if (propertyId.equals("durationinseconds")) {
				Integer duration = call.getDurationinseconds();
				if (duration != null && duration != 0) {
					int hours = duration / 3600;
					int minutes = (duration % 3600) / 60;
					StringBuffer value = new StringBuffer();
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
				return new FormViewField(AppContext.formatDateTime(call
						.getStartdate()));
			}

			return null;
		}
	}

	public static class ReadView extends CallPreviewBuilder {

		private VerticalLayout callInformation;
		private ReadViewLayout callAddLayout;

		public ReadView() {
			callAddLayout = new ReadViewLayout(new ThemeResource(
					"icons/48/crm/call.png"));
			this.addComponent(callAddLayout);
			initRelatedComponent();

			previewForm = new AdvancedPreviewBeanForm<SimpleCall>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new CallFormLayoutFactory.CallInformationLayout());
					this.setFormFieldFactory(new CallFormFieldFactory());
					super.setItemDataSource(newDataSource);
					callAddLayout.setTitle(call.getSubject());
				}

				@Override
				protected void doPrint() {
					// Create a window that contains what you want to print
					Window window = new Window("Window to Print");

					CallPreviewBuilder printView = new CallPreviewBuilder.PrintView();
					printView.previewItem(call);
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
					CallHistoryLogWindow historyLog = new CallHistoryLogWindow(
							ModuleNameConstants.CRM, CrmTypeConstants.CALL,
							call.getId());
					getWindow().addWindow(historyLog);
				}
			};

			final Layout optionalActionControls = PreviewFormControlsGenerator2
					.createFormOptionalControls(previewForm,
							RolePermissionCollections.CRM_CALL);

			callAddLayout.addControlButtons(optionalActionControls);

			callInformation = new VerticalLayout();
			callInformation.addStyleName("main-info");
			callInformation.setMargin(true);

			final Layout actionControls = PreviewFormControlsGenerator2
					.createFormControls(previewForm,
							RolePermissionCollections.CRM_CALL);
			actionControls.addStyleName("control-buttons");
			callInformation.addComponent(actionControls);
			
			callInformation.addComponent(previewForm);
			callInformation.addComponent(noteListItems);
			
			callAddLayout.addTab(callInformation, "Call Information");
		}
	}

	/**
	 * 
	 * @author haiphucnguyen
	 * 
	 */
	public static class PrintView extends CallPreviewBuilder {

		public PrintView() {
			initRelatedComponent();
			previewForm = new AdvancedPreviewBeanForm<SimpleCall>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new CallFormFieldFactory());
					super.setItemDataSource(newDataSource);
				}
			};

			this.addComponent(previewForm);
		}

		class FormLayoutFactory extends CallFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(call.getSubject());
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
