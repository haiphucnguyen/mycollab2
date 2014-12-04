package com.esofthead.mycollab.premium.module.project.view.time;

import java.util.Arrays;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.ui.components.GenericTaskTableDisplay;
import com.esofthead.mycollab.module.project.ui.components.GenericTaskTableFieldDef;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.vaadin.ui.WebResourceIds;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable.TableClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
class ProjectGenericTaskSelectionWindow extends Window {
	private static final long serialVersionUID = 1L;

	private GenericTaskTableDisplay taskTableDisplay;
	private TextField nameField;
	private ProjectGenericTaskSearchCriteria searchCriteria;

	public ProjectGenericTaskSelectionWindow(
			final AssignmentSelectableComp timeEntryWindow) {
		super("Select Assignments");
		this.center();
		this.setResizable(false);
		this.setModal(true);
		this.setWidth("800px");
		VerticalLayout content = new VerticalLayout();
		content.setMargin(true);
		content.setSpacing(true);
		taskTableDisplay = new GenericTaskTableDisplay(
				Arrays.asList(GenericTaskTableFieldDef.name));
		taskTableDisplay
				.addTableListener(new TableClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void itemClick(final TableClickEvent event) {
						final ProjectGenericTask task = (ProjectGenericTask) event
								.getData();
						if ("name".equals(event.getFieldName())) {
							timeEntryWindow.updateLinkTask(task);
							ProjectGenericTaskSelectionWindow.this.close();
						}
					}
				});

		searchCriteria = new ProjectGenericTaskSearchCriteria();
		searchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		taskTableDisplay.setSearchCriteria(searchCriteria);

		content.addComponent(constructTopPanel());
		content.addComponent(taskTableDisplay);

		this.setContent(content);
	}

	private ComponentContainer constructTopPanel() {
		final HorizontalLayout basicSearchBody = new HorizontalLayout();
		basicSearchBody.setSpacing(true);
		basicSearchBody.setMargin(true);
		UiUtils.addComponent(basicSearchBody, new Label("Name:"),
				Alignment.MIDDLE_LEFT);

		this.nameField = new TextField();
		this.nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
		UiUtils.addComponent(basicSearchBody, this.nameField,
				Alignment.MIDDLE_CENTER);

		final Button searchBtn = new Button(
				AppContext.getMessage(GenericI18Enum.BUTTON_SEARCH));
		searchBtn.setIcon(MyCollabResource.newResource(WebResourceIds._16_search));
		searchBtn.setStyleName(UIConstants.THEME_GREEN_LINK);

		searchBtn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				callSearchAction();
			}
		});
		searchBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
		UiUtils.addComponent(basicSearchBody, searchBtn, Alignment.MIDDLE_LEFT);

		final Button cancelBtn = new Button(
				AppContext.getMessage(GenericI18Enum.BUTTON_CLEAR));
		cancelBtn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				nameField.setValue("");
			}
		});
		cancelBtn.setStyleName(UIConstants.THEME_GRAY_LINK);
		basicSearchBody.addComponent(cancelBtn);

		return basicSearchBody;
	}

	private void callSearchAction() {
		searchCriteria.setName(new StringSearchField(this.nameField.getValue()
				.toString().trim()));
		taskTableDisplay.setSearchCriteria(searchCriteria);
	}
}
