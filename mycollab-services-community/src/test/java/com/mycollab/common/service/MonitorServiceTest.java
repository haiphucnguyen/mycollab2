package com.mycollab.common.service;

import com.mycollab.common.domain.MonitorItem;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.test.DataSet;
import com.mycollab.test.service.IntegrationServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class MonitorServiceTest extends IntegrationServiceTest {
    @Autowired
    private MonitorItemService monitorItemService;

    @Test
    @DataSet
    public void testSaveBatchMonitor() {
        MonitorItem mon1 = new MonitorItem();
        mon1.setMonitorDate(new GregorianCalendar().getTime());
        mon1.setSaccountid(1);
        mon1.setType(ProjectTypeConstants.BUG);
        mon1.setTypeid(1);
        mon1.setExtratypeid(1);
        mon1.setUser("hainguyen");
        List<MonitorItem> items = new ArrayList<>();
        items.add(mon1);
        monitorItemService.saveMonitorItems(items);
    }
}
