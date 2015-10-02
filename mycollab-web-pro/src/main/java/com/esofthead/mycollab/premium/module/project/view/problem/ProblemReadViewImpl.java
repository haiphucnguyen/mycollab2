package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.i18n.ProblemI18nEnum;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.ui.components.*;
import com.esofthead.mycollab.module.project.ui.format.ProblemFieldFormatter;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.schedule.email.project.ProjectProblemRelayEmailNotificationAction;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.*;
import com.esofthead.mycollab.vaadin.ui.form.field.DateViewField;
import com.esofthead.mycollab.vaadin.ui.form.field.I18nFormViewField;
import com.esofthead.mycollab.vaadin.ui.form.field.RichTextViewField;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.ratingstars.RatingStars;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class ProblemReadViewImpl extends AbstractPreviewItemComp<SimpleProblem> implements ProblemReadView {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(ProblemReadViewImpl.class);

    private ProjectActivityComponent activityComponent;
    private DateInfoComp dateInfoComp;
    private PeopleInfoComp peopleInfoComp;

    private ProjectFollowersComp<SimpleProblem> followerSheet;

    public ProblemReadViewImpl() {
        super(AppContext.getMessage(ProblemI18nEnum.VIEW_READ_TITLE),
                ProjectAssetsManager.getAsset(ProjectTypeConstants.PROBLEM));
    }

    @Override
    protected AdvancedPreviewBeanForm<SimpleProblem> initPreviewForm() {
        return new AdvancedPreviewBeanForm<>();
    }

    @Override
    protected ComponentContainer createButtonControls() {
        return new ProjectPreviewFormControlsGenerator<>(previewForm).createButtonControls(ProjectRolePermissionCollections.PROBLEMS);
    }

    @Override
    protected void initRelatedComponents() {
        activityComponent = new ProjectActivityComponent(ProjectTypeConstants.PROBLEM,
                CurrentProjectVariables.getProjectId(), ProblemFieldFormatter.instance(),
                ProjectProblemRelayEmailNotificationAction.class);
        dateInfoComp = new DateInfoComp();
        peopleInfoComp = new PeopleInfoComp();
        followerSheet = new ProjectFollowersComp<>(ProjectTypeConstants.PROBLEM, ProjectRolePermissionCollections.PROBLEMS);
        addToSideBar(dateInfoComp, peopleInfoComp, followerSheet);
    }

    @Override
    protected void onPreviewItem() {
        if (StatusI18nEnum.Closed.name().equals(beanItem.getStatus())) {
            addLayoutStyleName(UIConstants.LINK_COMPLETED);
        }

        if (beanItem.isOverdue()) {
            previewLayout.setTitleStyleName("headerNameOverdue");
        }
        activityComponent.loadActivities("" + beanItem.getId());

        dateInfoComp.displayEntryDateTime(beanItem);
        peopleInfoComp.displayEntryPeople(beanItem);
        followerSheet.displayFollowers(beanItem);
    }

    @Override
    protected String initFormTitle() {
        return beanItem.getIssuename();
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DynaFormLayout(ProjectTypeConstants.PROBLEM,
                ProblemDefaultFormLayoutFactory.getForm(),
                Problem.Field.issuename.name());
    }

    @Override
    protected AbstractBeanFieldGroupViewFieldFactory<SimpleProblem> initBeanFormFieldFactory() {
        return new ProblemReadFormFieldFactory(previewForm);
    }

    @Override
    protected ComponentContainer createBottomPanel() {
        return activityComponent;
    }

    @Override
    protected String getType() {
        return ProjectTypeConstants.PROBLEM;
    }

    private static class ProblemReadFormFieldFactory extends
            AbstractBeanFieldGroupViewFieldFactory<SimpleProblem> {
        private static final long serialVersionUID = 1L;

        public ProblemReadFormFieldFactory(GenericBeanForm<SimpleProblem> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(Object propertyId) {
            SimpleProblem problem = attachForm.getBean();

            if (propertyId.equals("raisedbyuser")) {
                return new ProjectUserFormLinkField(problem.getRaisedbyuser(),
                        problem.getRaisedByUserAvatarId(),
                        problem.getRaisedByUserFullName());
            } else if (propertyId.equals("level")) {
                final RatingStars tinyRs = new RatingStars();
                tinyRs.setValue(problem.getLevel());
                tinyRs.setReadOnly(true);
                return tinyRs;
            } else if (propertyId.equals("status")) {
                return new I18nFormViewField(problem.getStatus(),
                        StatusI18nEnum.class);
            } else if (propertyId.equals("datedue")) {
                return new DateViewField(problem.getDatedue());
            } else if (propertyId.equals("assigntouser")) {
                return new ProjectUserFormLinkField(problem.getAssigntouser(),
                        problem.getAssignUserAvatarId(),
                        problem.getAssignedUserFullName());
            } else if (propertyId.equals("description")) {
                return new RichTextViewField(problem.getDescription());
            }

            return null;
        }
    }

    @Override
    public SimpleProblem getItem() {
        return beanItem;
    }

    @Override
    public HasPreviewFormHandlers<SimpleProblem> getPreviewFormHandlers() {
        return previewForm;
    }

    private static class PeopleInfoComp extends VerticalLayout {
        private static final long serialVersionUID = 1L;

        void displayEntryPeople(ValuedBean bean) {
            this.removeAllComponents();
            this.setSpacing(true);
            this.setMargin(new MarginInfo(false, false, false, true));

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

                String createdUserName = (String) PropertyUtils.getProperty(
                        bean, "raisedbyuser");
                String createdUserAvatarId = (String) PropertyUtils
                        .getProperty(bean, "raisedByUserAvatarId");
                String createdUserDisplayName = (String) PropertyUtils
                        .getProperty(bean, "raisedByUserFullName");

                UserLink createdUserLink = new UserLink(createdUserName,
                        createdUserAvatarId, createdUserDisplayName);
                layout.addComponent(createdUserLink, 1, 0);
                layout.setColumnExpandRatio(1, 1.0f);

                Label assigneeLbl = new Label(AppContext
                        .getMessage(ProjectCommonI18nEnum.ITEM_ASSIGN_PEOPLE));
                assigneeLbl.setSizeUndefined();
                layout.addComponent(assigneeLbl, 0, 1);
                String assignUserName = (String) PropertyUtils.getProperty(
                        bean, "assigntouser");
                String assignUserAvatarId = (String) PropertyUtils.getProperty(
                        bean, "assignUserAvatarId");
                String assignUserDisplayName = (String) PropertyUtils
                        .getProperty(bean, "assignedUserFullName");

                UserLink assignUserLink = new UserLink(assignUserName,
                        assignUserAvatarId, assignUserDisplayName);
                layout.addComponent(assignUserLink, 1, 1);
            } catch (Exception e) {
                LOG.error("Can not build user link {} ",
                        BeanUtility.printBeanObj(bean));
            }

            this.addComponent(layout);

        }
    }
}
