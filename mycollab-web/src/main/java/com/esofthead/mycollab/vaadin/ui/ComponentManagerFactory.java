package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.common.ui.components.AbstractCloudDriveOAuthWindow;
import com.esofthead.mycollab.core.MyCollabException;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public class ComponentManagerFactory {
	private static final String oauthWindowImplCls[] = new String[] {
			"com.esofthead.mycollab.ondemand.module.file.view",
			"com.esofthead.mycollab.module.file.view.DropBoxOAuthWindow" };

	@SuppressWarnings("unchecked")
	public static AbstractCloudDriveOAuthWindow getCloudDriveOAuthWindow(
			String title) {
		for (String clsName : oauthWindowImplCls) {

			Class<AbstractCloudDriveOAuthWindow> cls;
			try {
				cls = (Class<AbstractCloudDriveOAuthWindow>) Class
						.forName(clsName);
			} catch (ClassNotFoundException e) {
				continue;
			}
			if (cls != null) {
				try {
					AbstractCloudDriveOAuthWindow newInstance = cls
							.newInstance();
					newInstance.setCaption(title);
					return newInstance;
				} catch (Exception e) {
					throw new MyCollabException(e);
				}
			}
		}

		throw new MyCollabException("Can not create the oauth window");
	}
}
