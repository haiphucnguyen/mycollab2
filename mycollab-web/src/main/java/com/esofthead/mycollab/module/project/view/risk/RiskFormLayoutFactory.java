package com.esofthead.mycollab.module.project.view.risk;

import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

public abstract class RiskFormLayoutFactory implements IFormLayoutFactory{
	private static final long serialVersionUID = 1L;

	@Override
	public Layout getLayout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void attachField(Object propertyId, Field field) {
		// TODO Auto-generated method stub
		
	}
	
	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

}
