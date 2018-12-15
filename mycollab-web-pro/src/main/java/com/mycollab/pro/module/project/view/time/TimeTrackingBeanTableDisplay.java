package com.mycollab.pro.module.project.view.time;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Text;
import com.mycollab.common.TableViewField;
import com.mycollab.html.DivLessFormatter;
import com.mycollab.module.project.ProjectLinkGenerator;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.service.ItemTimeLoggingService;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.TooltipHelper;
import com.mycollab.vaadin.web.ui.table.DefaultPagedBeanTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class TimeTrackingBeanTableDisplay extends DefaultPagedBeanTable<ItemTimeLoggingService, ItemTimeLoggingSearchCriteria,
        SimpleItemTimeLogging> {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(TimeTrackingBeanTableDisplay.class);

    public TimeTrackingBeanTableDisplay(List<TableViewField> displayColumns) {
        super(AppContextUtil.getSpringBean(ItemTimeLoggingService.class), SimpleItemTimeLogging.class, displayColumns);

//        this.addGeneratedColumn("logUserFullName", (source, itemId, columnId) -> {
//            SimpleItemTimeLogging timeItem = getBeanByIndex(itemId);
//            return new ProjectUserLink(timeItem.getProjectid(), timeItem.getLoguser(),
//                    timeItem.getLogUserAvatarId(), timeItem.getLogUserFullName());
//        });
//
//        this.addGeneratedColumn("name", (source, itemId, columnId) -> {
//                    SimpleItemTimeLogging itemLogging = getBeanByIndex(itemId);
//
//                    try {
//                        VerticalLayout summaryWrapper = new VerticalLayout();
//                        String type = itemLogging.getType();
//
//                        if (type == null) {
//                            return ELabel.html(itemLogging.getNote());
//                        } else {
//                            Label timeTrackingLink = ELabel.html(buildItemValue(itemLogging)).withStyleName
//                                    (UIConstants.LABEL_WORD_WRAP).withFullWidth();
//
//                            if (ProjectTypeConstants.BUG.equals(type)) {
//                                if (StatusI18nEnum.Verified.name().equals(itemLogging.getStatus())) {
//                                    timeTrackingLink.addStyleName(WebThemes.LINK_COMPLETED);
//                                } else if (itemLogging.getDueDate() != null && (itemLogging.getDueDate()
//                                        .before(DateTimeUtils.getCurrentDateWithoutMS()))) {
//                                    timeTrackingLink.addStyleName(WebThemes.LINK_OVERDUE);
//                                }
//                            } else if (type.equals(ProjectTypeConstants.TASK)) {
//                                if (itemLogging.getPercentageComplete() != null
//                                        && 100d == itemLogging.getPercentageComplete()) {
//                                    timeTrackingLink.addStyleName(WebThemes.LINK_COMPLETED);
//                                } else {
//                                    if (StatusI18nEnum.Pending.name().equals(itemLogging.getStatus())) {
//                                        timeTrackingLink.addStyleName(WebThemes.LINK_PENDING);
//                                    } else if (itemLogging.getDueDate() != null
//                                            && (itemLogging.getDueDate().before(DateTimeUtils.getCurrentDateWithoutMS()))) {
//                                        timeTrackingLink.addStyleName(WebThemes.LINK_OVERDUE);
//                                    }
//                                }
//                            } else {
//                                if (StatusI18nEnum.Closed.name().equals(itemLogging.getStatus())) {
//                                    timeTrackingLink.addStyleName(WebThemes.LINK_COMPLETED);
//                                } else if (itemLogging.getDueDate() != null &&
//                                        (itemLogging.getDueDate().before(DateTimeUtils.getCurrentDateWithoutMS()))) {
//                                    timeTrackingLink.addStyleName(WebThemes.LINK_OVERDUE);
//                                }
//                            }
//                            summaryWrapper.addComponent(timeTrackingLink);
//
//                            if (StringUtils.isNotBlank(itemLogging.getNote())) {
//                                summaryWrapper.addComponent(ELabel.html(itemLogging.getNote()));
//                            }
//
//                            return summaryWrapper;
//                        }
//
//
//                    } catch (Exception e) {
//                        LOG.error("Error: " + BeanUtility.printBeanObj(itemLogging), e);
//                        return new Label("");
//                    }
//                }
//        );
//
//        this.addGeneratedColumn("projectName", (source, itemId, columnId) -> {
//                    SimpleItemTimeLogging itemLogging = getBeanByIndex(itemId);
//
//                    LabelLink b = new LabelLink(itemLogging.getProjectName(),
//                            ProjectLinkGenerator.generateProjectLink(itemLogging.getProjectid()));
//                    b.setIconLink(ProjectAssetsManager.getAsset(ProjectTypeConstants.PROJECT));
//                    return b;
//                }
//        );
//
//        this.addGeneratedColumn("isbillable", (source, itemId, columnId) -> {
//                    SimpleItemTimeLogging timeLogging = getBeanByIndex(itemId);
//                    return (timeLogging.getIsbillable()) ? ELabel.fontIcon(VaadinIcons.CHECK) : ELabel.fontIcon(VaadinIcons.CLOSE);
//                }
//        );
//
//        this.addGeneratedColumn("isovertime", (source, itemId, columnId) -> {
//                    SimpleItemTimeLogging timeLogging = getBeanByIndex(itemId);
//                    return (Boolean.TRUE.equals(timeLogging.getIsovertime())) ? ELabel.fontIcon(VaadinIcons.CHECK) : ELabel.fontIcon(VaadinIcons.CLOSE);
//                }
//        );
//
//        this.addGeneratedColumn("logforday", (source, itemId, columnId) -> {
//                    final SimpleItemTimeLogging timeLogging = getBeanByIndex(itemId);
//                    return new Label(UserUIContext.formatDate(timeLogging.getLogforday()));
//                }
//        );
//
//        this.addGeneratedColumn("id", (source, itemId, columnId) -> {
//            final SimpleItemTimeLogging itemLogging = getBeanByIndex(itemId);
//
//            MButton editBtn = new MButton("", clickEvent -> fireTableEvent(new TableClickEvent(TimeTrackingTableDisplay.this, itemLogging, "edit")))
//                    .withIcon(VaadinIcons.EDIT).withStyleName(WebThemes.BUTTON_ICON_ONLY);
//
//            MButton deleteBtn = new MButton("", clickEvent -> fireTableEvent(new TableClickEvent(TimeTrackingTableDisplay.this, itemLogging, "delete")))
//                    .withIcon(VaadinIcons.TRASH).withStyleName(WebThemes.BUTTON_ICON_ONLY);
//
//            return new MHorizontalLayout(editBtn, deleteBtn);
//        });

        this.setWidth("100%");
    }

    private String buildItemValue(SimpleItemTimeLogging itemLogging) {
        String type = itemLogging.getType();
        if (type == null) {
            return itemLogging.getNote();
        }

        DivLessFormatter div = new DivLessFormatter();
        Text image = new Text(ProjectAssetsManager.getAsset(itemLogging.getType()).getHtml());
        A itemLink = new A().setId("tag" + TooltipHelper.TOOLTIP_ID);

        if (ProjectTypeConstants.TASK.equals(itemLogging.getType()) || ProjectTypeConstants.BUG.equals(itemLogging.getType())) {
            itemLink.setHref(ProjectLinkGenerator.generateProjectItemLink(
                    itemLogging.getProjectShortName(),
                    itemLogging.getProjectid(), itemLogging.getType(),
                    itemLogging.getExtraTypeId() + ""));
        } else {
            itemLink.setHref(ProjectLinkGenerator.generateProjectItemLink(
                    itemLogging.getProjectShortName(),
                    itemLogging.getProjectid(), itemLogging.getType(),
                    itemLogging.getTypeid() + ""));
        }

        itemLink.setAttribute("onmouseover", TooltipHelper.projectHoverJsFunction(itemLogging.getType(), itemLogging.getTypeid() + ""));
        itemLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction());
        itemLink.appendText(itemLogging.getName());

        div.appendChild(image, DivLessFormatter.EMPTY_SPACE, itemLink);
        return div.write();
    }
}
