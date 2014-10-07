package com.esofthead.mycollab.mobile.module.project.view.bug;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.mobile.module.project.ui.InsideProjectNavigationMenu;
import com.esofthead.mycollab.mobile.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.mobile.mvp.AbstractPresenter;
import com.esofthead.mycollab.mobile.ui.AbstractMobilePresenter;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.vaadin.mobilecomponent.MobileNavigationManager;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 * 
 */
public class BugPresenter extends AbstractMobilePresenter<BugContainer> {

	private static final long serialVersionUID = -7398666868034973815L;

	public BugPresenter() {
		super(BugContainer.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		InsideProjectNavigationMenu projectModuleMenu = (InsideProjectNavigationMenu) ((MobileNavigationManager) UI
				.getCurrent().getContent()).getNavigationMenu();
		projectModuleMenu.selectButton(AppContext
				.getMessage(ProjectCommonI18nEnum.VIEW_BUG));

		AbstractPresenter<?> presenter = null;

		if (data instanceof BugScreenData.Search) {
			presenter = PresenterResolver.getPresenter(BugListPresenter.class);
			// } else if (data instanceof BugScreenData.Add
			// || data instanceof BugScreenData.Edit) {
			// presenter =
			// PresenterResolver.getPresenter(BugAddPresenter.class);
			// } else if (data instanceof BugScreenData.Read) {
			// presenter =
			// PresenterResolver.getPresenter(BugReadPresenter.class);
			// } else if (data == null) {
			// BugSearchCriteria criteria = new BugSearchCriteria();
			// criteria.setProjectId(new
			// NumberSearchField(CurrentProjectVariables
			// .getProjectId()));
			// data = new BugScreenData.Search(new
			// BugFilterParameter("All Bugs",
			// criteria));

		} else {
			throw new MyCollabException("Do not support screen data");
		}

		presenter.go(view, data);
	}

}
