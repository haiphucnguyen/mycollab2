package com.esofthead.mycollab.module.crm.view;

import javax.annotation.PostConstruct;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.ui.components.ContactSearchPanel;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryContainer;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryDefinition;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryFactory;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.BeanTable;
import com.esofthead.mycollab.web.AppContext;
import com.jensjansson.pagedtable.PagedTable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;


public class ContactListViewImpl extends AbstractView implements
		ContactListView {
	private static final long serialVersionUID = 1L;

	private PagedTable tableItem;

	private ContactSearchCriteria searchCriteria;

	private VerticalLayout contactListLayout;
	
	public ContactListViewImpl() {
		this.setSpacing(true);

		ContactSearchPanel searchPanel = AppContext
				.getSpringBean(ContactSearchPanel.class);
		this.addComponent(searchPanel);

		contactListLayout = new VerticalLayout();
		this.addComponent(contactListLayout);
	}

	@Override
	public void doDefaultSearch() {
		searchCriteria = new ContactSearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		doSearch(searchCriteria);
	}

	@Override
	public void doSearch(ContactSearchCriteria searchCriteria) {
		tableItem = new BeanTable<SimpleContact>();
		tableItem.addStyleName("striped");

		MyBatisQueryContainer<SimpleContact> container = new MyBatisQueryContainer<SimpleContact>(
				new MyBatisQueryDefinition<ContactSearchCriteria>(
						AppContext.getSpringBean(ContactService.class), false,
						5), new MyBatisQueryFactory<ContactSearchCriteria>(
						searchCriteria));

		container.addContainerProperty("contactName", String.class, "", true,
				true);
		container.addContainerProperty("title", String.class, "", true, true);
		container.addContainerProperty("accountName", String.class, "", true,
				true);
		container.addContainerProperty("email", String.class, "", true, true);
		container.addContainerProperty("officephone", String.class, "", true,
				true);

		container.addContainerProperty("assignUserFullName", String.class, "",
				true, true);

		container.addContainerProperty("action", Object.class, "", true, false);
		
		tableItem.setWidth("1130px");
		tableItem.setColumnWidth("title", 140);
		tableItem.setColumnWidth("accountName", 140);
		tableItem.setColumnWidth("email", 180);
		tableItem.setColumnWidth("officephone", 90);
		tableItem.setColumnWidth("assignUserFullName", 140);
		tableItem.setColumnWidth("action", 82);

		tableItem.setContainerDataSource(container);
		tableItem.setColumnHeaders(new String[] { "Name", "Title",
				"Account Name", "Email", "Office Phone", "User", "Action" });

		tableItem.addGeneratedColumn("action", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				@SuppressWarnings("unchecked")
				final SimpleContact item = ((BeanTable<SimpleContact>) source)
						.getBeanByIndex(itemId);
				HorizontalLayout layout = new HorizontalLayout();
				layout.setSpacing(true);
				Button editBtn = new Button("Edit", new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						

					}
				});
				editBtn.setStyleName(BaseTheme.BUTTON_LINK);
				layout.addComponent(editBtn);

				layout.addComponent(new Label("|"));
				Button deleteBtn = new Button("Delete",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								// TODO Auto-generated method stub

							}
						});
				deleteBtn.setStyleName(BaseTheme.BUTTON_LINK);
				layout.addComponent(deleteBtn);
				return layout;
			}
		});

		contactListLayout.removeAllComponents();
		contactListLayout.addComponent(tableItem);
		contactListLayout.addComponent(tableItem.createControls());

	}

	@SuppressWarnings("serial")
	@PostConstruct
	private void init() {
//		eventBus.addListener(new ApplicationEventListener<ContactEvent.Search>() {
//
//			@Override
//			public Class<? extends ApplicationEvent> getEventType() {
//				return ContactEvent.Search.class;
//			}
//
//			@Override
//			public void handle(ContactEvent.Search event) {
//				searchCriteria = (ContactSearchCriteria) event.getData();
//			}
//		});
	}
}
