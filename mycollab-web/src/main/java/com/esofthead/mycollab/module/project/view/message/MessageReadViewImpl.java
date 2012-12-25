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
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
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

            private GridFormLayoutHelper informationLayout;

            @Override
            public Layout getLayout() {
                AddViewLayout riskAddLayout = new AddViewLayout("Risk");

                riskAddLayout.addTopControls(createTopPanel());

                VerticalLayout layout = new VerticalLayout();

                Label organizationHeader = new Label("Message Information");
                organizationHeader.setStyleName("h2");
                layout.addComponent(organizationHeader);

                informationLayout = new GridFormLayoutHelper(1, 3);
                informationLayout.getLayout().setWidth("100%");
                layout.addComponent(informationLayout.getLayout());
                layout.setComponentAlignment(informationLayout.getLayout(),
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
                if (propertyId.equals("title")) {
                    informationLayout.addComponent(field, "", 0, 0);
                } else if (propertyId.equals("messagehtml")) {
                    informationLayout.addComponent(field, "", 0, 1);
                }
            }
        }
    }
}
