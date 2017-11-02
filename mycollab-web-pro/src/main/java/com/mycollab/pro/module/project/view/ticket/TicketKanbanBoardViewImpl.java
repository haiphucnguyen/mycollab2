package com.mycollab.pro.module.project.view.ticket;

import com.google.common.eventbus.Subscribe;
import com.mycollab.common.domain.OptionVal;
import com.mycollab.common.i18n.OptionI18nEnum;
import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.mycollab.common.service.OptionValService;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.domain.ProjectTicket;
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;
import com.mycollab.module.project.event.TicketEvent;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.query.TicketQueryInfo;
import com.mycollab.module.project.service.ProjectTicketService;
import com.mycollab.module.project.ui.components.BlockRowRender;
import com.mycollab.module.project.ui.components.IBlockContainer;
import com.mycollab.module.project.view.ProjectView;
import com.mycollab.module.project.view.service.TicketComponentFactory;
import com.mycollab.module.project.view.ticket.TicketKanbanBoardView;
import com.mycollab.module.project.view.ticket.TicketSearchPanel;
import com.mycollab.module.project.view.ticket.ToggleTicketSummaryField;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.*;
import com.mycollab.vaadin.event.HasSearchHandlers;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.UIUtils;
import com.mycollab.vaadin.web.ui.OptionPopupContent;
import com.mycollab.vaadin.web.ui.ToggleButtonGroup;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.dd.HorizontalDropLocation;
import com.vaadin.shared.ui.dd.VerticalDropLocation;
import com.vaadin.ui.*;
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

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
@ViewComponent
public class TicketKanbanBoardViewImpl extends AbstractVerticalPageView implements TicketKanbanBoardView {

    private ProjectTicketService ticketService = AppContextUtil.getSpringBean(ProjectTicketService.class);

    private TicketSearchPanel searchPanel;
    private DDHorizontalLayout kanbanLayout;
    private Map<String, KanbanBlock> kanbanBlocks;
    private ProjectTicketSearchCriteria baseCriteria;

    private ApplicationEventListener<TicketEvent.SearchRequest> searchHandler = new
            ApplicationEventListener<TicketEvent.SearchRequest>() {
                @Override
                @Subscribe
                public void handle(TicketEvent.SearchRequest event) {
                    ProjectTicketSearchCriteria criteria = event.getSearchCriteria();
                    criteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
                    queryTickets(criteria);
                }
            };

    public TicketKanbanBoardViewImpl() {
        this.setSizeFull();
        this.withSpacing(true).withMargin(new MarginInfo(false, true, true, true));
        searchPanel = new TicketSearchPanel();
        MHorizontalLayout groupWrapLayout = new MHorizontalLayout();
        groupWrapLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        searchPanel.addHeaderRight(groupWrapLayout);

        MButton advanceDisplayBtn = new MButton(UserUIContext.getMessage(ProjectCommonI18nEnum.OPT_LIST),
                clickEvent -> EventBusFactory.getInstance().post(new TicketEvent.GotoDashboard(this, null)))
                .withIcon(FontAwesome.NAVICON).withWidth("100px");

        MButton kanbanBtn = new MButton(UserUIContext.getMessage(ProjectCommonI18nEnum.OPT_KANBAN)).withIcon(FontAwesome.TH)
                .withWidth("100px");

        ToggleButtonGroup viewButtons = new ToggleButtonGroup();
        viewButtons.addButton(advanceDisplayBtn);
        viewButtons.addButton(kanbanBtn);
        viewButtons.withDefaultButton(kanbanBtn);
        groupWrapLayout.addComponent(viewButtons);

        kanbanLayout = new DDHorizontalLayout();
        kanbanLayout.setHeight("100%");
        kanbanLayout.addStyleName("kanban-layout");
        kanbanLayout.setSpacing(true);
        kanbanLayout.setMargin(new MarginInfo(true, false, true, false));
        kanbanLayout.setComponentHorizontalDropRatio(0.3f);
        kanbanLayout.setDragMode(LayoutDragMode.CLONE_OTHER);

        // Enable dropping components
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
                }
            }

            @Override
            public AcceptCriterion getAcceptCriterion() {
                return new Not(VerticalLocationIs.MIDDLE);
            }
        });

        this.with(searchPanel, kanbanLayout).expand(kanbanLayout);
    }

    @Override
    public HasSearchHandlers<ProjectTicketSearchCriteria> getSearchHandlers() {
        return searchPanel;
    }

    @Override
    public void attach() {
        EventBusFactory.getInstance().register(searchHandler);
        super.attach();
    }

    @Override
    public void detach() {
        setProjectNavigatorVisibility(true);
        EventBusFactory.getInstance().unregister(searchHandler);
        super.detach();
    }

    private void setProjectNavigatorVisibility(boolean visibility) {
        ProjectView view = UIUtils.getRoot(this, ProjectView.class);
        if (view != null) {
            view.setNavigatorVisibility(visibility);
        }
    }

    @Override
    public void display() {
        searchPanel.selectQueryInfo(TicketQueryInfo.OPEN_TICKETS);
    }

    private void reload() {
        if (baseCriteria == null) {
            display();
        } else {
            queryTickets(baseCriteria);
        }
    }

    @Override
    public void queryTickets(ProjectTicketSearchCriteria searchCriteria) {
        baseCriteria = searchCriteria;
        kanbanLayout.removeAllComponents();
        kanbanBlocks = new ConcurrentHashMap<>();

        setProjectNavigatorVisibility(false);
        AsyncInvoker.access(getUI(), new AsyncInvoker.PageCommand() {
            @Override
            public void run() {
                StatusI18nEnum[] statuses = OptionI18nEnum.statuses;
                Arrays.stream(statuses).forEach(status -> {
                    KanbanBlock block = new KanbanBlock(status.name());
                    kanbanBlocks.put(status.name(), block);
                    kanbanLayout.addComponent(block);
                });
                push();

                int totalTickets = ticketService.getTotalCount(searchCriteria);
                searchPanel.setTotalCountNumber(totalTickets);
                int pages = totalTickets / 50;
                for (int page = 0; page < pages + 1; page++) {
                    List<ProjectTicket> tickets = (List<ProjectTicket>) ticketService.findPageableListByCriteria(new BasicSearchRequest<>(searchCriteria, page + 1, 50));
                    if (CollectionUtils.isNotEmpty(tickets)) {
                        for (ProjectTicket ticket : tickets) {
                            String status = ticket.getStatus();
                            KanbanBlock kanbanBlock = kanbanBlocks.get(status);
                            if (kanbanBlock != null) {
                                kanbanBlock.addBlockItem(new KanbanBlockItem(ticket));
                            }
                        }
                        this.push();
                    }
                }
            }
        });
    }

    private static class KanbanBlockItem extends BlockRowRender {
        private ProjectTicket projectTicket;

        private KanbanBlockItem(final ProjectTicket ticket) {
            this.projectTicket = ticket;
            this.addStyleName("kanban-item");

            TicketComponentFactory popupFieldFactory = AppContextUtil.getSpringBean(TicketComponentFactory.class);

            MHorizontalLayout headerLayout = new MHorizontalLayout();

            ToggleTicketSummaryField toggleTicketSummaryField = new ToggleTicketSummaryField(projectTicket);
            AbstractComponent priorityField = popupFieldFactory.createPriorityPopupField(projectTicket);
            headerLayout.with(priorityField, toggleTicketSummaryField).expand(toggleTicketSummaryField);

            this.with(headerLayout);

            CssLayout footer = new CssLayout();

            footer.addComponent(popupFieldFactory.createCommentsPopupField(projectTicket));
            footer.addComponent(popupFieldFactory.createFollowersPopupField(projectTicket));
            footer.addComponent(popupFieldFactory.createStartDatePopupField(projectTicket));
            footer.addComponent(popupFieldFactory.createEndDatePopupField(projectTicket));
            footer.addComponent(popupFieldFactory.createDueDatePopupField(projectTicket));
            footer.addComponent(popupFieldFactory.createAssigneePopupField(projectTicket));

            this.addComponent(footer);
        }
    }

    private class KanbanBlock extends MVerticalLayout implements IBlockContainer {
        private String status;
        private DDVerticalLayout dragLayoutContainer;
        private Label header;

        KanbanBlock(String stage) {
            this.withFullHeight().withWidth("350px").withStyleName("kanban-block").withMargin(false);
            this.status = stage;
            final String optionId = UUID.randomUUID().toString() + "-" + stage.hashCode();
            this.setId(optionId);
            JavaScript.getCurrent().execute("$('#" + optionId + "').css({'background-color':'red'});");

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
                    if (dragComponent instanceof KanbanBlockItem) {
                        KanbanBlockItem kanbanItem = (KanbanBlockItem) dragComponent;
                        int newIndex = details.getOverIndex();
                        if (details.getDropLocation() == VerticalDropLocation.BOTTOM) {
                            dragLayoutContainer.addComponent(kanbanItem);
                        } else if (newIndex == -1) {
                            dragLayoutContainer.addComponent(kanbanItem, 0);
                        } else {
                            dragLayoutContainer.addComponent(kanbanItem, newIndex);
                        }
                        ProjectTicket ticket = kanbanItem.projectTicket;
                        refresh();

                        Component sourceComponent = transferable.getSourceComponent();
                        KanbanBlock sourceKanban = UIUtils.getRoot(sourceComponent, KanbanBlock.class);
                        if (sourceKanban != null && sourceKanban != KanbanBlock.this) {
                            sourceKanban.refresh();
                        }

                        //Update task index
                        List<Map<String, Integer>> indexMap = new ArrayList<>();
                        for (int i = 0; i < dragLayoutContainer.getComponentCount(); i++) {
                            Component subComponent = dragLayoutContainer.getComponent(i);
                            if (subComponent instanceof KanbanBlockItem) {
                                KanbanBlockItem blockItem = (KanbanBlockItem) dragLayoutContainer.getComponent(i);
                                Map<String, Integer> map = new HashMap<>(2);
                                map.put("id", blockItem.projectTicket.getTypeId());
                                map.put("index", i);
                                indexMap.add(map);
                            }
                        }
                        if (indexMap.size() > 0) {
//                            ticketService.massUpdateTaskIndexes(indexMap, AppUI.getAccountId());
                        }
                    }
                }

                @Override
                public AcceptCriterion getAcceptCriterion() {
                    return new Not(VerticalLocationIs.MIDDLE);
                }
            });
            new Restrain(dragLayoutContainer).setMinHeight("50px").setMaxHeight((UIUtils.getBrowserHeight() - 390) + "px");

            MHorizontalLayout headerLayout = new MHorizontalLayout().withSpacing(false).withFullWidth().withStyleName("header");
            header = new Label(UserUIContext.getMessage(StatusI18nEnum.class, stage));
            headerLayout.with(header).expand(header);

            final PopupButton controlsBtn = new PopupButton();
            controlsBtn.addStyleName(WebThemes.BUTTON_LINK);
            headerLayout.with(controlsBtn);

            OptionPopupContent popupContent = new OptionPopupContent();

            popupContent.addSeparator();
        }

        void addBlockItem(KanbanBlockItem comp) {
            dragLayoutContainer.addComponent(comp);
            refresh();
        }

        private int getTicketComponentCount() {
            Component testComp = (dragLayoutContainer.getComponentCount() > 0) ? dragLayoutContainer.getComponent(0) : null;
            if (testComp instanceof KanbanBlockItem || testComp == null) {
                return dragLayoutContainer.getComponentCount();
            } else {
                return (dragLayoutContainer.getComponentCount() - 1);
            }
        }

        @Override
        public void refresh() {
            header.setValue(String.format("%s (%d)", status, getTicketComponentCount()));
        }
    }
}
