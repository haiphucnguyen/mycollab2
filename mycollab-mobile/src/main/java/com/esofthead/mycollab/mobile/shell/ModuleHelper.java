package com.esofthead.mycollab.mobile.shell;

import static com.esofthead.mycollab.common.MyCollabSession.CURRENT_MODULE;

import com.esofthead.mycollab.common.MyCollabSession;
import com.esofthead.mycollab.mobile.module.crm.view.CrmModule;
import com.esofthead.mycollab.mobile.module.project.view.ProjectModule;
import com.esofthead.mycollab.vaadin.mvp.IModule;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.4.0
 *
 */
public class ModuleHelper {

	public static void setCurrentModule(IModule module) {
		MyCollabSession.putVariable(CURRENT_MODULE, module);
	}

	public static IModule getCurrentModule() {
		return (IModule) MyCollabSession.getVariable(CURRENT_MODULE);
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
