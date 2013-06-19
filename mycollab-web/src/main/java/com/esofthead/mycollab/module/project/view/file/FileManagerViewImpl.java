package com.esofthead.mycollab.module.project.view.file;

import java.io.InputStream;
import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.easyuploads.SingleFileUploadField;
import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
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

@ViewComponent
public class FileManagerViewImpl extends AbstractView implements
		FileManagerView {
	private static final long serialVersionUID = 1L;

	private TreeTable folderTree;
	private ResourceTableDisplay resourceTable;
	private String projectPath;

	private Folder baseFolder;

	private ResourceService resourceService;

	public FileManagerViewImpl() {
		this.setWidth("100%");
		resourceService = AppContext.getSpringBean(ResourceService.class);

		HorizontalLayout menuBar = new HorizontalLayout();

		Button addFolderBtn = new Button("Add Folder",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						baseFolder = (Folder) folderTree.getValue();
						FileManagerViewImpl.this.getWindow().addWindow(
								new AddNewFolderWindow());
					}
				});

		menuBar.addComponent(addFolderBtn);

		Button uploadBtn = new Button("Upload", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				FileManagerViewImpl.this.getWindow().addWindow(
						new UploadContentWindow());

			}
		});
		menuBar.addComponent(uploadBtn);

		Button deleteBtn = new Button("Delete", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (baseFolder != null
						&& !projectPath.equals(baseFolder.getPath())) {
					ConfirmDialog.show(
							FileManagerViewImpl.this.getWindow(),
							"Are you sure to delete folder "
									+ baseFolder.getName() + " ?",
							new ConfirmDialog.Listener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void onClose(ConfirmDialog dialog) {
									// TODO Auto-generated method stub

								}
							});
				}

			}
		});

		menuBar.addComponent(deleteBtn);

		this.addComponent(menuBar);

		HorizontalLayout resourceContainer = new HorizontalLayout();
		resourceContainer.setSizeFull();

		folderTree = new TreeTable();
		folderTree.setMultiSelect(false);
		folderTree.setImmediate(true);
		folderTree.addContainerProperty("Name", String.class, "");
		folderTree.addContainerProperty("Date Modified", String.class, "");
		folderTree.setColumnWidth("Date Modified",
				UIConstants.TABLE_DATE_TIME_WIDTH);
		folderTree.setColumnExpandRatio("Name", 1.0f);
		folderTree.setWidth("300px");

		resourceContainer.addComponent(folderTree);

		folderTree.addListener(new Tree.ExpandListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void nodeExpand(ExpandEvent event) {
				Folder expandFolder = (Folder) event.getItemId();
				List<Folder> subFolders = resourceService
						.getSubFolders(expandFolder.getPath());

				folderTree.setItemIcon(expandFolder, MyCollabResource
						.newResource("icons/16/ecm/folder_open.png"));

				if (subFolders != null) {
					for (Folder subFolder : subFolders) {
						expandFolder.addChild(subFolder);
						folderTree.addItem(
								new Object[] {
										subFolder.getName(),
										AppContext.formatDateTime(subFolder
												.getCreated().getTime()) },
								subFolder);
						folderTree.setItemIcon(subFolder, MyCollabResource
								.newResource("icons/16/ecm/folder_close.png"));
						folderTree.setItemCaption(subFolder,
								subFolder.getName());
						folderTree.setParent(subFolder, expandFolder);
					}
				}
			}
		});

		folderTree.addListener(new Tree.CollapseListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void nodeCollapse(CollapseEvent event) {
				Folder collapseFolder = (Folder) event.getItemId();
				folderTree.setItemIcon(collapseFolder, MyCollabResource
						.newResource("icons/16/ecm/folder_close.png"));
				List<Folder> childs = collapseFolder.getChilds();
				for (Folder subFolder : childs) {
					folderTree.removeItem(subFolder);
				}

				childs.clear();
			}
		});

		folderTree.addListener(new ItemClickEvent.ItemClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent event) {
				baseFolder = (Folder) event.getItemId();
				displayResourcesInTable(baseFolder);
			}
		});

		resourceTable = new ResourceTableDisplay();
		resourceTable.setWidth("100%");
		resourceContainer.addComponent(resourceTable);
		resourceContainer.setExpandRatio(resourceTable, 1.0f);

		this.addComponent(resourceContainer);
	}

	private void displayResourcesInTable(Folder folder) {
		List<Resource> resources = resourceService.getResources(folder
				.getPath());
		resourceTable.setContainerDataSource(new BeanItemContainer<Resource>(
				Resource.class, resources));
		resourceTable.setVisibleColumns(new String[] { "uuid", "path", "size",
				"created", "createdBy" });
		resourceTable.setColumnHeaders(new String[] { "", "Name", "Size",
				"Created", "By" });

	}

	private void displayResourcesInTable(String foldername) {
		List<Folder> childs = baseFolder.getChilds();
		if (childs == null) {
			childs = resourceService.getSubFolders(baseFolder.getPath());

			for (Folder subFolder : childs) {
				baseFolder.addChild(subFolder);
				folderTree.addItem(
						new Object[] {
								subFolder.getName(),
								AppContext.formatDateTime(subFolder
										.getCreated().getTime()) }, subFolder);
				folderTree.setItemCaption(subFolder, subFolder.getName());
				folderTree.setParent(subFolder, baseFolder);
				if (foldername.equals(subFolder.getName())) {
					folderTree.setCollapsed(subFolder, false);
					displayResourcesInTable(subFolder);
					baseFolder = subFolder;
				} else {
					folderTree.setItemIcon(subFolder, MyCollabResource
							.newResource("icons/16/ecm/folder_close.png"));
				}
			}
		} else {
			for (Folder subFolder : childs) {
				if (foldername.equals(subFolder.getName())) {
					folderTree.setCollapsed(subFolder, false);
					folderTree.setValue(subFolder);
					displayResourcesInTable(subFolder);
					baseFolder = subFolder;
				}
			}
		}
	}

	@Override
	public void displayProjectFiles() {
		folderTree.removeAllItems();
		int projectId = CurrentProjectVariables.getProjectId();
		projectPath = String.format("%d/project/%d", AppContext.getAccountId(),
				projectId);

		baseFolder = new Folder();
		baseFolder.setPath(projectPath);
		folderTree.addItem(new Object[] { baseFolder.getName(), "" },
				baseFolder);
		folderTree.setItemCaption(baseFolder, CurrentProjectVariables
				.getProject().getName());

		folderTree.setCollapsed(baseFolder, false);
		displayResourcesInTable(baseFolder);
	}

	/**
	 * Window to ask user enter the new folder
	 * 
	 * @author haiphucnguyen
	 * 
	 */
	private class AddNewFolderWindow extends Window {
		private static final long serialVersionUID = 1L;

		private TextField folderName;

		public AddNewFolderWindow() {
			this.setWidth("500px");
			this.setModal(true);
			this.setCaption("New Folder");
			center();

			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);
			layout.addComponent(new Label("Enter folder name: "));

			folderName = new TextField();
			layout.addComponent(folderName);

			this.addComponent(layout);

			HorizontalLayout controlsLayout = new HorizontalLayout();

			Button saveBtn = new Button(
					LocalizationHelper
							.getMessage(GenericI18Enum.BUTTON_SAVE_LABEL),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {

							String folderVal = (String) folderName.getValue();
							if (folderVal != null
									&& !folderVal.trim().equals("")) {
								// TODO: check folder name with valid characters
								String baseFolderPath = (baseFolder == null) ? projectPath
										: baseFolder.getPath();
								Folder newFolder = resourceService
										.createNewFolder(baseFolderPath,
												folderVal,
												AppContext.getUsername());
								baseFolder.addChild(newFolder);
								folderTree.addItem(
										new Object[] {
												newFolder.getName(),
												AppContext
														.formatDateTime(newFolder
																.getCreated()
																.getTime()) },
										newFolder);
								folderTree.setItemCaption(newFolder,
										newFolder.getName());
								folderTree.setParent(newFolder, baseFolder);
								if (folderTree.isCollapsed(baseFolder)) {
									folderTree.setCollapsed(baseFolder, false);
								}
								AddNewFolderWindow.this.close();

							}
						}
					});
			controlsLayout.addComponent(saveBtn);

			Button cancelBtn = new Button(
					LocalizationHelper
							.getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							AddNewFolderWindow.this.close();

						}
					});
			controlsLayout.addComponent(cancelBtn);

			this.addComponent(controlsLayout);
		}
	}

	private class UploadContentWindow extends Window {
		private static final long serialVersionUID = 1L;

		private GridFormLayoutHelper layoutHelper;
		private TextArea descField;
		private SingleFileUploadField uploadField;

		public UploadContentWindow() {
			super("Upload Content");
			this.setWidth("500px");
			this.setModal(true);

			layoutHelper = new GridFormLayoutHelper(1, 2);

			uploadField = (SingleFileUploadField) layoutHelper.addComponent(
					new SingleFileUploadField(), "File", 0, 0);
			descField = (TextArea) layoutHelper.addComponent(new TextArea(),
					"Description", 0, 1);

			this.addComponent(layoutHelper.getLayout());

			HorizontalLayout controlsLayout = new HorizontalLayout();
			controlsLayout.setWidth("100%");

			Button uploadBtn = new Button("Upload", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					// TODO Auto-generated method stub
					InputStream contentStream = uploadField
							.getContentAsStream();
					if (contentStream != null) {
						Content content = new Content();
						content.setDescription((String) descField.getValue());
						content.setPath(baseFolder.getPath() + "/"
								+ uploadField.getFileName());
						resourceService.saveContent(content, contentStream);
						UploadContentWindow.this.close();
						displayResourcesInTable(baseFolder);
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

			controlsLayout.addComponent(uploadBtn);

			Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					UploadContentWindow.this.close();
				}
			});
			controlsLayout.addComponent(cancelBtn);

			this.addComponent(controlsLayout);
		}

	}

	@SuppressWarnings("serial")
	private class ResourceTableDisplay extends Table {
		public ResourceTableDisplay() {
			this.addGeneratedColumn("uuid", new Table.ColumnGenerator() {
				private static final long serialVersionUID = 1L;

				@Override
				public Object generateCell(Table source, Object itemId,
						Object columnId) {

					PopupButton resourceSettingPopupBtn = new PopupButton();
					VerticalLayout filterBtnLayout = new VerticalLayout();

					Button renameBtn = new Button("Rename",
							new Button.ClickListener() {

								@Override
								public void buttonClick(ClickEvent event) {
									// TODO Auto-generated method stub

								}
							});
					renameBtn.setStyleName("link");
					filterBtnLayout.addComponent(renameBtn);

					Button downloadBtn = new Button("Download",
							new Button.ClickListener() {

								@Override
								public void buttonClick(ClickEvent event) {
									// TODO Auto-generated method stub

								}
							});
					downloadBtn.setStyleName("link");
					filterBtnLayout.addComponent(downloadBtn);

					Button deleteBtn = new Button("Delete",
							new Button.ClickListener() {

								@Override
								public void buttonClick(ClickEvent event) {
									// TODO Auto-generated method stub

								}
							});
					deleteBtn.setStyleName("link");
					filterBtnLayout.addComponent(deleteBtn);

					filterBtnLayout.setMargin(true);
					filterBtnLayout.setSpacing(true);
					filterBtnLayout.setWidth("100px");
					resourceSettingPopupBtn.setIcon(MyCollabResource
							.newResource("icons/12/project/task_menu.png"));
					resourceSettingPopupBtn.setStyleName("link");
					resourceSettingPopupBtn.addComponent(filterBtnLayout);
					return resourceSettingPopupBtn;
				}
			});

			this.addGeneratedColumn("path", new Table.ColumnGenerator() {
				private static final long serialVersionUID = 1L;

				@Override
				public Object generateCell(Table source, Object itemId,
						Object columnId) {
					final Resource resource = ResourceTableDisplay.this
							.getResource(itemId);
					String path = resource.getPath();
					int pathIndex = path.lastIndexOf("/");
					if (pathIndex > -1) {
						path = path.substring(pathIndex + 1);
					}
					HorizontalLayout resourceLabel = new HorizontalLayout();

					com.vaadin.terminal.Resource iconResource = null;
					if (resource instanceof Content) {
						iconResource = MyCollabResource
								.newResource("icons/16/ecm/file.png");
					} else {
						iconResource = MyCollabResource
								.newResource("icons/16/ecm/folder_close.png");
					}
					Embedded iconEmbed = new Embedded(null, iconResource);

					resourceLabel.addComponent(iconEmbed);
					resourceLabel.setComponentAlignment(iconEmbed,
							Alignment.MIDDLE_CENTER);

					ButtonLink resourceLink = new ButtonLink(path,
							new Button.ClickListener() {

								@Override
								public void buttonClick(ClickEvent event) {
									if (resource instanceof Folder) {
										displayResourcesInTable(resource
												.getName());
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
				public Object generateCell(Table source, Object itemId,
						Object columnId) {
					Resource resource = ResourceTableDisplay.this
							.getResource(itemId);
					return new Label(AppContext.formatDateTime(resource
							.getCreated().getTime()));
				}
			});

			this.addGeneratedColumn("size", new Table.ColumnGenerator() {

				@Override
				public Object generateCell(Table source, Object itemId,
						Object columnId) {
					Resource resource = ResourceTableDisplay.this
							.getResource(itemId);
					return new Label(resource.getSize() + "");
				}
			});

			this.addGeneratedColumn("createdBy", new Table.ColumnGenerator() {

				@Override
				public Object generateCell(Table source, Object itemId,
						Object columnId) {
					Resource resource = ResourceTableDisplay.this
							.getResource(itemId);
					return new Label(resource.getCreatedBy());
				}
			});

			this.setColumnExpandRatio("path", 1);
			this.setColumnWidth("uuid", 30);
			this.setColumnWidth("createdBy", UIConstants.TABLE_X_LABEL_WIDTH);
			this.setColumnWidth("size", UIConstants.TABLE_S_LABEL_WIDTH);
			this.setColumnWidth("created", UIConstants.TABLE_DATE_TIME_WIDTH);
		}

		private Resource getResource(Object itemId) {
			final Container container = this.getContainerDataSource();
			final BeanItem<Resource> item = (BeanItem<Resource>) container
					.getItem(itemId);
			return (item != null) ? item.getBean() : null;
		}
	}
}
