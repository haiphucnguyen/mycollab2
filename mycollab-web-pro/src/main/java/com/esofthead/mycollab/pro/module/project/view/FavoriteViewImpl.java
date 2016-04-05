package com.esofthead.mycollab.pro.module.project.view;

import com.esofthead.mycollab.common.domain.FavoriteItem;
import com.esofthead.mycollab.common.i18n.ErrorI18nEnum;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.service.FavoriteItemService;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.ProjectGenericItem;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericItemSearchCriteria;
import com.esofthead.mycollab.module.project.events.*;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.module.project.service.ProjectGenericItemService;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.ui.components.ProjectActivityComponent;
import com.esofthead.mycollab.module.project.view.IFavoriteView;
import com.esofthead.mycollab.module.project.view.ProjectView;
import com.esofthead.mycollab.module.project.view.bug.BugPreviewForm;
import com.esofthead.mycollab.module.project.view.milestone.MilestonePreviewForm;
import com.esofthead.mycollab.module.project.view.settings.ComponentPreviewForm;
import com.esofthead.mycollab.module.project.view.settings.VersionPreviewForm;
import com.esofthead.mycollab.module.project.view.task.TaskPreviewForm;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.SimpleVersion;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import com.esofthead.mycollab.module.tracker.service.VersionService;
import com.esofthead.mycollab.pro.module.project.view.risk.RiskPreviewForm;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.UIUtils;
import com.esofthead.mycollab.vaadin.web.ui.AbstractBeanPagedList;
import com.esofthead.mycollab.vaadin.web.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.web.ui.SearchTextField;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.google.common.eventbus.Subscribe;
import com.vaadin.event.LayoutEvents;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Collections;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
@ViewComponent
public class FavoriteViewImpl extends AbstractPageView implements IFavoriteView {
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
                    ProjectGenericItem assignment = (ProjectGenericItem) event.getData();
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
                .withWidth("100%");
        headerLbl = new ELabel(FontAwesome.STAR.getHtml() + " Favorites", ContentMode.HTML).withWidthUndefined()
                .withStyleName(ValoTheme.LABEL_H2, ValoTheme.LABEL_NO_MARGIN);
        header.with(headerLbl);

        isSortAsc = true;
        final Button sortBtn = new Button("");
        sortBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                isSortAsc = !isSortAsc;
                if (searchCriteria != null) {
                    if (isSortAsc) {
                        sortBtn.setIcon(FontAwesome.SORT_ALPHA_ASC);
                    } else {
                        sortBtn.setIcon(FontAwesome.SORT_ALPHA_DESC);
                    }
                    displayFavoriteList();
                }
            }
        });
        sortBtn.setIcon(FontAwesome.SORT_ALPHA_ASC);
        sortBtn.addStyleName(UIConstants.BUTTON_ICON_ONLY);
        sortBtn.setWidthUndefined();

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
                .withStyleName("panel-header").withFullWidth().alignAll(Alignment.MIDDLE_LEFT);

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
            searchCriteria.setOrderFields(Collections.singletonList(new SearchCriteria.OrderField("summary",
                    SearchCriteria.ASC)));
        } else {
            searchCriteria.setOrderFields(Collections.singletonList(new SearchCriteria.OrderField("summary", SearchCriteria.DESC)));
        }
        int totalCount = favoriteListComp.setSearchCriteria(searchCriteria);
        headerLbl.setValue(FontAwesome.STAR.getHtml() + " Favorites (" + totalCount + ")");
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
            super(ApplicationContextUtil.getSpringBean(ProjectGenericItemService.class), new AssignmentRowHandler(), 10);
            addStyleName(UIConstants.BORDER_LIST);
            setControlStyle("borderlessControl");
        }
    }

    private static class AssignmentRowHandler implements AbstractBeanPagedList.RowDisplayHandler<ProjectGenericItem> {
        @Override
        public Component generateRow(final AbstractBeanPagedList host, final ProjectGenericItem item, int rowIndex) {
            final MHorizontalLayout layout = new MHorizontalLayout().withStyleName(UIConstants.BORDER_LIST_ROW)
                    .withStyleName(UIConstants.CURSOR_POINTER).withFullWidth();
            Button favoriteBtn = new Button(FontAwesome.STAR);
            favoriteBtn.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    FavoriteItem favoriteItem = new FavoriteItem();
                    favoriteItem.setExtratypeid(item.getProjectId());
                    favoriteItem.setType(item.getType());
                    favoriteItem.setTypeid(item.getTypeId() + "");
                    favoriteItem.setSaccountid(AppContext.getAccountId());
                    favoriteItem.setCreateduser(AppContext.getUsername());
                    FavoriteItemService favoriteItemService = ApplicationContextUtil.getSpringBean(FavoriteItemService.class);
                    favoriteItemService.saveOrDelete(favoriteItem);
                    host.removeRow(layout);
                }
            });
            favoriteBtn.addStyleName("favorite-btn-selected");
            favoriteBtn.addStyleName(UIConstants.BUTTON_ICON_ONLY);

            ELabel headerLbl = new ELabel(ProjectAssetsManager.getAsset(item.getType()).getHtml() + " " + item
                    .getSummary(), ContentMode.HTML).withWidth("100%").withStyleName(UIConstants.TEXT_ELLIPSIS);
            layout.with(favoriteBtn, headerLbl).expand(headerLbl);
            layout.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
                @Override
                public void layoutClick(LayoutEvents.LayoutClickEvent event) {
                    EventBusFactory.getInstance().post(new ProjectEvent.SelectFavoriteItem(this, item));
                    host.setSelectedRow(layout);
                }
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
                    BugService bugService = ApplicationContextUtil.getSpringBean(BugService.class);
                    final SimpleBug bug = bugService.findById(assignment.getExtraTypeId(), AppContext.getAccountId());
                    if (bug != null) {
                        ELabel headerLbl = ELabel.h2(ProjectAssetsManager.getAsset(assignment.getType()).getHtml() + " " + bug.getSummary());
                        Button editBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_EDIT), new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent clickEvent) {
                                EventBusFactory.getInstance().post(new BugEvent.GotoEdit(this, bug));
                            }
                        });
                        editBtn.setIcon(FontAwesome.EDIT);
                        editBtn.addStyleName(UIConstants.BUTTON_ACTION);
                        editBtn.setEnabled(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS));

                        MHorizontalLayout headerLayout = new MHorizontalLayout(headerLbl, editBtn).withAlign(editBtn,
                                Alignment.TOP_RIGHT).expand(headerLbl).withFullWidth();
                        addComponent(headerLayout);
                        BugPreviewForm form = new BugPreviewForm();
                        form.setBean(bug);
                        addComponent(form);
                        activityComponent = new ProjectActivityComponent(ProjectTypeConstants.BUG, assignment.getProjectId());
                        activityComponent.loadActivities("" + bug.getId());
                        addComponent(activityComponent);
                    }
                } else {
                    addComponent(ELabel.h3(AppContext.getMessage(ErrorI18nEnum.NO_ACCESS_PERMISSION)));
                }
            } else if (ProjectTypeConstants.TASK.equals(assignment.getType())) {
                if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.TASKS)) {
                    ProjectTaskService taskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
                    final SimpleTask task = taskService.findById(assignment.getExtraTypeId(), AppContext.getAccountId());
                    if (task != null) {
                        ELabel headerLbl = new ELabel(ProjectAssetsManager.getAsset(assignment.getType()).getHtml() + " "
                                + task.getTaskname(), ContentMode.HTML).withStyleName(ValoTheme.LABEL_H2, ValoTheme
                                .LABEL_NO_MARGIN);
                        Button editBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_EDIT), new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent clickEvent) {
                                EventBusFactory.getInstance().post(new TaskEvent.GotoEdit(this, task));
                            }
                        });
                        editBtn.setIcon(FontAwesome.EDIT);
                        editBtn.addStyleName(UIConstants.BUTTON_ACTION);
                        editBtn.setEnabled(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS));

                        MHorizontalLayout headerLayout = new MHorizontalLayout(headerLbl, editBtn).withAlign(editBtn,
                                Alignment.TOP_RIGHT).expand(headerLbl).withFullWidth();
                        addComponent(headerLayout);
                        TaskPreviewForm form = new TaskPreviewForm();
                        form.setBean(task);
                        addComponent(form);
                        activityComponent = new ProjectActivityComponent(ProjectTypeConstants.TASK, assignment.getProjectId());
                        activityComponent.loadActivities("" + task.getId());
                        addComponent(activityComponent);
                    }
                } else {
                    addComponent(ELabel.h3(AppContext.getMessage(ErrorI18nEnum.NO_ACCESS_PERMISSION)));
                }
            } else if (ProjectTypeConstants.MILESTONE.equals(assignment.getType())) {
                if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.MILESTONES)) {
                    MilestoneService milestoneService = ApplicationContextUtil.getSpringBean(MilestoneService.class);
                    final SimpleMilestone milestone = milestoneService.findById(Integer.parseInt(assignment.getTypeId()), AppContext
                            .getAccountId());
                    if (milestone != null) {
                        ELabel headerLbl = ELabel.h2(ProjectAssetsManager.getAsset(assignment.getType()).getHtml() + " "
                                + milestone.getName());

                        Button editBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_EDIT), new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent clickEvent) {
                                EventBusFactory.getInstance().post(new MilestoneEvent.GotoEdit(this, milestone));
                            }
                        });
                        editBtn.setIcon(FontAwesome.EDIT);
                        editBtn.addStyleName(UIConstants.BUTTON_ACTION);
                        editBtn.setEnabled(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.MILESTONES));

                        MHorizontalLayout headerLayout = new MHorizontalLayout(headerLbl, editBtn).withAlign(editBtn,
                                Alignment.TOP_RIGHT).expand(headerLbl).withFullWidth();
                        addComponent(headerLayout);
                        MilestonePreviewForm form = new MilestonePreviewForm();
                        form.setBean(milestone);
                        addComponent(form);
                        activityComponent = new ProjectActivityComponent(ProjectTypeConstants.MILESTONE, assignment.getProjectId());
                        activityComponent.loadActivities("" + milestone.getId());
                        addComponent(activityComponent);
                    }
                } else {
                    addComponent(ELabel.h3(AppContext.getMessage(ErrorI18nEnum.NO_ACCESS_PERMISSION)));
                }
            } else if (ProjectTypeConstants.RISK.equals(assignment.getType())) {
                if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.RISKS)) {
                    RiskService riskService = ApplicationContextUtil.getSpringBean(RiskService.class);
                    final SimpleRisk risk = riskService.findById(Integer.parseInt(assignment.getTypeId()), AppContext.getAccountId());
                    if (risk != null) {
                        ELabel headerLbl = new ELabel(ProjectAssetsManager.getAsset(assignment.getType()).getHtml() + " "
                                + risk.getRiskname(), ContentMode.HTML).withStyleName(ValoTheme.LABEL_H2, ValoTheme.LABEL_NO_MARGIN);

                        Button editBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_EDIT), new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent clickEvent) {
                                EventBusFactory.getInstance().post(new RiskEvent.GotoEdit(this, risk));
                            }
                        });
                        editBtn.setIcon(FontAwesome.EDIT);
                        editBtn.addStyleName(UIConstants.BUTTON_ACTION);
                        editBtn.setEnabled(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS));

                        MHorizontalLayout headerLayout = new MHorizontalLayout(headerLbl, editBtn).withAlign(editBtn,
                                Alignment.TOP_RIGHT).expand(headerLbl).withFullWidth();
                        addComponent(headerLayout);
                        RiskPreviewForm form = new RiskPreviewForm();
                        form.setBean(risk);
                        addComponent(form);
                        activityComponent = new ProjectActivityComponent(ProjectTypeConstants.RISK, assignment.getProjectId());
                        activityComponent.loadActivities("" + risk.getId());
                        addComponent(activityComponent);
                    }
                } else {
                    addComponent(ELabel.h3(AppContext.getMessage(ErrorI18nEnum.NO_ACCESS_PERMISSION)));
                }
            } else if (ProjectTypeConstants.BUG_COMPONENT.equals(assignment.getType())) {
                if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.COMPONENTS)) {
                    ComponentService componentService = ApplicationContextUtil.getSpringBean(ComponentService.class);
                    final SimpleComponent component = componentService.findById(Integer.parseInt(assignment.getTypeId()),
                            AppContext.getAccountId());
                    if (component != null) {
                        ELabel headerLbl = ELabel.h2(ProjectAssetsManager.getAsset(assignment.getType()).getHtml() + " " + component.getComponentname());

                        Button editBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_EDIT), new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent clickEvent) {
                                EventBusFactory.getInstance().post(new BugComponentEvent.GotoEdit(this, component));
                            }
                        });
                        editBtn.setIcon(FontAwesome.EDIT);
                        editBtn.addStyleName(UIConstants.BUTTON_ACTION);
                        editBtn.setEnabled(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.COMPONENTS));

                        MHorizontalLayout headerLayout = new MHorizontalLayout(headerLbl, editBtn).withAlign(editBtn,
                                Alignment.TOP_RIGHT).expand(headerLbl).withFullWidth();
                        addComponent(headerLayout);
                        ComponentPreviewForm form = new ComponentPreviewForm();
                        form.setBean(component);
                        addComponent(form);
                        activityComponent = new ProjectActivityComponent(ProjectTypeConstants.BUG_COMPONENT, assignment.getProjectId());
                        activityComponent.loadActivities("" + component.getId());
                        addComponent(activityComponent);
                    }
                } else {
                    addComponent(ELabel.h3(AppContext.getMessage(ErrorI18nEnum.NO_ACCESS_PERMISSION)));
                }
            } else if (ProjectTypeConstants.BUG_VERSION.equals(assignment.getType())) {
                if (CurrentProjectVariables.canRead(ProjectRolePermissionCollections.VERSIONS)) {
                    VersionService versionService = ApplicationContextUtil.getSpringBean(VersionService.class);
                    final SimpleVersion version = versionService.findById(Integer.parseInt(assignment.getTypeId()), AppContext
                            .getAccountId());
                    if (version != null) {
                        ELabel headerLbl = new ELabel(ProjectAssetsManager.getAsset(assignment.getType()).getHtml() + " "
                                + version.getVersionname(), ContentMode.HTML).withStyleName(ValoTheme.LABEL_H2, ValoTheme
                                .LABEL_NO_MARGIN);

                        Button editBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_EDIT), new Button.ClickListener() {
                            @Override
                            public void buttonClick(Button.ClickEvent clickEvent) {
                                EventBusFactory.getInstance().post(new BugVersionEvent.GotoEdit(this, version));
                            }
                        });
                        editBtn.setIcon(FontAwesome.EDIT);
                        editBtn.addStyleName(UIConstants.BUTTON_ACTION);
                        editBtn.setEnabled(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.VERSIONS));

                        MHorizontalLayout headerLayout = new MHorizontalLayout(headerLbl, editBtn).withAlign(editBtn,
                                Alignment.TOP_RIGHT).expand(headerLbl).withFullWidth();
                        addComponent(headerLayout);
                        VersionPreviewForm form = new VersionPreviewForm();
                        form.setBean(version);
                        addComponent(form);
                        activityComponent = new ProjectActivityComponent(ProjectTypeConstants.BUG_VERSION, assignment.getProjectId());
                        activityComponent.loadActivities("" + version.getId());
                        addComponent(activityComponent);
                    }
                } else {
                    addComponent(ELabel.h3(AppContext.getMessage(ErrorI18nEnum.NO_ACCESS_PERMISSION)));
                }
            }
        }
    }
}
