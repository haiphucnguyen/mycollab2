package com.esofthead.mycollab.module.file.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.easyuploads.MultiFileUploadExt;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.hene.splitbutton.PopupButtonControl;

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
import com.esofthead.mycollab.shell.view.ScreenSize;
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
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
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

@ViewComponent
public class FileMainViewImpl extends AbstractView implements FileMainView {
	private static final long serialVersionUID = 1L;
	private Tree menuTree;
	private FilterPanel filterPanel;
	private HorizontalLayout controllGroupBtn;
	private final ResourceService resourceService;
	private Folder baseFolder;
	private String rootPath;
	private String rootFolderName;
	private List<Resource> lstCheckedResource;
	private ItemResourceContainerLayout itemResourceContainerLayout;

	public FileMainViewImpl() {
		resourceService = AppContext.getSpringBean(ResourceService.class);
		this.setSpacing(true);
		this.setMargin(false);

		HorizontalLayout mainView = new HorizontalLayout();
		mainView.setSpacing(true);
		mainView.setMargin(true);
		if (ScreenSize.hasSupport1280Pixels()) {
			mainView.setWidth("1400px");
		} else {
			mainView.setWidth("1130px");
		}
		final HorizontalLayout menuBarContainerHorizontalLayout = new HorizontalLayout();
		menuBarContainerHorizontalLayout.setMargin(true);

		final VerticalLayout menuLayout = new VerticalLayout();
		menuLayout.setWidth("200px");
		menuBarContainerHorizontalLayout.addComponent(menuLayout);

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
				final List<Folder> childs = collapseFolder.getChilds();
				for (final Folder subFolder : childs) {
					menuTree.removeItem(subFolder);
				}

				childs.clear();
			}
		});

		this.menuTree.addListener(new ItemClickEvent.ItemClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(final ItemClickEvent event) {
				final Folder item = (Folder) event.getItemId();
				FileMainViewImpl.this.baseFolder = item;
				itemResourceContainerLayout.constructBody(item);
			}
		});

		HorizontalLayout shareActionLayout = new HorizontalLayout();
		final Embedded shareIcon = new Embedded();
		shareIcon.setSource(MyCollabResource
				.newResource("icons/24/shareICon.png"));
		shareActionLayout.addComponent(shareIcon);
		shareActionLayout.setComponentAlignment(shareIcon,
				Alignment.MIDDLE_CENTER);
		Button shareBtnLink = new Button("Shared with me");
		shareBtnLink.addStyleName("link");
		shareActionLayout.addComponent(shareBtnLink);
		shareActionLayout.setComponentAlignment(shareBtnLink,
				Alignment.MIDDLE_CENTER);
		menuLayout.addComponent(shareActionLayout);

		mainView.addComponent(menuBarContainerHorizontalLayout);

		Separator separator = new Separator();
		mainView.addComponent(separator);

		VerticalLayout mainBodyLayout = new VerticalLayout();
		mainBodyLayout.setSpacing(true);
		mainBodyLayout.addStyleName("box-no-border-left");

		filterPanel = new FilterPanel();
		mainBodyLayout.addComponent(filterPanel);

		// Construct controllGroupBtn
		controllGroupBtn = new HorizontalLayout();
		controllGroupBtn.setMargin(true, false, false, true);
		controllGroupBtn.setSpacing(true);

		final Button selectAllBtn = new Button();
		selectAllBtn.setIcon(MyCollabResource
				.newResource("icons/16/checkbox_empty.png"));
		selectAllBtn.setValue(false);

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
		PopupButtonControl tableActionControls = new PopupButtonControl(
				"selectAll", selectAllBtn);
		tableActionControls.setWidth("70px");
		UiUtils.addComponent(controllGroupBtn, tableActionControls,
				Alignment.MIDDLE_LEFT);

		Button goUpBtn = new Button();
		goUpBtn.setIcon(new ThemeResource("icons/16/ecm/up_to_root.png"));
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
				}
			}
		});
		goUpBtn.setStyleName(UIConstants.THEME_ROUND_BUTTON);
		UiUtils.addComponent(controllGroupBtn, goUpBtn, Alignment.MIDDLE_LEFT);

		Button createBtn = new Button("Create", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				AddNewFolderWindow addnewFolderWindow = new AddNewFolderWindow();
				FileMainViewImpl.this.getWindow().addWindow(addnewFolderWindow);
			}
		});
		createBtn.addStyleName(UIConstants.THEME_ROUND_BUTTON);
		UiUtils.addComponent(controllGroupBtn, createBtn, Alignment.MIDDLE_LEFT);

		Button uploadBtn = new Button("Upload", new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				MultiUploadContentWindow multiUploadWindow = new MultiUploadContentWindow();
				FileMainViewImpl.this.getWindow().addWindow(multiUploadWindow);
			}
		});
		uploadBtn.addStyleName(UIConstants.THEME_ROUND_BUTTON);
		UiUtils.addComponent(controllGroupBtn, uploadBtn, Alignment.MIDDLE_LEFT);

		Button downloadBtn = new Button("Download", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (lstCheckedResource != null && lstCheckedResource.size() > 0) {
					List<String> lstPath = new ArrayList<String>();
					for (Resource res : lstCheckedResource) {
						lstPath.add(res.getPath());
					}

					final com.vaadin.terminal.Resource downloadResource = StreamDownloadResourceFactory
							.getStreamFolderResource(lstPath
									.toArray(new String[lstPath.size()]));

					AppContext.getApplication().getMainWindow()
							.open(downloadResource, "_blank");
				}
			}
		});
		downloadBtn.addStyleName(UIConstants.THEME_ROUND_BUTTON);
		UiUtils.addComponent(controllGroupBtn, downloadBtn,
				Alignment.MIDDLE_LEFT);

		Button moveToBtn = new Button("Move to", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				MoveResourceWindow moveResourceWindow = new MoveResourceWindow(
						lstCheckedResource,
						FileMainViewImpl.this.resourceService);
				FileMainViewImpl.this.getWindow().addWindow(moveResourceWindow);
			}
		});
		moveToBtn.addStyleName(UIConstants.THEME_ROUND_BUTTON);
		UiUtils.addComponent(controllGroupBtn, moveToBtn, Alignment.MIDDLE_LEFT);

		Button deleteBtn = new Button("Delete", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				ConfirmDialogExt.show(
						FileMainViewImpl.this.getWindow(),
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
											FileMainViewImpl.this.resourceService
													.removeResource(res
															.getPath());
											itemResourceContainerLayout
													.constructBody(FileMainViewImpl.this.baseFolder);

											FileMainViewImpl.this.menuTree
													.collapseItem(FileMainViewImpl.this.baseFolder);
											FileMainViewImpl.this.menuTree
													.collapseItem(FileMainViewImpl.this.baseFolder);
											FileMainViewImpl.this
													.getWindow()
													.showNotification(
															"Delete successfully.");
											FileMainViewImpl.this.lstCheckedResource = new ArrayList<Resource>();
										}
									}
								}
							}
						});
			}
		});
		deleteBtn.addStyleName(UIConstants.THEME_ROUND_BUTTON);
		UiUtils.addComponent(controllGroupBtn, deleteBtn, Alignment.MIDDLE_LEFT);

		mainBodyLayout.addComponent(controllGroupBtn);

		String rootPath = String.format("%d/.crm", AppContext.getAccountId());
		this.baseFolder = new Folder();
		this.baseFolder.setPath(rootPath);

		itemResourceContainerLayout = new ItemResourceContainerLayout(
				FileMainViewImpl.this.baseFolder, resourceService);
		itemResourceContainerLayout.setWidth("100%");

		mainBodyLayout.addComponent(itemResourceContainerLayout);

		mainView.addComponent(mainBodyLayout);

		mainView.setExpandRatio(mainBodyLayout, 1.0f);

		this.addComponent(mainView);
		this.setComponentAlignment(mainView, Alignment.MIDDLE_CENTER);

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
			return new HorizontalLayout();
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

				Label filterLabel = new Label("Filter: ");
				filterLabel.addStyleName("h3");
				UiUtils.addComponent(basicSearchBody, filterLabel,
						Alignment.MIDDLE_CENTER);

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
		private List<CheckBox> listAllCheckBox;
		private VerticalLayout mainLayout;

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
			if (mainLayout != null) {
				this.removeAllComponents();
			}
			mainLayout = new VerticalLayout();
			mainLayout.setSpacing(false);
			List<Resource> lstResource = resourceService.getResources(curFolder
					.getPath());
			this.addComponent(new Hr());
			if (lstResource != null && lstResource.size() > 0) {
				for (Resource res : lstResource) {
					mainLayout
							.addComponent(constructOneIteamResourceLayout(res));
					mainLayout.addComponent(new Hr());
				}
			}
			this.addComponent(mainLayout);
		}

		private HorizontalLayout constructOneIteamResourceLayout(
				final Resource res) {
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
						.newResource("icons/48/Folder-icon.png"));
			else
				resourceIcon.setSource(MyCollabResource
						.newResource("icons/48/file_blue_icon.png"));
			resIconWapper.setWidth("70px");
			resIconWapper.addComponent(resourceIcon);

			layout.addComponent(resIconWapper);
			layout.setComponentAlignment(resIconWapper, Alignment.MIDDLE_LEFT);

			VerticalLayout informationLayout = new VerticalLayout();

			Button resourceLinkBtn = new Button(res.getName(),
					new ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							if (res instanceof Folder) {
								FileMainViewImpl.this.baseFolder = (Folder) res;
								itemResourceContainerLayout
										.constructBody((Folder) res);
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
				DecimalFormat df = new DecimalFormat("#.##");
				moreInfoAboutResLayout.addComponent(new Label(df.format(size)
						+ " KB"));
			}
			informationLayout.addComponent(moreInfoAboutResLayout);

			layout.addComponent(informationLayout);
			layout.setComponentAlignment(informationLayout,
					Alignment.MIDDLE_LEFT);
			layout.setExpandRatio(informationLayout, 1.0f);

			// CssLayout shareIconWapper = new CssLayout();
			// final Embedded shareIcon = new Embedded();
			// shareIcon.setSource(MyCollabResource
			// .newResource("icons/24/shareICon.png"));
			// shareIconWapper.addComponent(shareIcon);
			// shareIconWapper.addComponent(new Label("Share"));
			// shareIconWapper.setWidth("100px");
			//
			// layout.addComponent(shareIconWapper);
			// layout.setComponentAlignment(shareIconWapper,
			// Alignment.MIDDLE_RIGHT);

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

			// ----------------Is need or
			// not--------------------------------------
			final Button downloadBtn = new Button("Download",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
						}
					});
			downloadBtn.addStyleName("link");
			filterBtnLayout.addComponent(downloadBtn);

			final Button moveBtn = new Button("Move to",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
						}
					});
			moveBtn.addStyleName("link");
			filterBtnLayout.addComponent(moveBtn);

			final Button deleteBtn = new Button("Delete",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
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
	}

	private void displayResourcesInListLayout(final Folder folder) {
		// if
		// (!itemResourceContainerLayout.getFolder().equals(folder.getPath())) {
		// itemResourceContainerLayout.removeAllComponents();
		// itemResourceContainerLayout = new IteamResourceContainerLayout(
		// folder, resourceService);
		// }
		this.baseFolder = folder;
	}

	// when click on IteamResouce , we call this function
	private void displayResourcesInTable(final String foldername) {
		List<Folder> childs = resourceService.getSubFolders(this.baseFolder
				.getPath());
		if (childs == null) {
			childs = this.resourceService.getSubFolders(this.baseFolder
					.getPath());

			for (final Folder subFolder : childs) {
				this.baseFolder.addChild(subFolder);
				this.menuTree.addItem(new Object[] {
						subFolder.getName(),
						AppContext.formatDateTime(subFolder.getCreated()
								.getTime()) });
				this.menuTree.setItemCaption(subFolder, subFolder.getName());
				this.menuTree.setParent(subFolder, this.baseFolder);
				if (foldername.equals(subFolder.getName())) {
					menuTree.collapseItem(subFolder);
					this.displayResourcesInListLayout(subFolder);
				} else {
					this.menuTree.setItemIcon(subFolder, MyCollabResource
							.newResource("icons/16/ecm/folder_close.png"));
				}
			}
		} else {
			for (final Folder subFolder : childs) {
				if (foldername.equals(subFolder.getName())) {
					this.menuTree.collapseItem(subFolder);
					this.menuTree.setValue(subFolder);
					this.displayResourcesInListLayout(subFolder);
				}
			}
		}
	}

	public void displayResources(String rootPath, String rootFolderName) {
		this.menuTree.removeAllItems();
		this.rootPath = rootPath;
		this.rootFolderName = rootFolderName;

		this.baseFolder = new Folder();
		this.baseFolder.setPath(this.rootPath);
		this.menuTree.addItem(this.baseFolder);
		this.menuTree.setItemCaption(this.baseFolder, rootFolderName);
		this.menuTree.setItemIcon(this.baseFolder,
				MyCollabResource.newResource("icons/16/ecm/folder_close.png"));

		this.menuTree.collapseItem(this.baseFolder);
		this.displayResourcesInListLayout(this.baseFolder);
	}

	@Override
	public void display() {
		displayResources(rootPath, "My Documents");
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
		public void displayAfterMoveSuccess(Folder folder) {
			FileMainViewImpl.this.itemResourceContainerLayout
					.constructBody(FileMainViewImpl.this.baseFolder);
			FileMainViewImpl.this.getWindow().showNotification(
					"Move asset(s) successfully.");
			FileMainViewImpl.this.lstCheckedResource = new ArrayList<Resource>();
		}

		@Override
		protected void displayFiles() {
			String rootPath = FileMainViewImpl.this.rootPath;
			this.folderTree.removeAllItems();
			this.rootPath = rootPath;

			this.baseFolder = new Folder();
			baseFolder.setPath(this.rootPath);
			this.folderTree.addItem(new Object[] {
					FileMainViewImpl.this.rootFolderName, "" }, baseFolder);
			this.folderTree.setItemCaption(baseFolder,
					FileMainViewImpl.this.rootFolderName);

			this.folderTree.setCollapsed(baseFolder, false);
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
								folder.setPath(newPath);
								menuTree.removeItem(folder);
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
								final String baseFolderPath = (FileMainViewImpl.this.baseFolder == null) ? FileMainViewImpl.this.rootPath
										: FileMainViewImpl.this.baseFolder
												.getPath();
								final Folder newFolder = FileMainViewImpl.this.resourceService
										.createNewFolder(baseFolderPath,
												folderVal,
												AppContext.getUsername());
								FileMainViewImpl.this.baseFolder
										.addChild(newFolder);
								FileMainViewImpl.this.menuTree
										.addItem(newFolder);
								FileMainViewImpl.this.menuTree.setItemCaption(
										newFolder, newFolder.getName());
								FileMainViewImpl.this.menuTree.setParent(
										newFolder,
										FileMainViewImpl.this.baseFolder);
								FileMainViewImpl.this.menuTree.setItemIcon(
										newFolder,
										MyCollabResource
												.newResource("icons/16/ecm/folder_close.png"));
								FileMainViewImpl.this.menuTree
										.expandItem(FileMainViewImpl.this.baseFolder);

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
		private final MultiFileUploadExt multiUploadField;

		public MultiUploadContentWindow() {
			super("Multi Upload Content");
			this.setWidth("500px");
			((VerticalLayout) this.getContent()).setMargin(false, false, true,
					false);
			this.setModal(true);
			final AttachmentPanel attachments = new AttachmentPanel();

			this.layoutHelper = new GridFormLayoutHelper(1, 2, "100%", "167px",
					Alignment.MIDDLE_LEFT);

			this.multiUploadField = (MultiFileUploadExt) this.layoutHelper
					.addComponent(new MultiFileUploadExt(attachments), "File",
							0, 0);
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
}
