package com.esofthead.mycollab.pro.module.project.view.client;

import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.ui.components.CrmFollowersComp;
import com.esofthead.mycollab.module.crm.ui.components.CrmPreviewFormControlsGenerator;
import com.esofthead.mycollab.module.crm.view.account.AccountDefaultDynaFormLayoutFactory;
import com.esofthead.mycollab.module.crm.view.account.AccountReadFormFieldFactory;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.ui.components.AbstractPreviewItemComp;
import com.esofthead.mycollab.module.project.ui.components.DateInfoComp;
import com.esofthead.mycollab.module.project.view.ProjectAddWindow;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.web.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.web.ui.DynaFormLayout;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.UserLink;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
@ViewComponent
public class ClientReadViewImpl extends AbstractPreviewItemComp<SimpleAccount> implements ClientReadView {
    private static Logger LOG = LoggerFactory.getLogger(ClientReadViewImpl.class);

    private DateInfoComp dateInfoComp;
    private PeopleInfoComp peopleInfoComp;
    private CrmFollowersComp<SimpleAccount> followerSheet;
    private ProjectListComp projectListComp;

    public ClientReadViewImpl() {
        super("Client", FontAwesome.INSTITUTION);
    }

    @Override
    protected void initRelatedComponents() {
        dateInfoComp = new DateInfoComp();
        peopleInfoComp = new PeopleInfoComp();
        followerSheet = new CrmFollowersComp<>(CrmTypeConstants.ACCOUNT, RolePermissionCollections.CRM_ACCOUNT);
        addToSideBar(dateInfoComp, peopleInfoComp, followerSheet);
        projectListComp = new ProjectListComp();
    }

    @Override
    protected String getType() {
        return CrmTypeConstants.ACCOUNT;
    }

    @Override
    protected void onPreviewItem() {
        dateInfoComp.displayEntryDateTime(beanItem);
        peopleInfoComp.displayEntryPeople(beanItem);
        followerSheet.displayFollowers(beanItem);
        projectListComp.displayProjects(beanItem.getId());
    }

    @Override
    protected String initFormTitle() {
        return beanItem.getAccountname();
    }

    @Override
    protected AdvancedPreviewBeanForm<SimpleAccount> initPreviewForm() {
        return new AdvancedPreviewBeanForm<>();
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DynaFormLayout(CrmTypeConstants.ACCOUNT, AccountDefaultDynaFormLayoutFactory.getForm());
    }

    @Override
    protected AbstractBeanFieldGroupViewFieldFactory<SimpleAccount> initBeanFormFieldFactory() {
        return new AccountReadFormFieldFactory(previewForm);
    }

    @Override
    protected ComponentContainer createButtonControls() {
        return new CrmPreviewFormControlsGenerator<>(previewForm).createButtonControls(RolePermissionCollections.CRM_ACCOUNT);
    }

    @Override
    protected ComponentContainer createBottomPanel() {
        return projectListComp;
    }

    @Override
    public HasPreviewFormHandlers<SimpleAccount> getPreviewFormHandlers() {
        return previewForm;
    }

    @Override
    public SimpleAccount getItem() {
        return beanItem;
    }

    private static class PeopleInfoComp extends MVerticalLayout {
        private static final long serialVersionUID = 1L;

        void displayEntryPeople(SimpleAccount bean) {
            this.removeAllComponents();
            this.withMargin(new MarginInfo(false, false, false, true));

            Label peopleInfoHeader = new Label(FontAwesome.USER.getHtml() + " " +
                    AppContext.getMessage(ProjectCommonI18nEnum.SUB_INFO_PEOPLE), ContentMode.HTML);
            peopleInfoHeader.setStyleName("info-hdr");
            this.addComponent(peopleInfoHeader);

            GridLayout layout = new GridLayout(2, 2);
            layout.setSpacing(true);
            layout.setWidth("100%");
            layout.setMargin(new MarginInfo(false, false, false, true));
            try {
                Label createdLbl = new Label(AppContext.getMessage(ProjectCommonI18nEnum.ITEM_CREATED_PEOPLE));
                createdLbl.setSizeUndefined();
                layout.addComponent(createdLbl, 0, 0);

                String createdUserName = bean.getCreateduser();
                String createdUserAvatarId = bean.getCreatedUserAvatarId();
                String createdUserDisplayName = bean.getCreatedUserFullName();

                UserLink createdUserLink = new UserLink(createdUserName, createdUserAvatarId, createdUserDisplayName);
                layout.addComponent(createdUserLink, 1, 0);
                layout.setColumnExpandRatio(1, 1.0f);

                Label assigneeLbl = new Label(AppContext.getMessage(ProjectCommonI18nEnum.ITEM_ASSIGN_PEOPLE));
                assigneeLbl.setSizeUndefined();
                layout.addComponent(assigneeLbl, 0, 1);
                String assignUserName = bean.getAssignuser();
                String assignUserAvatarId = bean.getAssignUserAvatarId();
                String assignUserDisplayName = bean.getAssignUserFullName();

                UserLink assignUserLink = new UserLink(assignUserName, assignUserAvatarId, assignUserDisplayName);
                layout.addComponent(assignUserLink, 1, 1);
            } catch (Exception e) {
                LOG.error("Can not build user link {} ", BeanUtility.printBeanObj(bean), e);
            }

            this.addComponent(layout);
        }
    }

    private static class ProjectListComp extends VerticalLayout {
        ProjectListComp() {
            addStyleName("activity-comp");
        }

        void displayProjects(final Integer accountId) {
            ProjectSearchCriteria searchCriteria = new ProjectSearchCriteria();
            searchCriteria.addExtraField(ProjectSearchCriteria.p_account.buildParamIsEqual(SearchField.AND, accountId));
            ProjectService projectService = ApplicationContextUtil.getSpringBean(ProjectService.class);
            int totalCount = projectService.getTotalCount(searchCriteria);
            ELabel headerLbl = new ELabel("Projects (" + totalCount + ")");
            Button newProjectBtn = new Button("New Project", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    Project project = new Project();
                    project.setAccountid(accountId);
                    UI.getCurrent().addWindow(new ProjectAddWindow(project));
                }
            });
            newProjectBtn.addStyleName(UIConstants.BUTTON_ACTION);
            MHorizontalLayout headerPanel = new MHorizontalLayout().withMargin(true).withStyleName(UIConstants.FORM_SECTION)
                    .withWidth("100%").with(headerLbl, newProjectBtn).withAlign(headerLbl, Alignment.MIDDLE_LEFT)
                    .withAlign(newProjectBtn, Alignment.MIDDLE_RIGHT);
            this.addComponent(headerPanel);
            List<SimpleProject> projects = projectService.findPagableListByCriteria(new SearchRequest<>(searchCriteria, 0, Integer.MAX_VALUE));
            CssLayout content = new CssLayout();
            content.setStyleName(UIConstants.FLEX_DISPLAY);
            this.addComponent(content);
            for (SimpleProject project : projects) {
                content.addComponent(new ProjectBlock(project));
            }
        }
    }

    private static class ProjectBlock extends VerticalLayout {
        ProjectBlock(SimpleProject project) {
            this.setWidth("400px");
            this.addStyleName("entityblock");
            Div projectDiv = new Div().appendText(FontAwesome.BUILDING_O.getHtml() + " ").appendChild(new A
                    (ProjectLinkBuilder.generateProjectFullLink(project.getId())).appendText(project.getName()));
            ELabel headerLbl = new ELabel(projectDiv.write(), ContentMode.HTML).withStyleName("header");
            headerLbl.addStyleName(ValoTheme.LABEL_H3);
            headerLbl.addStyleName(ValoTheme.LABEL_NO_MARGIN);
            this.addComponent(headerLbl);


        }
    }
}
