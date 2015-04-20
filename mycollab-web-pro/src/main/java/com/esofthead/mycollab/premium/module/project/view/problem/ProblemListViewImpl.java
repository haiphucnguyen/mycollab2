package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTooltipGenerator;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProblemService;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserLink;
import com.esofthead.mycollab.reporting.ReportExportType;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.*;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.*;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.DefaultPagedBeanTable;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Table.ColumnGenerator;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.teemu.ratingstars.RatingStars;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class ProblemListViewImpl extends AbstractPageView implements
        ProblemListView {
    private static final long serialVersionUID = 1L;

    private ProblemSearchPanel problemSearchPanel;
    private SelectionOptionButton selectOptionButton;
    private DefaultPagedBeanTable<ProblemService, ProblemSearchCriteria, SimpleProblem> tableItem;
    private VerticalLayout problemListLayout;
    private DefaultMassItemActionHandlersContainer tableActionControls;
    private Label selectedItemsNumberLabel = new Label();

    public ProblemListViewImpl() {
        setMargin(new MarginInfo(false, true, false, true));

        problemSearchPanel = new ProblemSearchPanel();
        addComponent(problemSearchPanel);

        problemListLayout = new VerticalLayout();
        addComponent(problemListLayout);

        generateDisplayTable();
    }

    private void generateDisplayTable() {
        tableItem = new DefaultPagedBeanTable<>(
                ApplicationContextUtil.getSpringBean(ProblemService.class),
                SimpleProblem.class, ProblemListView.VIEW_DEF_ID,
                ProblemTableFieldDef.selected, Arrays.asList(
                ProblemTableFieldDef.name,
                ProblemTableFieldDef.assignUser,
                ProblemTableFieldDef.datedue,
                ProblemTableFieldDef.rating));

        tableItem.addGeneratedColumn("selected", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public Object generateCell(final Table source, final Object itemId,
                                       final Object columnId) {
                final SimpleProblem problem = tableItem
                        .getBeanByIndex(itemId);
                CheckBoxDecor cb = new CheckBoxDecor("", problem
                        .isSelected());
                cb.setImmediate(true);
                cb.addValueChangeListener(new ValueChangeListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void valueChange(ValueChangeEvent event) {
                        tableItem.fireSelectItemEvent(problem);
                    }
                });

                problem.setExtraData(cb);
                return cb;
            }
        });

        tableItem.addGeneratedColumn("issuename", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(final Table source,
                                                        final Object itemId, final Object columnId) {
                SimpleProblem problem = tableItem.getBeanByIndex(itemId);
                LabelLink b = new LabelLink(problem.getIssuename(),
                        ProjectLinkBuilder.generateProblemPreviewFullLink(
                                problem.getProjectid(), problem.getId()));

                if ("Closed".equals(problem.getStatus())) {
                    b.addStyleName(UIConstants.LINK_COMPLETED);
                } else {
                    if (problem.isOverdue()) {
                        b.addStyleName(UIConstants.LINK_OVERDUE);
                    }
                }
                b.setDescription(ProjectTooltipGenerator
                        .generateToolTipProblem(AppContext.getUserLocale(),
                                problem, AppContext.getSiteUrl(),
                                AppContext.getTimezone()));
                return b;

            }
        });

        tableItem.addGeneratedColumn("assignedUserFullName",
                new Table.ColumnGenerator() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public com.vaadin.ui.Component generateCell(
                            Table source, Object itemId,
                            Object columnId) {
                        SimpleProblem problem = tableItem.getBeanByIndex(itemId);
                        return new ProjectUserLink(problem.getAssigntouser(),
                                problem.getAssignUserAvatarId(), problem
                                .getAssignedUserFullName());

                    }
                });

        tableItem.addGeneratedColumn("raisedByUserFullName",
                new Table.ColumnGenerator() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public com.vaadin.ui.Component generateCell(
                            Table source, Object itemId,
                            Object columnId) {
                        SimpleProblem problem = tableItem
                                .getBeanByIndex(itemId);
                        return new ProjectUserLink(problem.getAssigntouser(),
                                problem.getRaisedByUserAvatarId(), problem
                                .getRaisedByUserFullName());

                    }
                });

        tableItem.addGeneratedColumn("datedue", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                                                        Object itemId, Object columnId) {
                SimpleProblem item = tableItem.getBeanByIndex(itemId);
                return new ELabel().prettyDate(item.getDatedue());
            }
        });

        tableItem.addGeneratedColumn("level", new ColumnGenerator() {
            private static final long serialVersionUID = 1L;

            @Override
            public com.vaadin.ui.Component generateCell(Table source,
                                                        Object itemId, Object columnId) {
                SimpleProblem item = tableItem.getBeanByIndex(itemId);
                RatingStars tinyRs = new RatingStars();
                tinyRs.setValue(item.getLevel());
                tinyRs.setStyleName("tiny");
                tinyRs.setReadOnly(true);
                return tinyRs;
            }
        });
        tableItem.setWidth("100%");
        problemListLayout.addComponent(constructTableActionControls());
        problemListLayout.addComponent(tableItem);
    }

    @Override
    public HasSearchHandlers<ProblemSearchCriteria> getSearchHandlers() {
        return problemSearchPanel;
    }

    private ComponentContainer constructTableActionControls() {
        CssLayout layoutWrapper = new CssLayout();
        layoutWrapper.setWidth("100%");
        MHorizontalLayout layout = new MHorizontalLayout().withWidth("100%");
        layoutWrapper.addStyleName(UIConstants.TABLE_ACTION_CONTROLS);
        layoutWrapper.addComponent(layout);

        selectOptionButton = new SelectionOptionButton(tableItem);
        selectOptionButton.setWidthUndefined();
        layout.addComponent(selectOptionButton);

        Button deleteBtn = new Button(
                AppContext.getMessage(GenericI18Enum.BUTTON_DELETE));
        deleteBtn.setEnabled(CurrentProjectVariables
                .canAccess(ProjectRolePermissionCollections.PROBLEMS));

        tableActionControls = new DefaultMassItemActionHandlersContainer();

        if (CurrentProjectVariables
                .canAccess(ProjectRolePermissionCollections.PROBLEMS)) {
            tableActionControls.addActionItem(
                    MassItemActionHandler.DELETE_ACTION, FontAwesome.TRASH_O,
                    "delete", AppContext
                            .getMessage(GenericI18Enum.BUTTON_DELETE));
        }

        tableActionControls.addActionItem(MassItemActionHandler.MAIL_ACTION,
                FontAwesome.ENVELOPE_O,
                "mail", AppContext.getMessage(GenericI18Enum.BUTTON_MAIL));

        tableActionControls.addDownloadActionItem(
                ReportExportType.PDF,
                FontAwesome.FILE_PDF_O,
                "export", "export.pdf",
                AppContext.getMessage(GenericI18Enum.BUTTON_EXPORT_PDF));

        tableActionControls.addDownloadActionItem(
                ReportExportType.EXCEL,
                FontAwesome.FILE_EXCEL_O,
                "export", "export.xlsx",
                AppContext.getMessage(GenericI18Enum.BUTTON_EXPORT_EXCEL));

        tableActionControls.addDownloadActionItem(
                ReportExportType.CSV,
                FontAwesome.FILE_TEXT_O,
                "export", "export.csv",
                AppContext.getMessage(GenericI18Enum.BUTTON_EXPORT_CSV));

        if (CurrentProjectVariables
                .canWrite(ProjectRolePermissionCollections.PROBLEMS)) {
            tableActionControls.addActionItem(
                    MassItemActionHandler.MASS_UPDATE_ACTION, FontAwesome.DATABASE,
                    "update", AppContext
                            .getMessage(GenericI18Enum.TOOLTIP_MASS_UPDATE));
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
                UI.getCurrent().addWindow(
                        new ProblemListCustomizeWindow(
                                ProblemListView.VIEW_DEF_ID, tableItem));

            }
        });
        customizeViewBtn.setIcon(FontAwesome.ADJUST);
        customizeViewBtn.setDescription("Layout Options");
        customizeViewBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        layout.with(customizeViewBtn).withAlign(customizeViewBtn, Alignment.MIDDLE_RIGHT);

        return layoutWrapper;
    }

    @Override
    public void enableActionControls(int numOfSelectedItems) {
        tableActionControls.setVisible(true);
        selectedItemsNumberLabel.setValue(AppContext.getMessage(
                GenericI18Enum.TABLE_SELECTED_ITEM_TITLE, numOfSelectedItems));
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
    public HasMassItemActionHandlers getPopupActionHandlers() {
        return tableActionControls;
    }

    @Override
    public HasSelectableItemHandlers<SimpleProblem> getSelectableItemHandlers() {
        return tableItem;
    }

    @Override
    public AbstractPagedBeanTable<ProblemSearchCriteria, SimpleProblem> getPagedBeanTable() {
        return tableItem;
    }
}
