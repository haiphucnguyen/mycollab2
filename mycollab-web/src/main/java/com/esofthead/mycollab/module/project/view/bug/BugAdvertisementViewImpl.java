package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.GenericForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

@ViewComponent
public class BugAdvertisementViewImpl extends AbstractView implements
		BugAdvertisementView {
	private static final long serialVersionUID = 1L;
	private final GenericForm form;

	public BugAdvertisementViewImpl() {
		form = new GenericForm();
		form.setFormLayoutFactory(new BugFormLayoutFactory());
		this.addComponent(form);
	}
	
	private static class BugFormLayoutFactory implements IFormLayoutFactory {
		private static final long serialVersionUID = 1L;
		private CustomLayout bugFormLayout;

		@Override
		public Layout getLayout() {
			bugFormLayout = new CustomLayout("ads/bug/bugAds");
			return bugFormLayout;
		}

		@Override
		public void attachField(Object propertyId, Field field) {
			
		}

	}
}
