/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.common.ui.components.CommentListDepot;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class MilestoneReadViewImpl extends AbstractView implements MilestoneReadView {
    
    private static final long serialVersionUID = 1L;
    private SimpleMilestone milestone;
    private MilestoneReadViewImpl.PreviewForm previewForm;
    
    public MilestoneReadViewImpl() {
        super();
        previewForm = new PreviewForm();
        this.addComponent(previewForm);
    }
    
    @Override
    public void previewItem(SimpleMilestone item) {
        milestone = item;
        previewForm.setItemDataSource(new BeanItem<Milestone>(item));
    }
    
    @Override
    public SimpleMilestone getItem() {
        return milestone;
    }
    
    @Override
    public HasPreviewFormHandlers<Milestone> getPreviewFormHandlers() {
        return previewForm;
    }
    
    @Override
    public void doPrint() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private class PreviewForm extends AdvancedPreviewBeanForm<Milestone> {
        
        private static final long serialVersionUID = 1L;
        
        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new MilestoneReadViewImpl.PreviewForm.FormLayoutFactory());
            this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
                private static final long serialVersionUID = 1L;
                
                @Override
                protected Field onCreateField(Item item, Object propertyId,
                        Component uiContext) {
                    
                    if (propertyId.equals("owner")) {
                        return new FormLinkViewField(milestone.getOwnerFullName(), new Button.ClickListener() {

                            @Override
                            public void buttonClick(ClickEvent event) {
                                //TODO: add link to user view
                            }
                        });
                    }
                    return null;
                }
            });
            super.setItemDataSource(newDataSource);
        }
        
        class FormLayoutFactory extends MilestoneFormLayoutFactory {
            
            private static final long serialVersionUID = 1L;
            
            public FormLayoutFactory() {
                super(milestone.getName());
            }
            
            @Override
            protected Layout createTopPanel() {
                return (new PreviewFormControlsGenerator<Milestone>(MilestoneReadViewImpl.PreviewForm.this))
                        .createButtonControls();
            }
            
            @Override
            protected Layout createBottomPanel() {
                return new CommentListDepot(CommentTypeConstants.PRJ_MILESTONE, milestone.getId());
            }
        }
    }
}
