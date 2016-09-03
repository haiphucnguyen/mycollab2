package com.mycollab.pro.module.project.view.milestone;

import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Milestone;
import com.mycollab.module.project.domain.ProjectGenericTask;
import com.mycollab.module.project.domain.SimpleMilestone;
import com.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.mycollab.module.project.events.MilestoneEvent;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.service.MilestoneService;
import com.mycollab.module.project.service.ProjectGenericTaskService;
import com.mycollab.module.project.view.ProjectView;
import com.mycollab.module.project.view.milestone.IMilestoneKanbanView;
import com.mycollab.pro.module.project.view.assignments.AssignmentSearchPanel;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.AsyncInvoker;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.mvp.view.AbstractLazyPageView;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.ui.UIUtils;
import com.mycollab.vaadin.web.ui.ToggleButtonGroup;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import fi.jasoft.dragdroplayouts.DDHorizontalLayout;
import fi.jasoft.dragdroplayouts.DDVerticalLayout;
import fi.jasoft.dragdroplayouts.client.ui.LayoutDragMode;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.jouni.restrain.Restrain;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
@ViewComponent
public class MilestoneKanbanViewImpl extends AbstractLazyPageView implements IMilestoneKanbanView {
    private AssignmentSearchPanel searchPanel;
    private DDHorizontalLayout kanbanLayout;
    private Map<Integer, KanbanBlock> kanbanBlocks;
    private KanbanBlock nullBlock;
    private ProjectGenericTaskSearchCriteria baseCriteria;

    public MilestoneKanbanViewImpl() {
        this.setSizeFull();
        this.withSpacing(true).withMargin(new MarginInfo(false, true, true, true));
    }

    @Override
    protected void displayView() {
        searchPanel = new AssignmentSearchPanel(true);

        kanbanLayout = new DDHorizontalLayout();
        kanbanLayout.setHeight("100%");
        kanbanLayout.addStyleName("kanban-layout");
        kanbanLayout.setSpacing(true);
        kanbanLayout.setMargin(new MarginInfo(true, false, true, false));
        kanbanLayout.setComponentHorizontalDropRatio(0.3f);
        kanbanLayout.setDragMode(LayoutDragMode.CLONE_OTHER);

        this.with(searchPanel, kanbanLayout).expand(kanbanLayout);
        MButton boardBtn = new MButton(AppContext.getMessage(ProjectCommonI18nEnum.OPT_BOARD), clickEvent ->
                EventBusFactory.getInstance().post(new MilestoneEvent.GotoRoadmap(this))).withIcon(FontAwesome.NAVICON);

        MButton advanceDisplayBtn = new MButton(AppContext.getMessage(ProjectCommonI18nEnum.OPT_LIST),
                clickEvent -> EventBusFactory.getInstance().post(new MilestoneEvent.GotoList(this, null)))
                .withIcon(FontAwesome.SERVER).withWidth("100px");

        MButton kanbanBtn = new MButton(AppContext.getMessage(ProjectCommonI18nEnum.OPT_KANBAN)).withIcon(FontAwesome.TH)
                .withWidth("100px");
        MHorizontalLayout groupWrapLayout = new MHorizontalLayout();
        groupWrapLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        ToggleButtonGroup viewButtons = new ToggleButtonGroup();
        viewButtons.addButton(boardBtn);
        viewButtons.addButton(advanceDisplayBtn);
        viewButtons.addButton(kanbanBtn);
        viewButtons.withDefaultButton(kanbanBtn);
        groupWrapLayout.addComponent(viewButtons);
        searchPanel.addHeaderRight(groupWrapLayout);

        ProjectGenericTaskSearchCriteria searchCriteria = new ProjectGenericTaskSearchCriteria();
        searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        searchCriteria.setTypes(new SetSearchField<>(ProjectTypeConstants.BUG, ProjectTypeConstants.TASK,
                ProjectTypeConstants.RISK));
        queryAssignments(searchCriteria);
    }

    private void queryAssignments(ProjectGenericTaskSearchCriteria searchCriteria) {
        baseCriteria = searchCriteria;
        kanbanLayout.removeAllComponents();
        kanbanBlocks = new ConcurrentHashMap<>();
        setProjectNavigatorVisibility(false);
        MilestoneService milestoneService = AppContextUtil.getSpringBean(MilestoneService.class);
        ProjectGenericTaskService projectGenericTaskService = AppContextUtil.getSpringBean(ProjectGenericTaskService.class);
        AsyncInvoker.access(getUI(), new AsyncInvoker.PageCommand() {
            @Override
            public void run() {
                MilestoneSearchCriteria milestoneSearchCriteria = new MilestoneSearchCriteria();
                milestoneSearchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
                List<SimpleMilestone> milestones = milestoneService.findPageableListByCriteria(new BasicSearchRequest<>(milestoneSearchCriteria));
                for (SimpleMilestone milestone : milestones) {
                    KanbanBlock kanbanBlock = new KanbanBlock(milestone);
                    kanbanBlocks.put(milestone.getId(), kanbanBlock);
                    kanbanLayout.addComponent(kanbanBlock);
                }

                nullBlock = new KanbanBlock(null);
                kanbanLayout.addComponent(nullBlock);

                int totalTasks = projectGenericTaskService.getTotalCount(searchCriteria);
                searchPanel.setTotalCountNumber(totalTasks);
                int pages = totalTasks / 50;
                for (int page = 0; page < pages + 1; page++) {
                    List<ProjectGenericTask> assignments = projectGenericTaskService.findPageableListByCriteria(new BasicSearchRequest<>(searchCriteria, page + 1, 50));
                    if (CollectionUtils.isNotEmpty(assignments)) {
                        for (ProjectGenericTask assignment : assignments) {
                            if (assignment.getMilestoneId() != null) {
                                KanbanBlock kanbanBlock = kanbanBlocks.get(assignment.getMilestoneId());
                                if (kanbanBlock != null) {
                                    kanbanBlock.addBlockItem(new KanbanAssignmentBlockItem(assignment));
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void attach() {
        super.attach();
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

    private class KanbanBlock extends MVerticalLayout {
        private Milestone milestone;
        private DDVerticalLayout dragLayoutContainer;
        private MHorizontalLayout buttonControls;
        private Label header;

        KanbanBlock(SimpleMilestone milestone) {
            this.withFullHeight().withWidth("300px").withStyleName("kanban-block").withMargin(false);
            this.milestone = milestone;
            dragLayoutContainer = new DDVerticalLayout();
            dragLayoutContainer.setSpacing(true);
            dragLayoutContainer.setComponentVerticalDropRatio(0.3f);
            dragLayoutContainer.setDragMode(LayoutDragMode.CLONE);
            new Restrain(dragLayoutContainer).setMinHeight("50px").setMaxHeight((UIUtils.getBrowserHeight() - 450) + "px");
            MHorizontalLayout headerLayout = new MHorizontalLayout().withSpacing(false).withFullWidth().withStyleName("header");
            if (milestone == null) {
                header = new ELabel(milestone.getName()).withStyleName(UIConstants.TEXT_ELLIPSIS).withDescription(milestone.getName());
            } else {
                header = new ELabel(milestone.getName()).withStyleName(UIConstants.TEXT_ELLIPSIS).withDescription(milestone.getName());
            }

            headerLayout.with(header).expand(header);

            final PopupButton controlsBtn = new PopupButton();
            controlsBtn.addStyleName(WebUIConstants.BUTTON_LINK);
            headerLayout.with(controlsBtn);
            this.with(headerLayout, dragLayoutContainer);
        }

        void addBlockItem(KanbanAssignmentBlockItem comp) {
            dragLayoutContainer.addComponent(comp);
            updateComponentCount();
        }

        private void updateComponentCount() {
            header.setValue(String.format("%s (%d)", milestone.getName(), getAssignmentComponentCount()));
        }

        private int getAssignmentComponentCount() {
            Component testComp = (dragLayoutContainer.getComponentCount() > 0) ? dragLayoutContainer.getComponent(0) : null;
            if (testComp instanceof KanbanAssignmentBlockItem || testComp == null) {
                return dragLayoutContainer.getComponentCount();
            } else {
                return (dragLayoutContainer.getComponentCount() - 1);
            }
        }
    }

    private static class KanbanAssignmentBlockItem extends CustomComponent {
        private ProjectGenericTask assignment;

        KanbanAssignmentBlockItem(final ProjectGenericTask assignment) {
            this.assignment = assignment;
            MVerticalLayout root = new MVerticalLayout();
            root.addStyleName("kanban-item");
            this.setCompositionRoot(root);

            root.addComponent(new Label(assignment.getName()));
        }
    }
}
