/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.project.view.bug;

import com.mycollab.common.GridFieldMeta;
import com.mycollab.db.query.VariableInjector;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.fielddef.BugTableFieldDef;
import com.mycollab.module.project.i18n.BugI18nEnum;
import com.mycollab.module.project.i18n.OptionI18nEnum;
import com.mycollab.module.project.i18n.OptionI18nEnum.BugResolution;
import com.mycollab.module.project.i18n.OptionI18nEnum.BugSeverity;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.mycollab.module.tracker.service.BugService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.reporting.CustomizeReportOutputWindow;
import org.joda.time.LocalDate;

import java.util.Arrays;
import java.util.Collection;

import static com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
public class BugCustomizeReportOutputWindow extends CustomizeReportOutputWindow<BugSearchCriteria, SimpleBug> {
    public BugCustomizeReportOutputWindow(VariableInjector<BugSearchCriteria> variableInjector) {
        super(ProjectTypeConstants.BUG, UserUIContext.getMessage(BugI18nEnum.LIST), SimpleBug.class,
                AppContextUtil.getSpringBean(BugService.class), variableInjector);
    }

    @Override
    protected Collection<GridFieldMeta> getDefaultColumns() {
        return Arrays.asList(BugTableFieldDef.summary, BugTableFieldDef.environment, BugTableFieldDef.priority,
                BugTableFieldDef.severity, BugTableFieldDef.status, BugTableFieldDef.resolution,
                BugTableFieldDef.logBy, BugTableFieldDef.dueDate, BugTableFieldDef.assignUser,
                BugTableFieldDef.billableHours, BugTableFieldDef.nonBillableHours);
    }

    @Override
    protected Collection<GridFieldMeta> getAvailableColumns() {
        return Arrays.asList(BugTableFieldDef.summary, BugTableFieldDef.environment, BugTableFieldDef.priority,
                BugTableFieldDef.severity, BugTableFieldDef.status, BugTableFieldDef.resolution,
                BugTableFieldDef.logBy, BugTableFieldDef.startDate, BugTableFieldDef.endDate, BugTableFieldDef.dueDate,
                BugTableFieldDef.assignUser, BugTableFieldDef.milestoneName, BugTableFieldDef.billableHours,
                BugTableFieldDef.nonBillableHours);
    }

    @Override
    protected Object[] buildSampleData() {
        return new Object[]{"Bug A", "Virtual Environment", OptionI18nEnum.Priority.High.name(),
                BugSeverity.Major.name(), StatusI18nEnum.Open.name(), BugResolution.None.name(),
                "John Adam", UserUIContext.formatDate(new LocalDate().minusDays(2).toDate()), UserUIContext.formatDate(new
                LocalDate().plusDays(1).toDate()), UserUIContext.formatDate(new LocalDate().plusDays(2).toDate()),
                "Will Smith", "Project Execution", "10", "2"};
    }
}
