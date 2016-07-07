package com.mycollab.pro.module.project.view.client;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.mycollab.configuration.Storage;
import com.mycollab.configuration.StorageFactory;
import com.mycollab.core.utils.BeanUtility;
import com.mycollab.core.utils.NumberUtils;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.html.DivLessFormatter;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.SimpleAccount;
import com.mycollab.module.crm.ui.components.CrmFollowersComp;
import com.mycollab.module.crm.ui.components.CrmPreviewFormControlsGenerator;
import com.mycollab.module.project.ProjectLinkBuilder;
import com.mycollab.module.project.domain.Project;
import com.mycollab.module.project.domain.SimpleProject;
import com.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.mycollab.module.project.i18n.ClientI18nEnum;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.i18n.ProjectI18nEnum;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.module.project.ui.components.AbstractPreviewItemComp;
import com.mycollab.module.project.ui.components.DateInfoComp;
import com.mycollab.pro.module.project.view.ProjectAddWindow;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.AdvancedPreviewBeanForm;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.mycollab.vaadin.web.ui.UserLink;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.button.MButton;
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
        super(AppContext.getMessage(ClientI18nEnum.SINGLE), FontAwesome.INSTITUTION);
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
        if (beanItem.getAvatarid() != null) {
            Img img = new Img("", Storage.getEntityLogoPath(AppContext.getAccountId(), beanItem.getAvatarid(), 32))
                    .setCSSClass(UIConstants.CIRCLE_BOX);
            return new Div().appendChild(img).appendChild(DivLessFormatter.EMPTY_SPACE()).appendText(beanItem.getAccountname()).write();
        } else {
            return beanItem.getAccountname();
        }
    }

    @Override
    protected AdvancedPreviewBeanForm<SimpleAccount> initPreviewForm() {
        return new ClientPreviewForm();
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
            this.withMargin(false);

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
            searchCriteria.setAccountId(NumberSearchField.and(accountId));
            ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
            int totalCount = projectService.getTotalCount(searchCriteria);
            ELabel headerLbl = new ELabel(AppContext.getMessage(ClientI18nEnum.OPT_NUM_PROJECTS, totalCount));
            MButton newProjectBtn = new MButton(AppContext.getMessage(ProjectI18nEnum.NEW), clickEvent -> {
                Project project = new Project();
                project.setAccountid(accountId);
                UI.getCurrent().addWindow(new ProjectAddWindow(project));
            }).withStyleName(UIConstants.BUTTON_ACTION).withVisible(AppContext.canBeYes(RolePermissionCollections.CREATE_NEW_PROJECT));

            MHorizontalLayout headerPanel = new MHorizontalLayout().withMargin(true).withStyleName(UIConstants.FORM_SECTION)
                    .withFullWidth().with(headerLbl, newProjectBtn).withAlign(headerLbl, Alignment.MIDDLE_LEFT)
                    .withAlign(newProjectBtn, Alignment.MIDDLE_RIGHT);
            this.addComponent(headerPanel);
            List<SimpleProject> projects = projectService.findPagableListByCriteria(new BasicSearchRequest<>(searchCriteria, 0, Integer.MAX_VALUE));
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
            A projectDiv = new A(ProjectLinkBuilder.generateProjectFullLink(project.getId())).appendText(FontAwesome
                    .BUILDING_O.getHtml() + " " + project.getName()).setTitle(project.getName());
            ELabel headerLbl = new ELabel(projectDiv.write(), ContentMode.HTML).withStyleName("header");
            headerLbl.addStyleName(ValoTheme.LABEL_H3);
            headerLbl.addStyleName(ValoTheme.LABEL_NO_MARGIN);
            headerLbl.addStyleName(UIConstants.TEXT_ELLIPSIS);
            this.addComponent(headerLbl);

            Div activeMembersDiv = new Div().appendText(FontAwesome.USERS.getHtml() + " " + project.getNumActiveMembers()).setTitle("Active members");
            Div createdTimeDiv = new Div().appendText(FontAwesome.CLOCK_O.getHtml() + " " + AppContext
                    .formatPrettyTime(project.getCreatedtime())).setTitle("Created time");
            Div billableHoursDiv = new Div().appendText(FontAwesome.MONEY.getHtml() + " " + NumberUtils.roundDouble(2, project.getTotalBillableHours())).
                    setTitle("Billable hours");
            Div nonBillableHoursDiv = new Div().appendText(FontAwesome.GIFT.getHtml() + " " + NumberUtils.roundDouble(2,
                    project.getTotalNonBillableHours())).setTitle(AppContext.getMessage(TimeTrackingI18nEnum.OPT_NON_BILLABLE_HOURS));
            Div metaDiv = new Div().appendChild(activeMembersDiv, DivLessFormatter.EMPTY_SPACE(), createdTimeDiv,
                    DivLessFormatter.EMPTY_SPACE(), billableHoursDiv, DivLessFormatter.EMPTY_SPACE(),
                    nonBillableHoursDiv, DivLessFormatter.EMPTY_SPACE());
            if (project.getLead() != null) {
                Div leadDiv = new Div().appendChild(new Img("", StorageFactory.getInstance().getAvatarPath(project
                        .getLeadAvatarId(), 16)), DivLessFormatter.EMPTY_SPACE(), new A(ProjectLinkBuilder.generateProjectMemberFullLink(project
                        .getId(), project.getLead())).appendText(StringUtils.trim(project.getLeadFullName(), 30, true)))
                        .setTitle("Manager");
                metaDiv.appendChild(0, leadDiv);
                metaDiv.appendChild(1, DivLessFormatter.EMPTY_SPACE());
            }
            metaDiv.setCSSClass(UIConstants.FLEX_DISPLAY);
            ELabel prjInfo = new ELabel(metaDiv.write(), ContentMode.HTML).withStyleName(UIConstants.META_INFO).withWidthUndefined();
            this.addComponent(prjInfo);

            int openAssignments = project.getNumOpenBugs() + project.getNumOpenTasks() + project.getNumOpenRisks();
            int totalAssignments = project.getNumBugs() + project.getNumTasks() + project.getNumRisks();
            ELabel progressInfoLbl;
            if (totalAssignments > 0) {
                progressInfoLbl = new ELabel(AppContext.getMessage(ProjectI18nEnum.OPT_PROJECT_ASSIGNMENT,
                        (totalAssignments - openAssignments), totalAssignments, (totalAssignments - openAssignments)
                                * 100 / totalAssignments)).withStyleName(UIConstants.META_INFO);
            } else {
                progressInfoLbl = new ELabel(AppContext.getMessage(ProjectI18nEnum.OPT_NO_ASSIGNMENT))
                        .withStyleName(UIConstants.META_INFO);
            }
            this.addComponent(progressInfoLbl);
        }
    }
}
