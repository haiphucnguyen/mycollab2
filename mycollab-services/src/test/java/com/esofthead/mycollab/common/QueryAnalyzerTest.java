package com.esofthead.mycollab.common;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import org.junit.Test;

/**
 * @author MyCollab Ltd
 * @since 5.3.1
 */
public class QueryAnalyzerTest {
    @Test
    public void testToQueryParams() {
        BugSearchCriteria searchCriteria = new BugSearchCriteria();
        System.out.println(Enum.valueOf(GenericI18Enum.class, "FORM_TYPE"));
    }
}
