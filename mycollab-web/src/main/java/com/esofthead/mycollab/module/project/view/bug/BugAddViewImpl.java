package com.esofthead.mycollab.module.project.view.bug;

import java.util.List;

import com.esofthead.mycollab.module.file.AttachmentType;
import com.esofthead.mycollab.module.project.ui.components.DefaultProjectFormViewFieldFactory.ProjectFormAttachmentUploadField;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneComboBox;
import com.esofthead.mycollab.module.project.view.people.component.ProjectMemberComboBox;
import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

@ViewComponent
public class BugAddViewImpl extends AbstractView implements BugAddView {

	private static final long serialVersionUID = 1L;

	private final EditForm editForm;
	private SimpleBug bug;
	private ProjectFormAttachmentUploadField attachmentUploadField;

	private ComponentMultiSelectField componentSelect;
	private VersionMultiSelectField affectedVersionSelect;
	private VersionMultiSelectField fixedVersionSelect;

	public BugAddViewImpl() {
		super();
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
		this.setMargin(true);
	}

	@Override
	public void editItem(final SimpleBug item) {
		this.bug = item;
		this.editForm.setItemDataSource(new BeanItem<BugWithBLOBs>(item));

	}

	@Override
	public ProjectFormAttachmentUploadField getAttachUploadField() {
		return this.attachmentUploadField;
	}

	@Override
	public List<Component> getComponents() {
		return this.componentSelect.getSelectedItems();
	}

	@Override
	public List<Version> getAffectedVersions() {
		return this.affectedVersionSelect.getSelectedItems();
	}

	@Override
	public List<Version> getFixedVersion() {
		return this.fixedVersionSelect.getSelectedItems();
	}

	private class EditForm extends AdvancedEditBeanForm<SimpleBug> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends BugAddFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super((BugAddViewImpl.this.bug.getId() == null) ? "Create Bug"
						: BugAddViewImpl.this.bug.getSummary());
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<SimpleBug>(
						EditForm.this)).createButtonControls();
				controlButtons.setSizeUndefined();
				controlPanel.addComponent(controlButtons);
				controlPanel.setWidth("100%");
				controlPanel.setComponentAlignment(controlButtons,
						Alignment.MIDDLE_CENTER);
				return controlPanel;
			}

			@Override
			protected Layout createTopPanel() {
				return this.createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return this.createButtonControls();
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {

				if (propertyId.equals("environment")) {
					final RichTextArea field = new RichTextArea("", "");
					field.setNullRepresentation("");
					return field;
				} else if (propertyId.equals("description")) {
					final RichTextArea field = new RichTextArea("", "");
					field.setNullRepresentation("");
					return field;
				} else if (propertyId.equals("priority")) {
					if (BugAddViewImpl.this.bug.getPriority() == null) {
						BugAddViewImpl.this.bug
								.setPriority(BugPriorityStatusConstants.PRIORITY_MAJOR);
					}
					return new BugPriorityComboBox();
				} else if (propertyId.equals("assignuser")) {
					return new ProjectMemberComboBox();
				} else if (propertyId.equals("id")) {
					BugAddViewImpl.this.attachmentUploadField = new ProjectFormAttachmentUploadField();
					if (BugAddViewImpl.this.bug.getId() != null) {
						BugAddViewImpl.this.attachmentUploadField
								.getAttachments(bug.getProjectid(),
										AttachmentType.PROJECT_BUG_TYPE,
										BugAddViewImpl.this.bug.getId());
					}
					return BugAddViewImpl.this.attachmentUploadField;
				} else if (propertyId.equals("severity")) {
					if (BugAddViewImpl.this.bug.getSeverity() == null) {
						BugAddViewImpl.this.bug
								.setSeverity(BugSeverityConstants.MAJOR);
					}
					return new BugSeverityComboBox();
				} else if (propertyId.equals("components")) {
					BugAddViewImpl.this.componentSelect = new ComponentMultiSelectField(
							"100%");
					if (BugAddViewImpl.this.bug.getComponents() != null
							&& BugAddViewImpl.this.bug.getComponents().size() > 0) {
						BugAddViewImpl.this.componentSelect
								.setSelectedItems(BugAddViewImpl.this.bug
										.getComponents());
					}
					return BugAddViewImpl.this.componentSelect;
				} else if (propertyId.equals("affectedVersions")) {
					BugAddViewImpl.this.affectedVersionSelect = new VersionMultiSelectField(
							"100%");
					if (BugAddViewImpl.this.bug.getAffectedVersions() != null
							&& BugAddViewImpl.this.bug.getAffectedVersions()
									.size() > 0) {
						BugAddViewImpl.this.affectedVersionSelect
								.setSelectedItems(BugAddViewImpl.this.bug
										.getAffectedVersions());
					}
					return BugAddViewImpl.this.affectedVersionSelect;
				} else if (propertyId.equals("fixedVersions")) {
					BugAddViewImpl.this.fixedVersionSelect = new VersionMultiSelectField(
							"100%");
					if (BugAddViewImpl.this.bug.getFixedVersions() != null
							&& BugAddViewImpl.this.bug.getFixedVersions()
									.size() > 0) {
						BugAddViewImpl.this.fixedVersionSelect
								.setSelectedItems(BugAddViewImpl.this.bug
										.getFixedVersions());
					}
					return BugAddViewImpl.this.fixedVersionSelect;
				} else if (propertyId.equals("summary")) {
					final TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter a Summary");
					return tf;
				} else if (propertyId.equals("milestoneid")) {
					final MilestoneComboBox milestoneBox = new MilestoneComboBox();
					milestoneBox
							.addListener(new Property.ValueChangeListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void valueChange(
										Property.ValueChangeEvent event) {
									String milestoneName = milestoneBox
											.getItemCaption(milestoneBox
													.getValue());
									bug.setMilestoneName(milestoneName);
								}
							});
					return milestoneBox;
				} else if (propertyId.equals("estimatetime")
						|| (propertyId.equals("estimateremaintime"))) {
					return new NumbericTextField();
				}

				return null;
			}
		}
	}

	private static class NumbericTextField extends TextField {
		private static final long serialVersionUID = 1L;

		@Override
		protected void setValue(final Object newValue,
				final boolean repaintIsNotNeeded) {
			try {
				final double d = Double.parseDouble((String) newValue);
				super.setValue(d, repaintIsNotNeeded);
			} catch (final Exception e) {
				super.setValue(0, repaintIsNotNeeded);
			}
		}
	}

	@Override
	public HasEditFormHandlers<SimpleBug> getEditFormHandlers() {
		return this.editForm;
	}
}
