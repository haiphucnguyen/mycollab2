/**
 * This file is part of mycollab-web-premium.
 *
 * mycollab-web-premium is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web-premium is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web-premium.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.module.user.accountsettings.customize.view.ICustomizeContainer;
import com.esofthead.mycollab.module.user.accountsettings.localization.UserI18nEnum;
import com.esofthead.mycollab.module.user.domain.AccountTheme;
import com.esofthead.mycollab.module.user.service.AccountThemeService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout2;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ColorPickerArea;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1
 * 
 */
@ViewComponent
public class CustomizeContainer extends AbstractPageView implements
ICustomizeContainer {
	private static final long serialVersionUID = 3149432226222905621L;

	private final AccountThemeService themeService;
	private final AccountTheme defaultTheme;

	public CustomizeContainer() {
		super();
		themeService = ApplicationContextUtil
				.getSpringBean(AccountThemeService.class);
		defaultTheme = themeService.getDefaultTheme();

		initUI();
	}

	protected void initUI() {
		this.setMargin(new MarginInfo(false, true, true, true));

		AddViewLayout2 mainLayout = new AddViewLayout2(
				AppContext.getMessage(UserI18nEnum.CUSTOMIZE_VIEW),
				MyCollabResource.newResource("icons/24/user/customize.png"));
		mainLayout.setWidth("100%");
		mainLayout.setStyleName("readview-layout");

		VerticalLayout mainBody = new VerticalLayout();
		mainBody.setSpacing(true);
		mainBody.addComponent(constructTopMenuCustomizeBlock());

		mainLayout.addBody(mainBody);
		this.addComponent(mainLayout);
	}

	private Component constructTopMenuCustomizeBlock() {
		VerticalLayout blockLayout = new VerticalLayout();
		Label blockHeader = new Label("Top Menu");
		blockHeader.setStyleName("block-hdr");
		blockHeader.addStyleName("h2");
		blockLayout.addComponent(blockHeader);
		blockLayout.setSpacing(true);

		VerticalLayout blockBody = new VerticalLayout();
		blockBody.setMargin(new MarginInfo(false, true, false, true));
		blockLayout.addComponent(blockBody);

		if (defaultTheme != null) {
			GridLayout propertyLayout = new GridLayout(2, 4);
			// propertyLayout.setWidth("100%");
			propertyLayout.setSpacing(true);
			propertyLayout.setStyleName("no-border");
			propertyLayout.setColumnExpandRatio(0, 1.0f);
			propertyLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

			ColorPickerArea topMenuBg = new ColorPickerArea(
					"Normal Tab",
					new Color(Integer.parseInt(defaultTheme.getTopmenubg(), 16)));
			topMenuBg.setWidth("55px");
			topMenuBg.setHeight("25px");
			propertyLayout.addComponent(new Label("Normal Tab"), 0, 0);
			propertyLayout.addComponent(topMenuBg, 1, 0);

			ColorPickerArea topMenuText = new ColorPickerArea(
					"Normal Tab Text", new Color(Integer.parseInt(
							defaultTheme.getTopmenutext(), 16)));
			topMenuText.setWidth("55px");
			topMenuText.setHeight("25px");
			propertyLayout.addComponent(new Label("Normal Tab Text"), 0, 1);
			propertyLayout.addComponent(topMenuText, 1, 1);

			ColorPickerArea topMenuBgSelected = new ColorPickerArea(
					"Selected Tab", new Color(Integer.parseInt(
							defaultTheme.getTopmenubgselected(), 16)));
			topMenuBgSelected.setWidth("55px");
			topMenuBgSelected.setHeight("25px");
			propertyLayout.addComponent(new Label("Selected Tab"), 0, 2);
			propertyLayout.addComponent(topMenuBgSelected, 1, 2);

			ColorPickerArea topMenuTextSelected = new ColorPickerArea(
					"Selected Tab Text", new Color(Integer.parseInt(
							defaultTheme.getTopmenutextselected(), 16)));
			topMenuTextSelected.setWidth("55px");
			topMenuTextSelected.setHeight("25px");
			propertyLayout.addComponent(new Label("Selected Tab Text"), 0, 3);
			propertyLayout.addComponent(topMenuTextSelected, 1, 3);

			blockBody.addComponent(propertyLayout);
		}
		return blockLayout;
	}
}
