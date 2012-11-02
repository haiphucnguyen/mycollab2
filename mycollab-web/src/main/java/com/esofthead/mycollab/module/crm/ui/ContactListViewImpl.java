package com.esofthead.mycollab.module.crm.ui;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ContactEvent;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.ui.components.ContactSearchPanel;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryContainer;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryDefinition;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryFactory;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEvent;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ui.Params;
import com.esofthead.mycollab.vaadin.ui.BeanTable;
import com.esofthead.mycollab.web.AppContext;
import com.jensjansson.pagedtable.PagedTable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

@Scope("prototype")
@Component
public class ContactListViewImpl extends AbstractView implements
		ContactListView {
	private static final long serialVersionUID = 1L;

	private PagedTable tableItem;

	private ContactSearchCriteria searchCriteria;

	
	public void handleRequest(Params params) {
		searchCriteria = new ContactSearchCriteria();
	}

	@SuppressWarnings("serial")
	@PostConstruct
	private void init() {
		eventBus.addListener(new ApplicationEventListener<ContactEvent.Search>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return ContactEvent.Search.class;
			}

			@Override
			public void handle(ContactEvent.Search event) {
				searchCriteria = (ContactSearchCriteria) event.getData();
			}
		});
	}

	@Override
	protected ComponentContainer initMainLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);

		ContactSearchPanel searchPanel = AppContext
				.getSpringBean(ContactSearchPanel.class);
		layout.addComponent(searchPanel);
		tableItem = new BeanTable<SimpleContact>();
		tableItem.setWidth("100%");
		tableItem.setHeight("200px");

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
		tableItem.setColumnWidth("action", 80);

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
						eventBus.fireEvent(new ContactEvent.GotoEdit(this, item));

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

		layout.addComponent(tableItem);
		layout.addComponent(tableItem.createControls());
		return layout;
	}
}
