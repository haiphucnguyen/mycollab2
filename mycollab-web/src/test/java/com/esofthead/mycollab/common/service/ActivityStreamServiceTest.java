/**
 * Engroup - Enterprise Groupware Platform Copyright (C) 2007-2009 eSoftHead
 * Company <engroup@esofthead.com> http://www.esofthead.com
 *
 * Licensed under the GPL, Version 3.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 * http://www.gnu.org/licenses/gpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.SimpleActivityStream;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;
import com.esofthead.mycollab.test.ServiceTest;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/service-test-context.xml"})
public class ActivityStreamServiceTest extends ServiceTest{

    @Autowired
    protected ActivityStreamService activityStreamService;

    @Test
    @DataSet
    public void testSearchActivityStreams() {
        ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
        searchCriteria.setModuleSet(new SetSearchField<String>(SearchField.AND, new String[]{"aa", "bb"}));

        List<SimpleActivityStream> activities = activityStreamService.findPagableListByCriteria(new SearchRequest<ActivityStreamSearchCriteria>(searchCriteria, 0, Integer.MAX_VALUE));
        Assert.assertEquals(3, activities.size());
    }
    
    @Test
    @DataSet
    public void testQueryActivityWithComments() {
         ActivityStreamSearchCriteria searchCriteria = new ActivityStreamSearchCriteria();
        searchCriteria.setModuleSet(new SetSearchField<String>(SearchField.AND, new String[]{"bb"}));
        
        List<SimpleActivityStream> activities = activityStreamService.findPagableListByCriteria(new SearchRequest<ActivityStreamSearchCriteria>(searchCriteria, 0, Integer.MAX_VALUE));
        Assert.assertEquals(1, activities.size());
        
        SimpleActivityStream activity = activities.get(0);
        Assert.assertEquals(2, activity.getComments().size());
    }
}
