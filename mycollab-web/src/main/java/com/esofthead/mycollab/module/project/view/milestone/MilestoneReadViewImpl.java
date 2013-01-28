/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.ui.components.CommentListDepot;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Window;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class MilestoneReadViewImpl extends AbstractView implements MilestoneReadView {
    
    private static final long serialVersionUID = 1L;
    protected SimpleMilestone milestone;
    protected AdvancedPreviewBeanForm<Milestone> previewForm;
    
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
    
    private class PreviewForm extends AdvancedPreviewBeanForm<Milestone> {
        
        private static final long serialVersionUID = 1L;
        
        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new MilestoneReadViewImpl.PreviewForm.FormLayoutFactory());
            this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
                private static final long serialVersionUID = 1L;
                
                @SuppressWarnings("serial")
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
                    } else if (propertyId.equals("startdate")) {
                        return new FormViewField(AppContext.formatDate(milestone.getStartdate()));
                    } else if (propertyId.equals("enddate")) {
                        return new FormViewField(AppContext.formatDate(milestone.getEnddate()));
                    }
                    return null;
                }
            });
            super.setItemDataSource(newDataSource);
        }
        
        @Override
        protected void doPrint() {
            // Create a window that contains what you want to print
            Window window = new Window("Window to Print");

            MilestoneReadViewImpl printView = new MilestoneReadViewImpl.PrintView();
            printView.previewItem(milestone);
            window.addComponent(printView);

            // Add the printing window as a new application-level window
            getApplication().addWindow(window);

            // Open it as a popup window with no decorations
            getWindow().open(new ExternalResource(window.getURL()),
                    "_blank", 1100, 200, // Width and height
                    Window.BORDER_NONE); // No decorations

            // Print automatically when the window opens.
            // This call will block until the print dialog exits!
            window.executeJavaScript("print();");

            // Close the window automatically after printing
            window.executeJavaScript("self.close();");
        }

        @Override
        protected void showHistory() {
            MilestoneHistoryLogWindow historyLog = new MilestoneHistoryLogWindow(
                    ModuleNameConstants.PRJ, ProjectContants.MILESTONE,
                    milestone.getId());
            getWindow().addWindow(historyLog);
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
    
    @SuppressWarnings("serial")
	public static class PrintView extends MilestoneReadViewImpl {

        public PrintView() {
            previewForm = new AdvancedPreviewBeanForm<Milestone>() {
                @Override
                public void setItemDataSource(Item newDataSource) {
                	 this.setFormLayoutFactory(new MilestoneReadViewImpl.PrintView.FormLayoutFactory());
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
                             } else if (propertyId.equals("startdate")) {
                                 return new FormViewField(AppContext.formatDate(milestone.getStartdate()));
                             } else if (propertyId.equals("enddate")) {
                                 return new FormViewField(AppContext.formatDate(milestone.getEnddate()));
                             }
                             return null;
                         }
                     });
                     super.setItemDataSource(newDataSource);
                }
            };

            this.addComponent(previewForm);
        }

        class FormLayoutFactory extends MilestoneFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
            	 super(milestone.getName());
            }

            @Override
            protected Layout createTopPanel() {
                return new HorizontalLayout();
            }

            @Override
            protected Layout createBottomPanel() {
            	 return new CommentListDepot(CommentTypeConstants.PRJ_MILESTONE, milestone.getId(), false);
            }
        }
    }
}
