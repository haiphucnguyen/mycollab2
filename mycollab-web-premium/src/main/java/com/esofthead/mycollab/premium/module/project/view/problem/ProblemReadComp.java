package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class ProblemReadComp extends AbstractProblemPreviewComp {
	private static final long serialVersionUID = 1L;

	@Override
	protected AdvancedPreviewBeanForm<SimpleProblem> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleProblem>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void doPrint() {
				// Create a window that contains what you want to print
				final Window window = new Window("Window to Print");

				final ProblemPrintComp printView = new ProblemPrintComp();
				printView.previewItem(beanItem);
				window.setContent(printView);

				UI.getCurrent().addWindow(window);

				// Print automatically when the window opens
				JavaScript.getCurrent().execute(
						"setTimeout(function() {"
								+ "  print(); self.close();}, 0);");
			}

			@Override
			public void showHistory() {
				final ProblemHistoryLogWindow historyLog = new ProblemHistoryLogWindow(
						ModuleNameConstants.PRJ, ProjectContants.PROBLEM,
						beanItem.getId());
				UI.getCurrent().addWindow(historyLog);
			}
		};
	}

	@Override
	protected ComponentContainer createButtonControls() {
		return new ProjectPreviewFormControlsGenerator<SimpleProblem>(
				previewForm)
				.createButtonControls(ProjectRolePermissionCollections.PROBLEMS);
	}

}
