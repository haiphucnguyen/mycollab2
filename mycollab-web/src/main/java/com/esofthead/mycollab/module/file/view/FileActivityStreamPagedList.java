package com.esofthead.mycollab.module.file.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogAction;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogAction.Create;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogAction.Delete;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogAction.Move;
import com.esofthead.mycollab.module.ecm.domain.ContentActivityLogAction.Rename;
import com.esofthead.mycollab.module.ecm.domain.SimpleContentActivityLog;
import com.esofthead.mycollab.module.ecm.domain.criteria.ContentActivityLogSearchCriteria;
import com.esofthead.mycollab.module.ecm.service.ContentActivityLogService;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanPagedList;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class FileActivityStreamPagedList
		extends
		AbstractBeanPagedList<ContentActivityLogSearchCriteria, SimpleContentActivityLog>
		implements HasSearchHandlers<FileSearchCriteria> {
	private static final long serialVersionUID = 1L;

	private final ContentActivityLogService contentActivityLogService;
	private List<SearchHandler<FileSearchCriteria>> handers;

	public FileActivityStreamPagedList() {
		super(null, 20);
		this.contentActivityLogService = AppContext
				.getSpringBean(ContentActivityLogService.class);

	}

	@Override
	public void doSearch() {
		this.totalCount = this.contentActivityLogService
				.getTotalCount(this.searchRequest.getSearchCriteria());
		this.totalPage = (this.totalCount - 1)
				/ this.searchRequest.getNumberOfItems() + 1;
		if (this.searchRequest.getCurrentPage() > this.totalPage) {
			this.searchRequest.setCurrentPage(this.totalPage);
		}

		if (totalPage > 1) {
			if (this.controlBarWrapper != null) {
				this.removeComponent(this.controlBarWrapper);
			}
			this.addComponent(this.createPageControls());
		} else {
			if (getComponentCount() == 2) {
				removeComponent(getComponent(1));
			}
		}

		final List<SimpleContentActivityLog> currentListData = this.contentActivityLogService
				.findPagableListByCriteria(this.searchRequest);
		this.listContainer.removeAllComponents();

		Date currentDate = new GregorianCalendar(2100, 1, 1).getTime();

		try {
			for (final SimpleContentActivityLog activityStream : currentListData) {
				final Date itemCreatedDate = activityStream.getCreatedtime();

				if (!DateUtils.isSameDay(currentDate, itemCreatedDate)) {
					final CssLayout dateWrapper = new CssLayout();
					dateWrapper.setWidth("100%");
					dateWrapper.addStyleName("date-wrapper");
					dateWrapper.addComponent(new Label(AppContext
							.formatDate(itemCreatedDate)));
					this.listContainer.addComponent(dateWrapper);
					currentDate = itemCreatedDate;
				}
				HorizontalLayout streamInfoLayout = new HorizontalLayout();
				streamInfoLayout.setSpacing(true);

				final ContentActivityLogAction contentActivityAction = ContentActivityLogAction
						.fromString(activityStream.getActiondesc());
				String userName = activityStream.getCreateduser();
				if (userName.indexOf("@") != -1)
					userName = userName.substring(0, userName.indexOf("@"));

				Embedded embedded = new Embedded();
				embedded.setIcon(new ThemeResource(activityStream
						.getUserAvatarId()));
				streamInfoLayout.addComponent(embedded);

				Button userNameBtn = new Button(userName,
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								notifySelectHandler(new FileSearchCriteria(),
										"", "");
							}
						});
				userNameBtn.addStyleName("link");
				streamInfoLayout.addComponent(userNameBtn);

				if (contentActivityAction instanceof Move) {
					final String oldPath = ((Move) contentActivityAction)
							.getOldPath();
					String newName = ((Move) contentActivityAction)
							.getNewPath();
					final String oldName = oldPath.substring(oldPath
							.lastIndexOf("/") + 1);
					newName = newName.substring(newName.lastIndexOf("/") + 1);
					streamInfoLayout.addComponent(new Label(" moved "
							+ ((Move) contentActivityAction).getMoveType()
							+ " "));
					Button oldNameLbl = new Button(oldName);
					oldNameLbl.addStyleName("link");
					oldNameLbl.setDescription(oldName);
					streamInfoLayout.addComponent(oldNameLbl);
					streamInfoLayout.addComponent(new Label(" to "));
					String asbPath = ((Move) contentActivityAction)
							.getNewPath();
					asbPath = asbPath.substring(AppContext.getAccountId()
							.toString().length() + 1);
					Button newPathBtn = new Button(asbPath,
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									if (((Move) contentActivityAction)
											.getMoveType()
											.equals(ContentActivityLogAction.CONTENT_TYPE)) {
										FileSearchCriteria criteria = new FileSearchCriteria();
										criteria.setBaseFolder(((Move) contentActivityAction)
												.getNewPath() + "/" + oldName);
										notifySelectHandler(
												criteria,
												ContentActivityLogAction.CONTENT_TYPE,
												"Move");
									} else {
										FileSearchCriteria criteria = new FileSearchCriteria();
										criteria.setBaseFolder(((Move) contentActivityAction)
												.getNewPath());
										notifySelectHandler(
												criteria,
												ContentActivityLogAction.FOLDER_TYPE,
												"Move");
									}
								}
							});
					newPathBtn.addStyleName("link");
					streamInfoLayout.addComponent(newPathBtn);
				} else if (contentActivityAction instanceof Delete) {
					String oldName = ((Delete) contentActivityAction).getPath();
					oldName = oldName.substring(oldName.lastIndexOf("/") + 1);

					streamInfoLayout
							.addComponent(new Label(" deleted "
									+ ((Delete) contentActivityAction)
											.getDeleteType()));
					Button pathBtn = new Button(oldName,
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									String path = ((Delete) contentActivityAction)
											.getPath();
									path = path.substring(0,
											path.lastIndexOf("/"));
									if (((Delete) contentActivityAction)
											.getDeleteType()
											.equals(ContentActivityLogAction.CONTENT_TYPE)) {
										FileSearchCriteria criteria = new FileSearchCriteria();
										criteria.setBaseFolder(path);
										notifySelectHandler(
												criteria,
												ContentActivityLogAction.CONTENT_TYPE,
												"Delete");
									} else {
										FileSearchCriteria criteria = new FileSearchCriteria();
										criteria.setBaseFolder(path);
										notifySelectHandler(
												criteria,
												ContentActivityLogAction.FOLDER_TYPE,
												"Delete");
									}
								}
							});
					pathBtn.setDescription("go to enclosing-folder");
					pathBtn.addStyleName("link");
					streamInfoLayout.addComponent(pathBtn);
				} else if (contentActivityAction instanceof Create) {
					String createName = ((Create) contentActivityAction)
							.getPath();
					createName = createName.substring(createName
							.lastIndexOf("/") + 1);
					streamInfoLayout
							.addComponent(new Label(" created "
									+ ((Create) contentActivityAction)
											.getCreateType()));
					Button infoBtn = new Button(createName,
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									if (((Create) contentActivityAction)
											.getCreateType()
											.equals(ContentActivityLogAction.CONTENT_TYPE)) {
										FileSearchCriteria criteria = new FileSearchCriteria();
										criteria.setBaseFolder(((Create) contentActivityAction)
												.getPath());
										notifySelectHandler(
												criteria,
												ContentActivityLogAction.CONTENT_TYPE,
												"Create");
									} else {
										FileSearchCriteria criteria = new FileSearchCriteria();
										criteria.setBaseFolder(((Create) contentActivityAction)
												.getPath());
										notifySelectHandler(
												criteria,
												ContentActivityLogAction.FOLDER_TYPE,
												"Create");
									}
								}
							});
					infoBtn.addStyleName("link");
					streamInfoLayout.addComponent(infoBtn);
				} else if (contentActivityAction instanceof Rename) {
					String newName = ((Rename) contentActivityAction)
							.getNewPath();
					newName = newName.substring(newName.lastIndexOf("/") + 1,
							newName.length());
					String oldName = ((Rename) contentActivityAction)
							.getOldPath();
					oldName = oldName.substring(oldName.lastIndexOf("/") + 1,
							oldName.length());
					streamInfoLayout.addComponent(new Label(" renamed "
							+ ((Rename) contentActivityAction)
									.getResourceType()));
					Button oldNameLbl = new Button(oldName);
					oldNameLbl.addStyleName("link");
					oldNameLbl.setDescription(oldName);
					streamInfoLayout.addComponent(oldNameLbl);
					streamInfoLayout.addComponent(new Label("to"));

					Button newNameBtn = new Button(newName,
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									if (((Rename) contentActivityAction)
											.getResourceType()
											.equals(ContentActivityLogAction.CONTENT_TYPE)) {
										FileSearchCriteria criteria = new FileSearchCriteria();
										criteria.setBaseFolder(((Rename) contentActivityAction)
												.getNewPath());
										notifySelectHandler(
												criteria,
												ContentActivityLogAction.CONTENT_TYPE,
												"Rename");
									} else {
										FileSearchCriteria criteria = new FileSearchCriteria();
										criteria.setBaseFolder(((Rename) contentActivityAction)
												.getNewPath());
										notifySelectHandler(
												criteria,
												ContentActivityLogAction.FOLDER_TYPE,
												"Rename");
									}
								}
							});
					newNameBtn.addStyleName("link");
					streamInfoLayout.addComponent(newNameBtn);
				}
				final CssLayout streamWrapper = new CssLayout();
				streamWrapper.setWidth("100%");
				streamWrapper.addStyleName("stream-wrapper");
				streamWrapper.addComponent(streamInfoLayout);
				this.listContainer.addComponent(streamWrapper);
			}
		} catch (final Exception e) {
			throw new MyCollabException(e);
		}
	}

	@Override
	protected int queryTotalCount() {
		return this.totalCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<SimpleContentActivityLog> queryCurrentData() {
		return this.contentActivityLogService
				.findPagableListByCriteria(this.searchRequest);
	}

	@Override
	public void addSearchHandler(final SearchHandler<FileSearchCriteria> handler) {
		if (this.handers == null) {
			this.handers = new ArrayList<SearchHandler<FileSearchCriteria>>();
		}
		this.handers.add(handler);
	}

	/**
	 * @see in list-handers , at index 0 handel for Folder-Type, index 1 handel
	 *      for File-Type , else do nothing
	 */
	public void notifySelectHandler(final FileSearchCriteria criteria,
			String objectType, String actionType) {
		if (this.handers != null) {
			if (objectType.equals(ContentActivityLogAction.FOLDER_TYPE)
					|| actionType.equals("Delete")) {
				this.handers.get(0).onSearch(criteria);
			} else if (objectType.equals(ContentActivityLogAction.CONTENT_TYPE)) {
				this.handers.get(1).onSearch(criteria);
			} else {
				this.handers.get(2).onSearch(criteria);
			}
		}
	}
}
