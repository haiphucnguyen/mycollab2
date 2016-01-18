package com.esofthead.mycollab.mobile.ui;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.vaadin.data.Property;
import com.vaadin.ui.TextField;
import org.vaadin.resetbuttonfortextfield.ResetButtonClickListener;
import org.vaadin.resetbuttonfortextfield.ResetButtonForTextField;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mycollab Ltd
 * @since 5.2.5
 */
public abstract class SearchInputField<S extends SearchCriteria> extends TextField implements HasSearchHandlers<S> {
    private List<SearchHandler<S>> searchHandlers;

    public SearchInputField() {
        this.setImmediate(true);
        this.setStyleName("searchinputfield");
        this.setInputPrompt("Search");
        this.setTextChangeEventMode(TextChangeEventMode.TIMEOUT);
        this.setTextChangeTimeout(3000);
        this.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                String value = getValue();
                if (StringUtils.isNotBlank(value)) {
                    final S searchCriteria = fillUpSearchCriteria(value);
                    notifySearchHandler(searchCriteria);
                }
            }
        });
        ResetButtonForTextField resetBtn = ResetButtonForTextField.extend(this);
        resetBtn.addResetButtonClickedListener(new ResetButtonClickListener() {
            @Override
            public void resetButtonClicked() {
                final S searchCriteria = fillUpSearchCriteria(null);
                notifySearchHandler(searchCriteria);
            }
        });
    }

    @Override
    public void addSearchHandler(SearchHandler<S> handler) {
        if (searchHandlers == null) {
            searchHandlers = new ArrayList<>();
        }
        searchHandlers.add(handler);
    }

    @Override
    public void notifySearchHandler(final S criteria) {
        if (searchHandlers != null) {
            for (SearchHandler<S> handler : searchHandlers) {
                handler.onSearch(criteria);
            }
        }
    }

    abstract protected S fillUpSearchCriteria(String value);

    @Override
    public void setTotalCountNumber(int totalCountNumber) {

    }
}
