package com.mycollab.module.crm.view.activity;

import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.SimpleCall;
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
 * @since 2.0
 */
@ViewComponent
public class CallReadViewImpl extends AbstractPreviewItemComp<SimpleCall> implements CallReadView {
    private static final long serialVersionUID = 1L;

    private CrmActivityComponent activityComponent;
    private DateInfoComp dateInfoComp;
    private CrmFollowersComp<SimpleCall> followersComp;

    public CallReadViewImpl() {
        super(CrmAssetsManager.getAsset(CrmTypeConstants.INSTANCE.getCALL()));
    }

    @Override
    protected AdvancedPreviewBeanForm<SimpleCall> initPreviewForm() {
        return new AdvancedPreviewBeanForm<>();
    }

    @Override
    protected ComponentContainer createButtonControls() {
        return new CrmPreviewFormControlsGenerator<>(previewForm).createButtonControls(RolePermissionCollections.INSTANCE.getCRM_CALL());
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
        activityComponent = new CrmActivityComponent(CrmTypeConstants.INSTANCE.getCALL());

        dateInfoComp = new DateInfoComp();
        followersComp = new CrmFollowersComp<>(CrmTypeConstants.INSTANCE.getCALL(), RolePermissionCollections.INSTANCE.getCRM_CALL());
        addToSideBar(dateInfoComp, followersComp);

        tabSheet.addTab(previewLayout, CrmTypeConstants.INSTANCE.getDETAIL(), UserUIContext.getMessage(CrmCommonI18nEnum.TAB_ABOUT),
                CrmAssetsManager.getAsset(CrmTypeConstants.INSTANCE.getDETAIL()));
        tabSheet.selectTab(CrmTypeConstants.INSTANCE.getDETAIL());
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DefaultDynaFormLayout(CrmTypeConstants.INSTANCE.getCALL(), CallDefaultFormLayoutFactory.getForm());
    }

    @Override
    protected AbstractBeanFieldGroupViewFieldFactory<SimpleCall> initBeanFormFieldFactory() {
        return new CallReadFormFieldFactory(previewForm);
    }

    @Override
    public SimpleCall getItem() {
        return beanItem;
    }

    @Override
    public HasPreviewFormHandlers<SimpleCall> getPreviewFormHandlers() {
        return previewForm;
    }

    @Override
    protected String getType() {
        return CrmTypeConstants.INSTANCE.getCALL();
    }
}
