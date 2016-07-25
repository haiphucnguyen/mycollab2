package com.mycollab.ondemand.module.project.service;

import com.mycollab.module.project.domain.SimpleStandupReport;
import com.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.mycollab.module.project.service.StandupReportService;
import com.mycollab.module.user.domain.SimpleUser;
import com.mycollab.test.DataSet;
import com.mycollab.test.service.IntegrationServiceTest;
import com.mycollab.db.arguments.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringJUnit4ClassRunner.class)
public class StandupReportServiceTest extends IntegrationServiceTest {
    @Autowired
    private StandupReportService reportService;

    @SuppressWarnings("unchecked")
    @Test
    @DataSet
    public void gatherStandupList() {
        StandupReportSearchCriteria criteria = new StandupReportSearchCriteria();
        criteria.setProjectIds(new SetSearchField<>(1));
        criteria.setLogBy(StringSearchField.and("hainguyen"));
        Date d = new GregorianCalendar(2013, 2, 13).getTime();
        criteria.setOnDate(new DateSearchField(d));
        criteria.setSaccountid(new NumberSearchField(1));
        List<SimpleStandupReport> reports = reportService.findPageableListByCriteria(new BasicSearchRequest<>(criteria, 0, Integer.MAX_VALUE));
        assertThat(reports.size()).isEqualTo(1);
        assertThat(reports).extracting("id", "logby", "whattoday").contains(tuple(1, "hainguyen", "a"));
    }

    @Test
    @DataSet
    public void testFindUsersNotDoReportYet() {
        Date d = new GregorianCalendar(2013, 2, 13).getTime();
        List<SimpleUser> users = reportService.findUsersNotDoReportYet(1, d, 1);
        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getUsername()).isEqualTo("linhduong");
    }
}
