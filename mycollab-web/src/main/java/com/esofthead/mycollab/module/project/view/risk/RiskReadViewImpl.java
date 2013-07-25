package com.esofthead.mycollab.module.project.view.risk;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.ui.components.CommentListDepot;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
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
public class RiskReadViewImpl extends AbstractView implements RiskReadView {

	private static final long serialVersionUID = 1L;
	protected SimpleRisk risk;
	protected AdvancedPreviewBeanForm<SimpleRisk> previewForm;

	public RiskReadViewImpl() {
		super();
		this.setMargin(true);
		this.previewForm = new PreviewForm();
		this.addComponent(this.previewForm);
	}

	@Override
	public void previewItem(final SimpleRisk item) {
		this.risk = item;
		this.previewForm.setItemDataSource(new BeanItem<SimpleRisk>(item));
	}

	@Override
	public SimpleRisk getItem() {
		return this.risk;
	}

	@Override
	public HasPreviewFormHandlers<SimpleRisk> getPreviewFormHandlers() {
		return this.previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<SimpleRisk> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(final Item item,
						final Object propertyId, final Component uiContext) {
					if (propertyId.equals("description")) {
						return new FormViewField(RiskReadViewImpl.this.risk
								.getDescription(), Label.CONTENT_XHTML);
					} else if (propertyId.equals("level")) {
						final RatingStars tinyRs = new RatingStars();
						tinyRs.setValue(RiskReadViewImpl.this.risk.getLevel());
						tinyRs.setReadOnly(true);
						return tinyRs;
					} else if (propertyId.equals("status")) {
						return new FormViewField(RiskReadViewImpl.this.risk
								.getStatus());
					} else if (propertyId.equals("datedue")) {
						return new FormViewField(AppContext
								.formatDate(RiskReadViewImpl.this.risk
										.getDatedue()));
					} else if (propertyId.equals("raisedbyuser")) {
						return new ProjectUserFormLinkField(
								RiskReadViewImpl.this.risk.getRaisedbyuser(),
								RiskReadViewImpl.this.risk
										.getRaisedByUserAvatarId(),
								RiskReadViewImpl.this.risk
										.getRaisedByUserFullName());
					} else if (propertyId.equals("response")) {
						return new FormViewField(RiskReadViewImpl.this.risk
								.getResponse(), Label.CONTENT_XHTML);
					} else if (propertyId.equals("assigntouser")) {
						return new ProjectUserFormLinkField(
								RiskReadViewImpl.this.risk.getAssigntouser(),
								RiskReadViewImpl.this.risk
										.getAssignToUserAvatarId(),
								RiskReadViewImpl.this.risk
										.getAssignedToUserFullName());
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

			final RiskReadViewImpl printView = new RiskReadViewImpl.PrintView();
			printView.previewItem(RiskReadViewImpl.this.risk);
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
			final RiskHistoryLogWindow historyLog = new RiskHistoryLogWindow(
					ModuleNameConstants.PRJ, ProjectContants.RISK,
					RiskReadViewImpl.this.risk.getId());
			this.getWindow().addWindow(historyLog);
		}

		class FormLayoutFactory extends RiskFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(RiskReadViewImpl.this.risk.getRiskname());
			}

			@Override
			protected Layout createTopPanel() {
				return (new ProjectPreviewFormControlsGenerator<SimpleRisk>(
						PreviewForm.this))
						.createButtonControls(ProjectRolePermissionCollections.RISKS);
			}

			@Override
			protected Layout createBottomPanel() {
				final CommentListDepot commentList = new CommentListDepot(
						CommentTypeConstants.PRJ_RISK,
						RiskReadViewImpl.this.risk.getId(),
						CurrentProjectVariables.getProjectId(), true, false);
				commentList.setWidth("100%");
				return commentList;
			}
		}
	}

	@SuppressWarnings("serial")
	public static class PrintView extends RiskReadViewImpl {

		public PrintView() {
			this.previewForm = new AdvancedPreviewBeanForm<SimpleRisk>() {
				@Override
				public void setItemDataSource(final Item newDataSource) {
					this.setFormLayoutFactory(new RiskReadViewImpl.PrintView.FormLayoutFactory());
					this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
						private static final long serialVersionUID = 1L;

						@Override
						protected Field onCreateField(final Item item,
								final Object propertyId,
								final Component uiContext) {

							if (propertyId.equals("description")) {
								return new FormViewField(PrintView.this.risk
										.getDescription(), Label.CONTENT_XHTML);
							} else if (propertyId.equals("level")) {
								final RatingStars tinyRs = new RatingStars();
								tinyRs.setValue(PrintView.this.risk.getLevel());
								tinyRs.setReadOnly(true);
								return tinyRs;
							} else if (propertyId.equals("status")) {
								return new FormViewField(PrintView.this.risk
										.getStatus());
							} else if (propertyId.equals("datedue")) {
								return new FormViewField(AppContext
										.formatDate(PrintView.this.risk
												.getDatedue()));
							} else if (propertyId.equals("raisedbyuser")) {
								return new ProjectUserFormLinkField(
										PrintView.this.risk.getRaisedbyuser(),
										PrintView.this.risk
												.getRaisedByUserAvatarId(),
										PrintView.this.risk
												.getRaisedByUserFullName());
							} else if (propertyId.equals("assigntouser")) {
								return new ProjectUserFormLinkField(
										PrintView.this.risk.getAssigntouser(),
										PrintView.this.risk
												.getAssignToUserAvatarId(),
										PrintView.this.risk
												.getAssignedToUserFullName());
							} else if (propertyId.equals("response")) {
								return new FormViewField(PrintView.this.risk
										.getResponse(), Label.CONTENT_XHTML);
							}

							return null;
						}
					});
					super.setItemDataSource(newDataSource);
				}
			};

			this.addComponent(this.previewForm);
		}

		class FormLayoutFactory extends RiskFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(PrintView.this.risk.getRiskname());
			}

			@Override
			protected Layout createTopPanel() {
				return new HorizontalLayout();
			}

			@Override
			protected Layout createBottomPanel() {
				return new CommentListDepot(CommentTypeConstants.PRJ_RISK,
						PrintView.this.risk.getId(),
						CurrentProjectVariables.getProjectId(), false, false);
			}
		}
	}
}
