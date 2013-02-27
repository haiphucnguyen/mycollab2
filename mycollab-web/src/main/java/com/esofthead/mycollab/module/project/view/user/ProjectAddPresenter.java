/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.ProjectPageAction;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectAddPresenter extends AbstractPresenter<ProjectAddView> {
	private static final long serialVersionUID = 1L;

	public ProjectAddPresenter() {
		super(ProjectAddView.class);
		bind();
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		MyProjectsContainer projectContainer = (MyProjectsContainer) container;
		projectContainer.removeAllComponents();
		projectContainer.addComponent(view.getWidget());
		view.editItem((Project) data.getParams());
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<Project>() {
					@Override
					public void onSave(final Project project) {
						saveProject(project);
						EventBus.getInstance().fireEvent(
								new ProjectEvent.GotoMyProject(this,
										new PageActionChain(
												new ProjectPageAction(
														new ScreenData(project
																.getId())))));
					}

					@Override
					public void onCancel() {
						if (view.getItem().getId() == null) {
							EventBus.getInstance().fireEvent(
									new ShellEvent.GotoProjectPage(
											ProjectAddPresenter.this, null));
						} else {
							EventBus.getInstance()
									.fireEvent(
											new ProjectEvent.GotoMyProject(
													this,
													new PageActionChain(
															new ProjectPageAction(
																	new ScreenData(
																			view.getItem()
																					.getId())))));
						}
					}

					@Override
					public void onSaveAndNew(final Project project) {
						saveProject(project);
						EventBus.getInstance().fireEvent(
								new ProjectEvent.GotoAdd(this, null));
					}
				});
	}

	public void saveProject(Project project) {
		ProjectService projectService = AppContext
				.getSpringBean(ProjectService.class);
		project.setSaccountid(AppContext.getAccountId());

		if (project.getId() == null) {
			projectService.saveWithSession(project, AppContext.getUsername());
		} else {
			projectService.updateWithSession(project, AppContext.getUsername());
			AppContext.putVariable(ProjectContants.CURRENT_PROJECT, project);
		}

	}
}
