package com.esofthead.mycollab.module.project.view.bug;

import java.util.List;

import com.esofthead.mycollab.module.file.AttachmentConstants;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneComboBox;
import com.esofthead.mycollab.module.project.view.people.component.ProjectMemberComboBox;
import com.esofthead.mycollab.module.project.view.people.component.ProjectMemberMultiSelectField;
import com.esofthead.mycollab.module.tracker.domain.Bug;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.AttachmentUploadField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormAttachmentUploadField;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MaskedTextField;
import com.vaadin.ui.NumericField;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

@ViewComponent
public class BugAddViewImpl extends AbstractView implements BugAddView {

	private static final long serialVersionUID = 1L;

	private final EditForm editForm;
	private SimpleBug bug;
	private FormAttachmentUploadField attachmentUploadField;

	private ComponentMultiSelectField componentSelect;
	private VersionMultiSelectField affectedVersionSelect;
	private VersionMultiSelectField fixedVersionSelect;
	private ProjectMemberMultiSelectField projectMemberSelect;

	public BugAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void editItem(SimpleBug item) {
		this.bug = item;
		editForm.setItemDataSource(new BeanItem<Bug>(item));

	}

	@Override
	public AttachmentUploadField getAttachUploadField() {
		return attachmentUploadField;
	}

	@Override
	public List<Component> getComponents() {
		return componentSelect.getSelectedItems();
	}

	@Override
	public List<Version> getAffectedVersions() {
		return affectedVersionSelect.getSelectedItems();
	}

	@Override
	public List<Version> getFixedVersion() {
		return fixedVersionSelect.getSelectedItems();
	}

	private class EditForm extends AdvancedEditBeanForm<SimpleBug> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends BugAddFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super((bug.getId() == null) ? "Create Bug" : bug.getSummary());
			}

			private Layout createButtonControls() {
				return (new EditFormControlsGenerator<SimpleBug>(EditForm.this))
						.createButtonControls();
			}

			@Override
			protected Layout createTopPanel() {
				return createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return createButtonControls();
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {

				if (propertyId.equals("environment")) {
					RichTextArea field = new RichTextArea("", "");
					field.setNullRepresentation("");
					return field;
				} else if (propertyId.equals("description")) {
					RichTextArea field = new RichTextArea("", "");
					field.setNullRepresentation("");
					return field;
				} else if (propertyId.equals("priority")) {
					if (bug.getPriority() == null) {
						bug.setPriority(BugPriorityComboBox.PRIORITY_MAJOR);
					}
					return new BugPriorityComboBox();
				} else if (propertyId.equals("assignuser")) {
					return new ProjectMemberComboBox();
				} else if (propertyId.equals("id")) {
					attachmentUploadField = new FormAttachmentUploadField();
					if (bug.getId() != null) {
						attachmentUploadField.getAttachments(
								AttachmentConstants.PROJECT_BUG_TYPE,
								bug.getId());
					}
					return attachmentUploadField;
				} else if (propertyId.equals("severity")) {
					if (bug.getSeverity() == null) {
						bug.setSeverity("Major");
					}
					return new BugSeverityComboBox();
				} else if (propertyId.equals("components")) {
					componentSelect = new ComponentMultiSelectField();
					if (bug.getComponents() != null
							&& bug.getComponents().size() > 0) {
						componentSelect.setSelectedItems(bug.getComponents());
					}
					return componentSelect;
				} else if (propertyId.equals("affectedVersions")) {
					affectedVersionSelect = new VersionMultiSelectField();
					if (bug.getAffectedVersions() != null
							&& bug.getAffectedVersions().size() > 0) {
						affectedVersionSelect.setSelectedItems(bug
								.getAffectedVersions());
					}
					return affectedVersionSelect;
				} else if (propertyId.equals("fixedVersions")) {
					fixedVersionSelect = new VersionMultiSelectField();
					if (bug.getFixedVersions() != null
							&& bug.getFixedVersions().size() > 0) {
						fixedVersionSelect.setSelectedItems(bug
								.getFixedVersions());
					}
					return fixedVersionSelect;
				} else if (propertyId.equals("summary")) {
					TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter a Summary");
					return tf;
				} else if (propertyId.equals("milestoneid")) {
					return new MilestoneComboBox();
				} else if (propertyId.equals("estimatetime") || (propertyId.equals("estimateremaintime"))) {
					return new NumericField();
				} 

				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<SimpleBug> getEditFormHandlers() {
		return editForm;
	}
}
