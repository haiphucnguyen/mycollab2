package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.PreviewFormHandlers;

public class CampaignReadPresenter  extends CrmGenericPresenter<CampaignReadView> {

	public CampaignReadPresenter(CampaignReadView view) {
		this.view = view;
		bind();
	}

	@SuppressWarnings("unchecked")
	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new PreviewFormHandlers<Campaign>() {

					@Override
					public void onEdit(Campaign data) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onDelete(Campaign data) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onClone(Campaign data) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onCancel() {
						// TODO Auto-generated method stub

					}
				});
	}
}
