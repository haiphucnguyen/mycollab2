/**
 * Engroup - Enterprise Groupware Platform
 * Copyright (C) 2007-2009 eSoftHead Company <engroup@esofthead.com>
 * http://www.esofthead.com
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.esofthead.mycollab.core;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public abstract class PlatformManager {
	private static PlatformManager instance;

	private static PlatformManagerSynchronizer synchronizer = new PlatformManagerSynchronizer();

	public static String PLATFORM_MANAGER_IMPLEMENTATION_CLASS = "com.engroup.platform.implementationClass";

	private Map<String, String> variables = new HashMap<String, String>();

	public Map<String, String> getVariables() {
		return variables;
	}

	/**
	 * 
	 * @param userSessionId
	 * @return
	 */
	public abstract Session getSession(String userSessionId);
	
	/**
	 * 
	 * @param userSessionId
	 * @return
	 */
	public abstract String getUsername(String userSessionId);

	/**
	 * 
	 * @param userSessionId
	 * @param appId
	 */
	public abstract void holdUserSessionExpiried(String userSessionId,
			String appId);

	/**
	 * 
	 * @param userSessionId
	 * @param appId
	 */
	public abstract void releaseUserSessionExpired(String userSessionId,
			String appId);

	public static PlatformManager getInstance() {
		synchronized (synchronizer) {
			if (!synchronizer.isInitialized) {
				if (System.getProperty(PLATFORM_MANAGER_IMPLEMENTATION_CLASS) != "") {
					String implClass = System
							.getProperty(PLATFORM_MANAGER_IMPLEMENTATION_CLASS);
					try {
						instance = (PlatformManager) Class.forName(implClass)
								.newInstance();

					} catch (Exception e) {
						instance = new PlatformManagerImpl();
					}
				} else {
					instance = new PlatformManagerImpl();
				}
				synchronizer.isInitialized = true;
			}
		}
		return instance;
	}

	static class PlatformManagerSynchronizer {
		private boolean isInitialized = false;

		PlatformManagerSynchronizer() {
		}
	}
}
