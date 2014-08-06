package com.esofthead.mycollab.premium.module.project.view.risk;

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
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.i18n.RiskI18nEnum;
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
public class RiskReadViewImpl extends AbstractPreviewItemComp2<SimpleRisk>
		implements RiskReadView {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(RiskReadViewImpl.class);

	private CommentDisplay commentDisplay;
	private RiskHistoryList historyList;

	private DateInfoComp dateInfoComp;

	private PeopleInfoComp peopleInfoComp;

	public RiskReadViewImpl() {
		super(AppContext.getMessage(RiskI18nEnum.FORM_READ_TITLE),
				MyCollabResource
						.newResource("icons/22/project/risk_selected.png"));
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
		commentDisplay.setMargin(true);

		historyList = new RiskHistoryList(ModuleNameConstants.PRJ,
				ProjectTypeConstants.RISK);

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

		commentDisplay.loadComments(beanItem.getId());
		historyList.loadHistory(beanItem.getId());

		dateInfoComp.displayEntryDateTime(beanItem);
		peopleInfoComp.displayEntryPeople(beanItem);
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

		tabContainer.addTab(commentDisplay, AppContext
				.getMessage(ProjectCommonI18nEnum.TAB_COMMENT),
				MyCollabResource
						.newResource("icons/16/project/gray/comment.png"));

		tabContainer.addTab(historyList, AppContext
				.getMessage(ProjectCommonI18nEnum.TAB_HISTORY),
				MyCollabResource
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
				return new I18nFormViewField(risk.getStatus(),
						StatusI18nEnum.class);
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

	@Override
	public SimpleRisk getItem() {
		return beanItem;
	}

	@Override
	public HasPreviewFormHandlers<SimpleRisk> getPreviewFormHandlers() {
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
						bean, "assignToUserAvatarId");
				String assignUserDisplayName = (String) PropertyUtils
						.getProperty(bean, "assignedToUserFullName");

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
