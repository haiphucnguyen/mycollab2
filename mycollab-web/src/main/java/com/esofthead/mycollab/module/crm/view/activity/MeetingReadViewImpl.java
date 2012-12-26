package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.ui.components.RelatedReadItemField;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class MeetingReadViewImpl extends AbstractView implements MeetingReadView {

    private static final long serialVersionUID = 1L;
    private PreviewForm previewForm;
    private SimpleMeeting meeting;

    public MeetingReadViewImpl() {
        super();
        previewForm = new PreviewForm();
        this.addComponent(previewForm);
    }

    @Override
    public void previewItem(SimpleMeeting meeting) {
        this.meeting = meeting;
        previewForm.setItemDataSource(new BeanItem<Meeting>(meeting));
    }

    @Override
    public HasPreviewFormHandlers<Meeting> getPreviewFormHandlers() {
        return previewForm;
    }

    @Override
    public void doPrint() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private class PreviewForm extends AdvancedPreviewBeanForm<Meeting> {

        private static final long serialVersionUID = 1L;

        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
                private static final long serialVersionUID = 1L;

                @Override
                protected Field onCreateField(Item item, Object propertyId,
                        Component uiContext) {
                    if (propertyId.equals("type")) {
                        return new RelatedReadItemField(meeting);
                    }

                    return null;
                }
            });
            super.setItemDataSource(newDataSource);
        }

        class FormLayoutFactory extends MeetingFormLayoutFactory {

            private static final long serialVersionUID = 1L;

            public FormLayoutFactory() {
                super(meeting.getSubject());
            }

            @Override
            protected Layout createTopPanel() {
                return (new PreviewFormControlsGenerator<Meeting>(
                        PreviewForm.this)).createButtonControls();
            }

            @Override
            protected Layout createBottomPanel() {
                VerticalLayout relatedItemsPanel = new VerticalLayout();

                return relatedItemsPanel;
            }
        }
    }

    @Override
    public SimpleMeeting getItem() {
        return meeting;
    }
}
