/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
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
public class ComponentReadViewImpl extends AbstractView implements ComponentReadView {
    private static final long serialVersionUID = 1L;
    protected SimpleComponent component;
    protected AdvancedPreviewBeanForm<SimpleComponent> previewForm;

    public ComponentReadViewImpl() {
        super();
        previewForm = new PreviewForm();
        this.addComponent(previewForm);
    }

    @Override
    public void previewItem(SimpleComponent item) {
        component = item;
        previewForm.setItemDataSource(new BeanItem<SimpleComponent>(item));
    }

    @Override
    public SimpleComponent getItem() {
        return component;
    }

    @Override
    public HasPreviewFormHandlers<SimpleComponent> getPreviewFormHandlers() {
        return previewForm;
    }

    private class PreviewForm extends AdvancedPreviewBeanForm<SimpleComponent> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
                private static final long serialVersionUID = 1L;

                @Override
                protected Field onCreateField(Item item, Object propertyId,
                        Component uiContext) {

                    return null;
                }
            });
            super.setItemDataSource(newDataSource);
        }
        
        @Override
        protected void doPrint() {
            // Create a window that contains what you want to print
            Window window = new Window("Window to Print");

            ComponentReadViewImpl printView = new ComponentReadViewImpl.PrintView();
            printView.previewItem(component);
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
            ComponentHistoryLogWindow historyLog = new ComponentHistoryLogWindow(
                    ModuleNameConstants.PRJ, ProjectContants.BUG_COMPONENT,
                    component.getId());
            getWindow().addWindow(historyLog);
        }

        class FormLayoutFactory extends ComponentFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super(component.getComponentname());
            }

            @Override
            protected Layout createTopPanel() {
                return (new PreviewFormControlsGenerator<SimpleComponent>(PreviewForm.this))
                        .createButtonControls();
            }

            @Override
            protected Layout createBottomPanel() {
                return new HorizontalLayout();
            }
        }
    }
    
    @SuppressWarnings("serial")
	public static class PrintView extends ComponentReadViewImpl {

        public PrintView() {
            previewForm = new AdvancedPreviewBeanForm<SimpleComponent>() {
                @Override
                public void setItemDataSource(Item newDataSource) {
                	 this.setFormLayoutFactory(new ComponentReadViewImpl.PrintView.FormLayoutFactory());
                     this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
                         private static final long serialVersionUID = 1L;
                         
                         @Override
                         protected Field onCreateField(Item item, Object propertyId,
                                 Component uiContext) {
                             
                             return null;
                         }
                     });
                     super.setItemDataSource(newDataSource);
                }
            };

            this.addComponent(previewForm);
        }

        class FormLayoutFactory extends ComponentFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
            	 super(component.getComponentname());
            }

            @Override
            protected Layout createTopPanel() {
                return new HorizontalLayout();
            }

            @Override
            protected Layout createBottomPanel() {
            	 return new HorizontalLayout();
            }
        }
    }
}
