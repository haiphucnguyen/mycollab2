/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.common.ActivityStreamConstants;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.SimpleActivityStream;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.common.service.ActivityStreamService;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.crm.CrmResources;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.localization.CrmLocalizationTypeMap;
import com.esofthead.mycollab.vaadin.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class ActivityStreamPanel extends Depot {
	private static final long serialVersionUID = 1L;
	private final DefaultBeanPagedList<ActivityStreamService, ActivityStreamSearchCriteria, SimpleActivityStream> activityStreamList;

	public ActivityStreamPanel() {
		super("Activity Channels", new VerticalLayout());
		activityStreamList = new DefaultBeanPagedList<ActivityStreamService, ActivityStreamSearchCriteria, SimpleActivityStream>(
				AppContext.getSpringBean(ActivityStreamService.class),
				ActivityStreamRowDisplayHandler.class, 10);
		this.bodyContent.addComponent(new LazyLoadWrapper(activityStreamList));
		this.addStyleName("activity-panel");
		((VerticalLayout) this.bodyContent).setMargin(false);
	}

	public void display() {
		ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
		searchCriteria.setModuleSet(new SetSearchField<String>(SearchField.AND,
				new String[] { ModuleNameConstants.CRM }));
		activityStreamList.setSearchCriteria(searchCriteria);
	}

	public static class ActivityStreamRowDisplayHandler implements
			DefaultBeanPagedList.RowDisplayHandler<SimpleActivityStream> {

		@Override
		public Component generateRow(SimpleActivityStream activityStream,
				int rowIndex) {
			CssLayout layout = new CssLayout();
			layout.setWidth("100%");
			layout.setStyleName("activity-stream");

			CssLayout header = new CssLayout();
			header.setStyleName("stream-content");

			CrmCommonI18nEnum action = null;

			if (ActivityStreamConstants.ACTION_CREATE.equals(activityStream
					.getAction())) {
				action = CrmCommonI18nEnum.WIDGET_ACTIVITY_CREATE_ACTION;
			} else if (ActivityStreamConstants.ACTION_UPDATE
					.equals(activityStream.getAction())) {
				action = CrmCommonI18nEnum.WIDGET_ACTIVITY_UPDATE_ACTION;
			}

			String content = LocalizationHelper.getMessage(action,
					UserAvatarControlFactory.getLink(AppContext.getAccountId(),
							activityStream.getCreateduser(), 16),
					activityStream.getCreatedUserFullName(), LocalizationHelper
							.getMessage(CrmLocalizationTypeMap
									.getType(activityStream.getType())),
					CrmResources.getResourceLink(activityStream.getType()),
					CrmLinkGenerator.generateCrmItemLink(
							activityStream.getType(),
							activityStream.getTypeid()), activityStream
							.getNamefield());
			Label activityLink = new Label(content, Label.CONTENT_XHTML);

			header.addComponent(activityLink);

			layout.addComponent(header);

			CssLayout body = new CssLayout();
			body.setStyleName("activity-date");
			Label dateLbl = new Label(
					DateTimeUtils.getStringDateFromNow(activityStream
							.getCreatedtime()));
			body.addComponent(dateLbl);

			layout.addComponent(body);
			return layout;
		}
	}
}
