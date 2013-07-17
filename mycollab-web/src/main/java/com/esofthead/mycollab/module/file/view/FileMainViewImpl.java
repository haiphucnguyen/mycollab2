package com.esofthead.mycollab.module.file.view;

import java.util.List;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.StreamDownloadResourceFactory;
import com.esofthead.mycollab.module.file.domain.criteria.FileSearchCriteria;
import com.esofthead.mycollab.module.file.view.components.FileDownloadWindow;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.Separator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
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
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.CollapseEvent;
import com.vaadin.ui.Tree.ExpandEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

@ViewComponent
public class FileMainViewImpl extends AbstractView implements FileMainView {
	private static final long serialVersionUID = 1L;

	private Tree treeMenu;
	private FilterPanel filterPanel;
	private ResourceTableDisplay tableDisplay;
	private HorizontalLayout controllGroupBtn;
	private final ResourceService resourceService;

	private Folder baseFolder;

	private String rootPath;

	private String rootFolderName;

	class FilterPanel extends GenericSearchPanel<FileSearchCriteria> {
		private static final long serialVersionUID = 1L;
		protected FileSearchCriteria searchCriteria;
		private ComponentContainer menuBar = null;
		private HorizontalLayout basicSearchBody;

		public HorizontalLayout getBasicSearchBody() {
			return basicSearchBody;
		}

		public FilterPanel(final ComponentContainer menuBar) {
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

	private class ResourceTableDisplay extends Table {
		private static final long serialVersionUID = 1L;

		public ResourceTableDisplay() {

			this.addGeneratedColumn("uuid", new Table.ColumnGenerator() {
				private static final long serialVersionUID = 1L;

				@Override
				public Object generateCell(final Table source,
						final Object itemId, final Object columnId) {

					final Resource resource = ResourceTableDisplay.this
							.getResource(itemId);
					if (resource != null) {
						if (resource.getUuid().equals("firstLine")) {
							Button upBtn = new Button();
							upBtn.addStyleName("link");

							upBtn.setIcon(MyCollabResource
									.newResource("icons/16/ecm/up_to_root.png"));
							upBtn.setDescription("Up to root Folder");

							upBtn.addListener(new ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									displayResources(rootPath, rootFolderName);
								}
							});
							return upBtn;
						}
					}
					final PopupButton resourceSettingPopupBtn = new PopupButton();
					final VerticalLayout filterBtnLayout = new VerticalLayout();

					final Button renameBtn = new Button("Rename",
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(final ClickEvent event) {
								}
							});
					renameBtn.setStyleName("link");
					filterBtnLayout.addComponent(renameBtn);

					final Button downloadBtn = new Button("Download",
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

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
												.open(downloadResource,
														"_blank");
									} else {
										final com.vaadin.terminal.Resource downloadResource = StreamDownloadResourceFactory
												.getStreamFolderResource(((Folder) resource)
														.getPath());
										AppContext
												.getApplication()
												.getMainWindow()
												.open(downloadResource,
														"_blank");
									}

								}
							});
					downloadBtn.setStyleName("link");
					filterBtnLayout.addComponent(downloadBtn);

					final Button deleteBtn = new Button("Delete",
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(final ClickEvent event) {
								}
							});
					deleteBtn.setStyleName("link");
					filterBtnLayout.addComponent(deleteBtn);

					final Button moveFolderBtn = new Button("Move",
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
								}
							});
					moveFolderBtn.setStyleName("link");
					filterBtnLayout.addComponent(moveFolderBtn);

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

					if (resource != null) {
						if (resource.getUuid().equals("firstLine")) {
							Button gotoParentBtn = new Button("...");
							gotoParentBtn.addStyleName("link");
							gotoParentBtn.setDescription("Up to parent Folder");

							gotoParentBtn.addListener(new ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									Folder parentFolder = resourceService
											.getParentFolder(baseFolder
													.getPath());

									displayResourcesInTable(parentFolder);
								}
							});
							return gotoParentBtn;
						}
					}

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
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(final ClickEvent event) {
									if (resource instanceof Folder) {
										displayResourcesInTable(resource
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
				private static final long serialVersionUID = 1L;

				@Override
				public Object generateCell(final Table source,
						final Object itemId, final Object columnId) {
					final Resource resource = ResourceTableDisplay.this
							.getResource(itemId);
					if (resource != null) {
						if (resource.getUuid().equals("firstLine")) {
							return "";
						}
					}
					return new Label(AppContext.formatDateTime(resource
							.getCreated().getTime()));
				}
			});

			this.addGeneratedColumn("size", new Table.ColumnGenerator() {
				private static final long serialVersionUID = 1L;

				@Override
				public Object generateCell(final Table source,
						final Object itemId, final Object columnId) {
					final Resource resource = ResourceTableDisplay.this
							.getResource(itemId);
					if (resource != null) {
						if (resource.getUuid().equals("firstLine")) {
							return "";
						}
					}
					return new Label(Math.round(resource.getSize() / 1024) + "");
				}
			});

			this.setColumnExpandRatio("path", 1);
			this.setColumnWidth("uuid", 22);
			this.setColumnWidth("size", UIConstants.TABLE_S_LABEL_WIDTH);
			this.setColumnWidth("created", UIConstants.TABLE_DATE_TIME_WIDTH);
		}

		private Resource getResource(final Object itemId) {
			return null;
		}
	}

	public FileMainViewImpl() {
		resourceService = AppContext.getSpringBean(ResourceService.class);
		this.setSpacing(true);
		this.setMargin(false);

		HorizontalLayout mainView = new HorizontalLayout();
		mainView.setSpacing(true);
		mainView.setMargin(true);
		mainView.setWidth("1130px");

		final HorizontalLayout resourceContainer = new HorizontalLayout();
		resourceContainer.setMargin(true);

		this.treeMenu = new Tree();
		this.treeMenu.setMultiSelect(false);
		this.treeMenu.setSelectable(true);
		this.treeMenu.setImmediate(true);

		resourceContainer.addComponent(this.treeMenu);

		this.treeMenu.addListener(new Tree.ExpandListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void nodeExpand(final ExpandEvent event) {
				final Folder expandFolder = (Folder) event.getItemId();
				final List<Folder> subFolders = resourceService
						.getSubFolders(expandFolder.getPath());

				treeMenu.setItemIcon(expandFolder, MyCollabResource
						.newResource("icons/16/ecm/folder_open.png"));

				if (subFolders != null) {
					for (final Folder subFolder : subFolders) {
						expandFolder.addChild(subFolder);
						treeMenu.addItem(subFolder);

						treeMenu.setItemIcon(subFolder, MyCollabResource
								.newResource("icons/16/ecm/folder_close.png"));
						treeMenu.setItemCaption(subFolder, subFolder.getName());
						treeMenu.setParent(subFolder, expandFolder);
					}
				}
			}
		});

		this.treeMenu.addListener(new Tree.CollapseListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void nodeCollapse(final CollapseEvent event) {
				final Folder collapseFolder = (Folder) event.getItemId();
				treeMenu.setItemIcon(collapseFolder, MyCollabResource
						.newResource("icons/16/ecm/folder_close.png"));
				final List<Folder> childs = collapseFolder.getChilds();
				for (final Folder subFolder : childs) {
					treeMenu.removeItem(subFolder);
				}

				childs.clear();
			}
		});

		this.treeMenu.addListener(new ItemClickEvent.ItemClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(final ItemClickEvent event) {

			}
		});
		mainView.addComponent(resourceContainer);

		VerticalLayout mainBodyLayout = new VerticalLayout();

		filterPanel = new FilterPanel(null);
		mainBodyLayout.addComponent(filterPanel);

		// Construct controllGroupBtn
		controllGroupBtn = new HorizontalLayout();
		controllGroupBtn.setMargin(true);
		controllGroupBtn.setSpacing(true);

		Button downloadBtn = new Button("Download");
		downloadBtn.addStyleName(UIConstants.THEME_ROUND_BUTTON);
		UiUtils.addComponent(controllGroupBtn, downloadBtn,
				Alignment.MIDDLE_LEFT);

		Button moveToBtn = new Button("Move to");
		moveToBtn.addStyleName(UIConstants.THEME_ROUND_BUTTON);
		UiUtils.addComponent(controllGroupBtn, moveToBtn, Alignment.MIDDLE_LEFT);
		mainBodyLayout.addComponent(controllGroupBtn);

		// Construct Table with no header
		tableDisplay = new ResourceTableDisplay();
		tableDisplay.setWidth("100%");
		mainBodyLayout.addComponent(tableDisplay);

//		Separator separator = new Separator();
//		separator.setWidth("100%");
//		mainView.addComponent(separator);

		mainView.addComponent(mainBodyLayout);

		mainView.setExpandRatio(mainBodyLayout, 1.0f);
		// mainView.setExpandRatio(resourceContainer, 1.0f);

		this.addComponent(mainView);
		this.setComponentAlignment(mainView, Alignment.MIDDLE_CENTER);

		String rootPath = String.format("%d/.fm", AppContext.getAccountId());
		displayResources(rootPath, "Documents");
	}

	private void displayResourcesInTable(final Folder folder) {
		final List<Resource> resources = this.resourceService
				.getResources(folder.getPath());

		tableDisplay.setContainerDataSource(new BeanItemContainer<Resource>(
				Resource.class, resources));

		tableDisplay.setVisibleColumns(new String[] { "uuid", "path", "size",
				"created" });
		// tableDisplay.setColumnHeaders(new String[] { "", "Name", "Size (Kb)",
		// "Created" });

		this.baseFolder = folder;
	}

	private void displayResourcesInTable(final String foldername) {
		List<Folder> childs = resourceService.getSubFolders(this.baseFolder
				.getPath());
		if (childs == null) {
			childs = this.resourceService.getSubFolders(this.baseFolder
					.getPath());

			for (final Folder subFolder : childs) {
				this.baseFolder.addChild(subFolder);
				this.treeMenu.addItem(new Object[] {
						subFolder.getName(),
						AppContext.formatDateTime(subFolder.getCreated()
								.getTime()) });
				this.treeMenu.setItemCaption(subFolder, subFolder.getName());
				this.treeMenu.setParent(subFolder, this.baseFolder);
				if (foldername.equals(subFolder.getName())) {
					treeMenu.collapseItem(subFolder);
					this.displayResourcesInTable(subFolder);
				} else {
					this.treeMenu.setItemIcon(subFolder, MyCollabResource
							.newResource("icons/16/ecm/folder_close.png"));
				}
			}
		} else {
			for (final Folder subFolder : childs) {
				if (foldername.equals(subFolder.getName())) {
					this.treeMenu.collapseItem(subFolder);
					this.treeMenu.setValue(subFolder);
					this.displayResourcesInTable(subFolder);
				}
			}
		}
	}

	public void displayResources(String rootPath, String rootFolderName) {
		this.treeMenu.removeAllItems();
		this.rootPath = rootPath;
		this.rootFolderName = rootFolderName;

		this.baseFolder = new Folder();
		this.baseFolder.setPath(this.rootPath);
		this.treeMenu.addItem(this.baseFolder);
		this.treeMenu.setItemCaption(this.baseFolder, rootFolderName);

		Folder shareActionFolder = new Folder();
		shareActionFolder.setPath(this.rootPath);

		this.treeMenu.addItem(shareActionFolder);
		this.treeMenu.setItemCaption(shareActionFolder, "Share with me");

		this.treeMenu.collapseItem(this.baseFolder);
		this.displayResourcesInTable(this.baseFolder);
	}

	@Override
	public void display() {
	}
}
