package com.esofthead.mycollab.vaadin;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.vaadin.server.BootstrapFragmentResponse;
import com.vaadin.server.BootstrapListener;
import com.vaadin.server.BootstrapPageResponse;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class MyCollabBootstrapListener implements BootstrapListener {
	private static final long serialVersionUID = 1L;

	@Override
	public void modifyBootstrapFragment(BootstrapFragmentResponse response) {
	}

	@Override
	public void modifyBootstrapPage(BootstrapPageResponse response) {
		response.getDocument().head()
				.append("<meta name=\"robots\" content=\"nofollow\" />");
		response.getDocument()
				.head()
				.append("<script type=\"text/javascript\" src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\"></script>");

		DeploymentMode deploymentMode = SiteConfiguration.getDeploymentMode();
		if (deploymentMode == DeploymentMode.SITE) {
			response.getDocument()
					.head()
					.append("<script type=\"text/javascript\" src=\"https://s3.amazonaws.com/mycollab_assets/assets/js/stickytooltip.js\"></script>");
		} else {
			response.getDocument()
					.head()
					.append("<script type=\"text/javascript\" src=\"/assets/js/stickytooltip.js\"></script>");
		}
	}

}
