package com.esofthead.mycollab.module.crm.service;

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
import com.esofthead.mycollab.module.crm.domain.criteria.ProductSearchCriteria;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.EngroupClassRunner;

@RunWith(EngroupClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:META-INF/spring/audit-context.xml",
		"classpath:META-INF/spring/file-context.xml",
		"classpath:META-INF/spring/crm-context.xml",
		"classpath:META-INF/spring/crm-service-test-context.xml",
		"classpath:META-INF/spring/datasource-test-context.xml" })
public class ProductServiceTest {

	@Autowired
	protected ProductService productService;

	@DataSet
	@Test
	public void testGetAll() {
		List products = productService
				.findPagableListByCriteria(new SearchRequest<ProductSearchCriteria>(
						null, 0, Integer.MAX_VALUE));
		Assert.assertEquals(3, products.size());
	}

	@DataSet
	@Test
	public void testSearchByCriteria() {
		ProductSearchCriteria criteria = new ProductSearchCriteria();
		criteria.setProductName(new StringSearchField(SearchField.AND, "a"));
		criteria.setAccountName(new StringSearchField(SearchField.AND,
				"account"));
		criteria.setAccountId(new NumberSearchField(SearchField.AND, 1));

		List products = productService
				.findPagableListByCriteria(new SearchRequest<ProductSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		Assert.assertEquals(2, products.size());

		Assert.assertEquals(2, productService.getTotalCount(criteria));

	}

	@DataSet
	@Test
	public void testSearchByContactCriteria() {
		ProductSearchCriteria criteria = new ProductSearchCriteria();
		criteria.setProductName(new StringSearchField(SearchField.AND, "a"));
		criteria.setContactName(new StringSearchField(SearchField.AND, "first"));
		criteria.setContactId(new NumberSearchField(SearchField.AND, 1));
		criteria.setSaccountid(new NumberSearchField(SearchField.AND, 1));

		List products = productService
				.findPagableListByCriteria(new SearchRequest<ProductSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		Assert.assertEquals(1, products.size());

		Assert.assertEquals(1, productService.getTotalCount(criteria));
	}
}
