package com.esofthead.mycollab.vaadin.ui;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.splitbutton.PopupButtonControl;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.domain.SaveSearchResultWithBLOBs;
import com.esofthead.mycollab.common.domain.criteria.SaveSearchResultCriteria;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.common.service.SaveSearchResultService;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.vaadin.events.PopupActionHandler;
import com.esofthead.mycollab.vaadin.ui.GenericSearchPanel.SearchLayout;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.thoughtworks.xstream.XStream;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
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
	private Label filterLabel = new Label("Filter");
	protected String type;
	private PopupButtonControl tableActionControls;
	private HorizontalLayout saveSearchControls;
	private Button addnewBtn;

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

	private HorizontalLayout createButtonControls() {
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

	private HorizontalLayout createSaveSearchControls() {
		final HorizontalLayout saveSearchControls = new HorizontalLayout();
		// ----- Defined reUsed Layout -----------------------
		saveSearchValue = new TextField();
		saveResultComboBox = new SavedSearchResultComboBox();

		addnewBtn = new Button("New");
		final Button saveBtn = new Button("Save");
		final Button updateBtn = new Button("Update");
		final Button cancelBtn = new Button("Cancel");

		addnewBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		updateBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		cancelBtn.setStyleName(UIConstants.THEME_BLUE_LINK);

		// tableActionControll for Update group controls
		tableActionControls = new PopupButtonControl("updateSearch", updateBtn);
		tableActionControls.addOptionItem("delete",
				LocalizationHelper.getMessage(CrmCommonI18nEnum.BUTTON_DELETE));
		tableActionControls.addOptionItem("new", "New");
		tableActionControls.setVisible(true);

		// ---- Default Layout Generation ----------
		UiUtils.addComponent(saveSearchControls, filterLabel,
				Alignment.MIDDLE_RIGHT);
		UiUtils.addComponent(saveSearchControls, saveResultComboBox,
				Alignment.MIDDLE_RIGHT);
		UiUtils.addComponent(saveSearchControls, addnewBtn,
				Alignment.MIDDLE_RIGHT);
		// -----Defined Listener ---------------------
		addnewBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				saveSearchControls.removeComponent(addnewBtn);
				UiUtils.addComponent(saveSearchControls, saveBtn,
						Alignment.MIDDLE_RIGHT);
				UiUtils.addComponent(saveSearchControls, cancelBtn,
						Alignment.MIDDLE_RIGHT);
				saveSearchControls.replaceComponent(saveResultComboBox,
						saveSearchValue);
			}
		});
		saveBtn.addListener(new ClickListener() {
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
					getWindow().showNotification("Query saved successfully.");
					saveSearchValue.setValue("");

					BeanContainer<String, SaveSearchResultWithBLOBs> beanData = saveResultComboBox
							.getBeanIteam();
					beanData.addBean(searchResult);
					saveResultComboBox.setContainerDataSource(beanData);
					saveResultComboBox.setItemCaptionPropertyId("queryname");

					saveResultComboBox.setValue(searchResult.getId());
				}

				saveSearchControls.replaceComponent(saveSearchValue,
						saveResultComboBox);
				saveSearchControls.removeComponent(cancelBtn);
				saveSearchControls.removeComponent(saveBtn);
				saveSearchControls.addComponent(tableActionControls);
			}
		});
		cancelBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				saveSearchControls.removeComponent(cancelBtn);
				saveSearchControls.replaceComponent(saveBtn, addnewBtn);
				saveSearchControls.replaceComponent(saveSearchValue,
						saveResultComboBox);
			}
		});
		updateBtn.addListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Integer itemId = (Integer) saveResultComboBox.getValue();
				if (itemId != null) {
					BeanContainer<String, SaveSearchResultWithBLOBs> beanData = saveResultComboBox
							.getBeanIteam();

					String captionStr = saveResultComboBox
							.getItemCaption(itemId);
					S searchCriteria = fillupSearchCriteria();

					SaveSearchResultWithBLOBs searchResult = new SaveSearchResultWithBLOBs();
					searchResult.setSaveuser(AppContext.getUsername());
					searchResult.setSaccountid(AppContext.getAccountId());
					XStream stream = new XStream();
					searchResult.setQuerytext(stream.toXML(searchCriteria));
					searchResult.setType(type);
					searchResult.setId(itemId);
					searchResult.setQueryname(captionStr);

					saveSearchResultService.updateWithSession(searchResult,
							AppContext.getUsername());
					getWindow().showNotification("Updated successfully.");

					beanData.removeItem(itemId);
					beanData.addBean(searchResult);
					saveResultComboBox.setContainerDataSource(beanData);
					saveResultComboBox.setItemCaptionPropertyId("queryname");

					saveResultComboBox.setValue(searchResult.getId());
				}
			}
		});
		tableActionControls.addPopupActionHandler(new PopupActionHandler() {
			@Override
			public void onSelect(String id, String caption) {
				if ("delete".equals(id)) {
					ConfirmDialogExt.show(
							getWindow(),
							LocalizationHelper
									.getMessage(
											GenericI18Enum.DELETE_DIALOG_TITLE,
											ApplicationProperties
													.getString(ApplicationProperties.SITE_NAME)),
							"Do you want to delete record ?",
							LocalizationHelper
									.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
							LocalizationHelper
									.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
							new ConfirmDialog.Listener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void onClose(ConfirmDialog dialog) {
									if (dialog.isConfirmed()) {
										Integer itemDelete = (Integer) saveResultComboBox
												.getValue();
										saveSearchResultService
												.removeWithSession(itemDelete,
														AppContext
																.getUsername());
										getWindow().showNotification(
												"Delete successfully.");

										BeanContainer<String, SaveSearchResultWithBLOBs> beanData = saveResultComboBox
												.getBeanIteam();
										beanData.removeItem(itemDelete);
										saveResultComboBox
												.setContainerDataSource(beanData);
										saveResultComboBox
												.setItemCaptionPropertyId("queryname");

										clearFields();
										saveResultComboBox.setValue(null);
									}
								}
							}); // end confirm Dialog
				} else if ("new".equals(id)) {
					saveSearchControls.replaceComponent(tableActionControls,
							saveBtn);
					saveSearchControls.replaceComponent(saveResultComboBox,
							saveSearchValue);
					cancelBtn.addListener(new ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							saveSearchControls.replaceComponent(
									saveSearchValue, saveResultComboBox);
							saveSearchControls.replaceComponent(saveBtn,
									tableActionControls);
							saveSearchControls.removeComponent(cancelBtn);
							saveSearchControls.removeComponent(addnewBtn);
						}
					});
					saveSearchControls.addComponent(cancelBtn);
				}
			}
		});

		return saveSearchControls;
	}

	public ComponentContainer constructFooter() {
		// ------Define VerticalLayout for footerLayout ------------------
		VerticalLayout footerLayout = new VerticalLayout();
		footerLayout.setSpacing(true);
		footerLayout.setMargin(false, true, false, false);
		// ------Define & contruct TopfooterLayout ------------------------
		HorizontalLayout topfooterLayout = new HorizontalLayout();
		topfooterLayout.setWidth("100%");

		HorizontalLayout buttonControls = createButtonControls();
		UiUtils.addComponent(topfooterLayout, buttonControls,
				Alignment.MIDDLE_RIGHT);
		buttonControls.setMargin(false, true, false, false);
		topfooterLayout.setExpandRatio(buttonControls, 3.0f);

		saveSearchControls = createSaveSearchControls();
		saveSearchControls.setSpacing(true);
		saveSearchControls.setMargin(false, true, false, true);
		UiUtils.addComponent(topfooterLayout, saveSearchControls,
				Alignment.MIDDLE_RIGHT);
		topfooterLayout.setExpandRatio(saveSearchControls, 1.0f);

		footerLayout.addComponent(topfooterLayout);
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
						saveSearchControls.replaceComponent(addnewBtn,
								tableActionControls);
					} else {
						saveSearchControls.removeComponent(tableActionControls);
						saveSearchControls.addComponent(addnewBtn);
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

		public BeanContainer<String, SaveSearchResultWithBLOBs> getBeanIteam() {
			return beanItem;
		}
	}
}
