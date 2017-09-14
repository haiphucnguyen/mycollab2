package com.mycollab.premium.mobile.module.project.view.risk;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Span;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.html.DivLessFormatter;
import com.mycollab.mobile.form.view.DynaFormLayout;
import com.mycollab.mobile.module.project.events.RiskEvent;
import com.mycollab.mobile.module.project.ui.CommentNavigationButton;
import com.mycollab.mobile.module.project.ui.ProjectAttachmentDisplayComp;
import com.mycollab.mobile.module.project.ui.ProjectPreviewFormControlsGenerator;
import com.mycollab.mobile.ui.AbstractPreviewItemComp;
import com.mycollab.mobile.ui.AdvancedPreviewBeanForm;
import com.mycollab.mobile.ui.FormSectionBuilder;
import com.mycollab.mobile.ui.MobileUIConstants;
import com.mycollab.module.ecm.domain.Content;
import com.mycollab.module.ecm.service.ResourceService;
import com.mycollab.module.file.AttachmentUtils;
import com.mycollab.module.project.*;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.i18n.OptionI18nEnum.Priority;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.touchkit.NavigationBarQuickMenu;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.ui.IFormLayoutFactory;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.ui.field.DefaultViewField;
import com.mycollab.vaadin.ui.field.I18nFormViewField;
import com.mycollab.vaadin.ui.field.RichTextViewField;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
@ViewComponent
public class RiskReadViewImpl extends AbstractPreviewItemComp<SimpleRisk> implements RiskReadView {
    private Button quickActionStatusBtn;
    private CommentNavigationButton relatedComments;
    private RiskTimeLogComp riskTimeLogComp;
    private ProjectAttachmentDisplayComp attachmentComp;

    @Override
    public HasPreviewFormHandlers<SimpleRisk> getPreviewFormHandlers() {
        return previewForm;
    }

    @Override
    protected void afterPreviewItem() {
        if (StatusI18nEnum.Open.name().equals(beanItem.getStatus())) {
            quickActionStatusBtn.setCaption(UserUIContext.getMessage(GenericI18Enum.BUTTON_CLOSE));
            this.removeStyleName(MobileUIConstants.STATUS_DISABLED);
        } else {
            quickActionStatusBtn.setCaption(UserUIContext.getMessage(GenericI18Enum.BUTTON_REOPEN));
            this.addStyleName(MobileUIConstants.STATUS_DISABLED);
        }
        relatedComments.displayTotalComments(beanItem.getId() + "");

        if (!SiteConfiguration.isCommunityEdition()) {
            riskTimeLogComp.displayTime(beanItem);
            previewForm.addComponent(riskTimeLogComp);
        }

        ResourceService resourceService = AppContextUtil.getSpringBean(ResourceService.class);
        List<Content> attachments = resourceService.getContents(AttachmentUtils.getProjectEntityAttachmentPath(AppUI.getAccountId(),
                beanItem.getProjectid(), ProjectTypeConstants.RISK, "" + beanItem.getId()));
        if (CollectionUtils.isNotEmpty(attachments)) {
            attachmentComp = new ProjectAttachmentDisplayComp(attachments);
            previewForm.addComponent(attachmentComp);
        } else if (attachmentComp != null) {
            previewForm.removeComponent(attachmentComp);
        }
    }

    @Override
    protected String initFormHeader() {
        Span beanTitle = new Span().appendText(beanItem.getName());
        if (beanItem.isCompleted()) {
            beanTitle.setCSSClass(MobileUIConstants.LINK_COMPLETED);
        } else if (beanItem.isOverdue()) {
            beanTitle.setCSSClass(MobileUIConstants.LINK_OVERDUE);
        }
        return ProjectAssetsManager.getAsset(ProjectTypeConstants.RISK).getHtml() + " " + beanTitle.write();
    }

    @Override
    protected AdvancedPreviewBeanForm<SimpleRisk> initPreviewForm() {
        return new AdvancedPreviewBeanForm<>();
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DynaFormLayout(ProjectTypeConstants.RISK, RiskDefaultFormLayoutFactory.getForm(), Risk.Field.name.name());
    }

    @Override
    protected AbstractBeanFieldGroupViewFieldFactory<SimpleRisk> initBeanFormFieldFactory() {
        return new ReadFormFieldFactory(previewForm);
    }

    @Override
    protected ComponentContainer createButtonControls() {
        ProjectPreviewFormControlsGenerator<SimpleRisk> previewForm = new ProjectPreviewFormControlsGenerator<>(this.previewForm);
        final VerticalLayout formControls = previewForm.createButtonControls(
                ProjectPreviewFormControlsGenerator.CLONE_BTN_PRESENTED
                        | ProjectPreviewFormControlsGenerator.DELETE_BTN_PRESENTED,
                ProjectRolePermissionCollections.RISKS);

        quickActionStatusBtn = new Button("", clickEvent -> {
            if (beanItem.getStatus() != null && beanItem.getStatus().equals(StatusI18nEnum.Closed.name())) {
                beanItem.setStatus(StatusI18nEnum.Open.name());
                beanItem.setPercentagecomplete(0d);
                RiskReadViewImpl.this.removeStyleName(MobileUIConstants.STATUS_DISABLED);
                quickActionStatusBtn.setCaption(UserUIContext.getMessage(GenericI18Enum.BUTTON_CLOSE));
            } else {
                beanItem.setStatus(StatusI18nEnum.Closed.name());
                beanItem.setPercentagecomplete(100d);
                RiskReadViewImpl.this.addStyleName(MobileUIConstants.STATUS_DISABLED);
                quickActionStatusBtn.setCaption(UserUIContext.getMessage(GenericI18Enum.BUTTON_REOPEN));
            }

            RiskService riskService = AppContextUtil.getSpringBean(RiskService.class);
            riskService.updateWithSession(beanItem, UserUIContext.getUsername());
        });
        quickActionStatusBtn.setWidth("100%");

        previewForm.insertToControlBlock(quickActionStatusBtn);

        if (!CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS)) {
            quickActionStatusBtn.setEnabled(false);
        }

        MButton editBtn = new MButton("", clickEvent -> EventBusFactory.getInstance().post(new RiskEvent.GotoEdit(this, beanItem)))
                .withIcon(FontAwesome.EDIT).withStyleName(UIConstants.CIRCLE_BOX)
                .withVisible(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS));
        return new MHorizontalLayout(editBtn, new NavigationBarQuickMenu(formControls));
    }

    @Override
    protected String getType() {
        return ProjectTypeConstants.RISK;
    }

    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        AppUI.addFragment(ProjectLinkGenerator.generateRiskPreviewLink(beanItem.getProjectid(),
                beanItem.getId()), beanItem.getName());
    }

    @Override
    protected ComponentContainer createBottomPanel() {
        MVerticalLayout toolbarLayout = new MVerticalLayout().withSpacing(false).withMargin(false);
        toolbarLayout.setDefaultComponentAlignment(Alignment.TOP_LEFT);

        relatedComments = new CommentNavigationButton(ProjectTypeConstants.RISK, beanItem.getId() + "");
        Component section = FormSectionBuilder.build(FontAwesome.COMMENT, relatedComments);
        toolbarLayout.addComponent(section);

        if (!SiteConfiguration.isCommunityEdition()) {
            riskTimeLogComp = new RiskTimeLogComp();
            toolbarLayout.addComponent(riskTimeLogComp);
        }

        return toolbarLayout;
    }


    private class ReadFormFieldFactory extends AbstractBeanFieldGroupViewFieldFactory<SimpleRisk> {
        private static final long serialVersionUID = 1L;

        ReadFormFieldFactory(GenericBeanForm<SimpleRisk> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(final Object propertyId) {
            if (Risk.Field.assignuser.equalTo(propertyId)) {
                return new DefaultViewField(ProjectLinkBuilder.generateProjectMemberHtmlLink(CurrentProjectVariables
                        .getProjectId(), beanItem.getAssignuser(), beanItem.getAssignedToUserFullName(), beanItem
                        .getAssignToUserAvatarId(), false), ContentMode.HTML);
            } else if (Risk.Field.createduser.equalTo(propertyId)) {
                return new DefaultViewField(ProjectLinkBuilder.generateProjectMemberHtmlLink(CurrentProjectVariables.getProjectId(),
                        beanItem.getCreateduser(), beanItem.getRaisedByUserFullName(), beanItem.getRaisedByUserAvatarId(), false),
                        ContentMode.HTML);
            } else if (Risk.Field.startdate.equalTo(propertyId)) {
                return new DefaultViewField(UserUIContext.formatDate(beanItem.getStartdate()));
            } else if (Risk.Field.enddate.equalTo(propertyId)) {
                return new DefaultViewField(UserUIContext.formatDate(beanItem.getEnddate()));
            } else if (Risk.Field.duedate.equalTo(propertyId)) {
                return new DefaultViewField(UserUIContext.formatDate(beanItem.getDuedate()));
            } else if (Risk.Field.priority.equalTo(propertyId)) {
                if (StringUtils.isNotBlank(beanItem.getPriority())) {
                    FontAwesome fontPriority = ProjectAssetsManager.getPriority(beanItem.getPriority());
                    String priorityLbl = fontPriority.getHtml() + " " + UserUIContext.getMessage(Priority.class, beanItem.getPriority());
                    DefaultViewField field = new DefaultViewField(priorityLbl, ContentMode.HTML);
                    field.addStyleName("priority-" + beanItem.getPriority().toLowerCase());
                    return field;
                }
            } else if (Risk.Field.milestoneid.equalTo(propertyId)) {
                if (beanItem.getMilestoneid() != null) {
                    A milestoneLink = new A(ProjectLinkBuilder.generateMilestonePreviewFullLink
                            (CurrentProjectVariables.getProjectId(), beanItem.getMilestoneid())).appendText(beanItem.getMilestoneName());
                    Div milestoneDiv = new Div().appendText(ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE).getHtml()).appendChild(DivLessFormatter.EMPTY_SPACE, milestoneLink);
                    return new DefaultViewField(milestoneDiv.write(), ContentMode.HTML);
                }
            } else if (Risk.Field.description.equalTo(propertyId)) {
                return new RichTextViewField(beanItem.getDescription());
            } else if (Risk.Field.status.equalTo(propertyId)) {
                return new I18nFormViewField(beanItem.getStatus(), StatusI18nEnum.class).withStyleName(UIConstants.FIELD_NOTE);
            }
            return null;
        }
    }

}
