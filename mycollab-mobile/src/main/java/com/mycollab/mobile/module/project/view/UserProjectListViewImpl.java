package com.mycollab.mobile.module.project.view;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.mobile.module.project.events.ProjectEvent;
import com.mycollab.mobile.module.project.ui.AbstractListPageView;
import com.mycollab.mobile.shell.events.ShellEvent;
import com.mycollab.mobile.ui.AbstractPagedBeanList;
import com.mycollab.mobile.ui.SearchInputField;
import com.mycollab.module.project.domain.SimpleProject;
import com.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.i18n.ProjectI18nEnum;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Component;
import org.vaadin.teemu.VaadinIcons;
import org.vaadin.viritin.button.MButton;

/**
 * @author MyCollab Ltd.
 * @since 4.4.0
 */
@ViewComponent
public class UserProjectListViewImpl extends AbstractListPageView<ProjectSearchCriteria, SimpleProject> implements UserProjectListView {
    private static final long serialVersionUID = 664947871255886622L;

    public UserProjectListViewImpl() {
        this.setCaption(UserUIContext.getMessage(ProjectI18nEnum.LIST));
    }

    @Override
    protected AbstractPagedBeanList<ProjectSearchCriteria, SimpleProject> createBeanList() {
        return new ProjectListDisplay();
    }

    @Override
    protected SearchInputField<ProjectSearchCriteria> createSearchField() {
        return null;
    }

    @Override
    protected void doSearch() {
        if (getPagedBeanTable().getSearchRequest() == null) {
            ProjectSearchCriteria criteria = new ProjectSearchCriteria();
            criteria.setInvolvedMember(StringSearchField.and(UserUIContext.getUsername()));
            criteria.setProjectStatuses(new SetSearchField<>(StatusI18nEnum.Open.name()));
            getPagedBeanTable().setSearchCriteria(criteria);
        }
        getPagedBeanTable().refresh();
    }

    @Override
    protected void buildNavigateMenu() {
        addSection("Views");

        // Buttons with styling (slightly smaller with left-aligned text)
        MButton activityBtn = new MButton(UserUIContext.getMessage(ProjectCommonI18nEnum.M_VIEW_PROJECT_ACTIVITIES), clickEvent -> {
            closeMenu();
            EventBusFactory.getInstance().post(new ProjectEvent.GotoAllActivitiesView(this));
        }).withIcon(FontAwesome.INBOX);
        addMenuItem(activityBtn);

        MButton prjBtn = new MButton(UserUIContext.getMessage(ProjectI18nEnum.LIST), clickEvent -> {
            closeMenu();
            EventBusFactory.getInstance().post(new ProjectEvent.GotoProjectList(this, null));
        }).withIcon(FontAwesome.BUILDING);
        addMenuItem(prjBtn);

        addSection("Modules");
        MButton crmModuleBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.MODULE_CRM), clickEvent -> {
            closeMenu();
            EventBusFactory.getInstance().post(new ShellEvent.GotoCrmModule(this, null));
        }).withIcon(VaadinIcons.MONEY);
        addMenuItem(crmModuleBtn);

        addSection(UserUIContext.getMessage(ProjectCommonI18nEnum.VIEW_SETTINGS));

        MButton logoutBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SIGNOUT), clickEvent -> {
            closeMenu();
            EventBusFactory.getInstance().post(new ShellEvent.LogOut(this));
        }).withIcon(FontAwesome.SIGN_OUT);
        addMenuItem(logoutBtn);
    }

    @Override
    protected Component buildRightComponent() {
        return new MButton("", clickEvent -> EventBusFactory.getInstance().post(new ProjectEvent.GotoAdd(this, null)))
                .withIcon(FontAwesome.PLUS).withStyleName(UIConstants.CIRCLE_BOX);
    }

    @Override
    public void onBecomingVisible() {
        super.onBecomingVisible();
        AppUI.addFragment("project", UserUIContext.getMessage(ProjectI18nEnum.LIST));
    }
}
