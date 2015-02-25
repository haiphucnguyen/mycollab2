package com.esofthead.mycollab.premium.module.project.view.time;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.ui.components.GenericTaskTableDisplay;
import com.esofthead.mycollab.module.project.ui.components.GenericTaskTableFieldDef;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable.TableClickListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

import java.util.Arrays;

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
		MVerticalLayout content = new MVerticalLayout();
		taskTableDisplay = new GenericTaskTableDisplay(
				Arrays.asList(GenericTaskTableFieldDef.name));
		taskTableDisplay.addTableListener(new TableClickListener() {
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
		searchCriteria.setProjectIds(new SetSearchField<>(
				CurrentProjectVariables.getProjectId()));
		taskTableDisplay.setSearchCriteria(searchCriteria);

		content.addComponent(constructTopPanel());
		content.addComponent(taskTableDisplay);

		this.setContent(content);
	}

	private ComponentContainer constructTopPanel() {
		final MHorizontalLayout basicSearchBody = new MHorizontalLayout().withMargin(true);

		Label nameLbl = new Label("Name:");
		basicSearchBody.with(nameLbl).withAlign(nameLbl, Alignment.MIDDLE_LEFT);

		this.nameField = new TextField();
		this.nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
		basicSearchBody.with(nameField).withAlign(nameField,
				Alignment.MIDDLE_CENTER);

		final Button searchBtn = new Button(
				AppContext.getMessage(GenericI18Enum.BUTTON_SEARCH));
		searchBtn.setIcon(FontAwesome.SEARCH);
		searchBtn.setStyleName(UIConstants.THEME_GREEN_LINK);

		searchBtn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(final ClickEvent event) {
				callSearchAction();
			}
		});
		searchBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
		basicSearchBody.with(searchBtn).withAlign(searchBtn,
				Alignment.MIDDLE_LEFT);

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
		searchCriteria.setName(new StringSearchField(this.nameField.getValue().trim()));
		taskTableDisplay.setSearchCriteria(searchCriteria);
	}
}
