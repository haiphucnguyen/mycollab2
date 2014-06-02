package com.esofthead.mycollab.premium.module.file.view;

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
import com.esofthead.mycollab.module.user.AccountLinkUtils;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class FileActivityStreamPagedList
		extends
		AbstractBeanPagedList<ContentActivityLogSearchCriteria, SimpleContentActivityLog>
		implements HasSearchHandlers<FileSearchCriteria> {
	private static final long serialVersionUID = 1L;

	private final ContentActivityLogService contentActivityLogService;
	private List<SearchHandler<FileSearchCriteria>> handers;

	public FileActivityStreamPagedList() {
		super(null, 20);
		this.contentActivityLogService = ApplicationContextUtil
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

				Embedded fileIconEmbedded = new Embedded();
				fileIconEmbedded.setSource(MyCollabResource
						.newResource("icons/16/ecm/file_white.png"));

				Embedded folderIconEmbedded = new Embedded();
				folderIconEmbedded.setSource(MyCollabResource
						.newResource("icons/16/ecm/folder_blue.png"));

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

				if (activityStream.getUserAvatarId() != null) {
					Label IconEmbedded = new Label("<img src=\""
							+ UserAvatarControlFactory.getAvatarLink(
									activityStream.getUserAvatarId(), 16)
							+ "\" alt=\"\">", ContentMode.HTML);
					streamInfoLayout.addComponent(IconEmbedded);
				} else {
					Button button = new Button();
					button.setIcon(UserAvatarControlFactory
							.createAvatarResource(null, 16));
					button.setStyleName("link");

					streamInfoLayout.addComponent(button);
				}
				String userLinkStr = "<a href=\""
						+ AccountLinkUtils.generatePreviewFullUserLink(
								AppContext.getSiteUrl(),
								activityStream.getCreateduser()) + "\">"
						+ activityStream.getUserFullName() + "</a>";
				Label userLbl = new Label(userLinkStr, ContentMode.HTML);
				streamInfoLayout.addComponent(userLbl);

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
					if (((Move) contentActivityAction).getMoveType().equals(
							ContentActivityLogAction.FOLDER_TYPE)) {
						streamInfoLayout.addComponent(folderIconEmbedded);
					} else {
						streamInfoLayout.addComponent(fileIconEmbedded);
					}

					Button oldNameLbl = new Button(oldName);
					oldNameLbl.addStyleName("link");
					oldNameLbl.setDescription(oldName);
					streamInfoLayout.addComponent(oldNameLbl);
					streamInfoLayout.addComponent(new Label(" to folder"));

					Embedded destinationFolderIconEmbedded = new Embedded();
					destinationFolderIconEmbedded.setSource(MyCollabResource
							.newResource("icons/16/ecm/folder_blue.png"));
					streamInfoLayout
							.addComponent(destinationFolderIconEmbedded);

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
					newPathBtn
							.setDescription("Download if it's available or go to enclosing folder. Please review activity logs.");
					streamInfoLayout.addComponent(newPathBtn);
				} else if (contentActivityAction instanceof Delete) {
					String oldName = ((Delete) contentActivityAction).getPath();
					oldName = oldName.substring(oldName.lastIndexOf("/") + 1);

					streamInfoLayout
							.addComponent(new Label(" deleted "
									+ ((Delete) contentActivityAction)
											.getDeleteType()));
					if (((Delete) contentActivityAction).getDeleteType()
							.equals(ContentActivityLogAction.FOLDER_TYPE)) {
						streamInfoLayout.addComponent(folderIconEmbedded);
					} else {
						streamInfoLayout.addComponent(fileIconEmbedded);
					}
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

					if (((Create) contentActivityAction).getCreateType()
							.equals(ContentActivityLogAction.FOLDER_TYPE)) {
						streamInfoLayout.addComponent(folderIconEmbedded);
					} else {
						streamInfoLayout.addComponent(fileIconEmbedded);
					}
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
					infoBtn.setDescription("Download if it's available or go to enclosing folder. Please review activity logs.");
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
					if (((Rename) contentActivityAction).getResourceType()
							.equals(ContentActivityLogAction.FOLDER_TYPE)) {
						streamInfoLayout.addComponent(folderIconEmbedded);
					} else {
						streamInfoLayout.addComponent(fileIconEmbedded);
					}
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
		return 0;
	}

	@Override
	protected List<SimpleContentActivityLog> queryCurrentData() {
		return null;
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
