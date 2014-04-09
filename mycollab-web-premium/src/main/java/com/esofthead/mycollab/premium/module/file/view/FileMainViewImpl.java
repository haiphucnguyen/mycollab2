package com.esofthead.mycollab.premium.module.file.view;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.peter.buttongroup.ButtonGroup;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.ecm.ResourceUtils;
import com.esofthead.mycollab.module.ecm.StorageNames;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.domain.ExternalFolder;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.DriveInfoService;
import com.esofthead.mycollab.module.ecm.service.ExternalDriveService;
import com.esofthead.mycollab.module.ecm.service.ExternalResourceService;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.module.file.view.FileMainView;
import com.esofthead.mycollab.module.file.view.components.FileDownloadWindow;
import com.esofthead.mycollab.module.file.view.components.ResourceHandlerComponent;
import com.esofthead.mycollab.module.user.domain.BillingPlan;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.Hr;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.ui.Separator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.vaadin.data.Container;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.CollapseEvent;
import com.vaadin.ui.Tree.ExpandEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@ViewComponent
public class FileMainViewImpl extends AbstractPageView implements FileMainView {
	private static final long serialVersionUID = 1L;
	private static final String illegalFileNamePattern = "[<>:&/\\|?*&]";

	private final Tree menuTree;

	private Folder baseFolder;
	private Folder rootECMFolder;
	private String rootPath;

	private final ResourceHandlerComponent resourceHandlerLayout;
	private FileActivityStreamComponent fileActivityStreamComponent;
	private FilterPanel filterPanel;
	private final Button switchViewBtn;

	private SettingConnectionDrive settingConnectionDrive;
	private final VerticalLayout mainBodyResourceLayout;

	private final ResourceService resourceService;
	private final ExternalDriveService externalDriveService;
	private final ExternalResourceService externalResourceService;

	public FileMainViewImpl() {
		resourceService = ApplicationContextUtil
				.getSpringBean(ResourceService.class);
		externalDriveService = ApplicationContextUtil
				.getSpringBean(ExternalDriveService.class);
		externalResourceService = ApplicationContextUtil
				.getSpringBean(ExternalResourceService.class);

		this.setSpacing(true);
		this.setMargin(false);
		this.setStyleName("file-list-view");
		HorizontalLayout mainView = new HorizontalLayout();
		mainView.setSpacing(true);
		mainView.setMargin(true);
		mainView.setWidth("100%");

		final HorizontalLayout menuBarContainerHorizontalLayout = new HorizontalLayout();
		menuBarContainerHorizontalLayout.setMargin(new MarginInfo(false, true,
				true, true));

		final VerticalLayout menuLayout = new VerticalLayout();
		menuLayout.setSpacing(true);
		menuLayout.setWidth("250px");
		menuBarContainerHorizontalLayout.addComponent(menuLayout);

		VerticalLayout topControlMenuWrapper = new VerticalLayout();
		topControlMenuWrapper.setWidth("250px");

		HorizontalLayout topControlMenu = new HorizontalLayout();
		topControlMenu.setWidth("100%");
		topControlMenu.addStyleName("border-box2-no-margin");
		topControlMenu.addStyleName("file-topcontrols");

		topControlMenuWrapper.addComponent(topControlMenu);

		ButtonGroup navButton = new ButtonGroup();
		navButton.addStyleName(UIConstants.THEME_GRAY_LINK);
		UiUtils.addComponent(topControlMenu, navButton, Alignment.MIDDLE_RIGHT);

		switchViewBtn = new Button();
		switchViewBtn.setDescription("Event");
		switchViewBtn.setIcon(MyCollabResource
				.newResource("icons/16/ecm/event.png"));
		switchViewBtn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (switchViewBtn.getDescription().equals("Event")) {
					switchViewBtn.setDescription("File Management");
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
		switchViewBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
		navButton.addButton(switchViewBtn);

		Button settingBtn = new Button();
		settingBtn.setIcon(MyCollabResource
				.newResource("icons/16/ecm/settings.png"));
		settingBtn.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				mainBodyResourceLayout.removeAllComponents();

				settingConnectionDrive = new SettingConnectionDrive(
						externalDriveService, externalResourceService);
				mainBodyResourceLayout.addComponent(settingConnectionDrive);
			}
		});
		settingBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
		navButton.addButton(settingBtn);

		final PopupButton linkBtn = new PopupButton();
		linkBtn.setIcon(MyCollabResource.newResource("icons/16/ecm/link.png"));
		linkBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
		linkBtn.setWidth("65px");
		final VerticalLayout filterBtnLayout = new VerticalLayout();
		filterBtnLayout.setMargin(true);
		filterBtnLayout.setSpacing(true);
		filterBtnLayout.setWidth("180px");

		HorizontalLayout connectDropboxLayout = new HorizontalLayout();
		connectDropboxLayout.setSpacing(true);

		final Image titleIcon = new Image(null,
				MyCollabResource.newResource("icons/16/ecm/dropbox.png"));
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
						UI.getCurrent().addWindow(dropboxConnectWindow);
					}
				});
		uploadDropboxBtn.addStyleName("link");
		connectDropboxLayout.addComponent(uploadDropboxBtn);
		filterBtnLayout.addComponent(connectDropboxLayout);

		linkBtn.setContent(filterBtnLayout);
		
		navButton.addButton(linkBtn);

		Label usedVolumeInfo = new Label();
		usedVolumeInfo.addStyleName("volumeUsageInfo");
		BillingPlan currentBillingPlan = AppContext.getBillingAccount()
				.getBillingPlan();
		DriveInfoService driveInfoService = ApplicationContextUtil
				.getSpringBean(DriveInfoService.class);
		String usedStorageTxt = ResourceUtils.getVolumeDisplay(driveInfoService
				.getUsedStorageVolume(AppContext.getAccountId()))
				+ " of "
				+ ResourceUtils
						.getVolumeDisplay(currentBillingPlan.getVolume());
		usedVolumeInfo
				.setValue("<div id='left-side'>&nbsp;</div><div id='info-content'>"
						+ usedStorageTxt
						+ "</div><div id='right-side'>&nbsp;</div>");
		usedVolumeInfo.setContentMode(ContentMode.HTML);
		usedVolumeInfo.setWidth("100%");
		topControlMenuWrapper.addComponent(usedVolumeInfo);
		topControlMenuWrapper.setComponentAlignment(usedVolumeInfo,
				Alignment.TOP_CENTER);

		menuLayout.addComponent(topControlMenuWrapper);

		this.menuTree = new Tree();
		this.menuTree.setMultiSelect(false);
		this.menuTree.setSelectable(true);
		this.menuTree.setImmediate(true);

		menuLayout.addComponent(this.menuTree);

		this.menuTree.addExpandListener(new Tree.ExpandListener() {
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

		this.menuTree.addCollapseListener(new Tree.CollapseListener() {
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

		this.menuTree
				.addItemClickListener(new ItemClickEvent.ItemClickListener() {
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
		separator.setHeight("100%");
		separator.setWidth(SIZE_UNDEFINED, Sizeable.Unit.PIXELS);
		mainView.addComponent(separator);
		mainView.setComponentAlignment(separator, Alignment.TOP_LEFT);

		// here for MainBodyResourceLayout class
		mainBodyResourceLayout = new VerticalLayout();
		mainBodyResourceLayout.setSpacing(true);

		VerticalLayout filterWapper = new VerticalLayout();
		filterWapper.setSpacing(true);
		filterPanel = new FilterPanel();
		filterWapper.addComponent(filterPanel);

		mainBodyResourceLayout.addComponent(filterWapper);

		// this is component handler Resource-----------
		FileMainViewImpl.this.rootPath = String.format("%d/Documents",
				AppContext.getAccountId());
		FileMainViewImpl.this.baseFolder = new Folder();
		FileMainViewImpl.this.baseFolder.setPath(rootPath);
		FileMainViewImpl.this.rootECMFolder = baseFolder;

		resourceHandlerLayout = new ResourceHandlerComponent(
				FileMainViewImpl.this.baseFolder, rootPath, menuTree);
		mainBodyResourceLayout.addComponent(resourceHandlerLayout);

		mainView.addComponent(mainBodyResourceLayout);
		mainView.setComponentAlignment(mainBodyResourceLayout,
				Alignment.TOP_LEFT);
		mainView.setExpandRatio(mainBodyResourceLayout, 1.0f);

		this.addComponent(mainView);
		this.setComponentAlignment(mainView, Alignment.MIDDLE_CENTER);

	}

	public boolean checkValidFolderName(String value) {
		Pattern pattern = Pattern.compile(illegalFileNamePattern);
		Matcher matcher = pattern.matcher(value);
		return matcher.find();
	}

	private void gotoActionLogPage() {
		mainBodyResourceLayout.removeAllComponents();
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
							UI.getCurrent().addWindow(
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
		mainBodyResourceLayout.addComponent(fileActivityStreamComponent);
		fileActivityStreamComponent.showContentFeeds();
	}

	private void gotoEnclosingFoder(Resource res, FileSearchCriteria criteria) {
		String path = criteria.getBaseFolder().substring(0,
				criteria.getBaseFolder().lastIndexOf("/"));
		Resource parentFolder = resourceService.getResource(path);
		if (parentFolder != null) {
			gotoFileMainViewPage((Folder) parentFolder);
		} else {
			String message = (res instanceof Folder) ? "Folder's location has been moved successfully"
					: "File's location has been moved successfully";
			NotificationUtil.showNotification(message);
		}
	}

	private void gotoFileMainViewPage(Folder baseFolder) {
		this.baseFolder = baseFolder;

		mainBodyResourceLayout.removeAllComponents();
		mainBodyResourceLayout.setSpacing(true);

		VerticalLayout filterWapper = new VerticalLayout();
		filterWapper.setSpacing(true);
		filterPanel = new FilterPanel();
		filterWapper.addComponent(filterPanel);

		mainBodyResourceLayout.addComponent(filterWapper);
		mainBodyResourceLayout.addComponent(resourceHandlerLayout);

		resourceHandlerLayout.gotoFolderBreadCumb(baseFolder);
		resourceHandlerLayout.constructBodyItemContainer(baseFolder);

		switchViewBtn.setDescription("Event");
		switchViewBtn.setIcon(MyCollabResource
				.newResource("icons/16/ecm/event.png"));
	}

	public void displayResources(String rootPath, String rootFolderName) {
		this.baseFolder = new Folder();
		this.baseFolder.setPath(rootPath);
		this.rootECMFolder = this.baseFolder;

		this.menuTree.removeAllItems();
		this.menuTree.addItem(this.baseFolder);
		this.menuTree.setItemCaption(this.baseFolder, rootFolderName);
		this.menuTree.setItemIcon(this.baseFolder,
				MyCollabResource.newResource("icons/16/ecm/folder_close.png"));
		this.menuTree.collapseItem(this.baseFolder);

		resourceHandlerLayout.displayComponent(this.baseFolder, rootPath,
				rootFolderName, true);

		resourceHandlerLayout
				.addSearchHandlerToBreadCrumb(new SearchHandler<FileSearchCriteria>() {
					@Override
					public void onSearch(FileSearchCriteria criteria) {
						Folder selectedFolder = null;
						if (criteria.getStorageName() != null
								&& criteria.getStorageName().equals(
										StorageNames.DROPBOX)) {
							selectedFolder = (Folder) externalResourceService
									.getCurrentResourceByPath(
											criteria.getExternalDrive(),
											criteria.getBaseFolder());
						} else {
							selectedFolder = (Folder) FileMainViewImpl.this.resourceService
									.getResource(criteria.getBaseFolder());
						}
						resourceHandlerLayout
								.constructBodyItemContainer(selectedFolder);
						resourceHandlerLayout
								.gotoFolderBreadCumb(selectedFolder);
						FileMainViewImpl.this.baseFolder = selectedFolder;
						resourceHandlerLayout
								.setCurrentBaseFolder(selectedFolder);
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
			layout.setMargin(true);

			final Image titleIcon = new Image(null,
					MyCollabResource
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
				final HorizontalLayout basicSearchBody = new HorizontalLayout();
				basicSearchBody.setSpacing(true);
				basicSearchBody.setMargin(true);
				UiUtils.addComponent(basicSearchBody,new Label("Name:"), Alignment.MIDDLE_LEFT);
				this.addStyleName("file-list-view");
				this.nameField = new TextField();
				this.nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
				UiUtils.addComponent(basicSearchBody, this.nameField,
						Alignment.MIDDLE_CENTER);

				this.myItemCheckbox = new CheckBox("My Items");
				UiUtils.addComponent(basicSearchBody, this.myItemCheckbox,
						Alignment.MIDDLE_CENTER);
				
				final Button searchBtn = new Button("Search");
				searchBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
				searchBtn.setIcon(MyCollabResource
						.newResource("icons/16/search.png"));

				searchBtn.addClickListener(new Button.ClickListener() {
				
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						resourceHandlerLayout.setCurrentBaseFolder(baseFolder);
						List<Resource> lstResource = FileMainViewImpl.this.resourceService
								.searchResourcesByName(
										FileMainViewImpl.this.rootPath,
										nameField.getValue().toString().trim());
						if (lstResource != null && lstResource.size() > 0) {
							resourceHandlerLayout
									.constructBodyItemContainerSearchActionResult(
											lstResource, nameField.getValue()
													.toString().trim());
							resourceHandlerLayout.initBreadCrumb();
							resourceHandlerLayout
									.setCurrentBaseFolder((Folder) FileMainViewImpl.this.resourceService
											.getResource(rootPath));
						} else {
							resourceHandlerLayout
									.constructBodyItemContainerSearchActionResult(
											lstResource, nameField.getValue()
													.toString().trim());
						}
					}
				});
				UiUtils.addComponent(basicSearchBody, searchBtn,
						Alignment.MIDDLE_LEFT);
				
				final Button cancelBtn = new Button(LocalizationHelper
						.getMessage(GenericI18Enum.BUTTON_CLEAR));
			
				cancelBtn.addClickListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						FileBasicSearchLayout.this.nameField.setValue("");
					}
				});
				cancelBtn.setStyleName(UIConstants.THEME_BLANK_LINK);
				basicSearchBody.addComponent(cancelBtn);

				
				return basicSearchBody;
			}

			@Override
			protected SearchCriteria fillupSearchCriteria() {
				return null;
			}
		}
	}

	protected class SettingConnectionDrive extends VerticalLayout {
		private static final long serialVersionUID = 1L;

		private final Button connectAccountBtn;
		private final VerticalLayout bodyLayout;
		private final VerticalLayout mainLayout;
		private final ExternalDriveService externalDriveService;
		private final ExternalResourceService externalResourceService;

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
							UI.getCurrent().addWindow(dropboxConnectWindow);
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
							.newResource("icons/16/item_settings_big.png"));
					popupBtn.setStyleName(UIConstants.THEME_BLANK_LINK);
					

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
												UI.getCurrent(),
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
																	.getCurrentResourceByPath(
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
					popupBtn.setContent(popupOptionActionLayout);
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
									NotificationUtil
											.showErrorNotification("Please enter valid folder name except any follow characters : <>:&/\\|?*&");
									return;
								}
								ExternalFolder res = (ExternalFolder) externalResourceService
										.getCurrentResourceByPath(drive, "/");

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
				saveBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
				saveBtn.setIcon(MyCollabResource.newResource("icons/16/save.png"));
				layout.addComponent(saveBtn);

				Button cancelBtn = new Button("Cancel", new ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						turnBackMainLayout(parentLayout, lbl, layout,
								externalDriveEditLayout);
					}
				});
				cancelBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
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
