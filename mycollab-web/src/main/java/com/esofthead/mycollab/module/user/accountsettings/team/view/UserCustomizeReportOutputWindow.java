package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.core.db.query.VariableInjector;
import com.esofthead.mycollab.module.user.AdminTypeConstants;
import com.esofthead.mycollab.module.user.accountsettings.localization.UserI18nEnum;
import com.esofthead.mycollab.module.user.accountsettings.view.UserTableFieldDef;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.reporting.CustomizeReportOutputWindow;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import org.joda.time.LocalDate;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
public class UserCustomizeReportOutputWindow extends CustomizeReportOutputWindow<UserSearchCriteria, SimpleUser> {
    public UserCustomizeReportOutputWindow(VariableInjector<UserSearchCriteria> variableInjector) {
        super(AdminTypeConstants.USER, AppContext.getMessage(UserI18nEnum.LIST), SimpleUser.class,
                AppContextUtil.getSpringBean(UserService.class), variableInjector);
    }

    @Override
    protected Collection<TableViewField> getDefaultColumns() {
        return Arrays.asList(UserTableFieldDef.displayName(), UserTableFieldDef.roleName(),
                UserTableFieldDef.email(), UserTableFieldDef.birthday(),
                UserTableFieldDef.officePhone(), UserTableFieldDef.homePhone(), UserTableFieldDef.company());
    }

    @Override
    protected Collection<TableViewField> getAvailableColumns() {
        return Arrays.asList(UserTableFieldDef.displayName(), UserTableFieldDef.roleName(),
                UserTableFieldDef.email(), UserTableFieldDef.birthday(),
                UserTableFieldDef.officePhone(), UserTableFieldDef.homePhone(), UserTableFieldDef.company());
    }

    @Override
    protected Object[] buildSampleData() {
        return new Object[]{"John Adams", "Administrator", "john.adam@mycollab.com", AppContext.formatDate(new
                LocalDate(1979, 3, 13).toDate()), "11111111", "11111111", "MyCollab"};
    }
}
