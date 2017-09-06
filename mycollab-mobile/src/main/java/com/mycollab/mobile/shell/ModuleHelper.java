package com.mycollab.mobile.shell;

import com.mycollab.mobile.module.crm.view.CrmModule;
import com.mycollab.mobile.module.project.view.ProjectModule;
import com.mycollab.vaadin.mvp.IModule;
import com.mycollab.vaadin.ui.MyCollabSession;

import static com.mycollab.vaadin.ui.MyCollabSession.CURRENT_MODULE;

/**
 * @author MyCollab Ltd.
 * @since 4.4.0
 */
public class ModuleHelper {

    public static void setCurrentModule(IModule module) {
        MyCollabSession.putCurrentUIVariable(CURRENT_MODULE, module);
    }

    public static IModule getCurrentModule() {
        return (IModule) MyCollabSession.getCurrentUIVariable(CURRENT_MODULE);
    }

    public static boolean isCurrentProjectModule() {
        IModule module = getCurrentModule();
        return (module != null) && (module instanceof ProjectModule);
    }

    public static boolean isCurrentCrmModule() {
        IModule module = getCurrentModule();
        return (module != null) && (module instanceof CrmModule);
    }
}
