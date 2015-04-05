/**
 * This file is part of mycollab-web.
 * <p>
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.domain.SimpleComment;
import com.esofthead.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.common.ui.components.CommentRowDisplayHandler;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.i18n.ProjectCommonI18nEnum;
import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.ReloadableComponent;
import com.esofthead.mycollab.vaadin.ui.TabSheetLazyLoadComponent;
import com.vaadin.shared.ui.MarginInfo;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 *
 * @author MyCollab Ltd.
 * @since 1.0
 *
 */
public class CommentDisplay extends MVerticalLayout implements ReloadableComponent {
    private static final long serialVersionUID = 1L;

    private BeanList<CommentService, CommentSearchCriteria, SimpleComment> commentList;
    private CommentType type;
    private String typeId;
    private ProjectCommentInput commentBox;

    public CommentDisplay(
            CommentType type,
            Integer extraTypeId,
            boolean isDisplayCommentInput,
            boolean isSendingRelayEmail,
            Class<? extends SendingRelayEmailNotificationAction> emailHandler) {
        withMargin(new MarginInfo(true, false, true, false)).withStyleName("comment-display");
        this.type = type;
        if (isDisplayCommentInput) {
            commentBox = new ProjectCommentInput(this, type, extraTypeId,
                    false, isSendingRelayEmail, emailHandler);
            this.addComponent(commentBox);
        }

        commentList = new BeanList<>(
                ApplicationContextUtil.getSpringBean(CommentService.class),
                CommentRowDisplayHandler.class);
        commentList.setDisplayEmptyListText(false);
        this.addComponent(commentList);

        displayCommentList();
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void displayCommentList() {
        if (type == null || typeId == null) {
            return;
        }

        CommentSearchCriteria searchCriteria = new CommentSearchCriteria();
        searchCriteria.setType(new StringSearchField(type.toString()));
        searchCriteria.setTypeid(new StringSearchField(typeId));
        int numComments = commentList.setSearchCriteria(searchCriteria);

        Object parentComp = this.getParent();
        if (parentComp instanceof TabSheetLazyLoadComponent) {
            ((TabSheetLazyLoadComponent) parentComp).getTab(this).setCaption(AppContext.getMessage(ProjectCommonI18nEnum
                    .TAB_COMMENT, numComments));
        }
    }

    public void loadComments(final String typeId) {
        this.typeId = typeId;
        if (commentBox != null) {
            commentBox.setTypeAndId(typeId);
        }
        displayCommentList();
    }

    @Override
    public void reload() {
        displayCommentList();
    }
}