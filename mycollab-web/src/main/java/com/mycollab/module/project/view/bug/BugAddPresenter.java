package com.mycollab.module.project.view.bug;

import com.google.common.eventbus.AsyncEventBus;
import com.mycollab.cache.CleanCacheEvent;
import com.mycollab.common.domain.MonitorItem;
import com.mycollab.common.service.MonitorItemService;
import com.mycollab.core.SecureAccessException;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.file.AttachmentUtils;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.event.BugEvent;
import com.mycollab.module.project.event.TicketEvent;
import com.mycollab.module.project.i18n.OptionI18nEnum.BugStatus;
import com.mycollab.module.project.view.ProjectBreadcrumb;
import com.mycollab.module.project.view.ProjectGenericPresenter;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.module.tracker.service.BugRelatedItemService;
import com.mycollab.module.tracker.service.BugService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.IEditFormHandler;
import com.mycollab.vaadin.mvp.LoadPolicy;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.mvp.ViewScope;
import com.mycollab.vaadin.web.ui.field.AttachmentUploadField;
import com.vaadin.ui.HasComponents;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
public class BugAddPresenter extends ProjectGenericPresenter<BugAddView> {
    private static final long serialVersionUID = 1L;

    public BugAddPresenter() {
        super(BugAddView.class);
    }

    @Override
    protected void postInitView() {
        view.getEditFormHandlers().addFormHandler(new IEditFormHandler<SimpleBug>() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSave(SimpleBug bug) {
                int bugId = saveBug(bug);
                EventBusFactory.getInstance().post(new BugEvent.GotoRead(this, bugId));
            }

            @Override
            public void onCancel() {
                EventBusFactory.getInstance().post(new TicketEvent.GotoDashboard(this, null));
            }

            @Override
            public void onSaveAndNew(SimpleBug bug) {
                saveBug(bug);
                EventBusFactory.getInstance().post(new BugEvent.GotoAdd(this, null));
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.INSTANCE.getBUGS())) {
            BugContainer bugContainer = (BugContainer) container;
            bugContainer.removeAllComponents();
            bugContainer.addComponent(view);

            SimpleBug bug = (SimpleBug) data.getParams();
            view.editItem(bug);

            ProjectBreadcrumb breadcrumb = ViewManager.getCacheComponent(ProjectBreadcrumb.class);
            if (bug.getId() == null) {
                breadcrumb.gotoBugAdd();
            } else {
                breadcrumb.gotoBugEdit(bug);
            }
        } else {
            throw new SecureAccessException();
        }
    }

    private int saveBug(SimpleBug bug) {
        BugService bugService = AppContextUtil.getSpringBean(BugService.class);
        bug.setProjectid(CurrentProjectVariables.getProjectId());
        bug.setSaccountid(AppUI.getAccountId());
        AsyncEventBus asyncEventBus = AppContextUtil.getSpringBean(AsyncEventBus.class);
        if (bug.getId() == null) {
            bug.setStatus(BugStatus.Open.name());
            bug.setCreateduser(UserUIContext.getUsername());
            int bugId = bugService.saveWithSession(bug, UserUIContext.getUsername());
            AttachmentUploadField uploadField = view.getAttachUploadField();
            String attachPath = AttachmentUtils.INSTANCE.getProjectEntityAttachmentPath(AppUI.getAccountId(), bug.getProjectid(),
                    ProjectTypeConstants.INSTANCE.getBUG(), "" + bugId);
            uploadField.saveContentsToRepo(attachPath);

            // save component
            BugRelatedItemService bugRelatedItemService = AppContextUtil.getSpringBean(BugRelatedItemService.class);
            bugRelatedItemService.saveAffectedVersionsOfBug(bugId, view.getAffectedVersions());
            bugRelatedItemService.saveFixedVersionsOfBug(bugId, view.getFixedVersion());
            bugRelatedItemService.saveComponentsOfBug(bugId, view.getComponents());
            asyncEventBus.post(new CleanCacheEvent(AppUI.getAccountId(), new Class[]{BugService.class}));

            List<String> followers = view.getFollowers();
            if (followers.size() > 0) {
                List<MonitorItem> monitorItems = new ArrayList<>();
                for (String follower : followers) {
                    MonitorItem monitorItem = new MonitorItem();
                    monitorItem.setMonitorDate(new GregorianCalendar().getTime());
                    monitorItem.setSaccountid(AppUI.getAccountId());
                    monitorItem.setType(ProjectTypeConstants.INSTANCE.getBUG());
                    monitorItem.setTypeid(bugId);
                    monitorItem.setUser(follower);
                    monitorItem.setExtratypeid(CurrentProjectVariables.getProjectId());
                    monitorItems.add(monitorItem);
                }
                MonitorItemService monitorItemService = AppContextUtil.getSpringBean(MonitorItemService.class);
                monitorItemService.saveMonitorItems(monitorItems);
            }
        } else {
            bugService.updateWithSession(bug, UserUIContext.getUsername());
            AttachmentUploadField uploadField = view.getAttachUploadField();
            String attachPath = AttachmentUtils.INSTANCE.getProjectEntityAttachmentPath(AppUI.getAccountId(), bug.getProjectid(),
                    ProjectTypeConstants.INSTANCE.getBUG(), "" + bug.getId());
            uploadField.saveContentsToRepo(attachPath);

            int bugId = bug.getId();
            BugRelatedItemService bugRelatedItemService = AppContextUtil.getSpringBean(BugRelatedItemService.class);
            bugRelatedItemService.updateAffectedVersionsOfBug(bugId, view.getAffectedVersions());
            bugRelatedItemService.updateFixedVersionsOfBug(bugId, view.getFixedVersion());
            bugRelatedItemService.updateComponentsOfBug(bugId, view.getComponents());
            asyncEventBus.post(new CleanCacheEvent(AppUI.getAccountId(), new Class[]{BugService.class}));
        }

        return bug.getId();
    }
}