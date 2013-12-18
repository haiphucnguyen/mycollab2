package com.esofthead.mycollab.premium.module.project.view.risk;

import org.vaadin.teemu.ratingstars.RatingStars;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.ui.components.AbstractPreviewItemComp;
import com.esofthead.mycollab.module.project.ui.components.CommentListDepot;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.schedule.email.project.ProjectRiskRelayEmailNotificationAction;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormDetectAndDisplayUrlViewField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormViewField;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
abstract class AbstractRiskPreviewComp extends
		AbstractPreviewItemComp<SimpleRisk> {
	private static final long serialVersionUID = 1L;

	protected CommentListDepot commentList;

	public AbstractRiskPreviewComp() {
		super(MyCollabResource.newResource("icons/22/project/menu_risk.png"));
	}

	@Override
	protected String initFormTitle() {
		return beanItem.getRiskname();
	}

	@Override
	protected void initRelatedComponents() {
		commentList = new CommentListDepot(CommentType.PRJ_RISK,
				CurrentProjectVariables.getProjectId(), true, true,
				ProjectRiskRelayEmailNotificationAction.class);
		commentList.setWidth("100%");
	}

	@Override
	protected void onPreviewItem() {
		commentList.loadComments(CommentType.PRJ_RISK, beanItem.getId());
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
		return commentList;
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
