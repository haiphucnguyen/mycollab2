package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout2;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.server.Resource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 * @param <B>
 */
public abstract class AbstractPreviewItemComp<B> extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	protected B beanItem;
	protected AddViewLayout2 previewLayout;
	protected AdvancedPreviewBeanForm<B> previewForm;

	public AbstractPreviewItemComp(Resource iconResource) {
		previewLayout = new AddViewLayout2("", iconResource);
		this.addComponent(previewLayout);

		initRelatedComponents();

		previewForm = initPreviewForm();

		VerticalLayout informationLayout = new VerticalLayout();
		informationLayout.addStyleName("main-info");
		ComponentContainer actionControls = createButtonControls();
		if (actionControls != null) {
			actionControls.addStyleName("control-buttons");
			informationLayout.addComponent(actionControls);
		}

		informationLayout.addComponent(previewForm);
		previewLayout.addBody(informationLayout);
		previewLayout.addBody(createBottomPanel());
	}

	public void previewItem(final B item) {
		this.beanItem = item;
		previewLayout.setTitle(initFormTitle());

		previewForm.setFormLayoutFactory(initFormLayoutFactory());
		previewForm.setBeanFormFieldFactory(initBeanFormFieldFactory());
		previewForm.setBean(item);

		onPreviewItem();
	}

	public B getBeanItem() {
		return beanItem;
	}

	abstract protected void onPreviewItem();

	abstract protected String initFormTitle();

	abstract protected AdvancedPreviewBeanForm<B> initPreviewForm();

	abstract protected void initRelatedComponents();

	abstract protected IFormLayoutFactory initFormLayoutFactory();

	abstract protected AbstractBeanFieldGroupViewFieldFactory<B> initBeanFormFieldFactory();

	abstract protected ComponentContainer createButtonControls();

	abstract protected ComponentContainer createBottomPanel();

}
