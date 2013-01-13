package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.tracker.BugResolutionConstants;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.terminal.ThemeResource;
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
        initUI();
    }

    private void initUI() {
        HorizontalLayout header = new HorizontalLayout();
        header.setWidth("100%");

        Label title = new Label("Bug Dashboard");
        title.setStyleName("h2");
        header.addComponent(title);
        header.setExpandRatio(title, 1.0f);

        Button createBtn = new Button("Create", new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                EventBus.getInstance().fireEvent(
                        new BugEvent.GotoAdd(this, null));
            }
        });
        createBtn.setStyleName("link");
        createBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
        header.addComponent(createBtn);
        header.setComponentAlignment(createBtn, Alignment.MIDDLE_RIGHT);

        this.addComponent(header);

        HorizontalLayout body = new HorizontalLayout();
        body.setWidth("100%");
        body.setSpacing(true);

        leftColumn = new VerticalLayout();
        leftColumn.setSpacing(true);
        body.addComponent(leftColumn);

        rightColumn = new VerticalLayout();
        rightColumn.setSpacing(true);
        body.addComponent(rightColumn);

        this.addComponent(body);
    }

    @Override
    public void attach() {
        super.attach();
        leftColumn.removeAllComponents();
        rightColumn.removeAllComponents();

        BugDisplayWidget dueBugWidget = new BugDisplayWidget("Due Bugs");
        LazyLoadWrapper dueBugWidgetWrapper = new LazyLoadWrapper(dueBugWidget);
        leftColumn.addComponent(dueBugWidgetWrapper);

        BugDisplayWidget updateBugWidget = new BugDisplayWidget("Updated Bug Recently");
        LazyLoadWrapper updateBugWidgetWrapper = new LazyLoadWrapper(updateBugWidget);
        leftColumn.addComponent(updateBugWidgetWrapper);


        rightColumn.addComponent(new PrioritySummaryWidget());
        
        rightColumn.addComponent(new StatusSummaryWidget());

        //Due bug search criteria
        SimpleProject project = (SimpleProject) AppContext
                .getVariable(ProjectContants.PROJECT_NAME);
        
        BugSearchCriteria dueDefectsCriteria = new BugSearchCriteria();
        dueDefectsCriteria.setProjectid(new NumberSearchField(project.getId()));
        dueDefectsCriteria.setResolutions(new SetSearchField<String>(
                new String[]{BugResolutionConstants.UNRESOLVED}));
        dueBugWidget.setSearchCriteria(dueDefectsCriteria);
        
        BugSearchCriteria recentDefectsCriteria = new BugSearchCriteria();
        recentDefectsCriteria.setProjectid(new NumberSearchField(project.getId()));
        updateBugWidget.setSearchCriteria(recentDefectsCriteria);

    }
}
