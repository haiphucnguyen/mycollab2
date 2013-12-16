package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.ui.components.AbstractPreviewItemComp;
import com.esofthead.mycollab.module.crm.view.cases.CasesDefaultFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class AbstractCasePreviewComp extends
		AbstractPreviewItemComp<SimpleCase> {
	private static final long serialVersionUID = 1L;

	public AbstractCasePreviewComp() {
		super(MyCollabResource.newResource("icons/22/crm/case.png"));
	}

	@Override
	protected void onPreviewItem() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String initFormTitle() {
		return beanItem.getSubject();
	}

	@Override
	protected AdvancedPreviewBeanForm<SimpleCase> initPreviewForm() {
		return previewForm;
	}

	@Override
	protected void initRelatedComponents() {
		// TODO Auto-generated method stub

	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new DynaFormLayout(CrmTypeConstants.CASE,
				CasesDefaultFormLayoutFactory.getForm());
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<SimpleCase> initBeanFormFieldFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ComponentContainer createButtonControls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ComponentContainer createBottomPanel() {
		// TODO Auto-generated method stub
		return null;
	}

}
