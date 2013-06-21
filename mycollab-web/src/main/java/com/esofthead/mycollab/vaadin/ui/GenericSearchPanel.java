package com.esofthead.mycollab.vaadin.ui;

import java.util.ArrayList;
import java.util.List;

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

@SuppressWarnings("serial")
public class GenericSearchPanel<S extends SearchCriteria> extends
		CustomComponent implements HasSearchHandlers<S> {

	private List<SearchHandler<S>> handers;

	@Override
	public void addSearchHandler(final SearchHandler<S> handler) {
		if (this.handers == null) {
			this.handers = new ArrayList<SearchHandler<S>>();
		}
		this.handers.add(handler);
	}

	public void notifySearchHandler(final S criteria) {
		if (this.handers != null) {
			for (final SearchHandler<S> handler : this.handers) {
				handler.onSearch(criteria);
			}
		}
	}

	abstract public static class SearchLayout<S extends SearchCriteria> extends
			CustomLayout {
		protected GenericSearchPanel<S> searchPanel;

		public SearchLayout(final GenericSearchPanel<S> parent,
				final String layoutName) {
			super(layoutName);
			this.searchPanel = parent;
		}

		public void callSearchAction() {
			final S searchCriteria = this.fillupSearchCriteria();
			this.searchPanel.notifySearchHandler(searchCriteria);
		}

		public TextField createSeachSupportTextField(final TextField textField,
				final String keyField) {
			textField.addShortcutListener(new ShortcutListener(keyField,
					ShortcutAction.KeyCode.ENTER, null) {
				@Override
				public void handleAction(final Object sender,
						final Object target) {
					if (target == textField) {
						SearchLayout.this.callSearchAction();
					}
				}

			});
			return textField;
		}

		@SuppressWarnings("hiding")
		public <S extends ListSelect> S createSeachSupportComboBox(
				final S textField) {
			textField.addShortcutListener(new ShortcutListener(String
					.valueOf(textField.hashCode()),
					ShortcutAction.KeyCode.ENTER, null) {
				@Override
				public void handleAction(final Object sender,
						final Object target) {
					if (target == textField) {
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
		protected ComponentContainer header;
		protected ComponentContainer body;

		public BasicSearchLayout(final GenericSearchPanel<S> parent) {
			super(parent, "basicSearch");
			this.setStyleName("basicSearchLayout");
			this.initLayout();
		}

		protected void initLayout() {
			this.header = this.constructHeader();
			this.body = this.constructBody();
			this.addComponent(this.header, "basicSearchHeader");
			this.addComponent(this.body, "basicSearchBody");
		}

		abstract public ComponentContainer constructHeader();

		abstract public ComponentContainer constructBody();
	}

	abstract public static class AdvancedSearchLayout<S extends SearchCriteria>
			extends SearchLayout<S> {

		protected ComponentContainer header;
		protected ComponentContainer body;
		protected ComponentContainer footer;

		public AdvancedSearchLayout(final GenericSearchPanel<S> parent) {
			super(parent, "advancedSearch");
			this.setStyleName("advancedSearchLayout");
			this.initLayout();
		}

		protected void initLayout() {
			this.header = this.constructHeader();
			this.body = this.constructBody();
			this.footer = this.constructFooter();
			this.addComponent(this.header, "advSearchHeader");
			this.addComponent(this.body, "advSearchBody");
			this.addComponent(this.footer, "advSearchFooter");
		}

		public abstract ComponentContainer constructHeader();

		public abstract ComponentContainer constructBody();

		public abstract ComponentContainer constructFooter();
	}
}
