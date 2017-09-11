package com.mycollab.module.crm.view.lead;

import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.SimpleActivity;
import com.mycollab.module.crm.domain.SimpleCampaign;
import com.mycollab.module.crm.domain.SimpleLead;
import com.mycollab.module.crm.domain.criteria.ActivitySearchCriteria;
import com.mycollab.module.crm.i18n.CampaignI18nEnum;
import com.mycollab.module.crm.i18n.CrmCommonI18nEnum;
import com.mycollab.module.crm.i18n.LeadI18nEnum;
import com.mycollab.module.crm.ui.CrmAssetsManager;
import com.mycollab.module.crm.ui.components.*;
import com.mycollab.module.crm.view.activity.ActivityRelatedItemListComp;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.mycollab.vaadin.ui.IFormLayoutFactory;
import com.mycollab.vaadin.ui.IRelatedListHandlers;
import com.mycollab.vaadin.web.ui.AdvancedPreviewBeanForm;
import com.mycollab.vaadin.web.ui.DefaultDynaFormLayout;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.ComponentContainer;
import org.vaadin.viritin.button.MButton;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class LeadReadViewImpl extends AbstractPreviewItemComp<SimpleLead> implements LeadReadView {
    private static final long serialVersionUID = 1L;

    private LeadCampaignListComp associateCampaignList;
    private ActivityRelatedItemListComp associateActivityList;
    private CrmActivityComponent activityComponent;

    private PeopleInfoComp peopleInfoComp;
    private DateInfoComp dateInfoComp;
    private CrmFollowersComp<SimpleLead> compFollowers;

    public LeadReadViewImpl() {
        super(CrmAssetsManager.getAsset(CrmTypeConstants.INSTANCE.getLEAD()));
    }

    @Override
    protected AdvancedPreviewBeanForm<SimpleLead> initPreviewForm() {
        return new AdvancedPreviewBeanForm<>();
    }

    @Override
    protected ComponentContainer createButtonControls() {
        CrmPreviewFormControlsGenerator<SimpleLead> controlsButton = new CrmPreviewFormControlsGenerator<>(
                previewForm);

        if (UserUIContext.canWrite(RolePermissionCollections.INSTANCE.getCRM_LEAD())) {
            MButton convertButton = new MButton(UserUIContext.getMessage(LeadI18nEnum.BUTTON_CONVERT_LEAD),
                    clickEvent -> previewForm.fireExtraAction("convert", beanItem))
                    .withIcon(FontAwesome.FLASK).withStyleName(WebThemes.BUTTON_ACTION);
            controlsButton.insertToControlBlock(convertButton);
        }

        return controlsButton.createButtonControls(RolePermissionCollections.INSTANCE.getCRM_LEAD());
    }

    @Override
    protected ComponentContainer createBottomPanel() {
        return activityComponent;
    }

    @Override
    protected void onPreviewItem() {
        activityComponent.loadActivities("" + beanItem.getId());
        displayActivities();
        displayCampaigns();

        dateInfoComp.displayEntryDateTime(beanItem);
        peopleInfoComp.displayEntryPeople(beanItem);
        compFollowers.displayFollowers(beanItem);

        tabSheet.selectTab(CrmTypeConstants.INSTANCE.getDETAIL());
    }

    @Override
    protected String initFormTitle() {
        return beanItem.getLeadName();
    }

    @Override
    protected void initRelatedComponents() {
        activityComponent = new CrmActivityComponent(CrmTypeConstants.INSTANCE.getLEAD());

        associateCampaignList = new LeadCampaignListComp();
        associateActivityList = new ActivityRelatedItemListComp(true);

        dateInfoComp = new DateInfoComp();
        peopleInfoComp = new PeopleInfoComp();
        compFollowers = new CrmFollowersComp<>(CrmTypeConstants.INSTANCE.getLEAD(), RolePermissionCollections.INSTANCE.getCRM_LEAD());
        addToSideBar(dateInfoComp, peopleInfoComp, compFollowers);

        tabSheet.addTab(previewLayout, CrmTypeConstants.INSTANCE.getDETAIL(), UserUIContext.getMessage(CrmCommonI18nEnum.TAB_ABOUT),
                CrmAssetsManager.getAsset(CrmTypeConstants.INSTANCE.getDETAIL()));
        tabSheet.addTab(associateCampaignList, CrmTypeConstants.INSTANCE.getCAMPAIGN(), UserUIContext.getMessage(CampaignI18nEnum.LIST),
                CrmAssetsManager.getAsset(CrmTypeConstants.INSTANCE.getCAMPAIGN()));
        tabSheet.addTab(associateActivityList, CrmTypeConstants.INSTANCE.getACTIVITY(), UserUIContext.getMessage(CrmCommonI18nEnum.TAB_ACTIVITY),
                CrmAssetsManager.getAsset(CrmTypeConstants.INSTANCE.getACTIVITY()));
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DefaultDynaFormLayout(CrmTypeConstants.INSTANCE.getLEAD(), LeadDefaultDynaFormLayoutFactory.getForm());
    }

    @Override
    protected AbstractBeanFieldGroupViewFieldFactory<SimpleLead> initBeanFormFieldFactory() {
        return new LeadReadFormFieldFactory(previewForm);
    }

    private void displayCampaigns() {
        associateCampaignList.displayCampaigns(beanItem);
    }

    private void displayActivities() {
        ActivitySearchCriteria criteria = new ActivitySearchCriteria();
        criteria.setSaccountid(new NumberSearchField(AppUI.getAccountId()));
        criteria.setType(StringSearchField.and(CrmTypeConstants.INSTANCE.getLEAD()));
        criteria.setTypeid(new NumberSearchField(beanItem.getId()));
        associateActivityList.setSearchCriteria(criteria);
    }

    @Override
    public SimpleLead getItem() {
        return beanItem;
    }

    @Override
    public HasPreviewFormHandlers<SimpleLead> getPreviewFormHandlers() {
        return previewForm;
    }

    @Override
    public IRelatedListHandlers<SimpleActivity> getRelatedActivityHandlers() {
        return associateActivityList;
    }

    @Override
    public IRelatedListHandlers<SimpleCampaign> getRelatedCampaignHandlers() {
        return associateCampaignList;
    }

    @Override
    protected String getType() {
        return CrmTypeConstants.INSTANCE.getLEAD();
    }
}
