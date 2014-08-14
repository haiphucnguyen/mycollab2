package com.esofthead.mycollab.module.project.view.page;

import java.util.List;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.events.PageEvent;
import com.esofthead.mycollab.module.project.i18n.Page18InEnum;
import com.esofthead.mycollab.module.wiki.domain.Folder;
import com.esofthead.mycollab.module.wiki.domain.Page;
import com.esofthead.mycollab.module.wiki.domain.WikiResource;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.4.0
 *
 */
@ViewComponent
public class PageListViewImpl extends AbstractPageView implements PageListView {
	private static final long serialVersionUID = 1L;

	private HorizontalLayout headerLayout;

	private VerticalLayout pagesLayout;

	private List<WikiResource> resources;

	public PageListViewImpl() {
		this.setMargin(new MarginInfo(false, true, false, true));

		headerLayout = new HorizontalLayout();
		this.addComponent(headerLayout);
		initHeader();

		pagesLayout = new VerticalLayout();
		this.addComponent(pagesLayout);
	}

	private void initHeader() {
		Image titleIcon = new Image(null,
				MyCollabResource
						.newResource("icons/22/project/page_selected.png"));
		Label headerText = new Label(
				AppContext.getMessage(Page18InEnum.VIEW_LIST_TITLE));

		UiUtils.addComponent(headerLayout, titleIcon, Alignment.MIDDLE_LEFT);
		UiUtils.addComponent(headerLayout, headerText, Alignment.MIDDLE_LEFT);
		headerLayout.setExpandRatio(headerText, 1.0f);

		final Button newGroupBtn = new Button(
				AppContext.getMessage(Page18InEnum.BUTTON_NEW_GROUP),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						UI.getCurrent().addWindow(new NewGroupWindow());
					}
				});
		newGroupBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
		newGroupBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		newGroupBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.PAGES));
		UiUtils.addComponent(headerLayout, newGroupBtn, Alignment.MIDDLE_RIGHT);

		final Button newPageBtn = new Button(
				AppContext.getMessage(Page18InEnum.BUTTON_NEW_PAGE),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						EventBusFactory.getInstance().post(
								new PageEvent.GotoAdd(this, null));
					}
				});
		newPageBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
		newPageBtn.setIcon(MyCollabResource
				.newResource("icons/16/addRecord.png"));
		newPageBtn.setEnabled(CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.PAGES));

		headerText.setStyleName(UIConstants.HEADER_TEXT);

		UiUtils.addComponent(headerLayout, newPageBtn, Alignment.MIDDLE_RIGHT);

		headerLayout.setStyleName(UIConstants.HEADER_VIEW);
		headerLayout.setWidth("100%");
		headerLayout.setSpacing(true);
		headerLayout.setMargin(new MarginInfo(true, false, true, false));
	}

	@Override
	public void displayPages(List<WikiResource> resources) {
		this.resources = resources;
		pagesLayout.removeAllComponents();
		if (resources != null) {
			for (WikiResource resource : resources) {
				VerticalLayout resourceBlock = (resource instanceof Page) ? displayPageBlock((Page) resource)
						: displayFolderBlock((Folder) resource);
				pagesLayout.addComponent(resourceBlock);
			}
		}

	}

	private VerticalLayout displayFolderBlock(Folder resource) {
		VerticalLayout block = new VerticalLayout();
		return block;
	}

	private VerticalLayout displayPageBlock(final Page resource) {
		VerticalLayout block = new VerticalLayout();
		HorizontalLayout headerPanel = new HorizontalLayout();
		Button pageLink = new Button(resource.getSubject(),
				new ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBusFactory.getInstance().post(
								new PageEvent.GotoRead(PageListViewImpl.this,
										resource));

					}
				});
		headerPanel.addComponent(pageLink);

		block.addComponent(headerPanel);
		return block;
	}

	private class NewGroupWindow extends Window {
		private static final long serialVersionUID = 1L;

		public NewGroupWindow() {
			super(AppContext.getMessage(Page18InEnum.DIALOG_NEW_GROUP_TITLE));
			this.setModal(true);
			this.setWidth("600px");
			this.setResizable(true);
			this.center();
			VerticalLayout content = new VerticalLayout();
			content.setSpacing(true);
			content.setMargin(true);

			HorizontalLayout inputPanel = new HorizontalLayout();
			inputPanel.setWidth("100%");
			inputPanel.addComponent(new Label(AppContext
					.getMessage(Page18InEnum.FIELD_GROUP)));
			TextField groupNameField = new TextField();
			inputPanel.addComponent(groupNameField);
			inputPanel.setExpandRatio(groupNameField, 1);
			content.addComponent(inputPanel);

			HorizontalLayout controlPanel = new HorizontalLayout();
			controlPanel.setSpacing(true);
			Button cancelBtn = new Button(
					AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
					new ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							NewGroupWindow.this.close();

						}
					});
			cancelBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
			UiUtils.addComponent(controlPanel, cancelBtn,
					Alignment.MIDDLE_RIGHT);

			Button saveBtn = new Button(
					AppContext.getMessage(GenericI18Enum.BUTTON_SAVE_LABEL),
					new ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							// TODO Auto-generated method stub

						}
					});
			saveBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
			UiUtils.addComponent(controlPanel, saveBtn, Alignment.MIDDLE_RIGHT);

			content.addComponent(controlPanel);
			content.setComponentAlignment(controlPanel, Alignment.MIDDLE_RIGHT);
			this.setContent(content);
		}
	}

}
