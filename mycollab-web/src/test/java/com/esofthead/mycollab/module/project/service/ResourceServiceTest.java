package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.domain.criteria.ResourceSearchCriteria;
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
public class ResourceServiceTest extends ServiceTest{

    @Autowired
    protected ResourceService resourceService;

    @DataSet
    @Test
    public void testGetListResources() {
        List resources = resourceService
                .findPagableListByCriteria(new SearchRequest<ResourceSearchCriteria>(
                null, 0, Integer.MAX_VALUE));
        Assert.assertEquals(3, resources.size());
    }

    @DataSet
    @Test
    public void testSearchResourcesByName() {
        ResourceSearchCriteria criteria = new ResourceSearchCriteria();
        criteria.setResourcename(new StringSearchField(SearchField.AND, "A"));
        List resources = resourceService
                .findPagableListByCriteria(new SearchRequest<ResourceSearchCriteria>(
                criteria, 0, Integer.MAX_VALUE));
        Assert.assertEquals(2, resources.size());
    }

    @DataSet
    @Test
    public void testGetListResourceNamesByProjectId() {
        Assert.assertEquals(3, resourceService.getResourceNamesByProjectId(1)
                .size());
    }
}
