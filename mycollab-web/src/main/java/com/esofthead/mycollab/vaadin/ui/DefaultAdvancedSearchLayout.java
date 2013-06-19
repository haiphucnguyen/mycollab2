package com.esofthead.mycollab.vaadin.ui;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.domain.SaveSearchResultWithBLOBs;
import com.esofthead.mycollab.common.domain.criteria.SaveSearchResultCriteria;
import com.esofthead.mycollab.common.service.SaveSearchResultService;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel.SearchLayout;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.thoughtworks.xstream.XStream;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class DefaultAdvancedSearchLayout<S extends SearchCriteria>
		extends SearchLayout<S> {

	private static Logger log = LoggerFactory
			.getLogger(DefaultAdvancedSearchLayout.class);

	private SaveSearchResultService saveSearchResultService;

	private TextField saveSearchValue;
	private SavedSearchResultComboBox saveResult;
	protected String type;

	public DefaultAdvancedSearchLayout(DefaultGenericSearchPanel<S> parent,
			String type) {
		super(parent, "advancedSearch");
		setStyleName("advancedSearchLayout");

		saveSearchResultService = AppContext
				.getSpringBean(SaveSearchResultService.class);
		this.type = type;
		initLayout();
	}

	protected void initLayout() {
		ComponentContainer header = constructHeader();
		ComponentContainer body = constructBody();
		ComponentContainer footer = constructFooter();
		this.addComponent(header, "advSearchHeader");
		this.addComponent(body, "advSearchBody");
		this.addComponent(footer, "advSearchFooter");
	}

	public abstract ComponentContainer constructHeader();

	public abstract ComponentContainer constructBody();

	protected abstract void loadSaveSearchToField(S value);

	protected abstract void clearFields();

	public ComponentContainer constructFooter() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);

		HorizontalLayout footerLayout = new HorizontalLayout();
		footerLayout.setWidth("100%");

		final HorizontalLayout buttonControls = new HorizontalLayout();
		buttonControls.setSpacing(true);

		final Button searchBtn = new Button(
				LocalizationHelper.getMessage(CrmCommonI18nEnum.BUTTON_SEARCH),
				new Button.ClickListener() {
					@Override
					public void buttonClick(final ClickEvent event) {
						DefaultAdvancedSearchLayout.this.callSearchAction();
					}
				});
		UiUtils.addComponent(buttonControls, searchBtn, Alignment.MIDDLE_CENTER);
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
		UiUtils.addComponent(buttonControls, clearBtn, Alignment.MIDDLE_CENTER);

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
		UiUtils.addComponent(buttonControls, basicSearchBtn,
				Alignment.MIDDLE_CENTER);

		UiUtils.addComponent(footerLayout, buttonControls,
				Alignment.MIDDLE_RIGHT);
		footerLayout.setExpandRatio(buttonControls, 3.0f);

		HorizontalLayout saveSearchLayout = new HorizontalLayout();
		saveSearchLayout.setSpacing(true);

		Label saveSearchLbl = new Label("Save Search As ");
		UiUtils.addComponent(saveSearchLayout, saveSearchLbl,
				Alignment.MIDDLE_RIGHT);

		saveSearchValue = new TextField();
		UiUtils.addComponent(saveSearchLayout, saveSearchValue,
				Alignment.MIDDLE_RIGHT);

		saveResult = new SavedSearchResultComboBox();

		Button saveSearchBtn = new Button("Save", new Button.ClickListener() {

			@SuppressWarnings("deprecation")
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
					getWindow().showNotification(
							"You created successfully searchItem.");
					saveSearchValue.setValue("");

					saveResult.contructComboBox();
					saveResult.setValue(searchResult.getId());
				}

			}
		});
		saveSearchBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		UiUtils.addComponent(saveSearchLayout, saveSearchBtn,
				Alignment.MIDDLE_RIGHT);

		UiUtils.addComponent(footerLayout, saveSearchLayout,
				Alignment.MIDDLE_RIGHT);
		footerLayout.setExpandRatio(saveSearchLayout, 1.0f);

		HorizontalLayout bottomLayout = new HorizontalLayout();
		bottomLayout.setWidth("100%");

		HorizontalLayout bottomLayout1 = new HorizontalLayout();
		bottomLayout1.setSpacing(true);
		HorizontalLayout bottomLayout2 = new HorizontalLayout();
		bottomLayout2.setSpacing(true);

		Label saveSearchLable = new Label("Save Search Result ");
		UiUtils.addComponent(bottomLayout2, saveSearchLable,
				Alignment.MIDDLE_CENTER);

		UiUtils.addComponent(bottomLayout2, saveResult, Alignment.MIDDLE_CENTER);

		UiUtils.addComponent(bottomLayout, bottomLayout1,
				Alignment.MIDDLE_CENTER);
		bottomLayout.setExpandRatio(bottomLayout1, 19.0f);
		UiUtils.addComponent(bottomLayout, bottomLayout2, Alignment.MIDDLE_LEFT);
		bottomLayout.setExpandRatio(bottomLayout2, 1.0f);

		layout.addComponent(footerLayout);
		UiUtils.addComponent(layout, bottomLayout, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	private class SavedSearchResultComboBox extends ComboBox {
		BeanContainer<String, SaveSearchResultWithBLOBs> beanItem;

		public SavedSearchResultComboBox() {
			this.setImmediate(true);
			this.setItemCaptionMode(ITEM_CAPTION_MODE_PROPERTY);

			contructComboBox();

			this.addListener(new ValueChangeListener() {
				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent event) {
					Object itemId = SavedSearchResultComboBox.this.getValue();
					if (saveResult != null)
						itemId = saveResult.getValue();
					if (itemId != null) {
						SaveSearchResultWithBLOBs data = beanItem.getItem(
								itemId).getBean();

						saveSearchValue.setValue("");
						String queryText = data.getQuerytext();
						XStream xstream = new XStream();
						S value = (S) xstream.fromXML(queryText);
						loadSaveSearchToField(value);
					}
				}
			});
			this.setImmediate(true);
		}

		public void contructComboBox() {
			SaveSearchResultCriteria searchCriteria = new SaveSearchResultCriteria();
			searchCriteria.setType(new StringSearchField(type));
			searchCriteria.setCreateUser(new StringSearchField(AppContext
					.getUsername()));
			searchCriteria.setsAccountId(new NumberSearchField(AppContext
					.getAccountId()));

			List<SaveSearchResultWithBLOBs> result = saveSearchResultService
					.findPagableListByCriteria(new SearchRequest<SaveSearchResultCriteria>(
							searchCriteria, 0, Integer.MAX_VALUE));
			beanItem = new BeanContainer<String, SaveSearchResultWithBLOBs>(
					SaveSearchResultWithBLOBs.class);
			beanItem.setBeanIdProperty("id");

			for (SaveSearchResultWithBLOBs searchResult : result) {
				beanItem.addBean(searchResult);
			}
			if (saveResult != null) {
				saveResult.setContainerDataSource(beanItem);
				saveResult.setItemCaptionPropertyId("queryname");
			} else {
				this.setContainerDataSource(beanItem);
				this.setItemCaptionPropertyId("queryname");
			}
		}

		public void addNewSearchResult(SaveSearchResultWithBLOBs result) {
			// ((BeanContainer<String, SaveSearchResultWithBLOBs>) (this
			// .getContainerDataSource())).addItem(result);
			beanItem.addItem(result);
		}

	}
}
