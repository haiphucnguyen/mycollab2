package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.domain.OptionVal;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.view.kanban.AddNewColumnWindow;
import com.esofthead.mycollab.module.project.view.kanban.KanbanBlock;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.vaadin.maddon.layouts.MHorizontalLayout;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
@ViewComponent
public class TaskKanbanviewImpl extends AbstractPageView implements TaskKanbanview {

    private ProjectTaskService taskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
    private MHorizontalLayout kanbanLayout;
    private Map<String, KanbanBlock> kanbanBlocks = new ConcurrentHashMap<>();

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

        header.with(headerWrapper, addNewColumnBtn, cancelBtn).withAlign(headerWrapper, Alignment.MIDDLE_LEFT)
                .withAlign(cancelBtn, Alignment.MIDDLE_RIGHT).withAlign(addNewColumnBtn, Alignment.MIDDLE_RIGHT).expand(headerWrapper);

        kanbanLayout = new MHorizontalLayout().withFullHeight().withSpacing(false);
        this.with(header, kanbanLayout).expand(kanbanLayout);
    }

    @Override
    public void display() {
        kanbanLayout.removeAllComponents();

        TaskSearchCriteria searchCriteria = new TaskSearchCriteria();
        searchCriteria.setProjectid(new NumberSearchField(CurrentProjectVariables.getProjectId()));
        final List<SimpleTask> tasks = taskService.findPagableListByCriteria(new SearchRequest<>(searchCriteria));

        UI.getCurrent().access(new Runnable() {
            @Override
            public void run() {
                for (SimpleTask task : tasks) {
                    String status = task.getStatus();
                    KanbanBlock kanbanBlock = kanbanBlocks.get(status);
                    if (kanbanBlock == null) {
                        kanbanBlock = new KanbanBlock(status);
                        kanbanBlocks.put(status, kanbanBlock);
                        kanbanLayout.with(kanbanBlock);
                    }

                    kanbanBlock.with(new Label(task.getTaskname()));
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
                KanbanBlock kanbanBlock = new KanbanBlock(option.getTypeval());
                kanbanBlocks.put(option.getTypeval(), kanbanBlock);
                kanbanLayout.with(kanbanBlock);
                UI.getCurrent().push();
            }
        });
    }
}
