package com.esofthead.mycollab.cache;

import com.esofthead.mycollab.configuration.DeploymentMode;
import com.esofthead.mycollab.configuration.SiteConfiguration;

public class L2CacheManager {
	private static L2Cache instance;

	static {
		if (SiteConfiguration.getDeploymentMode() == DeploymentMode.SITE) {
			instance = new MemcacheL2Cache();
		} else {
			instance = new InfinispanL2Cache();
		}
	}

	public static L2Cache getCache() {
		return instance;
	}
}
