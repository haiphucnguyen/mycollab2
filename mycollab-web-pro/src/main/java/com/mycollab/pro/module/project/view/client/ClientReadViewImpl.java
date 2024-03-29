package com.mycollab.pro.module.project.view.client;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.mycollab.common.domain.SimpleClient;
import com.mycollab.common.i18n.ClientI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.utils.BeanUtility;
import com.mycollab.core.utils.NumberUtils;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.html.DivLessFormatter;
import com.mycollab.module.file.StorageUtils;
import com.mycollab.module.project.ProjectLinkGenerator;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Project;
import com.mycollab.module.project.domain.SimpleProject;
import com.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.mycollab.module.project.i18n.ProjectI18nEnum;
import com.mycollab.module.project.i18n.ProjectMemberI18nEnum;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.pro.module.project.view.ProjectAddWindow;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.event.HasPreviewFormHandlers;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.AbstractPreviewItemComp;
import com.mycollab.vaadin.web.ui.AdvancedPreviewBeanForm;
import com.mycollab.vaadin.web.ui.UserLink;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
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
public class ClientReadViewImpl extends AbstractPreviewItemComp<SimpleClient> implements ClientReadView {
    private static Logger LOG = LoggerFactory.getLogger(ClientReadViewImpl.class);

    private ProjectListComp projectListComp;

    public ClientReadViewImpl() {
        super(UserUIContext.getMessage(ClientI18nEnum.SINGLE), VaadinIcons.INSTITUTION);
    }

    @Override
    protected void initRelatedComponents() {
        projectListComp = new ProjectListComp();
    }

    @Override
    protected String getType() {
        return ProjectTypeConstants.CLIENT;
    }

    @Override
    protected void onPreviewItem() {
        projectListComp.displayProjects(beanItem.getId());
    }

    @Override
    protected String initFormTitle() {
        if (beanItem.getAvatarid() != null) {
            Img img = new Img("", StorageUtils.getEntityLogoPath(AppUI.getAccountId(), beanItem.getAvatarid(), 16))
                    .setCSSClass(WebThemes.CIRCLE_BOX);
            return new Div().appendChild(img).appendChild(DivLessFormatter.EMPTY_SPACE).appendText(beanItem.getName()).write();
        } else {
            return beanItem.getName();
        }
    }

    @Override
    protected AdvancedPreviewBeanForm<SimpleClient> initPreviewForm() {
        return new ClientPreviewForm();
    }

    @Override
    protected HorizontalLayout createButtonControls() {
        ClientPreviewFormControlsGenerator generator = new ClientPreviewFormControlsGenerator(previewForm);
        return generator.createButtonControls(RolePermissionCollections.CLIENT);
    }

    @Override
    protected ComponentContainer createBottomPanel() {
        return projectListComp;
    }

    @Override
    public HasPreviewFormHandlers<SimpleClient> getPreviewFormHandlers() {
        return previewForm;
    }

    @Override
    public SimpleClient getItem() {
        return beanItem;
    }

    private static class PeopleInfoComp extends MVerticalLayout {
        private static final long serialVersionUID = 1L;

        void displayEntryPeople(SimpleClient bean) {
            this.removeAllComponents();
            this.withMargin(false);

            Label peopleInfoHeader = new Label(VaadinIcons.USER.getHtml() + " " +
                    UserUIContext.getMessage(ProjectCommonI18nEnum.SUB_INFO_PEOPLE), ContentMode.HTML);
            peopleInfoHeader.setStyleName("info-hdr");
            this.addComponent(peopleInfoHeader);

            GridLayout layout = new GridLayout(2, 2);
            layout.setSpacing(true);
            layout.setWidth("100%");
            layout.setMargin(new MarginInfo(false, false, false, true));
            try {
                Label createdLbl = new Label(UserUIContext.getMessage(ProjectCommonI18nEnum.ITEM_CREATED_PEOPLE));
                createdLbl.setSizeUndefined();
                layout.addComponent(createdLbl, 0, 0);

                String createdUserName = bean.getCreateduser();
                String createdUserAvatarId = bean.getCreatedUserAvatarId();
                String createdUserDisplayName = bean.getCreatedUserFullName();

                UserLink createdUserLink = new UserLink(createdUserName, createdUserAvatarId, createdUserDisplayName);
                layout.addComponent(createdUserLink, 1, 0);
                layout.setColumnExpandRatio(1, 1.0f);

                Label assigneeLbl = new Label(UserUIContext.getMessage(ProjectCommonI18nEnum.ITEM_ASSIGN_PEOPLE));
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
            setMargin(false);
        }

        void displayProjects(final Integer accountId) {
            ProjectSearchCriteria searchCriteria = new ProjectSearchCriteria();
            searchCriteria.setSaccountid(NumberSearchField.equal(AppUI.getAccountId()));
            searchCriteria.setClientId(NumberSearchField.equal(accountId));
            ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
            int totalCount = projectService.getTotalCount(searchCriteria);
            ELabel headerLbl = new ELabel(UserUIContext.getMessage(ClientI18nEnum.OPT_NUM_PROJECTS, totalCount));

            MHorizontalLayout headerPanel = new MHorizontalLayout(headerLbl).withMargin(false).withStyleName(WebThemes.FORM_SECTION).withFullWidth();
            this.addComponent(headerPanel);
            List<SimpleProject> projects = (List<SimpleProject>) projectService.findPageableListByCriteria(new BasicSearchRequest<>(searchCriteria));
            CssLayout content = new CssLayout();
            content.setStyleName(WebThemes.FLEX_DISPLAY);
            this.addComponent(content);
            projects.forEach(project -> content.addComponent(new ProjectBlock(project)));
        }
    }

    private static class ProjectBlock extends VerticalLayout {
        ProjectBlock(SimpleProject project) {
            this.setWidth("400px");
            this.addStyleName("entityblock");
            A projectDiv = new A(ProjectLinkGenerator.generateProjectLink(project.getId())).appendText(ProjectAssetsManager.getAsset(ProjectTypeConstants.PROJECT).getHtml() + " " +
                    project.getName()).setTitle(project.getName());
            ELabel headerLbl = ELabel.h3(projectDiv.write()).withStyleName("header", WebThemes.TEXT_ELLIPSIS);
            this.addComponent(headerLbl);

            Div activeMembersDiv = new Div().appendText(VaadinIcons.USERS.getHtml() + " " + project.getNumActiveMembers())
                    .setTitle(UserUIContext.getMessage(ProjectMemberI18nEnum.OPT_ACTIVE_MEMBERS));
            Div createdTimeDiv = new Div().appendText(VaadinIcons.CLOCK.getHtml() + " " + UserUIContext
                    .formatPrettyTime(project.getCreatedtime())).setTitle(UserUIContext.getMessage(GenericI18Enum.FORM_CREATED_TIME));
            Div billableHoursDiv = new Div().appendText(VaadinIcons.MONEY.getHtml() + " " + NumberUtils.roundDouble(2, project.getTotalBillableHours())).
                    setTitle(UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_BILLABLE_HOURS));
            Div nonBillableHoursDiv = new Div().appendText(VaadinIcons.GIFT.getHtml() + " " + NumberUtils.roundDouble(2,
                    project.getTotalNonBillableHours())).setTitle(UserUIContext.getMessage(TimeTrackingI18nEnum.OPT_NON_BILLABLE_HOURS));
            Div metaDiv = new Div().appendChild(activeMembersDiv, DivLessFormatter.EMPTY_SPACE, createdTimeDiv,
                    DivLessFormatter.EMPTY_SPACE, billableHoursDiv, DivLessFormatter.EMPTY_SPACE,
                    nonBillableHoursDiv, DivLessFormatter.EMPTY_SPACE);
            if (project.getMemlead() != null) {
                Div leadDiv = new Div().appendChild(new Img("", StorageUtils.getAvatarPath(project
                        .getLeadAvatarId(), 16)), DivLessFormatter.EMPTY_SPACE, new A(ProjectLinkGenerator.generateProjectMemberLink(project
                        .getId(), project.getMemlead())).appendText(StringUtils.trim(project.getLeadFullName(), 30, true)))
                        .setTitle(UserUIContext.getMessage(ProjectI18nEnum.FORM_LEADER));
                metaDiv.appendChild(0, leadDiv);
                metaDiv.appendChild(1, DivLessFormatter.EMPTY_SPACE);
            }
            metaDiv.setCSSClass(WebThemes.FLEX_DISPLAY);
            ELabel prjInfo = ELabel.html(metaDiv.write()).withStyleName(WebThemes.META_INFO).withUndefinedWidth();
            this.addComponent(prjInfo);

            int openAssignments = project.getNumOpenBugs() + project.getNumOpenTasks() + project.getNumOpenRisks();
            int totalAssignments = project.getNumBugs() + project.getNumTasks() + project.getNumRisks();
            ELabel progressInfoLbl;
            if (totalAssignments > 0) {
                progressInfoLbl = new ELabel(UserUIContext.getMessage(ProjectI18nEnum.OPT_PROJECT_TICKET,
                        (totalAssignments - openAssignments), totalAssignments, (totalAssignments - openAssignments)
                                * 100 / totalAssignments)).withStyleName(WebThemes.META_INFO);
            } else {
                progressInfoLbl = new ELabel(UserUIContext.getMessage(ProjectI18nEnum.OPT_NO_TICKET))
                        .withStyleName(WebThemes.META_INFO);
            }
            this.addComponent(progressInfoLbl);
        }
    }
}
