/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.message;

import com.esofthead.mycollab.module.crm.ui.components.AddViewLayout;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class MessageReadViewImpl extends AbstractView implements MessageReadView {

    private PreviewForm previewForm;
    private SimpleMessage message;

    public MessageReadViewImpl() {
        super();
        previewForm = new PreviewForm();
        this.addComponent(previewForm);
    }

    @Override
    public HasPreviewFormHandlers<SimpleMessage> getPreviewFormHandlers() {
        return previewForm;
    }

    @Override
    public void previewItem(SimpleMessage item) {
        this.message = item;
        previewForm.setItemDataSource(new BeanItem<SimpleMessage>(item));
    }

    @Override
    public SimpleMessage getItem() {
        return message;
    }

    @Override
    public void doPrint() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private class PreviewForm extends AdvancedPreviewBeanForm<SimpleMessage> {

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new MessageReadViewImpl.PreviewForm.FormLayoutFactory());
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

        class FormLayoutFactory implements IFormLayoutFactory {

            @Override
            public Layout getLayout() {
                AddViewLayout riskAddLayout = new AddViewLayout(message.getTitle(), new ThemeResource("icons/48/project/message.png"));

                riskAddLayout.addTopControls(createTopPanel());

                VerticalLayout layout = new VerticalLayout();

                GridLayout bodyLayout = new GridLayout(1, 3);
                bodyLayout.setWidth("100%");
                layout.addComponent(bodyLayout);
                layout.setComponentAlignment(bodyLayout,
                        Alignment.BOTTOM_CENTER);

                riskAddLayout.addBottomControls(createBottomPanel());

                riskAddLayout.addBody(layout);

                return riskAddLayout;
            }

            protected Layout createTopPanel() {
                return new HorizontalLayout();
            }

            protected Layout createBottomPanel() {
                return new HorizontalLayout();
            }

            @Override
            public void attachField(Object propertyId, Field field) {
                
            }
        }
    }
}
