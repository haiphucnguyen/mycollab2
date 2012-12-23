package com.esofthead.mycollab.module.crm.service.ibatis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultService;
import com.esofthead.mycollab.module.crm.dao.ProductMapper;
import com.esofthead.mycollab.module.crm.dao.QuoteMapper;
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

@Service
public class QuoteServiceImpl extends DefaultService<Integer, Quote, QuoteSearchCriteria>
		implements QuoteService {

	@Autowired
	private QuoteMapper quoteMapper;
	
	@Autowired
	private QuoteMapperExt quoteMapperExt;

	@Autowired
	private QuoteGroupProductService quoteGroupProductService;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private AuditLogService auditLogService;

	@Override
	public ICrudGenericDAO<Integer, Quote> getCrudMapper() {
		return quoteMapper;
	}

	@Override
	public ISearchableDAO<QuoteSearchCriteria> getSearchMapper() {
		return quoteMapperExt;
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
		this.productMapper = productDAO;
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
				productMapper.insert(quoteProduct);
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
			List<Product> quoteProducts = productMapper
					.selectByExample(quoteProductEx);
			simpleQuoteGroupProduct.setQuoteProducts(quoteProducts);
			result.add(simpleQuoteGroupProduct);
		}
		return result;
	}

}
