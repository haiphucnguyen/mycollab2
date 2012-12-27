package com.esofthead.mycollab.module.project.view.risk;

import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.PopupButtonControl;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.teemu.ratingstars.RatingStars;

@ViewComponent
public class RiskListViewImpl extends AbstractView implements RiskListView {

    private static final long serialVersionUID = 1L;
    private final RiskSearchPanel riskSearchPanel;
    private SelectionOptionButton selectOptionButton;
    private PagedBeanTable2<RiskService, RiskSearchCriteria, SimpleRisk> tableItem;
    private final VerticalLayout riskListLayout;
    private PopupButtonControl tableActionControls;
    private final Label selectedItemsNumberLabel = new Label();

    public RiskListViewImpl() {
        this.setSpacing(true);

        riskSearchPanel = new RiskSearchPanel();
        this.addComponent(riskSearchPanel);

        riskListLayout = new VerticalLayout();
        riskListLayout.setSpacing(true);
        this.addComponent(riskListLayout);

        generateDisplayTable();
    }

    private void generateDisplayTable() {
        tableItem = new PagedBeanTable2<RiskService, RiskSearchCriteria, SimpleRisk>(
                AppContext.getSpringBean(RiskService.class), SimpleRisk.class,
                new String[]{"selected", "riskname",
                    "assignedToUserFullName", "datedue", "level"},
                new String[]{"", "Name", "Assigned to", "Due Date", "Level"});

        tableItem.addGeneratedColumn("selected", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public Object generateCell(final Table source, final Object itemId,
                    Object columnId) {
                final CheckBox cb = new CheckBox("", false);
                cb.setImmediate(true);
                cb.addListener(new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        SimpleRisk account = tableItem.getBeanByIndex(itemId);
                        tableItem.fireSelectItemEvent(account);

                    }
                });

                SimpleRisk account = tableItem.getBeanByIndex(itemId);
                account.setExtraData(cb);
                return cb;
            }
        });

        tableItem.addGeneratedColumn("riskname", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    final Object itemId, Object columnId) {
                final SimpleRisk risk = tableItem.getBeanByIndex(itemId);
                ButtonLink b = new ButtonLink(risk.getRiskname(),
                        new Button.ClickListener() {
                            private static final long serialVersionUID = 1L;

                            @Override
                            public void buttonClick(ClickEvent event) {
                                EventBus.getInstance().fireEvent(
                                        new RiskEvent.GotoRead(this, risk
                                        .getId()));
                            }
                        });
                b.addStyleName("medium-text");
                return b;

            }
        });

        tableItem.addGeneratedColumn("datedue", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    Object itemId, Object columnId) {
                final SimpleRisk item = tableItem.getBeanByIndex(itemId);
                Label l = new Label();
                l.setValue(AppContext.formatDate(item.getDatedue()));
                return l;
            }
        });

        tableItem.addGeneratedColumn("level", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                    Object itemId, Object columnId) {
                final SimpleRisk item = tableItem.getBeanByIndex(itemId);
                RatingStars tinyRs = new RatingStars();
                tinyRs.setValue(item.getLevel());
                tinyRs.setStyleName("tiny");
                tinyRs.setReadOnly(true);
                return tinyRs;
            }
        });

        tableItem.setColumnExpandRatio("riskname", 1);
        tableItem.setColumnWidth("assignedToUserFullName", UIConstants.TABLE_X_LABEL_WIDTH);
        tableItem.setColumnWidth("level",
                UIConstants.TABLE_X_LABEL_WIDTH);
        tableItem.setColumnWidth("datedue", UIConstants.TABLE_DATE_WIDTH);

        tableItem.setWidth("100%");

        riskListLayout.addComponent(constructTableActionControls());
        riskListLayout.addComponent(tableItem);
    }

    @Override
    public HasSearchHandlers<RiskSearchCriteria> getSearchHandlers() {
        return riskSearchPanel;
    }

    private ComponentContainer constructTableActionControls() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);

        selectOptionButton = new SelectionOptionButton(tableItem);
        layout.addComponent(selectOptionButton);

        tableActionControls = new PopupButtonControl("delete", "Delete");
        tableActionControls.addOptionItem("mail", "Mail");
        tableActionControls.addOptionItem("export", "Export");

        layout.addComponent(tableActionControls);
        layout.addComponent(selectedItemsNumberLabel);
        layout.setComponentAlignment(selectedItemsNumberLabel,
                Alignment.MIDDLE_CENTER);
        return layout;
    }

    @Override
    public void enableActionControls(int numOfSelectedItems) {
        tableActionControls.setEnabled(true);
        selectedItemsNumberLabel.setValue("Selected: " + numOfSelectedItems);
    }

    @Override
    public void disableActionControls() {
        tableActionControls.setEnabled(false);
        selectedItemsNumberLabel.setValue("");
    }

    @Override
    public HasSelectionOptionHandlers getOptionSelectionHandlers() {
        return selectOptionButton;
    }

    @Override
    public HasPopupActionHandlers getPopupActionHandlers() {
        return tableActionControls;
    }

    @Override
    public HasSelectableItemHandlers<SimpleRisk> getSelectableItemHandlers() {
        return tableItem;
    }

    @Override
    public IPagedBeanTable<RiskService, RiskSearchCriteria, SimpleRisk> getPagedBeanTable() {
        return tableItem;
    }
}
