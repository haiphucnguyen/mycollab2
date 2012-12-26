package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
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
public class CampaignReadViewImpl extends AbstractView implements
        CampaignReadView {

    private static final long serialVersionUID = 1L;
    private PreviewForm previewForm;
    private SimpleCampaign campaign;

    public CampaignReadViewImpl() {
        super();
        previewForm = new PreviewForm();
        this.addComponent(previewForm);
    }

    @Override
    public void previewItem(SimpleCampaign campaign) {
        this.campaign = campaign;
        previewForm.setItemDataSource(new BeanItem<Campaign>(campaign));
    }

    @Override
    public HasPreviewFormHandlers<Campaign> getPreviewFormHandlers() {
        return previewForm;
    }

    @Override
    public void doPrint() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private class PreviewForm extends AdvancedPreviewBeanForm<Campaign> {

        private static final long serialVersionUID = 1L;

        @SuppressWarnings("serial")
        @Override
        public void setItemDataSource(Item newDataSource) {
            this.setFormLayoutFactory(new FormLayoutFactory());
            this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
                @Override
                protected Field onCreateField(Item item, Object propertyId,
                        Component uiContext) {
                    if (propertyId.equals("assignuser")) {
                        return new FormLinkViewField(campaign
                                .getAssignUserFullName(),
                                new Button.ClickListener() {
                                    @Override
                                    public void buttonClick(ClickEvent event) {
                                    }
                                });
                    } else if (propertyId.equals("startdate")) {
                        return new FormViewField(AppContext.formatDate(campaign
                                .getStartdate()));
                    } else if (propertyId.equals("enddate")) {
                        return new FormViewField(AppContext.formatDate(campaign
                                .getEnddate()));
                    }

                    return null;
                }
            });
            super.setItemDataSource(newDataSource);
        }

        class FormLayoutFactory extends CampaignFormLayoutFactory {

            private static final long serialVersionUID = 1L;
            
            public FormLayoutFactory() {
                super(campaign.getCampaignname());
            }

            @Override
            protected Layout createTopPanel() {
                return (new PreviewFormControlsGenerator<Campaign>(
                        PreviewForm.this)).createButtonControls();
            }

            @Override
            protected Layout createBottomPanel() {
                VerticalLayout relatedItemsPanel = new VerticalLayout();

                relatedItemsPanel.addComponent(new NoteListItems(
                        "Notes", "Campaign", campaign.getId()));

                return relatedItemsPanel;

            }
        }
    }

    @Override
    public SimpleCampaign getItem() {
        return campaign;
    }
}
