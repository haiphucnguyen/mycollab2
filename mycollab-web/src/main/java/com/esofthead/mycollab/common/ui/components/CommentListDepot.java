/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.ui.components;

import com.esofthead.mycollab.common.domain.Comment;
import com.esofthead.mycollab.common.domain.SimpleComment;
import com.esofthead.mycollab.common.domain.criteria.CommentSearchCriteria;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import java.util.GregorianCalendar;

/**
 *
 * @author haiphucnguyen
 */
public class CommentListDepot extends Depot {

    private String type;
    private int typeid;
    
    private BeanList<CommentService, CommentSearchCriteria, SimpleComment> commentList;
    
    
    public CommentListDepot(String type, int typeid) {
        super("Comments", new VerticalLayout());
        this.setWidth("900px");
        this.type = type;
        this.typeid = typeid;
        initUI();
    }

    private void initUI() {
        VerticalLayout contentContainer = (VerticalLayout) content;
        contentContainer.setSpacing(true);

        contentContainer.addComponent(new CommentTextAreaInput());
        
        commentList = new BeanList<CommentService, CommentSearchCriteria, SimpleComment>(AppContext.getSpringBean(CommentService.class), new CommentRowDisplayHandler());
        contentContainer.addComponent(commentList);
        displayCommentList();
    }
    
    private void displayCommentList() {
        CommentSearchCriteria searchCriteria = new CommentSearchCriteria();
        searchCriteria.setType(new StringSearchField(type));
        searchCriteria.setTypeid(new NumberSearchField(typeid));
        commentList.setSearchCriteria(searchCriteria);
    }
    
    private static class CommentRowDisplayHandler implements BeanList.RowDisplayHandler<SimpleComment> {

        @Override
        public Component generateRow(SimpleComment obj, int rowIndex) {
            return new Label("aa");
        }
        
    }
    
    private class CommentTextAreaInput extends VerticalLayout {
        private TextArea commentArea;
        
        public CommentTextAreaInput() {
            this.setWidth("600px");
            this.setSpacing(true);
            
            commentArea = new TextArea();
            commentArea.setWidth("100%");
            this.addComponent(commentArea);
            
            Button newCommentBtn = new Button("Post", new Button.ClickListener() {

                @Override
                public void buttonClick(ClickEvent event) {
                    Comment comment = new Comment();
                    comment.setComment((String)commentArea.getValue());
                    comment.setCreatedtime(new GregorianCalendar().getTime());
                    comment.setCreateduser(AppContext.getUsername());
                    comment.setSaccountid(AppContext.getAccountId());
                    comment.setType(type);
                    comment.setTypeid(typeid);
                    
                    CommentService commentService = AppContext.getSpringBean(CommentService.class);
                    commentService.saveWithSession(comment, AppContext.getUsername());
                    
                    //save success, clear comment area and load list comments again
                    commentArea.setValue("");
                    displayCommentList();
                }
            });
            this.addComponent(newCommentBtn);
            this.setComponentAlignment(newCommentBtn, Alignment.MIDDLE_RIGHT);
        }
    }
}
