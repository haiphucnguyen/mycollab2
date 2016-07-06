package com.mycollab.common.service;

import com.mycollab.common.domain.GroupItem;
import com.mycollab.common.domain.criteria.TimelineTrackingSearchCriteria;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.module.project.i18n.OptionI18nEnum;
import com.mycollab.test.DataSet;
import com.mycollab.test.service.IntergrationServiceTest;
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
public class TimelineTrackingServiceTest extends IntergrationServiceTest {
    @Autowired
    private TimelineTrackingService timelineTrackingService;

    @Test
    @DataSet
    public void testFindTimeline() {
        TimelineTrackingSearchCriteria criteria = new TimelineTrackingSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(1));
        Map<String, List<GroupItem>> timelineItems = timelineTrackingService.findTimelineItems("status",
                Arrays.asList(OptionI18nEnum.BugStatus.ReOpen.name(), OptionI18nEnum.BugStatus.Resolved.name()),
                new GregorianCalendar(2015, 9, 2).getTime(), new GregorianCalendar(2015, 9, 31).getTime(), criteria);
    }
}
