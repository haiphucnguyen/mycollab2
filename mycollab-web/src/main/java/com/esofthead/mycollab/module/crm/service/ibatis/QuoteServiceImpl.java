package com.esofthead.mycollab.module.crm.service.ibatis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.interceptor.service.Auditable;
import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
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

@Service
@Transactional
@Traceable(module = "Crm", type = "Quote", nameField = "subject")
@Auditable(module = "Crm", type = "Quote")
public class QuoteServiceImpl extends DefaultService<Integer, Quote, QuoteSearchCriteria> implements
        QuoteService {

    @Autowired
    private QuoteMapper quoteMapper;
    @Autowired
    private QuoteMapperExt quoteMapperExt;
    @Autowired
    private QuoteGroupProductService quoteGroupProductService;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ICrudGenericDAO<Integer, Quote> getCrudMapper() {
        return quoteMapper;
    }

    @Override
    public ISearchableDAO<QuoteSearchCriteria> getSearchMapper() {
        return quoteMapperExt;
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
