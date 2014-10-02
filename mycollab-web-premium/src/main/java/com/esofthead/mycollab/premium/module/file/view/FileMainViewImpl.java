package com.esofthead.mycollab.premium.module.file.view;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.peter.buttongroup.ButtonGroup;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.ui.components.AbstractCloudDriveOAuthWindow;
import com.esofthead.mycollab.common.ui.components.AbstractCloudDriveOAuthWindow.ExternalDriveConnectedEvent;
import com.esofthead.mycollab.common.ui.components.AbstractCloudDriveOAuthWindow.ExternalDriveConnectedListener;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.ecm.ResourceUtils;
import com.esofthead.mycollab.module.ecm.StorageNames;
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
import com.esofthead.mycollab.module.file.view.components.ResourcesDisplayComponent;
import com.esofthead.mycollab.module.user.domain.BillingPlan;
import com.esofthead.mycollab.premium.module.file.view.FolderNavigatorMenu.SelectFolderEvent;
import com.esofthead.mycollab.premium.module.file.view.FolderNavigatorMenu.SelectedFolderListener;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.ViewScope;
import com.esofthead.mycollab.vaadin.ui.ComponentManagerFactory;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.Hr;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.ui.Separator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.vaadin.data.Container;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@ViewComponent(scope = ViewScope.PROTOTYPE)
public class FileMainViewImpl extends AbstractPageView implements FileMainView {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(FileMainViewImpl.class);

	private static final String illegalFileNamePattern = "[<>:&/\\|?*&]";

	private FolderNavigatorMenu folderNavigator;

	private Folder rootECMFolder;

	private Folder rootFolder;
	private String rootPath;

	private ResourcesDisplayComponent resourceHandlerLayout;

	private SettingConnectionDrive settingConnectionDrive;
	private VerticalLayout mainBodyResourceLayout;

	private ResourceService resourceService;
	private ExternalDriveService externalDriveService;
	private ExternalResourceService externalResourceService;

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

		HorizontalLayout menuBarContainerHorizontalLayout = buildLeftColumn();
		mainView.addComponent(menuBarContainerHorizontalLayout);
		mainView.setComponentAlignment(menuBarContainerHorizontalLayout,
				Alignment.TOP_LEFT);

		Separator separator = new Separator();
		separator.setHeight("100%");
		separator.setWidthUndefined();
		mainView.addComponent(separator);
		mainView.setComponentAlignment(separator, Alignment.TOP_LEFT);

		this.rootFolder = new Folder(rootPath);
		this.rootECMFolder = rootFolder;

		// here for MainBodyResourceLayout class
		VerticalLayout rightColumn = new VerticalLayout();
		rightColumn.addComponent(constructHeader());

		mainBodyResourceLayout = new VerticalLayout();
		mainBodyResourceLayout.setSpacing(true);

		resourceHandlerLayout = new ResourcesDisplayComponent(rootPath,
				rootFolder);
		mainBodyResourceLayout.addComponent(resourceHandlerLayout);

		rightColumn.addComponent(mainBodyResourceLayout);

		mainView.addComponent(rightColumn);
		mainView.setComponentAlignment(rightColumn, Alignment.TOP_LEFT);
		mainView.setExpandRatio(rightColumn, 1.0f);

		this.addComponent(mainView);
		this.setComponentAlignment(mainView, Alignment.MIDDLE_CENTER);

	}

	private HorizontalLayout constructHeader() {
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

	private HorizontalLayout buildLeftColumn() {
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

		Button settingBtn = new Button();
		settingBtn.setIcon(MyCollabResource
				.newResource("icons/16/ecm/settings.png"));
		settingBtn.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				mainBodyResourceLayout.removeAllComponents();

				settingConnectionDrive = new SettingConnectionDrive();
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
						AbstractCloudDriveOAuthWindow cloudDriveOAuthWindow = ComponentManagerFactory
								.getCloudDriveOAuthWindow("Add Dropbox");
						cloudDriveOAuthWindow
								.addExternalDriveConnectedListener(new ExternalDriveConnectedListener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void connectedSuccess(
											ExternalDriveConnectedEvent event) {
										folderNavigator
												.expandItem(rootECMFolder);

									}
								});
						UI.getCurrent().addWindow(cloudDriveOAuthWindow);
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

		this.rootPath = String
				.format("%d/Documents", AppContext.getAccountId());

		this.folderNavigator = new FolderNavigatorMenu(rootPath);

		menuLayout.addComponent(this.folderNavigator);

		return menuBarContainerHorizontalLayout;
	}

	private boolean checkValidFolderName(String value) {
		Pattern pattern = Pattern.compile(illegalFileNamePattern);
		Matcher matcher = pattern.matcher(value);
		return matcher.find();
	}

	private void gotoFileMainViewPage(Folder baseFolder) {
		this.rootFolder = baseFolder;

		mainBodyResourceLayout.removeAllComponents();
		mainBodyResourceLayout.setSpacing(true);
		mainBodyResourceLayout.addComponent(resourceHandlerLayout);

		resourceHandlerLayout.constructBodyItemContainer(rootFolder);
	}

	private void displayResources(String rootPath, String rootFolderName) {
		this.rootFolder = new Folder(rootPath);
		this.rootECMFolder = this.rootFolder;

		this.folderNavigator.removeAllItems();
		this.folderNavigator.addItem(this.rootFolder);
		this.folderNavigator.setItemCaption(this.rootFolder, rootFolderName);
		this.folderNavigator.setItemIcon(this.rootFolder,
				MyCollabResource.newResource("icons/16/ecm/folder_close.png"));
		this.folderNavigator.collapseItem(this.rootFolder);

		resourceHandlerLayout.displayComponent(this.rootFolder, rootPath,
				rootFolderName);

		resourceHandlerLayout
				.addSearchHandlerToBreadCrumb(new SearchHandler<FileSearchCriteria>() {
					@Override
					public void onSearch(FileSearchCriteria criteria) {
						Resource selectedFolder = null;
						if (StorageNames.DROPBOX.equals(criteria
								.getStorageName())) {
							selectedFolder = externalResourceService
									.getCurrentResourceByPath(
											criteria.getExternalDrive(),
											criteria.getBaseFolder());
						} else {
							selectedFolder = FileMainViewImpl.this.resourceService
									.getResource(criteria.getBaseFolder());
						}

						if (selectedFolder == null) {
							log.error("Can not find folder with path "
									+ criteria.getBaseFolder() + "--"
									+ criteria.getRootFolder());
						} else if (!(selectedFolder instanceof Folder)) {
							log.error("Expect folder but the result is file "
									+ criteria.getBaseFolder() + "--"
									+ criteria.getRootFolder());
						} else {
							Folder resultFolder = (Folder) selectedFolder;
							resourceHandlerLayout
									.constructBodyItemContainer(resultFolder);

							FileMainViewImpl.this.rootFolder = resultFolder;
						}

					}
				});
	}

	@Override
	public void display() {
		folderNavigator.addSelectFolderListener(new SelectedFolderListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void selectFolder(SelectFolderEvent event) {
				Folder folder = (Folder) event.getData();
				if (folder != null) {
					gotoFileMainViewPage(folder);
				}

			}
		});
		displayResources(rootPath, "Documents");
	}

	private class SettingConnectionDrive extends VerticalLayout {
		private static final long serialVersionUID = 1L;

		private final Button connectAccountBtn;
		private final VerticalLayout bodyLayout;
		private final VerticalLayout mainLayout;

		public SettingConnectionDrive() {
			mainLayout = new VerticalLayout();
			mainLayout.setSpacing(true);
			mainLayout.setWidth("100%");
			mainLayout.setMargin(true);

			connectAccountBtn = new Button("Connect account",
					new ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							AbstractCloudDriveOAuthWindow cloudDriveOAuthWindow = ComponentManagerFactory
									.getCloudDriveOAuthWindow("Add Dropbox");
							cloudDriveOAuthWindow
									.addExternalDriveConnectedListener(new ExternalDriveConnectedListener() {
										private static final long serialVersionUID = 1L;

										@Override
										public void connectedSuccess(
												ExternalDriveConnectedEvent event) {
											FileMainViewImpl.this.folderNavigator
													.collapseItem(rootECMFolder);
											FileMainViewImpl.this.folderNavigator
													.expandItem(rootECMFolder);
											OneDriveConnectionBodyLayout layout = new OneDriveConnectionBodyLayout(
													(ExternalDrive) event
															.getData());
											bodyLayout.addComponent(layout);
											bodyLayout.setComponentAlignment(
													layout,
													Alignment.MIDDLE_LEFT);
											bodyLayout.addComponent(new Hr());

										}
									});
							UI.getCurrent().addWindow(cloudDriveOAuthWindow);
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

					Button editBtn = new Button(
							AppContext
									.getMessage(GenericI18Enum.BUTTON_EDIT),
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

					Button deleteBtn = new Button(
							AppContext
									.getMessage(GenericI18Enum.BUTTON_DELETE),
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									try {
										ConfirmDialogExt.show(
												UI.getCurrent(),
												AppContext
														.getMessage(
																GenericI18Enum.DIALOG_DELETE_TITLE,
																SiteConfiguration
																		.getSiteName()),
												AppContext
														.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
												AppContext
														.getMessage(GenericI18Enum.BUTTON_YES),
												AppContext
														.getMessage(GenericI18Enum.BUTTON_NO),
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
															if (res instanceof Folder) {
																Container dataSource = FileMainViewImpl.this.folderNavigator
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
																FileMainViewImpl.this.folderNavigator
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

				Button saveBtn = new Button(
						AppContext.getMessage(GenericI18Enum.BUTTON_SAVE),
						new ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								String folderName = folderNameTextField
										.getValue().toString().trim();
								try {
									if (folderName != null
											&& folderName.length() > 0) {
										boolean checkingError = checkValidFolderName(folderName);
										if (checkingError) {
											NotificationUtil
													.showErrorNotification("Please enter valid folder name except any follow characters : <>:&/\\|?*&");
											return;
										}
										ExternalFolder res = (ExternalFolder) externalResourceService
												.getCurrentResourceByPath(
														drive, "/");

										Container dataSource = FileMainViewImpl.this.folderNavigator
												.getContainerDataSource();
										final Object[] dataCollectionArray = dataSource
												.getItemIds().toArray();
										for (int i = 0; i < dataCollectionArray.length; i++) {
											Folder folder = (Folder) FileMainViewImpl.this.folderNavigator
													.getContainerDataSource()
													.getItemIds().toArray()[i];
											if (folder.getName().equals(
													res.getExternalDrive()
															.getFoldername())
													&& folder instanceof ExternalFolder) {
												FileMainViewImpl.this.folderNavigator
														.collapseItem(rootECMFolder);
												FileMainViewImpl.this.folderNavigator
														.expandItem(rootECMFolder);
												break;
											}
										}

										ExternalDrive currentEditDrive = drive;
										currentEditDrive
												.setFoldername(folderName);
										externalDriveService.updateWithSession(
												currentEditDrive,
												AppContext.getUsername());

										foldernameLbl = new Label(folderName);
										foldernameLbl.addStyleName("h3");

										turnBackMainLayout(parentLayout,
												foldernameLbl, layout,
												externalDriveEditLayout);
									}
								} catch (Exception e) {
									throw new MyCollabException(e);
								}
							}
						});
				saveBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
				saveBtn.setIcon(MyCollabResource
						.newResource("icons/16/save.png"));
				layout.addComponent(saveBtn);

				Button cancelBtn = new Button(
						AppContext
								.getMessage(GenericI18Enum.BUTTON_CANCEL),
						new ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								turnBackMainLayout(parentLayout, lbl, layout,
										externalDriveEditLayout);
							}
						});
				cancelBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
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
