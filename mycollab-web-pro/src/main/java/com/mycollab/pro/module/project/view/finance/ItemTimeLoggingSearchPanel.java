package com.mycollab.pro.module.project.view.finance;

import com.mycollab.common.i18n.DayI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.utils.DateTimeUtils;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.query.ConstantValueInjector;
import com.mycollab.db.query.DateParam;
import com.mycollab.db.query.Param;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.ProjectMember;
import com.mycollab.module.project.domain.SimpleProjectMember;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.i18n.TimeTrackingI18nEnum;
import com.mycollab.module.project.ui.components.ComponentUtils;
import com.mycollab.module.project.view.settings.component.ProjectMemberListSelect;
import com.mycollab.module.user.accountsettings.localization.UserI18nEnum;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.HeaderWithIcon;
import com.mycollab.vaadin.web.ui.*;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

/**
 * @author MyCollab Ltd
 * @since 2.0
 */
class ItemTimeLoggingSearchPanel extends DefaultGenericSearchPanel<ItemTimeLoggingSearchCriteria> {
    private static final long serialVersionUID = 1L;

    private static Param[] paramFields = new Param[]{ItemTimeLoggingSearchCriteria.p_logDates,
            ItemTimeLoggingSearchCriteria.p_logUsers, ItemTimeLoggingSearchCriteria.p_isBillable,
            ItemTimeLoggingSearchCriteria.p_isOvertime,
            ItemTimeLoggingSearchCriteria.p_isApproved};

    @Override
    protected SearchLayout<ItemTimeLoggingSearchCriteria> createBasicSearchLayout() {
        return new TimeLoggingBasicSearchLayout();
    }

    @Override
    protected SearchLayout<ItemTimeLoggingSearchCriteria> createAdvancedSearchLayout() {
        return new TimeLoggingAdvancedSearchLayout();
    }

    @Override
    protected HeaderWithIcon buildSearchTitle() {
        return ComponentUtils.headerH2(ProjectTypeConstants.TIME, UserUIContext.getMessage(TimeTrackingI18nEnum.SEARCH_TIME_TITLE));
    }

    private class TimeLoggingAdvancedSearchLayout extends DynamicQueryParamLayout<ItemTimeLoggingSearchCriteria> {

        TimeLoggingAdvancedSearchLayout() {
            super(ItemTimeLoggingSearchPanel.this, ProjectTypeConstants.TIME);
        }

        @Override
        public Param[] getParamFields() {
            return paramFields;
        }

        @Override
        protected Class<ItemTimeLoggingSearchCriteria> getType() {
            return ItemTimeLoggingSearchCriteria.class;
        }

        @Override
        protected Component buildSelectionComp(String fieldId) {
            if ("loguser".equals(fieldId)) {
                return new ProjectMemberListSelect(singletonList(CurrentProjectVariables.getProjectId()));
            }
            return null;
        }
    }

    private class TimeLoggingBasicSearchLayout extends BasicSearchLayout<ItemTimeLoggingSearchCriteria> {
        private DateField startDateField, endDateField;

        private ProjectMemberListSelect userField;
        private MVerticalLayout bodyWrap;

        TimeLoggingBasicSearchLayout() {
            super(ItemTimeLoggingSearchPanel.this);
        }

        @Override
        public ComponentContainer constructBody() {
            bodyWrap = new MVerticalLayout();
            GridLayout gridLayout = new GridLayout(2, 2);
            gridLayout.setSpacing(true);
            gridLayout.setMargin(true);

            LocalDate[] boundWeekDays = DateTimeUtils.getBounceDatesOfWeek(LocalDate.now());
            startDateField = new DateField();
            startDateField.setValue(boundWeekDays[0]);
            endDateField = new DateField();
            endDateField.setValue(boundWeekDays[1]);

            Label dateStartLb = new ELabel(UserUIContext.getMessage(TimeTrackingI18nEnum.LOG_FOR_DATE)).withStyleName
                    (WebThemes.META_COLOR, WebThemes.TEXT_ALIGN_RIGHT).withWidth("90px");

            Label dateEndLb = new ELabel(UserUIContext.getMessage(DayI18nEnum.OPT_TO)).withStyleName(WebThemes.META_COLOR,
                    WebThemes.TEXT_ALIGN_RIGHT).withWidth("90px");

            gridLayout.addComponent(new MHorizontalLayout(dateStartLb, startDateField), 0, 0);
            gridLayout.addComponent(new MHorizontalLayout(dateEndLb, endDateField), 0, 1);
            ELabel userSelectLbl = new ELabel(UserUIContext.getMessage(UserI18nEnum.SINGLE)).withStyleName(WebThemes.META_COLOR,
                    WebThemes.TEXT_ALIGN_RIGHT).withWidth("90px");

            userField = new ProjectMemberListSelect(singletonList(CurrentProjectVariables.getProjectId()));
            userField.setWidth(WebThemes.FORM_CONTROL_WIDTH);
            gridLayout.addComponent(new MHorizontalLayout(userSelectLbl, userField), 1, 0, 1, 1);

            MButton searchBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SEARCH), clickEvent -> callSearchAction())
                    .withStyleName(WebThemes.BUTTON_ACTION).withIcon(VaadinIcons.SEARCH);

            MButton clearBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CLEAR), clickEvent -> userField.setValue(null))
                    .withStyleName(WebThemes.BUTTON_OPTION);

            MButton advancedSearchBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_ADVANCED_SEARCH),
                    clickEvent -> moveToAdvancedSearchLayout()).withStyleName(WebThemes.BUTTON_LINK);

            MHorizontalLayout buttonControls = new MHorizontalLayout(searchBtn, clearBtn, advancedSearchBtn);
            bodyWrap.with(gridLayout, buttonControls).withAlign(buttonControls, Alignment.MIDDLE_CENTER);

            return bodyWrap;
        }

        @Override
        protected ItemTimeLoggingSearchCriteria fillUpSearchCriteria() {
            ItemTimeLoggingSearchCriteria searchCriteria = new ItemTimeLoggingSearchCriteria();
            searchCriteria.setProjectIds(new SetSearchField<>(CurrentProjectVariables.getProjectId()));
            LocalDate fDate = startDateField.getValue();
            LocalDate tDate = endDateField.getValue();
            searchCriteria.addExtraField(DateParam.inRangeDate(ItemTimeLoggingSearchCriteria.p_logDates,
                    ConstantValueInjector.valueOf(LocalDate.class, new LocalDate[]{fDate, tDate})));
            Set<SimpleProjectMember> selectedUsers = userField.getValue();
            if (CollectionUtils.isNotEmpty(selectedUsers)) {
                List<String> selectedUsernames = selectedUsers.stream().map(ProjectMember::getUsername).collect(Collectors.toList());
                searchCriteria.setLogUsers(new SetSearchField<>(selectedUsernames));
            }

            return searchCriteria;
        }
    }
}