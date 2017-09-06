package com.mycollab.pro.module.project.view.assignments;

import com.mycollab.module.project.view.ProjectView;
import com.mycollab.pro.module.project.view.assignments.gantt.GanttExt;
import com.mycollab.pro.module.project.view.assignments.gantt.GanttTreeTable;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.web.ui.AbstractLazyPageView;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIUtils;
import com.mycollab.vaadin.web.ui.ValueComboBox;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import org.tltv.gantt.client.shared.Resolution;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
@ViewComponent
public class GanttChartViewImpl extends AbstractLazyPageView implements GanttChartView {
    private static final long serialVersionUID = 1L;

    private MHorizontalLayout mainLayout;
    private GanttExt gantt;
    private GanttTreeTable taskTable;

    public GanttChartViewImpl() {
        this.setSizeFull();
        this.withMargin(true);
    }

    @Override
    public void detach() {
        setProjectNavigatorVisibility(true);
        super.detach();
    }

    private void setProjectNavigatorVisibility(boolean visibility) {
        ProjectView view = UIUtils.getRoot(this, ProjectView.class);
        if (view != null) {
            view.setNavigatorVisibility(visibility);
        }
    }

    private void constructUI() {
        MHorizontalLayout header = new MHorizontalLayout().withMargin(new MarginInfo(false, false, true, false)).withFullWidth();
        header.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        ELabel headerText = ELabel.h2(FontAwesome.BAR_CHART_O.getHtml() + " Gantt chart");
        CssLayout headerWrapper = new CssLayout();
        headerWrapper.addComponent(headerText);

        MHorizontalLayout resWrapper = new MHorizontalLayout();
        Label resLbl = new Label("Resolution: ");
        final ComboBox resValue = new ValueComboBox(false, "Day", "Week");
        resValue.addValueChangeListener(valueChangeEvent -> {
            String val = (String) resValue.getValue();
            if ("Day".equals(val)) {
                gantt.setResolution(Resolution.Day);
            } else if ("Week".equals(val)) {
                gantt.setResolution(Resolution.Week);
            }
        });
        resWrapper.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        resWrapper.with(resLbl, resValue);

        header.with(headerWrapper, resWrapper).withAlign(headerWrapper, Alignment.MIDDLE_LEFT).expand(headerWrapper);

        mainLayout = new MHorizontalLayout().withSpacing(false);
        mainLayout.addStyleName("gantt_container");
        mainLayout.setSizeFull();
        this.with(header, mainLayout);
    }

    @Override
    protected void displayView() {
        setProjectNavigatorVisibility(false);
        constructUI();

        gantt = new GanttExt();
        taskTable = new GanttTreeTable(gantt);
        mainLayout.with(taskTable, gantt).expand(gantt);
        taskTable.loadAssignments();
    }

    @Override
    public GanttExt getGantt() {
        return gantt;
    }

    @Override
    public GanttTreeTable getTaskTable() {
        return taskTable;
    }
}
