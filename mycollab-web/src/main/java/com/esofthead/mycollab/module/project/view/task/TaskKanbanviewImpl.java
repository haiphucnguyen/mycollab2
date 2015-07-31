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
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.domain.OptionVal;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.service.OptionValService;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectResources;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.kanban.AddNewColumnWindow;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.*;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Text;
import com.vaadin.event.LayoutEvents;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.Not;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.dd.HorizontalDropLocation;
import com.vaadin.shared.ui.dd.VerticalDropLocation;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import fi.jasoft.dragdroplayouts.DDHorizontalLayout;
import fi.jasoft.dragdroplayouts.DDVerticalLayout;
import fi.jasoft.dragdroplayouts.client.ui.LayoutDragMode;
import fi.jasoft.dragdroplayouts.events.LayoutBoundTransferable;
import fi.jasoft.dragdroplayouts.events.VerticalLocationIs;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.jouni.restrain.Restrain;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
@ViewComponent
public class TaskKanbanviewImpl extends AbstractPageView implements TaskKanbanview {
    private static Logger LOG = LoggerFactory.getLogger(TaskKanbanviewImpl.class);

    private ProjectTaskService taskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
    private OptionValService optionValService = ApplicationContextUtil.getSpringBean(OptionValService.class);

    private boolean projectNavigatorVisibility = false;

    private DDHorizontalLayout kanbanLayout;
    private Map<String, KanbanBlock> kanbanBlocks;
    private Button toogleMenuShowBtn;

    public TaskKanbanviewImpl() {
        this.setSizeFull();
        this.withMargin(new MarginInfo(false, true, true, true));

        MHorizontalLayout header = new MHorizontalLayout().withMargin(new MarginInfo(true, false, true, false))
                .withStyleName("hdr-view").withWidth("100%");
        header.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        Label headerText = new Label(String.format("%s %s (Kanban Board)", FontAwesome.TH.getHtml(),
                CurrentProjectVariables.getProject().getName()), ContentMode.HTML);
        headerText.setStyleName(UIConstants.HEADER_TEXT);
        CssLayout headerWrapper = new CssLayout();
        headerWrapper.addComponent(headerText);

        toogleMenuShowBtn = new Button("", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                projectNavigatorVisibility = !projectNavigatorVisibility;
                setProjectNavigatorVisibility(projectNavigatorVisibility);
                if (projectNavigatorVisibility) {
                    toogleMenuShowBtn.setCaption("Hide menu");
                } else {
                    toogleMenuShowBtn.setCaption("Show menu");
                }
            }
        });
        toogleMenuShowBtn.addStyleName(UIConstants.THEME_LINK);

        Button addNewColumnBtn = new Button("Add a new column", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().addWindow(new AddNewColumnWindow(TaskKanbanviewImpl.this, ProjectTypeConstants.TASK));
            }
        });
        addNewColumnBtn.setStyleName(UIConstants.THEME_GREEN_LINK);

        Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                EventBusFactory.getInstance().post(new ShellEvent.GotoProjectModule(this, new String[]{
                        "task", "dashboard", UrlEncodeDecoder.encode(CurrentProjectVariables.getProjectId())}));
            }
        });
        cancelBtn.setStyleName(UIConstants.THEME_GRAY_LINK);

        header.with(headerWrapper, toogleMenuShowBtn, addNewColumnBtn, cancelBtn).withAlign(headerWrapper, Alignment.MIDDLE_LEFT)
                .withAlign(toogleMenuShowBtn, Alignment.MIDDLE_RIGHT).withAlign(cancelBtn, Alignment.MIDDLE_RIGHT)
                .withAlign(addNewColumnBtn, Alignment.MIDDLE_RIGHT).expand(headerWrapper);

        kanbanLayout = new DDHorizontalLayout();
        kanbanLayout.setHeight("100%");
        kanbanLayout.addStyleName("kanban-layout");
        kanbanLayout.setSpacing(true);
        kanbanLayout.setComponentHorizontalDropRatio(0.3f);
        kanbanLayout.setDragMode(LayoutDragMode.CLONE);

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

                    //Update options index for this project
                    List<Map<String, Integer>> indexMap = new ArrayList<>();
                    for (int i = 0; i < kanbanLayout.getComponentCount(); i++) {
                        KanbanBlock blockItem = (KanbanBlock) kanbanLayout.getComponent(i);
                        Map<String, Integer> map = new HashedMap(2);
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
        this.with(header, kanbanLayout).expand(kanbanLayout);
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

    @Override
    public void display() {
        kanbanLayout.removeAllComponents();
        kanbanBlocks = new ConcurrentHashMap<>();

        toogleMenuShowBtn.setCaption("Show menu");
        setProjectNavigatorVisibility(false);

        TaskSearchCriteria searchCriteria = new TaskSearchCriteria();
        searchCriteria.setProjectid(new NumberSearchField(CurrentProjectVariables.getProjectId()));
        final List<SimpleTask> tasks = taskService.findPagableListByCriteria(new SearchRequest<>(searchCriteria));

        UI.getCurrent().access(new Runnable() {
            @Override
            public void run() {
                List<OptionVal> optionVals = optionValService.findOptionVals(ProjectTypeConstants.TASK,
                        CurrentProjectVariables.getProjectId(), AppContext.getAccountId());
                for (OptionVal optionVal : optionVals) {
                    KanbanBlock kanbanBlock = new KanbanBlock(optionVal);
                    kanbanBlocks.put(optionVal.getTypeval(), kanbanBlock);
                    kanbanLayout.addComponent(kanbanBlock);
                }

                for (SimpleTask task : tasks) {
                    String status = task.getStatus();
                    KanbanBlock kanbanBlock = kanbanBlocks.get(status);
                    if (kanbanBlock == null) {
                        LOG.error("Can not find a kanban block for status: " + status);
                    } else {
                        kanbanBlock.addBlockItem(new KanbanTaskBlockItem(task));
                    }
                    UI.getCurrent().push();
                }
            }
        });
    }

    @Override
    public void addColumn(final OptionVal option) {
        UI.getCurrent().access(new Runnable() {
            @Override
            public void run() {
                KanbanBlock kanbanBlock = new KanbanBlock(option);
                kanbanBlocks.put(option.getTypeval(), kanbanBlock);
                kanbanLayout.addComponent(kanbanBlock);
                UI.getCurrent().push();
            }
        });
    }

    private static class KanbanTaskBlockItem extends CustomComponent {
        private SimpleTask task;

        KanbanTaskBlockItem(SimpleTask task) {
            this.task = task;
            MVerticalLayout root = new MVerticalLayout();
            root.addStyleName("kanban-item");
            this.setCompositionRoot(root);

            String taskname = String.format("[%s-%s] %s", task.getProjectShortname(), task.getTaskkey(), task.getTaskname());
            ButtonLink taskBtn = new ButtonLink(taskname, new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {

                }
            });
            taskBtn.setIcon(new ExternalResource(ProjectResources.getIconResourceLink12ByTaskPriority(task.getPriority())));
            root.with(taskBtn);

            // Build footer
            if (task.getNumComments() != null || task.getDeadline() != null || task.getAssignuser() != null) {
                MHorizontalLayout footer = new MHorizontalLayout().withWidth("100%");

                CssLayout extraInfoBtn = new CssLayout();
                extraInfoBtn.setCaptionAsHtml(true);
                extraInfoBtn.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
                    @Override
                    public void layoutClick(LayoutEvents.LayoutClickEvent layoutClickEvent) {
                        NotificationUtil.showNotification("Debug", "Click");
                    }
                });
                footer.with(extraInfoBtn).expand(extraInfoBtn).withAlign(extraInfoBtn, Alignment.TOP_LEFT);
                Div footerDiv = new Div().setCSSClass(UIConstants.FOOTER_NOTE);
                if (task.getNumComments() != null) {
                    Text comment = new Text(FontAwesome.COMMENT_O.getHtml() + " " + task.getNumComments());
                    footerDiv.appendChild(comment);
                }

                if (task.getDeadlineRoundPlusOne() != null) {
                    Text dueDate = new Text(AppContext.formatPrettyTime(task.getDeadlineRoundPlusOne()));
                    footerDiv.appendChild(dueDate);
                }

                extraInfoBtn.addComponent(new Label(footerDiv.write(), ContentMode.HTML));

                if (task.getAssignuser() != null) {
                    Button image = UserAvatarControlFactory.createUserAvatarEmbeddedButton(task.getAssignUserAvatarId(), 16);
                    image.setDescription(task.getAssignUserFullName());
                    footer.with(image).withAlign(image, Alignment.TOP_RIGHT);
                }
                root.with(footer);
            }
        }
    }

    private static class KanbanBlock extends CustomComponent {
        private OptionVal optionVal;
        private MVerticalLayout root;
        private DDVerticalLayout dragLayoutContainer;

        public KanbanBlock(OptionVal stage) {
            this.setHeight("100%");
            this.optionVal = stage;
            root = new MVerticalLayout();
            root.setWidth("300px");
            root.addStyleName("kanban-block");
            this.setCompositionRoot(root);

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
                        ProjectTaskService taskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
                        taskService.updateSelectiveWithSession(task, AppContext.getUsername());

                        //Update task index
                        List<Map<String, Integer>> indexMap = new ArrayList<>();
                        for (int i = 0; i < dragLayoutContainer.getComponentCount(); i++) {
                            KanbanTaskBlockItem blockItem = (KanbanTaskBlockItem) dragLayoutContainer.getComponent(i);
                            Map<String, Integer> map = new HashedMap(2);
                            map.put("id", blockItem.task.getId());
                            map.put("index", i);
                            indexMap.add(map);
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
            new Restrain(dragLayoutContainer).setMinHeight("50px").setMaxHeight((Page.getCurrent()
                    .getBrowserWindowHeight() - 265) + "px");

            HorizontalLayout headerLayout = new HorizontalLayout();
            headerLayout.setWidth("100%");
            Label header = new Label(optionVal.getTypeval());
            header.addStyleName("header");
            headerLayout.addComponent(header);
            headerLayout.setComponentAlignment(header, Alignment.MIDDLE_LEFT);
            headerLayout.setExpandRatio(header, 1.0f);

            PopupButton controlsBtn = new PopupButton();
            controlsBtn.addStyleName(UIConstants.THEME_LINK);
            headerLayout.addComponent(controlsBtn);
            headerLayout.setComponentAlignment(controlsBtn, Alignment.MIDDLE_RIGHT);

            OptionPopupContent popupContent = new OptionPopupContent();
            Button addBtn = new Button("Add a task", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {

                }
            });
            addBtn.setEnabled(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
            popupContent.addOption(addBtn);
            controlsBtn.setContent(popupContent);

            Button addNewBtn = new Button("Add a task", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {

                }
            });
            addNewBtn.setEnabled(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));
            addNewBtn.addStyleName(UIConstants.BUTTON_SMALL_PADDING);
            addNewBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
            root.with(headerLayout, dragLayoutContainer, addNewBtn);
        }

        void addBlockItem(KanbanTaskBlockItem comp) {
            dragLayoutContainer.addComponent(comp);
        }
    }
}
