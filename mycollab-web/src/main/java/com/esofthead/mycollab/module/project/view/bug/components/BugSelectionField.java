/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.view.bug.components;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.BasicSearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.vaadin.suggestfield.BeanSuggestionConverter;
import org.vaadin.suggestfield.SuggestField;
import org.vaadin.suggestfield.client.SuggestFieldSuggestion;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
public class BugSelectionField extends CustomField<SimpleBug> implements FieldSelection<SimpleBug> {
    private SimpleBug selectedBug;
    private SuggestField suggestField;
    private List<SimpleBug> items;
    private BugService bugService;

    public BugSelectionField() {
        bugService = ApplicationContextUtil.getSpringBean(BugService.class);
        suggestField = new SuggestField();
        suggestField.setPopupWidth(600);
        suggestField.setWidth("400px");
        suggestField.setInputPrompt("Enter related bug's name");
        suggestField.setInvalidAllowed(false);

        suggestField.setSuggestionHandler(new SuggestField.SuggestionHandler() {
            @Override
            public List<Object> searchItems(String query) {
                return handleSearchQuery(query);
            }
        });

        suggestField.setSuggestionConverter(new BugSuggestionConverter());
    }

    public SimpleBug getSelectedBug() {
        return selectedBug;
    }

    @Override
    protected Component initContent() {
        MHorizontalLayout layout = new MHorizontalLayout();
        Button browseBtn = new Button(FontAwesome.ELLIPSIS_H);
        browseBtn.addStyleName(UIConstants.BUTTON_OPTION);
        browseBtn.addStyleName(UIConstants.BUTTON_SMALL_PADDING);
        browseBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().addWindow(new BugSelectionWindow(BugSelectionField.this));
            }
        });
        layout.with(suggestField, new Label("or browse"), browseBtn);
        return layout;
    }

    @Override
    public Class<? extends SimpleBug> getType() {
        return SimpleBug.class;
    }

    @Override
    public void fireValueChange(SimpleBug data) {
        selectedBug = data;
        suggestField.setValue(selectedBug);
    }

    private List<Object> handleSearchQuery(String query) {
        if ("".equals(query) || query == null) {
            return Collections.emptyList();
        }
        BugSearchCriteria searchCriteria = new BugSearchCriteria();
        searchCriteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));
        searchCriteria.setSummary(StringSearchField.and(query));
        items = bugService.findPagableListByCriteria(new BasicSearchRequest<>(searchCriteria));
        return new ArrayList<Object>(items);
    }

    private class BugSuggestionConverter extends BeanSuggestionConverter {

        public BugSuggestionConverter() {
            super(SimpleBug.class, "id", "summary", "summary");
        }

        @Override
        public Object toItem(SuggestFieldSuggestion suggestion) {
            for (SimpleBug bean : items) {
                if (bean.getId().toString().equals(suggestion.getId())) {
                    selectedBug = bean;
                    break;
                }
            }
            assert selectedBug != null : "This should not be happening";
            return selectedBug;
        }
    }
}
