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

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.ecm.ContentException;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.StreamDownloadResourceFactory;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.module.file.view.components.FileDashboardComponent.AbstractMoveWindow;
import com.esofthead.mycollab.module.file.view.components.FileDownloadWindow;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AttachmentPanel;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.Hr;
import com.esofthead.mycollab.vaadin.ui.Separator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
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
	private Tree menuTree;
	private final ResourceService resourceService;
	private Folder baseFolder;
	private String rootPath;
	private String rootFolderName;
	private List<Resource> lstCheckedResource;
	private ItemResourceContainerLayout itemResourceContainerLayout;
	private MainBodyResourceLayout bodyResourceLayout;
	private FileActivityStreamComponent fileActivityStreamComponent;
	public static final String illegalFileNamePattern = "[<>:&/\\|?*&]";
	private Button eventBtn;

	public FileMainViewImpl() {
		resourceService = AppContext.getSpringBean(ResourceService.class);
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

		eventBtn = new Button();
		eventBtn.setDescription("Event");
		eventBtn.setIcon(MyCollabResource.newResource("icons/16/ecm/event.png"));
		eventBtn.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (eventBtn.getDescription().equals("Event")) {
					eventBtn.setDescription("FileManagement");
					eventBtn.setIcon(MyCollabResource
							.newResource("icons/16/ecm/document_yellow_icon.png"));
					gotoActionLogPage();
				} else if (eventBtn.getDescription().equals("FileManagement")) {
					eventBtn.setDescription("Event");
					eventBtn.setIcon(MyCollabResource
							.newResource("icons/16/ecm/event.png"));
					gotoFileMainViewPage(baseFolder);
				}
			}
		});
		eventBtn.addStyleName("graybtn2");
		navButton.addButton(eventBtn);

		Button settingBtn = new Button();
		settingBtn.setIcon(MyCollabResource
				.newResource("icons/16/ecm/settings.png"));
		settingBtn.addListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
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
		Button uploadDropboxBtn = new Button("Upload from Dropbox");
		uploadDropboxBtn.addStyleName("link");
		filterBtnLayout.addComponent(uploadDropboxBtn);

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
				final Folder expandFolder = (Folder) event.getItemId();
				final List<Folder> subFolders = resourceService
						.getSubFolders(expandFolder.getPath());

				menuTree.setItemIcon(expandFolder, MyCollabResource
						.newResource("icons/16/ecm/folder_open.png"));

				if (subFolders != null) {
					for (final Folder subFolder : subFolders) {
						expandFolder.addChild(subFolder);
						menuTree.addItem(subFolder);

						menuTree.setItemIcon(subFolder, MyCollabResource
								.newResource("icons/16/ecm/folder_close.png"));
						menuTree.setItemCaption(subFolder, subFolder.getName());
						menuTree.setParent(subFolder, expandFolder);
					}
				}
			}
		});

		this.menuTree.addListener(new Tree.CollapseListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void nodeCollapse(final CollapseEvent event) {
				final Folder collapseFolder = (Folder) event.getItemId();
				menuTree.setItemIcon(collapseFolder, MyCollabResource
						.newResource("icons/16/ecm/folder_close.png"));

				Container dataSource = menuTree.getContainerDataSource();
				final Object[] dataCollectionArray = dataSource.getItemIds()
						.toArray();
				for (Object id : dataCollectionArray) {
					Folder folder = (Folder) id;
					if (folder.getPath().contains(collapseFolder.getPath())
							&& !folder.getPath().equals(
									collapseFolder.getPath())) {
						dataSource.removeItem(folder);
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

		mainView.addComponent(bodyResourceLayout);
		mainView.setComponentAlignment(bodyResourceLayout, Alignment.TOP_LEFT);
		mainView.setExpandRatio(bodyResourceLayout, 1.0f);

		this.addComponent(mainView);
		this.setComponentAlignment(mainView, Alignment.MIDDLE_CENTER);

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
		bodyResourceLayout.removeAllComponents();
		bodyResourceLayout.setSpacing(true);
		bodyResourceLayout.addComponent(bodyResourceLayout.filterPanel);
		bodyResourceLayout.addComponent(bodyResourceLayout.fileBreadCrumb);
		bodyResourceLayout.addComponent(bodyResourceLayout.controllGroupBtn);
		bodyResourceLayout.addComponent(itemResourceContainerLayout);
		this.baseFolder = baseFolder;
		bodyResourceLayout.fileBreadCrumb.gotoFolder(baseFolder);
		itemResourceContainerLayout.constructBody(baseFolder);
		eventBtn.setDescription("Event");
		eventBtn.setIcon(MyCollabResource.newResource("icons/16/ecm/event.png"));
	}

	public void displayResources(String rootPath, String rootFolderName) {
		this.menuTree.removeAllItems();
		this.rootFolderName = rootFolderName;

		this.baseFolder = new Folder();
		this.baseFolder.setPath(rootPath);
		this.menuTree.addItem(this.baseFolder);
		this.menuTree.setItemCaption(this.baseFolder, rootFolderName);
		this.menuTree.setItemIcon(this.baseFolder,
				MyCollabResource.newResource("icons/16/ecm/folder_close.png"));

		this.menuTree.collapseItem(this.baseFolder);
		bodyResourceLayout.fileBreadCrumb
				.addSearchHandler(new SearchHandler<FileSearchCriteria>() {
					@Override
					public void onSearch(FileSearchCriteria criteria) {
						Folder selectedFolder = (Folder) FileMainViewImpl.this.resourceService
								.getResource(criteria.getBaseFolder());
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
		displayResources(rootPath, "My Documents");
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
					.newResource("icons/24/document_preview.png"));
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
			lstCheckedResource = new ArrayList<Resource>();
			listAllCheckBox = new ArrayList<CheckBox>();
			this.setMargin(true);
			constructBody(folder);
		}

		private void constructBody(Folder curFolder) {
			isSearchAction = false;
			if (mainLayout != null) {
				this.removeAllComponents();
			}
			if (lstCheckedResource != null && lstCheckedResource.size() > 0)
				lstCheckedResource.clear();
			if (listAllCheckBox != null && listAllCheckBox.size() > 0) {
				listAllCheckBox.clear();
			}
			mainLayout = new VerticalLayout();
			mainLayout.setSpacing(false);
			List<Resource> lstResource = resourceService.getResources(curFolder
					.getPath());
			this.addComponent(new Hr());
			if (lstResource != null && lstResource.size() > 0) {
				for (Resource res : lstResource) {
					mainLayout.addComponent(constructOneItemResourceLayout(res,
							false));
					mainLayout.addComponent(new Hr());
				}
			}
			this.addComponent(mainLayout);
		}

		private void constructBodySearchActionResult(List<Resource> lstResource) {
			isSearchAction = true;
			if (mainLayout != null) {
				this.removeAllComponents();
			}
			if (lstCheckedResource != null && lstCheckedResource.size() > 0)
				lstCheckedResource.clear();
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
			layout.addStyleName("resourceItem");
			layout.setWidth("100%");
			layout.setSpacing(true);

			final CheckBox checkbox = new CheckBox();
			checkbox.setWidth("30px");
			checkbox.setImmediate(true);
			checkbox.setStyleName(UIConstants.THEME_ROUND_BUTTON);
			listAllCheckBox.add(checkbox);

			checkbox.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(ValueChangeEvent event) {
					if ((Boolean) checkbox.getValue()) {
						lstCheckedResource.add(res);
					} else {
						lstCheckedResource.remove(res);
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
				resourceIcon.setSource(MyCollabResource
						.newResource("icons/48/folder_blue_icon.png"));
			else
				resourceIcon.setSource(MyCollabResource
						.newResource("icons/48/file_blue_icon.png"));
			resIconWapper.setWidth("70px");
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
			moreInfoAboutResLayout.addComponent(new Label(res.getCreatedBy()));
			moreInfoAboutResLayout.addComponent(new Separator());
			moreInfoAboutResLayout.addComponent(new Label(AppContext
					.formatDate(res.getCreated().getTime())));
			if (res instanceof Content) {
				moreInfoAboutResLayout.addComponent(new Separator());
				Double size = res.getSize();
				DecimalFormat df = new DecimalFormat("#");
				df.setRoundingMode(RoundingMode.HALF_UP);
				moreInfoAboutResLayout.addComponent(new Label(df.format(size)
						+ " KB"));
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
								downloadResource = StreamDownloadResourceFactory
										.getStreamFolderResource(
												new String[] { res.getPath() },
												false);
							} else {
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
							lstCheckedResource.clear();
							lstCheckedResource.add(res);

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
					.newResource("icons/48/folder_arrow_right_icon.png"));
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
			FileMainViewImpl.this.lstCheckedResource = new ArrayList<Resource>();

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
					final String parentPath = oldPath.substring(0,
							oldPath.lastIndexOf("/") + 1);
					final String newNameValue = (String) newName.getValue();
					final String newPath = parentPath + newNameValue;
					try {
						RenameResourceWindow.this.service.rename(oldPath,
								newPath, AppContext.getUsername());
						// reset layout
						FileMainViewImpl.this.itemResourceContainerLayout
								.constructBody(FileMainViewImpl.this.baseFolder);
						// Set item caption for sub folder of base folder in
						// folderTree

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
								menuTree.setItemCaption(folder, newNameValue);
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

								final Folder newFolder = FileMainViewImpl.this.resourceService
										.createNewFolder(baseFolderPath,
												folderVal,
												AppContext.getUsername());

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

										FileMainViewImpl.this.resourceService
												.saveContent(content,
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
					if (!FileMainViewImpl.this.baseFolder.getPath().equals(
							FileMainViewImpl.this.rootPath)) {
						Folder parentFolder = resourceService
								.getParentFolder(FileMainViewImpl.this.baseFolder
										.getPath());
						FileMainViewImpl.this.lstCheckedResource = new ArrayList<Resource>();
						itemResourceContainerLayout.constructBody(parentFolder);
						FileMainViewImpl.this.baseFolder = parentFolder;
						bodyResourceLayout.fileBreadCrumb
								.gotoFolder(FileMainViewImpl.this.baseFolder);
					}
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
							if (lstCheckedResource != null
									&& lstCheckedResource.size() > 0) {
								List<String> lstPath = new ArrayList<String>();
								for (Resource res : lstCheckedResource) {
									lstPath.add(res.getPath());
								}
								com.vaadin.terminal.Resource downloadResource = null;
								if (lstCheckedResource.size() == 1
										&& lstCheckedResource.get(0) instanceof Content) {
									downloadResource = StreamDownloadResourceFactory
											.getStreamResource(lstCheckedResource
													.get(0).getPath());
								} else if (lstCheckedResource.size() > 0) {
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
							if (lstCheckedResource.size() > 0) {
								MoveResourceWindow moveResourceWindow = new MoveResourceWindow(
										lstCheckedResource,
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
							if (lstCheckedResource.size() == 0) {
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
								public void onClose(final ConfirmDialog dialog) {
									if (dialog.isConfirmed()) {
										if (lstCheckedResource != null
												&& lstCheckedResource.size() > 0) {
											for (Resource res : lstCheckedResource) {
												FileMainViewImpl.this.resourceService.removeResource(
														res.getPath(),
														AppContext
																.getUsername());
												if (res instanceof Folder) {
													FileMainViewImpl.this.menuTree
															.removeItem((Folder) res);
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
											FileMainViewImpl.this.lstCheckedResource = new ArrayList<Resource>();
										}
									}
								}
							});
		}
	}
}
