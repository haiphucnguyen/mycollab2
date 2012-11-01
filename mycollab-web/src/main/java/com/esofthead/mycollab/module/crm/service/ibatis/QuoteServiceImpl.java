package com.esofthead.mycollab.module.crm.service.ibatis;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.crm.dao.ProductMapper;
import com.esofthead.mycollab.module.crm.dao.QuoteMapperExt;
import com.esofthead.mycollab.module.crm.domain.Product;
import com.esofthead.mycollab.module.crm.domain.ProductExample;
import com.esofthead.mycollab.module.crm.domain.Quote;
import com.esofthead.mycollab.module.crm.domain.QuoteGroupProduct;
import com.esofthead.mycollab.module.crm.domain.SimpleQuoteGroupProduct;
import com.esofthead.mycollab.module.crm.domain.criteria.QuoteSearchCriteria;
import com.esofthead.mycollab.module.crm.service.QuoteGroupProductService;
import com.esofthead.mycollab.module.crm.service.QuoteService;
import com.esofthead.mycollab.shared.audit.service.AuditLogService;

public class QuoteServiceImpl extends DefaultCrudService<Integer, Quote>
		implements QuoteService {

	private QuoteMapperExt quoteExtDAO;

	private QuoteGroupProductService quoteGroupProductService;

	private ProductMapper productDAO;

	private AuditLogService auditLogService;

	public void setQuoteExtDAO(QuoteMapperExt quoteExtDAO) {
		this.quoteExtDAO = quoteExtDAO;
	}

	public void setAuditLogService(AuditLogService auditLogService) {
		this.auditLogService = auditLogService;
	}

	public void setQuoteGroupProductService(
			QuoteGroupProductService quoteGroupProductService) {
		this.quoteGroupProductService = quoteGroupProductService;
	}

	@Override
	public int updateWithSession(Quote record, String username) {
		Quote oldValue = this.findByPrimaryKey(record.getId());
		String refid = "crm-quote-" + record.getId();
		auditLogService.saveAuditLog(
				username, refid, (Object) oldValue,
				(Object) record);
		return super.updateWithSession(record, username);
	}

	

	public void setProductDAO(ProductMapper productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	public List findPagableListByCriteria(QuoteSearchCriteria criteria,
			int skipNum, int maxResult) {
		return quoteExtDAO.findPagableList(criteria, new RowBounds(skipNum, maxResult));
	}

	@Override
	public int getTotalCount(QuoteSearchCriteria criteria) {
		return quoteExtDAO.getTotalCount(criteria);
	}

	@Override
	public void saveSimpleQuoteGroupProducts(int accountid, int quoteId,
			List<SimpleQuoteGroupProduct> entity) {
		quoteGroupProductService.deleteQuoteGroupByQuoteId(quoteId);

		for (SimpleQuoteGroupProduct simpleQuoteGroupProduct : entity) {
			QuoteGroupProduct quoteGroupProduct = simpleQuoteGroupProduct
			.getQuoteGroupProduct();
			quoteGroupProductService.insertQuoteGroupExt(quoteGroupProduct);

			for (Product quoteProduct : simpleQuoteGroupProduct
					.getQuoteProducts()) {
				// quoteProduct.setAccountid(accountid);
				quoteProduct.setGroupid(quoteGroupProduct.getId());
				quoteProduct.setStatus("Quoted");
				productDAO.insert(quoteProduct);
			}
		}
	}

	@Override
	public List<SimpleQuoteGroupProduct> getListSimpleQuoteGroupProducts(
			int quoteId) {
		List<QuoteGroupProduct> quoteGroupProducts = quoteGroupProductService
				.findQuoteGroupByQuoteId(quoteId);

		List<SimpleQuoteGroupProduct> result = new ArrayList<SimpleQuoteGroupProduct>();
		for (QuoteGroupProduct quoteGroupProduct : quoteGroupProducts) {
			SimpleQuoteGroupProduct simpleQuoteGroupProduct = new SimpleQuoteGroupProduct();
			simpleQuoteGroupProduct.setQuoteGroupProduct(quoteGroupProduct);

			ProductExample quoteProductEx = new ProductExample();
			quoteProductEx.createCriteria().andGroupidEqualTo(
					quoteGroupProduct.getId());
			List<Product> quoteProducts = productDAO
					.selectByExample(quoteProductEx);
			simpleQuoteGroupProduct.setQuoteProducts(quoteProducts);
			result.add(simpleQuoteGroupProduct);
		}
		return result;
	}

	@Override
	public int insertQuoteAndReturnKey(Quote quote) {
		quoteExtDAO.insertQuoteAndReturnKey(quote);
		return quote.getId();
	}

}
