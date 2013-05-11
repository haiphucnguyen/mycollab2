/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import java.util.List;

import com.esofthead.mycollab.common.ActivityStreamConstants;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.ProjectResources;
import com.esofthead.mycollab.module.project.domain.ProjectActivityStream;
import com.esofthead.mycollab.module.project.localization.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.localization.ProjectLocalizationTypeMap;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.ProjectLinkBuilder;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.ResourceResolver;
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
public class ActivityStreamComponent extends Depot {
	private static final long serialVersionUID = 1L;
	private final ProjectActivityStreamPagedList activityStreamList;

	public ActivityStreamComponent() {
		super(LocalizationHelper.getMessage(ProjectCommonI18nEnum.FEEDS_TITLE),
				new VerticalLayout());
		activityStreamList = new ProjectActivityStreamPagedList();
		this.bodyContent.addComponent(new LazyLoadWrapper(activityStreamList));
		this.addStyleName("activity-panel");
		((VerticalLayout) this.bodyContent).setMargin(false);
	}

	public void showFeeds(List<Integer> prjKeys) {
		ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
		searchCriteria.setModuleSet(new SetSearchField<String>(SearchField.AND,
				new String[] { ModuleNameConstants.PRJ }));
		searchCriteria.setExtraTypeIds(new SetSearchField<Integer>(prjKeys
				.toArray(new Integer[0])));
		activityStreamList.setSearchCriteria(searchCriteria);

	}

	static class ProjectActivityStreamPagedList
			extends
			AbstractBeanPagedList<ActivityStreamSearchCriteria, ProjectActivityStream> {
		private static final long serialVersionUID = 1L;
		private final ProjectService projectService;

		public ProjectActivityStreamPagedList() {
			super(
					ActivityStreamComponent.ActivityStreamRowDisplayHandler.class,
					10);

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

			this.setCurrentPage(currentPage);
			this.setTotalPage(totalPage);

			List<ProjectActivityStream> currentListData = projectService
					.getProjectActivityStreams(searchRequest);
			listContainer.removeAllComponents();
			int i = 0;
			try {
				for (ProjectActivityStream item : currentListData) {
					AbstractBeanPagedList.RowDisplayHandler<ProjectActivityStream> rowHandler = rowDisplayHandler
							.newInstance();
					Component row = rowHandler.generateRow(item, i);
					listContainer.addComponent(row);
					i++;
				}
			} catch (Exception e) {
				throw new MyCollabException(e);
			}
		}
	}

	public static class ActivityStreamRowDisplayHandler implements
			DefaultBeanPagedList.RowDisplayHandler<ProjectActivityStream> {

		@Override
		public Component generateRow(
				final ProjectActivityStream activityStream, int rowIndex) {
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
								ProjectCommonI18nEnum.FEED_PROJECT_USER_ACTIVITY_CREATE_ACTION_TITLE,
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
								activityStream.getNamefield(),
								ResourceResolver
										.getResourceLink("icons/16/project/project.png"),
								ProjectLinkBuilder.WebLinkGenerator.generateProjectFullLink(
										activityStream.getProjectId(),
										ProjectLinkBuilder.DEFAULT_PREFIX_PARAM),
								activityStream.getProjectName());
			}

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
