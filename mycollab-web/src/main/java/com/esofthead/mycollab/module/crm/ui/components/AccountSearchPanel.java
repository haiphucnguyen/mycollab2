package com.esofthead.mycollab.module.crm.ui.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;
import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.user.ui.components.UserListSelect;
import com.esofthead.mycollab.vaadin.mvp.eventbus.EventBus;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UiUtils;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
@Component
public class AccountSearchPanel extends CustomComponent {
	protected AccountSearchCriteria searchCriteria;

	@Autowired
	private EventBus eventBus;

	public AccountSearchPanel() {
		searchCriteria = new AccountSearchCriteria();
	}

	@Override
	public void attach() {
		super.attach();
		createBasicSearchLayout();
	}

	private void createBasicSearchLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(createSearchTopPanel());
		layout.addComponent(new BasicSearchLayout());
		layout.setSpacing(true);
		this.setCompositionRoot(layout);
	}

	private void createAdvancedSearchLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(createSearchTopPanel());
		layout.addComponent(new AdvancedSearchLayout());
		layout.setSpacing(true);
		this.setCompositionRoot(layout);
	}

	private HorizontalLayout createSearchTopPanel() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setSpacing(true);

		Label searchtitle = new Label("Search");
		searchtitle.setStyleName(Reindeer.LABEL_H2);
		layout.addComponent(searchtitle);

		Button createAccountBtn = new Button("Create",
				new Button.ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						eventBus.fireEvent(new AccountEvent(this,
								AccountEvent.GOTO_ADD_VIEW));

					}
				});
		createAccountBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
		createAccountBtn.setStyleName(BaseTheme.BUTTON_LINK);

		UiUtils.addComponent(layout, createAccountBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	private class BasicSearchLayout extends HorizontalLayout {
		private TextField nameField;
		private CheckBox myItemCheckbox;

		public BasicSearchLayout() {
			this.setSpacing(true);
			this.addComponent(new Label("Name"));
			nameField = new TextField();
			nameField.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			UiUtils.addComponent(this, nameField, Alignment.MIDDLE_CENTER);
			myItemCheckbox = new CheckBox("My Items");
			UiUtils.addComponent(this, myItemCheckbox, Alignment.MIDDLE_CENTER);

			this.addComponent(new Button("Search", new Button.ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					searchCriteria = new AccountSearchCriteria();
					searchCriteria.setSaccountid(new NumberSearchField(
							SearchField.AND, AppContext.getAccountId()));
					searchCriteria.setAccountname(new StringSearchField(
							SearchField.AND, (String) nameField.getValue()));
					eventBus.fireEvent(new AccountEvent(
							AccountSearchPanel.this,
							AccountEvent.SEARCH, searchCriteria));
				}
			}));

			this.addComponent(new Button("Cancel", new Button.ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					nameField.setValue("");
				}
			}));

			Button advancedSearchBtn = new Button("Advanced Search",
					new Button.ClickListener() {

						@Override
						public void buttonClick(ClickEvent event) {
							AccountSearchPanel.this
									.createAdvancedSearchLayout();
						}
					});
			advancedSearchBtn.setStyleName("link");
			UiUtils.addComponent(this, advancedSearchBtn,
					Alignment.MIDDLE_CENTER);
		}
	}

	private class AdvancedSearchLayout extends VerticalLayout {
		private TextField nameField;
		private TextField websiteField;
		private TextField anyPhoneField;
		private TextField anyMailField;
		private TextField anyAddressField;
		private TextField cityField;
		private AccountIndustryListSelect industryField;
		private AccountTypeListSelect typeField;
		private UserListSelect userField;

		public AdvancedSearchLayout() {
			super();
			this.setSpacing(true);
			GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(3, 3);
			nameField = (TextField) gridLayout.addComponent(new TextField(),
					"Name", 0, 0);

			websiteField = (TextField) gridLayout.addComponent(new TextField(),
					"Website", 1, 0);

			anyPhoneField = (TextField) gridLayout.addComponent(
					new TextField(), "Any Phone", 2, 0);

			anyMailField = (TextField) gridLayout.addComponent(new TextField(),
					"Any Email", 0, 1);

			anyAddressField = (TextField) gridLayout.addComponent(
					new TextField(), "Any Address", 1, 1);

			cityField = (TextField) gridLayout.addComponent(new TextField(),
					"City", 2, 1);

			industryField = (AccountIndustryListSelect) gridLayout
					.addComponent(AppContext
							.getSpringBean(AccountIndustryListSelect.class),
							"Industry", 0, 2);

			typeField = (AccountTypeListSelect) gridLayout.addComponent(
					AppContext.getSpringBean(AccountTypeListSelect.class),
					"Type", 1, 2);

			userField = (UserListSelect) gridLayout.addComponent(
					AppContext.getSpringBean(UserListSelect.class),
					"Assigned User", 2, 2);

			HorizontalLayout buttonControls = new HorizontalLayout();
			buttonControls.setSpacing(true);
			buttonControls.addComponent(new Button("Search",
					new Button.ClickListener() {

						@Override
						public void buttonClick(ClickEvent event) {
							searchCriteria = new AccountSearchCriteria();
							searchCriteria.setSaccountid(new NumberSearchField(
									SearchField.AND, AppContext.getAccountId()));
							searchCriteria
									.setAccountname(new StringSearchField(
											SearchField.AND, (String) nameField
													.getValue()));

							eventBus.fireEvent(new AccountEvent(
									AccountSearchPanel.this,
									AccountEvent.SEARCH,
									searchCriteria));
						}

					}));

			buttonControls.addComponent(new Button("Clear",
					new Button.ClickListener() {

						@Override
						public void buttonClick(ClickEvent event) {

						}

					}));

			Button basicSearchBtn = new Button("Basic Search",
					new Button.ClickListener() {

						@Override
						public void buttonClick(ClickEvent event) {
							AccountSearchPanel.this.createBasicSearchLayout();

						}
					});
			basicSearchBtn.setStyleName("link");
			UiUtils.addComponent(buttonControls, basicSearchBtn,
					Alignment.MIDDLE_CENTER);

			this.addComponent(gridLayout.getLayout());
			this.addComponent(buttonControls);
		}
	}

}
