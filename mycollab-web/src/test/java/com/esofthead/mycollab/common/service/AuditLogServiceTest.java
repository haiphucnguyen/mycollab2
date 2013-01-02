/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.common.service;

import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.criteria.AuditLogSearchCriteria;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;
import com.esofthead.mycollab.test.ServiceTest;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author haiphucnguyen
 */
@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/service-test-context.xml"})
public class AuditLogServiceTest extends ServiceTest {
    @Autowired
    protected AuditLogService auditLogService;
    
    @Autowired
    protected AccountService accountService;
    
    @Test
    @DataSet
    public void testSaveAccountAndTrace() {
        Account account = new Account();
        account.setSaccountid(1);
        account.setAccountname("a");
        
        int accountId = accountService.saveWithSession(account, "hainguyen");
        
//        AuditLogSearchCriteria criteria = new AuditLogSearchCriteria();
//        criteria.setTypeid(new NumberSearchField(accountId));
//        criteria.setType(new StringSearchField("Account"));
//        criteria.setSaccountid(new NumberSearchField(1));
//        criteria.setModule(new StringSearchField("Crm"));
//        
//        List<SimpleAuditLog> logs = auditLogService.findPagableListByCriteria(new SearchRequest<AuditLogSearchCriteria>(criteria, 0, Integer.MAX_VALUE));
//        Assert.assertEquals(1, logs.size());
//        
//        SimpleAuditLog log = logs.get(0);
//        Assert.assertEquals((int)accountId, (int)log.getTypeid());
    }
}
