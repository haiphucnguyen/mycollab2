package com.mycollab.module.crm.view.activity;

import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.SimpleMeeting;
import com.mycollab.module.crm.i18n.CrmCommonI18nEnum;
import com.mycollab.module.crm.ui.CrmAssetsManager;
import com.mycollab.module.crm.ui.components.*;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.mycollab.vaadin.ui.IFormLayoutFactory;
import com.mycollab.vaadin.web.ui.AdvancedPreviewBeanForm;
import com.mycollab.vaadin.web.ui.DefaultDynaFormLayout;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class MeetingReadViewImpl extends AbstractPreviewItemComp<SimpleMeeting> implements MeetingReadView {
    private static final long serialVersionUID = 1L;

    private CrmActivityComponent activityComponent;
    private DateInfoComp dateInfoComp;
    private CrmFollowersComp<SimpleMeeting> followersComp;

    public MeetingReadViewImpl() {
        super(CrmAssetsManager.getAsset(CrmTypeConstants.INSTANCE.getMEETING()));
    }

    @Override
    protected AdvancedPreviewBeanForm<SimpleMeeting> initPreviewForm() {
        return new AdvancedPreviewBeanForm<>();
    }

    @Override
    protected ComponentContainer createButtonControls() {
        return new CrmPreviewFormControlsGenerator<>(previewForm).createButtonControls(RolePermissionCollections.INSTANCE.getCRM_MEETING());
    }

    @Override
    protected ComponentContainer createBottomPanel() {
        return activityComponent;
    }

    @Override
    protected void onPreviewItem() {
        activityComponent.loadActivities("" + beanItem.getId());
        dateInfoComp.displayEntryDateTime(beanItem);
        followersComp.displayFollowers(beanItem);
    }

    @Override
    protected String initFormTitle() {
        return beanItem.getSubject();
    }

    @Override
    protected void initRelatedComponents() {
        activityComponent = new CrmActivityComponent(CrmTypeConstants.INSTANCE.getMEETING());

        dateInfoComp = new DateInfoComp();
        followersComp = new CrmFollowersComp<>(CrmTypeConstants.INSTANCE.getMEETING(), RolePermissionCollections.INSTANCE.getCRM_MEETING());
        addToSideBar(dateInfoComp, followersComp);

        tabSheet.addTab(previewLayout, CrmTypeConstants.INSTANCE.getDETAIL(), UserUIContext.getMessage(CrmCommonI18nEnum.TAB_ABOUT),
                CrmAssetsManager.getAsset(CrmTypeConstants.INSTANCE.getDETAIL()));
        tabSheet.selectTab(CrmTypeConstants.INSTANCE.getDETAIL());
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DefaultDynaFormLayout(CrmTypeConstants.INSTANCE.getMEETING(), MeetingDefaultFormLayoutFactory.getForm());
    }

    @Override
    protected AbstractBeanFieldGroupViewFieldFactory<SimpleMeeting> initBeanFormFieldFactory() {
        return new MeetingReadFormFieldFactory(previewForm);
    }

    @Override
    public SimpleMeeting getItem() {
        return beanItem;
    }

    @Override
    public HasPreviewFormHandlers<SimpleMeeting> getPreviewFormHandlers() {
        return previewForm;
    }

    @Override
    protected String getType() {
        return CrmTypeConstants.INSTANCE.getMEETING();
    }
}
