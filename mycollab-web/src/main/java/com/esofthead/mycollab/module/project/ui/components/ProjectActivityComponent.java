package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction;
import com.esofthead.mycollab.vaadin.ui.ReloadableComponent;
import com.vaadin.shared.ui.MarginInfo;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public class ProjectActivityComponent extends MVerticalLayout implements ReloadableComponent {
    private String type;
    private String typeId;
    private ProjectCommentInput commentBox;

    public ProjectActivityComponent(String type, Integer extraTypeId, Class<? extends SendingRelayEmailNotificationAction> emailHandler) {
        withMargin(new MarginInfo(true, false, true, true)).withStyleName("comment-display");
        this.type = type;
        commentBox = new ProjectCommentInput(this, type, extraTypeId, emailHandler);
        this.addComponent(commentBox);

    }

    private void displayCommentList() {
        if (type == null || typeId == null) {
            return;
        }

        CommentSearchCriteria searchCriteria = new CommentSearchCriteria();
        searchCriteria.setType(new StringSearchField(type));
        searchCriteria.setTypeid(new StringSearchField(typeId));

    }

    @Override
    public void reload() {

    }
}
