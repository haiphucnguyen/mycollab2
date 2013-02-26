package com.esofthead.mycollab.module.project.view.bug;

import java.util.GregorianCalendar;

import org.vaadin.hene.splitbutton.SplitButton;
import org.vaadin.peter.buttongroup.ButtonGroup;

import com.esofthead.mycollab.core.arguments.DateTimeSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.BugVersionEvent;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class BugDashboardViewImpl extends AbstractView implements
		BugDashboardView {

	private VerticalLayout leftColumn, rightColumn;

	public BugDashboardViewImpl() {
		super();
		this.setMargin(true);
		initUI();
	}

	private void initUI() {
		HorizontalLayout header = new HorizontalLayout();
		header.setWidth("100%");

		Label title = new Label("Bug Dashboard");
		title.setStyleName("h2");
		header.addComponent(title);
		header.setExpandRatio(title, 0.5f);

		ButtonGroup navButton = new ButtonGroup();
		Button bugListBtn = new Button("Bugs", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new BugEvent.GotoList(this, null));
			}
		});

		navButton.addButton(bugListBtn);

		Button componentListBtn = new Button("Components",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new BugComponentEvent.GotoList(this, null));
					}
				});

		navButton.addButton(componentListBtn);

		Button versionListBtn = new Button("Versions",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new BugVersionEvent.GotoList(this, null));
					}
				});

		navButton.addButton(versionListBtn);

		header.addComponent(navButton);
		header.setExpandRatio(navButton, 0.5f);

		final SplitButton controlsBtn = new SplitButton();
		controlsBtn.addStyleName(UIConstants.SPLIT_BUTTON);
		controlsBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		controlsBtn.setCaption("Create Bug");
		controlsBtn
				.addClickListener(new SplitButton.SplitButtonClickListener() {
					@Override
					public void splitButtonClick(
							SplitButton.SplitButtonClickEvent event) {
						EventBus.getInstance().fireEvent(
								new BugEvent.GotoAdd(this, null));
					}
				});

		VerticalLayout btnControlsLayout = new VerticalLayout();
		btnControlsLayout.setWidth("150px");
		Button createComponentBtn = new Button("Create Component",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						controlsBtn.setPopupVisible(false);
						EventBus.getInstance().fireEvent(
								new BugComponentEvent.GotoAdd(this, null));
					}
				});
		createComponentBtn.setStyleName("link");
		btnControlsLayout.addComponent(createComponentBtn);

		Button createVersionBtn = new Button("Create Version",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						controlsBtn.setPopupVisible(false);
						EventBus.getInstance().fireEvent(
								new BugVersionEvent.GotoAdd(this, null));
					}
				});
		createVersionBtn.setStyleName("link");
		btnControlsLayout.addComponent(createVersionBtn);
		controlsBtn.addComponent(btnControlsLayout);

		header.addComponent(controlsBtn);

		header.setComponentAlignment(controlsBtn, Alignment.MIDDLE_RIGHT);

		this.addComponent(header);

		HorizontalLayout body = new HorizontalLayout();
		body.setWidth("100%");
		body.setSpacing(true);

		leftColumn = new VerticalLayout();
		leftColumn.setSpacing(true);
		body.addComponent(leftColumn);
		body.setExpandRatio(leftColumn, 1.0f);

		rightColumn = new VerticalLayout();
		rightColumn.setSpacing(true);
		rightColumn.setWidth("500px");
		body.addComponent(rightColumn);
		body.setComponentAlignment(rightColumn, Alignment.TOP_RIGHT);

		this.addComponent(body);
	}

	@Override
	public void attach() {
		leftColumn.removeAllComponents();
		rightColumn.removeAllComponents();

		SimpleProject project = CurrentProjectVariables.getProject();

		MyBugListWidget myBugListWidget = new MyBugListWidget();
		LazyLoadWrapper myBugsWidgetWrapper = new LazyLoadWrapper(
				myBugListWidget);
		leftColumn.addComponent(myBugsWidgetWrapper);
		BugSearchCriteria myBugsSearchCriteria = new BugSearchCriteria();
		myBugsSearchCriteria
				.setProjectId(new NumberSearchField(project.getId()));
		myBugsSearchCriteria
				.setStatuses(new SetSearchField<String>(SearchField.AND,
						new String[] { BugStatusConstants.INPROGRESS,
								BugStatusConstants.OPEN,
								BugStatusConstants.REOPENNED }));
		myBugsSearchCriteria.setAssignuser(new StringSearchField(AppContext
				.getUsername()));

		myBugListWidget.setSearchCriteria(myBugsSearchCriteria);

		RecentBugUpdateWidget updateBugWidget = new RecentBugUpdateWidget();
		LazyLoadWrapper updateBugWidgetWrapper = new LazyLoadWrapper(
				updateBugWidget);
		leftColumn.addComponent(updateBugWidgetWrapper);
		BugSearchCriteria recentDefectsCriteria = new BugSearchCriteria();
		recentDefectsCriteria.setProjectId(new NumberSearchField(project
				.getId()));
		updateBugWidget.setSearchCriteria(recentDefectsCriteria);

		BugSearchCriteria chartSearchCriteria = new BugSearchCriteria();
		chartSearchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		BugChartComponent bugChartComponent = new BugChartComponent(
				chartSearchCriteria);
		rightColumn.addComponent(bugChartComponent);

		DueBugWidget dueBugWidget = new DueBugWidget();
		LazyLoadWrapper dueBugWidgetWrapper = new LazyLoadWrapper(dueBugWidget);
		rightColumn.addComponent(dueBugWidgetWrapper);
		BugSearchCriteria dueDefectsCriteria = new BugSearchCriteria();
		dueDefectsCriteria.setProjectId(new NumberSearchField(project.getId()));
		dueDefectsCriteria.setDueDate(new DateTimeSearchField(SearchField.AND,
				DateTimeSearchField.LESSTHANEQUAL, new GregorianCalendar()
						.getTime()));
		dueDefectsCriteria
				.setStatuses(new SetSearchField<String>(SearchField.AND,
						new String[] { BugStatusConstants.INPROGRESS,
								BugStatusConstants.OPEN,
								BugStatusConstants.REOPENNED }));
		dueBugWidget.setSearchCriteria(dueDefectsCriteria);
	}
}
