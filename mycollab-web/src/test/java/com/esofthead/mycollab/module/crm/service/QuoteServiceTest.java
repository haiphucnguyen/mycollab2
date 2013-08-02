package com.esofthead.mycollab.module.crm.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.crm.domain.Product;
import com.esofthead.mycollab.module.crm.domain.Quote;
import com.esofthead.mycollab.module.crm.domain.QuoteGroupProduct;
import com.esofthead.mycollab.module.crm.domain.SimpleQuoteGroupProduct;
import com.esofthead.mycollab.module.crm.domain.criteria.QuoteSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;
import com.esofthead.mycollab.test.ServiceTest;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring/service-context-test.xml"})
public class QuoteServiceTest extends ServiceTest{

    @Autowired
    protected QuoteService quoteService;

    @DataSet
    @Test
    public void testGetAll() {
        List quotes = quoteService
                .findPagableListByCriteria(new SearchRequest<QuoteSearchCriteria>(
                null, 0, Integer.MAX_VALUE));
        Assert.assertEquals(3, quotes.size());
    }

    @DataSet
    @Test
    public void testGetSearchCriteria() {
        List quotes = quoteService
                .findPagableListByCriteria(new SearchRequest<QuoteSearchCriteria>(
                getCriteria(), 0, Integer.MAX_VALUE));
        Assert.assertEquals(1, quotes.size());
    }

    @DataSet
    @Test
    public void testGetTotalCount() {
        Assert.assertEquals(1, quoteService.getTotalCount(getCriteria()));
    }

    private QuoteSearchCriteria getCriteria() {
        QuoteSearchCriteria criteria = new QuoteSearchCriteria();
        criteria.setAssignUserName(new StringSearchField(SearchField.AND, "a"));
        criteria.setBillingAccountName(new StringSearchField(SearchField.AND,
                "A"));
        criteria.setShippingAccountName(new StringSearchField(SearchField.AND,
                "B"));
        criteria.setBillingContactName(new StringSearchField(SearchField.AND,
                "abc"));
        criteria.setShippingContactName(new StringSearchField(SearchField.AND,
                "abc"));
        criteria.setOpportunityId(new NumberSearchField(SearchField.AND, 1));
        criteria.setAccountId(new NumberSearchField(SearchField.AND, 1));
        criteria.setContactId(new NumberSearchField(SearchField.AND, 1));
        criteria.setSaccountid(new NumberSearchField(SearchField.AND, 1));
        return criteria;
    }

    @DataSet
    @Test
    public void testSaveQuote() {
        Quote quote = new Quote();
        quote.setSubject("AA");
        quote.setBillaccount(new Integer(1));
        quote.setBillingaddress("AAA");
        quote.setShippingaddress("BBB");
        quote.setSaccountid(1);
        int quoteId = quoteService.saveWithSession(quote, "");
        // quoteId = quoteService.insertQuoteAndReturnKey(quote);
        Assert.assertNotNull(quoteId);
        System.out.println("Quote " + quoteId);
        // Assert.assertNotSame(0, quoteId);
    }

    @DataSet
    @Test
    public void testSaveSimpleQuoteGroupProduct() {
        List<SimpleQuoteGroupProduct> list = new ArrayList<SimpleQuoteGroupProduct>();

        SimpleQuoteGroupProduct simpleQuoteGroupProduct = new SimpleQuoteGroupProduct();
        QuoteGroupProduct quoteGroupProduct = new QuoteGroupProduct();
        quoteGroupProduct.setQuoteid(1);
        quoteGroupProduct.setShipping("10");
        quoteGroupProduct.setGroupname("aaa");
        quoteGroupProduct.setTax("12");

        List<Product> quoteProducts = new ArrayList<Product>();
        Product quoteProduct = new Product();
        quoteProduct.setProductname("AAA");
        quoteProduct.setCost(new Double(1));
        quoteProduct.setListprice(new Double(2));
        quoteProduct.setSaccountid(1);
        quoteProducts.add(quoteProduct);

        simpleQuoteGroupProduct.setQuoteGroupProduct(quoteGroupProduct);
        simpleQuoteGroupProduct.setQuoteProducts(quoteProducts);
        list.add(simpleQuoteGroupProduct);

        quoteService.saveSimpleQuoteGroupProducts(1, 1, list);
    }
}
