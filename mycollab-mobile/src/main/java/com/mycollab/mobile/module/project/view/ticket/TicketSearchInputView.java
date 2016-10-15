package com.mycollab.mobile.module.project.view.ticket;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.mobile.module.project.ui.SearchInputView;
import com.mycollab.mobile.ui.FormSectionBuilder;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;
import com.mycollab.vaadin.UserUIContext;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public class TicketSearchInputView extends SearchInputView<ProjectTicketSearchCriteria> {
    private MTextField nameField;

    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        MVerticalLayout content = new MVerticalLayout().withMargin(false);
        nameField = new MTextField().withFullWidth().withInputPrompt(UserUIContext.getMessage(GenericI18Enum.ACTION_QUERY_BY_TEXT));
        content.with(nameField);

        content.with(FormSectionBuilder.build(UserUIContext.getMessage(GenericI18Enum.OPT_SHARED_TO_ME)));
        setContent(content);
    }

    @Override
    protected ProjectTicketSearchCriteria buildSearchCriteria() {
        ProjectTicketSearchCriteria criteria = new ProjectTicketSearchCriteria();
        criteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
        criteria.setTypes(CurrentProjectVariables.getRestrictedTicketTypes());
        criteria.setName(StringSearchField.and(nameField.getValue()));
        return criteria;
    }
}
