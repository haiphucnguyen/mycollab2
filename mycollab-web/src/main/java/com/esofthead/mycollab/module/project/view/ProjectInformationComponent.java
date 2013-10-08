/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.view.user.ProjectFormViewLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectInformationComponent extends VerticalLayout {
	private class BasicPreviewForm extends
			AdvancedPreviewBeanForm<SimpleProject> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new IFormLayoutFactory() {
				private static final long serialVersionUID = 1L;
				private GridFormLayoutHelper informationLayout;

				@Override
				public void attachField(final Object propertyId,
						final Field field) {
					if (propertyId.equals("homepage")) {
						this.informationLayout.addComponent(field, "Home Page",
								0, 0, Alignment.TOP_LEFT);
					} else if (propertyId.equals("actualstartdate")) {
						this.informationLayout.addComponent(field,
								"Start Date", 1, 0, Alignment.TOP_LEFT);
					} else if (propertyId.equals("description")) {
						this.informationLayout.addComponent(field,
								"Description", 0, 1, 2, "100%",
								Alignment.TOP_LEFT);
					}
				}

				@Override
				public Layout getLayout() {
					this.informationLayout = new GridFormLayoutHelper(2, 3,
							"100%", "167px", Alignment.MIDDLE_LEFT);
					this.informationLayout.getLayout().setWidth("100%");
					this.informationLayout.getLayout().setMargin(false);
					this.informationLayout.getLayout().addStyleName(
							"colored-gridlayout");
					return this.informationLayout.getLayout();
				}
			});

			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(final Item item,
						final Object propertyId, final Component uiContext) {
					if (propertyId.equals("actualstartdate")) {
						return new DefaultFormViewFieldFactory.FormViewField(
								AppContext
										.formatDate(ProjectInformationComponent.this.project
												.getActualstartdate()));
					} else if (propertyId.equals("homepage")) {
						return new FormUrlLinkViewField(
								ProjectInformationComponent.this.project
										.getHomepage());
					} else if (propertyId.equals("description")) {
						return new FormDetectAndDisplayUrlViewField(
								ProjectInformationComponent.this.project
										.getDescription());
					}
					return null;
				}
			});
			super.setItemDataSource(newDataSource);
		}
	}

	private class BasicProjectInformation extends VerticalLayout implements
			ProjectDisplayInformation {
		private static final long serialVersionUID = 1L;
		private final BasicPreviewForm previewForm;

		public BasicProjectInformation() {
			this.previewForm = new BasicPreviewForm();
			this.addComponent(this.previewForm);
		}

		@Override
		public void show() {
			this.previewForm.setItemDataSource(new BeanItem<SimpleProject>(
					ProjectInformationComponent.this.project));
		}
	}

	private class DetailPreviewForm extends
			AdvancedPreviewBeanForm<SimpleProject> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new ProjectFormViewLayoutFactory.ProjectInformationLayout());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(final Item item,
						final Object propertyId, final Component uiContext) {
					if (propertyId.equals("planstartdate")) {
						return new FormViewField(
								AppContext
										.formatDate(ProjectInformationComponent.this.project
												.getPlanstartdate()));
					} else if (propertyId.equals("planenddate")) {
						return new FormViewField(
								AppContext
										.formatDate(ProjectInformationComponent.this.project
												.getPlanenddate()));
					} else if (propertyId.equals("actualstartdate")) {
						return new FormViewField(
								AppContext
										.formatDate(ProjectInformationComponent.this.project
												.getActualstartdate()));
					} else if (propertyId.equals("actualenddate")) {
						return new FormViewField(
								AppContext
										.formatDate(ProjectInformationComponent.this.project
												.getActualenddate()));
					} else if (propertyId.equals("homepage")) {
						return new FormUrlLinkViewField(
								ProjectInformationComponent.this.project
										.getHomepage());
					} else if (propertyId.equals("description")) {
						return new FormDetectAndDisplayUrlViewField(
								ProjectInformationComponent.this.project
										.getDescription());
					} else if (propertyId.equals("currencyid")) {
						if (ProjectInformationComponent.this.project
								.getCurrency() != null) {
							return new FormViewField(
									ProjectInformationComponent.this.project
											.getCurrency().getShortname());
						} else {
							return new FormViewField("");
						}
					}
					return null;
				}
			});
			super.setItemDataSource(newDataSource);
		}
	}

	private class DetailProjectInformation extends VerticalLayout implements
			ProjectDisplayInformation {
		private static final long serialVersionUID = 1L;
		private final DetailPreviewForm previewForm;

		public DetailProjectInformation() {
			this.previewForm = new DetailPreviewForm();
			this.addComponent(this.previewForm);
		}

		@Override
		public void show() {
			this.previewForm.setItemDataSource(new BeanItem<SimpleProject>(
					ProjectInformationComponent.this.project));
		}
	}

	private interface ProjectDisplayInformation extends Component {

		void show();
	}

	private static final long serialVersionUID = 1L;

	private SimpleProject project;

	private ProjectDisplayInformation prjDisplay;

	private final HorizontalLayout projectInfoHeader;

	private final HorizontalLayout projectInfoFooter;

	public ProjectInformationComponent() {
		this.setStyleName(UIConstants.PROJECT_INFO);
		this.prjDisplay = new BasicProjectInformation();
		this.projectInfoHeader = new HorizontalLayout();
		this.projectInfoHeader.setWidth("100%");
		this.projectInfoHeader.setStyleName(UIConstants.PROJECT_INFO_HEADER);
		this.addComponent(this.projectInfoHeader);
		this.addComponent(this.prjDisplay);

		this.projectInfoFooter = new HorizontalLayout();
		this.projectInfoFooter.setStyleName(UIConstants.PROJECT_INFO_FOOTER);
		final Button toggleBtn = new Button("More");
		toggleBtn.addListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				final int replaceIndex = ProjectInformationComponent.this
						.getComponentIndex(ProjectInformationComponent.this.prjDisplay);
				ProjectInformationComponent.this
						.removeComponent(ProjectInformationComponent.this.prjDisplay);
				if (ProjectInformationComponent.this.prjDisplay instanceof BasicProjectInformation) {
					ProjectInformationComponent.this.prjDisplay = new DetailProjectInformation();
					event.getButton().setCaption("Less");
				} else {
					ProjectInformationComponent.this.prjDisplay = new BasicProjectInformation();
					event.getButton().setCaption("More");
				}
				ProjectInformationComponent.this.addComponent(
						ProjectInformationComponent.this.prjDisplay,
						replaceIndex);
				ProjectInformationComponent.this.prjDisplay.show();
			}
		});
		toggleBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		this.projectInfoFooter.addComponent(toggleBtn);
		this.addComponent(this.projectInfoFooter);
	}

	public void displayProjectInformation() {
		this.project = CurrentProjectVariables.getProject();

		this.projectInfoHeader.removeAllComponents();
		this.projectInfoHeader.setSpacing(true);
		final Embedded icon = new Embedded();
		icon.setSource(MyCollabResource
				.newResource("icons/24/project/dashboard.png"));
		final Label projectName = new Label(this.project.getName());
		projectName.setStyleName(UIConstants.PROJECT_NAME);
		projectName.setSizeUndefined();
		final Label projectShortname = new Label("("
				+ this.project.getShortname() + ")");
		projectShortname.setStyleName(UIConstants.PROJECT_SHORT_NAME);
		this.projectInfoHeader.addComponent(icon);
		this.projectInfoHeader.addComponent(projectName);
		this.projectInfoHeader.setComponentAlignment(projectName,
				Alignment.MIDDLE_LEFT);
		this.projectInfoHeader.addComponent(projectShortname);
		this.projectInfoHeader.setExpandRatio(projectShortname, 1.0f);
		this.projectInfoHeader.setComponentAlignment(projectShortname,
				Alignment.MIDDLE_LEFT);

		this.prjDisplay.show();
	}
}
