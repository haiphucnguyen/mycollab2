package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class GenericSearchPanel<S extends SearchCriteria> extends
		CustomComponent implements HasSearchHandlers<S> {

	private List<SearchHandler<S>> handers;

	@Override
	public void addSearchHandler(SearchHandler<S> handler) {
		if (handers == null) {
			handers = new ArrayList<SearchHandler<S>>();
		}
		handers.add(handler);
	}

	public void notifySearchHandler(S criteria) {
		if (handers != null) {
			for (SearchHandler<S> handler : handers) {
				handler.onSearch(criteria);
			}
		}
	}

	abstract public static class SearchLayout<S extends SearchCriteria> extends
			CustomLayout{
		private GenericSearchPanel<S> searchPanel;
		public SearchLayout(GenericSearchPanel<S> parent, String layoutName) {
			super(layoutName);
			this.searchPanel = parent;
		}
		public void callSearchAction() {
			S searchCriteria = fillupSearchCriteria();
			searchPanel.notifySearchHandler(searchCriteria);
		}
		public TextField createSeachSupportTextField(final TextField textField,String keyField) {
			textField.addShortcutListener(new ShortcutListener(keyField, ShortcutAction.KeyCode.ENTER, null) {
				public void handleAction(Object sender, Object target) {
					if(target==textField){ 
						SearchLayout.this.callSearchAction();
					}
				}

			});
			return textField;
		}
		@SuppressWarnings("hiding")
		public <S extends ListSelect> S createSeachSupportComboBox(final S textField) {
			textField.addShortcutListener(new ShortcutListener(String.valueOf(textField.hashCode()), ShortcutAction.KeyCode.ENTER, null) {
				public void handleAction(Object sender, Object target) {
					if(target==textField){ 
						SearchLayout.this.callSearchAction();
					}
				}

			});
			return textField;
		}
		

		abstract protected S fillupSearchCriteria();
	}

	abstract public static class BasicSearchLayout<S extends SearchCriteria>
			extends SearchLayout<S> {
		private static final long serialVersionUID = 1L;

		public BasicSearchLayout(GenericSearchPanel<S> parent) {
			super(parent, "basicSearch");
			setStyleName("basicSearchLayout");
			this.initLayout();
		}

		private void initLayout() {
			ComponentContainer header = constructHeader();
			ComponentContainer body = constructBody();
			this.addComponent(header, "basicSearchHeader");
			this.addComponent(body, "basicSearchBody");
		}

		abstract public ComponentContainer constructHeader();

		abstract public ComponentContainer constructBody();
	}

	abstract public static class AdvancedSearchLayout<S extends SearchCriteria>
			extends SearchLayout<S> {

		public AdvancedSearchLayout(GenericSearchPanel<S> parent) {
			super(parent, "advancedSearch");
			setStyleName("advancedSearchLayout");
			initLayout();
		}

		public void initLayout() {
			ComponentContainer header = constructHeader();
			ComponentContainer body = constructBody();
			ComponentContainer footer = constructFooter();
			this.addComponent(header, "advSearchHeader");
			this.addComponent(body, "advSearchBody");
			this.addComponent(footer, "advSearchFooter");
		}

		public abstract ComponentContainer constructHeader();

		public abstract ComponentContainer constructBody();

		public abstract ComponentContainer constructFooter();
	}
}
