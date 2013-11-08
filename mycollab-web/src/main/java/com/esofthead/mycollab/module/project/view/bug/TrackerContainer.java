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
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.BugSearchParameter;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

@ViewComponent
public class TrackerContainer extends AbstractView {

	private static final long serialVersionUID = 1L;

	private BugDashboardPresenter dashboardPresenter;

	private BugPresenter bugPresenter;

	private ComponentPresenter componentPresenter;

	private VersionPresenter versionPresenter;

	private final DetachedTabs myProjectTab;
	private final CssLayout mySpaceArea = new CssLayout();

	public TrackerContainer() {

		this.myProjectTab = new DetachedTabs.Horizontal(this.mySpaceArea);
		this.myProjectTab.setSizeUndefined();

		final HorizontalLayout menu = new HorizontalLayout();
		menu.setWidth("100%");
		menu.setStyleName(UIConstants.THEME_TAB_STYLE3);
		menu.setHeight("40px");
		menu.addComponent(this.myProjectTab);

		this.addComponent(menu);
		this.mySpaceArea.setWidth("100%");
		this.mySpaceArea.setHeight(null);
		this.mySpaceArea.addStyleName("usergroup-view");
		this.addComponent(this.mySpaceArea);
		this.setExpandRatio(this.mySpaceArea, 1.0f);
		this.setWidth("100%");
		this.setMargin(true);
		this.buildComponents();
	}

	private void buildComponents() {
		dashboardPresenter = PresenterResolver
				.getPresenter(BugDashboardPresenter.class);

		this.myProjectTab
				.addTab(this.dashboardPresenter.getView(), "Dashboard");

		bugPresenter = PresenterResolver.getPresenter(BugPresenter.class);
		this.myProjectTab.addTab(bugPresenter.getView(), "Bugs");

		componentPresenter = PresenterResolver
				.getPresenter(ComponentPresenter.class);
		this.myProjectTab.addTab(componentPresenter.getView(), "Components");

		versionPresenter = PresenterResolver
				.getPresenter(VersionPresenter.class);
		this.myProjectTab.addTab(versionPresenter.getView(), "Versions");

		this.myProjectTab
				.addTabChangedListener(new DetachedTabs.TabChangedListener() {
					@Override
					public void tabChanged(
							final DetachedTabs.TabChangedEvent event) {
						final Button btn = event.getSource();
						final String caption = btn.getCaption();
						final SimpleProject project = CurrentProjectVariables
								.getProject();

						if ("Dashboard".equals(caption)) {
							dashboardPresenter.go(TrackerContainer.this, null);
						} else if ("Bugs".equals(caption)) {
							final BugSearchCriteria criteria = new BugSearchCriteria();
							criteria.setProjectId(new NumberSearchField(project
									.getId()));
							bugPresenter.go(TrackerContainer.this,
									new BugScreenData.Search(
											new BugSearchParameter("All Bugs",
													criteria)));
						} else if ("Components".equals(caption)) {
							componentPresenter.go(TrackerContainer.this, null);
						} else if ("Versions".equals(caption)) {
							versionPresenter.go(TrackerContainer.this, null);
						}
					}
				});
	}

	public Component gotoSubView(final String name) {
		final View component = (View) this.myProjectTab.selectTab(name);
		return component;
	}
}
