/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.vaadin.mvp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.web.MyCollabApplication;

public class PresenterResolver {

	private static final String PRESENTER_VAL = "presenterMap";

	private static Logger log = LoggerFactory
			.getLogger(PresenterResolver.class);

	protected static Set<Class<? extends IPresenter>> presenterClasses;

	static {
		log.debug("Scan presenter implementation");
		Reflections reflections = new Reflections("com.esofthead.mycollab");
		presenterClasses = reflections.getSubTypesOf(IPresenter.class);
	}

	public static void init() {

	}

	@SuppressWarnings("unchecked")
	public static <P extends IPresenter> P getPresenter(Class<P> presenterClass) {
		Map<Class<?>, Object> presenterMap = (Map<Class<?>, Object>) MyCollabApplication
				.getVariable(PRESENTER_VAL);
		if (presenterMap == null) {
			presenterMap = new HashMap<Class<?>, Object>();
			MyCollabApplication.putVariable(PRESENTER_VAL, presenterMap);
		}

		P value = (P) presenterMap.get(presenterClass);
		if (value == null) {
			try {
				if (!presenterClass.isInterface()) {
					value = (P) presenterClass.newInstance();
				} else {
					for (Class<?> classInstance : presenterClasses) {
						if (presenterClass.isAssignableFrom(classInstance)
								&& !classInstance.isInterface()) {

							value = (P) classInstance.newInstance();
							log.debug("Get implementation of presenter "
									+ presenterClass.getName() + " is "
									+ value.getClass().getName());
						}
					}
				}

				presenterMap.put(presenterClass, value);
				return value;
			} catch (Exception e) {
				throw new MyCollabException(e);
			}
		} else {
			return value;
		}
	}
}
