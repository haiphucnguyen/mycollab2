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

package com.esofthead.mycollab.module.project.view.task;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public abstract class TaskGroupFormLayoutFactory implements IFormLayoutFactory {
	private static final long serialVersionUID = 1L;
	private final String title;
	private TaskListInformationLayout informationLayout;
	private final List<String> lstStyleTitle = new ArrayList<String>();

	public TaskGroupFormLayoutFactory(final String title) {
		this.title = title;
	}

	@Override
	public Layout getLayout() {
		final AddViewLayout accountAddLayout = new AddViewLayout(this.title,
				MyCollabResource.newResource("icons/24/project/task.png"));

		for (int i = 0; i < this.lstStyleTitle.size(); i++) {
			accountAddLayout.addTitleStyleName(this.lstStyleTitle.get(i));
		}

		final Layout topPanel = this.createTopPanel();
		if (topPanel != null) {
			accountAddLayout.addTopControls(topPanel);
		}

		this.informationLayout = new TaskListInformationLayout();
		accountAddLayout.addBody(this.informationLayout.getLayout());

		final Layout bottomPanel = this.createBottomPanel();
		if (bottomPanel != null) {
			accountAddLayout.addBottomControls(bottomPanel);
		}

		return accountAddLayout;
	}

	protected void addTitleStyle(final String styleName) {
		this.lstStyleTitle.add(styleName);
	}

	@Override
	public void attachField(final Object propertyId, final Field field) {
		this.informationLayout.attachField(propertyId, field);
	}

	protected abstract Layout createTopPanel();

	protected abstract Layout createBottomPanel();

	@SuppressWarnings("serial")
	public static class TaskListInformationLayout implements IFormLayoutFactory {

		private GridFormLayoutHelper informationLayout;

		@Override
		public Layout getLayout() {
			this.informationLayout = new GridFormLayoutHelper(2, 4, "100%",
					"180px", Alignment.MIDDLE_LEFT);
			this.informationLayout.getLayout().addStyleName(
					"colored-gridlayout");
			this.informationLayout.getLayout().setMargin(false);
			this.informationLayout.getLayout().setWidth("100%");
			final VerticalLayout layout = new VerticalLayout();
			layout.addComponent(this.informationLayout.getLayout());
			layout.setComponentAlignment(this.informationLayout.getLayout(),
					Alignment.BOTTOM_CENTER);
			return layout;
		}

		@Override
		public void attachField(final Object propertyId, final Field field) {
			if (propertyId.equals("name")) {
				this.informationLayout.addComponent(field, "Name", 0, 0, 2,
						"100%");
			} else if (propertyId.equals("description")) {
				this.informationLayout.addComponent(field, "Description", 0, 1,
						2, "100%");
			} else if (propertyId.equals("owner")) {
				this.informationLayout.addComponent(field, LocalizationHelper
						.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD), 0, 2);
			} else if (propertyId.equals("milestoneid")) {
				this.informationLayout.addComponent(field, "Related Milestone",
						1, 2);
			} else if (propertyId.equals("percentageComplete")) {
				this.informationLayout.addComponent(field, "Progress", 0, 3);
			} else if (propertyId.equals("numOpenTasks")) {
				this.informationLayout.addComponent(field,
						"Number of open tasks", 1, 3);
			}
		}
	}
}
