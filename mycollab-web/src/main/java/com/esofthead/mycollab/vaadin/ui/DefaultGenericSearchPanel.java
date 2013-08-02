package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.arguments.SearchCriteria;

public abstract class DefaultGenericSearchPanel<S extends SearchCriteria>
		extends GenericSearchPanel<S> {
	private static final long serialVersionUID = 1L;

	public DefaultGenericSearchPanel() {
		moveToBasicSearchLayout();
	}

	abstract protected SearchLayout<S> createBasicSearchLayout();

	abstract protected SearchLayout<S> createAdvancedSearchLayout();

	protected void moveToBasicSearchLayout() {
		SearchLayout<S> layout = createBasicSearchLayout();
		setCompositionRoot(layout);
	}

	protected void moveToAdvancedSearchLayout() {
		SearchLayout<S> layout = createAdvancedSearchLayout();
		setCompositionRoot(layout);
	}
}
