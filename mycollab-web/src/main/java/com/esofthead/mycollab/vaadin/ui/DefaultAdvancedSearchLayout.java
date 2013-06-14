package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.common.domain.SaveSearchResultWithBLOBs;
import com.esofthead.mycollab.common.service.SaveSearchResultService;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel.AdvancedSearchLayout;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.thoughtworks.xstream.XStream;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public abstract class DefaultAdvancedSearchLayout<S extends SearchCriteria>
		extends AdvancedSearchLayout<S> {

	private SaveSearchResultService saveSearchResultService;

	protected String type;

	public DefaultAdvancedSearchLayout(DefaultGenericSearchPanel<S> parent,
			String type) {
		super(parent);

		saveSearchResultService = AppContext
				.getSpringBean(SaveSearchResultService.class);
		this.type = type;
	}

	public abstract ComponentContainer constructHeader();

	public abstract ComponentContainer constructBody();

	protected abstract void clearFields();

	public ComponentContainer constructFooter() {
		HorizontalLayout footerLayout = new HorizontalLayout();
		footerLayout.setWidth("100%");
		
		final HorizontalLayout buttonControls = new HorizontalLayout();
		buttonControls.setSpacing(true);
		
		final Button searchBtn = new Button(
				LocalizationHelper.getMessage(CrmCommonI18nEnum.BUTTON_SEARCH),
				new Button.ClickListener() {
					@SuppressWarnings({ "unchecked", "rawtypes" })
					@Override
					public void buttonClick(final ClickEvent event) {
						DefaultAdvancedSearchLayout.this.callSearchAction();

					}
				});
		UiUtils.addComponent(buttonControls, searchBtn,Alignment.MIDDLE_CENTER);
		searchBtn.setStyleName(UIConstants.THEME_BLUE_LINK);

		final Button clearBtn = new Button(
				LocalizationHelper.getMessage(CrmCommonI18nEnum.BUTTON_CLEAR),
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						clearFields();
					}
				});
		clearBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		UiUtils.addComponent(buttonControls, clearBtn ,Alignment.MIDDLE_CENTER);

		final Button basicSearchBtn = new Button(
				LocalizationHelper
						.getMessage(CrmCommonI18nEnum.BUTTON_BASIC_SEARCH),
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						((DefaultGenericSearchPanel<S>) DefaultAdvancedSearchLayout.this.searchPanel)
								.moveToBasicSearchLayout();
					}
				});
		basicSearchBtn.setStyleName("link");
		UiUtils.addComponent(buttonControls, basicSearchBtn,Alignment.MIDDLE_CENTER);

		UiUtils.addComponent(footerLayout, buttonControls, Alignment.MIDDLE_RIGHT);
		footerLayout.setExpandRatio(buttonControls, 2.0f);
		
		HorizontalLayout saveSearchLayout = new HorizontalLayout();
		saveSearchLayout.setSpacing(true);
		
		Label saveSearchLbl = new Label("Save Search As");
		UiUtils.addComponent(saveSearchLayout, saveSearchLbl,
				Alignment.MIDDLE_RIGHT);

		final TextField saveSearchValue = new TextField();
		UiUtils.addComponent(saveSearchLayout, saveSearchValue,
				Alignment.MIDDLE_RIGHT);

		Button saveSearchBtn = new Button("Save", new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				S searchCriteria = fillupSearchCriteria();
				if (searchCriteria != null
						&& saveSearchValue.getValue() != null) {
					SaveSearchResultWithBLOBs searchResult = new SaveSearchResultWithBLOBs();
					searchResult.setSaveuser(AppContext.getUsername());
					searchResult.setSaccountid(AppContext.getAccountId());
					XStream stream = new XStream();
					searchResult.setQuerytext(stream.toXML(searchCriteria));
					searchResult.setType(type);
					searchResult.setQueryname((String) saveSearchValue
							.getValue());
					saveSearchResultService.saveWithSession(searchResult,
							AppContext.getUsername());
				}

			}
		});
		UiUtils.addComponent(saveSearchLayout, saveSearchBtn,
				Alignment.MIDDLE_RIGHT);

		
		UiUtils.addComponent(footerLayout, saveSearchLayout, Alignment.MIDDLE_RIGHT);
		footerLayout.setExpandRatio(saveSearchLayout, 1.0f);
		
		return footerLayout;
	}
}
