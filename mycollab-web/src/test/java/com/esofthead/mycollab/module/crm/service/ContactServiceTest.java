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
package com.esofthead.mycollab.module.crm.service;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;
import com.esofthead.mycollab.test.ServiceTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/service-test-context.xml"})
public class ContactServiceTest extends ServiceTest {

    @Autowired
    protected ContactService contactService;

    @DataSet
    @Test
    public void testGetFindByCriteria() {
        Assert.assertEquals(
                1,
                contactService.findPagableListByCriteria(
                new SearchRequest<ContactSearchCriteria>(getCriteria(),
                0, 2)).size());
    }

    @DataSet
    @Test
    public void testGetTotalCount() {
        Assert.assertEquals(1, contactService.getTotalCount(getCriteria()));
    }

    private ContactSearchCriteria getCriteria() {
        ContactSearchCriteria criteria = new ContactSearchCriteria();
        criteria.setAssignUserName(new StringSearchField(SearchField.AND,
                "Duong"));
//        criteria.setOpportunityId(new NumberSearchField(SearchField.AND, 1));
        criteria.setAccountName(new StringSearchField(SearchField.AND, "x"));
        criteria.setContactName(new StringSearchField(SearchField.AND, "Hai"));
        criteria.setSaccountid(new NumberSearchField(SearchField.AND, 1));
        return criteria;
    }

    @Test
    @DataSet
    public void testSearchContactName() {
        ContactSearchCriteria criteria = new ContactSearchCriteria();
        criteria.setContactName(new StringSearchField(SearchField.AND, "Nguyen Hai"));
        Assert.assertEquals(1, contactService.getTotalCount(criteria));
        Assert.assertEquals(
                1,
                contactService.findPagableListByCriteria(
                new SearchRequest<ContactSearchCriteria>(criteria, 0,
                Integer.MAX_VALUE)).size());
    }

    @Test
    @DataSet
    public void testSearchAssignUsers() {
        ContactSearchCriteria criteria = new ContactSearchCriteria();
        criteria.setAssignUsers(new SetSearchField<String>(SearchField.AND, new String[]{"linh", "hai"}));
        Assert.assertEquals(3, contactService.getTotalCount(criteria));
        Assert.assertEquals(
                3,
                contactService.findPagableListByCriteria(
                new SearchRequest<ContactSearchCriteria>(criteria, 0,
                Integer.MAX_VALUE)).size());
    }

    @Test
    @DataSet
    public void testSearchLeadSources() {
        ContactSearchCriteria criteria = new ContactSearchCriteria();
        criteria.setLeadSources(new SetSearchField<String>(SearchField.AND, new String[]{"Email", "Campaign"}));
        Assert.assertEquals(2, contactService.getTotalCount(criteria));
        Assert.assertEquals(
                2,
                contactService.findPagableListByCriteria(
                new SearchRequest<ContactSearchCriteria>(criteria, 0,
                Integer.MAX_VALUE)).size());
    }

    @Test
    @DataSet
    public void testSearchAnyPostalCode() {
        ContactSearchCriteria criteria = new ContactSearchCriteria();
        criteria.setAnyPostalCode(new StringSearchField(SearchField.AND, "70000"));
        Assert.assertEquals(2, contactService.getTotalCount(criteria));
        Assert.assertEquals(
                2,
                contactService.findPagableListByCriteria(
                new SearchRequest<ContactSearchCriteria>(criteria, 0,
                Integer.MAX_VALUE)).size());
    }

    @Test
    @DataSet
    public void testSearchAnyCity() {
        ContactSearchCriteria criteria = new ContactSearchCriteria();
        criteria.setAnyCity(new StringSearchField(SearchField.AND, "HCM"));
        Assert.assertEquals(2, contactService.getTotalCount(criteria));
        Assert.assertEquals(
                2,
                contactService.findPagableListByCriteria(
                new SearchRequest<ContactSearchCriteria>(criteria, 0,
                Integer.MAX_VALUE)).size());
    }

    @Test
    @DataSet
    public void testSearchAnyPhone() {
        ContactSearchCriteria criteria = new ContactSearchCriteria();
        criteria.setAnyPhone(new StringSearchField(SearchField.AND, "(111)-(222)"));
        Assert.assertEquals(2, contactService.getTotalCount(criteria));
        Assert.assertEquals(
                2,
                contactService.findPagableListByCriteria(
                new SearchRequest<ContactSearchCriteria>(criteria, 0,
                Integer.MAX_VALUE)).size());
    }

    @Test
    @DataSet
    public void testSearchAnyCountries() {
        ContactSearchCriteria criteria = new ContactSearchCriteria();
        criteria.setCountries(new SetSearchField<String>(SearchField.AND, new String[]{"Viet nam", "America"}));
        Assert.assertEquals(2, contactService.getTotalCount(criteria));
        Assert.assertEquals(
                2,
                contactService.findPagableListByCriteria(
                new SearchRequest<ContactSearchCriteria>(criteria, 0,
                Integer.MAX_VALUE)).size());
    }

    @Test
    @DataSet
    public void testSearchAnyState() {
        ContactSearchCriteria criteria = new ContactSearchCriteria();
        criteria.setAnyState(new StringSearchField(SearchField.AND, "abc"));
        Assert.assertEquals(2, contactService.getTotalCount(criteria));
        Assert.assertEquals(
                2,
                contactService.findPagableListByCriteria(
                new SearchRequest<ContactSearchCriteria>(criteria, 0,
                Integer.MAX_VALUE)).size());
    }

    @Test
    @DataSet
    public void testSearchAnyAddress() {
        ContactSearchCriteria criteria = new ContactSearchCriteria();
        criteria.setAnyAddress(new StringSearchField(SearchField.AND, "ade"));
        Assert.assertEquals(2, contactService.getTotalCount(criteria));
        Assert.assertEquals(
                2,
                contactService.findPagableListByCriteria(
                new SearchRequest<ContactSearchCriteria>(criteria, 0,
                Integer.MAX_VALUE)).size());
    }

    @Test
    @DataSet
    public void testSearchAnyEmail() {
        ContactSearchCriteria criteria = new ContactSearchCriteria();
        criteria.setAnyEmail(new StringSearchField(SearchField.AND, "abc@y.co"));
        Assert.assertEquals(2, contactService.getTotalCount(criteria));
        Assert.assertEquals(
                2,
                contactService.findPagableListByCriteria(
                new SearchRequest<ContactSearchCriteria>(criteria, 0,
                Integer.MAX_VALUE)).size());
    }

    @Test
    @DataSet
    public void testSearchLastname() {
        ContactSearchCriteria criteria = new ContactSearchCriteria();
        criteria.setLastname(new StringSearchField(SearchField.AND, "linh"));
        Assert.assertEquals(0, contactService.getTotalCount(criteria));
        Assert.assertEquals(
                0,
                contactService.findPagableListByCriteria(
                new SearchRequest<ContactSearchCriteria>(criteria, 0,
                Integer.MAX_VALUE)).size());
    }

    @Test
    @DataSet
    public void testSearchFirstname() {
        ContactSearchCriteria criteria = new ContactSearchCriteria();
        criteria.setFirstname(new StringSearchField(SearchField.AND, "Nguyen"));
        Assert.assertEquals(3, contactService.getTotalCount(criteria));
        Assert.assertEquals(
                3,
                contactService.findPagableListByCriteria(
                new SearchRequest<ContactSearchCriteria>(criteria, 0,
                Integer.MAX_VALUE)).size());
    }
}
