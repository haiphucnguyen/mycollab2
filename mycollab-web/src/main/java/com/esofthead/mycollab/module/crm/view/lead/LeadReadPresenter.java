package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.PreviewFormHandlers;

public class LeadReadPresenter extends CrmGenericPresenter<LeadReadView> {

	public LeadReadPresenter(LeadReadView view) {
		this.view = view;
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new PreviewFormHandlers<Lead>() {

					@Override
					public void onEdit(Lead data) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onDelete(Lead data) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onClone(Lead data) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onCancel() {
						// TODO Auto-generated method stub

					}
				});
	}

}
