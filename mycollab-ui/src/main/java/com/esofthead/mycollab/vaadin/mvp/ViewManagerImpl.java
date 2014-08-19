/**
 * This file is part of mycollab-ui.
 *
 * mycollab-ui is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-ui is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-ui.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.esofthead.mycollab.vaadin.mvp;

import static com.esofthead.mycollab.common.MyCollabSession.VIEW_MANAGER_VAL;

import java.util.HashMap;
import java.util.Map;

import com.esofthead.mycollab.common.MyCollabSession;
import com.esofthead.mycollab.core.MyCollabException;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
class ViewManagerImpl extends ViewManager {

	@SuppressWarnings("unchecked")
	@Override
	protected <T extends CacheableComponent> T getViewInstance(
			final Class<T> viewClass) {

		Map<Class<?>, Object> viewMap = (Map<Class<?>, Object>) MyCollabSession
				.getVariable(VIEW_MANAGER_VAL);
		if (viewMap == null) {
			viewMap = new HashMap<Class<?>, Object>();
			MyCollabSession.putVariable(VIEW_MANAGER_VAL, viewMap);
		}

		Class<?> implCls = getImplCls(viewClass);
		if (implCls != null) {
			ViewComponent annotation = implCls
					.getAnnotation(ViewComponent.class);
			ViewScope scope = annotation.scope();

			try {
				if (scope == ViewScope.PROTOTYPE) {
					return (T) implCls.newInstance();
				} else {
					T value = (T) viewMap.get(viewClass);
					if (value == null) {
						value = (T) implCls.newInstance();
						viewMap.put(viewClass, value);
					}
					return value;
				}
			} catch (Exception e) {
				throw new MyCollabException("Can not create view: "
						+ viewClass.getName(), e);
			}
		} else {
			throw new MyCollabException(
					"Can not find the implementation class of view: "
							+ viewClass.getName());
		}
	}

	private Class<?> getImplCls(Class<?> viewClass) {
		for (Class<?> classInstance : viewClasses) {
			if (viewClass.isAssignableFrom(classInstance)) {
				return classInstance;
			}
		}
		return null;
	}

	@Override
	protected void clearViews() {
		MyCollabSession.removeVariable(VIEW_MANAGER_VAL);
	}
}
