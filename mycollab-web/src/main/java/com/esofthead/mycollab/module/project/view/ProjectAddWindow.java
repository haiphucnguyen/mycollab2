package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class ProjectAddWindow extends Window {
	private static final long serialVersionUID = 1L;

	private Project project;
	private EditForm editForm;

	public ProjectAddWindow() {
		this.setWidth("900px");
		center();

		project = new Project();
		editForm = new EditForm();
		this.addComponent(editForm);
		editForm.setItemDataSource(new BeanItem<Project>(project));
	}

	private class EditForm extends AdvancedEditBeanForm<Project> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory implements IFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			private GridFormLayoutHelper informationLayout;

			@Override
			public Layout getLayout() {
				AddViewLayout projectAddLayout = new AddViewLayout(
						"New Project",
						MyCollabResource
								.newResource("icons/48/project/project.png"));
				informationLayout = new GridFormLayoutHelper(2, 4);

				projectAddLayout.addBody(informationLayout.getLayout());

				HorizontalLayout buttonControls = new HorizontalLayout();
				buttonControls.setSpacing(true);
				buttonControls.setMargin(true);
				buttonControls.setStyleName("addNewControl");

				Button saveBtn = new Button(
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_SAVE_LABEL),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								ProjectService projectService = AppContext
										.getSpringBean(ProjectService.class);
								project.setSaccountid(AppContext.getAccountId());
								projectService.saveWithSession(project,
										AppContext.getUsername());

								EventBus.getInstance()
										.fireEvent(
												new ProjectEvent.GotoMyProject(
														this,
														new PageActionChain(
																new ProjectScreenData.Goto(
																		project.getId()))));
								ProjectAddWindow.this.close();
							}

						});
				saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				buttonControls.addComponent(saveBtn);
				buttonControls.setComponentAlignment(saveBtn,
						Alignment.MIDDLE_CENTER);

				Button closeBtn = new Button(
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_CLOSE_LABEL),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								ProjectAddWindow.this.close();
							}

						});
				closeBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				buttonControls.addComponent(closeBtn);
				buttonControls.setComponentAlignment(closeBtn,
						Alignment.MIDDLE_CENTER);

				projectAddLayout.addBottomControls(buttonControls);
				return projectAddLayout;
			}

			@Override
			public void attachField(Object propertyId, Field field) {
				if (propertyId.equals("name")) {
					informationLayout.addComponent(field, "Project Name", 0, 0);
				} else if (propertyId.equals("homepage")) {
					informationLayout.addComponent(field, "Home Page", 1, 0);
				} else if (propertyId.equals("shortname")) {
					informationLayout.addComponent(field, "Short Name", 0, 1);
				} else if (propertyId.equals("projectstatus")) {
					informationLayout.addComponent(field, "Status", 1, 1);
				} else if (propertyId.equals("planstartdate")) {
					informationLayout.addComponent(field, "Plan Start Date", 0,
							2);
				} else if (propertyId.equals("planenddate")) {
					informationLayout
							.addComponent(field, "Plan End Date", 1, 2);
				} else if (propertyId.equals("description")) {
					informationLayout.addComponent(field, "Description", 0, 3,
							2, UIConstants.DEFAULT_2XCONTROL_WIDTH);
				}

			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {
				if (propertyId.equals("description")) {
					RichTextArea field = new RichTextArea();
					field.setHeight("350px");
					return field;
				} else if (propertyId.equals("projectstatus")) {
					ProjectStatusComboBox projectCombo = new ProjectStatusComboBox();
					projectCombo.setRequired(true);
					projectCombo
							.setRequiredError("Please enter a project status");
					if (project.getProjectstatus() == null) {
						project.setProjectstatus("Open");
					}
					return projectCombo;
				} else if (propertyId.equals("shortname")) {
					TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter a project short name");
					return tf;
				} else if (propertyId.equals("name")) {
					TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter a Name");
					return tf;
				}

				return null;
			}
		}
	}

	private static class ProjectStatusComboBox extends ValueComboBox {
		private static final long serialVersionUID = 1L;

		public ProjectStatusComboBox() {
			super(false, ProjectDataTypeFactory.getProjectStatusList());
		}
	}
}
