package com.esofthead.mycollab.vaadin.mvp;

import com.esofthead.mycollab.module.crm.view.CrmModule;
import com.esofthead.mycollab.module.file.view.FileModule;
import com.esofthead.mycollab.module.project.view.ProjectModule;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountModule;
import com.esofthead.mycollab.web.AppContext;

public class ModuleHelper {
	public static final String CURRENT_MODULE = "currentModule";

	public static void setCurrentModule(IModule module) {
		AppContext.putVariable(CURRENT_MODULE, module);
	}

	public static IModule getCurrentModule() {
		return (IModule) AppContext.getVariable(CURRENT_MODULE);
	}

	public static boolean isCurrentProjectModule() {
		IModule module = getCurrentModule();
		return (module != null) && (module instanceof ProjectModule);
	}

	public static boolean isCurrentCrmModule() {
		IModule module = getCurrentModule();
		return (module != null) && (module instanceof CrmModule);
	}

	public static boolean isCurrentFileModule() {
		IModule module = getCurrentModule();
		return (module != null) && (module instanceof FileModule);
	}

	public static boolean isCurrentAccountModule() {
		IModule module = getCurrentModule();
		return (module != null) && (module instanceof AccountModule);
	}
}
