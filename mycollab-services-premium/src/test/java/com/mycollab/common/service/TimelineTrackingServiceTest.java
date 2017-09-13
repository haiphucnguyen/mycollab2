package com.mycollab.common.service;

import com.mycollab.common.domain.GroupItem;
import com.mycollab.common.domain.criteria.TimelineTrackingSearchCriteria;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.i18n.OptionI18nEnum.BugStatus;
import com.mycollab.test.DataSet;
import com.mycollab.test.spring.IntegrationServiceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class TimelineTrackingServiceTest extends IntegrationServiceTest {
    @Autowired
    private TimelineTrackingService timelineTrackingService;

    @Test
    @DataSet
    public void testFindTimeline() {
        TimelineTrackingSearchCriteria criteria = new TimelineTrackingSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(1));
        criteria.setTypes(new SetSearchField(ProjectTypeConstants.BUG));
        Map<String, List<GroupItem>> timelineItems = timelineTrackingService.findTimelineItems("status",
                Arrays.asList(BugStatus.ReOpen.name(), BugStatus.Resolved.name()),
                new GregorianCalendar(2015, 9, 2).getTime(), new GregorianCalendar(2015, 9, 31).getTime(), criteria);
        System.out.println(timelineItems);
    }
}
