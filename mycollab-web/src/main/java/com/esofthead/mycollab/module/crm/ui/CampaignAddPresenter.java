package com.esofthead.mycollab.module.crm.ui;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.vaadin.mvp.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractPresenter.ViewInterface;

@SuppressWarnings("serial")
@Scope("prototype")
@Component
@ViewInterface(CampaignAddView.class)
public class CampaignAddPresenter extends AbstractPresenter<CampaignAddView> {

}
