package com.mycollab.pro.module.project.view.risk;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.mycollab.module.project.*;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.mycollab.module.project.i18n.OptionI18nEnum.RiskConsequence;
import com.mycollab.module.project.i18n.OptionI18nEnum.RiskProbability;
import com.mycollab.module.project.service.RiskService;
import com.mycollab.module.project.view.settings.component.ProjectUserLink;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.events.HasMassItemActionHandler;
import com.mycollab.vaadin.events.HasSearchHandlers;
import com.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.mycollab.vaadin.mvp.AbstractPageView;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.DefaultMassItemActionHandlerContainer;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.CheckBoxDecor;
import com.mycollab.vaadin.web.ui.LabelLink;
import com.mycollab.vaadin.web.ui.SelectionOptionButton;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.mycollab.vaadin.web.ui.table.AbstractPagedBeanTable;
import com.mycollab.vaadin.web.ui.table.DefaultPagedBeanTable;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.teemu.ratingstars.RatingStars;
import org.vaadin.viritin.button.MButton;
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
        setMargin(new MarginInfo(false, true, true, true));
    }

    @Override
    public void initContent() {
        riskSearchPanel = new RiskSearchPanel();
        addComponent(riskSearchPanel);
        tableItem = new DefaultPagedBeanTable<>(AppContextUtil.getSpringBean(RiskService.class),
                SimpleRisk.class, ProjectTypeConstants.RISK,
                RiskTableFieldDef.selected(), Arrays.asList(
                RiskTableFieldDef.name(), RiskTableFieldDef.assignUser(),
                RiskTableFieldDef.duedate(), RiskTableFieldDef.rating()));

        tableItem.addGeneratedColumn("selected", (source, itemId, columnId) -> {
            final SimpleRisk item = tableItem.getBeanByIndex(itemId);
            final CheckBoxDecor cb = new CheckBoxDecor("", item.isSelected());
            cb.setImmediate(true);
            cb.addValueChangeListener(valueChangeEvent -> tableItem.fireSelectItemEvent(item));
            item.setExtraData(cb);
            return cb;
        });

        tableItem.addGeneratedColumn("riskname", (source, itemId, columnId) -> {
            SimpleRisk risk = tableItem.getBeanByIndex(itemId);
            LabelLink b = new LabelLink(risk.getRiskname(), ProjectLinkBuilder.generateRiskPreviewFullLink(risk.getProjectid(),
                    risk.getId()));

            if ("Closed".equals(risk.getStatus())) {
                b.addStyleName(WebUIConstants.LINK_COMPLETED);
            } else {
                if (risk.isOverdue()) {
                    b.addStyleName(WebUIConstants.LINK_OVERDUE);
                }
            }
            b.setDescription(ProjectTooltipGenerator.generateToolTipRisk(UserUIContext.getUserLocale(), MyCollabUI.getDateFormat(),
                    risk, MyCollabUI.getSiteUrl(), UserUIContext.getUserTimeZone(), false));
            return b;
        });

        tableItem.addGeneratedColumn("assignedToUserFullName", (source, itemId, columnId) -> {
            SimpleRisk risk = tableItem.getBeanByIndex(itemId);
            return new ProjectUserLink(risk.getAssigntouser(), risk.getAssignToUserAvatarId(),
                    risk.getAssignedToUserFullName());
        });

        tableItem.addGeneratedColumn("raisedByUserFullName", (source, itemId, columnId) -> {
            SimpleRisk risk = tableItem.getBeanByIndex(itemId);
            return new ProjectUserLink(risk.getRaisedbyuser(), risk.getRaisedByUserAvatarId(), risk.getRaisedByUserFullName());
        });

        tableItem.addGeneratedColumn("datedue", (source, itemId, columnId) -> {
            SimpleRisk item = tableItem.getBeanByIndex(itemId);
            return new ELabel().prettyDate(item.getDatedue());
        });

        tableItem.addGeneratedColumn("status", (source, itemId, columnId) -> {
            SimpleRisk item = tableItem.getBeanByIndex(itemId);
            return ELabel.i18n(item.getStatus(), StatusI18nEnum.class);
        });

        tableItem.addGeneratedColumn("consequence", (source, itemId, columnId) -> {
            SimpleRisk item = tableItem.getBeanByIndex(itemId);
            return ELabel.i18n(item.getConsequence(), RiskConsequence.class);
        });

        tableItem.addGeneratedColumn("probalitity", (source, itemId, columnId) -> {
            SimpleRisk item = tableItem.getBeanByIndex(itemId);
            return ELabel.i18n(item.getProbalitity(), RiskProbability.class);
        });

        tableItem.addGeneratedColumn("level", (source, itemId, columnId) -> {
            SimpleRisk item = tableItem.getBeanByIndex(itemId);
            RatingStars tinyRs = new RatingStars();
            tinyRs.setValue(item.getLevel());
            tinyRs.addStyleName("tiny");
            tinyRs.setReadOnly(true);
            return tinyRs;
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
        MHorizontalLayout layout = new MHorizontalLayout().withFullWidth().withStyleName(WebUIConstants.TABLE_ACTION_CONTROLS);

        selectOptionButton = new SelectionOptionButton(tableItem);
        selectOptionButton.setWidthUndefined();
        layout.addComponent(selectOptionButton);

        tableActionControls = new DefaultMassItemActionHandlerContainer();

        if (CurrentProjectVariables.canAccess(ProjectRolePermissionCollections.RISKS)) {
            tableActionControls.addDeleteActionItem();
        }

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

        MButton customizeViewBtn = new MButton("", clickEvent -> UI.getCurrent().addWindow(new RiskListCustomizeWindow(tableItem)))
                .withIcon(FontAwesome.ADJUST).withStyleName(WebUIConstants.BUTTON_ACTION);
        customizeViewBtn.setDescription(UserUIContext.getMessage(GenericI18Enum.OPT_LAYOUT_OPTIONS));
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
        selectedItemsNumberLabel.setValue(UserUIContext.getMessage(GenericI18Enum.TABLE_SELECTED_ITEM_TITLE, numOfSelectedItems));
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
