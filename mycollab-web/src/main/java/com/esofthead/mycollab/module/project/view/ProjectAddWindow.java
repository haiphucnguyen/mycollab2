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
package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.ProjectDataTypeFactory;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.web.AppContext;
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
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ProjectAddWindow extends Window {
	private static final long serialVersionUID = 1L;

	private final Project project;
	private final EditForm editForm;

	public ProjectAddWindow() {
		this.setWidth("900px");
		this.center();

		this.project = new Project();
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
		this.setCaption("New Project");
		this.editForm.setItemDataSource(new BeanItem<Project>(this.project));
		((VerticalLayout) this.getContent()).setMargin(false, false, true,
				false);
	}

	private class EditForm extends AdvancedEditBeanForm<Project> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory implements IFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			private GridFormLayoutHelper informationLayout;

			@Override
			public Layout getLayout() {

				final VerticalLayout projectAddLayout = new VerticalLayout();

				this.informationLayout = new GridFormLayoutHelper(2, 4, "100%",
						"167px", Alignment.MIDDLE_LEFT);
				this.informationLayout.getLayout().setWidth("100%");
				this.informationLayout.getLayout().setMargin(false);
				this.informationLayout.getLayout().setSpacing(false);
				this.informationLayout.getLayout().addStyleName(
						"colored-gridlayout");

				projectAddLayout.addComponent(this.informationLayout
						.getLayout());

				final HorizontalLayout buttonControls = new HorizontalLayout();
				buttonControls.setSpacing(true);
				buttonControls.setMargin(true);
				buttonControls.setStyleName("addNewControl");

				final Button saveBtn = new Button(
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_SAVE_LABEL),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(final ClickEvent event) {
								final ProjectService projectService = ApplicationContextUtil
										.getSpringBean(ProjectService.class);
								ProjectAddWindow.this.project
										.setSaccountid(AppContext
												.getAccountId());
								projectService.saveWithSession(
										ProjectAddWindow.this.project,
										AppContext.getUsername());

								EventBus.getInstance()
										.fireEvent(
												new ProjectEvent.GotoMyProject(
														this,
														new PageActionChain(
																new ProjectScreenData.Goto(
																		ProjectAddWindow.this.project
																				.getId()))));
								ProjectAddWindow.this.close();
							}

						});
				saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				buttonControls.addComponent(saveBtn);
				buttonControls.setComponentAlignment(saveBtn,
						Alignment.MIDDLE_CENTER);

				final Button closeBtn = new Button(
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_CLOSE_LABEL),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(final ClickEvent event) {
								ProjectAddWindow.this.close();
							}

						});
				closeBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				buttonControls.addComponent(closeBtn);
				buttonControls.setComponentAlignment(closeBtn,
						Alignment.MIDDLE_CENTER);

				projectAddLayout.addComponent(buttonControls);
				projectAddLayout.setComponentAlignment(buttonControls,
						Alignment.MIDDLE_CENTER);
				return projectAddLayout;
			}

			@Override
			public void attachField(final Object propertyId, final Field field) {
				if (propertyId.equals("name")) {
					this.informationLayout.addComponent(field, "Project Name",
							0, 0);
				} else if (propertyId.equals("homepage")) {
					this.informationLayout.addComponent(field, "Home Page", 1,
							0);
				} else if (propertyId.equals("shortname")) {
					this.informationLayout.addComponent(field, "Short Name", 0,
							1);
				} else if (propertyId.equals("projectstatus")) {
					this.informationLayout.addComponent(field, "Status", 1, 1);
				} else if (propertyId.equals("planstartdate")) {
					this.informationLayout.addComponent(field,
							"Plan Start Date", 0, 2);
				} else if (propertyId.equals("planenddate")) {
					this.informationLayout.addComponent(field, "Plan End Date",
							1, 2);
				} else if (propertyId.equals("description")) {
					this.informationLayout.addComponent(field, "Description",
							0, 3, 2, "100%", Alignment.MIDDLE_LEFT);
				}

			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {
				if (propertyId.equals("description")) {
					final RichTextArea field = new RichTextArea();
					field.setHeight("350px");
					return field;
				} else if (propertyId.equals("projectstatus")) {
					final ProjectStatusComboBox projectCombo = new ProjectStatusComboBox();
					projectCombo.setRequired(true);
					projectCombo
							.setRequiredError("Please enter a project status");
					if (ProjectAddWindow.this.project.getProjectstatus() == null) {
						ProjectAddWindow.this.project.setProjectstatus("Open");
					}
					return projectCombo;
				} else if (propertyId.equals("shortname")) {
					final TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter a project short name");
					return tf;
				} else if (propertyId.equals("name")) {
					final TextField tf = new TextField();
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
