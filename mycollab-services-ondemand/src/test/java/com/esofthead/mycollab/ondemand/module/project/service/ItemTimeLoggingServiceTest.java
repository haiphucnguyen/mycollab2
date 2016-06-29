package com.esofthead.mycollab.ondemand.module.project.service;

import com.esofthead.mycollab.core.arguments.BooleanSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.BasicSearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.domain.ItemTimeLogging;
import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.module.project.service.ItemTimeLoggingService;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.service.IntergrationServiceTest;
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
public class ItemTimeLoggingServiceTest extends IntergrationServiceTest {

    private static final DateFormat DF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    protected ItemTimeLoggingService itemTimeLoggingService;

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
        List<ItemTimeLogging> itemTimeLoggings = itemTimeLoggingService
                .findPagableListByCriteria(new BasicSearchRequest<>(getCriteria(), 0, Integer.MAX_VALUE));

        assertThat(itemTimeLoggings.size()).isEqualTo(2);
        assertThat(itemTimeLoggings).extracting("id", "type", "logforday", "loguser", "summary").contains(
                tuple(4, "Project-Task", DF.parse("2014-04-19 13:29:23"), "hai79", "task1"),
                tuple(2, "Project-Bug", DF.parse("2014-06-10 13:29:23"), "nghiemle", "summary 2"));
    }

    @DataSet
    @Test
    public void testGetTotalCount() {
        List<ItemTimeLogging> itemTimeLoggings = itemTimeLoggingService.findPagableListByCriteria(
                new BasicSearchRequest<>(getCriteria(), 0, Integer.MAX_VALUE));
        assertThat(itemTimeLoggings.size()).isEqualTo(2);
    }
}