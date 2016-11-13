package com.mycollab.pro.module.project.view.time;

import com.mycollab.common.i18n.DayI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.utils.DateTimeUtils;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.query.ConstantValueInjector;
import com.mycollab.db.query.DateParam;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.ui.components.ComponentUtils;
import com.mycollab.module.project.view.settings.component.ProjectMemberListSelect;
import com.mycollab.module.user.accountsettings.localization.UserI18nEnum;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.HeaderWithFontAwesome;
import com.mycollab.vaadin.ui.PopupDateFieldExt;
import com.mycollab.vaadin.web.ui.*;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Collection;
import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 2.0
 */
class ItemTimeLoggingSearchPanel extends DefaultGenericSearchPanel<ItemTimeLoggingSearchCriteria> {
    private static final long serialVersionUID = 1L;

    private TimeLoggingBasicSearchLayout layout;
    private ComboBox groupField, orderField;

    @Override
    protected SearchLayout<ItemTimeLoggingSearchCriteria> createBasicSearchLayout() {
        layout = new TimeLoggingBasicSearchLayout();
        return layout;
    }

    Date getFromDate() {
        return layout.startDateField.getValue();
    }

    Date getToDate() {
        return layout.endDateField.getValue();
    }

    @Override
    protected SearchLayout<ItemTimeLoggingSearchCriteria> createAdvancedSearchLayout() {
        return null;
    }

    @Override
    protected HeaderWithFontAwesome buildSearchTitle() {
        return ComponentUtils.headerH2(ProjectTypeConstants.TIME, UserUIContext.getMessage(TimeTrackingI18nEnum.SEARCH_TIME_TITLE));
    }

    public void addComponent(Component c, int index) {
        layout.bodyWrap.addComponent(c, index);
    }

    private class TimeLoggingBasicSearchLayout extends BasicSearchLayout<ItemTimeLoggingSearchCriteria> {
        private PopupDateFieldExt startDateField, endDateField;

        private ProjectMemberListSelect userField;
        private MVerticalLayout bodyWrap;

        TimeLoggingBasicSearchLayout() {
            super(ItemTimeLoggingSearchPanel.this);
        }

        @Override
        public ComponentContainer constructBody() {
            bodyWrap = new MVerticalLayout();
            GridLayout gridLayout = new GridLayout(6, 2);
            gridLayout.setSpacing(true);
            gridLayout.setMargin(true);

            Date[] boundWeekDays = DateTimeUtils.getBounceDatesOfWeek(new Date());
            startDateField = new PopupDateFieldExt();
            startDateField.setResolution(Resolution.DAY);
            startDateField.setValue(boundWeekDays[0]);
            endDateField = new PopupDateFieldExt();
            endDateField.setResolution(Resolution.DAY);
            endDateField.setValue(boundWeekDays[1]);

            Label dateStartLb = new ELabel(UserUIContext.getMessage(TimeTrackingI18nEnum.LOG_FOR_DATE)).withStyleName
                    (WebThemes.META_COLOR, WebThemes.TEXT_ALIGN_RIGHT).withWidth("90px");

            Label dateEndLb = new ELabel(UserUIContext.getMessage(DayI18nEnum.OPT_TO)).withStyleName(WebThemes.META_COLOR,
                    WebThemes.TEXT_ALIGN_RIGHT).withWidth("90px");

            gridLayout.addComponent(dateStartLb, 0, 0);
            gridLayout.addComponent(startDateField, 1, 0);
            gridLayout.addComponent(dateEndLb, 2, 0);
            gridLayout.addComponent(endDateField, 3, 0);
            gridLayout.addComponent(new ELabel(UserUIContext.getMessage(UserI18nEnum.SINGLE)).withStyleName(WebThemes.META_COLOR,
                    WebThemes.TEXT_ALIGN_RIGHT).withWidth("90px"), 4, 0);

            userField = new ProjectMemberListSelect();
            userField.setWidth("250px");
            gridLayout.addComponent(userField, 5, 0, 5, 1);

            MButton searchBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SEARCH), clickEvent -> callSearchAction())
                    .withStyleName(WebThemes.BUTTON_ACTION).withIcon(FontAwesome.SEARCH);

            MButton clearBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CLEAR), clickEvent -> userField.setValue(null))
                    .withStyleName(WebThemes.BUTTON_OPTION);

            MHorizontalLayout buttonControls = new MHorizontalLayout(searchBtn, clearBtn);
            bodyWrap.with(gridLayout, buttonControls).withAlign(buttonControls, Alignment.MIDDLE_CENTER);

            return bodyWrap;
        }

        @Override
        protected ItemTimeLoggingSearchCriteria fillUpSearchCriteria() {
            ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
            searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
            Date fDate = startDateField.getValue();
            Date tDate = endDateField.getValue();
            searchCriteria.addExtraField(DateParam.inRangeDate(ItemTimeLoggingSearchCriteria.p_logDates,
                    ConstantValueInjector.valueOf(Date.class, new Date[]{fDate, tDate})));
            Collection<String> selectedUsers = (Collection<String>) userField.getValue();
            if (CollectionUtils.isNotEmpty(selectedUsers)) {
                searchCriteria.setLogUsers(new SetSearchField(selectedUsers));
            }

            return searchCriteria;
        }
    }
}