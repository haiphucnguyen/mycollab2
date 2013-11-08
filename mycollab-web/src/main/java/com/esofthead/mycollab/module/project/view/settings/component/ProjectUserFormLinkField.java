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
package com.esofthead.mycollab.module.project.view.settings.component;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.vaadin.ui.UIConstants;

public class ProjectUserFormLinkField extends CustomField {
	private static final long serialVersionUID = 1L;

	public ProjectUserFormLinkField(String username, String userAvatarId,
			String displayName) {
		ProjectUserLink l = new ProjectUserLink(username, userAvatarId,
				displayName, true, true);
		l.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
		this.setCompositionRoot(l);
	}

	@Override
	public Class<?> getType() {
		return Object.class;
	}
}
