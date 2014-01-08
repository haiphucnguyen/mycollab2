package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.PageView;
import com.esofthead.mycollab.vaadin.resource.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.resource.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.resource.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.resource.ui.ReadViewLayout;
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
public abstract class AbstractPreviewItemComp<B> extends VerticalLayout
		implements PageView {
	private static final long serialVersionUID = 1L;
	
	protected B beanItem;
	protected AdvancedPreviewBeanForm<B> previewForm;
	protected ReadViewLayout previewLayout;

	abstract protected void initRelatedComponents();

	public AbstractPreviewItemComp(Resource iconResource) {
		previewLayout = new ReadViewLayout("", iconResource);

		this.addComponent(previewLayout);

		previewForm = initPreviewForm();
		ComponentContainer actionControls = createButtonControls();
		if (actionControls != null) {
			actionControls.addStyleName("control-buttons");
			previewLayout.addTopControls(actionControls);
		}

		previewLayout.addBody(previewForm);
	}

	private void initLayout() {
		initRelatedComponents();
		ComponentContainer bottomPanel = createBottomPanel();
		if (bottomPanel != null) {
			previewLayout.addBottomControls(bottomPanel);
		}
	}

	public void previewItem(final B item) {
		this.beanItem = item;
		initLayout();
		previewLayout.setTitle(initFormTitle());

		previewForm.setFormLayoutFactory(initFormLayoutFactory());
		previewForm.setBeanFormFieldFactory(initBeanFormFieldFactory());
		previewForm.setBean(item);

		onPreviewItem();
	}

	public B getBeanItem() {
		return beanItem;
	}

	public AdvancedPreviewBeanForm<B> getPreviewForm() {
		return previewForm;
	}

	protected void addLayoutStyleName(String styleName) {
		previewLayout.addTitleStyleName(styleName);
	}

	protected void removeLayoutStyleName(String styleName) {
		previewLayout.removeTitleStyleName(styleName);
	}

	@Override
	public ComponentContainer getWidget() {
		return this;
	}

	@Override
	public void addViewListener(
			ApplicationEventListener<? extends ApplicationEvent> listener) {

	}

	abstract protected void onPreviewItem();

	abstract protected String initFormTitle();

	abstract protected AdvancedPreviewBeanForm<B> initPreviewForm();

	abstract protected IFormLayoutFactory initFormLayoutFactory();

	abstract protected AbstractBeanFieldGroupViewFieldFactory<B> initBeanFormFieldFactory();

	abstract protected ComponentContainer createButtonControls();

	abstract protected ComponentContainer createBottomPanel();
}
