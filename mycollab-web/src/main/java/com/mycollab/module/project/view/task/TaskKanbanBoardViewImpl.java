/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.project.view.task;

import com.google.common.eventbus.Subscribe;
import com.mycollab.common.domain.OptionVal;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.mycollab.common.service.OptionValService;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.eventmanager.ApplicationEventListener;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleTask;
import com.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.mycollab.module.project.events.TaskEvent;
import com.mycollab.module.project.i18n.TaskI18nEnum;
import com.mycollab.module.project.service.ProjectTaskService;
import com.mycollab.module.project.view.ProjectView;
import com.mycollab.module.project.view.kanban.AddNewColumnWindow;
import com.mycollab.module.project.view.kanban.DeleteColumnWindow;
import com.mycollab.module.project.view.task.components.TaskSavedFilterComboBox;
import com.mycollab.module.project.view.task.components.TaskSearchPanel;
import com.mycollab.module.project.view.task.components.ToggleTaskSummaryField;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.AsyncInvoker;
import com.mycollab.vaadin.events.HasSearchHandlers;
import com.mycollab.vaadin.mvp.AbstractPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.ui.UIUtils;
import com.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.mycollab.vaadin.web.ui.OptionPopupContent;
import com.mycollab.vaadin.web.ui.ToggleButtonGroup;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.jouni.restrain.Restrain;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
@ViewComponent
public class TaskKanbanBoardViewImpl extends AbstractPageView implements TaskKanbanBoardView {
    private static Logger LOG = LoggerFactory.getLogger(TaskKanbanBoardViewImpl.class);

    private ProjectTaskService taskService = AppContextUtil.getSpringBean(ProjectTaskService.class);
    private OptionValService optionValService = AppContextUtil.getSpringBean(OptionValService.class);

    private TaskSearchPanel searchPanel;
    private DDHorizontalLayout kanbanLayout;
    private Map<String, KanbanBlock> kanbanBlocks;
    private ComponentContainer newTaskComp = null;
    private MButton toggleShowColumsBtn;
    private boolean displayHiddenColumns = false;
    private TaskSearchCriteria baseCriteria;

    private ApplicationEventListener<TaskEvent.SearchRequest> searchHandler = new
            ApplicationEventListener<TaskEvent.SearchRequest>() {
                @Override
                @Subscribe
                public void handle(TaskEvent.SearchRequest event) {
                    TaskSearchCriteria criteria = (TaskSearchCriteria) event.getData();
                    if (criteria != null) {
                        criteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));
                        criteria.setOrderFields(Collections.singletonList(new SearchCriteria.OrderField("taskindex", SearchCriteria.ASC)));
                        queryTask(criteria);
                    }
                }
            };

    public TaskKanbanBoardViewImpl() {
        this.setSizeFull();
        this.withSpacing(true).withMargin(new MarginInfo(false, true, true, true));
        searchPanel = new TaskSearchPanel();
        MHorizontalLayout groupWrapLayout = new MHorizontalLayout();
        groupWrapLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        searchPanel.addHeaderRight(groupWrapLayout);

        toggleShowColumsBtn = new MButton("", clickEvent -> {
            displayHiddenColumns = !displayHiddenColumns;
            reload();
            toggleShowButton();
        }).withStyleName(UIConstants.BUTTON_LINK);
        groupWrapLayout.addComponent(toggleShowColumsBtn);
        toggleShowButton();

        if (CurrentProjectVariables.canAccess(ProjectRolePermissionCollections.TASKS)) {
            MButton addNewColumnBtn = new MButton(AppContext.getMessage(TaskI18nEnum.ACTION_NEW_COLUMN),
                    clickEvent -> UI.getCurrent().addWindow(new AddNewColumnWindow(this, ProjectTypeConstants.TASK, "status")))
                    .withIcon(FontAwesome.PLUS).withStyleName(UIConstants.BUTTON_ACTION);
            groupWrapLayout.addComponent(addNewColumnBtn);
        }

        MButton deleteColumnBtn = new MButton("Delete columns",
                clickEvent -> UI.getCurrent().addWindow(new DeleteColumnWindow(this, ProjectTypeConstants.TASK)))
                .withIcon(FontAwesome.TRASH_O).withStyleName(UIConstants.BUTTON_DANGER);
        deleteColumnBtn.setVisible(CurrentProjectVariables.canAccess(ProjectRolePermissionCollections.TASKS));

        MButton advanceDisplayBtn = new MButton("List",
                clickEvent -> EventBusFactory.getInstance().post(new TaskEvent.GotoDashboard(this, null)))
                .withIcon(FontAwesome.SITEMAP).withWidth("100px");

        MButton kanbanBtn = new MButton("Kanban").withIcon(FontAwesome.TH).withWidth("100px");

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

//      Enable dropping components
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
                        Map<String, Integer> map = new HashMap<>(2);
                        map.put("id", blockItem.optionVal.getId());
                        map.put("index", i);
                        indexMap.add(map);
                    }
                    if (indexMap.size() > 0) {
                        optionValService.massUpdateOptionIndexes(indexMap, AppContext.getAccountId());
                    }
                }
            }

            @Override
            public AcceptCriterion getAcceptCriterion() {
                return new Not(VerticalLocationIs.MIDDLE);
            }
        });
        this.setWidth("100%");
        this.with(searchPanel, kanbanLayout).expand(kanbanLayout);
    }

    private void toggleShowButton() {
        if (displayHiddenColumns) {
            toggleShowColumsBtn.setCaption(AppContext.getMessage(TaskI18nEnum.ACTION_HIDE_COLUMNS));
        } else {
            toggleShowColumsBtn.setCaption(AppContext.getMessage(TaskI18nEnum.ACTION_SHOW_COLUMNS));
        }
    }

    @Override
    public HasSearchHandlers<TaskSearchCriteria> getSearchHandlers() {
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
        searchPanel.selectQueryInfo(TaskSavedFilterComboBox.OPEN_TASKS);
    }

    private void reload() {
        if (baseCriteria == null) {
            display();
        } else {
            queryTask(baseCriteria);
        }
    }

    @Override
    public void queryTask(final TaskSearchCriteria searchCriteria) {
        baseCriteria = searchCriteria;
        kanbanLayout.removeAllComponents();
        kanbanBlocks = new ConcurrentHashMap<>();

        setProjectNavigatorVisibility(false);
        AsyncInvoker.access(new AsyncInvoker.PageCommand() {
            @Override
            public void run() {
                List<OptionVal> optionVals = optionValService.findOptionVals(ProjectTypeConstants.TASK,
                        CurrentProjectVariables.getProjectId(), AppContext.getAccountId());
                for (OptionVal optionVal : optionVals) {
                    if (!displayHiddenColumns && Boolean.FALSE.equals(optionVal.getIsshow())) {
                        continue;
                    }
                    KanbanBlock kanbanBlock = new KanbanBlock(optionVal);
                    kanbanBlocks.put(optionVal.getTypeval(), kanbanBlock);
                    kanbanLayout.addComponent(kanbanBlock);
                }
                this.push();

                int totalTasks = taskService.getTotalCount(searchCriteria);
                searchPanel.setTotalCountNumber(totalTasks);
                int pages = totalTasks / 50;
                for (int page = 0; page < pages + 1; page++) {
                    List<SimpleTask> tasks = taskService.findPageableListByCriteria(new BasicSearchRequest<>(searchCriteria, page + 1, 50));
                    if (CollectionUtils.isNotEmpty(tasks)) {
                        for (SimpleTask task : tasks) {
                            String status = task.getStatus();
                            KanbanBlock kanbanBlock = kanbanBlocks.get(status);
                            if (kanbanBlock != null) {
                                kanbanBlock.addBlockItem(new KanbanTaskBlockItem(task));
                            }
                        }
                        this.push();
                    }
                }
            }
        });
    }

    @Override
    public void addColumn(final OptionVal option) {
        AsyncInvoker.access(new AsyncInvoker.PageCommand() {
            @Override
            public void run() {
                KanbanBlock kanbanBlock = new KanbanBlock(option);
                kanbanBlocks.put(option.getTypeval(), kanbanBlock);
                kanbanLayout.addComponent(kanbanBlock);
            }
        });
    }

    private static class KanbanTaskBlockItem extends CustomComponent {
        private SimpleTask task;

        KanbanTaskBlockItem(final SimpleTask task) {
            this.task = task;
            MVerticalLayout root = new MVerticalLayout();
            root.addStyleName("kanban-item");
            this.setCompositionRoot(root);

            TaskPopupFieldFactory popupFieldFactory = ViewManager.getCacheComponent(TaskPopupFieldFactory.class);

            MHorizontalLayout headerLayout = new MHorizontalLayout();

            ToggleTaskSummaryField toggleTaskSummaryField = new ToggleTaskSummaryField(task, 70);
            AbstractComponent priorityField = popupFieldFactory.createPriorityPopupField(task);
            headerLayout.with(priorityField, toggleTaskSummaryField).expand(toggleTaskSummaryField);

            root.with(headerLayout);

            CssLayout footer = new CssLayout();

            AbstractComponent commentField = popupFieldFactory.createCommentsPopupField(task);
            footer.addComponent(commentField);

            AbstractComponent followerField = popupFieldFactory.createFollowersPopupField(task);
            footer.addComponent(followerField);

            AbstractComponent deadlineField = popupFieldFactory.createDeadlinePopupField(task);
            footer.addComponent(deadlineField);

            AbstractComponent assigneeField = popupFieldFactory.createAssigneePopupField(task);
            footer.addComponent(assigneeField);

            root.addComponent(footer);
        }
    }

    private class KanbanBlock extends MVerticalLayout {
        private OptionVal optionVal;
        private DDVerticalLayout dragLayoutContainer;
        private MHorizontalLayout buttonControls;
        private Button hideColumnBtn;
        private Label header;

        public KanbanBlock(final OptionVal stage) {
            this.withFullHeight().withWidth("300px").withStyleName("kanban-block").withMargin(false);
            this.optionVal = stage;
            final String optionId = UUID.randomUUID().toString() + "-" + stage.hashCode();
            this.setId(optionId);
            JavaScript.getCurrent().execute("$('#" + optionId + "').css({'background-color':'#" + stage.getColor() + "'});");

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
                    if (dragComponent instanceof KanbanTaskBlockItem) {
                        KanbanTaskBlockItem kanbanItem = (KanbanTaskBlockItem) dragComponent;
                        int newIndex = details.getOverIndex();
                        if (details.getDropLocation() == VerticalDropLocation.BOTTOM) {
                            dragLayoutContainer.addComponent(kanbanItem);
                        } else if (newIndex == -1) {
                            dragLayoutContainer.addComponent(kanbanItem, 0);
                        } else {
                            dragLayoutContainer.addComponent(kanbanItem, newIndex);
                        }
                        SimpleTask task = kanbanItem.task;
                        task.setStatus(optionVal.getTypeval());
                        ProjectTaskService taskService = AppContextUtil.getSpringBean(ProjectTaskService.class);
                        taskService.updateSelectiveWithSession(task, AppContext.getUsername());
                        updateComponentCount();

                        Component sourceComponent = transferable.getSourceComponent();
                        KanbanBlock sourceKanban = UIUtils.getRoot(sourceComponent, KanbanBlock.class);
                        if (sourceKanban != null && sourceKanban != KanbanBlock.this) {
                            sourceKanban.updateComponentCount();
                        }

                        //Update task index
                        List<Map<String, Integer>> indexMap = new ArrayList<>();
                        for (int i = 0; i < dragLayoutContainer.getComponentCount(); i++) {
                            Component subComponent = dragLayoutContainer.getComponent(i);
                            if (subComponent instanceof KanbanTaskBlockItem) {
                                KanbanTaskBlockItem blockItem = (KanbanTaskBlockItem) dragLayoutContainer.getComponent(i);
                                Map<String, Integer> map = new HashMap<>(2);
                                map.put("id", blockItem.task.getId());
                                map.put("index", i);
                                indexMap.add(map);
                            }
                        }
                        if (indexMap.size() > 0) {
                            taskService.massUpdateTaskIndexes(indexMap, AppContext.getAccountId());
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
            header = new Label(AppContext.getMessage(StatusI18nEnum.class, optionVal.getTypeval()));
            headerLayout.with(header).expand(header);

            final PopupButton controlsBtn = new PopupButton();
            controlsBtn.addStyleName(UIConstants.BUTTON_LINK);
            headerLayout.with(controlsBtn);

            String typeVal = optionVal.getTypeval();
            boolean canRename = !typeVal.equals(StatusI18nEnum.Closed.name()) && !typeVal.equals(StatusI18nEnum.Open.name());
            boolean canExecute = CurrentProjectVariables.canAccess(ProjectRolePermissionCollections.TASKS);

            OptionPopupContent popupContent = new OptionPopupContent();

            if (canExecute && canRename) {
                MButton renameColumnBtn = new MButton(AppContext.getMessage(TaskI18nEnum.ACTION_RENAME_COLUMN), clickEvent -> {
                    controlsBtn.setPopupVisible(false);
                    UI.getCurrent().addWindow(new RenameColumnWindow());
                }).withIcon(FontAwesome.EDIT);
                popupContent.addOption(renameColumnBtn);
            }

            if (canExecute) {
                hideColumnBtn = new Button("", clickEvent -> {
                    controlsBtn.setPopupVisible(false);
                    if (Boolean.FALSE.equals(optionVal.getIsshow())) {
                        optionVal.setIsshow(Boolean.TRUE);
                    } else {
                        optionVal.setIsshow(Boolean.FALSE);
                    }
                    optionValService.updateWithSession(optionVal, AppContext.getUsername());
                    toggleShowButton();
                    if (!displayHiddenColumns && Boolean.FALSE.equals(optionVal.getIsshow())) {
                        ((ComponentContainer) KanbanBlock.this.getParent()).removeComponent(KanbanBlock.this);
                    }
                });
                popupContent.addOption(hideColumnBtn);
            }

            if (canExecute) {
                MButton changeColorBtn = new MButton(AppContext.getMessage(TaskI18nEnum.ACTION_CHANGE_COLOR), clickEvent -> {
                    ColumnColorPickerWindow popup = new ColumnColorPickerWindow(Color.CYAN);
                    UI.getCurrent().addWindow(popup);
                    popup.addColorChangeListener(colorChangeEvent -> {
                        Color color = colorChangeEvent.getColor();
                        String colorStr = color.getCSS().substring(1);
                        OptionValService optionValService = AppContextUtil.getSpringBean(OptionValService.class);
                        optionVal.setColor(colorStr);
                        optionValService.updateWithSession(optionVal, AppContext.getUsername());
                        JavaScript.getCurrent().execute("$('#" + optionId + "').css({'background-color':'#" + colorStr + "'});");
                    });
                    controlsBtn.setPopupVisible(false);
                }).withIcon(FontAwesome.PENCIL);
                popupContent.addOption(changeColorBtn);
            }

            if (canExecute && canRename) {
                MButton deleteColumnBtn = new MButton(AppContext.getMessage(TaskI18nEnum.ACTION_DELETE_COLUMN), clickEvent -> {
                    if (getTaskComponentCount() > 0) {
                        NotificationUtil.showErrorNotification(AppContext.getMessage(TaskI18nEnum.ERROR_CAN_NOT_DELETE_COLUMN_HAS_TASK));
                    } else {
                        ConfirmDialogExt.show(UI.getCurrent(), AppContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE,
                                AppContext.getSiteName()),
                                AppContext.getMessage(GenericI18Enum.DIALOG_DELETE_MULTIPLE_ITEMS_MESSAGE),
                                AppContext.getMessage(GenericI18Enum.BUTTON_YES),
                                AppContext.getMessage(GenericI18Enum.BUTTON_NO),
                                confirmDialog -> {
                                    if (confirmDialog.isConfirmed()) {
                                        optionValService.removeWithSession(stage, AppContext.getUsername(), AppContext.getAccountId());
                                        ((ComponentContainer) KanbanBlock.this.getParent()).removeComponent(KanbanBlock.this);
                                    }
                                });
                    }
                    controlsBtn.setPopupVisible(false);
                }).withIcon(FontAwesome.TRASH_O);
                popupContent.addDangerOption(deleteColumnBtn);
            }

            popupContent.addSeparator();

            if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS)) {
                MButton addBtn = new MButton(AppContext.getMessage(TaskI18nEnum.NEW), clickEvent -> {
                    controlsBtn.setPopupVisible(false);
                    addNewTaskComp();
                }).withIcon(FontAwesome.PLUS);
                popupContent.addOption(addBtn);
            }
            controlsBtn.setContent(popupContent);

            if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS)) {
                MButton addNewBtn = new MButton(AppContext.getMessage(TaskI18nEnum.NEW), clickEvent -> addNewTaskComp())
                        .withIcon(FontAwesome.PLUS).withStyleName(UIConstants.BUTTON_ACTION);
                buttonControls = new MHorizontalLayout(addNewBtn).withAlign(addNewBtn, Alignment.MIDDLE_RIGHT).withFullWidth();
                this.with(headerLayout, dragLayoutContainer, buttonControls);
            } else {
                this.with(headerLayout, dragLayoutContainer);
            }
            toggleShowButton();
        }

        void toggleShowButton() {
            if (CurrentProjectVariables.canAccess(ProjectRolePermissionCollections.TASKS)) {
                if (Boolean.FALSE.equals(optionVal.getIsshow())) {
                    hideColumnBtn.setCaption(AppContext.getMessage(TaskI18nEnum.ACTION_SHOW_COLUMN));
                    hideColumnBtn.setIcon(FontAwesome.TOGGLE_ON);
                    ELabel invisibleLbl = new ELabel("Inv").withWidthUndefined().withStyleName(UIConstants.FIELD_NOTE)
                            .withDescription(AppContext.getMessage(TaskI18nEnum.OPT_INVISIBLE_COLUMN_DESCRIPTION));
                    buttonControls.addComponent(invisibleLbl, 0);
                    buttonControls.withAlign(invisibleLbl, Alignment.MIDDLE_LEFT);
                } else {
                    hideColumnBtn.setCaption(AppContext.getMessage(TaskI18nEnum.ACTION_HIDE_COLUMN));
                    hideColumnBtn.setIcon(FontAwesome.TOGGLE_OFF);
                    if (buttonControls.getComponentCount() > 1) {
                        buttonControls.removeComponent(buttonControls.getComponent(0));
                    }
                }
            }
        }

        void addBlockItem(KanbanTaskBlockItem comp) {
            dragLayoutContainer.addComponent(comp);
            updateComponentCount();
        }

        private int getTaskComponentCount() {
            Component testComp = (dragLayoutContainer.getComponentCount() > 0) ? dragLayoutContainer.getComponent(0) : null;
            if (testComp instanceof KanbanTaskBlockItem || testComp == null) {
                return dragLayoutContainer.getComponentCount();
            } else {
                return (dragLayoutContainer.getComponentCount() - 1);
            }
        }

        private void updateComponentCount() {
            header.setValue(String.format("%s (%d)", optionVal.getTypeval(), getTaskComponentCount()));
        }

        void addNewTaskComp() {
            Component testComp = (dragLayoutContainer.getComponentCount() > 0) ? dragLayoutContainer.getComponent(0) : null;
            if (testComp instanceof KanbanTaskBlockItem || testComp == null) {
                final SimpleTask task = new SimpleTask();
                task.setSaccountid(AppContext.getAccountId());
                task.setProjectid(CurrentProjectVariables.getProjectId());
                task.setPercentagecomplete(0d);
                task.setStatus(optionVal.getTypeval());
                task.setProjectShortname(CurrentProjectVariables.getShortName());
                final MVerticalLayout layout = new MVerticalLayout();
                layout.addStyleName("kanban-item");
                final TextField taskNameField = new TextField();
                taskNameField.focus();
                taskNameField.setWidth("100%");
                layout.with(taskNameField);
                MHorizontalLayout controlsBtn = new MHorizontalLayout();
                MButton saveBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_ADD), clickEvent -> {
                    String taskName = taskNameField.getValue();
                    if (StringUtils.isNotBlank(taskName)) {
                        task.setTaskname(taskName);
                        ProjectTaskService taskService = AppContextUtil.getSpringBean(ProjectTaskService.class);
                        taskService.saveWithSession(task, AppContext.getUsername());
                        dragLayoutContainer.removeComponent(layout);
                        KanbanTaskBlockItem kanbanTaskBlockItem = new KanbanTaskBlockItem(task);
                        dragLayoutContainer.addComponent(kanbanTaskBlockItem, 0);
                        updateComponentCount();
                    }
                }).withStyleName(UIConstants.BUTTON_ACTION);

                MButton cancelBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> {
                    dragLayoutContainer.removeComponent(layout);
                    newTaskComp = null;
                }).withStyleName(UIConstants.BUTTON_OPTION);
                controlsBtn.with(cancelBtn, saveBtn);
                layout.with(controlsBtn).withAlign(controlsBtn, Alignment.MIDDLE_RIGHT);
                if (newTaskComp != null && newTaskComp.getParent() != null) {
                    ((ComponentContainer) newTaskComp.getParent()).removeComponent(newTaskComp);
                }
                newTaskComp = layout;
                dragLayoutContainer.addComponent(layout, 0);
                dragLayoutContainer.markAsDirty();
            }
        }

        private class RenameColumnWindow extends MWindow {
            RenameColumnWindow() {
                super(AppContext.getMessage(TaskI18nEnum.ACTION_RENAME_COLUMN));
                withWidth("500px").withModal(true).withResizable(false);
                this.center();

                MVerticalLayout content = new MVerticalLayout().withMargin(false);
                this.setContent(content);

                final TextField columnNameField = new TextField();
                columnNameField.setValue(optionVal.getTypeval());
                GridFormLayoutHelper gridFormLayoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 1);
                gridFormLayoutHelper.addComponent(columnNameField, "Column name", 0, 0);

                MButton cancelBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                        .withStyleName(UIConstants.BUTTON_OPTION);

                MButton saveBtn = new MButton(AppContext.getMessage(GenericI18Enum.BUTTON_SAVE), clickEvent -> {
                    if (StringUtils.isNotBlank(columnNameField.getValue())) {
                        OptionValService optionValService = AppContextUtil.getSpringBean(OptionValService.class);
                        if (optionValService.isExistedOptionVal(ProjectTypeConstants.TASK, columnNameField
                                .getValue(), "status", optionVal.getExtraid(), AppContext.getAccountId())) {
                            NotificationUtil.showErrorNotification(AppContext.getMessage(TaskI18nEnum.ERROR_THERE_IS_ALREADY_COLUMN_NAME, columnNameField.getValue()));
                        } else {
                            taskService.massUpdateStatuses(optionVal.getTypeval(), columnNameField.getValue(), optionVal.getExtraid(),
                                    AppContext.getAccountId());
                            optionVal.setTypeval(columnNameField.getValue());
                            optionValService.updateWithSession(optionVal, AppContext.getUsername());
                            KanbanBlock.this.updateComponentCount();
                        }
                    } else {
                        NotificationUtil.showErrorNotification(AppContext.getMessage(TaskI18nEnum.ERROR_COLUMN_NAME_NOT_NULL));
                    }

                    close();
                }).withIcon(FontAwesome.SAVE).withStyleName(UIConstants.BUTTON_ACTION);

                MHorizontalLayout buttonControls = new MHorizontalLayout().withMargin(new MarginInfo(false, true, true, false))
                        .with(cancelBtn, saveBtn);
                content.with(gridFormLayoutHelper.getLayout(), buttonControls).withAlign(buttonControls, Alignment.MIDDLE_RIGHT);
            }
        }
    }

    private static class ColumnColorPickerWindow extends ColorPickerPopup {
        ColumnColorPickerWindow(Color initialColor) {
            super(initialColor);
            this.center();
        }
    }
}
