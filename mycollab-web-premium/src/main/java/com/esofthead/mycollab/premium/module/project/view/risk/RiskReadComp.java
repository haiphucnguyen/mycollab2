package com.esofthead.mycollab.premium.module.project.view.risk;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class RiskReadComp extends AbstractRiskPreviewComp {
	private static final long serialVersionUID = 1L;

	@Override
	protected AdvancedPreviewBeanForm<SimpleRisk> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleRisk>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void showHistory() {
				final RiskHistoryLogWindow historyLog = new RiskHistoryLogWindow(
						ModuleNameConstants.PRJ, ProjectContants.RISK,
						beanItem.getId());
				UI.getCurrent().addWindow(historyLog);
			}
		};
	}

	@Override
	protected ComponentContainer createButtonControls() {
		return new ProjectPreviewFormControlsGenerator<SimpleRisk>(previewForm)
				.createButtonControls(ProjectRolePermissionCollections.RISKS);
	}
}
