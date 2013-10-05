package com.esofthead.mycollab.module.crm.view.opportunity;

import java.util.Arrays;
import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.domain.ContactOpportunity;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.CrmToolbar;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class OpportunityAddPresenter extends
		CrmGenericPresenter<OpportunityAddView> {

	private static final long serialVersionUID = 1L;

	public OpportunityAddPresenter() {
		super(OpportunityAddView.class);
		bind();
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<Opportunity>() {
					@Override
					public void onSave(final Opportunity item) {
						saveOpportunity(item);
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new OpportunityEvent.GotoList(this, null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new OpportunityEvent.GotoList(this, null));
						}
					}

					@Override
					public void onSaveAndNew(final Opportunity item) {
						saveOpportunity(item);
						EventBus.getInstance().fireEvent(
								new OpportunityEvent.GotoAdd(this, null));
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canWrite(RolePermissionCollections.CRM_OPPORTUNITY)) {
			CrmToolbar crmToolbar = ViewManager.getView(CrmToolbar.class);
			crmToolbar.gotoItem(LocalizationHelper
					.getMessage(CrmCommonI18nEnum.TOOLBAR_OPPORTUNTIES_HEADER));

			Opportunity opportunity = null;
			if (data.getParams() instanceof Opportunity) {
				opportunity = (Opportunity) data.getParams();
			} else if (data.getParams() instanceof Integer) {
				OpportunityService accountService = ApplicationContextUtil
						.getSpringBean(OpportunityService.class);
				opportunity = accountService.findByPrimaryKey(
						(Integer) data.getParams(), AppContext.getAccountId());
				if (opportunity == null) {
					AppContext
							.getApplication()
							.getMainWindow()
							.showNotification(
									LocalizationHelper
											.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
									LocalizationHelper
											.getMessage(GenericI18Enum.INFORMATION_RECORD_IS_NOT_EXISTED_MESSAGE),
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
					return;
				}
			}
			super.onGo(container, data);
			view.editItem(opportunity);

			if (opportunity.getId() == null) {
				AppContext.addFragment("crm/opportunity/add",
						LocalizationHelper.getMessage(
								GenericI18Enum.BROWSER_ADD_ITEM_TITLE,
								"Opportunity"));
			} else {
				AppContext
						.addFragment(
								"crm/opportunity/edit/"
										+ UrlEncodeDecoder.encode(opportunity
												.getId()),
								LocalizationHelper.getMessage(
										GenericI18Enum.BROWSER_EDIT_ITEM_TITLE,
										"Opportunity",
										opportunity.getOpportunityname()));
			}
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}

	public void saveOpportunity(Opportunity opportunity) {
		OpportunityService opportunityService = ApplicationContextUtil
				.getSpringBean(OpportunityService.class);

		opportunity.setSaccountid(AppContext.getAccountId());
		if (opportunity.getId() == null) {
			opportunityService.saveWithSession(opportunity,
					AppContext.getUsername());

			if ((opportunity.getExtraData() != null)
					&& (opportunity.getExtraData() instanceof SimpleContact)) {
				ContactOpportunity associateOpportunity = new ContactOpportunity();
				associateOpportunity.setOpportunityid(opportunity.getId());
				associateOpportunity.setContactid(((SimpleContact) opportunity
						.getExtraData()).getId());
				associateOpportunity.setCreatedtime(new GregorianCalendar()
						.getTime());
				ContactService contactService = ApplicationContextUtil
						.getSpringBean(ContactService.class);
				contactService.saveContactOpportunityRelationship(
						Arrays.asList(associateOpportunity),
						AppContext.getAccountId());
			}
		} else {
			opportunityService.updateWithSession(opportunity,
					AppContext.getUsername());
		}

	}
}
