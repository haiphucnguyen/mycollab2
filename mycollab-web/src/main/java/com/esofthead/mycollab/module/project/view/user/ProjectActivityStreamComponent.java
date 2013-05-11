/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.common.ActivityStreamConstants;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.SimpleActivityStream;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.common.service.ActivityStreamService;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectResources;
import com.esofthead.mycollab.module.project.localization.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.localization.ProjectLocalizationTypeMap;
import com.esofthead.mycollab.module.project.view.ProjectLinkBuilder;
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
public class ProjectActivityStreamComponent extends Depot {
	private static final long serialVersionUID = 1L;
	private final DefaultBeanPagedList<ActivityStreamService, ActivityStreamSearchCriteria, SimpleActivityStream> activityStreamList;

	public ProjectActivityStreamComponent() {
		super("Project Feeds", new VerticalLayout());
		activityStreamList = new DefaultBeanPagedList<ActivityStreamService, ActivityStreamSearchCriteria, SimpleActivityStream>(
				AppContext.getSpringBean(ActivityStreamService.class),
				ActivityStreamRowDisplayHandler.class, 10);
		this.bodyContent.addComponent(new LazyLoadWrapper(activityStreamList));
		this.addStyleName("activity-panel");
		((VerticalLayout) this.bodyContent).setMargin(false);
	}

	public void showProjectFeeds() {
		ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
		searchCriteria.setModuleSet(new SetSearchField<String>(SearchField.AND,
				new String[] { ModuleNameConstants.PRJ }));

		searchCriteria.setExtraTypeIds(new SetSearchField<Integer>(
				CurrentProjectVariables.getProjectId()));
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
			String content = "";

			if (ActivityStreamConstants.ACTION_CREATE.equals(activityStream
					.getAction())) {
				content = LocalizationHelper
						.getMessage(
								ProjectCommonI18nEnum.FEED_USER_ACTIVITY_CREATE_ACTION_TITLE,
								UserAvatarControlFactory.getLink(
										AppContext.getAccountId(),
										activityStream.getCreateduser(), 16),
								ProjectLinkBuilder.WebLinkGenerator.generateProjectMemberFullLink(
										activityStream.getExtratypeid(),
										activityStream.getCreateduser()),
								activityStream.getCreatedUserFullName(),
								LocalizationHelper
										.getMessage(ProjectLocalizationTypeMap
												.getType(activityStream
														.getType())),
								ProjectResources.getResourceLink(activityStream
										.getType()),
								ProjectLinkBuilder.WebLinkGenerator.generateProjectItemLink(
										activityStream.getExtratypeid(),
										activityStream.getType(),
										activityStream.getTypeid()),
								activityStream.getNamefield());
			} else if (ActivityStreamConstants.ACTION_UPDATE
					.equals(activityStream.getAction())) {
				content = LocalizationHelper
						.getMessage(
								ProjectCommonI18nEnum.FEED_USER_ACTIVITY_UPDATE_ACTION_TITLE,
								UserAvatarControlFactory.getLink(
										AppContext.getAccountId(),
										activityStream.getCreateduser(), 16),
								activityStream.getCreatedUserFullName(),
								LocalizationHelper
										.getMessage(ProjectLocalizationTypeMap
												.getType(activityStream
														.getType())),
								ProjectResources.getResourceLink(activityStream
										.getType()),
								ProjectLinkBuilder.WebLinkGenerator.generateProjectItemLink(
										activityStream.getExtratypeid(),
										activityStream.getType(),
										activityStream.getTypeid()),
								activityStream.getNamefield());
			}

			Label actionLbl = new Label(content, Label.CONTENT_XHTML);
			header.addComponent(actionLbl);
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
