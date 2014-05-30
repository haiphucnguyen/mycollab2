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

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.user.accountsettings.customize.view.ICustomizeContainer;
import com.esofthead.mycollab.module.user.accountsettings.localization.UserI18nEnum;
import com.esofthead.mycollab.module.user.accountsettings.view.events.AccountCustomizeEvent;
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
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.colorpicker.ColorChangeEvent;
import com.vaadin.ui.components.colorpicker.ColorChangeListener;

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
	private final AccountTheme accountTheme;

	public CustomizeContainer() {
		super();
		themeService = ApplicationContextUtil
				.getSpringBean(AccountThemeService.class);
		accountTheme = themeService.getAccountTheme(AppContext.getAccountId());

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
		mainBody.addComponent(constructTabsheetCustomizeBlock());
		mainBody.addComponent(constructVTabsheetCustomizeBlock());

		HorizontalLayout controlButton = new HorizontalLayout();
		controlButton.setWidth("100%");
		controlButton.setMargin(true);
		controlButton.addComponent(new Button("Save",
				new Button.ClickListener() {
					private static final long serialVersionUID = -6901103392231786935L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new AccountCustomizeEvent.SaveTheme(this,
										accountTheme));
					}
				}));
		mainBody.addComponent(controlButton);

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

		if (accountTheme != null) {
			GridLayout propertyLayout = new GridLayout(2, 4);
			propertyLayout.setSpacing(true);
			propertyLayout.setStyleName("no-border");
			propertyLayout.setColumnExpandRatio(0, 1.0f);
			propertyLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

			CustomColorPickerArea topMenuBg = new CustomColorPickerArea(
					"Normal Tab", new Color(Integer.parseInt(
							accountTheme.getTopmenubg(), 16)));
			topMenuBg.addColorChangeListener(new ColorChangeListener() {
				private static final long serialVersionUID = -3462278784149813444L;

				@Override
				public void colorChanged(ColorChangeEvent event) {
					accountTheme.setTopmenubg(event.getColor().getCSS()
							.substring(1).toUpperCase());
				}
			});
			propertyLayout.addComponent(new Label("Normal Menu"), 0, 0);
			propertyLayout.addComponent(topMenuBg, 1, 0);

			CustomColorPickerArea topMenuText = new CustomColorPickerArea(
					"Normal Tab Text", new Color(Integer.parseInt(
							accountTheme.getTopmenutext(), 16)));
			topMenuText.addColorChangeListener(new ColorChangeListener() {
				private static final long serialVersionUID = -1370026552930193996L;

				@Override
				public void colorChanged(ColorChangeEvent event) {
					accountTheme.setTopmenutext(event.getColor().getCSS()
							.substring(1).toUpperCase());
				}
			});
			propertyLayout.addComponent(new Label("Normal Menu Text"), 0, 1);
			propertyLayout.addComponent(topMenuText, 1, 1);

			CustomColorPickerArea topMenuBgSelected = new CustomColorPickerArea(
					"Selected Tab", new Color(Integer.parseInt(
							accountTheme.getTopmenubgselected(), 16)));
			topMenuBgSelected.addColorChangeListener(new ColorChangeListener() {
				private static final long serialVersionUID = -7897981001643385884L;

				@Override
				public void colorChanged(ColorChangeEvent event) {
					accountTheme.setTopmenubgselected(event.getColor().getCSS()
							.substring(1).toUpperCase());
				}
			});
			propertyLayout.addComponent(new Label("Selected Menu"), 0, 2);
			propertyLayout.addComponent(topMenuBgSelected, 1, 2);

			CustomColorPickerArea topMenuTextSelected = new CustomColorPickerArea(
					"Selected Tab Text", new Color(Integer.parseInt(
							accountTheme.getTopmenutextselected(), 16)));
			topMenuTextSelected
					.addColorChangeListener(new ColorChangeListener() {
						private static final long serialVersionUID = -5381166604049121169L;

						@Override
						public void colorChanged(ColorChangeEvent event) {
							accountTheme.setTopmenutextselected(event
									.getColor().getCSS().substring(1)
									.toUpperCase());
						}
					});
			propertyLayout.addComponent(new Label("Selected Menu Text"), 0, 3);
			propertyLayout.addComponent(topMenuTextSelected, 1, 3);

			blockBody.addComponent(propertyLayout);
		}
		return blockLayout;
	}

	private Component constructTabsheetCustomizeBlock() {
		VerticalLayout blockLayout = new VerticalLayout();
		Label blockHeader = new Label("Tab Sheet");
		blockHeader.setStyleName("block-hdr");
		blockHeader.addStyleName("h2");
		blockLayout.addComponent(blockHeader);
		blockLayout.setSpacing(true);

		VerticalLayout blockBody = new VerticalLayout();
		blockBody.setMargin(new MarginInfo(false, true, false, true));
		blockLayout.addComponent(blockBody);

		if (accountTheme != null) {
			GridLayout propertyLayout = new GridLayout(2, 4);
			propertyLayout.setSpacing(true);
			propertyLayout.setStyleName("no-border");
			propertyLayout.setColumnExpandRatio(0, 1.0f);
			propertyLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

			CustomColorPickerArea tabsheetBg = new CustomColorPickerArea(
					"Normal Tab", new Color(Integer.parseInt(
							accountTheme.getTabsheetbg(), 16)));
			tabsheetBg.addColorChangeListener(new ColorChangeListener() {
				private static final long serialVersionUID = -675674373089622451L;

				@Override
				public void colorChanged(ColorChangeEvent event) {
					accountTheme.setTabsheetbg(event.getColor().getCSS()
							.substring(1).toUpperCase());
				}
			});
			propertyLayout.addComponent(new Label("Normal Tab"), 0, 0);
			propertyLayout.addComponent(tabsheetBg, 1, 0);

			CustomColorPickerArea tabsheetText = new CustomColorPickerArea(
					"Normal Tab Text", new Color(Integer.parseInt(
							accountTheme.getTabsheettext(), 16)));
			tabsheetText.addColorChangeListener(new ColorChangeListener() {
				private static final long serialVersionUID = 3487570137637191332L;

				@Override
				public void colorChanged(ColorChangeEvent event) {
					accountTheme.setTabsheettext(event.getColor().getCSS()
							.substring(1).toUpperCase());
				}
			});
			propertyLayout.addComponent(new Label("Normal Tab Text"), 0, 1);
			propertyLayout.addComponent(tabsheetText, 1, 1);

			CustomColorPickerArea tabsheetBgSelected = new CustomColorPickerArea(
					"Selected Tab", new Color(Integer.parseInt(
							accountTheme.getTabsheetbgselected(), 16)));
			tabsheetBgSelected
					.addColorChangeListener(new ColorChangeListener() {
						private static final long serialVersionUID = -2435453092194064504L;

						@Override
						public void colorChanged(ColorChangeEvent event) {
							accountTheme.setTabsheetbgselected(event.getColor()
									.getCSS().substring(1).toUpperCase());
						}
					});
			propertyLayout.addComponent(new Label("Selected Tab"), 0, 2);
			propertyLayout.addComponent(tabsheetBgSelected, 1, 2);

			CustomColorPickerArea tabsheetTextSelected = new CustomColorPickerArea(
					"Selected Tab Text", new Color(Integer.parseInt(
							accountTheme.getTabsheettextselected(), 16)));
			tabsheetTextSelected
					.addColorChangeListener(new ColorChangeListener() {
						private static final long serialVersionUID = 3190273972739835060L;

						@Override
						public void colorChanged(ColorChangeEvent event) {
							accountTheme.setTabsheettextselected(event
									.getColor().getCSS().substring(1)
									.toUpperCase());
						}
					});
			propertyLayout.addComponent(new Label("Selected Tab Text"), 0, 3);
			propertyLayout.addComponent(tabsheetTextSelected, 1, 3);

			blockBody.addComponent(propertyLayout);
		}
		return blockLayout;
	}

	private Component constructVTabsheetCustomizeBlock() {
		VerticalLayout blockLayout = new VerticalLayout();
		Label blockHeader = new Label("Vertical Menu");
		blockHeader.setStyleName("block-hdr");
		blockHeader.addStyleName("h2");
		blockLayout.addComponent(blockHeader);
		blockLayout.setSpacing(true);

		VerticalLayout blockBody = new VerticalLayout();
		blockBody.setMargin(new MarginInfo(false, true, false, true));
		blockLayout.addComponent(blockBody);

		if (accountTheme != null) {
			GridLayout propertyLayout = new GridLayout(2, 4);
			propertyLayout.setSpacing(true);
			propertyLayout.setStyleName("no-border");
			propertyLayout.setColumnExpandRatio(0, 1.0f);
			propertyLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

			CustomColorPickerArea vTabsheetBg = new CustomColorPickerArea(
					"Normal Tab", new Color(Integer.parseInt(
							accountTheme.getVtabsheetbg(), 16)));
			vTabsheetBg.addColorChangeListener(new ColorChangeListener() {
				private static final long serialVersionUID = -675674373089622451L;

				@Override
				public void colorChanged(ColorChangeEvent event) {
					accountTheme.setVtabsheetbg(event.getColor().getCSS()
							.substring(1).toUpperCase());
				}
			});
			propertyLayout.addComponent(new Label("Normal Menu"), 0, 0);
			propertyLayout.addComponent(vTabsheetBg, 1, 0);

			CustomColorPickerArea vTabsheetText = new CustomColorPickerArea(
					"Normal Tab Text", new Color(Integer.parseInt(
							accountTheme.getVtabsheettext(), 16)));
			vTabsheetText.addColorChangeListener(new ColorChangeListener() {
				private static final long serialVersionUID = 3487570137637191332L;

				@Override
				public void colorChanged(ColorChangeEvent event) {
					accountTheme.setVtabsheettext(event.getColor().getCSS()
							.substring(1).toUpperCase());
				}
			});
			propertyLayout.addComponent(new Label("Normal Menu Text"), 0, 1);
			propertyLayout.addComponent(vTabsheetText, 1, 1);

			CustomColorPickerArea vTabsheetBgSelected = new CustomColorPickerArea(
					"Selected Menu", new Color(Integer.parseInt(
							accountTheme.getVtabsheetbgselected(), 16)));
			vTabsheetBgSelected
					.addColorChangeListener(new ColorChangeListener() {
						private static final long serialVersionUID = -2435453092194064504L;

						@Override
						public void colorChanged(ColorChangeEvent event) {
							accountTheme.setVtabsheetbgselected(event
									.getColor().getCSS().substring(1)
									.toUpperCase());
						}
					});
			propertyLayout.addComponent(new Label("Selected Menu"), 0, 2);
			propertyLayout.addComponent(vTabsheetBgSelected, 1, 2);

			CustomColorPickerArea vTabsheetTextSelected = new CustomColorPickerArea(
					"Selected Menu Text", new Color(Integer.parseInt(
							accountTheme.getVtabsheettextselected(), 16)));
			vTabsheetTextSelected
					.addColorChangeListener(new ColorChangeListener() {
						private static final long serialVersionUID = 3190273972739835060L;

						@Override
						public void colorChanged(ColorChangeEvent event) {
							accountTheme.setVtabsheettextselected(event
									.getColor().getCSS().substring(1)
									.toUpperCase());
						}
					});
			propertyLayout.addComponent(new Label("Selected Menu Text"), 0, 3);
			propertyLayout.addComponent(vTabsheetTextSelected, 1, 3);

			blockBody.addComponent(propertyLayout);
		}
		return blockLayout;
	}
}
