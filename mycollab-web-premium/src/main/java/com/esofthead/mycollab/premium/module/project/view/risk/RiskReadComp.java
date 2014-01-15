package com.esofthead.mycollab.premium.module.project.view.risk;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.ui.components.AbstractPreviewItemComp;
import com.esofthead.mycollab.module.project.ui.components.CommentDisplay;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.schedule.email.project.ProjectRiskRelayEmailNotificationAction;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.TabsheetLazyLoadComp;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormDetectAndDisplayUrlViewField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormViewField;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class RiskReadComp extends AbstractPreviewItemComp<SimpleRisk> {
	private static final long serialVersionUID = 1L;

	private CommentDisplay commentDisplay;
	private RiskHistoryList historyList;

	public RiskReadComp() {
		super(MyCollabResource.newResource("icons/22/project/menu_risk.png"));
	}

	@Override
	protected AdvancedPreviewBeanForm<SimpleRisk> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleRisk>();
	}

	@Override
	protected ComponentContainer createButtonControls() {
		return new ProjectPreviewFormControlsGenerator<SimpleRisk>(previewForm)
				.createButtonControls(ProjectRolePermissionCollections.RISKS);
	}

	@Override
	protected String initFormTitle() {
		return beanItem.getRiskname();
	}

	@Override
	protected void initRelatedComponents() {
		commentDisplay = new CommentDisplay(CommentType.PRJ_RISK,
				CurrentProjectVariables.getProjectId(), true, true,
				ProjectRiskRelayEmailNotificationAction.class);
		commentDisplay.setWidth("100%");

		historyList = new RiskHistoryList(ModuleNameConstants.PRJ,
				ProjectContants.RISK);
	}

	@Override
	protected void onPreviewItem() {
		commentDisplay.loadComments(beanItem.getId());
		historyList.loadHistory(beanItem.getId());
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new RiskFormLayoutFactory();
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<SimpleRisk> initBeanFormFieldFactory() {
		return new RiskReadFormFieldFactory(previewForm);
	}

	@Override
	protected ComponentContainer createBottomPanel() {
		final TabsheetLazyLoadComp tabContainer = new TabsheetLazyLoadComp();
		tabContainer.setWidth("100%");

		tabContainer.addTab(commentDisplay, "Comments", MyCollabResource
				.newResource("icons/16/project/gray/comment.png"));

		tabContainer.addTab(historyList, "History", MyCollabResource
				.newResource("icons/16/project/gray/history.png"));
		return tabContainer;
	}

	private static class RiskReadFormFieldFactory extends
			AbstractBeanFieldGroupViewFieldFactory<SimpleRisk> {
		private static final long serialVersionUID = 1L;

		public RiskReadFormFieldFactory(GenericBeanForm<SimpleRisk> form) {
			super(form);
		}

		@Override
		protected Field<?> onCreateField(Object propertyId) {
			SimpleRisk risk = attachForm.getBean();
			if (propertyId.equals("description")) {
				return new FormDetectAndDisplayUrlViewField(
						risk.getDescription());
			} else if (propertyId.equals("level")) {
				final RatingStars tinyRs = new RatingStars();
				tinyRs.setValue(risk.getLevel());
				tinyRs.setReadOnly(true);
				return tinyRs;
			} else if (propertyId.equals("status")) {
				return new FormViewField(risk.getStatus());
			} else if (propertyId.equals("datedue")) {
				return new FormViewField(AppContext.formatDate(risk
						.getDatedue()));
			} else if (propertyId.equals("raisedbyuser")) {
				return new ProjectUserFormLinkField(risk.getRaisedbyuser(),
						risk.getRaisedByUserAvatarId(),
						risk.getRaisedByUserFullName());
			} else if (propertyId.equals("assigntouser")) {
				return new ProjectUserFormLinkField(risk.getAssigntouser(),
						risk.getAssignToUserAvatarId(),
						risk.getAssignedToUserFullName());
			} else if (propertyId.equals("response")) {
				return new FormDetectAndDisplayUrlViewField(risk.getResponse());
			}

			return null;
		}
	}
}
