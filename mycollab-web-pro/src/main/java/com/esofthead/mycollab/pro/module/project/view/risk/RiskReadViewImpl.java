package com.esofthead.mycollab.pro.module.project.view.risk;

import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.i18n.RiskI18nEnum;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.ui.components.*;
import com.esofthead.mycollab.module.project.ui.form.ProjectItemViewField;
import com.esofthead.mycollab.module.project.ui.format.RiskFieldFormatter;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.web.ui.*;
import com.esofthead.mycollab.vaadin.web.ui.field.DateViewField;
import com.esofthead.mycollab.vaadin.web.ui.field.I18nFormViewField;
import com.esofthead.mycollab.vaadin.web.ui.field.RichTextViewField;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.ratingstars.RatingStars;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class RiskReadViewImpl extends AbstractPreviewItemComp<SimpleRisk> implements RiskReadView {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(RiskReadViewImpl.class);

    private TagViewComponent tagViewComponent;
    private ProjectActivityComponent activityComponent;
    private DateInfoComp dateInfoComp;
    private PeopleInfoComp peopleInfoComp;
    private ProjectFollowersComp<SimpleRisk> followerSheet;

    public RiskReadViewImpl() {
        super(AppContext.getMessage(RiskI18nEnum.FORM_READ_TITLE), ProjectAssetsManager.getAsset(ProjectTypeConstants.RISK));
    }

    @Override
    protected AdvancedPreviewBeanForm<SimpleRisk> initPreviewForm() {
        return new AdvancedPreviewBeanForm<>();
    }

    @Override
    protected ComponentContainer createButtonControls() {
        return new ProjectPreviewFormControlsGenerator<>(previewForm).createButtonControls(ProjectRolePermissionCollections.RISKS);
    }

    @Override
    protected String initFormTitle() {
        return beanItem.getRiskname();
    }

    @Override
    protected void initRelatedComponents() {
        activityComponent = new ProjectActivityComponent(ProjectTypeConstants.RISK,
                CurrentProjectVariables.getProjectId(), RiskFieldFormatter.instance());
        dateInfoComp = new DateInfoComp();
        peopleInfoComp = new PeopleInfoComp();
        followerSheet = new ProjectFollowersComp<>(ProjectTypeConstants.RISK, ProjectRolePermissionCollections.RISKS);
        addToSideBar(dateInfoComp, peopleInfoComp, followerSheet);
    }

    @Override
    protected void onPreviewItem() {
        if (StatusI18nEnum.Closed.name().equals(beanItem.getStatus())) {
            addLayoutStyleName(UIConstants.LINK_COMPLETED);
        }

        if (beanItem.isOverdue()) {
            previewLayout.addTitleStyleName(UIConstants.LABEL_OVERDUE);
        }

        tagViewComponent.display(ProjectTypeConstants.RISK, beanItem.getId());
        activityComponent.loadActivities("" + beanItem.getId());
        dateInfoComp.displayEntryDateTime(beanItem);
        peopleInfoComp.displayEntryPeople(beanItem);
        followerSheet.displayFollowers(beanItem);
    }

    @Override
    protected ComponentContainer createExtraControls() {
        tagViewComponent = new TagViewComponent();
        return tagViewComponent;
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DynaFormLayout(ProjectTypeConstants.RISK,
                RiskDefaultFormLayoutFactory.getForm(), Risk.Field.riskname.name());
    }

    @Override
    protected AbstractBeanFieldGroupViewFieldFactory<SimpleRisk> initBeanFormFieldFactory() {
        return new RiskReadFormFieldFactory(previewForm);
    }

    @Override
    protected ComponentContainer createBottomPanel() {
        return activityComponent;
    }

    @Override
    protected String getType() {
        return ProjectTypeConstants.RISK;
    }

    private static class RiskReadFormFieldFactory extends AbstractBeanFieldGroupViewFieldFactory<SimpleRisk> {
        private static final long serialVersionUID = 1L;

        public RiskReadFormFieldFactory(GenericBeanForm<SimpleRisk> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(Object propertyId) {
            SimpleRisk risk = attachForm.getBean();
            if (Risk.Field.description.equalTo(propertyId)) {
                return new RichTextViewField(risk.getDescription());
            } else if (Risk.Field.level.equalTo(propertyId)) {
                RatingStars tinyRs = new RatingStars();
                tinyRs.setValue(risk.getLevel());
                tinyRs.setReadOnly(true);
                return tinyRs;
            } else if (Risk.Field.status.equalTo(propertyId)) {
                return new I18nFormViewField(risk.getStatus(), StatusI18nEnum.class);
            } else if (Risk.Field.datedue.equalTo(propertyId)) {
                return new DateViewField(risk.getDatedue());
            } else if (Risk.Field.startdate.equalTo(propertyId)) {
                return new DateViewField(risk.getStartdate());
            } else if (Risk.Field.enddate.equalTo(propertyId)) {
                return new DateViewField(risk.getEnddate());
            } else if (Risk.Field.raisedbyuser.equalTo(propertyId)) {
                return new ProjectUserFormLinkField(risk.getRaisedbyuser(), risk.getRaisedByUserAvatarId(),
                        risk.getRaisedByUserFullName());
            } else if (Risk.Field.assigntouser.equalTo(propertyId)) {
                return new ProjectUserFormLinkField(risk.getAssigntouser(), risk.getAssignToUserAvatarId(),
                        risk.getAssignedToUserFullName());
            } else if (Risk.Field.response.equalTo(propertyId)) {
                return new RichTextViewField(risk.getResponse());
            } else if (Risk.Field.milestoneid.equalTo(propertyId)) {
                return new ProjectItemViewField(ProjectTypeConstants.MILESTONE, risk.getMilestoneid() + "",
                        risk.getMilestoneName());
            }

            return null;
        }
    }

    @Override
    public SimpleRisk getItem() {
        return beanItem;
    }

    @Override
    public HasPreviewFormHandlers<SimpleRisk> getPreviewFormHandlers() {
        return previewForm;
    }

    private static class PeopleInfoComp extends MVerticalLayout {
        private static final long serialVersionUID = 1L;

        void displayEntryPeople(ValuedBean bean) {
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

                String createdUserName = (String) PropertyUtils.getProperty(bean, "raisedbyuser");
                String createdUserAvatarId = (String) PropertyUtils.getProperty(bean, "raisedByUserAvatarId");
                String createdUserDisplayName = (String) PropertyUtils.getProperty(bean, "raisedByUserFullName");

                UserLink createdUserLink = new UserLink(createdUserName, createdUserAvatarId, createdUserDisplayName);
                layout.addComponent(createdUserLink, 1, 0);
                layout.setColumnExpandRatio(1, 1.0f);

                Label assigneeLbl = new Label(AppContext.getMessage(ProjectCommonI18nEnum.ITEM_ASSIGN_PEOPLE));
                assigneeLbl.setSizeUndefined();
                layout.addComponent(assigneeLbl, 0, 1);
                String assignUserName = (String) PropertyUtils.getProperty(bean, "assigntouser");
                String assignUserAvatarId = (String) PropertyUtils.getProperty(bean, "assignToUserAvatarId");
                String assignUserDisplayName = (String) PropertyUtils.getProperty(bean, "assignedToUserFullName");

                UserLink assignUserLink = new UserLink(assignUserName, assignUserAvatarId, assignUserDisplayName);
                layout.addComponent(assignUserLink, 1, 1);
            } catch (Exception e) {
                LOG.error("Can not build user link {} ", BeanUtility.printBeanObj(bean));
            }

            this.addComponent(layout);
        }
    }
}
