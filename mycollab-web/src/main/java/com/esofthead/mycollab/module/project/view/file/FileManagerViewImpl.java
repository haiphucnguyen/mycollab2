package com.esofthead.mycollab.module.project.view.file;

import java.util.List;

import org.vaadin.easyuploads.SingleFileUploadField;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.CollapseEvent;
import com.vaadin.ui.Tree.ExpandEvent;
import com.vaadin.ui.Window;

@ViewComponent
public class FileManagerViewImpl extends AbstractView implements
		FileManagerView {
	private static final long serialVersionUID = 1L;

	private Tree folderTree;
	private ResourceTableDisplay resourceTable;
	private String projectPath;

	private Folder baseFolder;

	private ResourceService resourceService;

	public FileManagerViewImpl() {
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
				// TODO Auto-generated method stub

			}
		});

		menuBar.addComponent(deleteBtn);

		this.addComponent(menuBar);

		HorizontalSplitPanel resourceContainer = new HorizontalSplitPanel();
		resourceContainer.setSizeFull();

		folderTree = new Tree();
		folderTree.setMultiSelect(false);
		folderTree.setImmediate(true);
		folderTree.setItemCaptionMode(Tree.ITEM_CAPTION_MODE_EXPLICIT);

		resourceContainer.addComponent(folderTree);

		folderTree.addListener(new Tree.ExpandListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void nodeExpand(ExpandEvent event) {
				Folder expandFolder = (Folder) event.getItemId();
				List<Folder> subFolders = resourceService
						.getSubFolders(expandFolder.getPath());
				if (subFolders != null) {
					for (Folder subFolder : subFolders) {
						expandFolder.addChild(subFolder);
						folderTree.addItem(subFolder);
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
		resourceTable.setWidth("800px");
		resourceContainer.addComponent(resourceTable);

		this.addComponent(resourceContainer);
	}

	private void displayResourcesInTable(Folder folder) {
		List<Resource> resources = resourceService.getResources(folder
				.getPath());
		resourceTable.setContainerDataSource(new BeanItemContainer<Resource>(
				Resource.class, resources));
		resourceTable.setVisibleColumns(new String[] { "path", "size",
				"created", "createdBy" });
		resourceTable.setColumnHeaders(new String[] { "Name", "Size",
				"Created", "By" });
	}

	@Override
	public void displayProjectFiles() {
		folderTree.removeAllItems();
		int projectId = CurrentProjectVariables.getProjectId();
		projectPath = String.format("%d/project/%d", AppContext.getAccountId(),
				projectId);

		baseFolder = new Folder();
		baseFolder.setPath(projectPath);
		folderTree.addItem(baseFolder);
		folderTree.setItemCaption(baseFolder, CurrentProjectVariables
				.getProject().getName());

		folderTree.expandItem(baseFolder);
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
								folderTree.addItem(newFolder);
								folderTree.setItemCaption(newFolder,
										newFolder.getName());
								folderTree.setParent(newFolder, baseFolder);
								if (!folderTree.isExpanded(baseFolder)) {
									folderTree.expandItem(baseFolder);
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

		private TextField titleField;
		private TextArea descField;
		private SingleFileUploadField uploadField;

		public UploadContentWindow() {
			super("Upload Content");
			this.setWidth("500px");
			this.setModal(true);

			layoutHelper = new GridFormLayoutHelper(1, 3);

			titleField = (TextField) layoutHelper.addComponent(new TextField(),
					"Title", 0, 0);
			descField = (TextArea) layoutHelper.addComponent(new TextArea(),
					"Description", 0, 1);

			uploadField = (SingleFileUploadField) layoutHelper.addComponent(
					new SingleFileUploadField(), "File", 0, 2);

			this.addComponent(layoutHelper.getLayout());

			HorizontalLayout controlsLayout = new HorizontalLayout();
			controlsLayout.setWidth("100%");

			Button uploadBtn = new Button("Upload", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					// TODO Auto-generated method stub
					Content content = new Content();
					content.setTitle((String) titleField.getValue());
					content.setDescription((String) descField.getValue());
					content.setPath(baseFolder.getPath() + "/" );
					UploadContentWindow.this.close();
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
			this.addGeneratedColumn("path", new Table.ColumnGenerator() {
				private static final long serialVersionUID = 1L;

				@Override
				public Object generateCell(Table source, Object itemId,
						Object columnId) {
					Resource resource = ResourceTableDisplay.this
							.getResource(itemId);
					String path = resource.getPath();
					int pathIndex = path.lastIndexOf("/");
					if (pathIndex > -1) {
						path = path.substring(pathIndex + 1);
					}
					return new Label(path);
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
		}

		private Resource getResource(Object itemId) {
			final Container container = this.getContainerDataSource();
			final BeanItem<Resource> item = (BeanItem<Resource>) container
					.getItem(itemId);
			return (item != null) ? item.getBean() : null;
		}
	}
}
