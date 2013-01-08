package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.CustomLayout;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class GenericSearchPanel<S extends SearchCriteria> extends CustomComponent implements HasSearchHandlers<S> {

    private List<SearchHandler<S>> handers;

    @Override
    public void addSearchHandler(SearchHandler<S> handler) {
        if (handers == null) {
            handers = new ArrayList<SearchHandler<S>>();
        }
        handers.add(handler);
    }

    protected void notifySearchHandler(S criteria) {
        if (handers != null) {
            for (SearchHandler<S> handler : handers) {
                handler.onSearch(criteria);
            }
        }
    }

    abstract public static class BasicSearchLayout extends CustomLayout {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        public BasicSearchLayout() {
            super("basicSearch");
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

    abstract public static class AdvancedSearchLayout extends CustomLayout {

        public AdvancedSearchLayout() {
            super("advancedSearch");
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
