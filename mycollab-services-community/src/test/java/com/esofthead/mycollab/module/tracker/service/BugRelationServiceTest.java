package com.esofthead.mycollab.module.tracker.service;

import com.esofthead.mycollab.module.tracker.domain.SimpleRelatedBug;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.service.IntergrationServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class BugRelationServiceTest extends IntergrationServiceTest {

    @Autowired
    private BugRelationService bugRelationService;

    @DataSet
    @Test
    public void testGetRelatedBugs() {
        List<SimpleRelatedBug> relatedBugs = bugRelationService.findRelatedBugs(1);
        assertThat(relatedBugs.size()).isEqualTo(2);
    }
}
