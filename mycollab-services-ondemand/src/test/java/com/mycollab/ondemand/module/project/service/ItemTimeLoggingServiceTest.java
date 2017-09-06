package com.mycollab.ondemand.module.project.service;

import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.BooleanSearchField;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.module.project.domain.ItemTimeLogging;
import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.mycollab.module.project.service.ItemTimeLoggingService;
import com.mycollab.ondemand.test.spring.IntegrationServiceTest;
import com.mycollab.test.DataSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringJUnit4ClassRunner.class)
public class ItemTimeLoggingServiceTest extends IntegrationServiceTest {

    private static final DateFormat DF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    private ItemTimeLoggingService itemTimeLoggingService;

    private ItemTimeLoggingSearchCriteria getCriteria() {
        ItemTimeLoggingSearchCriteria criteria = new ItemTimeLoggingSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(1));
        criteria.setIsBillable(new BooleanSearchField(false));
        criteria.setLogUsers(new SetSearchField<>("hai79", "nghiemle"));
        return criteria;
    }

    @DataSet
    @Test
    public void testGetListItemTimeLoggings() throws ParseException {
        List<ItemTimeLogging> itemTimeLoggings = itemTimeLoggingService.findPageableListByCriteria(new BasicSearchRequest<>(getCriteria()));

        assertThat(itemTimeLoggings.size()).isEqualTo(2);
        assertThat(itemTimeLoggings).extracting("id", "type", "logforday", "loguser", "name").contains(
                tuple(4, "Project-Task", DF.parse("2014-04-19 13:29:23"), "hai79", "task1"),
                tuple(2, "Project-Bug", DF.parse("2014-06-10 13:29:23"), "nghiemle", "name 2"));
    }

    @DataSet
    @Test
    public void testGetTotalCount() {
        List<ItemTimeLogging> itemTimeLoggings = itemTimeLoggingService.findPageableListByCriteria(new BasicSearchRequest<>(getCriteria()));
        assertThat(itemTimeLoggings.size()).isEqualTo(2);
    }
}
