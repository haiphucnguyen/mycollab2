package com.esofthead.mycollab.mobile.module.project.ui;

import com.esofthead.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.addon.touchkit.ui.NavigationButton;

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
public class CommentNavigationButton extends NavigationButton {
    private String type;
    private String typeId;

    public CommentNavigationButton(String type) {
        super(AppContext.getMessage(GenericI18Enum.TAB_COMMENT, 0));
        this.type = type;
    }

    public void displayTotalComments(String typeId) {
        this.typeId = typeId;
        CommentService commentService = ApplicationContextUtil.getSpringBean(CommentService.class);
        CommentSearchCriteria searchCriteria = new CommentSearchCriteria();
        searchCriteria.setType(StringSearchField.and(type));
        searchCriteria.setTypeid(StringSearchField.and(typeId));
        this.setCaption(AppContext.getMessage(GenericI18Enum.TAB_COMMENT, commentService.getTotalCount(searchCriteria)));
    }
}
