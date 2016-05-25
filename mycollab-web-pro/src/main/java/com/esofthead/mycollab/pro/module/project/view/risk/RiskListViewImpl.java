package com.esofthead.mycollab.pro.module.project.view.risk;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTooltipGenerator;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserLink;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasMassItemActionHandler;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.DefaultMassItemActionHandlerContainer;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.CheckBoxDecor;
import com.esofthead.mycollab.vaadin.web.ui.LabelLink;
import com.esofthead.mycollab.vaadin.web.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.web.ui.table.DefaultPagedBeanTable;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Table.ColumnGenerator;
import org.vaadin.teemu.ratingstars.RatingStars;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class RiskListViewImpl extends AbstractPageView implements RiskListView {
    private static final long serialVersionUID = 1L;

    private RiskSearchPanel riskSearchPanel;
    private SelectionOptionButton selectOptionButton;
    private DefaultPagedBeanTable<RiskService, RiskSearchCriteria, SimpleRisk> tableItem;
    private DefaultMassItemActionHandlerContainer tableActionControls;
    private Label selectedItemsNumberLabel = new Label();

    public RiskListViewImpl() {
        setMargin(new MarginInfo(false, true, false, true));
    }

    @Override
    public void initContent() {
        riskSearchPanel = new RiskSearchPanel();
        addComponent(riskSearchPanel);
        tableItem = new DefaultPagedBeanTable<>(
                AppContextUtil.getSpringBean(RiskService.class),
                SimpleRisk.class, VIEW_DEF_ID,
                RiskTableFieldDef.selected(), Arrays.asList(
                RiskTableFieldDef.name(), RiskTableFieldDef.assignUser(),
                RiskTableFieldDef.duedate(), RiskTableFieldDef.rating()));

        tableItem.addGeneratedColumn("selected", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public Object generateCell(Table source, Object itemId, Object columnId) {
                final SimpleRisk item = tableItem.getBeanByIndex(itemId);
                final CheckBoxDecor cb = new CheckBoxDecor("", item.isSelected());
                cb.setImmediate(true);
                cb.addValueChangeListener(new ValueChangeListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void valueChange(ValueChangeEvent event) {
                        tableItem.fireSelectItemEvent(item);
                    }
                });
                item.setExtraData(cb);
                return cb;
            }
        });

        tableItem.addGeneratedColumn("riskname", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(final Table source,
                                                        final Object itemId, final Object columnId) {
                SimpleRisk risk = tableItem.getBeanByIndex(itemId);
                LabelLink b = new LabelLink(risk.getRiskname(), ProjectLinkBuilder.generateRiskPreviewFullLink(risk.getProjectid(), risk.getId()));

                if ("Closed".equals(risk.getStatus())) {
                    b.addStyleName(UIConstants.LINK_COMPLETED);
                } else {
                    if (risk.isOverdue()) {
                        b.addStyleName(UIConstants.LINK_OVERDUE);
                    }
                }
                b.setDescription(ProjectTooltipGenerator.generateToolTipRisk(AppContext.getUserLocale(), risk,
                        AppContext.getSiteUrl(), AppContext.getUserTimeZone(), false));
                return b;

            }
        });

        tableItem.addGeneratedColumn("assignedToUserFullName", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source, Object itemId, Object columnId) {
                SimpleRisk risk = tableItem.getBeanByIndex(itemId);
                return new ProjectUserLink(risk.getAssigntouser(), risk.getAssignToUserAvatarId(),
                        risk.getAssignedToUserFullName());

            }
        });

        tableItem.addGeneratedColumn("raisedByUserFullName", new Table.ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source, Object itemId, Object columnId) {
                SimpleRisk risk = tableItem.getBeanByIndex(itemId);
                return new ProjectUserLink(risk.getRaisedbyuser(), risk.getRaisedByUserAvatarId(), risk.getRaisedByUserFullName());

            }
        });

        tableItem.addGeneratedColumn("datedue", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source, Object itemId, Object columnId) {
                SimpleRisk item = tableItem.getBeanByIndex(itemId);
                return new ELabel().prettyDate(item.getDatedue());
            }
        });

        tableItem.addGeneratedColumn("level", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source, Object itemId, Object columnId) {
                SimpleRisk item = tableItem.getBeanByIndex(itemId);
                RatingStars tinyRs = new RatingStars();
                tinyRs.setValue(item.getLevel());
                tinyRs.addStyleName("tiny");
                tinyRs.setReadOnly(true);
                return tinyRs;
            }
        });

        tableItem.setWidth("100%");

        this.addComponent(constructTableActionControls());
        this.addComponent(tableItem);
    }

    @Override
    public HasSearchHandlers<RiskSearchCriteria> getSearchHandlers() {
        return riskSearchPanel;
    }

    private ComponentContainer constructTableActionControls() {
        MHorizontalLayout layout = new MHorizontalLayout().withFullWidth();
        layout.addStyleName(UIConstants.TABLE_ACTION_CONTROLS);

        selectOptionButton = new SelectionOptionButton(tableItem);
        selectOptionButton.setWidthUndefined();
        layout.addComponent(selectOptionButton);

        Button deleteBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_DELETE));
        deleteBtn.setEnabled(CurrentProjectVariables.canAccess(ProjectRolePermissionCollections.RISKS));

        tableActionControls = new DefaultMassItemActionHandlerContainer();

        if (CurrentProjectVariables.canAccess(ProjectRolePermissionCollections.RISKS)) {
            tableActionControls.addDeleteActionItem();
        }

        tableActionControls.addMailActionItem();
        tableActionControls.addDownloadPdfActionItem();
        tableActionControls.addDownloadExcelActionItem();
        tableActionControls.addDownloadCsvActionItem();

        if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.RISKS)) {
            tableActionControls.addMassUpdateActionItem();
        }

        tableActionControls.setVisible(false);
        tableActionControls.setWidthUndefined();

        layout.addComponent(tableActionControls);
        selectedItemsNumberLabel.setWidth("100%");
        layout.with(selectedItemsNumberLabel).withAlign(selectedItemsNumberLabel, Alignment.MIDDLE_CENTER).expand(selectedItemsNumberLabel);

        Button customizeViewBtn = new Button("", new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                UI.getCurrent().addWindow(new RiskListCustomizeWindow(VIEW_DEF_ID, tableItem));

            }
        });
        customizeViewBtn.setIcon(FontAwesome.ADJUST);
        customizeViewBtn.setDescription("Layout Options");
        customizeViewBtn.addStyleName(UIConstants.BUTTON_ACTION);
        layout.with(customizeViewBtn).withAlign(customizeViewBtn, Alignment.MIDDLE_RIGHT);

        return layout;
    }

    @Override
    public void showNoItemView() {
        removeAllComponents();
        addComponent(new RiskListNoItemComponent());
    }

    @Override
    public void enableActionControls(final int numOfSelectedItems) {
        tableActionControls.setVisible(true);
        selectedItemsNumberLabel.setValue(AppContext.getMessage(GenericI18Enum.TABLE_SELECTED_ITEM_TITLE, numOfSelectedItems));
    }

    @Override
    public void disableActionControls() {
        tableActionControls.setVisible(false);
        selectOptionButton.setSelectedCheckbox(false);
        selectedItemsNumberLabel.setValue("");
    }

    @Override
    public HasSelectionOptionHandlers getOptionSelectionHandlers() {
        return selectOptionButton;
    }

    @Override
    public HasMassItemActionHandler getPopupActionHandlers() {
        return tableActionControls;
    }

    @Override
    public HasSelectableItemHandlers<SimpleRisk> getSelectableItemHandlers() {
        return tableItem;
    }

    @Override
    public AbstractPagedBeanTable<RiskSearchCriteria, SimpleRisk> getPagedBeanTable() {
        return tableItem;
    }
}
