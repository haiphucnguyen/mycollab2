package com.esofthead.mycollab.premium.module.project.view.problem;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.ratingstars.RatingStars;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.i18n.ProblemI18nEnum;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.ui.components.AbstractPreviewItemComp2;
import com.esofthead.mycollab.module.project.ui.components.CommentDisplay;
import com.esofthead.mycollab.module.project.ui.components.DateInfoComp;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.schedule.email.project.ProjectRiskRelayEmailNotificationAction;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormDetectAndDisplayUrlViewField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormViewField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.I18nFormViewField;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.TabsheetLazyLoadComp;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserLink;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@ViewComponent
public class ProblemReadViewImpl extends
		AbstractPreviewItemComp2<SimpleProblem> implements ProblemReadView {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(ProblemReadViewImpl.class);

	private CommentDisplay commentList;
	private ProblemHistoryList historyList;

	private DateInfoComp dateInfoComp;
	private PeopleInfoComp peopleInfoComp;

	public ProblemReadViewImpl() {
		super(AppContext.getMessage(ProblemI18nEnum.VIEW_READ_TITLE),
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

		dateInfoComp = new DateInfoComp();
		addToSideBar(dateInfoComp);

		peopleInfoComp = new PeopleInfoComp();
		addToSideBar(peopleInfoComp);
	}

	@Override
	protected void onPreviewItem() {
		if (StatusI18nEnum.Closed.name().equals(beanItem.getStatus())) {
			addLayoutStyleName(UIConstants.LINK_COMPLETED);
		}
		commentList.loadComments("" + beanItem.getId());
		historyList.loadHistory(beanItem.getId());

		dateInfoComp.displayEntryDateTime(beanItem);
		peopleInfoComp.displayEntryPeople(beanItem);
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
				.getMessage(ProjectCommonI18nEnum.TAB_COMMENT),
				MyCollabResource
						.newResource("icons/16/project/gray/comment.png"));

		tabContainer.addTab(historyList, AppContext
				.getMessage(ProjectCommonI18nEnum.TAB_HISTORY),
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
			} else if (propertyId.equals("status")) {
				return new I18nFormViewField(problem.getStatus(),
						StatusI18nEnum.class);
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

	private class PeopleInfoComp extends VerticalLayout {
		private static final long serialVersionUID = 1L;

		public void displayEntryPeople(ValuedBean bean) {
			this.removeAllComponents();
			this.setSpacing(true);
			this.setMargin(new MarginInfo(false, false, false, true));

			Label peopleInfoHeader = new Label(
					AppContext
							.getMessage(ProjectCommonI18nEnum.SUB_INFO_PEOPLE));
			peopleInfoHeader.setStyleName("info-hdr");
			this.addComponent(peopleInfoHeader);

			GridLayout layout = new GridLayout(2, 2);
			layout.setSpacing(true);
			layout.setWidth("100%");
			layout.setMargin(new MarginInfo(false, false, false, true));
			try {
				Label createdLbl = new Label(
						AppContext
								.getMessage(ProjectCommonI18nEnum.ITEM_CREATED_PEOPLE));
				createdLbl.setSizeUndefined();
				layout.addComponent(createdLbl, 0, 0);

				String createdUserName = (String) PropertyUtils.getProperty(
						bean, "raisedbyuser");
				String createdUserAvatarId = (String) PropertyUtils
						.getProperty(bean, "raisedByUserAvatarId");
				String createdUserDisplayName = (String) PropertyUtils
						.getProperty(bean, "raisedByUserFullName");

				UserLink createdUserLink = new UserLink(createdUserName,
						createdUserAvatarId, createdUserDisplayName);
				layout.addComponent(createdUserLink, 1, 0);
				layout.setColumnExpandRatio(1, 1.0f);

				Label assigneeLbl = new Label(
						AppContext
								.getMessage(ProjectCommonI18nEnum.ITEM_ASSIGN_PEOPLE));
				assigneeLbl.setSizeUndefined();
				layout.addComponent(assigneeLbl, 0, 1);
				String assignUserName = (String) PropertyUtils.getProperty(
						bean, "assigntouser");
				String assignUserAvatarId = (String) PropertyUtils.getProperty(
						bean, "assignUserAvatarId");
				String assignUserDisplayName = (String) PropertyUtils
						.getProperty(bean, "assignedUserFullName");

				UserLink assignUserLink = new UserLink(assignUserName,
						assignUserAvatarId, assignUserDisplayName);
				layout.addComponent(assignUserLink, 1, 1);
			} catch (Exception e) {
				log.error("Can not build user link {} ",
						BeanUtility.printBeanObj(bean));
			}

			this.addComponent(layout);

		}
	}
}
