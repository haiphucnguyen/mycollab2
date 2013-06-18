/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import com.esofthead.mycollab.common.ActivityStreamConstants;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.ProjectResources;
import com.esofthead.mycollab.module.project.domain.ProjectActivityStream;
import com.esofthead.mycollab.module.project.localization.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.localization.ProjectLocalizationTypeMap;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.ProjectLinkBuilder;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.ResourceResolver;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class ActivityStreamComponent extends Depot {

	static class ProjectActivityStreamPagedList
			extends
			AbstractBeanPagedList<ActivityStreamSearchCriteria, ProjectActivityStream> {
		private static final long serialVersionUID = 1L;
		private final ProjectService projectService;

		public ProjectActivityStreamPagedList() {
			super(null, 20);

			projectService = AppContext.getSpringBean(ProjectService.class);
		}

		@Override
		public void doSearch() {
			totalCount = projectService.getTotalActivityStream(searchRequest
					.getSearchCriteria());
			totalPage = (totalCount - 1) / searchRequest.getNumberOfItems() + 1;
			if (searchRequest.getCurrentPage() > totalPage) {
				searchRequest.setCurrentPage(totalPage);
			}

			setCurrentPage(currentPage);
			setTotalPage(totalPage);

			final List<ProjectActivityStream> currentListData = projectService
					.getProjectActivityStreams(searchRequest);
			listContainer.removeAllComponents();

			Date currentDate = new GregorianCalendar(2100, 1, 1).getTime();

			try {
				for (final ProjectActivityStream activityStream : currentListData) {
					Date itemCreatedDate = activityStream.getCreatedtime();
					if (!DateUtils.isSameDay(currentDate, itemCreatedDate)) {
						listContainer.addComponent(new Label(AppContext
								.formatDate(itemCreatedDate)));
						currentDate = itemCreatedDate;
					}

					String content = "";

					if (ActivityStreamConstants.ACTION_CREATE
							.equals(activityStream.getAction())) {
						content = LocalizationHelper
								.getMessage(
										ProjectCommonI18nEnum.FEED_PROJECT_USER_ACTIVITY_CREATE_ACTION_TITLE,
										UserAvatarControlFactory.getLink(
												activityStream.getCreateduser(),
												16),
										ProjectLinkBuilder.WebLinkGenerator.generateProjectMemberFullLink(
												activityStream.getExtratypeid(),
												activityStream.getCreateduser()),
										activityStream.getCreatedUserFullName(),
										LocalizationHelper
												.getMessage(ProjectLocalizationTypeMap
														.getType(activityStream
																.getType())),
										ProjectResources
												.getResourceLink(activityStream
														.getType()),
										ProjectLinkBuilder.WebLinkGenerator.generateProjectItemLink(
												activityStream.getExtratypeid(),
												activityStream.getType(),
												activityStream.getTypeid()),
										activityStream.getNamefield(),
										ResourceResolver
												.getResourceLink("icons/16/project/project.png"),
										ProjectLinkBuilder.WebLinkGenerator.generateProjectFullLink(
												activityStream.getProjectId(),
												ProjectLinkBuilder.DEFAULT_PREFIX_PARAM),
										activityStream.getProjectName());
					} else if (ActivityStreamConstants.ACTION_UPDATE
							.equals(activityStream.getAction())) {
						content = LocalizationHelper
								.getMessage(
										ProjectCommonI18nEnum.FEED_PROJECT_USER_ACTIVITY_UPDATE_ACTION_TITLE,
										UserAvatarControlFactory.getLink(
												activityStream.getCreateduser(),
												16),
										ProjectLinkBuilder.WebLinkGenerator.generateProjectMemberFullLink(
												activityStream.getExtratypeid(),
												activityStream.getCreateduser()),
										activityStream.getCreatedUserFullName(),
										LocalizationHelper
												.getMessage(ProjectLocalizationTypeMap
														.getType(activityStream
																.getType())),
										ProjectResources
												.getResourceLink(activityStream
														.getType()),
										ProjectLinkBuilder.WebLinkGenerator.generateProjectItemLink(
												activityStream.getExtratypeid(),
												activityStream.getType(),
												activityStream.getTypeid()),
										activityStream.getNamefield(),
										ResourceResolver
												.getResourceLink("icons/16/project/project.png"),
										ProjectLinkBuilder.WebLinkGenerator.generateProjectFullLink(
												activityStream.getProjectId(),
												ProjectLinkBuilder.DEFAULT_PREFIX_PARAM),
										activityStream.getProjectName());
					}

					final Label activityLink = new Label(content,
							Label.CONTENT_XHTML);
					listContainer.addComponent(activityLink);
				}
			} catch (final Exception e) {
				throw new MyCollabException(e);
			}
		}
	}

	private static final long serialVersionUID = 1L;

	private final ProjectActivityStreamPagedList activityStreamList;

	public ActivityStreamComponent() {
		super(LocalizationHelper.getMessage(ProjectCommonI18nEnum.FEEDS_TITLE),
				new VerticalLayout());
		activityStreamList = new ProjectActivityStreamPagedList();
		bodyContent.addComponent(new LazyLoadWrapper(activityStreamList));
		addStyleName("activity-panel");
		((VerticalLayout) bodyContent).setMargin(false);
	}

	public void showFeeds(final List<Integer> prjKeys) {
		final ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
		searchCriteria.setModuleSet(new SetSearchField<String>(SearchField.AND,
				new String[] { ModuleNameConstants.PRJ }));
		searchCriteria.setExtraTypeIds(new SetSearchField<Integer>(prjKeys
				.toArray(new Integer[0])));
		activityStreamList.setSearchCriteria(searchCriteria);

	}
}
