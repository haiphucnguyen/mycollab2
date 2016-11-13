package com.mycollab.pro.module.project.view.time;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Text;
import com.mycollab.common.TableViewField;
import com.mycollab.common.i18n.OptionI18nEnum;
import com.mycollab.core.utils.BeanUtility;
import com.mycollab.core.utils.DateTimeUtils;
import com.mycollab.html.DivLessFormatter;
import com.mycollab.module.project.ProjectLinkBuilder;
import com.mycollab.module.project.ProjectLinkGenerator;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.i18n.OptionI18nEnum.BugStatus;
import com.mycollab.module.project.service.ItemTimeLoggingService;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.view.settings.component.ProjectUserLink;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.TooltipHelper;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.web.ui.LabelLink;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.table.DefaultPagedBeanTable;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.List;

import static com.mycollab.vaadin.TooltipHelper.TOOLTIP_ID;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class TimeTrackingTableDisplay extends DefaultPagedBeanTable<ItemTimeLoggingService, ItemTimeLoggingSearchCriteria,
        SimpleItemTimeLogging> {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(TimeTrackingTableDisplay.class);

    public TimeTrackingTableDisplay(List<TableViewField> displayColumns) {
        super(AppContextUtil.getSpringBean(ItemTimeLoggingService.class), SimpleItemTimeLogging.class, displayColumns);

        this.addGeneratedColumn("logUserFullName", (source, itemId, columnId) -> {
            SimpleItemTimeLogging timeItem = getBeanByIndex(itemId);
            return new ProjectUserLink(timeItem.getLoguser(), timeItem.getLogUserAvatarId(), timeItem.getLogUserFullName());
        });

        this.addGeneratedColumn("name", (source, itemId, columnId) -> {
                    SimpleItemTimeLogging itemLogging = getBeanByIndex(itemId);

                    try {
                        VerticalLayout summaryWrapper = new VerticalLayout();
                        String type = itemLogging.getType();

                        if (type == null) {
                            return ELabel.html(itemLogging.getNote());
                        } else {
                            Label timeTrackingLink = ELabel.html(buildItemValue(itemLogging)).withStyleName
                                    (UIConstants.LABEL_WORD_WRAP).withFullWidth();

                            if (ProjectTypeConstants.BUG.equals(type)) {
                                if (BugStatus.Verified.name().equals(itemLogging.getStatus())) {
                                    timeTrackingLink.addStyleName(WebThemes.LINK_COMPLETED);
                                } else if (itemLogging.getDueDate() != null && (itemLogging.getDueDate()
                                        .before(DateTimeUtils.getCurrentDateWithoutMS()))) {
                                    timeTrackingLink.addStyleName(WebThemes.LINK_OVERDUE);
                                }
                            } else if (type.equals(ProjectTypeConstants.TASK)) {
                                if (itemLogging.getPercentageComplete() != null
                                        && 100d == itemLogging.getPercentageComplete()) {
                                    timeTrackingLink.addStyleName(WebThemes.LINK_COMPLETED);
                                } else {
                                    if (OptionI18nEnum.StatusI18nEnum.Pending.name().equals(itemLogging.getStatus())) {
                                        timeTrackingLink.addStyleName(WebThemes.LINK_PENDING);
                                    } else if (itemLogging.getDueDate() != null
                                            && (itemLogging.getDueDate().before(DateTimeUtils.getCurrentDateWithoutMS()))) {
                                        timeTrackingLink.addStyleName(WebThemes.LINK_OVERDUE);
                                    }
                                }
                            } else {
                                if (OptionI18nEnum.StatusI18nEnum.Closed.name().equals(itemLogging.getStatus())) {
                                    timeTrackingLink.addStyleName(WebThemes.LINK_COMPLETED);
                                } else if (itemLogging.getDueDate() != null &&
                                        (itemLogging.getDueDate().before(DateTimeUtils.getCurrentDateWithoutMS()))) {
                                    timeTrackingLink.addStyleName(WebThemes.LINK_OVERDUE);
                                }
                            }
                            summaryWrapper.addComponent(timeTrackingLink);

                            if (StringUtils.isNotBlank(itemLogging.getNote())) {
                                summaryWrapper.addComponent(ELabel.html(itemLogging.getNote()));
                            }

                            return summaryWrapper;
                        }


                    } catch (Exception e) {
                        LOG.error("Error: " + BeanUtility.printBeanObj(itemLogging), e);
                        return new Label("");
                    }
                }
        );

        this.addGeneratedColumn("projectName", (source, itemId, columnId) -> {
                    SimpleItemTimeLogging itemLogging = getBeanByIndex(itemId);

                    LabelLink b = new LabelLink(itemLogging.getProjectName(),
                            ProjectLinkBuilder.generateProjectFullLink(itemLogging.getProjectid()));
                    b.setIconLink(ProjectAssetsManager.getAsset(ProjectTypeConstants.PROJECT));
                    return b;
                }
        );

        this.addGeneratedColumn("isbillable", (source, itemId, columnId) -> {
                    SimpleItemTimeLogging timeLogging = getBeanByIndex(itemId);
                    return (timeLogging.getIsbillable()) ? ELabel.fontIcon(FontAwesome.CHECK) : ELabel.fontIcon(FontAwesome.TIMES);
                }
        );

        this.addGeneratedColumn("isovertime", (source, itemId, columnId) -> {
                    SimpleItemTimeLogging timeLogging = getBeanByIndex(itemId);
                    return (Boolean.TRUE.equals(timeLogging.getIsovertime())) ? ELabel.fontIcon(FontAwesome.CHECK) : ELabel.fontIcon(FontAwesome.TIMES);
                }
        );

        this.addGeneratedColumn("logforday", (source, itemId, columnId) -> {
                    final SimpleItemTimeLogging timeLogging = getBeanByIndex(itemId);
                    return new Label(UserUIContext.formatDate(timeLogging.getLogforday()));
                }
        );

        this.addGeneratedColumn("id", (source, itemId, columnId) -> {
            final SimpleItemTimeLogging itemLogging = getBeanByIndex(itemId);

            MButton editBtn = new MButton("", clickEvent -> fireTableEvent(new TableClickEvent(TimeTrackingTableDisplay.this, itemLogging, "edit")))
                    .withIcon(FontAwesome.EDIT).withStyleName(WebThemes.BUTTON_ICON_ONLY);

            MButton deleteBtn = new MButton("", clickEvent -> fireTableEvent(new TableClickEvent(TimeTrackingTableDisplay.this, itemLogging, "delete")))
                    .withIcon(FontAwesome.TRASH_O).withStyleName(WebThemes.BUTTON_ICON_ONLY);

            return new MHorizontalLayout(editBtn, deleteBtn);
        });

        this.setWidth("100%");
    }

    private String buildItemValue(SimpleItemTimeLogging itemLogging) {
        String type = itemLogging.getType();
        if (type == null) {
            return itemLogging.getNote();
        }

        DivLessFormatter div = new DivLessFormatter();
        Text image = new Text(ProjectAssetsManager.getAsset(itemLogging.getType()).getHtml());
        A itemLink = new A().setId("tag" + TOOLTIP_ID);

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

        div.appendChild(image, DivLessFormatter.EMPTY_SPACE(), itemLink);
        return div.write();
    }
}
