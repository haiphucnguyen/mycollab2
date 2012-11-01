package com.esofthead.mycollab.module.crm.ui;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.vaadin.mvp.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractPresenter.ViewInterface;

@Scope("prototype")
@Component
@ViewInterface(AccountListView.class)
public class AccountListPresenter extends AbstractPresenter<AccountListView> {
	private static final long serialVersionUID = 1L;
}
