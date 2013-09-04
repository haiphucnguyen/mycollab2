package com.esofthead.mycollab.module.file.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.easyuploads.MultiFileUploadExt;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.peter.buttongroup.ButtonGroup;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.ecm.ContentException;
import com.esofthead.mycollab.module.ecm.StorageNames;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.ExternalContent;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.domain.ExternalFolder;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ExternalDriveService;
import com.esofthead.mycollab.module.ecm.service.ExternalResourceService;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.module.file.resource.StreamDownloadResourceFactory;
import com.esofthead.mycollab.module.file.view.components.FileDashboardComponent.AbstractMoveWindow;
import com.esofthead.mycollab.module.file.view.components.FileDownloadWindow;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AttachmentPanel;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.Hr;
import com.esofthead.mycollab.vaadin.ui.Separator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Container;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.CollapseEvent;
import com.vaadin.ui.Tree.ExpandEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

@ViewComponent
public class FileMainViewImpl extends AbstractView implements FileMainView {
	private static final long serialVersionUID = 1L;
	private static final String illegalFileNamePattern = "[<>:&/\\|?*&]";

	private Tree menuTree;

	private Folder baseFolder;
	private Folder rootECMFolder;
	private String rootPath;
	private String rootFolderName;

	private List<Resource> selectedResourcesList;
	private ItemResourceContainerLayout itemResourceContainerLayout;
	private MainBodyResourceLayout bodyResourceLayout;
	private FileActivityStreamComponent fileActivityStreamComponent;
	private Button switchViewBtn;
	private PagingResourceWapper pagingResourceWapper;

	private final ResourceService resourceService;
	private final ExternalDriveService externalDriveService;
	private final ExternalResourceService externalResourceService;
	private SettingConnectionDrive settingConnectionDrive;

	public FileMainViewImpl() {
		resourceService = AppContext.getSpringBean(ResourceService.class);
		externalDriveService = AppContext
				.getSpringBean(ExternalDriveService.class);
		externalResourceService = AppContext
				.getSpringBean(ExternalResourceService.class);

		this.setSpacing(true);
		this.setMargin(false);

		HorizontalLayout mainView = new HorizontalLayout();
		mainView.setSpacing(true);
		mainView.setMargin(true);
		mainView.setWidth("100%");

		final HorizontalLayout menuBarContainerHorizontalLayout = new HorizontalLayout();
		menuBarContainerHorizontalLayout.setMargin(true);

		final VerticalLayout menuLayout = new VerticalLayout();
		menuLayout.setSpacing(true);
		menuLayout.setWidth("250px");
		menuBarContainerHorizontalLayout.addComponent(menuLayout);

		HorizontalLayout topControlMenuLayoutWapper = new HorizontalLayout();
		topControlMenuLayoutWapper.setWidth("250px");
		topControlMenuLayoutWapper.addStyleName("border-box2-no-margin");

		ButtonGroup navButton = new ButtonGroup();
		navButton.addStyleName(UIConstants.THEME_GRAY_LINK);
		UiUtils.addComponent(topControlMenuLayoutWapper, navButton,
				Alignment.MIDDLE_RIGHT);

		switchViewBtn = new Button();
		switchViewBtn.setDescription("Event");
		switchViewBtn.setIcon(MyCollabResource
				.newResource("icons/16/ecm/event.png"));
		switchViewBtn.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (switchViewBtn.getDescription().equals("Event")) {
					switchViewBtn.setDescription("FileManagement");
					switchViewBtn.setIcon(MyCollabResource
							.newResource("icons/16/ecm/file_managerment.png"));
					gotoActionLogPage();
				} else if (switchViewBtn.getDescription().equals(
						"FileManagement")) {
					switchViewBtn.setDescription("Event");
					switchViewBtn.setIcon(MyCollabResource
							.newResource("icons/16/ecm/event.png"));
					gotoFileMainViewPage(baseFolder);
				}
			}
		});
		switchViewBtn.addStyleName("graybtn2");
		navButton.addButton(switchViewBtn);

		Button settingBtn = new Button();
		settingBtn.setIcon(MyCollabResource
				.newResource("icons/16/ecm/settings.png"));
		settingBtn.addListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				bodyResourceLayout.removeAllComponents();

				settingConnectionDrive = new SettingConnectionDrive(
						externalDriveService, externalResourceService);
				bodyResourceLayout.addComponent(settingConnectionDrive);
			}
		});
		settingBtn.addStyleName("graybtn2");
		navButton.addButton(settingBtn);

		final PopupButton linkBtn = new PopupButton();
		linkBtn.setIcon(MyCollabResource.newResource("icons/16/ecm/link.png"));
		linkBtn.setWidth("65px");
		final VerticalLayout filterBtnLayout = new VerticalLayout();
		filterBtnLayout.setMargin(true);
		filterBtnLayout.setSpacing(true);
		filterBtnLayout.setWidth("180px");

		HorizontalLayout connectDropboxLayout = new HorizontalLayout();
		connectDropboxLayout.setSpacing(true);

		final Embedded titleIcon = new Embedded();
		titleIcon.setSource(MyCollabResource
				.newResource("icons/16/ecm/dropbox.png"));
		connectDropboxLayout.addComponent(titleIcon);

		Button uploadDropboxBtn = new Button("Connect Dropbox",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						linkBtn.setPopupVisible(false);
						DropBoxOAuthWindow dropboxConnectWindow = new DropBoxOAuthWindow() {
							private static final long serialVersionUID = 1L;

							@Override
							protected void addExternalDrive(
									ExternalDrive externalDrive) {
								FileMainViewImpl.this.menuTree
										.expandItem(rootECMFolder);
							}
						};
						FileMainViewImpl.this.getWindow().addWindow(
								dropboxConnectWindow);
					}
				});
		uploadDropboxBtn.addStyleName("link");
		connectDropboxLayout.addComponent(uploadDropboxBtn);
		filterBtnLayout.addComponent(connectDropboxLayout);

		linkBtn.addComponent(filterBtnLayout);
		linkBtn.addStyleName("graybtn2");
		navButton.addButton(linkBtn);
		menuLayout.addComponent(topControlMenuLayoutWapper);

		this.menuTree = new Tree();
		this.menuTree.setMultiSelect(false);
		this.menuTree.setSelectable(true);
		this.menuTree.setImmediate(true);

		menuLayout.addComponent(this.menuTree);

		this.menuTree.addListener(new Tree.ExpandListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void nodeExpand(final ExpandEvent event) {
				Object object = event.getItemId();
				if (object instanceof Folder) {
					final Folder expandFolder = (Folder) event.getItemId();

					// if expand folder is root, will load external drives also
					if (rootPath.equals(expandFolder.getPath())) {
						List<ExternalDrive> externalDrives = externalDriveService
								.getExternalDrivesOfUser(AppContext
										.getUsername());
						for (ExternalDrive externalDrive : externalDrives) {
							ExternalFolder externalMapFolder = new ExternalFolder();
							externalMapFolder.setStorageName(externalDrive
									.getStoragename());
							externalMapFolder.setExternalDrive(externalDrive);
							externalMapFolder.setPath("/");
							externalMapFolder.setName(externalDrive
									.getFoldername());
							expandFolder.addChild(externalMapFolder);
							menuTree.addItem(externalMapFolder);

							menuTree.setItemIcon(
									externalMapFolder,
									MyCollabResource
											.newResource("icons/16/ecm/dropbox.png"));
							menuTree.setItemCaption(externalMapFolder,
									externalMapFolder.getName());
							menuTree.setParent(externalMapFolder, expandFolder);
						}
					}
					if (expandFolder instanceof ExternalFolder) {
						List<ExternalFolder> subFolders = externalResourceService
								.getSubFolders(((ExternalFolder) expandFolder)
										.getExternalDrive(), expandFolder
										.getPath());
						for (final Folder subFolder : subFolders) {
							expandFolder.addChild(subFolder);
							menuTree.addItem(subFolder);

							menuTree.setItemIcon(
									subFolder,
									MyCollabResource
											.newResource("icons/16/ecm/dropbox_subfolder.png"));
							menuTree.setItemCaption(subFolder,
									subFolder.getName());
							menuTree.setParent(subFolder, expandFolder);
						}
					} else {
						final List<Folder> subFolders = resourceService
								.getSubFolders(expandFolder.getPath());

						menuTree.setItemIcon(expandFolder, MyCollabResource
								.newResource("icons/16/ecm/folder_open.png"));

						if (subFolders != null) {
							for (final Folder subFolder : subFolders) {
								expandFolder.addChild(subFolder);
								menuTree.addItem(subFolder);

								menuTree.setItemIcon(
										subFolder,
										MyCollabResource
												.newResource("icons/16/ecm/folder_close.png"));
								menuTree.setItemCaption(subFolder,
										subFolder.getName());
								menuTree.setParent(subFolder, expandFolder);
							}
						}
					}
				}
			}
		});

		this.menuTree.addListener(new Tree.CollapseListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void nodeCollapse(final CollapseEvent event) {
				final Folder collapseFolder = (Folder) event.getItemId();
				Container dataSource = menuTree.getContainerDataSource();
				final Object[] dataCollectionArray = dataSource.getItemIds()
						.toArray();
				for (Object id : dataCollectionArray) {
					Folder folder = (Folder) id;
					if (folder.getPath().contains(collapseFolder.getPath())
							&& !folder.getPath().equals(
									collapseFolder.getPath())
							|| collapseFolder.getPath().equals(rootPath)) {
						if (!folder.getPath().equals(rootPath)) {
							if (collapseFolder instanceof ExternalFolder
									&& folder instanceof ExternalFolder) {
								if (((ExternalFolder) folder)
										.getExternalDrive()
										.getAccesstoken()
										.equals(((ExternalFolder) folder)
												.getExternalDrive()
												.getAccesstoken()))
									dataSource.removeItem(folder);
							} else if (!(collapseFolder instanceof ExternalFolder)) {
								dataSource.removeItem(folder);
							}
						}
					}
				}
				FileMainViewImpl.this.menuTree
						.setContainerDataSource(dataSource);
			}
		});

		this.menuTree.addListener(new ItemClickEvent.ItemClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(final ItemClickEvent event) {
				final Folder item = (Folder) event.getItemId();
				gotoFileMainViewPage(item);
			}
		});

		mainView.addComponent(menuBarContainerHorizontalLayout);
		mainView.setComponentAlignment(menuBarContainerHorizontalLayout,
				Alignment.TOP_LEFT);

		Separator separator = new Separator();
		separator.setWidth(Sizeable.SIZE_UNDEFINED, 0);
		separator.setHeight("100%");
		mainView.addComponent(separator);
		mainView.setComponentAlignment(separator, Alignment.TOP_LEFT);

		// here for MainBodyResourceLayout class
		bodyResourceLayout = new MainBodyResourceLayout();
		bodyResourceLayout.setSpacing(true);

		mainView.addComponent(bodyResourceLayout);
		mainView.setComponentAlignment(bodyResourceLayout, Alignment.TOP_LEFT);
		mainView.setExpandRatio(bodyResourceLayout, 1.0f);

		this.addComponent(mainView);
		this.setComponentAlignment(mainView, Alignment.MIDDLE_CENTER);

	}

	public boolean checkValidFolderName(String value) {
		Pattern pattern = Pattern.compile(illegalFileNamePattern);
		Matcher matcher = pattern.matcher(value);
		return matcher.find();
	}

	private void gotoActionLogPage() {
		bodyResourceLayout.removeAllComponents();
		if (fileActivityStreamComponent == null) {
			fileActivityStreamComponent = new FileActivityStreamComponent();
			// and Folder - handeler
			fileActivityStreamComponent
					.addSelectedHandlerToPageList(new SearchHandler<FileSearchCriteria>() {
						@Override
						public void onSearch(FileSearchCriteria criteria) {
							Resource res = resourceService.getResource(criteria
									.getBaseFolder());
							if (res == null) {
								gotoEnclosingFoder(res, criteria);
								return;
							}
							gotoFileMainViewPage((Folder) res);
						}
					});
			// add File - handeler
			fileActivityStreamComponent
					.addSelectedHandlerToPageList(new SearchHandler<FileSearchCriteria>() {
						@Override
						public void onSearch(FileSearchCriteria criteria) {
							Resource res = resourceService.getResource(criteria
									.getBaseFolder());
							if (res == null) {
								gotoEnclosingFoder(res, criteria);
								return;
							}
							FileMainViewImpl.this.getWindow().addWindow(
									new FileDownloadWindow((Content) res));
						}
					});
			// add handele
			fileActivityStreamComponent
					.addSelectedHandlerToPageList(new SearchHandler<FileSearchCriteria>() {
						@Override
						public void onSearch(FileSearchCriteria criteria) {
						}
					});
		}
		bodyResourceLayout.addComponent(fileActivityStreamComponent);
		fileActivityStreamComponent.showContentFeeds();
	}

	private void gotoEnclosingFoder(Resource res, FileSearchCriteria criteria) {
		String path = criteria.getBaseFolder().substring(0,
				criteria.getBaseFolder().lastIndexOf("/"));
		Resource parentFolder = resourceService.getResource(path);
		if (parentFolder != null) {
			gotoFileMainViewPage((Folder) parentFolder);
		} else {
			String message = (res instanceof Folder) ? "Folder's location has moved, Please review activity-logs for more informations."
					: "File's location has moved, Please review activity-logs for more informations.";
			FileMainViewImpl.this.getWindow().showNotification(message);
		}
	}

	private void gotoFileMainViewPage(Folder baseFolder) {
		this.baseFolder = baseFolder;

		bodyResourceLayout.removeAllComponents();

		bodyResourceLayout.setSpacing(true);
		bodyResourceLayout.addComponent(bodyResourceLayout.filterPanel);
		bodyResourceLayout.addComponent(bodyResourceLayout.fileBreadCrumb);
		bodyResourceLayout.addComponent(bodyResourceLayout.controllGroupBtn);
		bodyResourceLayout.addComponent(itemResourceContainerLayout);

		bodyResourceLayout.fileBreadCrumb.gotoFolder(baseFolder);

		itemResourceContainerLayout.constructBody(baseFolder);
		switchViewBtn.setDescription("Event");
		switchViewBtn.setIcon(MyCollabResource
				.newResource("icons/16/ecm/event.png"));
	}

	public void displayResources(String rootPath, String rootFolderName) {
		this.rootFolderName = rootFolderName;
		this.baseFolder = new Folder();
		this.baseFolder.setPath(rootPath);
		this.rootECMFolder = this.baseFolder;

		this.menuTree.removeAllItems();
		this.menuTree.addItem(this.baseFolder);
		this.menuTree.setItemCaption(this.baseFolder, rootFolderName);
		this.menuTree.setItemIcon(this.baseFolder,
				MyCollabResource.newResource("icons/16/ecm/folder_close.png"));
		this.menuTree.collapseItem(this.baseFolder);

		bodyResourceLayout.fileBreadCrumb
				.addSearchHandler(new SearchHandler<FileSearchCriteria>() {
					@Override
					public void onSearch(FileSearchCriteria criteria) {
						Folder selectedFolder = null;
						if (criteria.getStorageName() != null
								&& criteria.getStorageName().equals(
										StorageNames.DROPBOX)) {
							selectedFolder = (Folder) externalResourceService
									.getcurrentResourceByPath(
											criteria.getExternalDrive(),
											criteria.getBaseFolder());
						} else {
							selectedFolder = (Folder) FileMainViewImpl.this.resourceService
									.getResource(criteria.getBaseFolder());
						}
						FileMainViewImpl.this.itemResourceContainerLayout
								.constructBody(selectedFolder);
						bodyResourceLayout.fileBreadCrumb
								.gotoFolder(selectedFolder);
						FileMainViewImpl.this.baseFolder = selectedFolder;
						bodyResourceLayout.fileBreadCrumb
								.setCurrentBreadCrumbFolder(selectedFolder);
					}
				});
	}

	@Override
	public void display() {
		displayResources(rootPath, "Documents");
	}

	class FilterPanel extends GenericSearchPanel<FileSearchCriteria> {
		private static final long serialVersionUID = 1L;
		protected FileSearchCriteria searchCriteria;
		private HorizontalLayout basicSearchBody;

		public HorizontalLayout getBasicSearchBody() {
			return basicSearchBody;
		}

		public FilterPanel() {
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
					.newResource("icons/24/ecm/document_preview.png"));
			layout.addComponent(titleIcon);
			layout.setComponentAlignment(titleIcon, Alignment.MIDDLE_LEFT);

			final Label searchtitle = new Label("Files");
			searchtitle.setStyleName(Reindeer.LABEL_H2);
			layout.addComponent(searchtitle);
			layout.setComponentAlignment(searchtitle, Alignment.MIDDLE_LEFT);
			layout.setExpandRatio(searchtitle, 1.0f);
			return layout;
		}

		@SuppressWarnings("rawtypes")
		class FileBasicSearchLayout extends BasicSearchLayout {

			@SuppressWarnings("unchecked")
			public FileBasicSearchLayout() {
				super(FilterPanel.this);
			}

			private static final long serialVersionUID = 1L;
			private TextField nameField;
			private CheckBox myItemCheckbox;

			@Override
			public ComponentContainer constructHeader() {
				return FilterPanel.this.createSearchTopPanel();
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
						List<Resource> lstResource = FileMainViewImpl.this.resourceService
								.searchResourcesByName(
										FileMainViewImpl.this.rootPath,
										nameField.getValue().toString().trim());
						if (lstResource != null && lstResource.size() > 0) {
							itemResourceContainerLayout
									.constructBodySearchActionResult(lstResource);
							bodyResourceLayout.fileBreadCrumb.initBreadcrumb();
							bodyResourceLayout.fileBreadCrumb
									.setCurrentBreadCrumbFolder((Folder) FileMainViewImpl.this.resourceService
											.getResource(rootPath));
						} else {
							FileMainViewImpl.this.getWindow().showNotification(
									"Searching has no any results.");
						}
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
				return null;
			}
		}
	}

	private class ItemResourceContainerLayout extends VerticalLayout {
		private static final long serialVersionUID = 1L;
		private final List<CheckBox> listAllCheckBox;
		private VerticalLayout mainLayout;
		private boolean isSearchAction = false;

		public List<CheckBox> getListAllCheckBox() {
			return listAllCheckBox;
		}

		public ItemResourceContainerLayout(Folder folder,
				ResourceService resourceService) {
			selectedResourcesList = new ArrayList<Resource>();
			listAllCheckBox = new ArrayList<CheckBox>();
			this.setMargin(true);
			constructBody(folder);
		}

		private void constructBody(Folder currentFolder) {
			isSearchAction = false;
			if (mainLayout != null) {
				this.removeAllComponents();
			}

			mainLayout = new VerticalLayout();
			mainLayout.setSpacing(false);

			List<Resource> lstResource;
			if (currentFolder instanceof ExternalFolder) {
				lstResource = externalResourceService.getResources(
						((ExternalFolder) currentFolder).getExternalDrive(),
						currentFolder.getPath());
			} else {
				lstResource = resourceService.getResources(currentFolder
						.getPath());
			}
			if (currentFolder.getPath().equals(rootPath)) {
				List<ExternalDrive> lst = externalDriveService
						.getExternalDrivesOfUser(AppContext.getUsername());
				if (lst != null && lst.size() > 0) {
					for (ExternalDrive drive : lst) {
						if (drive.getStoragename().equals(StorageNames.DROPBOX)) {
							Resource res = externalResourceService
									.getcurrentResourceByPath(drive, "/");
							res.setName(drive.getFoldername());
							lstResource.add(0, res);
						}
					}
				}
			}
			this.addComponent(new Hr());
			if (lstResource != null && lstResource.size() > 0) {
				if (lstResource.size() <= PagingResourceWapper.pageItemNum) {
					for (Resource res : lstResource) {
						mainLayout.addComponent(constructOneItemResourceLayout(
								res, false));
						mainLayout.addComponent(new Hr());
					}
				} else if (lstResource.size() > PagingResourceWapper.pageItemNum) {
					for (int i = 0; i < PagingResourceWapper.pageItemNum; i++) {
						Resource res = lstResource.get(i);
						mainLayout.addComponent(constructOneItemResourceLayout(
								res, false));
						mainLayout.addComponent(new Hr());
					}
					pagingResourceWapper = new PagingResourceWapper(lstResource);
					pagingResourceWapper.setWidth("100%");
					mainLayout.addComponent(pagingResourceWapper);
					mainLayout.setComponentAlignment(pagingResourceWapper,
							Alignment.MIDDLE_CENTER);
				}
			}
			this.addComponent(mainLayout);
		}

		private void constructBodySearchActionResult(List<Resource> lstResource) {
			isSearchAction = true;
			if (mainLayout != null) {
				this.removeAllComponents();
			}
			if (selectedResourcesList != null
					&& selectedResourcesList.size() > 0)
				selectedResourcesList.clear();
			if (listAllCheckBox != null && listAllCheckBox.size() > 0) {
				listAllCheckBox.clear();
			}
			mainLayout = new VerticalLayout();
			mainLayout.setSpacing(false);

			HorizontalLayout messageSearchLayout = new HorizontalLayout();
			messageSearchLayout.setWidth("100%");
			Label titleLabel = new Label("Search result: ");
			titleLabel.setWidth("115px");
			titleLabel.addStyleName("h3");
			messageSearchLayout.addComponent(titleLabel);

			Label nameLabel = new Label("Name");
			nameLabel.addStyleName("h3");
			nameLabel.setWidth("350px");
			messageSearchLayout.addComponent(nameLabel);
			Label pathLabel = new Label("Path");
			pathLabel.addStyleName("h3");
			messageSearchLayout.addComponent(pathLabel);
			messageSearchLayout.setExpandRatio(pathLabel, 1.0f);

			this.addComponent(messageSearchLayout);
			this.addComponent(new Hr());
			if (lstResource != null && lstResource.size() > 0) {
				for (Resource res : lstResource) {
					mainLayout.addComponent(constructOneItemResourceLayout(res,
							true));
					mainLayout.addComponent(new Hr());
				}
			}
			this.addComponent(mainLayout);
		}

		private HorizontalLayout constructOneItemResourceLayout(
				final Resource res, final boolean isSearchAction) {
			final HorizontalLayout layout = new HorizontalLayout();
			layout.setWidth("100%");
			layout.setSpacing(true);
			layout.addStyleName("resourceItem");
			layout.setHeight("44px");

			final CheckBox checkbox = new CheckBox();
			checkbox.setWidth("25px");
			checkbox.setImmediate(true);
			checkbox.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			listAllCheckBox.add(checkbox);

			checkbox.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(ValueChangeEvent event) {
					if ((Boolean) checkbox.getValue()) {
						selectedResourcesList.add(res);
					} else {
						selectedResourcesList.remove(res);
					}
				}
			});
			layout.addComponent(checkbox);
			layout.setComponentAlignment(checkbox, Alignment.MIDDLE_LEFT);

			layout.addListener(new LayoutClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void layoutClick(LayoutClickEvent event) {
					if ((Boolean) checkbox.getValue())
						checkbox.setValue(false);
					else
						checkbox.setValue(true);
				}
			});

			CssLayout resIconWapper = new CssLayout();
			final Embedded resourceIcon = new Embedded();
			if (res instanceof Folder)
				if (res instanceof ExternalFolder)
					resourceIcon.setSource(MyCollabResource
							.newResource("icons/32/ecm/folder_dropbox.png"));
				else
					resourceIcon.setSource(MyCollabResource
							.newResource("icons/32/ecm/folder.png"));
			else {
				if (res instanceof ExternalContent)
					resourceIcon.setSource(MyCollabResource
							.newResource("icons/32/ecm/file_dropbox.png"));
				else
					resourceIcon.setSource(MyCollabResource
							.newResource("icons/32/ecm/file.png"));
			}

			resIconWapper.setWidth("40px");
			resIconWapper.addComponent(resourceIcon);

			layout.addComponent(resIconWapper);
			layout.setComponentAlignment(resIconWapper, Alignment.MIDDLE_LEFT);

			VerticalLayout informationLayout = new VerticalLayout();
			informationLayout.setWidth("345px");

			Button resourceLinkBtn = new Button(res.getName(),
					new ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							if (res instanceof Folder) {
								FileMainViewImpl.this.baseFolder = (Folder) res;
								itemResourceContainerLayout
										.constructBody((Folder) res);
								bodyResourceLayout.fileBreadCrumb
										.gotoFolder((Folder) res);
							} else {
								FileDownloadWindow fileDownloadWindow = new FileDownloadWindow(
										(Content) res);
								FileMainViewImpl.this.getWindow().addWindow(
										fileDownloadWindow);
							}
						}
					});
			resourceLinkBtn.addStyleName("link");
			resourceLinkBtn.addStyleName("h3");
			informationLayout.addComponent(resourceLinkBtn);

			HorizontalLayout moreInfoAboutResLayout = new HorizontalLayout();

			// If resource is dropbox resource then we can not define the
			// created user so we do not need to display, then we assume the
			// current user is created user
			if (res.getCreatedBy() == null
					|| res.getCreatedBy().trim().equals("")) {
				Label usernameLbl = new Label(AppContext.getUsername());
				usernameLbl.addStyleName("grayLabel");
				moreInfoAboutResLayout.addComponent(usernameLbl);
			} else {
				Label usernameLbl = new Label(res.getCreatedBy());
				usernameLbl.addStyleName("grayLabel");
				moreInfoAboutResLayout.addComponent(usernameLbl);
			}
			moreInfoAboutResLayout.addComponent(new Separator());

			// If resource is dropbox resource then we can not define the
			// created date so we do not need to display\
			if (res.getCreated() != null) {
				Label createdTimeLbl = new Label((AppContext.formatDate(res
						.getCreated().getTime())));
				createdTimeLbl.addStyleName("grayLabel");
				moreInfoAboutResLayout.addComponent(createdTimeLbl);
			} else {
				Label createdTimeLbl = new Label("Undefined");
				createdTimeLbl.addStyleName("grayLabel");
				moreInfoAboutResLayout.addComponent(createdTimeLbl);
			}

			if (res instanceof Content) {
				moreInfoAboutResLayout.addComponent(new Separator());
				Double size = res.getSize();
				DecimalFormat df = new DecimalFormat("#");
				df.setRoundingMode(RoundingMode.HALF_UP);

				Label lbl = new Label(df.format(size) + " KB");
				lbl.addStyleName("grayLabel");
				moreInfoAboutResLayout.addComponent(lbl);
			}
			informationLayout.addComponent(moreInfoAboutResLayout);

			layout.addComponent(informationLayout);
			layout.setComponentAlignment(informationLayout,
					Alignment.MIDDLE_LEFT);

			if (isSearchAction) {
				HorizontalLayout resourcePathLayout = constructBreadcrumbPathLayout(res);
				layout.addComponent(resourcePathLayout);
				layout.setExpandRatio(resourcePathLayout, 1.0f);
			} else {
				layout.setExpandRatio(informationLayout, 1.0f);
			}

			final PopupButton resourceSettingPopupBtn = new PopupButton();
			resourceSettingPopupBtn.setWidth("18px");
			final VerticalLayout filterBtnLayout = new VerticalLayout();

			final Button renameBtn = new Button("Rename",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							resourceSettingPopupBtn.setPopupVisible(false);
							final RenameResourceWindow renameWindow = new RenameResourceWindow(
									res, resourceService);
							FileMainViewImpl.this.getWindow().addWindow(
									renameWindow);
						}
					});
			renameBtn.addStyleName("link");
			filterBtnLayout.addComponent(renameBtn);

			final Button downloadBtn = new Button("Download",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							final com.vaadin.terminal.Resource downloadResource;
							if (res instanceof Folder) {
								if (res instanceof ExternalFolder) {
									// downloadResource =
									// StreamDownloadResourceFactory.getStreamDropboxResource(res);
									FileMainViewImpl.this
											.getWindow()
											.showNotification(
													"Sorry for this inconvenience! This function will update soon. Best regard.");
									return;
								} else
									downloadResource = StreamDownloadResourceFactory.getStreamFolderResource(
											new String[] { res.getPath() },
											false);
							} else {
								if (res instanceof ExternalContent) {
									downloadResource = StreamDownloadResourceFactory
											.getStreamDropboxResource(res);
								} else
									downloadResource = StreamDownloadResourceFactory
											.getStreamResource(res.getPath());
							}
							AppContext.getApplication().getMainWindow()
									.open(downloadResource, "_blank");
						}
					});
			downloadBtn.addStyleName("link");
			filterBtnLayout.addComponent(downloadBtn);

			final Button moveBtn = new Button("Move to",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							MoveResourceWindow moveResourceWindow = new MoveResourceWindow(
									res, FileMainViewImpl.this.resourceService);
							FileMainViewImpl.this.getWindow().addWindow(
									moveResourceWindow);
						}
					});
			moveBtn.addStyleName("link");
			filterBtnLayout.addComponent(moveBtn);

			final Button deleteBtn = new Button("Delete",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							selectedResourcesList.clear();
							selectedResourcesList.add(res);

							bodyResourceLayout.deleteResourceAction();
						}
					});
			deleteBtn.addStyleName("link");
			filterBtnLayout.addComponent(deleteBtn);
			// ------------------------------------------------------------------------

			filterBtnLayout.setMargin(true);
			filterBtnLayout.setSpacing(true);
			filterBtnLayout.setWidth("100px");
			resourceSettingPopupBtn.setIcon(MyCollabResource
					.newResource("icons/16/item_settings.png"));
			resourceSettingPopupBtn.setStyleName("link");
			resourceSettingPopupBtn.addComponent(filterBtnLayout);

			layout.addComponent(resourceSettingPopupBtn);
			layout.setComponentAlignment(resourceSettingPopupBtn,
					Alignment.MIDDLE_RIGHT);
			return layout;
		}

		private HorizontalLayout constructBreadcrumbPathLayout(
				final Resource res) {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);

			String parentFolderPath = resourceService.getParentFolder(
					res.getPath()).getPath();
			StringBuffer parentFolderPathStrBuffer;
			if (parentFolderPath.equals(rootPath)) {
				parentFolderPathStrBuffer = new StringBuffer(rootFolderName);
			} else
				parentFolderPathStrBuffer = new StringBuffer(rootFolderName
						+ parentFolderPath.substring(parentFolderPath.indexOf(
								"/", 2)));
			if (parentFolderPathStrBuffer.toString().split("/").length > 6) {
				String[] parentFolderPathArray = parentFolderPath.split("/");
				parentFolderPathStrBuffer = new StringBuffer("");
				parentFolderPathStrBuffer
						.append(rootFolderName)
						.append("/")
						.append((parentFolderPathArray[2].length() > 25) ? parentFolderPathArray[2]
								.substring(0, 10) + "..."
								: parentFolderPathArray[2])
						.append("/")
						.append((parentFolderPathArray[3].length() > 25) ? parentFolderPathArray[3]
								.substring(0, 10) + "..."
								: parentFolderPathArray[3])
						.append("/")
						.append("...")
						.append("/")
						.append((parentFolderPathArray[parentFolderPathArray.length - 2]
								.length() > 25) ? parentFolderPathArray[parentFolderPathArray.length - 2]
								.substring(0, 10) + "..."
								: parentFolderPathArray[parentFolderPathArray.length - 2])
						.append("/")
						.append((parentFolderPathArray[parentFolderPathArray.length - 1]
								.length() > 25) ? parentFolderPathArray[parentFolderPathArray.length - 1]
								.substring(0, 10) + "..."
								: parentFolderPathArray[parentFolderPathArray.length - 1]);
			}
			Label pathLabel = new Label(parentFolderPathStrBuffer.toString());
			pathLabel.addStyleName("h3");
			UiUtils.addComponent(layout, pathLabel, Alignment.MIDDLE_CENTER);

			Button toContainFolder = new Button();
			toContainFolder.setIcon(MyCollabResource
					.newResource("icons/48/ecm/folder_arrow_right.png"));
			toContainFolder.setDescription("Go to folder");
			toContainFolder.addListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					Resource containFolder = FileMainViewImpl.this.resourceService
							.getParentFolder(res.getPath());
					FileMainViewImpl.this.menuTree
							.expandItem((Folder) containFolder);
					FileMainViewImpl.this.itemResourceContainerLayout
							.constructBody((Folder) containFolder);
					FileMainViewImpl.this.baseFolder = (Folder) containFolder;
					bodyResourceLayout.fileBreadCrumb
							.gotoFolder(FileMainViewImpl.this.baseFolder);
				}
			});
			toContainFolder.addStyleName("link");
			UiUtils.addComponent(layout, toContainFolder,
					Alignment.MIDDLE_CENTER);

			return layout;
		}
	}

	protected class MoveResourceWindow extends AbstractMoveWindow {
		private static final long serialVersionUID = 1L;

		public MoveResourceWindow(Resource resource,
				ResourceService resourceService) {
			super(resource, resourceService);
		}

		public MoveResourceWindow(List<Resource> lstResource,
				ResourceService resourceService) {
			super(lstResource, resourceService);
		}

		@Override
		public void displayAfterMoveSuccess(Folder folder, boolean checking) {
			FileMainViewImpl.this.itemResourceContainerLayout
					.constructBody(FileMainViewImpl.this.baseFolder);
			if (!checking)
				FileMainViewImpl.this.getWindow().showNotification(
						"Move asset(s) successfully.");
			else
				FileMainViewImpl.this
						.getWindow()
						.showNotification(
								"Move finish, some items can't move to destination. Please check duplicated file-name and try again.");
			FileMainViewImpl.this.selectedResourcesList = new ArrayList<Resource>();

			Container dataSource = FileMainViewImpl.this.menuTree
					.getContainerDataSource();
			Object[] dataSourceArray = dataSource.getItemIds().toArray();

			for (Object item : dataSourceArray) {
				if (((Folder) item).getPath().equals(folder.getPath())) {
					FileMainViewImpl.this.menuTree.collapseItem((Folder) item);
					FileMainViewImpl.this.menuTree.expandItem((Folder) item);
					break;
				}
			}
			for (Object item : dataSourceArray) {
				if (((Folder) item).getPath().equals(
						FileMainViewImpl.this.baseFolder.getPath())) {
					FileMainViewImpl.this.menuTree.collapseItem((Folder) item);
					FileMainViewImpl.this.menuTree.expandItem((Folder) item);
					break;
				}
			}
			if ((Boolean) bodyResourceLayout.selectAllBtn.getValue())
				bodyResourceLayout.selectAllBtn.click();
		}

		@Override
		protected void displayFiles() {
			this.folderTree.removeAllItems();

			this.baseFolder = new Folder();
			baseFolder.setPath(FileMainViewImpl.this.rootPath);
			this.folderTree
					.addItem(new Object[] {
							FileMainViewImpl.this.rootFolderName, "" },
							this.baseFolder);
			this.folderTree.setItemCaption(this.baseFolder,
					FileMainViewImpl.this.rootFolderName);

			this.folderTree.setCollapsed(this.baseFolder, false);
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
					String parentPath = oldPath.substring(0,
							oldPath.lastIndexOf("/") + 1);
					if (resource instanceof ExternalFolder
							|| resource instanceof ExternalContent) {
						parentPath = (parentPath.length() == 0) ? "/"
								: parentPath;
					}
					String newNameValue = (String) newName.getValue();
					String newPath = parentPath + newNameValue;

					if (resource instanceof ExternalFolder
							|| resource instanceof ExternalContent) {
						if (resource instanceof ExternalFolder)
							FileMainViewImpl.this.externalResourceService
									.rename(((ExternalFolder) resource)
											.getExternalDrive(), oldPath,
											newPath);
						else
							FileMainViewImpl.this.externalResourceService
									.rename(((ExternalContent) resource)
											.getExternalDrive(), oldPath,
											newPath);
					} else {
						try {
							RenameResourceWindow.this.service.rename(oldPath,
									newPath, AppContext.getUsername());
							final List<Folder> childs = FileMainViewImpl.this.baseFolder
									.getChilds();
							for (final Folder folder : childs) {
								if (folder.getName().equals(
										RenameResourceWindow.this.resource
												.getName())) {
									menuTree.removeItem(folder);
									folder.setPath(newPath);
									menuTree.addItem(folder);
									menuTree.setParent(folder, baseFolder);
									menuTree.setItemCaption(folder,
											newNameValue);
								}
							}
						} catch (final ContentException e) {
							RenameResourceWindow.this.getWindow()
									.showNotification(e.getMessage());
						}
					}
					FileMainViewImpl.this.itemResourceContainerLayout
							.constructBody(FileMainViewImpl.this.baseFolder);

					if ((resource instanceof ExternalFolder || resource instanceof ExternalContent)
							&& pagingResourceWapper.getCurrentPage() != 1)
						pagingResourceWapper.pageChange(pagingResourceWapper
								.getCurrentPage());
					RenameResourceWindow.this.close();
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
								Pattern pattern = Pattern
										.compile(illegalFileNamePattern);
								Matcher matcher = pattern.matcher(folderVal);
								if (matcher.find()) {
									FileMainViewImpl.this
											.getWindow()
											.showNotification(
													"Please enter valid folder name except any follow characters : <>:&/\\|?*&");
									return;
								}

								String baseFolderPath = (FileMainViewImpl.this.baseFolder == null) ? FileMainViewImpl.this.rootPath
										: FileMainViewImpl.this.baseFolder
												.getPath();
								if (itemResourceContainerLayout.isSearchAction) {
									baseFolderPath = rootPath;
									baseFolder = (Folder) resourceService
											.getResource(rootPath);
								}
								Folder newFolder = null;
								if (FileMainViewImpl.this.baseFolder instanceof ExternalFolder) {
									String path = FileMainViewImpl.this.baseFolder
											.getPath() + "/" + folderVal;
									newFolder = FileMainViewImpl.this.externalResourceService
											.createFolder(
													((ExternalFolder) FileMainViewImpl.this.baseFolder)
															.getExternalDrive(),
													path);
								} else {
									newFolder = FileMainViewImpl.this.resourceService
											.createNewFolder(baseFolderPath,
													folderVal,
													AppContext.getUsername());
								}
								if (!FileMainViewImpl.this.menuTree
										.isExpanded(FileMainViewImpl.this.baseFolder)) {
									FileMainViewImpl.this.menuTree
											.expandItem(FileMainViewImpl.this.baseFolder);
								} else {
									FileMainViewImpl.this.menuTree
											.addItem(newFolder);
									FileMainViewImpl.this.menuTree
											.setItemCaption(newFolder,
													newFolder.getName());
									FileMainViewImpl.this.menuTree.setParent(
											newFolder,
											FileMainViewImpl.this.baseFolder);
									FileMainViewImpl.this.menuTree.setItemIcon(
											newFolder,
											MyCollabResource
													.newResource("icons/16/ecm/folder_close.png"));
								}
								FileMainViewImpl.this.itemResourceContainerLayout
										.constructBody(FileMainViewImpl.this.baseFolder);
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

	private class MultiUploadContentWindow extends Window {
		private static final long serialVersionUID = 1L;

		private final GridFormLayoutHelper layoutHelper;
		private final MultiFileUploadExt multiFileUploadExt;

		public MultiUploadContentWindow() {
			super("Multi Upload Content");
			this.setWidth("500px");
			((VerticalLayout) this.getContent()).setMargin(false, false, true,
					false);
			this.setModal(true);
			final AttachmentPanel attachments = new AttachmentPanel();

			this.layoutHelper = new GridFormLayoutHelper(1, 2, "100%", "167px",
					Alignment.MIDDLE_LEFT);

			multiFileUploadExt = new MultiFileUploadExt(attachments);
			multiFileUploadExt.addComponent(attachments);
			multiFileUploadExt.setWidth("100%");

			this.layoutHelper.addComponent(multiFileUploadExt, "File", 0, 0);

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
							List<File> lstFileAttachments = attachments
									.getListFile();
							if (lstFileAttachments != null
									&& lstFileAttachments.size() > 0) {
								for (File file : lstFileAttachments) {
									try {
										if (file.getName() != null
												&& file.getName().length() > 0) {
											Pattern pattern = Pattern
													.compile(illegalFileNamePattern);
											Matcher matcher = pattern
													.matcher(file.getName());
											if (matcher.find()) {
												FileMainViewImpl.this
														.getWindow()
														.showNotification(
																"Please upload valid file-name except any follow characters : <>:&/\\|?*&");
												return;
											}
										}
										final Content content = new Content();
										content.setPath(FileMainViewImpl.this.baseFolder
												.getPath()
												+ "/"
												+ file.getName());
										double sizeInByte = file.length();
										content.setSize(sizeInByte / 1024);
										FileInputStream fileInputStream = new FileInputStream(
												file);

										if (FileMainViewImpl.this.baseFolder instanceof ExternalFolder) {
											FileMainViewImpl.this.externalResourceService
													.saveContent(
															((ExternalFolder) FileMainViewImpl.this.baseFolder)
																	.getExternalDrive(),
															content,
															fileInputStream);
										} else
											FileMainViewImpl.this.resourceService
													.saveContent(
															content,
															AppContext
																	.getUsername(),
															fileInputStream);
									} catch (IOException e) {
										throw new MyCollabException(e);
									}
								}
								FileMainViewImpl.this.itemResourceContainerLayout
										.constructBody(FileMainViewImpl.this.baseFolder);
								MultiUploadContentWindow.this.close();
								FileMainViewImpl.this.getWindow()
										.showNotification(
												"Upload successfully.");
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
							MultiUploadContentWindow.this.close();
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

	private class MainBodyResourceLayout extends VerticalLayout {
		private static final long serialVersionUID = 1L;
		private HorizontalLayout controllGroupBtn;
		private Button deleteBtn;
		private Button selectAllBtn;
		private FileBreadcrumb fileBreadCrumb;
		private FilterPanel filterPanel;

		public MainBodyResourceLayout() {
			VerticalLayout mainBodyLayout = new VerticalLayout();
			mainBodyLayout.setSpacing(true);
			mainBodyLayout.addStyleName("box-no-border-left");

			filterPanel = new FilterPanel();
			mainBodyLayout.addComponent(filterPanel);

			// file bread Crum ---------------------
			fileBreadCrumb = new FileBreadcrumb();
			fileBreadCrumb.setCurrentBreadCrumbFolder(baseFolder);
			mainBodyLayout.addComponent(fileBreadCrumb);

			// Construct controllGroupBtn
			controllGroupBtn = new HorizontalLayout();
			controllGroupBtn.setMargin(true, false, false, true);
			controllGroupBtn.setSpacing(true);

			selectAllBtn = new Button();
			selectAllBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
			selectAllBtn.setIcon(MyCollabResource
					.newResource("icons/16/checkbox_empty.png"));
			selectAllBtn.setValue(false);
			selectAllBtn.setImmediate(true);

			selectAllBtn.addListener(new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					if (!(Boolean) selectAllBtn.getValue()) {
						selectAllBtn.setIcon(MyCollabResource
								.newResource("icons/16/checkbox.png"));
						selectAllBtn.setValue(true);
						if (itemResourceContainerLayout.getListAllCheckBox() != null) {
							for (CheckBox cb : itemResourceContainerLayout
									.getListAllCheckBox()) {
								if (!(Boolean) cb.getValue())
									cb.setValue(true);
							}
						}
					} else {
						selectAllBtn.setValue(false);
						selectAllBtn.setIcon(MyCollabResource
								.newResource("icons/16/checkbox_empty.png"));
						if (itemResourceContainerLayout.getListAllCheckBox() != null) {
							for (CheckBox cb : itemResourceContainerLayout
									.getListAllCheckBox()) {
								if ((Boolean) cb.getValue())
									cb.setValue(false);
							}
						}
					}
				}
			});
			UiUtils.addComponent(controllGroupBtn, selectAllBtn,
					Alignment.MIDDLE_LEFT);

			Button goUpBtn = new Button();
			goUpBtn.setIcon(MyCollabResource
					.newResource("icons/16/ecm/up_to_root.png"));
			goUpBtn.addListener(new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					Folder parentFolder = null;
					if (FileMainViewImpl.this.baseFolder instanceof ExternalFolder) {
						if (baseFolder.getPath().equals("/")) {
							parentFolder = rootECMFolder;
						} else {
							parentFolder = externalResourceService
									.getParentResourceFolder(
											((ExternalFolder) FileMainViewImpl.this.baseFolder)
													.getExternalDrive(),
											baseFolder.getPath());
						}
					} else if (!FileMainViewImpl.this.baseFolder.getPath()
							.equals(FileMainViewImpl.this.rootPath)) {
						parentFolder = resourceService
								.getParentFolder(FileMainViewImpl.this.baseFolder
										.getPath());
					} else {
						parentFolder = rootECMFolder;
					}
					FileMainViewImpl.this.selectedResourcesList = new ArrayList<Resource>();
					itemResourceContainerLayout.constructBody(parentFolder);
					FileMainViewImpl.this.baseFolder = parentFolder;
					bodyResourceLayout.fileBreadCrumb
							.gotoFolder(FileMainViewImpl.this.baseFolder);
				}
			});
			goUpBtn.setDescription("Back to parent folder");
			goUpBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
			UiUtils.addComponent(controllGroupBtn, goUpBtn,
					Alignment.MIDDLE_LEFT);

			ButtonGroup navButton = new ButtonGroup();
			navButton.addStyleName(UIConstants.THEME_GRAY_LINK);
			Button createBtn = new Button("Create", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					AddNewFolderWindow addnewFolderWindow = new AddNewFolderWindow();
					FileMainViewImpl.this.getWindow().addWindow(
							addnewFolderWindow);
				}
			});
			createBtn.setIcon(MyCollabResource
					.newResource("icons/16/ecm/add.png"));
			createBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
			navButton.addButton(createBtn);

			Button uploadBtn = new Button("Upload", new ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					MultiUploadContentWindow multiUploadWindow = new MultiUploadContentWindow();
					FileMainViewImpl.this.getWindow().addWindow(
							multiUploadWindow);
				}
			});
			uploadBtn.setIcon(MyCollabResource
					.newResource("icons/16/ecm/upload.png"));
			uploadBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
			navButton.addButton(uploadBtn);

			Button downloadBtn = new Button("Download",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							if (selectedResourcesList != null
									&& selectedResourcesList.size() > 0) {
								List<String> lstPath = new ArrayList<String>();
								for (int i = 0; i < selectedResourcesList
										.size(); i++) {
									Resource res = selectedResourcesList.get(i);
									if (res instanceof ExternalFolder) {
										FileMainViewImpl.this
												.getWindow()
												.showNotification(
														"Sorry for this inconvenience, we not yet support download dropbox folder! This function will update soon. Best regard.");
										return;
									} else if (res instanceof ExternalContent) {
										com.vaadin.terminal.Resource downloadResource = StreamDownloadResourceFactory
												.getStreamDropboxResource(res);
										AppContext
												.getApplication()
												.getMainWindow()
												.open(downloadResource,
														"_blank");
										if (i == selectedResourcesList.size() - 1) {
											return;
										}
									} else {
										lstPath.add(res.getPath());
									}
								}
								com.vaadin.terminal.Resource downloadResource = null;
								if (selectedResourcesList.size() == 1
										&& selectedResourcesList.get(0) instanceof Content) {
									downloadResource = StreamDownloadResourceFactory
											.getStreamResource(selectedResourcesList
													.get(0).getPath());
								} else if (selectedResourcesList.size() > 0) {
									downloadResource = StreamDownloadResourceFactory.getStreamFolderResource(
											lstPath.toArray(new String[lstPath
													.size()]),
											itemResourceContainerLayout.isSearchAction);
								}
								AppContext.getApplication().getMainWindow()
										.open(downloadResource, "_blank");

							} else {
								FileMainViewImpl.this
										.getWindow()
										.showNotification(
												"Please choose items to download.");
							}
						}
					});
			downloadBtn.setIcon(MyCollabResource
					.newResource("icons/16/ecm/download.png"));
			downloadBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
			navButton.addButton(downloadBtn);

			Button moveToBtn = new Button("Move to",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							if (selectedResourcesList.size() > 0) {
								MoveResourceWindow moveResourceWindow = new MoveResourceWindow(
										selectedResourcesList,
										FileMainViewImpl.this.resourceService);
								FileMainViewImpl.this.getWindow().addWindow(
										moveResourceWindow);
							} else {
								FileMainViewImpl.this
										.getParent()
										.getWindow()
										.showNotification(
												"Please select items to move");
							}
						}
					});
			moveToBtn.setIcon(MyCollabResource
					.newResource("icons/16/ecm/move_up.png"));
			moveToBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
			navButton.addButton(moveToBtn);

			deleteBtn = new Button(
					LocalizationHelper
							.getMessage(GenericI18Enum.BUTTON_DELETE_LABEL),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							if (selectedResourcesList.size() == 0) {
								FileMainViewImpl.this
										.getParent()
										.getWindow()
										.showNotification(
												"Please select items to delete");
							} else {
								deleteResourceAction();
							}
						}
					});
			deleteBtn.setIcon(MyCollabResource
					.newResource("icons/16/ecm/delete.png"));
			deleteBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
			deleteBtn.setImmediate(true);
			navButton.addButton(deleteBtn);
			controllGroupBtn.addComponent(navButton);

			mainBodyLayout.addComponent(controllGroupBtn);

			FileMainViewImpl.this.rootPath = String.format("%d/Documents",
					AppContext.getAccountId());
			FileMainViewImpl.this.baseFolder = new Folder();
			FileMainViewImpl.this.baseFolder.setPath(rootPath);

			itemResourceContainerLayout = new ItemResourceContainerLayout(
					FileMainViewImpl.this.baseFolder, resourceService);
			itemResourceContainerLayout.setWidth("100%");

			mainBodyLayout.addComponent(itemResourceContainerLayout);
			this.addComponent(mainBodyLayout);
		}

		protected void deleteResourceAction() {
			ConfirmDialogExt
					.show(FileMainViewImpl.this.getWindow(),
							LocalizationHelper.getMessage(
									GenericI18Enum.DELETE_DIALOG_TITLE,
									SiteConfiguration.getSiteName()),
							LocalizationHelper
									.getMessage(GenericI18Enum.DELETE_SINGLE_ITEM_DIALOG_MESSAGE),
							LocalizationHelper
									.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
							LocalizationHelper
									.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
							new ConfirmDialog.Listener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void onClose(final ConfirmDialog dialog) {
									if (dialog.isConfirmed()) {
										if (selectedResourcesList != null
												&& selectedResourcesList.size() > 0) {
											for (Resource res : selectedResourcesList) {
												if (res instanceof ExternalFolder
														|| res instanceof ExternalContent) {
													if (res instanceof ExternalFolder) {
														FileMainViewImpl.this.externalResourceService
																.deleteResource(
																		((ExternalFolder) res)
																				.getExternalDrive(),
																		res.getPath());
													} else
														FileMainViewImpl.this.externalResourceService
																.deleteResource(
																		((ExternalContent) res)
																				.getExternalDrive(),
																		res.getPath());
												} else {
													FileMainViewImpl.this.resourceService.removeResource(
															res.getPath(),
															AppContext
																	.getUsername());
													if (res instanceof Folder) {
														FileMainViewImpl.this.menuTree
																.removeItem((Folder) res);
													}
												}
											}
											if (itemResourceContainerLayout.isSearchAction) {
												itemResourceContainerLayout
														.constructBody((Folder) resourceService
																.getResource(rootPath));
											} else {
												itemResourceContainerLayout
														.constructBody(baseFolder);
											}
											if ((Boolean) selectAllBtn
													.getValue())
												selectAllBtn.click();

											FileMainViewImpl.this.menuTree
													.collapseItem(FileMainViewImpl.this.baseFolder);
											FileMainViewImpl.this.menuTree
													.expandItem(FileMainViewImpl.this.baseFolder);
											FileMainViewImpl.this
													.getWindow()
													.showNotification(
															"Delete successfully.");
											FileMainViewImpl.this.selectedResourcesList = new ArrayList<Resource>();
										}
									}
								}
							});
		}
	}

	private class PagingResourceWapper extends CssLayout {
		private static final long serialVersionUID = 1L;
		private int totalItem;
		public static final int pageItemNum = 15;
		private int currentPage;
		private CssLayout controlBarWrapper;
		private HorizontalLayout pageManagement;
		private int totalPage;
		private List<Resource> lstResource;
		private Button currentBtn;
		private HorizontalLayout controlBar;

		public PagingResourceWapper(List<Resource> lstResource) {
			this.totalItem = lstResource.size();
			this.currentPage = 1;
			this.totalPage = ((int) totalItem / pageItemNum) + 1;
			this.lstResource = lstResource;

			// defined layout here ---------------------------
			this.controlBarWrapper = new CssLayout();
			this.controlBarWrapper.setStyleName("listControl");
			this.controlBarWrapper.setWidth("100%");

			controlBar = new HorizontalLayout();
			controlBar.setWidth("100%");
			this.controlBarWrapper.addComponent(controlBar);

			this.pageManagement = new HorizontalLayout();
			createPageControls();
		}

		private void createPageControls() {
			this.pageManagement.removeAllComponents();
			if (this.currentPage > 1) {
				final Button firstLink = new ButtonLink("1",
						new ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(final ClickEvent event) {
								pageChange(1);
							}
						});
				firstLink.addStyleName("buttonPaging");
				this.pageManagement.addComponent(firstLink);
			}
			if (this.currentPage >= 5) {
				final Label ss1 = new Label("...");
				ss1.addStyleName("buttonPaging");
				this.pageManagement.addComponent(ss1);
			}
			if (this.currentPage > 3) {
				final Button previous2 = new ButtonLink(""
						+ (this.currentPage - 2), new ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						pageChange(currentPage - 2);
					}
				});
				previous2.addStyleName("buttonPaging");
				this.pageManagement.addComponent(previous2);
			}
			if (this.currentPage > 2) {
				final Button previous1 = new ButtonLink(""
						+ (this.currentPage - 1), new ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						pageChange(currentPage - 1);
					}
				});
				previous1.addStyleName("buttonPaging");
				this.pageManagement.addComponent(previous1);
			}
			// Here add current ButtonLink
			currentBtn = new ButtonLink("" + this.currentPage,
					new ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							// pageChange(currentPage);
						}
					});
			currentBtn.addStyleName("buttonPaging");
			currentBtn.addStyleName("buttonPagingcurrent");

			this.pageManagement.addComponent(currentBtn);
			final int range = this.totalPage - this.currentPage;
			if (range >= 1) {
				final Button next1 = new ButtonLink(
						"" + (this.currentPage + 1), new ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(final ClickEvent event) {
								pageChange(currentPage + 1);
							}
						});
				next1.addStyleName("buttonPaging");
				this.pageManagement.addComponent(next1);
			}
			if (range >= 2) {
				final Button next2 = new ButtonLink(
						"" + (this.currentPage + 2), new ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(final ClickEvent event) {
								pageChange(currentPage + 2);
							}
						});
				next2.addStyleName("buttonPaging");
				this.pageManagement.addComponent(next2);
			}
			if (range >= 4) {
				final Label ss2 = new Label("...");
				ss2.addStyleName("buttonPaging");
				this.pageManagement.addComponent(ss2);
			}
			if (range >= 3) {
				final Button last = new ButtonLink("" + this.totalPage,
						new ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(final ClickEvent event) {
								pageChange(totalPage);
							}
						});
				last.addStyleName("buttonPaging");
				this.pageManagement.addComponent(last);
			}

			this.pageManagement.setWidth(null);
			this.pageManagement.setSpacing(true);
			controlBar.addComponent(this.pageManagement);
			controlBar.setComponentAlignment(this.pageManagement,
					Alignment.MIDDLE_RIGHT);
			this.addComponent(controlBarWrapper);
		}

		public void pageChange(int currentPage) {
			this.currentPage = currentPage;
			itemResourceContainerLayout.mainLayout.removeAllComponents();
			int index = currentPage - 1;
			int start = (index == 0) ? index : index * pageItemNum;
			int end = ((start + pageItemNum) > lstResource.size()) ? lstResource
					.size() : start + pageItemNum;

			for (int i = start; i < end; i++) {
				Resource res = lstResource.get(i);
				itemResourceContainerLayout.mainLayout
						.addComponent(itemResourceContainerLayout
								.constructOneItemResourceLayout(res, false));
				itemResourceContainerLayout.mainLayout.addComponent(new Hr());
			}
			createPageControls();
			itemResourceContainerLayout.mainLayout.addComponent(this);
		}

		public int getCurrentPage() {
			return currentPage;
		}
	}

	protected class SettingConnectionDrive extends VerticalLayout {
		private static final long serialVersionUID = 1L;

		private Button connectAccountBtn;
		private VerticalLayout bodyLayout;
		private VerticalLayout mainLayout;
		private ExternalDriveService externalDriveService;
		private ExternalResourceService externalResourceService;

		public SettingConnectionDrive(
				ExternalDriveService externalDriveService,
				ExternalResourceService externalResourceService) {
			this.externalDriveService = externalDriveService;
			this.externalResourceService = externalResourceService;
			mainLayout = new VerticalLayout();
			mainLayout.setSpacing(true);
			mainLayout.setWidth("100%");
			mainLayout.setMargin(true);

			connectAccountBtn = new Button("Connect account",
					new ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							DropBoxOAuthWindow dropboxConnectWindow = new DropBoxOAuthWindow() {
								private static final long serialVersionUID = 1L;

								@Override
								protected void addExternalDrive(
										ExternalDrive externalDrive) {
									FileMainViewImpl.this.menuTree
											.collapseItem(rootECMFolder);
									FileMainViewImpl.this.menuTree
											.expandItem(rootECMFolder);
									OneDriveConnectionBodyLayout layout = new OneDriveConnectionBodyLayout(
											externalDrive);
									bodyLayout.addComponent(layout);
									bodyLayout.setComponentAlignment(layout,
											Alignment.MIDDLE_LEFT);
									bodyLayout.addComponent(new Hr());
								}
							};
							FileMainViewImpl.this.getWindow().addWindow(
									dropboxConnectWindow);
						}
					});
			connectAccountBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
			mainLayout.addComponent(connectAccountBtn);

			bodyLayout = new VerticalLayout();
			bodyLayout.setSpacing(false);
			bodyLayout.setWidth("100%");
			bodyLayout.setMargin(false);

			mainLayout.addComponent(bodyLayout);
			this.addComponent(mainLayout);

			List<ExternalDrive> lst = externalDriveService
					.getExternalDrivesOfUser(AppContext.getUsername());

			bodyLayout.addComponent(new Hr());
			for (final ExternalDrive drive : lst) {
				OneDriveConnectionBodyLayout layout = new OneDriveConnectionBodyLayout(
						drive);
				bodyLayout.addComponent(layout);
				bodyLayout.setComponentAlignment(layout, Alignment.MIDDLE_LEFT);
				bodyLayout.addComponent(new Hr());
			}
		}

		private class OneDriveConnectionBodyLayout extends VerticalLayout {
			private static final long serialVersionUID = 1L;
			private boolean isEdit = false;
			private Label foldernameLbl;

			public OneDriveConnectionBodyLayout(final ExternalDrive drive) {
				final VerticalLayout externalDriveEditLayout = new VerticalLayout();
				externalDriveEditLayout.setSpacing(true);
				externalDriveEditLayout.setMargin(true);

				final HorizontalLayout title = new HorizontalLayout();
				title.setSpacing(true);
				title.setWidth("100%");
				externalDriveEditLayout.addComponent(title);

				CssLayout iconWapper = new CssLayout();
				iconWapper.setWidth("60px");
				final Embedded embed = new Embedded();
				if (drive.getStoragename().equals(StorageNames.DROPBOX))
					embed.setSource(MyCollabResource
							.newResource("icons/48/ecm/dropbox.png"));
				iconWapper.addComponent(embed);
				title.addComponent(iconWapper);
				title.setComponentAlignment(iconWapper, Alignment.MIDDLE_LEFT);

				if (drive.getStoragename().equals(StorageNames.DROPBOX)) {
					Label lbl = new Label("Dropbox");
					lbl.addStyleName("h2");
					lbl.setWidth("100px");
					title.addComponent(lbl);
					title.setComponentAlignment(lbl, Alignment.MIDDLE_LEFT);

					// ----construct title --------------
					foldernameLbl = new Label(drive.getFoldername());
					foldernameLbl.addStyleName("h3-dropbox");
					title.addComponent(foldernameLbl);
					title.setComponentAlignment(foldernameLbl,
							Alignment.MIDDLE_LEFT);
					title.setExpandRatio(foldernameLbl, 1.0f);

					final PopupButton popupBtn = new PopupButton();
					popupBtn.setIcon(MyCollabResource
							.newResource("icons/16/item_settings.png"));
					popupBtn.setWidth("18px");
					popupBtn.addStyleName("link");

					final VerticalLayout popupOptionActionLayout = new VerticalLayout();
					popupOptionActionLayout.setMargin(true);
					popupOptionActionLayout.setSpacing(true);
					popupOptionActionLayout.setWidth("100px");

					Button editBtn = new Button("Edit",
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									popupBtn.setPopupVisible(false);
									if (!isEdit) {
										isEdit = true;
										externalDriveEditLayout
												.addStyleName("driveEditting");
										title.removeComponent(popupBtn);
										HorizontalLayout layout = editActionHorizontalLayout(
												drive, title, foldernameLbl,
												externalDriveEditLayout);
										title.replaceComponent(foldernameLbl,
												layout);
										title.setComponentAlignment(layout,
												Alignment.MIDDLE_LEFT);
										title.setExpandRatio(layout, 1.0f);
										title.addComponent(popupBtn);
										title.setComponentAlignment(popupBtn,
												Alignment.MIDDLE_RIGHT);
									}
								}
							});
					editBtn.addStyleName("link");
					popupOptionActionLayout.addComponent(editBtn);

					Button deleteBtn = new Button("Delete",
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									try {
										ConfirmDialogExt.show(
												FileMainViewImpl.this
														.getWindow(),
												LocalizationHelper
														.getMessage(
																GenericI18Enum.DELETE_DIALOG_TITLE,
																SiteConfiguration
																		.getSiteName()),
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
														if (dialog
																.isConfirmed()) {
															externalDriveService
																	.removeWithSession(
																			drive.getId(),
																			AppContext
																					.getUsername(),
																			AppContext
																					.getAccountId());
															int index = bodyLayout
																	.getComponentIndex(OneDriveConnectionBodyLayout.this);
															bodyLayout
																	.removeComponent(bodyLayout
																			.getComponent(index + 1));
															bodyLayout
																	.removeComponent(OneDriveConnectionBodyLayout.this);
															ExternalFolder res = (ExternalFolder) externalResourceService
																	.getcurrentResourceByPath(
																			drive,
																			"/");
															if (res != null
																	&& res instanceof Folder) {
																Container dataSource = menuTree
																		.getContainerDataSource();
																final Object[] dataCollectionArray = dataSource
																		.getItemIds()
																		.toArray();
																for (Object id : dataCollectionArray) {
																	Folder folder = (Folder) id;
																	if (folder
																			.getName()
																			.equals(res
																					.getExternalDrive()
																					.getFoldername())
																			&& folder instanceof ExternalFolder) {
																		dataSource
																				.removeItem(folder);
																	}
																}
																FileMainViewImpl.this.menuTree
																		.setContainerDataSource(dataSource);
															}
														}
													}
												});
									} catch (Exception e) {
										throw new MyCollabException(e);
									}
								}
							});
					deleteBtn.addStyleName("link");
					popupOptionActionLayout.addComponent(deleteBtn);
					popupBtn.addComponent(popupOptionActionLayout);
					title.addComponent(popupBtn);
					title.setComponentAlignment(popupBtn,
							Alignment.MIDDLE_RIGHT);

				}
				this.addComponent(externalDriveEditLayout);
			}

			private HorizontalLayout editActionHorizontalLayout(
					final ExternalDrive drive,
					final HorizontalLayout parentLayout, final Label lbl,
					final VerticalLayout externalDriveEditLayout) {
				final HorizontalLayout layout = new HorizontalLayout();
				layout.setSpacing(true);
				layout.addStyleName("resourceItem");

				Label folderTitleLbl = new Label("Folder title");
				layout.addComponent(folderTitleLbl);
				layout.setComponentAlignment(folderTitleLbl,
						Alignment.MIDDLE_CENTER);

				final TextField folderNameTextField = new TextField();
				folderNameTextField.setImmediate(true);
				folderNameTextField.setValue(drive.getFoldername());
				layout.addComponent(folderNameTextField);

				Button saveBtn = new Button("Save", new ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						String folderName = folderNameTextField.getValue()
								.toString().trim();
						try {
							if (folderName != null && folderName.length() > 0) {
								boolean checkingError = checkValidFolderName(folderName);
								if (checkingError) {
									FileMainViewImpl.this
											.getWindow()
											.showNotification(
													"Please enter valid folder-name except any follow characters : <>:&/\\|?*&");
									return;
								}
								ExternalFolder res = (ExternalFolder) externalResourceService
										.getcurrentResourceByPath(drive, "/");

								Container dataSource = menuTree
										.getContainerDataSource();
								final Object[] dataCollectionArray = dataSource
										.getItemIds().toArray();
								for (int i = 0; i < dataCollectionArray.length; i++) {
									Folder folder = (Folder) menuTree
											.getContainerDataSource()
											.getItemIds().toArray()[i];
									if (folder.getName().equals(
											res.getExternalDrive()
													.getFoldername())
											&& folder instanceof ExternalFolder) {
										menuTree.collapseItem(rootECMFolder);
										menuTree.expandItem(rootECMFolder);
										break;
									}
								}

								ExternalDrive currentEditDrive = drive;
								currentEditDrive.setFoldername(folderName);
								externalDriveService.updateWithSession(
										currentEditDrive,
										AppContext.getUsername());

								foldernameLbl = new Label(folderName);
								foldernameLbl.addStyleName("h3");

								turnBackMainLayout(parentLayout, foldernameLbl,
										layout, externalDriveEditLayout);
							}
						} catch (Exception e) {
							throw new MyCollabException(e);
						}
					}
				});
				saveBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
				layout.addComponent(saveBtn);

				Button cancelBtn = new Button("Cancel", new ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						turnBackMainLayout(parentLayout, lbl, layout,
								externalDriveEditLayout);
					}
				});
				cancelBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
				layout.addComponent(cancelBtn);
				return layout;
			}

			private void turnBackMainLayout(
					final HorizontalLayout parentLayout, Label lbl,
					HorizontalLayout newComponent,
					VerticalLayout externalDriveEditLayout) {
				this.isEdit = false;
				parentLayout.replaceComponent(newComponent, lbl);
				parentLayout.setComponentAlignment(lbl, Alignment.MIDDLE_LEFT);
				parentLayout.setExpandRatio(lbl, 1.0f);
				externalDriveEditLayout.removeStyleName("driveEditting");
			}
		}
	}
}
