package com.esofthead.mycollab.module.project.view.file;

import java.io.InputStream;
import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.easyuploads.SingleFileUploadField;
import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.ecm.ContentException;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.StreamDownloadResourceFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectContentEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.Separator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.CollapseEvent;
import com.vaadin.ui.Tree.ExpandEvent;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

@ViewComponent
public class FileDashboardViewImpl extends AbstractView implements
		FileDashboardView {
	private static final long serialVersionUID = 1L;

	private final TreeTable folderTree;
	private final ResourceTableDisplay resourceTable;
	private String projectPath;
	private final FileSearchPanel fileSearchPanel;

	private Folder baseFolder;

	private final ResourceService resourceService;

	public FileDashboardViewImpl() {
		this.setWidth("100%");
		this.setSpacing(true);
		this.setMargin(false, true, true, true);
		this.resourceService = AppContext.getSpringBean(ResourceService.class);

		final HorizontalLayout menuBar = new HorizontalLayout();
		menuBar.setSpacing(true);
		menuBar.addStyleName("control-buttons");

		final Button addFolderBtn = new Button("Add Folder",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						if (FileDashboardViewImpl.this.folderTree.getValue() != null) {
							FileDashboardViewImpl.this.baseFolder = (Folder) FileDashboardViewImpl.this.folderTree
									.getValue();
						}

						FileDashboardViewImpl.this.getWindow().addWindow(
								new AddNewFolderWindow());
					}
				});
		addFolderBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		addFolderBtn.addStyleName(UIConstants.THEME_BLUE_LINK);

		menuBar.addComponent(addFolderBtn);

		final Button uploadBtn = new Button("Upload",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						FileDashboardViewImpl.this.getWindow().addWindow(
								new UploadContentWindow());

					}
				});
		uploadBtn.setIcon(MyCollabResource.newResource("icons/16/upload.png"));
		uploadBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		menuBar.addComponent(uploadBtn);

		final Button deleteBtn = new Button("Delete",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						if (FileDashboardViewImpl.this.baseFolder != null
								&& !FileDashboardViewImpl.this.projectPath
										.equals(FileDashboardViewImpl.this.baseFolder
												.getPath())) {
							ConfirmDialogExt.show(
									FileDashboardViewImpl.this.getWindow(),
									LocalizationHelper
											.getMessage(
													GenericI18Enum.DELETE_DIALOG_TITLE,
													ApplicationProperties
															.getString(ApplicationProperties.SITE_NAME)),
									"Are you sure to delete folder "
											+ FileDashboardViewImpl.this.baseFolder
													.getName() + " ?",
									LocalizationHelper
											.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
									LocalizationHelper
											.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
									new ConfirmDialog.Listener() {
										private static final long serialVersionUID = 1L;

										@Override
										public void onClose(
												final ConfirmDialog dialog) {
											// TODO Auto-generated method stub

										}
									});
						}

					}
				});
		deleteBtn.setIcon(MyCollabResource.newResource("icons/16/delete2.png"));
		deleteBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		menuBar.addComponent(deleteBtn);

		this.fileSearchPanel = new FileSearchPanel(menuBar);

		this.addComponent(this.fileSearchPanel);

		final HorizontalLayout resourceContainer = new HorizontalLayout();
		resourceContainer.setSizeFull();

		this.folderTree = new TreeTable();
		this.folderTree.setMultiSelect(false);
		this.folderTree.setSelectable(true);
		this.folderTree.setImmediate(true);
		this.folderTree.addContainerProperty("Name", String.class, "");
		this.folderTree.addContainerProperty("Date Modified", String.class, "");
		this.folderTree.setColumnWidth("Date Modified",
				UIConstants.TABLE_DATE_TIME_WIDTH);
		this.folderTree.setColumnExpandRatio("Name", 1.0f);
		this.folderTree.setWidth("300px");

		resourceContainer.addComponent(this.folderTree);

		this.folderTree.addListener(new Tree.ExpandListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void nodeExpand(final ExpandEvent event) {
				final Folder expandFolder = (Folder) event.getItemId();
				final List<Folder> subFolders = FileDashboardViewImpl.this.resourceService
						.getSubFolders(expandFolder.getPath());

				FileDashboardViewImpl.this.folderTree.setItemIcon(expandFolder,
						MyCollabResource
								.newResource("icons/16/ecm/folder_open.png"));

				if (subFolders != null) {
					for (final Folder subFolder : subFolders) {
						expandFolder.addChild(subFolder);
						Object addItem = FileDashboardViewImpl.this.folderTree.addItem(
								new Object[] {
										subFolder.getName(),
										AppContext.formatDateTime(subFolder
												.getCreated().getTime()) },
								subFolder);

						FileDashboardViewImpl.this.folderTree.setItemIcon(
								subFolder,
								MyCollabResource
										.newResource("icons/16/ecm/folder_close.png"));
						FileDashboardViewImpl.this.folderTree.setItemCaption(
								subFolder, subFolder.getName());
						FileDashboardViewImpl.this.folderTree.setParent(
								subFolder, expandFolder);
					}
				}
			}
		});

		this.folderTree.addListener(new Tree.CollapseListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void nodeCollapse(final CollapseEvent event) {
				final Folder collapseFolder = (Folder) event.getItemId();
				FileDashboardViewImpl.this.folderTree.setItemIcon(collapseFolder,
						MyCollabResource
								.newResource("icons/16/ecm/folder_close.png"));
				final List<Folder> childs = collapseFolder.getChilds();
				for (final Folder subFolder : childs) {
					FileDashboardViewImpl.this.folderTree.removeItem(subFolder);
				}

				childs.clear();
			}
		});

		this.folderTree.addListener(new ItemClickEvent.ItemClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(final ItemClickEvent event) {
				FileDashboardViewImpl.this.baseFolder = (Folder) event
						.getItemId();
				FileDashboardViewImpl.this
						.displayResourcesInTable(FileDashboardViewImpl.this.baseFolder);
			}
		});

		this.resourceTable = new ResourceTableDisplay();
		this.resourceTable.setWidth("100%");

		final VerticalLayout rightColumn = new VerticalLayout();
		rightColumn.setWidth("100%");
		rightColumn.setMargin(false, false, false, true);
		rightColumn.addComponent(this.resourceTable);

		resourceContainer.addComponent(rightColumn);
		resourceContainer.setExpandRatio(rightColumn, 1.0f);

		this.addComponent(resourceContainer);
	}

	private void displayResourcesInTable(final Folder folder) {
		final List<Resource> resources = this.resourceService
				.getResources(folder.getPath());
		this.resourceTable
				.setContainerDataSource(new BeanItemContainer<Resource>(
						Resource.class, resources));
		this.resourceTable.setVisibleColumns(new String[] { "uuid", "path",
				"size", "created" });
		this.resourceTable.setColumnHeaders(new String[] { "", "Name",
				"Size (Kb)", "Created" });

	}

	private void displayResourcesInTable(final String foldername) {
		List<Folder> childs = this.baseFolder.getChilds();
		if (childs == null) {
			childs = this.resourceService.getSubFolders(this.baseFolder
					.getPath());

			for (final Folder subFolder : childs) {
				this.baseFolder.addChild(subFolder);
				this.folderTree.addItem(
						new Object[] {
								subFolder.getName(),
								AppContext.formatDateTime(subFolder
										.getCreated().getTime()) }, subFolder);
				this.folderTree.setItemCaption(subFolder, subFolder.getName());
				this.folderTree.setParent(subFolder, this.baseFolder);
				if (foldername.equals(subFolder.getName())) {
					this.folderTree.setCollapsed(subFolder, false);
					this.displayResourcesInTable(subFolder);
					this.baseFolder = subFolder;
				} else {
					this.folderTree.setItemIcon(subFolder, MyCollabResource
							.newResource("icons/16/ecm/folder_close.png"));
				}
			}
		} else {
			for (final Folder subFolder : childs) {
				if (foldername.equals(subFolder.getName())) {
					this.folderTree.setCollapsed(subFolder, false);
					this.folderTree.setValue(subFolder);
					this.displayResourcesInTable(subFolder);
					this.baseFolder = subFolder;
				}
			}
		}
	}

	@Override
	public void displayProjectFiles() {
		this.folderTree.removeAllItems();
		final int projectId = CurrentProjectVariables.getProjectId();
		this.projectPath = String.format("%d/project/%d",
				AppContext.getAccountId(), projectId);

		this.baseFolder = new Folder();
		this.baseFolder.setPath(this.projectPath);
		this.folderTree.addItem(new Object[] {
				CurrentProjectVariables.getProject().getName(), "" },
				this.baseFolder);
		this.folderTree.setItemCaption(this.baseFolder, CurrentProjectVariables
				.getProject().getName());

		this.folderTree.setCollapsed(this.baseFolder, false);
		this.displayResourcesInTable(this.baseFolder);
	}

	/**
	 * Window to ask user enter the new folder
	 * 
	 * @author haiphucnguyen
	 * 
	 */
	private class AddNewFolderWindow extends Window {
		private static final long serialVersionUID = 1L;

		private final TextField folderName;

		public AddNewFolderWindow() {
			((VerticalLayout) this.getContent()).setWidth(
					Sizeable.SIZE_UNDEFINED, 0);
			this.setModal(true);
			this.setCaption("New Folder");
			this.center();

			final HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);
			layout.setSizeUndefined();
			final Label captionLbl = new Label("Enter folder name: ");
			layout.addComponent(captionLbl);
			layout.setComponentAlignment(captionLbl, Alignment.MIDDLE_LEFT);

			this.folderName = new TextField();
			layout.addComponent(this.folderName);
			layout.setComponentAlignment(this.folderName, Alignment.MIDDLE_LEFT);
			layout.setExpandRatio(this.folderName, 1.0f);

			this.addComponent(layout);
			((VerticalLayout) this.getContent()).setComponentAlignment(layout,
					Alignment.MIDDLE_CENTER);

			final HorizontalLayout controlsLayout = new HorizontalLayout();
			controlsLayout.setSpacing(true);
			controlsLayout.setMargin(true, false, false, false);

			final Button saveBtn = new Button(
					LocalizationHelper
							.getMessage(GenericI18Enum.BUTTON_SAVE_LABEL),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {

							final String folderVal = (String) AddNewFolderWindow.this.folderName
									.getValue();
							if (folderVal != null
									&& !folderVal.trim().equals("")) {
								// TODO: check folder name with valid characters
								final String baseFolderPath = (FileDashboardViewImpl.this.baseFolder == null) ? FileDashboardViewImpl.this.projectPath
										: FileDashboardViewImpl.this.baseFolder
												.getPath();
								final Folder newFolder = FileDashboardViewImpl.this.resourceService
										.createNewFolder(baseFolderPath,
												folderVal,
												AppContext.getUsername());
								FileDashboardViewImpl.this.baseFolder
										.addChild(newFolder);
								FileDashboardViewImpl.this.folderTree.addItem(
										new Object[] {
												newFolder.getName(),
												AppContext
														.formatDateTime(newFolder
																.getCreated()
																.getTime()) },
										newFolder);
								FileDashboardViewImpl.this.folderTree
										.setItemCaption(newFolder,
												newFolder.getName());
								FileDashboardViewImpl.this.folderTree.setParent(
										newFolder,
										FileDashboardViewImpl.this.baseFolder);
								FileDashboardViewImpl.this.folderTree
										.setItemIcon(
												newFolder,
												MyCollabResource
														.newResource("icons/16/ecm/folder_close.png"));
								if (FileDashboardViewImpl.this.folderTree
										.isCollapsed(FileDashboardViewImpl.this.baseFolder)) {
									FileDashboardViewImpl.this.folderTree
											.setCollapsed(
													FileDashboardViewImpl.this.baseFolder,
													false);
								}
								AddNewFolderWindow.this.close();

							}
						}
					});
			saveBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
			controlsLayout.addComponent(saveBtn);

			final Button cancelBtn = new Button(
					LocalizationHelper
							.getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							AddNewFolderWindow.this.close();

						}
					});
			cancelBtn.addStyleName(UIConstants.THEME_LINK);
			controlsLayout.addComponent(cancelBtn);
			controlsLayout.setComponentAlignment(cancelBtn,
					Alignment.MIDDLE_RIGHT);

			this.addComponent(controlsLayout);
			((VerticalLayout) this.getContent()).setComponentAlignment(
					controlsLayout, Alignment.MIDDLE_CENTER);
		}
	}

	private class UploadContentWindow extends Window {
		private static final long serialVersionUID = 1L;

		private final GridFormLayoutHelper layoutHelper;
		private final TextArea descField;
		private final SingleFileUploadField uploadField;

		public UploadContentWindow() {
			super("Upload Content");
			this.setWidth("500px");
			((VerticalLayout) this.getContent()).setMargin(false, false, true,
					false);
			this.setModal(true);

			this.layoutHelper = new GridFormLayoutHelper(1, 2, "100%", "167px",
					Alignment.MIDDLE_LEFT);

			this.uploadField = (SingleFileUploadField) this.layoutHelper
					.addComponent(new SingleFileUploadField(), "File", 0, 0);
			this.descField = (TextArea) this.layoutHelper.addComponent(
					new TextArea(), "Description", 0, 1);

			this.layoutHelper.getLayout().setWidth("100%");
			this.layoutHelper.getLayout().setMargin(false);
			this.layoutHelper.getLayout().addStyleName("colored-gridlayout");
			this.addComponent(this.layoutHelper.getLayout());

			final HorizontalLayout controlsLayout = new HorizontalLayout();
			controlsLayout.setSpacing(true);
			controlsLayout.setMargin(true, false, false, false);

			final Button uploadBtn = new Button("Upload",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							final InputStream contentStream = UploadContentWindow.this.uploadField
									.getContentAsStream();
							if (contentStream != null) {
								final Content content = new Content();
								content.setDescription((String) UploadContentWindow.this.descField
										.getValue());
								content.setPath(FileDashboardViewImpl.this.baseFolder
										.getPath()
										+ "/"
										+ UploadContentWindow.this.uploadField
												.getFileName());
								content.setSize(Double.parseDouble(""
										+ UploadContentWindow.this.uploadField
												.getFileSize()));
								FileDashboardViewImpl.this.resourceService
										.saveContent(content,
												AppContext.getUsername(),
												contentStream);
								UploadContentWindow.this.close();
								FileDashboardViewImpl.this
										.displayResourcesInTable(FileDashboardViewImpl.this.baseFolder);
								UploadContentWindow.this.close();
							} else {
								AppContext
										.getApplication()
										.getMainWindow()
										.showNotification(
												"It seems you did not attach file yet!");
							}

						}
					});
			uploadBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
			controlsLayout.addComponent(uploadBtn);

			final Button cancelBtn = new Button(
					LocalizationHelper
							.getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							UploadContentWindow.this.close();
						}
					});
			cancelBtn.addStyleName(UIConstants.THEME_LINK);
			controlsLayout.addComponent(cancelBtn);
			controlsLayout.setComponentAlignment(cancelBtn,
					Alignment.MIDDLE_RIGHT);

			this.addComponent(controlsLayout);
			((VerticalLayout) this.getContent()).setComponentAlignment(
					controlsLayout, Alignment.MIDDLE_CENTER);
		}

	}

	@SuppressWarnings("serial")
	private class ResourceTableDisplay extends Table {
		public ResourceTableDisplay() {

			this.addGeneratedColumn("uuid", new Table.ColumnGenerator() {
				private static final long serialVersionUID = 1L;

				@Override
				public Object generateCell(final Table source,
						final Object itemId, final Object columnId) {

					final Resource resource = ResourceTableDisplay.this
							.getResource(itemId);

					final PopupButton resourceSettingPopupBtn = new PopupButton();
					final VerticalLayout filterBtnLayout = new VerticalLayout();

					final Button renameBtn = new Button("Rename",
							new Button.ClickListener() {

								@Override
								public void buttonClick(final ClickEvent event) {
									resourceSettingPopupBtn
											.setPopupVisible(false);
									final RenameResourceWindow renameWindow = new RenameResourceWindow(
											resource,
											FileDashboardViewImpl.this.resourceService);
									ResourceTableDisplay.this.getWindow()
											.addWindow(renameWindow);
								}
							});
					renameBtn.setStyleName("link");
					filterBtnLayout.addComponent(renameBtn);

					final Button downloadBtn = new Button("Download",
							new Button.ClickListener() {

								@Override
								public void buttonClick(final ClickEvent event) {
									resourceSettingPopupBtn
											.setPopupVisible(false);
									if (resource instanceof Content) {
										resourceSettingPopupBtn
												.setPopupVisible(false);
										final com.vaadin.terminal.Resource downloadResource = StreamDownloadResourceFactory
												.getStreamResource(((Content) resource)
														.getPath());
										AppContext
												.getApplication()
												.getMainWindow()
												.open(downloadResource, "_self");
									} else {
										final com.vaadin.terminal.Resource downloadResource = StreamDownloadResourceFactory
												.getStreamFolderResource(((Folder) resource)
														.getPath());
										AppContext
												.getApplication()
												.getMainWindow()
												.open(downloadResource, "_self");
									}

								}
							});
					downloadBtn.setStyleName("link");
					filterBtnLayout.addComponent(downloadBtn);

					final Button deleteBtn = new Button("Delete",
							new Button.ClickListener() {

								@Override
								public void buttonClick(final ClickEvent event) {
									resourceSettingPopupBtn
											.setPopupVisible(false);
									ConfirmDialogExt.show(
											FileDashboardViewImpl.this
													.getWindow(),
											LocalizationHelper
													.getMessage(
															GenericI18Enum.DELETE_DIALOG_TITLE,
															ApplicationProperties
																	.getString(ApplicationProperties.SITE_NAME)),
											LocalizationHelper
													.getMessage(GenericI18Enum.DELETE_SINGLE_ITEM_DIALOG_MESSAGE),
											LocalizationHelper
													.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
											LocalizationHelper
													.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
											new ConfirmDialog.Listener() {
												private static final long serialVersionUID = 1L;

												@Override
												public void onClose(
														final ConfirmDialog dialog) {
													if (dialog.isConfirmed()) {
														FileDashboardViewImpl.this.resourceService
																.removeResource(resource
																		.getPath());

														FileDashboardViewImpl.this
																.displayResourcesInTable(FileDashboardViewImpl.this.baseFolder);

														FileDashboardViewImpl.this.folderTree
																.setCollapsed(
																		FileDashboardViewImpl.this.baseFolder,
																		true);
														FileDashboardViewImpl.this.folderTree
																.setCollapsed(
																		FileDashboardViewImpl.this.baseFolder,
																		false);
													}
												}
											});

								}
							});
					deleteBtn.setStyleName("link");
					filterBtnLayout.addComponent(deleteBtn);

					filterBtnLayout.setMargin(true);
					filterBtnLayout.setSpacing(true);
					filterBtnLayout.setWidth("100px");
					resourceSettingPopupBtn.setIcon(MyCollabResource
							.newResource("icons/16/item_settings.png"));
					resourceSettingPopupBtn.setStyleName("link");
					resourceSettingPopupBtn.addComponent(filterBtnLayout);
					return resourceSettingPopupBtn;
				}
			});

			this.addGeneratedColumn("path", new Table.ColumnGenerator() {
				private static final long serialVersionUID = 1L;

				@Override
				public Object generateCell(final Table source,
						final Object itemId, final Object columnId) {
					final Resource resource = ResourceTableDisplay.this
							.getResource(itemId);
					String path = resource.getPath();
					final int pathIndex = path.lastIndexOf("/");
					if (pathIndex > -1) {
						path = path.substring(pathIndex + 1);
					}
					final HorizontalLayout resourceLabel = new HorizontalLayout();

					com.vaadin.terminal.Resource iconResource = null;
					if (resource instanceof Content) {
						iconResource = UiUtils
								.getFileIconResource(((Content) resource)
										.getPath());
					} else {
						iconResource = MyCollabResource
								.newResource("icons/16/ecm/folder_close.png");
					}
					final Embedded iconEmbed = new Embedded(null, iconResource);

					resourceLabel.addComponent(iconEmbed);
					resourceLabel.setComponentAlignment(iconEmbed,
							Alignment.MIDDLE_CENTER);

					final ButtonLink resourceLink = new ButtonLink(path,
							new Button.ClickListener() {

								@Override
								public void buttonClick(final ClickEvent event) {
									if (resource instanceof Folder) {
										FileDashboardViewImpl.this
												.displayResourcesInTable(resource
														.getName());
									} else if (resource instanceof Content) {
										final FileDownloadWindow downloadFileWindow = new FileDownloadWindow(
												(Content) resource);
										ResourceTableDisplay.this.getWindow()
												.addWindow(downloadFileWindow);
									}
								}
							});

					resourceLink.setWidth("100%");
					resourceLabel.addComponent(resourceLink);
					resourceLabel.setExpandRatio(resourceLink, 1.0f);
					resourceLabel.setWidth("100%");
					return resourceLabel;
				}
			});

			this.addGeneratedColumn("created", new Table.ColumnGenerator() {

				@Override
				public Object generateCell(final Table source,
						final Object itemId, final Object columnId) {
					final Resource resource = ResourceTableDisplay.this
							.getResource(itemId);
					return new Label(AppContext.formatDateTime(resource
							.getCreated().getTime()));
				}
			});

			this.addGeneratedColumn("size", new Table.ColumnGenerator() {

				@Override
				public Object generateCell(final Table source,
						final Object itemId, final Object columnId) {
					final Resource resource = ResourceTableDisplay.this
							.getResource(itemId);
					return new Label(Math.round(resource.getSize() / 1024) + "");
				}
			});

			this.setColumnExpandRatio("path", 1);
			this.setColumnWidth("uuid", 22);
			this.setColumnWidth("size", UIConstants.TABLE_S_LABEL_WIDTH);
			this.setColumnWidth("created", UIConstants.TABLE_DATE_TIME_WIDTH);
		}

		private Resource getResource(final Object itemId) {
			final Container container = this.getContainerDataSource();
			final BeanItem<Resource> item = (BeanItem<Resource>) container
					.getItem(itemId);
			return (item != null) ? item.getBean() : null;
		}
	}

	protected class FileDownloadWindow extends Window {
		private static final long serialVersionUID = 1L;
		private final Content content;

		public FileDownloadWindow(final Content content) {
			super(content.getName());
			this.setWidth("400px");
			this.center();

			this.content = content;
			this.constructBody();
		}

		private void constructBody() {
			final VerticalLayout layout = new VerticalLayout();
			final Embedded iconEmbed = new Embedded();
			iconEmbed.setSource(MyCollabResource
					.newResource("icons/page_white.png"));
			UiUtils.addComponent(layout, iconEmbed, Alignment.MIDDLE_CENTER);

			final GridFormLayoutHelper info = new GridFormLayoutHelper(1, 4,
					"100%", "80px", Alignment.MIDDLE_LEFT);
			info.getLayout().setWidth("100%");
			info.getLayout().setMargin(false);
			info.getLayout().setSpacing(false);

			if (this.content.getDescription() != null) {
				final Label desvalue = new Label(this.content.getDescription());
				info.addComponent(desvalue, "Description", 0, 0);
			}
			final Label author = new Label(this.content.getCreatedBy());
			info.addComponent(author, "Created by", 0, 1);

			final Label size = new Label(this.content.getSize() + "KB");
			info.addComponent(size, "Size", 0, 2);

			final Label dateCreate = new Label(
					AppContext.formatDate(this.content.getCreated().getTime()));
			info.addComponent(dateCreate, "Date created", 0, 3);

			layout.addComponent(info.getLayout());

			final HorizontalLayout buttonControls = new HorizontalLayout();
			buttonControls.setSpacing(true);
			final Button download = new Button("Download", new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					final com.vaadin.terminal.Resource downloadResource = StreamDownloadResourceFactory
							.getStreamResource(FileDownloadWindow.this.content
									.getPath());
					AppContext.getApplication().getMainWindow()
							.open(downloadResource, "_self");
				}
			});
			download.addStyleName(UIConstants.THEME_BLUE_LINK);
			UiUtils.addComponent(buttonControls, download,
					Alignment.MIDDLE_CENTER);

			final Button cancle = new Button("Cancel", new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					FileDownloadWindow.this.close();
				}
			});
			cancle.addStyleName(UIConstants.THEME_BLUE_LINK);

			UiUtils.addComponent(buttonControls, cancle,
					Alignment.MIDDLE_CENTER);
			UiUtils.addComponent(layout, buttonControls,
					Alignment.MIDDLE_CENTER);
			this.addComponent(layout);
		}
	}

	protected class RenameResourceWindow extends Window {
		private static final long serialVersionUID = 1L;
		private final Resource resource;
		private final ResourceService service;

		public RenameResourceWindow(final Resource resource,
				final ResourceService service) {
			super("Rename folder/file");
			this.center();
			this.setWidth("400px");

			this.service = service;
			this.resource = resource;
			this.constructBody();
		}

		private void constructBody() {
			final VerticalLayout layout = new VerticalLayout();
			final HorizontalLayout topRename = new HorizontalLayout();
			topRename.setSpacing(true);
			topRename.setMargin(true);

			final Label label = new Label("Enter new name: ");
			UiUtils.addComponent(topRename, label, Alignment.MIDDLE_LEFT);

			final TextField newName = new TextField();
			newName.setWidth("150px");
			UiUtils.addComponent(topRename, newName, Alignment.MIDDLE_LEFT);

			UiUtils.addComponent(layout, topRename, Alignment.MIDDLE_LEFT);

			final HorizontalLayout controlButton = new HorizontalLayout();
			controlButton.setSpacing(true);
			final Button save = new Button("Save", new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					final String oldPath = RenameResourceWindow.this.resource
							.getPath();
					final String parentPath = oldPath.substring(0,
							oldPath.lastIndexOf("/") + 1);
					final String newNameValue = (String) newName.getValue();
					final String newPath = parentPath + newNameValue;
					try {
						RenameResourceWindow.this.service.rename(oldPath,
								newPath);
						// reset layout
						FileDashboardViewImpl.this
								.displayResourcesInTable(FileDashboardViewImpl.this.baseFolder);

						// Set item caption for sub folder of base folder in
						// folderTree

						final List<Folder> childs = FileDashboardViewImpl.this.baseFolder
								.getChilds();
						for (final Folder folder : childs) {
							if (folder.getName().equals(
									RenameResourceWindow.this.resource
											.getName())) {
								folder.setPath(newPath);
								folderTree.removeItem(folder);
								folderTree.addItem(
										new Object[] {
												folder.getName(),
												AppContext
														.formatDate(folder
																.getCreated()
																.getTime()) },
										folder);
								folderTree.setParent(folder, baseFolder);
								folderTree.setItemCaption(folder, newNameValue);
							}
						}
						RenameResourceWindow.this.close();

					} catch (final ContentException e) {
						RenameResourceWindow.this.getWindow().showNotification(
								e.getMessage());
					}
				}
			});
			save.addStyleName(UIConstants.THEME_BLUE_LINK);

			UiUtils.addComponent(controlButton, save, Alignment.MIDDLE_CENTER);

			final Button cancel = new Button("Cancel", new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					RenameResourceWindow.this.close();
				}
			});
			cancel.addStyleName(UIConstants.THEME_BLUE_LINK);
			UiUtils.addComponent(controlButton, cancel, Alignment.MIDDLE_CENTER);
			UiUtils.addComponent(layout, controlButton, Alignment.MIDDLE_CENTER);
			this.addComponent(layout);
		}
	}

	class FileSearchPanel extends GenericSearchPanel<FileSearchCriteria> {
		private static final long serialVersionUID = 1L;
		private final SimpleProject project;
		protected FileSearchCriteria searchCriteria;
		private ComponentContainer menuBar = null;
		private HorizontalLayout basicSearchBody;

		public HorizontalLayout getBasicSearchBody() {
			return basicSearchBody;
		}

		public FileSearchPanel(final ComponentContainer menuBar) {
			this.project = (SimpleProject) AppContext.getVariable("project");
			this.menuBar = menuBar;
		}

		@Override
		public void attach() {
			super.attach();
			this.createBasicSearchLayout();
		}

		private void createBasicSearchLayout() {

			this.setCompositionRoot(new FileBasicSearchLayout());
		}

		private HorizontalLayout createSearchTopPanel() {
			final HorizontalLayout layout = new HorizontalLayout();
			layout.setWidth("100%");
			layout.setSpacing(true);

			final Embedded titleIcon = new Embedded();
			titleIcon.setSource(MyCollabResource
					.newResource("icons/24/project/file.png"));
			layout.addComponent(titleIcon);
			layout.setComponentAlignment(titleIcon, Alignment.MIDDLE_LEFT);

			final Label searchtitle = new Label("Files");
			searchtitle.setStyleName(Reindeer.LABEL_H2);
			layout.addComponent(searchtitle);
			layout.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);
			layout.setExpandRatio(searchtitle, 1.0f);

			if (this.menuBar != null) {
				UiUtils.addComponent(layout, this.menuBar,
						Alignment.MIDDLE_RIGHT);
			}

			return layout;
		}

		@SuppressWarnings("rawtypes")
		class FileBasicSearchLayout extends BasicSearchLayout {

			@SuppressWarnings("unchecked")
			public FileBasicSearchLayout() {
				super(FileSearchPanel.this);
			}

			private static final long serialVersionUID = 1L;
			private TextField nameField;
			private CheckBox myItemCheckbox;

			@Override
			public ComponentContainer constructHeader() {
				return FileSearchPanel.this.createSearchTopPanel();
			}

			@Override
			public ComponentContainer constructBody() {
				basicSearchBody = new HorizontalLayout();
				basicSearchBody.setSpacing(false);

				this.nameField = this.createSeachSupportTextField(
						new TextField(), "NameFieldOfBasicSearch");

				this.nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
				UiUtils.addComponent(basicSearchBody, this.nameField,
						Alignment.MIDDLE_CENTER);

				final Button searchBtn = new Button();
				searchBtn.setStyleName("search-icon-button");
				searchBtn.setIcon(MyCollabResource
						.newResource("icons/16/search_white.png"));
				searchBtn.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						EventBus.getInstance()
								.fireEvent(
										new ProjectContentEvent.Search(
												FileSearchPanel.this,
												new String[] {
														baseFolder.getPath(),
														(String) nameField
																.getValue() }));
					}
				});
				UiUtils.addComponent(basicSearchBody, searchBtn,
						Alignment.MIDDLE_LEFT);

				this.myItemCheckbox = new CheckBox("My Items");
				this.myItemCheckbox.setWidth("75px");
				UiUtils.addComponent(basicSearchBody, this.myItemCheckbox,
						Alignment.MIDDLE_CENTER);

				final Separator separator = new Separator();
				UiUtils.addComponent(basicSearchBody, separator,
						Alignment.MIDDLE_LEFT);

				final Button cancelBtn = new Button(
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.BUTTON_CLEAR));
				cancelBtn.setStyleName(UIConstants.THEME_LINK);
				cancelBtn.addStyleName("cancel-button");
				cancelBtn.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						FileBasicSearchLayout.this.nameField.setValue("");
					}
				});
				UiUtils.addComponent(basicSearchBody, cancelBtn,
						Alignment.MIDDLE_CENTER);
				return basicSearchBody;
			}

			@Override
			protected SearchCriteria fillupSearchCriteria() {
				FileSearchPanel.this.searchCriteria = new FileSearchCriteria();
				FileSearchPanel.this.searchCriteria
						.setProjectId(new NumberSearchField(SearchField.AND,
								FileSearchPanel.this.project.getId()));

				FileSearchPanel.this.searchCriteria
						.setFileName(new StringSearchField(this.nameField
								.getValue().toString().trim()));

				return FileSearchPanel.this.searchCriteria;
			}
		}
	}
}
