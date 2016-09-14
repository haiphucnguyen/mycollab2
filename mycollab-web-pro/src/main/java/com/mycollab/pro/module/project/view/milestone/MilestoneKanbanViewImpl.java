package com.mycollab.pro.module.project.view.milestone;

import com.google.common.eventbus.Subscribe;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.eventmanager.ApplicationEventListener;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTooltipGenerator;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Milestone;
import com.mycollab.module.project.domain.ProjectGenericTask;
import com.mycollab.module.project.domain.SimpleMilestone;
import com.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.mycollab.module.project.events.MilestoneEvent;
import com.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.service.MilestoneService;
import com.mycollab.module.project.service.ProjectGenericTaskService;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.view.ProjectView;
import com.mycollab.module.project.view.milestone.IMilestoneKanbanView;
import com.mycollab.module.project.view.milestone.MilestoneAddWindow;
import com.mycollab.module.project.view.milestone.ToggleGenericTaskSummaryField;
import com.mycollab.pro.module.project.view.assignments.AssignmentAddWindow;
import com.mycollab.pro.module.project.view.assignments.AssignmentSearchPanel;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.AsyncInvoker;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.mvp.view.AbstractLazyPageView;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.ui.UIUtils;
import com.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.mycollab.vaadin.web.ui.OptionPopupContent;
import com.mycollab.vaadin.web.ui.ToggleButtonGroup;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.dd.VerticalDropLocation;
import com.vaadin.ui.*;
import fi.jasoft.dragdroplayouts.DDHorizontalLayout;
import fi.jasoft.dragdroplayouts.DDVerticalLayout;
import fi.jasoft.dragdroplayouts.client.ui.LayoutDragMode;
import fi.jasoft.dragdroplayouts.events.LayoutBoundTransferable;
import fi.jasoft.dragdroplayouts.events.VerticalLocationIs;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.LocalDate;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.jouni.restrain.Restrain;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.mycollab.vaadin.web.ui.WebUIConstants.BUTTON_ACTION;

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

    private ApplicationEventListener<MilestoneEvent.NewMilestoneAdded> newMilestoneHandler = new
            ApplicationEventListener<MilestoneEvent.NewMilestoneAdded>() {
                @Override
                @Subscribe
                public void handle(MilestoneEvent.NewMilestoneAdded event) {
                    insertMilestone((Integer) event.getData());
                }
            };

    public MilestoneKanbanViewImpl() {
        this.setSizeFull();
        this.withSpacing(true).withMargin(new MarginInfo(false, true, true, true));
    }

    @Override
    protected void displayView() {
        searchPanel = new AssignmentSearchPanel(false);

        kanbanLayout = new DDHorizontalLayout();
        kanbanLayout.setHeight("100%");
        kanbanLayout.addStyleName("kanban-layout");
        kanbanLayout.setSpacing(true);
        kanbanLayout.setMargin(new MarginInfo(true, false, true, false));
        kanbanLayout.setComponentHorizontalDropRatio(0.3f);
        kanbanLayout.setDragMode(LayoutDragMode.CLONE_OTHER);

        this.with(searchPanel, kanbanLayout).expand(kanbanLayout);
        MButton boardBtn = new MButton(UserUIContext.getMessage(ProjectCommonI18nEnum.OPT_BOARD), clickEvent ->
                EventBusFactory.getInstance().post(new MilestoneEvent.GotoRoadmap(this))).withIcon(FontAwesome.NAVICON);

        MButton advanceDisplayBtn = new MButton(UserUIContext.getMessage(ProjectCommonI18nEnum.OPT_LIST),
                clickEvent -> EventBusFactory.getInstance().post(new MilestoneEvent.GotoList(this, null)))
                .withIcon(FontAwesome.SERVER).withWidth("100px");

        MButton kanbanBtn = new MButton(UserUIContext.getMessage(ProjectCommonI18nEnum.OPT_KANBAN)).withIcon(FontAwesome.TH)
                .withWidth("100px");

        MButton newMilestoneBtn = new MButton(UserUIContext.getMessage(MilestoneI18nEnum.NEW), clickEvent -> {
            SimpleMilestone milestone = new SimpleMilestone();
            milestone.setSaccountid(UserUIContext.getAccountId());
            milestone.setProjectid(CurrentProjectVariables.getProjectId());
            UI.getCurrent().addWindow(new MilestoneAddWindow(milestone));
        }).withIcon(FontAwesome.PLUS).withStyleName(WebUIConstants.BUTTON_ACTION);

        ToggleButtonGroup viewButtons = new ToggleButtonGroup();
        viewButtons.addButton(boardBtn);
        viewButtons.addButton(advanceDisplayBtn);
        viewButtons.addButton(kanbanBtn);
        viewButtons.withDefaultButton(kanbanBtn);
        MHorizontalLayout groupWrapLayout = new MHorizontalLayout(newMilestoneBtn, viewButtons);
        groupWrapLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
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
                            } else {
                                nullBlock.addBlockItem(new KanbanAssignmentBlockItem(assignment));
                            }
                        }
                    }
                }
            }
        });
    }

    private void insertMilestone(Integer milestoneId) {
        MilestoneService milestoneService = AppContextUtil.getSpringBean(MilestoneService.class);
        SimpleMilestone milestone = milestoneService.findById(milestoneId, UserUIContext.getAccountId());
        if (milestone != null) {
            KanbanBlock kanbanBlock = new KanbanBlock(milestone);
            kanbanBlocks.put(milestone.getId(), kanbanBlock);
            kanbanLayout.addComponent(kanbanBlock, 0);
        }
    }

    @Override
    public void attach() {
        EventBusFactory.getInstance().register(newMilestoneHandler);
        super.attach();
    }

    @Override
    public void detach() {
        setProjectNavigatorVisibility(true);
        EventBusFactory.getInstance().unregister(newMilestoneHandler);
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
        private Label header;

        KanbanBlock(SimpleMilestone milestone) {
            this.withFullHeight().withWidth("350px").withStyleName("kanban-block").withMargin(false);
            this.milestone = milestone;
            dragLayoutContainer = new DDVerticalLayout();
            dragLayoutContainer.setSpacing(true);
            dragLayoutContainer.setComponentVerticalDropRatio(0.3f);
            dragLayoutContainer.setDragMode(LayoutDragMode.CLONE);
            dragLayoutContainer.setDropHandler(new DropHandler() {
                @Override
                public void drop(DragAndDropEvent event) {
                    LayoutBoundTransferable transferable = (LayoutBoundTransferable) event.getTransferable();

                    DDVerticalLayout.VerticalLayoutTargetDetails details = (DDVerticalLayout.VerticalLayoutTargetDetails) event
                            .getTargetDetails();

                    Component dragComponent = transferable.getComponent();
                    if (dragComponent instanceof KanbanAssignmentBlockItem) {
                        KanbanAssignmentBlockItem kanbanItem = (KanbanAssignmentBlockItem) dragComponent;
                        int newIndex = details.getOverIndex();
                        if (details.getDropLocation() == VerticalDropLocation.BOTTOM) {
                            dragLayoutContainer.addComponent(kanbanItem);
                        } else if (newIndex == -1) {
                            dragLayoutContainer.addComponent(kanbanItem, 0);
                        } else {
                            dragLayoutContainer.addComponent(kanbanItem, newIndex);
                        }
                        ProjectGenericTask task = kanbanItem.assignment;
                        if (milestone == null) {
                            task.setMilestoneId(null);
                        } else {
                            task.setMilestoneId(milestone.getId());
                        }
                        ProjectGenericTaskService projectGenericTaskService = AppContextUtil.getSpringBean(ProjectGenericTaskService.class);
                        projectGenericTaskService.updateAssignmentValue(task, UserUIContext.getUsername());

                        updateComponentCount();

                        Component sourceComponent = transferable.getSourceComponent();
                        KanbanBlock sourceKanban = UIUtils.getRoot(sourceComponent, KanbanBlock.class);
                        if (sourceKanban != null && sourceKanban != KanbanBlock.this) {
                            sourceKanban.updateComponentCount();
                        }
                    }
                }

                @Override
                public AcceptCriterion getAcceptCriterion() {
                    return new Not(VerticalLocationIs.MIDDLE);
                }
            });
            new Restrain(dragLayoutContainer).setMinHeight("50px").setMaxHeight((UIUtils.getBrowserHeight() - 450) + "px");
            MHorizontalLayout headerLayout = new MHorizontalLayout().withSpacing(false).withFullWidth().withStyleName("header");
            if (milestone == null) {
                header = new ELabel(UserUIContext.getMessage(GenericI18Enum.OPT_UNDEFINED)).withStyleName(UIConstants.TEXT_ELLIPSIS)
                        .withDescription(UserUIContext.getMessage(GenericI18Enum.OPT_UNDEFINED));
            } else {
                header = new ELabel(milestone.getName()).withStyleName(UIConstants.TEXT_ELLIPSIS).withDescription
                        (ProjectTooltipGenerator.generateToolTipMilestone(UserUIContext.getUserLocale(), UserUIContext.getDateFormat(),
                                milestone, UserUIContext.getSiteUrl(), UserUIContext.getUserTimeZone(), false));
            }

            headerLayout.with(header).expand(header);

            final PopupButton controlsBtn = new PopupButton();
            controlsBtn.addStyleName(WebUIConstants.BUTTON_LINK);

            boolean canWrite = CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.MILESTONES);
            boolean canExecute = CurrentProjectVariables.canAccess(ProjectRolePermissionCollections.MILESTONES);
            OptionPopupContent popupContent = new OptionPopupContent();

            if (canWrite) {
                MButton editBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT), clickEvent ->
                        EventBusFactory.getInstance().post(new MilestoneEvent.GotoEdit(this, milestone))).withIcon(FontAwesome.EDIT);
                popupContent.addOption(editBtn);
            }
            if (canExecute) {
                MButton deleteBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_DELETE), clickEvent -> {
                    ConfirmDialogExt.show(UI.getCurrent(),
                            UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE, UserUIContext.getSiteName()),
                            UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                            UserUIContext.getMessage(GenericI18Enum.BUTTON_YES),
                            UserUIContext.getMessage(GenericI18Enum.BUTTON_NO),
                            confirmDialog -> {
                                if (confirmDialog.isConfirmed()) {
                                    MilestoneService milestoneService = AppContextUtil.getSpringBean(MilestoneService.class);
                                    milestoneService.removeWithSession(milestone, UserUIContext.getUsername(), UserUIContext.getAccountId());
                                    ((ComponentContainer) KanbanBlock.this.getParent()).removeComponent(KanbanBlock.this);
                                }
                            });
                }).withIcon(FontAwesome.TRASH_O);
                popupContent.addDangerOption(deleteBtn);
            }

            controlsBtn.setContent(popupContent);
            headerLayout.with(controlsBtn);

            MButton newAssignmentBtn = new MButton(UserUIContext.getMessage(ProjectCommonI18nEnum.ACTION_NEW_ASSIGNMENT),
                    clickEvent -> UI.getCurrent().addWindow(new AssignmentAddWindow(new LocalDate().toDate(),
                            CurrentProjectVariables.getProjectId(), false))).withIcon(FontAwesome.PLUS).withStyleName(BUTTON_ACTION);

            this.with(headerLayout, dragLayoutContainer, newAssignmentBtn).withAlign(newAssignmentBtn, Alignment.MIDDLE_RIGHT);
        }

        void addBlockItem(KanbanAssignmentBlockItem comp) {
            dragLayoutContainer.addComponent(comp);
            updateComponentCount();
        }

        private void updateComponentCount() {
            if (milestone != null) {
                header.setValue(String.format("%s (%d)", milestone.getName(), getAssignmentComponentCount()));
            } else {
                header.setValue(String.format("%s (%d)", UserUIContext.getMessage(GenericI18Enum.OPT_UNDEFINED),
                        getAssignmentComponentCount()));
            }
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

            ToggleGenericTaskSummaryField toggleGenericTaskSummaryField = new ToggleGenericTaskSummaryField(assignment);
            MHorizontalLayout headerLayout = new MHorizontalLayout(ELabel.fontIcon(ProjectAssetsManager.getAsset(assignment
                    .getType())).withWidthUndefined(), toggleGenericTaskSummaryField);
            root.addComponent(headerLayout);
        }
    }
}
