package com.esofthead.mycollab.community.module.crm.view.setting;

import java.util.List;

import com.esofthead.mycollab.community.view.NotPresentedView;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.module.crm.view.setting.ICrmCustomView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class CrmCustomViewImpl extends NotPresentedView implements
		ICrmCustomView {
	private static final long serialVersionUID = 1L;

	@Override
	public void display(String moduleName) {

	}

	@Override
	public void addActiveSection(DynaSection section) {

	}

	@Override
	public List<DynaSection> getActiveSections() {
		return null;
	}

	@Override
	public String getCandidateTextFieldName() {
		return null;
	}

	@Override
	public void refreshSectionLayout(DynaSection section) {

	}

}
