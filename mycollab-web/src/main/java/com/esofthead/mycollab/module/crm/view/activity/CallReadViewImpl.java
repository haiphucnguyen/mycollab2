package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.module.crm.ui.components.RelatedReadItemField;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class CallReadViewImpl extends AbstractView implements CallReadView {

    private static final long serialVersionUID = 1L;
    private PreviewForm previewForm;
    private SimpleCall call;

    public CallReadViewImpl() {
        super();
        previewForm = new PreviewForm();
        this.addComponent(previewForm);
    }

    @Override
    public void previewItem(SimpleCall call) {
        this.call = call;
        previewForm.setItemDataSource(new BeanItem<Call>(call));
    }

    @Override
    public HasPreviewFormHandlers<SimpleCall> getPreviewFormHandlers() {
        return previewForm;
    }

    private class PreviewForm extends AdvancedPreviewBeanForm<SimpleCall> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
                private static final long serialVersionUID = 1L;

                @Override
                protected Field onCreateField(Item item, Object propertyId,
                        Component uiContext) {
                    if (propertyId.equals("assignuser")) {
                        return new FormLinkViewField(call
                                .getAssignUserFullName(),
                                new Button.ClickListener() {
                                    private static final long serialVersionUID = 1L;

                                    @Override
                                    public void buttonClick(ClickEvent event) {
                                    }
                                });
                    } else if (propertyId.equals("type")) {
                        return new RelatedReadItemField(call);
                    } else if (propertyId.equals("status")) {
                        String value = call.getStatus() + " "
                                + call.getCalltype();
                        FormViewField field = new FormViewField(value);
                        return field;
                    } else if (propertyId.equals("durationinseconds")) {
                        Integer duration = call.getDurationinseconds();
                        if (duration != null && duration != 0) {
                            int hours = duration / 3600;
                            int minutes = (duration % 3600) / 60;
                            StringBuffer value = new StringBuffer();
                            if (hours == 1) {
                                value.append("1 hour ");
                            } else if (hours >= 2) {
                                value.append(hours + " hours ");
                            }

                            if (minutes > 0) {
                                value.append(minutes + " minutes");
                            }

                            return new FormViewField(value.toString());
                        } else {
                            return new FormViewField("");
                        }
                    } else if (propertyId.equals("startdate")) {
                        return new FormViewField(AppContext.formatDateTime(call
                                .getStartdate()));
                    }

                    return null;
                }
            });
            super.setItemDataSource(newDataSource);
        }

        class FormLayoutFactory extends CallFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super(call.getSubject());
            }

            @Override
            protected Layout createTopPanel() {
                return (new PreviewFormControlsGenerator<SimpleCall>(
                        PreviewForm.this)).createButtonControls();
            }

            @Override
            protected Layout createBottomPanel() {
                return new VerticalLayout();
            }
        }
    }

    @Override
    public SimpleCall getItem() {
        return call;
    }
}
