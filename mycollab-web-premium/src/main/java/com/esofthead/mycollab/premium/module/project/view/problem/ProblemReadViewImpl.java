package com.esofthead.mycollab.premium.module.project.view.problem;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.localization.ProblemI18nEnum;
import com.esofthead.mycollab.module.project.localization.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.ui.components.AbstractPreviewItemComp;
import com.esofthead.mycollab.module.project.ui.components.CommentDisplay;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.schedule.email.project.ProjectRiskRelayEmailNotificationAction;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormDetectAndDisplayUrlViewField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormViewField;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.TabsheetLazyLoadComp;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@ViewComponent
public class ProblemReadViewImpl extends AbstractPreviewItemComp<SimpleProblem>
		implements ProblemReadView {

	private static final long serialVersionUID = 1L;

	private CommentDisplay commentList;
	private ProblemHistoryList historyList;

	public ProblemReadViewImpl() {
		super(AppContext.getMessage(ProblemI18nEnum.FORM_READ_TITLE),
				MyCollabResource
						.newResource("icons/22/project/problem_selected.png"));
	}

	@Override
	protected AdvancedPreviewBeanForm<SimpleProblem> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleProblem>();
	}

	@Override
	protected ComponentContainer createButtonControls() {
		return new ProjectPreviewFormControlsGenerator<SimpleProblem>(
				previewForm)
				.createButtonControls(ProjectRolePermissionCollections.PROBLEMS);
	}

	@Override
	protected void initRelatedComponents() {
		commentList = new CommentDisplay(CommentType.PRJ_PROBLEM,
				CurrentProjectVariables.getProjectId(), true, true,
				ProjectRiskRelayEmailNotificationAction.class);
		commentList.setWidth("100%");

		historyList = new ProblemHistoryList(ModuleNameConstants.PRJ,
				ProjectTypeConstants.PROBLEM);
	}

	@Override
	protected void onPreviewItem() {
		if ("Closed".equals(beanItem.getStatus())) {
			addLayoutStyleName(UIConstants.LINK_COMPLETED);
		}
		commentList.loadComments(beanItem.getId());
		historyList.loadHistory(beanItem.getId());
	}

	@Override
	protected String initFormTitle() {
		return beanItem.getIssuename();
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new ProblemFormLayoutFactory();
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<SimpleProblem> initBeanFormFieldFactory() {
		return new ProblemReadFormFieldFactory(previewForm);
	}

	@Override
	protected ComponentContainer createBottomPanel() {
		final TabsheetLazyLoadComp tabContainer = new TabsheetLazyLoadComp();
		tabContainer.setWidth("100%");

		tabContainer.addTab(commentList, AppContext
				.getMessage(ProjectCommonI18nEnum.COMMENT_TAB),
				MyCollabResource
						.newResource("icons/16/project/gray/comment.png"));

		tabContainer.addTab(historyList, AppContext
				.getMessage(ProjectCommonI18nEnum.HISTORY_TAB),
				MyCollabResource
						.newResource("icons/16/project/gray/history.png"));
		return tabContainer;
	}

	private static class ProblemReadFormFieldFactory extends
			AbstractBeanFieldGroupViewFieldFactory<SimpleProblem> {
		private static final long serialVersionUID = 1L;

		public ProblemReadFormFieldFactory(GenericBeanForm<SimpleProblem> form) {
			super(form);
		}

		@Override
		protected Field<?> onCreateField(Object propertyId) {
			SimpleProblem problem = attachForm.getBean();

			if (propertyId.equals("raisedbyuser")) {
				return new ProjectUserFormLinkField(problem.getRaisedbyuser(),
						problem.getRaisedByUserAvatarId(),
						problem.getRaisedByUserFullName());
			} else if (propertyId.equals("level")) {
				final RatingStars tinyRs = new RatingStars();
				tinyRs.setValue(problem.getLevel());
				tinyRs.setReadOnly(true);
				return tinyRs;
			} else if (propertyId.equals("datedue")) {
				return new FormViewField(AppContext.formatDate(problem
						.getDatedue()));
			} else if (propertyId.equals("assigntouser")) {
				return new ProjectUserFormLinkField(problem.getAssigntouser(),
						problem.getAssignUserAvatarId(),
						problem.getAssignedUserFullName());
			} else if (propertyId.equals("description")) {
				return new FormDetectAndDisplayUrlViewField(
						problem.getDescription());
			}

			return null;
		}
	}

	@Override
	public SimpleProblem getItem() {
		return beanItem;
	}

	@Override
	public HasPreviewFormHandlers<SimpleProblem> getPreviewFormHandlers() {
		return previewForm;
	}
}
