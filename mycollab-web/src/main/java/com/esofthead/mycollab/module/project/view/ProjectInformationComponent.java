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
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
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
			setFormLayoutFactory(new IFormLayoutFactory() {
				private static final long serialVersionUID = 1L;
				private GridFormLayoutHelper informationLayout;

				@Override
				public void attachField(final Object propertyId,
						final Field field) {
					if (propertyId.equals("homepage")) {
						informationLayout.addComponent(field, "Home Page", 0,
								0, Alignment.TOP_LEFT);
					} else if (propertyId.equals("actualstartdate")) {
						informationLayout.addComponent(field, "Start Date", 1,
								0, Alignment.TOP_LEFT);
					} else if (propertyId.equals("description")) {
						informationLayout.addComponent(field, "Description", 0,
								1, 2, "100%", Alignment.TOP_LEFT);
					}
				}

				@Override
				public Layout getLayout() {
					informationLayout = new GridFormLayoutHelper(2, 3, "100%",
							"167px", Alignment.MIDDLE_LEFT);
					informationLayout.getLayout().setWidth("100%");
					informationLayout.getLayout().setMargin(false);
					informationLayout.getLayout().addStyleName(
							"colored-gridlayout");
					return informationLayout.getLayout();
				}
			});

			setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(final Item item,
						final Object propertyId, final Component uiContext) {
					if (propertyId.equals("actualstartdate")) {
						return new DefaultFormViewFieldFactory.FormViewField(
								AppContext.formatDate(project
										.getActualstartdate()));
					} else if (propertyId.equals("homepage")) {
						return new FormUrlLinkViewField(project.getHomepage());
					} else if (propertyId.equals("description")) {
						return new FormViewField(project.getDescription(),
								Label.CONTENT_XHTML);
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
			previewForm = new BasicPreviewForm();
			this.addComponent(previewForm);

			// final Button moreBtn = new Button("More",
			// new Button.ClickListener() {
			// private static final long serialVersionUID = 1L;
			//
			// @Override
			// public void buttonClick(final ClickEvent event) {
			// ProjectInformationComponent.this
			// .removeComponent(BasicProjectInformation.this);
			// prjDisplay = new DetailProjectInformation();
			// ProjectInformationComponent.this
			// .addComponent(prjDisplay);
			// prjDisplay.show();
			// }
			// });
			// moreBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			// this.addComponent(moreBtn);
		}

		@Override
		public void show() {
			previewForm.setItemDataSource(new BeanItem<SimpleProject>(project));
		}
	}

	private class DetailPreviewForm extends
			AdvancedPreviewBeanForm<SimpleProject> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			setFormLayoutFactory(new ProjectFormViewLayoutFactory.ProjectInformationLayout());
			setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(final Item item,
						final Object propertyId, final Component uiContext) {
					if (propertyId.equals("planstartdate")) {
						return new FormViewField(AppContext.formatDate(project
								.getPlanstartdate()));
					} else if (propertyId.equals("planenddate")) {
						return new FormViewField(AppContext.formatDate(project
								.getPlanenddate()));
					} else if (propertyId.equals("actualstartdate")) {
						return new FormViewField(AppContext.formatDate(project
								.getActualstartdate()));
					} else if (propertyId.equals("actualenddate")) {
						return new FormViewField(AppContext.formatDate(project
								.getActualenddate()));
					} else if (propertyId.equals("homepage")) {
						return new FormUrlLinkViewField(project.getHomepage());
					} else if (propertyId.equals("description")) {
						return new FormViewField(project.getDescription(),
								Label.CONTENT_XHTML);
					} else if (propertyId.equals("currencyid")) {
						if (project.getCurrency() != null) {
							return new FormViewField(project.getCurrency()
									.getName());
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
			previewForm = new DetailPreviewForm();
			this.addComponent(previewForm);
		}

		@Override
		public void show() {
			previewForm.setItemDataSource(new BeanItem<SimpleProject>(project));
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
		setStyleName(UIConstants.PROJECT_INFO);
		prjDisplay = new BasicProjectInformation();
		projectInfoHeader = new HorizontalLayout();
		projectInfoHeader.setWidth("100%");
		projectInfoHeader.setStyleName(UIConstants.PROJECT_INFO_HEADER);
		this.addComponent(projectInfoHeader);
		this.addComponent(prjDisplay);

		projectInfoFooter = new HorizontalLayout();
		projectInfoFooter.setStyleName(UIConstants.PROJECT_INFO_FOOTER);
		final Button toggleBtn = new Button("More");
		toggleBtn.addListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				final int replaceIndex = ProjectInformationComponent.this
						.getComponentIndex(prjDisplay);
				ProjectInformationComponent.this.removeComponent(prjDisplay);
				if (prjDisplay instanceof BasicProjectInformation) {
					prjDisplay = new DetailProjectInformation();
					event.getButton().setCaption("Less");
				} else {
					prjDisplay = new BasicProjectInformation();
					event.getButton().setCaption("More");
				}
				ProjectInformationComponent.this.addComponent(prjDisplay,
						replaceIndex);
				prjDisplay.show();
			}
		});
		toggleBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		projectInfoFooter.addComponent(toggleBtn);
		this.addComponent(projectInfoFooter);
	}

	public void displayProjectInformation() {
		project = CurrentProjectVariables.getProject();

		projectInfoHeader.removeAllComponents();
		final Label projectName = new Label(project.getName());
		projectName.setStyleName(UIConstants.PROJECT_NAME);
		projectName.setSizeUndefined();
		final Label projectShortname = new Label("(" + project.getShortname()
				+ ")");
		projectShortname.setStyleName(UIConstants.PROJECT_SHORT_NAME);
		projectInfoHeader.addComponent(projectName);
		projectInfoHeader.addComponent(projectShortname);
		projectInfoHeader.setExpandRatio(projectShortname, 1.0f);
		projectInfoHeader.setComponentAlignment(projectShortname,
				Alignment.TOP_LEFT);

		prjDisplay.show();
	}
}
