/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.common.domain.Comment;
import com.esofthead.mycollab.common.service.CommentService;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.Bug;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.service.BugRelatedItemService;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.module.user.ui.components.UserComboBox;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class WontFixExplainWindow extends Window {

    private SimpleBug bug;
    private EditForm editForm;
    private VersionMultiSelectField fixedVersionSelect;

    public WontFixExplainWindow(SimpleBug bug) {
        this.bug = bug;
        this.setWidth("830px");
        editForm = new EditForm();
        this.addComponent(editForm);
        editForm.setItemDataSource(new BeanItem<SimpleBug>(bug));
        center();
    }

    private class EditForm extends AdvancedEditBeanForm<Bug> {

        private static final long serialVersionUID = 1L;
        private RichTextArea commentArea;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new EditFormFieldFactory());
            super.setItemDataSource(newDataSource);
        }

        class FormLayoutFactory implements IFormLayoutFactory {

            private static final long serialVersionUID = 1L;
            private GridFormLayoutHelper informationLayout;

            @Override
            public Layout getLayout() {
                VerticalLayout layout = new VerticalLayout();
                informationLayout = new GridFormLayoutHelper(2, 6);
                informationLayout.getLayout().setWidth("800px");

                layout.addComponent(informationLayout.getLayout());

                HorizontalLayout controlsBtn = new HorizontalLayout();
                controlsBtn.setSpacing(true);
                layout.addComponent(controlsBtn);

                Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        WontFixExplainWindow.this.close();
                    }
                });
                cancelBtn.setStyleName("link");
                controlsBtn.addComponent(cancelBtn);
                controlsBtn.setComponentAlignment(cancelBtn, Alignment.MIDDLE_LEFT);

                Button wonFixBtn = new Button("Won't Fix", new Button.ClickListener() {
                    @SuppressWarnings("unchecked")
					@Override
                    public void buttonClick(ClickEvent event) {
                        bug.setStatus(BugStatusConstants.WONFIX);
                        
                        BugRelatedItemService bugRelatedItemService = AppContext.getSpringBean(BugRelatedItemService.class);
                        bugRelatedItemService.updateFixedVersionsOfBug(bug.getId(), (List<Version>)fixedVersionSelect.getSelectedItems());

                        //Save bug status and assignee
                        BugService bugService = AppContext.getSpringBean(BugService.class);
                        bugService.updateWithSession(bug, AppContext.getUsername());
                        

                        //Save comment
                        Comment comment = new Comment();
                        comment.setComment((String) commentArea.getValue());
                        comment.setCreatedtime(new GregorianCalendar().getTime());
                        comment.setCreateduser(AppContext.getUsername());
                        comment.setSaccountid(AppContext.getAccountId());
                        comment.setType(CommentTypeConstants.PRJ_BUG);
                        comment.setTypeid(bug.getId());

                        CommentService commentService = AppContext.getSpringBean(CommentService.class);
                        commentService.saveWithSession(comment, AppContext.getUsername());
                        WontFixExplainWindow.this.close();
                        EventBus.getInstance().fireEvent(new BugEvent.GotoRead(WontFixExplainWindow.this, bug.getId()));
                    }
                });
                wonFixBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
                controlsBtn.addComponent(wonFixBtn);
                controlsBtn.setComponentAlignment(wonFixBtn, Alignment.MIDDLE_RIGHT);

                layout.setComponentAlignment(controlsBtn, Alignment.MIDDLE_RIGHT);

                return layout;
            }

            @Override
            public void attachField(Object propertyId, Field field) {
                if (propertyId.equals("resolution")) {
                    informationLayout.addComponent(field, "Resolution", 0, 0);
                } else if (propertyId.equals("assignuser")) {
                    informationLayout.addComponent(field, "Assign User", 0, 1);
                } else if (propertyId.equals("fixedVersions")) {
                    informationLayout.addComponent(field, "Fixed Versions", 0, 2, 2, "100%");
                } else if (propertyId.equals("comment")) {
                    informationLayout.addComponent(field, "Comments", 0, 3, 2, UIConstants.DEFAULT_2XCONTROL_WIDTH);
                }
            }
        }

        private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

            private static final long serialVersionUID = 1L;

            @Override
            protected Field onCreateField(Item item, Object propertyId,
                    com.vaadin.ui.Component uiContext) {
                if (propertyId.equals("resolution")) {
                    return new BugResolutionComboBox();
                } else if (propertyId.equals("assignuser")) {
                    return new UserComboBox();
                } else if (propertyId.equals("fixedVersions")) {
                    fixedVersionSelect = new VersionMultiSelectField();
                    if (bug.getFixedVersions().size() > 0) {
                    	fixedVersionSelect.setSelectedItems(bug.getFixedVersions());
                    }
                    return fixedVersionSelect;
                } else if (propertyId.equals("comment")) {
                    commentArea = new RichTextArea();
                    commentArea.setNullRepresentation("");
                    return commentArea;
                }


                return null;
            }
        }
    }
}
