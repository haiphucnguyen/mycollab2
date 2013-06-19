/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import com.esofthead.mycollab.common.ActivityStreamConstants;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.SimpleActivityStream;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.common.service.ActivityStreamService;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.crm.CrmResources;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.localization.CrmLocalizationTypeMap;
import com.esofthead.mycollab.module.project.domain.ProjectActivityStream;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class ActivityStreamPanel extends Depot {

	static class CrmActivityStreamPagedList
			extends
			AbstractBeanPagedList<ActivityStreamSearchCriteria, ProjectActivityStream> {
		private static final long serialVersionUID = 1L;

		private ActivityStreamService activityStreamService;

		public CrmActivityStreamPagedList() {
			super(null, 20);
			activityStreamService = AppContext
					.getSpringBean(ActivityStreamService.class);

		}

		@Override
		public void doSearch() {
			this.totalCount = this.activityStreamService
					.getTotalCount(this.searchRequest.getSearchCriteria());
			this.totalPage = (this.totalCount - 1)
					/ this.searchRequest.getNumberOfItems() + 1;
			if (this.searchRequest.getCurrentPage() > this.totalPage) {
				this.searchRequest.setCurrentPage(this.totalPage);
			}

			this.setCurrentPage(this.currentPage);
			this.setTotalPage(this.totalPage);

			final List<SimpleActivityStream> currentListData = this.activityStreamService
					.findPagableListByCriteria(this.searchRequest);
			this.listContainer.removeAllComponents();

			Date currentDate = new GregorianCalendar(2100, 1, 1).getTime();

			try {
				for (final SimpleActivityStream activityStream : currentListData) {
					final Date itemCreatedDate = activityStream
							.getCreatedtime();

					if (!DateUtils.isSameDay(currentDate, itemCreatedDate)) {
						final CssLayout dateWrapper = new CssLayout();
						dateWrapper.setWidth("100%");
						dateWrapper.addStyleName("date-wrapper");
						dateWrapper.addComponent(new Label(AppContext
								.formatDate(itemCreatedDate)));
						this.listContainer.addComponent(dateWrapper);
						currentDate = itemCreatedDate;
					}

					CrmCommonI18nEnum action = null;

					if (ActivityStreamConstants.ACTION_CREATE
							.equals(activityStream.getAction())) {
						action = CrmCommonI18nEnum.WIDGET_ACTIVITY_CREATE_ACTION;
					} else if (ActivityStreamConstants.ACTION_UPDATE
							.equals(activityStream.getAction())) {
						action = CrmCommonI18nEnum.WIDGET_ACTIVITY_UPDATE_ACTION;
					}

					final String content = LocalizationHelper
							.getMessage(action, UserAvatarControlFactory
									.getLink(activityStream.getCreateduser(),
											16), activityStream
									.getCreatedUserFullName(),
									LocalizationHelper
											.getMessage(CrmLocalizationTypeMap
													.getType(activityStream
															.getType())),
									CrmResources.getResourceLink(activityStream
											.getType()),
									CrmLinkGenerator.generateCrmItemLink(
											activityStream.getType(),
											activityStream.getTypeid()),
									activityStream.getNamefield());
					final Label activityLink = new Label(content,
							Label.CONTENT_XHTML);

					listContainer.addComponent(activityLink);
				}
			} catch (Exception e) {
				throw new MyCollabException(e);
			}
		}
	}

	private static final long serialVersionUID = 1L;

	private final CrmActivityStreamPagedList activityStreamList;

	public ActivityStreamPanel() {
		super("Activity Channels", new VerticalLayout(), "100%");
		activityStreamList = new CrmActivityStreamPagedList();

		activityStreamList.addStyleName("stream-list");
		bodyContent.addComponent(new LazyLoadWrapper(activityStreamList));
		addStyleName("activity-panel");
		((VerticalLayout) bodyContent).setMargin(false);
	}

	public void display() {
		final ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
		searchCriteria.setModuleSet(new SetSearchField<String>(SearchField.AND,
				new String[] { ModuleNameConstants.CRM }));
		activityStreamList.setSearchCriteria(searchCriteria);
	}
}
