package com.esofthead.mycollab.module.project.view.file;

import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.module.ecm.ContentException;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.domain.Folder;
import com.esofthead.mycollab.module.ecm.domain.Resource;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.StreamDownloadResourceFactory;
import com.esofthead.mycollab.module.file.view.components.FileDownloadWindow;
import com.esofthead.mycollab.module.project.events.ProjectContentEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@ViewComponent
public class FileSearchResultViewImpl extends AbstractView implements
		FileSearchResultView {
	private static final long serialVersionUID = 1L;

	private final Label searchHeader;
	private final VerticalLayout bodyLayout;
	private final ResourceService resourceService;
	private final ResourceTableDisplay resourceTable;
	private String basePath;
	private String searchString;

	public FileSearchResultViewImpl() {
		this.basePath = "";
		this.searchString = "";

		this.resourceService = AppContext.getSpringBean(ResourceService.class);

		final CssLayout headerWrapper = new CssLayout();
		headerWrapper.setWidth("100%");
		headerWrapper.addStyleName(UIConstants.THEME_COMP_HEADER);

		final HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setWidth("100%");
		headerLayout.setSpacing(true);

		headerWrapper.addComponent(headerLayout);
		this.addComponent(headerWrapper);

		final Embedded headerIcon = new Embedded();
		headerIcon.setSource(MyCollabResource
				.newResource("icons/16/search.png"));
		headerLayout.addComponent(headerIcon);
		headerLayout.setComponentAlignment(headerIcon, Alignment.MIDDLE_LEFT);

		this.searchHeader = new Label();
		headerLayout.addComponent(this.searchHeader);
		headerLayout.setComponentAlignment(this.searchHeader,
				Alignment.MIDDLE_LEFT);
		headerLayout.setExpandRatio(this.searchHeader, 1.0f);

		final Button backButton = new Button("Back to dashboard",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new ProjectContentEvent.GotoDashboard(
										FileSearchResultViewImpl.this));

					}
				});
		backButton.addStyleName(UIConstants.THEME_BLUE_LINK);
		headerLayout.addComponent(backButton);

		this.resourceTable = new ResourceTableDisplay();
		this.resourceTable.setWidth("100%");

		this.bodyLayout = new VerticalLayout();
		this.bodyLayout.addComponent(this.resourceTable);
		this.addComponent(this.bodyLayout);
		this.setMargin(true);
	}

	@Override
	public void displaySearchResult(final String basePath, final String name) {
		this.basePath = basePath;
		this.searchString = name;

		final String header = "Search result of '%s'";
		this.searchHeader.setValue(String.format(header, name));

		final List<Resource> resourceList = this.resourceService
				.searchResourcesByName(name);

		this.resourceTable.refreshRowCache();

		this.resourceTable
				.setContainerDataSource(new BeanItemContainer<Resource>(
						Resource.class, resourceList));
		this.resourceTable.setVisibleColumns(new String[] { "uuid", "path",
				"size", "created" });
		this.resourceTable.setColumnHeaders(new String[] { "", "Name",
				"Size (Kb)", "Created" });
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
											FileSearchResultViewImpl.this.resourceService);
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
											FileSearchResultViewImpl.this
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
														FileSearchResultViewImpl.this.resourceService
																.removeResource(resource
																		.getPath());

														FileSearchResultViewImpl.this
																.displaySearchResult(
																		FileSearchResultViewImpl.this.basePath,
																		FileSearchResultViewImpl.this.searchString);
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

									final FileDownloadWindow downloadFileWindow = new FileDownloadWindow(
											(Content) resource);
									ResourceTableDisplay.this.getWindow()
											.addWindow(downloadFileWindow);

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
						FileSearchResultViewImpl.this.displaySearchResult(
								FileSearchResultViewImpl.this.basePath,
								FileSearchResultViewImpl.this.searchString);

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

}
