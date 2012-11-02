package com.esofthead.mycollab.module.crm.ui.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.user.ui.components.UserListSelect;
import com.esofthead.mycollab.vaadin.mvp.eventbus.EventBus;
import com.esofthead.mycollab.vaadin.ui.CountryListSelect;
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
@Scope("prototype")
@Component
public class ContactSearchPanel extends CustomComponent {

	protected ContactSearchCriteria searchCriteria;

	@Autowired
	private EventBus eventBus;

	@Override
	public void attach() {
		createBasicSearchLayout();
	}

	private void createBasicSearchLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.addComponent(createSearchTopPanel());
		layout.addComponent(new BasicSearchLayout());
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
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						eventBus.fireEvent(new ContactEvent.GotoAdd(this, null));

					}
				});
		createAccountBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
		createAccountBtn.setStyleName(BaseTheme.BUTTON_LINK);

		UiUtils.addComponent(layout, createAccountBtn, Alignment.MIDDLE_RIGHT);

		return layout;
	}

	private class BasicSearchLayout extends HorizontalLayout {
		private static final long serialVersionUID = 1L;

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
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					searchCriteria = new ContactSearchCriteria();
					searchCriteria.setSaccountid(new NumberSearchField(
							SearchField.AND, AppContext.getAccountId()));
					searchCriteria.setContactName(new StringSearchField(
							SearchField.AND, (String) nameField.getValue()));
					eventBus.fireEvent(new ContactEvent.Search(
							ContactSearchPanel.this, searchCriteria));
				}
			}));

			this.addComponent(new Button("Cancel", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					nameField.setValue("");
				}
			}));

			Button advancedSearchBtn = new Button("Advanced Search",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							ContactSearchPanel.this
									.createAdvancedSearchLayout();
						}
					});
			advancedSearchBtn.setStyleName("link");
			UiUtils.addComponent(this, advancedSearchBtn,
					Alignment.MIDDLE_CENTER);
		}
	}

	private class AdvancedSearchLayout extends VerticalLayout {
		private static final long serialVersionUID = 1L;

		private TextField firstnameField;
		private TextField lastnameField;
		private TextField accountnameField;
		private UserListSelect assignUserField;
		private TextField anyEmailField;
		private TextField anyAddressField;
		private TextField stateField;
		private CountryListSelect countryField;
		private TextField anyPhoneField;
		private TextField postalCodeField;
		private TextField cityField;
		private LeadSourceListSelect leadSourceField;

		public AdvancedSearchLayout() {
			super();
			this.setSpacing(true);
			GridFormLayoutHelper gridLayout = new GridFormLayoutHelper(3, 4);

			firstnameField = (TextField) gridLayout.addComponent(
					new TextField(), "First Name", 0, 0);
			lastnameField = (TextField) gridLayout.addComponent(
					new TextField(), "Last Name", 0, 1);
			accountnameField = (TextField) gridLayout.addComponent(
					new TextField(), "Account Name", 0, 2);
			assignUserField = (UserListSelect) gridLayout.addComponent(
					AppContext.getSpringBean(UserListSelect.class),
					"Assign User", 0, 3);

			anyEmailField = (TextField) gridLayout.addComponent(
					new TextField(), "Any Email", 1, 0);
			anyAddressField = (TextField) gridLayout.addComponent(
					new TextField(), "Any Address", 1, 1);
			stateField = (TextField) gridLayout.addComponent(new TextField(),
					"State", 1, 2);
			countryField = (CountryListSelect) gridLayout.addComponent(
					AppContext.getSpringBean(CountryListSelect.class),
					"Country", 1, 3);

			anyPhoneField = (TextField) gridLayout.addComponent(
					new TextField(), "Any Phone", 2, 0);
			cityField = (TextField) gridLayout.addComponent(new TextField(),
					"City", 2, 1);
			postalCodeField = (TextField) gridLayout.addComponent(
					new TextField(), "Postal Code", 2, 2);
			leadSourceField = (LeadSourceListSelect) gridLayout.addComponent(
					AppContext.getSpringBean(LeadSourceListSelect.class),
					"Lead Source", 2, 3);

			this.addComponent(gridLayout.getLayout());

			HorizontalLayout buttonControls = new HorizontalLayout();
			buttonControls.setSpacing(true);
			buttonControls.addComponent(new Button("Search",
					new Button.ClickListener() {

						@Override
						public void buttonClick(ClickEvent event) {
							searchCriteria = new ContactSearchCriteria();
							searchCriteria.setSaccountid(new NumberSearchField(
									SearchField.AND, AppContext.getAccountId()));
							searchCriteria
									.setContactName(new StringSearchField(
											SearchField.AND,
											(String) firstnameField.getValue()));

							eventBus.fireEvent(new ContactEvent.Search(
									ContactSearchPanel.this, searchCriteria));
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
							ContactSearchPanel.this.createBasicSearchLayout();

						}
					});
			basicSearchBtn.setStyleName("link");
			UiUtils.addComponent(buttonControls, basicSearchBtn,
					Alignment.MIDDLE_CENTER);
			this.addComponent(buttonControls);
		}
	}
}
