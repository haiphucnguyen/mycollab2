package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.4.0
 *
 */
public class CustomizeUIPresenter extends AbstractPresenter<CustomizeUIView> {
	private static final long serialVersionUID = 1L;

	public CustomizeUIPresenter() {
		super(CustomizeUIView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		UserSettingView userSettingView = (UserSettingView) container;
		userSettingView.gotoSubView(AppContext
				.getMessage(ProjectCommonI18nEnum.VIEW_CUSTOMIZE_UI));

		view.showFeatures();
	}

}
