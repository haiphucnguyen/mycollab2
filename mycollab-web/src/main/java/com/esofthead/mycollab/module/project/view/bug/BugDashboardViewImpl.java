package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.BugVersionEvent;
import com.esofthead.mycollab.module.tracker.BugResolutionConstants;
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
import org.vaadin.hene.splitbutton.SplitButton;
import org.vaadin.peter.buttongroup.ButtonGroup;

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
        header.setExpandRatio(title, 0.5f);
        
        ButtonGroup navButton = new ButtonGroup();
        navButton.addButton(new Button("Bugs"));
        navButton.addButton(new Button("Components"));
        navButton.addButton(new Button("Versions"));
        header.addComponent(navButton);
        header.setExpandRatio(navButton, 0.5f);
        
        SplitButton controlsBtn = new SplitButton();
        controlsBtn.addStyleName(UIConstants.SPLIT_BUTTON);
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
                        EventBus.getInstance().fireEvent(new BugComponentEvent.GotoAdd(this, null));
                    }
                });
        createComponentBtn.setStyleName("link");
        btnControlsLayout.addComponent(createComponentBtn);
        
        Button createVersionBtn = new Button("Create Version",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        EventBus.getInstance().fireEvent(new BugVersionEvent.GotoAdd(this, null));
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

        rightColumn = new VerticalLayout();
        rightColumn.setSpacing(true);
        body.addComponent(rightColumn);

        this.addComponent(body);
    }

    @Override
    public void attach() {
        leftColumn.removeAllComponents();
        rightColumn.removeAllComponents();

        BugDisplayWidget dueBugWidget = new BugDisplayWidget("Due Bugs");
        LazyLoadWrapper dueBugWidgetWrapper = new LazyLoadWrapper(dueBugWidget);
        leftColumn.addComponent(dueBugWidgetWrapper);

        BugDisplayWidget updateBugWidget = new BugDisplayWidget("Updated Bug Recently");
        LazyLoadWrapper updateBugWidgetWrapper = new LazyLoadWrapper(updateBugWidget);
        leftColumn.addComponent(updateBugWidgetWrapper);


        rightColumn.addComponent(new LazyLoadWrapper(new PrioritySummaryWidget()));
        
        rightColumn.addComponent(new LazyLoadWrapper(new StatusSummaryWidget()));

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
