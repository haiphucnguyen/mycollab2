package com.mycollab.common.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mycollab.common.domain.SimpleRelayEmailNotification;
import com.mycollab.common.domain.criteria.RelayEmailNotificationSearchCriteria;
import com.mycollab.db.arguments.BasicSearchRequest;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:META-INF/spring/service-context-test.xml"})
public class RelayEmailNotificationServiceTest {
	@Autowired
	protected RelayEmailNotificationService relayEmailNotificationService;

	//@Test
	//@DataSet
	@SuppressWarnings("unchecked")
	public void testRemoveItems() {
		RelayEmailNotificationSearchCriteria criteria = new RelayEmailNotificationSearchCriteria();
		List<SimpleRelayEmailNotification> items = relayEmailNotificationService
				.findPageableListByCriteria(new BasicSearchRequest<RelayEmailNotificationSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		assertThat(items.size()).isEqualTo(1);
	}
}
