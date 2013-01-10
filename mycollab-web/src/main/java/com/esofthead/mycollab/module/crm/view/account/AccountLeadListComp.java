package com.esofthead.mycollab.module.crm.view.account;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.ui.components.RelatedListComp;
import com.esofthead.mycollab.module.crm.view.lead.LeadTableDisplay;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.hene.splitbutton.SplitButton;

public class AccountLeadListComp extends RelatedListComp<LeadSearchCriteria> {

    private static final long serialVersionUID = 1L;

    public AccountLeadListComp() {
        super("Leads");

        initUI();
    }

    private void initUI() {
        VerticalLayout contentContainer = (VerticalLayout) content;
        contentContainer.setSpacing(true);

        SplitButton controlsBtn = new SplitButton();
        controlsBtn.addStyleName(SplitButton.STYLE_CHAMELEON);
        controlsBtn.setCaption("New Lead");
        controlsBtn.setIcon(new ThemeResource("icons/16/addRecord.png"));
        controlsBtn.addClickListener(new SplitButton.SplitButtonClickListener() {
            @Override
            public void splitButtonClick(SplitButton.SplitButtonClickEvent event) {
                fireRelatedListHandler("");
            }
        });
        Button selectBtn = new Button("Select from existing leads", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            }
        });
        selectBtn.setIcon(new ThemeResource("icons/16/select.png"));
        selectBtn.setStyleName("link");
        controlsBtn.addComponent(selectBtn);

        contentContainer.addComponent(controlsBtn);

        tableItem = new LeadTableDisplay(
                new String[]{"leadName", "status", "officephone", "email", "id"}, new String[]{"Name", "Status",
                    "Office Phone", "Email", "Action"});

        tableItem.addTableListener(new ApplicationEventListener<IPagedBeanTable.TableClickEvent>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return IPagedBeanTable.TableClickEvent.class;
            }

            @Override
            public void handle(IPagedBeanTable.TableClickEvent event) {
                SimpleLead lead = (SimpleLead) event.getData();
                if ("leadName".equals(event.getFieldName())) {
                    EventBus.getInstance().fireEvent(
                            new LeadEvent.GotoRead(this, lead
                            .getId()));
                }
            }
        });

        tableItem.addGeneratedColumn("id", new ColumnGenerator() {
            @Override
            public Object generateCell(Table source, Object itemId,
                    Object columnId) {
                Button deleteBtn = new Button(null, new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                });
                deleteBtn.setStyleName("link");
                deleteBtn.setIcon(new ThemeResource("icons/16/delete.png"));
                return deleteBtn;
            }
        });

        contentContainer.addComponent(tableItem);
    }
}
