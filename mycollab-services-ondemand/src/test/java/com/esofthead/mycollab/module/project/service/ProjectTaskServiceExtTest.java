package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.test.service.IntergrationServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectTaskServiceExtTest extends IntergrationServiceTest {

	private static Logger LOG = LoggerFactory
			.getLogger(ProjectTaskServiceExtTest.class);

	@Autowired
	private ProjectTaskService projectTaskService;

	@Test
	public void testSaveWithoutSaveTheSameKey() {
		final Task baseRecord = new Task();
		baseRecord.setProjectid(1);
		baseRecord.setTaskname("Hello world");
		baseRecord.setStatus(StatusI18nEnum.Open.name());
		baseRecord.setSaccountid(1);
		baseRecord.setPercentagecomplete(10d);

		for (int i = 0; i < 100; i++) {
			Runnable exeService = new Runnable() {
				@Override
				public void run() {
					Task record = (Task) baseRecord.copy();
					int count = projectTaskService.saveWithSession(record,
							"hainguyen@esofthead.com");
					LOG.debug("COUNT: " + count + "---" + record.getTaskkey());
				}
			};

			new Thread(exeService).start();
		}

		while (true) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
