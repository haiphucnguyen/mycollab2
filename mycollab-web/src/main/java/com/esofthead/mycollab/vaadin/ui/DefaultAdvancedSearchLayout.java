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
	private SavedSearchResultComboBox saveResultComboBox;
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

	private HorizontalLayout createButtonControls(){
		 HorizontalLayout buttonControls = new HorizontalLayout();
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
			return buttonControls;
	}
	
	private HorizontalLayout createSaveSearchControls(){
		HorizontalLayout saveSearchControls = new HorizontalLayout();
		Label saveSearchLbl = new Label("Save Search As ");
		UiUtils.addComponent(saveSearchControls, saveSearchLbl, Alignment.MIDDLE_RIGHT);

		saveSearchValue = new TextField();
		UiUtils.addComponent(saveSearchControls, saveSearchValue, Alignment.MIDDLE_RIGHT);

		saveResultComboBox = new SavedSearchResultComboBox();
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
							"Query saved successfully.");
					saveSearchValue.setValue("");

					BeanContainer<String, SaveSearchResultWithBLOBs> beanData = saveResultComboBox.getBeanIteam();
					beanData.addBean(searchResult);
					saveResultComboBox.setContainerDataSource(beanData);
					saveResultComboBox.setItemCaptionPropertyId("queryname");
					
					saveResultComboBox.setValue(searchResult.getId());
				}

			}
		});
		saveSearchBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		UiUtils.addComponent(saveSearchControls, saveSearchBtn,Alignment.MIDDLE_RIGHT);
		return saveSearchControls;
	}
	
	public ComponentContainer constructFooter() {		
//------Define VerticalLayout for footerLayout ------------------
		VerticalLayout footerLayout = new VerticalLayout();
		footerLayout.setSpacing(true);
		footerLayout.setMargin(false, true, false, false);
//------Define & contruct TopfooterLayout ------------------------
		HorizontalLayout topfooterLayout = new HorizontalLayout();
		topfooterLayout.setWidth("100%");
		
		HorizontalLayout buttonControls = createButtonControls();
		UiUtils.addComponent(topfooterLayout, buttonControls, Alignment.MIDDLE_RIGHT);
		topfooterLayout.setExpandRatio(buttonControls, 4.0f);
		
		HorizontalLayout saveSearchControls = createSaveSearchControls();
		saveSearchControls.setSpacing(true);
		UiUtils.addComponent(topfooterLayout, saveSearchControls, Alignment.MIDDLE_RIGHT);
		topfooterLayout.setExpandRatio(saveSearchControls, 1.0f);
		
		footerLayout.addComponent(topfooterLayout);
//------Define & contruct bottomfooterLayout ---------------------- 
		HorizontalLayout bottomFooterLayout = new HorizontalLayout();
		bottomFooterLayout.setSpacing(true);
		
		Label saveSearchLable = new Label("Save Search Result ");
		bottomFooterLayout.addComponent(saveSearchLable);
		bottomFooterLayout.addComponent(saveResultComboBox);
		
		footerLayout.addComponent(bottomFooterLayout);
		footerLayout.setComponentAlignment(bottomFooterLayout, Alignment.MIDDLE_RIGHT);
		return footerLayout;
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
					if (saveResultComboBox != null)
						itemId = saveResultComboBox.getValue();
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
			this.setContainerDataSource(beanItem);
			this.setItemCaptionPropertyId("queryname");
		}
		
		public BeanContainer<String, SaveSearchResultWithBLOBs> getBeanIteam(){
			return beanItem;
		}
	}
}
