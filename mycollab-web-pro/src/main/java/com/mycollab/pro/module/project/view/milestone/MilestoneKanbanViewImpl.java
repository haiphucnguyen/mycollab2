package com.mycollab.pro.module.project.view.milestone;

import com.google.common.eventbus.Subscribe;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.eventmanager.ApplicationEventListener;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTooltipGenerator;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Milestone;
import com.mycollab.module.project.domain.ProjectTicket;
import com.mycollab.module.project.domain.SimpleMilestone;
import com.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;
import com.mycollab.module.project.event.MilestoneEvent;
import com.mycollab.module.project.event.TicketEvent;
import com.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.mycollab.module.project.i18n.OptionI18nEnum.MilestoneStatus;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.i18n.TicketI18nEnum;
import com.mycollab.module.project.service.MilestoneService;
import com.mycollab.module.project.service.ProjectTicketService;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.view.ProjectView;
import com.mycollab.module.project.view.milestone.IMilestoneKanbanView;
import com.mycollab.module.project.view.milestone.MilestoneAddWindow;
import com.mycollab.module.project.view.milestone.ToggleTicketSummaryField;
import com.mycollab.module.project.view.service.TicketComponentFactory;
import com.mycollab.pro.module.project.view.assignments.AssignmentSearchPanel;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AsyncInvoker;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
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
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.shared.ui.dd.HorizontalDropLocation;
import com.vaadin.shared.ui.dd.VerticalDropLocation;
import com.vaadin.ui.*;
import com.vaadin.ui.components.colorpicker.ColorPickerPopup;
import fi.jasoft.dragdroplayouts.DDHorizontalLayout;
import fi.jasoft.dragdroplayouts.DDVerticalLayout;
import fi.jasoft.dragdroplayouts.client.ui.LayoutDragMode;
import fi.jasoft.dragdroplayouts.events.LayoutBoundTransferable;
import fi.jasoft.dragdroplayouts.events.VerticalLocationIs;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.jouni.restrain.Restrain;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.*;
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
    private ProjectTicketSearchCriteria baseCriteria;
    private MButton toggleShowClosedMilestonesBtn;
    private boolean displayClosedMilestones = false;

    private ApplicationEventListener<MilestoneEvent.NewMilestoneAdded> newMilestoneHandler = new
            ApplicationEventListener<MilestoneEvent.NewMilestoneAdded>() {
                @Override
                @Subscribe
                public void handle(MilestoneEvent.NewMilestoneAdded event) {
                    insertMilestone((Integer) event.getData());
                }
            };

    private ApplicationEventListener<TicketEvent.NewTicketAdded> newAssignmentHandler = new ApplicationEventListener<TicketEvent.NewTicketAdded>() {
        @Override
        @Subscribe
        public void handle(TicketEvent.NewTicketAdded event) {
            ProjectTicketService projectTicketService = AppContextUtil.getSpringBean(ProjectTicketService.class);
            ProjectTicket ticket = projectTicketService.findAssignment(event.getTypeVal(), event.getTypeIdVal());
            if (ticket != null) {
                insertTicket(ticket);
            }
        }
    };

    public MilestoneKanbanViewImpl() {
        this.setSizeFull();
        this.withSpacing(true).withMargin(new MarginInfo(false, true, true, true));
    }

    @Override
    protected void displayView() {
        searchPanel = new AssignmentSearchPanel(false) {
            @Override
            protected ComponentContainer buildSearchTitle() {
                return new MHorizontalLayout(ELabel.h2(ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE).getHtml() +
                        " " + UserUIContext.getMessage(ProjectCommonI18nEnum.OPT_KANBAN)).withWidthUndefined());
            }
        };

        searchPanel.addSearchHandler(criteria -> {
            queryTickets(criteria);
        });

        kanbanLayout = new DDHorizontalLayout();
        kanbanLayout.setHeight("100%");
        kanbanLayout.addStyleName("kanban-layout");
        kanbanLayout.setSpacing(true);
        kanbanLayout.setMargin(new MarginInfo(true, false, true, false));
        kanbanLayout.setComponentHorizontalDropRatio(0.3f);
        kanbanLayout.setDragMode(LayoutDragMode.CLONE_OTHER);

        kanbanLayout.setDropHandler(new DropHandler() {
            @Override
            public void drop(DragAndDropEvent event) {
                LayoutBoundTransferable transferable = (LayoutBoundTransferable) event.getTransferable();

                DDHorizontalLayout.HorizontalLayoutTargetDetails details = (DDHorizontalLayout.HorizontalLayoutTargetDetails) event
                        .getTargetDetails();
                Component dragComponent = transferable.getComponent();
                if (dragComponent instanceof KanbanBlock) {
                    KanbanBlock kanbanItem = (KanbanBlock) dragComponent;
                    int newIndex = details.getOverIndex();
                    if (details.getDropLocation() == HorizontalDropLocation.RIGHT) {
                        kanbanLayout.addComponent(kanbanItem);
                    } else if (newIndex == -1) {
                        kanbanLayout.addComponent(kanbanItem, 0);
                    } else {
                        kanbanLayout.addComponent(kanbanItem, newIndex);
                    }

                    //Update options index for this project
                    List<Map<String, Integer>> indexMap = new ArrayList<>();
                    for (int i = 0; i < kanbanLayout.getComponentCount(); i++) {
                        KanbanBlock blockItem = (KanbanBlock) kanbanLayout.getComponent(i);
                        if (blockItem != nullBlock) {
                            Map<String, Integer> map = new HashMap<>(2);
                            map.put("id", blockItem.milestone.getId());
                            map.put("index", i);
                            indexMap.add(map);
                        }
                    }
                    if (indexMap.size() > 0) {
                        AppContextUtil.getSpringBean(MilestoneService.class).massUpdateOptionIndexes(indexMap, MyCollabUI.getAccountId());
                    }
                }
            }

            @Override
            public AcceptCriterion getAcceptCriterion() {
                return new Not(VerticalLocationIs.MIDDLE);
            }
        });

        this.with(searchPanel, kanbanLayout).expand(kanbanLayout);

        toggleShowClosedMilestonesBtn = new MButton("", clickEvent -> {
            displayClosedMilestones = !displayClosedMilestones;
            reload();
            toggleShowButton();
        }).withStyleName(WebUIConstants.BUTTON_LINK);


        MButton boardBtn = new MButton(UserUIContext.getMessage(ProjectCommonI18nEnum.OPT_BOARD), clickEvent ->
                EventBusFactory.getInstance().post(new MilestoneEvent.GotoRoadmap(this))).withIcon(FontAwesome.NAVICON).withWidth("100px");

        MButton advanceDisplayBtn = new MButton(UserUIContext.getMessage(ProjectCommonI18nEnum.OPT_LIST),
                clickEvent -> EventBusFactory.getInstance().post(new MilestoneEvent.GotoList(this, null)))
                .withIcon(FontAwesome.SERVER).withWidth("100px");

        MButton kanbanBtn = new MButton(UserUIContext.getMessage(ProjectCommonI18nEnum.OPT_KANBAN)).withIcon(FontAwesome.TH)
                .withWidth("100px");

        MButton newMilestoneBtn = new MButton(UserUIContext.getMessage(MilestoneI18nEnum.NEW), clickEvent -> {
            SimpleMilestone milestone = new SimpleMilestone();
            milestone.setSaccountid(MyCollabUI.getAccountId());
            milestone.setProjectid(CurrentProjectVariables.getProjectId());
            UI.getCurrent().addWindow(new MilestoneAddWindow(milestone));
        }).withIcon(FontAwesome.PLUS).withStyleName(WebUIConstants.BUTTON_ACTION);

        ToggleButtonGroup viewButtons = new ToggleButtonGroup();
        viewButtons.addButton(boardBtn);
        viewButtons.addButton(advanceDisplayBtn);
        viewButtons.addButton(kanbanBtn);
        viewButtons.withDefaultButton(kanbanBtn);
        MHorizontalLayout groupWrapLayout = new MHorizontalLayout(toggleShowClosedMilestonesBtn, newMilestoneBtn, viewButtons);
        groupWrapLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        searchPanel.addHeaderRight(groupWrapLayout);

        ProjectTicketSearchCriteria searchCriteria = new ProjectTicketSearchCriteria();
        searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        searchCriteria.setTypes(new SetSearchField<>(ProjectTypeConstants.BUG, ProjectTypeConstants.TASK,
                ProjectTypeConstants.RISK));
        queryTickets(searchCriteria);
    }

    private void reload() {
        queryTickets(baseCriteria);
    }

    private void toggleShowButton() {
        if (displayClosedMilestones) {
            toggleShowClosedMilestonesBtn.setCaption(UserUIContext.getMessage(MilestoneI18nEnum.OPT_HIDE_CLOSED_MILESTONES));
        } else {
            toggleShowClosedMilestonesBtn.setCaption(UserUIContext.getMessage(MilestoneI18nEnum.OPT_SHOW_CLOSED_MILESTONES));
        }
    }

    private void queryTickets(ProjectTicketSearchCriteria searchCriteria) {
        baseCriteria = searchCriteria;
        kanbanLayout.removeAllComponents();
        toggleShowButton();
        kanbanBlocks = new ConcurrentHashMap<>();
        setProjectNavigatorVisibility(false);
        MilestoneService milestoneService = AppContextUtil.getSpringBean(MilestoneService.class);
        ProjectTicketService projectTicketService = AppContextUtil.getSpringBean(ProjectTicketService.class);
        AsyncInvoker.access(getUI(), new AsyncInvoker.PageCommand() {
            @Override
            public void run() {
                MilestoneSearchCriteria milestoneSearchCriteria = new MilestoneSearchCriteria();
                milestoneSearchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
                milestoneSearchCriteria.setOrderFields(Collections.singletonList(new SearchCriteria.OrderField("orderIndex", SearchCriteria.ASC)));
                if (displayClosedMilestones) {
                    milestoneSearchCriteria.setStatuses(null);
                } else {
                    milestoneSearchCriteria.setStatuses(new SetSearchField<>(MilestoneStatus.Future.name(), MilestoneStatus.InProgress.name()));
                }
                List<SimpleMilestone> milestones = milestoneService.findPageableListByCriteria(new BasicSearchRequest<>(milestoneSearchCriteria));
                for (SimpleMilestone milestone : milestones) {
                    KanbanBlock kanbanBlock = new KanbanBlock(milestone);
                    kanbanBlocks.put(milestone.getId(), kanbanBlock);
                    kanbanLayout.addComponent(kanbanBlock);
                }

                nullBlock = new KanbanBlock(null);
                kanbanLayout.addComponent(nullBlock);
                this.push();

                int totalTasks = projectTicketService.getTotalCount(searchCriteria);
                searchPanel.setTotalCountNumber(totalTasks);
                int pages = totalTasks / 50;
                for (int page = 0; page < pages + 1; page++) {
                    List<ProjectTicket> assignments = projectTicketService.findPageableListByCriteria(new BasicSearchRequest<>(searchCriteria, page + 1, 50));
                    if (CollectionUtils.isNotEmpty(assignments)) {
                        for (ProjectTicket assignment : assignments) {
                            if (assignment.getMilestoneId() != null) {
                                KanbanBlock kanbanBlock = kanbanBlocks.get(assignment.getMilestoneId());
                                if (kanbanBlock != null) {
                                    kanbanBlock.addBlockItem(new KanbanTicketBlockItem(assignment));
                                }
                            } else {
                                nullBlock.addBlockItem(new KanbanTicketBlockItem(assignment));
                            }
                        }
                    }
                    this.push();
                }
            }
        });
    }

    private void insertMilestone(Integer milestoneId) {
        MilestoneService milestoneService = AppContextUtil.getSpringBean(MilestoneService.class);
        SimpleMilestone milestone = milestoneService.findById(milestoneId, MyCollabUI.getAccountId());
        if (milestone != null) {
            KanbanBlock kanbanBlock = new KanbanBlock(milestone);
            kanbanBlocks.put(milestone.getId(), kanbanBlock);
            kanbanLayout.addComponent(kanbanBlock, 0);
        }
    }

    private void insertTicket(ProjectTicket ticket) {
        KanbanBlock block = null;
        if (ticket.getMilestoneId() != null) {
            block = kanbanBlocks.get(ticket.getMilestoneId());
        }
        if (block != null) {
            block.addBlockItem(new KanbanTicketBlockItem(ticket));
        } else {
            nullBlock.addBlockItem(new KanbanTicketBlockItem(ticket));
        }
    }

    @Override
    public void attach() {
        EventBusFactory.getInstance().register(newMilestoneHandler);
        EventBusFactory.getInstance().register(newAssignmentHandler);
        super.attach();
    }

    @Override
    public void detach() {
        setProjectNavigatorVisibility(true);
        EventBusFactory.getInstance().unregister(newMilestoneHandler);
        EventBusFactory.getInstance().unregister(newAssignmentHandler);
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
            String valId;
            if (milestone != null) {
                valId = UUID.randomUUID().toString() + "-" + milestone.hashCode();
                this.setId(valId);
                JavaScript.getCurrent().execute("$('#" + valId + "').css({'background-color':'#" + milestone.getColor() + "'});");
            } else {
                valId = UUID.randomUUID().toString();
            }
            final String optionId = valId;
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
                    if (dragComponent instanceof KanbanTicketBlockItem) {
                        KanbanTicketBlockItem kanbanItem = (KanbanTicketBlockItem) dragComponent;
                        int newIndex = details.getOverIndex();
                        if (details.getDropLocation() == VerticalDropLocation.BOTTOM) {
                            dragLayoutContainer.addComponent(kanbanItem);
                        } else if (newIndex == -1) {
                            dragLayoutContainer.addComponent(kanbanItem, 0);
                        } else {
                            dragLayoutContainer.addComponent(kanbanItem, newIndex);
                        }
                        ProjectTicket task = kanbanItem.ticket;
                        if (milestone == null) {
                            task.setMilestoneId(null);
                        } else {
                            task.setMilestoneId(milestone.getId());
                        }
                        ProjectTicketService projectTicketService = AppContextUtil.getSpringBean(ProjectTicketService.class);
                        projectTicketService.updateAssignmentValue(task, UserUIContext.getUsername());

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
            MHorizontalLayout headerLayout = new MHorizontalLayout().withFullWidth().withStyleName("header");
            if (milestone == null) {
                header = new ELabel(UserUIContext.getMessage(GenericI18Enum.OPT_UNDEFINED)).withStyleName(UIConstants.TEXT_ELLIPSIS)
                        .withDescription(UserUIContext.getMessage(GenericI18Enum.OPT_UNDEFINED));
                headerLayout.with(header).expand(header);
            } else {
                header = new ELabel(milestone.getName()).withStyleName(UIConstants.TEXT_ELLIPSIS)
                        .withDescription(ProjectTooltipGenerator.generateToolTipMilestone(UserUIContext.getUserLocale(),
                                MyCollabUI.getDateFormat(), milestone, MyCollabUI.getSiteUrl(), UserUIContext.getUserTimeZone(), false));
                headerLayout.with(ELabel.fontIcon(ProjectAssetsManager.getMilestoneStatus(milestone.getStatus())), header).expand(header);
            }

            final PopupButton controlsBtn = new PopupButton();
            controlsBtn.addStyleName(WebUIConstants.BUTTON_LINK);

            boolean canWrite = CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.MILESTONES);
            boolean canExecute = CurrentProjectVariables.canAccess(ProjectRolePermissionCollections.MILESTONES);
            OptionPopupContent popupContent = new OptionPopupContent();

            if (canWrite) {
                MButton editBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT), clickEvent ->
                        EventBusFactory.getInstance().post(new MilestoneEvent.GotoEdit(this, milestone))).withIcon(FontAwesome.EDIT);
                popupContent.addOption(editBtn);

                if (milestone != null) {
                    MButton changeColorBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.ACTION_CHANGE_COLOR), clickEvent -> {
                        ColorPickerPopup popup = new ColorPickerPopup(Color.CYAN);
                        popup.center();
                        UI.getCurrent().addWindow(popup);
                        popup.addColorChangeListener(colorChangeEvent -> {
                            Color color = colorChangeEvent.getColor();
                            String colorStr = color.getCSS().substring(1);
                            MilestoneService optionValService = AppContextUtil.getSpringBean(MilestoneService.class);
                            milestone.setColor(colorStr);
                            optionValService.updateWithSession(milestone, UserUIContext.getUsername());
                            JavaScript.getCurrent().execute("$('#" + optionId + "').css({'background-color':'#"
                                    + colorStr + "'});");
                        });
                        controlsBtn.setPopupVisible(false);
                    }).withIcon(FontAwesome.PENCIL);
                    popupContent.addOption(changeColorBtn);
                }

            }
            if (canExecute) {
                MButton deleteBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_DELETE), clickEvent -> {
                    ConfirmDialogExt.show(UI.getCurrent(),
                            UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE, MyCollabUI.getSiteName()),
                            UserUIContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                            UserUIContext.getMessage(GenericI18Enum.BUTTON_YES),
                            UserUIContext.getMessage(GenericI18Enum.BUTTON_NO),
                            confirmDialog -> {
                                if (confirmDialog.isConfirmed()) {
                                    MilestoneService milestoneService = AppContextUtil.getSpringBean(MilestoneService.class);
                                    milestoneService.removeWithSession(milestone, UserUIContext.getUsername(), MyCollabUI.getAccountId());
                                    ((ComponentContainer) KanbanBlock.this.getParent()).removeComponent(KanbanBlock.this);
                                }
                            });
                }).withIcon(FontAwesome.TRASH_O);
                popupContent.addDangerOption(deleteBtn);
            }

            controlsBtn.setContent(popupContent);
            headerLayout.with(controlsBtn);

            MButton newAssignmentBtn = new MButton(UserUIContext.getMessage(TicketI18nEnum.NEW),
                    clickEvent -> UI.getCurrent().addWindow(AppContextUtil.getSpringBean(TicketComponentFactory.class)
                            .createNewTicketWindow(null, CurrentProjectVariables.getProjectId(), milestone.getId(), false)))
                    .withIcon(FontAwesome.PLUS).withStyleName(BUTTON_ACTION);

            this.with(headerLayout, dragLayoutContainer, newAssignmentBtn).withAlign(newAssignmentBtn, Alignment.MIDDLE_RIGHT);
        }

        void addBlockItem(KanbanTicketBlockItem comp) {
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
            if (testComp instanceof KanbanTicketBlockItem || testComp == null) {
                return dragLayoutContainer.getComponentCount();
            } else {
                return (dragLayoutContainer.getComponentCount() - 1);
            }
        }
    }

    private static class KanbanTicketBlockItem extends CustomComponent {
        private ProjectTicket ticket;

        KanbanTicketBlockItem(final ProjectTicket ticket) {
            this.ticket = ticket;
            MVerticalLayout root = new MVerticalLayout();
            root.addStyleName("kanban-item");
            this.setCompositionRoot(root);

            ToggleTicketSummaryField toggleTicketSummaryField = new ToggleTicketSummaryField(ticket);
            MHorizontalLayout headerLayout = new MHorizontalLayout(ELabel.fontIcon(ProjectAssetsManager.getAsset(ticket.getType())).withWidthUndefined(),
                    toggleTicketSummaryField).expand(toggleTicketSummaryField).withFullWidth();
            root.addComponent(headerLayout);

            CssLayout footer = new CssLayout();
            TicketComponentFactory fieldFactory = AppContextUtil.getSpringBean(TicketComponentFactory.class);
            footer.addComponent(fieldFactory.createCommentsPopupField(ticket));
            footer.addComponent(fieldFactory.createPriorityPopupField(ticket));
            footer.addComponent(fieldFactory.createFollowersPopupField(ticket));
            footer.addComponent(fieldFactory.createStatusPopupField(ticket));
            footer.addComponent(fieldFactory.createStartDatePopupField(ticket));
            footer.addComponent(fieldFactory.createEndDatePopupField(ticket));
            footer.addComponent(fieldFactory.createDueDatePopupField(ticket));
            root.addComponent(footer);
        }
    }
}
