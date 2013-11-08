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

import java.util.Set;

import org.reflections.Reflections;

import com.esofthead.mycollab.vaadin.ui.ViewComponent;

public abstract class ViewManager {

	private static ViewManager impl = new ViewManagerImpl();

	protected static Set<Class<?>> viewClasses;

	static {
		Reflections reflections = new Reflections("com.esofthead.mycollab");
		viewClasses = reflections.getTypesAnnotatedWith(ViewComponent.class);
	}

	protected abstract <T extends View> T getViewInstance(
			final Class<T> viewClass);

	public static void init() {

	}

	public static <T extends View> T getView(final Class<T> viewClass) {
		return impl.getViewInstance(viewClass);
	}
}
