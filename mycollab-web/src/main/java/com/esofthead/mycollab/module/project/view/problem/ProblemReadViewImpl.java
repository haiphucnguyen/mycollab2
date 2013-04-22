package com.esofthead.mycollab.module.project.view.problem;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.ui.components.CommentListDepot;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Window;

@ViewComponent
public class ProblemReadViewImpl extends AbstractView implements
		ProblemReadView {

	private static final long serialVersionUID = 1L;
	protected SimpleProblem problem;
	protected AdvancedPreviewBeanForm<Problem> previewForm;

	public ProblemReadViewImpl() {
		super();
		this.setMargin(false, true, true, true);
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleProblem item) {
		problem = item;
		previewForm.setItemDataSource(new BeanItem<Problem>(item));
	}

	@Override
	public SimpleProblem getItem() {
		return problem;
	}

	@Override
	public HasPreviewFormHandlers<Problem> getPreviewFormHandlers() {
		return previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<Problem> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(Item item, Object propertyId,
						Component uiContext) {

					if (propertyId.equals("raisedbyuser")) {
						return new ProjectUserFormLinkField(problem
								.getRaisedbyuser(), problem
								.getRaisedByUserFullName());
					} else if (propertyId.equals("level")) {
						RatingStars tinyRs = new RatingStars();
						tinyRs.setValue(problem.getLevel());
						tinyRs.setReadOnly(true);
						return tinyRs;
					} else if (propertyId.equals("datedue")) {
						return new FormViewField(AppContext.formatDate(problem
								.getDatedue()));
					} else if (propertyId.equals("assigntouser")) {
						return new ProjectUserFormLinkField(problem
								.getAssigntouser(), problem
								.getAssignedUserFullName());
					} else if (propertyId.equals("description")) {
						return new FormViewField(problem.getDescription(),
								Label.CONTENT_XHTML);
					}

					return null;
				}
			});
			super.setItemDataSource(newDataSource);
		}

		@Override
		protected void doPrint() {
			// Create a window that contains what you want to print
			Window window = new Window("Window to Print");

			ProblemReadViewImpl printView = new ProblemReadViewImpl.PrintView();
			printView.previewItem(problem);
			window.addComponent(printView);

			// Add the printing window as a new application-level window
			getApplication().addWindow(window);

			// Open it as a popup window with no decorations
			getWindow().open(new ExternalResource(window.getURL()), "_blank",
					1100, 200, // Width and height
					Window.BORDER_NONE); // No decorations

			// Print automatically when the window opens.
			// This call will block until the print dialog exits!
			window.executeJavaScript("print();");

			// Close the window automatically after printing
			window.executeJavaScript("self.close();");
		}

		@Override
		protected void showHistory() {
			ProblemHistoryLogWindow historyLog = new ProblemHistoryLogWindow(
					ModuleNameConstants.PRJ, ProjectContants.PROBLEM,
					problem.getId());
			getWindow().addWindow(historyLog);
		}

		class FormLayoutFactory extends ProblemFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(problem.getIssuename());
			}

			@Override
			protected Layout createTopPanel() {
				return (new ProjectPreviewFormControlsGenerator<Problem>(
						PreviewForm.this)).createButtonControls(
						ProjectRolePermissionCollections.PROBLEMS);
			}

			@Override
			protected Layout createBottomPanel() {
				return new CommentListDepot(CommentTypeConstants.PRJ_PROBLEM,
						problem.getId(), true, false);
			}
		}
	}

	@SuppressWarnings("serial")
	public static class PrintView extends ProblemReadViewImpl {

		public PrintView() {
			previewForm = new AdvancedPreviewBeanForm<Problem>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					this.setFormLayoutFactory(new ProblemReadViewImpl.PrintView.FormLayoutFactory());
					this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
						private static final long serialVersionUID = 1L;

						@Override
						protected Field onCreateField(Item item,
								Object propertyId, Component uiContext) {

							if (propertyId.equals("raisedbyuser")) {
								return new ProjectUserFormLinkField(problem
										.getRaisedbyuser(), problem
										.getRaisedByUserFullName());
							} else if (propertyId.equals("assigntouser")) {
								return new ProjectUserFormLinkField(problem
										.getAssigntouser(), problem
										.getAssignedUserFullName());
							} else if (propertyId.equals("level")) {
								RatingStars tinyRs = new RatingStars();
								tinyRs.setValue(problem.getLevel());
								tinyRs.setReadOnly(true);
								return tinyRs;
							} else if (propertyId.equals("datedue")) {
								return new FormViewField(AppContext
										.formatDate(problem.getDatedue()));
							} else if (propertyId.equals("description")) {
								return new FormViewField(problem
										.getDescription(), Label.CONTENT_XHTML);
							} else if (propertyId.equals("resolution")) {
								return new FormViewField(problem
										.getResolution(), Label.CONTENT_XHTML);
							}

							return null;
						}
					});
					super.setItemDataSource(newDataSource);
				}
			};

			this.addComponent(previewForm);
		}

		class FormLayoutFactory extends ProblemFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(problem.getIssuename());
			}

			@Override
			protected Layout createTopPanel() {
				return new HorizontalLayout();
			}

			@Override
			protected Layout createBottomPanel() {
				return new CommentListDepot(CommentTypeConstants.PRJ_PROBLEM,
						problem.getId(), false, false);
			}
		}
	}
}
