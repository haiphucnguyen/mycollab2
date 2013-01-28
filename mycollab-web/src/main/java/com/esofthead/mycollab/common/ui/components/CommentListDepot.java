/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.ui.components;

import com.esofthead.mycollab.common.domain.SimpleComment;
import com.esofthead.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class CommentListDepot extends Depot implements ReloadableComponent{

    private String type;
    private int typeid;
    private boolean isDisplayCommentInput = true;
    
    private BeanList<CommentService, CommentSearchCriteria, SimpleComment> commentList;
    
    
    public CommentListDepot(String type, int typeid) {
        super("Comments", new VerticalLayout());
        this.setWidth("900px");
        this.type = type;
        this.typeid = typeid;
        initUI();
    }
    
    public CommentListDepot(String type, int typeid, boolean isDisplayCommentInput) {
        super("Comments", new VerticalLayout());
        this.setWidth("900px");
        this.type = type;
        this.typeid = typeid;
        this.isDisplayCommentInput = isDisplayCommentInput;
        initUI();
    }

    private void initUI() {
        VerticalLayout contentContainer = (VerticalLayout) bodyContent;
        contentContainer.setSpacing(true);

        if (isDisplayCommentInput) {
        	 contentContainer.addComponent(new CommentInput(this, type, typeid));
        }
        
        commentList = new BeanList<CommentService, CommentSearchCriteria, SimpleComment>(AppContext.getSpringBean(CommentService.class), CommentRowDisplayHandler.class);
        contentContainer.addComponent(commentList);
    }

    @Override
    public void attach() {
        displayCommentList();
    }
    
    private void displayCommentList() {
        CommentSearchCriteria searchCriteria = new CommentSearchCriteria();
        searchCriteria.setType(new StringSearchField(type));
        searchCriteria.setTypeid(new NumberSearchField(typeid));
        int numComments = commentList.setSearchCriteria(searchCriteria);
        this.setTitle("Comments (" + numComments + ")");
    }

    @Override
    public void reload() {
        displayCommentList();
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
