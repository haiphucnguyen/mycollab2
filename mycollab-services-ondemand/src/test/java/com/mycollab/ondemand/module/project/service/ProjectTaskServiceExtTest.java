package com.mycollab.ondemand.module.project.service;

import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.mycollab.module.project.domain.Task;
import com.mycollab.module.project.service.ProjectTaskService;
import com.mycollab.test.service.IntegrationServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectTaskServiceExtTest extends IntegrationServiceTest {
    private static Logger LOG = LoggerFactory.getLogger(ProjectTaskServiceExtTest.class);

    @Autowired
    private ProjectTaskService projectTaskService;

    @Test
    public void testSaveWithoutSaveTheSameKey() throws ExecutionException, InterruptedException {
        final Task baseRecord = new Task();
        baseRecord.setProjectid(1);
        baseRecord.setTaskname("Hello world");
        baseRecord.setStatus(StatusI18nEnum.Open.name());
        baseRecord.setSaccountid(1);
        baseRecord.setPercentagecomplete(10d);

        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            LOG.info("Initialize thread " + i);
            Runnable exeService = new Runnable() {
                @Override
                public void run() {
                    Task record = (Task) baseRecord.copy();
                    projectTaskService.saveWithSession(record, "hainguyen@esofthead.com");
                }
            };

            Future<?> result = executorService.submit(exeService);
            LOG.info("Result:" + result.get());
        }
    }
}
