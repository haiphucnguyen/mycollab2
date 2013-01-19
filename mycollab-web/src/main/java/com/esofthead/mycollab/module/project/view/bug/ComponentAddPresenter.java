/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class ComponentAddPresenter extends AbstractPresenter<ComponentAddView> {

    public ComponentAddPresenter() {
        super(ComponentAddView.class);

        view.getEditFormHandlers().addFormHandler(new EditFormHandler<Component>() {
            @Override
            public void onSave(final Component item) {
                save(item);
                ViewState viewState = HistoryViewManager.back();
                if (viewState instanceof NullViewState) {
                    EventBus.getInstance().fireEvent(
                            new BugComponentEvent.GotoList(this, null));
                }
            }

            @Override
            public void onCancel() {
                ViewState viewState = HistoryViewManager.back();
                if (viewState instanceof NullViewState) {
                    EventBus.getInstance().fireEvent(
                            new BugComponentEvent.GotoList(this, null));
                }
            }

            @Override
            public void onSaveAndNew(final Component item) {
                save(item);
                EventBus.getInstance().fireEvent(
                        new BugComponentEvent.GotoAdd(this, null));
            }
        });
    }

    public void save(Component item) {
        ComponentService componentService = AppContext.getSpringBean(ComponentService.class);

        SimpleProject project = (SimpleProject) AppContext.getVariable(ProjectContants.PROJECT_NAME);
        item.setSaccountid(AppContext.getAccountId());
        item.setProjectid(project.getId());

        if (item.getId() == null) {
            componentService.saveWithSession(item, AppContext.getUsername());
        } else {
            componentService.updateWithSession(item, AppContext.getUsername());
        }
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        BugContainer bugContainer = (BugContainer) container;
        bugContainer.addComponent(view.getWidget());

        Component component = (Component) data.getParams();
        view.editItem(component);
        
        ProjectBreadcrumb breadcrumb = ViewManager.getView(ProjectBreadcrumb.class);
        
        if (component.getId() == null) {
            breadcrumb.gotoComponentAdd();
        } else {
            breadcrumb.gotoComponentEdit(component);
        }
    }
}
