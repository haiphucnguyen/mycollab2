package com.mycollab.pro.module.project.view;

import com.google.common.eventbus.Subscribe;
import com.mycollab.common.ModuleNameConstants;
import com.mycollab.common.domain.FavoriteItem;
import com.mycollab.common.i18n.ErrorI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.service.FavoriteItemService;
import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.*;
import com.mycollab.module.project.domain.criteria.ProjectGenericItemSearchCriteria;
import com.mycollab.module.project.event.*;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.service.MilestoneService;
import com.mycollab.module.project.service.ProjectGenericItemService;
import com.mycollab.module.project.service.ProjectTaskService;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.ui.components.ProjectActivityComponent;
import com.mycollab.module.project.view.IFavoriteView;
import com.mycollab.module.project.view.ProjectView;
import com.mycollab.module.project.view.bug.BugDefaultFormLayoutFactory;
import com.mycollab.module.project.view.bug.BugPreviewForm;
import com.mycollab.module.project.view.milestone.MilestoneDefaultFormLayoutFactory;
import com.mycollab.module.project.view.milestone.MilestonePreviewForm;
import com.mycollab.module.project.view.settings.ComponentDefaultFormLayoutFactory;
import com.mycollab.module.project.view.settings.ComponentPreviewForm;
import com.mycollab.module.project.view.settings.VersionDefaultFormLayoutFactory;
import com.mycollab.module.project.view.settings.VersionPreviewForm;
import com.mycollab.module.project.view.task.TaskDefaultFormLayoutFactory;
import com.mycollab.module.project.view.task.TaskPreviewForm;
import com.mycollab.module.tracker.domain.*;
import com.mycollab.module.tracker.service.BugService;
import com.mycollab.module.tracker.service.ComponentService;
import com.mycollab.module.tracker.service.VersionService;
import com.mycollab.pro.module.project.view.risk.RiskDefaultFormLayoutFactory;
import com.mycollab.pro.module.project.view.risk.RiskPreviewForm;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.spring.AppEventBus;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.ApplicationEventListener;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.AbstractVerticalPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.reporting.FormReportLayout;
import com.mycollab.vaadin.reporting.PrintButton;
import com.mycollab.vaadin.ui.*;
import com.mycollab.vaadin.web.ui.AbstractBeanPagedList;
import com.mycollab.vaadin.web.ui.DefaultBeanPagedList;
import com.mycollab.vaadin.web.ui.SearchTextField;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Collections;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
@ViewComponent
public class FavoriteViewImpl extends AbstractVerticalPageView implements IFavoriteView {
    private ProjectGenericItemSearchCriteria searchCriteria;

    private boolean isSortAsc = true;
    private ELabel headerLbl;
    private FavoriteListComp favoriteListComp;
    private AssignmentReadView assignmentReadView;

    private ApplicationEventListener<ProjectEvent.SelectFavoriteItem> selectFavoriteHandler = new
            ApplicationEventListener<ProjectEvent.SelectFavoriteItem>() {
                @Override
                @Subscribe
                public void handle(ProjectEvent.SelectFavoriteItem event) {
                    ProjectGenericItem assignment = event.getData();
                    viewFavoriteItem(assignment);
                }
            };

    public FavoriteViewImpl() {
        addStyleName("hdr-view");
    }

    @Override
    public void attach() {
        EventBusFactory.getInstance().register(selectFavoriteHandler);
        super.attach();
    }

    @Override
    public void detach() {
        EventBusFactory.getInstance().unregister(selectFavoriteHandler);
        setProjectNavigatorVisibility(true);
        super.detach();
    }

    @Override
    public void display() {
        removeAllComponents();
        setProjectNavigatorVisibility(false);
        MHorizontalLayout header = new MHorizontalLayout().withMargin(new MarginInfo(false, false, true, false))
                .withFullWidth();
        headerLbl = ELabel.h2(String.format("%s %s", FontAwesome.STAR.getHtml(),
                UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_FAVORITES))).withUndefinedWidth();
        header.with(headerLbl);

        isSortAsc = true;
        final MButton sortBtn = new MButton("");
        sortBtn.addClickListener(clickEvent -> {
            isSortAsc = !isSortAsc;
            if (searchCriteria != null) {
                if (isSortAsc) {
                    sortBtn.setIcon(VaadinIcons.CARET_UP);
                } else {
                    sortBtn.setIcon(VaadinIcons.CARET_DOWN);
                }
                displayFavoriteList();
            }
        });
        sortBtn.withIcon(VaadinIcons.CARET_UP).withStyleName(WebThemes.BUTTON_ICON_ONLY).withWidth("-1px");

        final SearchTextField searchTextField = new SearchTextField() {
            @Override
            public void doSearch(String value) {
                searchCriteria.setTxtValue(StringSearchField.and(value));
                displayFavoriteList();
            }

            @Override
            public void emptySearch() {
                searchCriteria.setTxtValue(null);
                displayFavoriteList();
            }
        };
        searchTextField.addStyleName(ValoTheme.TEXTFIELD_SMALL);
        ELabel listLnl = new ELabel();
        MHorizontalLayout favoriteListHeaderPanel = new MHorizontalLayout(listLnl, sortBtn, searchTextField).expand(listLnl)
                .withStyleName(WebThemes.PANEL_HEADER).withFullWidth().alignAll(Alignment.MIDDLE_LEFT);

        favoriteListComp = new FavoriteListComp();

        MVerticalLayout favoriteListPanel = new MVerticalLayout(favoriteListHeaderPanel, favoriteListComp).withMargin(false).withSpacing(false).withWidth("300px");

        assignmentReadView = new AssignmentReadView();
        MHorizontalLayout contentWrapper = new MHorizontalLayout(favoriteListPanel, assignmentReadView).expand(assignmentReadView).withFullWidth();
        searchCriteria = new ProjectGenericItemSearchCriteria();
        searchCriteria.setPrjKeys(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        searchCriteria.setMonitorProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        displayFavoriteList();
        with(header, contentWrapper);
    }

    private void displayFavoriteList() {
        if (isSortAsc) {
            searchCriteria.setOrderFields(Collections.singletonList(new SearchCriteria.OrderField("name",
                    SearchCriteria.ASC)));
        } else {
            searchCriteria.setOrderFields(Collections.singletonList(new SearchCriteria.OrderField("name", SearchCriteria.DESC)));
        }
        int totalCount = favoriteListComp.setSearchCriteria(searchCriteria);
        headerLbl.setValue(String.format("%s %s (%d)", FontAwesome.STAR.getHtml(),
                UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_FAVORITES), totalCount));
        if (totalCount > 0) {
            ProjectGenericItem assignment = favoriteListComp.getItemAt(0);
            if (assignment != null) {
                viewFavoriteItem(assignment);
            }
            Component firstRow = favoriteListComp.getRowAt(0);
            if (firstRow != null) {
                favoriteListComp.setSelectedRow(firstRow);
            }
        }
    }

    private void viewFavoriteItem(ProjectGenericItem assignment) {
        assignmentReadView.showAssignment(assignment);
    }

    private void setProjectNavigatorVisibility(boolean visibility) {
        ProjectView view = UIUtils.getRoot(this, ProjectView.class);
        if (view != null) {
            view.setNavigatorVisibility(visibility);
        }
    }

    private static class FavoriteListComp extends DefaultBeanPagedList<ProjectGenericItemService, ProjectGenericItemSearchCriteria,
            ProjectGenericItem> {
        FavoriteListComp() {
            super(AppContextUtil.getSpringBean(ProjectGenericItemService.class), new AssignmentRowHandler(), 10);
            addStyleName(WebThemes.BORDER_LIST);
            setControlStyle("borderlessControl");
        }
    }

    private static class AssignmentRowHandler implements IBeanList.RowDisplayHandler<ProjectGenericItem> {
        @Override
        public Component generateRow(final IBeanList<ProjectGenericItem> host, final ProjectGenericItem item, int rowIndex) {
            final MHorizontalLayout layout = new MHorizontalLayout().withStyleName(WebThemes.BORDER_LIST_ROW,
                    WebThemes.CURSOR_POINTER).withFullWidth();
            MButton favoriteBtn = new MButton(FontAwesome.STAR, clickEvent -> {
                FavoriteItem favoriteItem = new FavoriteItem();
                favoriteItem.setExtratypeid(item.getProjectId());
                favoriteItem.setType(item.getType());
                favoriteItem.setTypeid(item.getTypeId() + "");
                favoriteItem.setSaccountid(AppUI.getAccountId());
                favoriteItem.setCreateduser(UserUIContext.getUsername());
                FavoriteItemService favoriteItemService = AppContextUtil.getSpringBean(FavoriteItemService.class);
                favoriteItemService.saveOrDelete(favoriteItem);
                ((BeanList) host).removeRow(layout);
            }).withStyleName("favorite-btn-selected", WebThemes.BUTTON_ICON_ONLY);

            ELabel headerLbl = ELabel.html(String.format("%s %s", ProjectAssetsManager.getAsset(item.getType()).getHtml(), item.getName()))
                    .withFullWidth().withStyleName(UIConstants.TEXT_ELLIPSIS);
            layout.with(favoriteBtn, headerLbl).expand(headerLbl);
            layout.addLayoutClickListener(layoutClickEvent -> {
                EventBusFactory.getInstance().post(new ProjectEvent.SelectFavoriteItem(this, item));
                ((AbstractBeanPagedList) host).setSelectedRow(layout);
            });
            return layout;
        }
    }

    private static class AssignmentReadView extends VerticalLayout {
        void showAssignment(ProjectGenericItem assignment) {
            this.setMargin(new MarginInfo(false, false, false, true));
            removeAllComponents();
            ProjectActivityComponent activityComponent;
            if (ProjectTypeConstants.BUG.equals(assignment.getType())) {
                if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.BUGS)) {
                    BugService bugService = AppContextUtil.getSpringBean(BugService.class);
                    SimpleBug bug = bugService.findById(Integer.parseInt(assignment.getTypeId()), AppUI.getAccountId());
                    if (bug != null) {
                        ELabel headerLbl = ELabel.h2(String.format("%s %s", ProjectAssetsManager.getAsset(assignment.getType()).getHtml(), bug.getName()));

                        final PrintButton printBtn = new PrintButton();
                        printBtn.addClickListener(clickEvent ->
                                printBtn.doPrint(bug, new FormReportLayout(ProjectTypeConstants.BUG, BugWithBLOBs.Field.name.name(),
                                        BugDefaultFormLayoutFactory.getForm(), SimpleBug.Field.components.name(), SimpleBug.Field
                                        .affectedVersions.name(), SimpleBug.Field.fixedVersions.name(), BugWithBLOBs.Field.id.name(),
                                        SimpleBug.Field.selected.name()))
                        );
                        printBtn.setStyleName(WebThemes.BUTTON_OPTION);
                        printBtn.setDescription(UserUIContext.getMessage(GenericI18Enum.ACTION_PRINT));
                        printBtn.setVisible(CurrentProjectVariables.canRead(ProjectRolePermissionCollections.BUGS));

                        MButton editBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT),
                                clickEvent -> EventBusFactory.getInstance().post(new BugEvent.GotoEdit(this, bug)))
                                .withIcon(FontAwesome.EDIT).withStyleName(WebThemes.BUTTON_ACTION)
                                .withVisible(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS));

                        MHorizontalLayout headerLayout = new MHorizontalLayout(headerLbl, printBtn, editBtn).withAlign(printBtn, Alignment.TOP_RIGHT)
                                .withAlign(editBtn, Alignment.TOP_RIGHT).expand(headerLbl).withFullWidth();
                        addComponent(headerLayout);
                        BugPreviewForm form = new BugPreviewForm();
                        form.setBean(bug);
                        addComponent(form);
                        activityComponent = new ProjectActivityComponent(ProjectTypeConstants.BUG, assignment.getProjectId());
                        activityComponent.loadActivities("" + bug.getId());
                        addComponent(activityComponent);
                    }
                } else {
                    addComponent(ELabel.h3(UserUIContext.getMessage(ErrorI18nEnum.NO_ACCESS_PERMISSION)));
                }
            } else if (ProjectTypeConstants.TASK.equals(assignment.getType())) {
                if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.TASKS)) {
                    ProjectTaskService taskService = AppContextUtil.getSpringBean(ProjectTaskService.class);
                    SimpleTask task = taskService.findById(Integer.parseInt(assignment.getTypeId()), AppUI.getAccountId());
                    if (task != null) {
                        ELabel headerLbl = ELabel.h2(String.format("%s %s", ProjectAssetsManager.getAsset(assignment.getType()).getHtml(), task.getName()));

                        final PrintButton printBtn = new PrintButton();
                        printBtn.addClickListener(clickEvent ->
                                printBtn.doPrint(task, new FormReportLayout(ProjectTypeConstants.TASK, Task.Field.name.name(),
                                        TaskDefaultFormLayoutFactory.getForm(), Task.Field.name.name(), Task.Field.id.name(),
                                        Task.Field.parenttaskid.name()))
                        );
                        printBtn.setStyleName(WebThemes.BUTTON_OPTION);
                        printBtn.setDescription(UserUIContext.getMessage(GenericI18Enum.ACTION_PRINT));
                        printBtn.setVisible(CurrentProjectVariables.canRead(ProjectRolePermissionCollections.TASKS));

                        MButton editBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT),
                                clickEvent -> EventBusFactory.getInstance().post(new TaskEvent.GotoEdit(this, task)))
                                .withIcon(FontAwesome.EDIT).withStyleName(WebThemes.BUTTON_ACTION)
                                .withVisible(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));

                        MHorizontalLayout headerLayout = new MHorizontalLayout(headerLbl, printBtn, editBtn)
                                .withAlign(printBtn, Alignment.TOP_RIGHT).withAlign(editBtn, Alignment.TOP_RIGHT).expand(headerLbl).withFullWidth();
                        addComponent(headerLayout);
                        TaskPreviewForm form = new TaskPreviewForm();
                        form.setBean(task);
                        addComponent(form);
                        activityComponent = new ProjectActivityComponent(ProjectTypeConstants.TASK, assignment.getProjectId());
                        activityComponent.loadActivities("" + task.getId());
                        addComponent(activityComponent);
                    }
                } else {
                    addComponent(ELabel.h3(UserUIContext.getMessage(ErrorI18nEnum.NO_ACCESS_PERMISSION)));
                }
            } else if (ProjectTypeConstants.MILESTONE.equals(assignment.getType())) {
                if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.MILESTONES)) {
                    MilestoneService milestoneService = AppContextUtil.getSpringBean(MilestoneService.class);
                    SimpleMilestone milestone = milestoneService.findById(Integer.parseInt(assignment.getTypeId()),
                            AppUI.getAccountId());
                    if (milestone != null) {
                        ELabel headerLbl = ELabel.h2(String.format("%s %s", ProjectAssetsManager.getAsset(assignment.getType()).getHtml(), milestone.getName()));

                        final PrintButton printBtn = new PrintButton();
                        printBtn.withListener(clickEvent ->
                                printBtn.doPrint(milestone, new FormReportLayout(ProjectTypeConstants.MILESTONE, Milestone.Field.name.name(),
                                        MilestoneDefaultFormLayoutFactory.getForm(), Milestone.Field.id.name()))
                        ).withStyleName(WebThemes.BUTTON_OPTION).withDescription(UserUIContext.getMessage(GenericI18Enum.ACTION_PRINT))
                                .withVisible(CurrentProjectVariables.canRead(ProjectRolePermissionCollections.MILESTONES));

                        MButton editBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT),
                                clickEvent -> EventBusFactory.getInstance().post(new MilestoneEvent.GotoEdit(this, milestone)))
                                .withIcon(FontAwesome.EDIT).withStyleName(WebThemes.BUTTON_ACTION)
                                .withVisible(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.MILESTONES));

                        MHorizontalLayout headerLayout = new MHorizontalLayout(headerLbl, printBtn, editBtn)
                                .withAlign(printBtn, Alignment.TOP_RIGHT).withAlign(editBtn, Alignment.TOP_RIGHT).expand(headerLbl).withFullWidth();
                        addComponent(headerLayout);
                        MilestonePreviewForm form = new MilestonePreviewForm();
                        form.setBean(milestone);
                        addComponent(form);
                        activityComponent = new ProjectActivityComponent(ProjectTypeConstants.MILESTONE, assignment.getProjectId());
                        activityComponent.loadActivities("" + milestone.getId());
                        addComponent(activityComponent);
                    }
                } else {
                    addComponent(ELabel.h3(UserUIContext.getMessage(ErrorI18nEnum.NO_ACCESS_PERMISSION)));
                }
            } else if (ProjectTypeConstants.RISK.equals(assignment.getType())) {
                if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.RISKS)) {
                    RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
                    final SimpleRisk risk = riskService.findById(Integer.parseInt(assignment.getTypeId()), AppUI.getAccountId());
                    if (risk != null) {
                        ELabel headerLbl = ELabel.h2(ProjectAssetsManager.getAsset(assignment.getType()).getHtml() + " " + risk.getName());

                        final PrintButton printBtn = new PrintButton();
                        printBtn.addClickListener(clickEvent ->
                                printBtn.doPrint(risk, new FormReportLayout(ProjectTypeConstants.RISK, Risk.Field.name.name(),
                                        RiskDefaultFormLayoutFactory.getForm()))
                        );
                        printBtn.setStyleName(WebThemes.BUTTON_OPTION);
                        printBtn.setDescription(UserUIContext.getMessage(GenericI18Enum.ACTION_PRINT));
                        printBtn.setVisible(CurrentProjectVariables.canRead(ProjectRolePermissionCollections.RISKS));

                        MButton editBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT),
                                clickEvent -> EventBusFactory.getInstance().post(new RiskEvent.GotoEdit(this, risk)))
                                .withIcon(FontAwesome.EDIT).withStyleName(WebThemes.BUTTON_ACTION)
                                .withVisible(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS));

                        MHorizontalLayout headerLayout = new MHorizontalLayout(headerLbl, printBtn, editBtn)
                                .withAlign(printBtn, Alignment.TOP_RIGHT).withAlign(editBtn, Alignment.TOP_RIGHT).expand(headerLbl).withFullWidth();
                        addComponent(headerLayout);
                        RiskPreviewForm form = new RiskPreviewForm();
                        form.setBean(risk);
                        addComponent(form);
                        activityComponent = new ProjectActivityComponent(ProjectTypeConstants.RISK, assignment.getProjectId());
                        activityComponent.loadActivities("" + risk.getId());
                        addComponent(activityComponent);
                    }
                } else {
                    addComponent(ELabel.h3(UserUIContext.getMessage(ErrorI18nEnum.NO_ACCESS_PERMISSION)));
                }
            } else if (ProjectTypeConstants.BUG_COMPONENT.equals(assignment.getType())) {
                if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.COMPONENTS)) {
                    ComponentService componentService = AppContextUtil.getSpringBean(ComponentService.class);
                    SimpleComponent component = componentService.findById(Integer.parseInt(assignment.getTypeId()),
                            AppUI.getAccountId());
                    if (component != null) {
                        ELabel headerLbl = ELabel.h2(String.format("%s %s", ProjectAssetsManager.getAsset(assignment.getType()).getHtml(), component.getName()));

                        final PrintButton printBtn = new PrintButton();
                        printBtn.addClickListener(clickEvent ->
                                printBtn.doPrint(component, new FormReportLayout(ProjectTypeConstants.BUG_COMPONENT,
                                        com.mycollab.module.tracker.domain.Component.Field.name.name(),
                                        ComponentDefaultFormLayoutFactory.getForm(), com.mycollab.module.tracker.domain.Component.Field.id.name()))
                        );
                        printBtn.setStyleName(WebThemes.BUTTON_OPTION);
                        printBtn.setDescription(UserUIContext.getMessage(GenericI18Enum.ACTION_PRINT));
                        printBtn.setVisible(CurrentProjectVariables.canRead(ProjectRolePermissionCollections.COMPONENTS));

                        MButton editBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT),
                                clickEvent -> EventBusFactory.getInstance().post(new BugComponentEvent.GotoEdit(this, component)))
                                .withIcon(FontAwesome.EDIT).withStyleName(WebThemes.BUTTON_ACTION)
                                .withVisible(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.COMPONENTS));

                        MHorizontalLayout headerLayout = new MHorizontalLayout(headerLbl, printBtn, editBtn)
                                .withAlign(printBtn, Alignment.TOP_RIGHT)
                                .withAlign(editBtn, Alignment.TOP_RIGHT).expand(headerLbl).withFullWidth();
                        addComponent(headerLayout);
                        ComponentPreviewForm form = new ComponentPreviewForm();
                        form.setBean(component);
                        addComponent(form);
                        activityComponent = new ProjectActivityComponent(ProjectTypeConstants.BUG_COMPONENT, assignment.getProjectId());
                        activityComponent.loadActivities("" + component.getId());
                        addComponent(activityComponent);
                    }
                } else {
                    addComponent(ELabel.h3(UserUIContext.getMessage(ErrorI18nEnum.NO_ACCESS_PERMISSION)));
                }
            } else if (ProjectTypeConstants.BUG_VERSION.equals(assignment.getType())) {
                if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.VERSIONS)) {
                    VersionService versionService = AppContextUtil.getSpringBean(VersionService.class);
                    final SimpleVersion version = versionService.findById(Integer.parseInt(assignment.getTypeId()),
                            AppUI.getAccountId());
                    if (version != null) {
                        ELabel headerLbl = ELabel.h2(String.format("%s %s", ProjectAssetsManager.getAsset(assignment.getType()).getHtml(), version.getName()));
                        final PrintButton printBtn = new PrintButton();
                        printBtn.addClickListener(clickEvent ->
                                printBtn.doPrint(version, new FormReportLayout(ProjectTypeConstants.BUG_VERSION, Version.Field.name.name(),
                                        VersionDefaultFormLayoutFactory.getForm(), Version.Field.id.name()))
                        );
                        printBtn.setStyleName(WebThemes.BUTTON_OPTION);
                        printBtn.setDescription(UserUIContext.getMessage(GenericI18Enum.ACTION_PRINT));
                        printBtn.setVisible(CurrentProjectVariables.canRead(ProjectRolePermissionCollections.VERSIONS));

                        MButton editBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_EDIT),
                                clickEvent -> EventBusFactory.getInstance().post(new BugVersionEvent.GotoEdit(this, version)))
                                .withIcon(FontAwesome.EDIT).withStyleName(WebThemes.BUTTON_ACTION)
                                .withVisible(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.VERSIONS));

                        MHorizontalLayout headerLayout = new MHorizontalLayout(headerLbl, printBtn, editBtn).withAlign
                                (editBtn, Alignment.TOP_RIGHT).withAlign(printBtn, Alignment.TOP_RIGHT).expand(headerLbl).withFullWidth();
                        addComponent(headerLayout);
                        VersionPreviewForm form = new VersionPreviewForm();
                        form.setBean(version);
                        addComponent(form);
                        activityComponent = new ProjectActivityComponent(ProjectTypeConstants.BUG_VERSION, assignment.getProjectId());
                        activityComponent.loadActivities("" + version.getId());
                        addComponent(activityComponent);
                    }
                } else {
                    addComponent(ELabel.h3(UserUIContext.getMessage(ErrorI18nEnum.NO_ACCESS_PERMISSION)));
                }

                AppEventBus.getInstance().post(new UpdateNotificationItemReadStatusEvent(UserUIContext.getUsername(),
                        ModuleNameConstants.PRJ, assignment.getType(), assignment.getTypeId()));
            }
        }
    }
}
