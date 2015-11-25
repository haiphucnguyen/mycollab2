package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.common.domain.criteria.TimelineTrackingSearchCriteria;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.service.IntergrationServiceTest;
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
        Map<String, List<GroupItem>> timelineItems = timelineTrackingService.findTimelineItems(Arrays.asList(OptionI18nEnum.BugStatus.ReOpened.name(), OptionI18nEnum
                        .BugStatus.Resolved.name()), new GregorianCalendar(2015, 9, 2).getTime(), new
                        GregorianCalendar(2015, 9, 31).getTime(),
                criteria);
        System.out.println("A: " + timelineItems);
    }
}
