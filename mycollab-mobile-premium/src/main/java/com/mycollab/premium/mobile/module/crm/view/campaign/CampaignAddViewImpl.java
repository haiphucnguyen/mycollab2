package com.mycollab.premium.mobile.module.crm.view.campaign;

import com.mycollab.mobile.form.view.DynaFormLayout;
import com.mycollab.mobile.module.crm.view.campaign.CampaignAddView;
import com.mycollab.mobile.module.crm.view.campaign.CampaignDefaultDynaFormLayoutFactory;
import com.mycollab.mobile.ui.AbstractEditItemComp;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.SimpleCampaign;
import com.mycollab.module.crm.i18n.CampaignI18nEnum;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.IFormLayoutFactory;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@ViewComponent
public class CampaignAddViewImpl extends AbstractEditItemComp<SimpleCampaign> implements CampaignAddView {
    private static final long serialVersionUID = -345238804067938727L;

    @Override
    protected String initFormTitle() {
        return beanItem.getCampaignname() != null ? beanItem.getCampaignname() : UserUIContext.getMessage(CampaignI18nEnum.NEW);
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DynaFormLayout(CrmTypeConstants.CAMPAIGN, CampaignDefaultDynaFormLayoutFactory.getForm());
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<SimpleCampaign> initBeanFormFieldFactory() {
        return new CampaignEditFormFieldFactory<>(editForm);
    }
}
