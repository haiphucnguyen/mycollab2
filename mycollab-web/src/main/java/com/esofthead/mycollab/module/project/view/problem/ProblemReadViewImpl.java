package com.esofthead.mycollab.module.project.view.problem;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.ui.components.CommentListDepot;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.schedule.email.project.ProjectProblemRelayEmailNotificationAction;
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
		this.setMargin(true);
		this.previewForm = new PreviewForm();
		this.addComponent(this.previewForm);
	}

	@Override
	public void previewItem(final SimpleProblem item) {
		this.problem = item;
		this.previewForm.setItemDataSource(new BeanItem<Problem>(item));
	}

	@Override
	public SimpleProblem getItem() {
		return this.problem;
	}

	@Override
	public HasPreviewFormHandlers<Problem> getPreviewFormHandlers() {
		return this.previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<Problem> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(final Item item,
						final Object propertyId, final Component uiContext) {

					if (propertyId.equals("raisedbyuser")) {
						return new ProjectUserFormLinkField(
								ProblemReadViewImpl.this.problem
										.getRaisedbyuser(),
								ProblemReadViewImpl.this.problem
										.getRaisedByUserAvatarId(),
								ProblemReadViewImpl.this.problem
										.getRaisedByUserFullName());
					} else if (propertyId.equals("level")) {
						final RatingStars tinyRs = new RatingStars();
						tinyRs.setValue(ProblemReadViewImpl.this.problem
								.getLevel());
						tinyRs.setReadOnly(true);
						return tinyRs;
					} else if (propertyId.equals("datedue")) {
						return new FormViewField(AppContext
								.formatDate(ProblemReadViewImpl.this.problem
										.getDatedue()));
					} else if (propertyId.equals("assigntouser")) {
						return new ProjectUserFormLinkField(
								ProblemReadViewImpl.this.problem
										.getAssigntouser(),
								ProblemReadViewImpl.this.problem
										.getAssignUserAvatarId(),
								ProblemReadViewImpl.this.problem
										.getAssignedUserFullName());
					} else if (propertyId.equals("description")) {
						return new FormDetectAndDisplayUrlViewField(
								ProblemReadViewImpl.this.problem
										.getDescription());
					}

					return null;
				}
			});
			super.setItemDataSource(newDataSource);
		}

		@Override
		protected void doPrint() {
			// Create a window that contains what you want to print
			final Window window = new Window("Window to Print");

			final ProblemReadViewImpl printView = new ProblemReadViewImpl.PrintView();
			printView.previewItem(ProblemReadViewImpl.this.problem);
			window.addComponent(printView);

			// Add the printing window as a new application-level window
			this.getApplication().addWindow(window);

			// Open it as a popup window with no decorations
			this.getWindow().open(new ExternalResource(window.getURL()),
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
			final ProblemHistoryLogWindow historyLog = new ProblemHistoryLogWindow(
					ModuleNameConstants.PRJ, ProjectContants.PROBLEM,
					ProblemReadViewImpl.this.problem.getId());
			this.getWindow().addWindow(historyLog);
		}

		class FormLayoutFactory extends ProblemFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(ProblemReadViewImpl.this.problem.getIssuename());
			}

			@Override
			protected Layout createTopPanel() {
				return (new ProjectPreviewFormControlsGenerator<Problem>(
						PreviewForm.this))
						.createButtonControls(ProjectRolePermissionCollections.PROBLEMS);
			}

			@Override
			protected Layout createBottomPanel() {
				final CommentListDepot commentList = new CommentListDepot(
						CommentType.PRJ_PROBLEM,
						ProblemReadViewImpl.this.problem.getId(),
						CurrentProjectVariables.getProjectId(), true, true,
						ProjectProblemRelayEmailNotificationAction.class);
				commentList.setWidth("100%");
				return commentList;
			}
		}
	}

	@SuppressWarnings("serial")
	public static class PrintView extends ProblemReadViewImpl {

		public PrintView() {
			this.previewForm = new AdvancedPreviewBeanForm<Problem>() {
				@Override
				public void setItemDataSource(final Item newDataSource) {
					this.setFormLayoutFactory(new ProblemReadViewImpl.PrintView.FormLayoutFactory());
					this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
						private static final long serialVersionUID = 1L;

						@Override
						protected Field onCreateField(final Item item,
								final Object propertyId,
								final Component uiContext) {

							if (propertyId.equals("raisedbyuser")) {
								return new ProjectUserFormLinkField(
										PrintView.this.problem
												.getRaisedbyuser(),
										PrintView.this.problem
												.getRaisedByUserAvatarId(),
										PrintView.this.problem
												.getRaisedByUserFullName());
							} else if (propertyId.equals("assigntouser")) {
								return new ProjectUserFormLinkField(
										PrintView.this.problem
												.getAssigntouser(),
										PrintView.this.problem
												.getAssignUserAvatarId(),
										PrintView.this.problem
												.getAssignedUserFullName());
							} else if (propertyId.equals("level")) {
								final RatingStars tinyRs = new RatingStars();
								tinyRs.setValue(PrintView.this.problem
										.getLevel());
								tinyRs.setReadOnly(true);
								return tinyRs;
							} else if (propertyId.equals("datedue")) {
								return new FormViewField(AppContext
										.formatDate(PrintView.this.problem
												.getDatedue()));
							} else if (propertyId.equals("description")) {
								return new FormDetectAndDisplayUrlViewField(
										PrintView.this.problem.getDescription());
							} else if (propertyId.equals("resolution")) {
								return new FormDetectAndDisplayUrlViewField(
										PrintView.this.problem.getResolution());
							}

							return null;
						}
					});
					super.setItemDataSource(newDataSource);
				}
			};

			this.addComponent(this.previewForm);
		}

		class FormLayoutFactory extends ProblemFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(PrintView.this.problem.getIssuename());
			}

			@Override
			protected Layout createTopPanel() {
				return new HorizontalLayout();
			}

			@Override
			protected Layout createBottomPanel() {
				return new CommentListDepot(CommentType.PRJ_PROBLEM,
						PrintView.this.problem.getId(),
						CurrentProjectVariables.getProjectId(), false, true,
						ProjectProblemRelayEmailNotificationAction.class);
			}
		}
	}
}
