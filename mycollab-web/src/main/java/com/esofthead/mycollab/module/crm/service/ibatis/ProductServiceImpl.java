package com.esofthead.mycollab.module.crm.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.crm.dao.ProductMapper;
import com.esofthead.mycollab.module.crm.dao.ProductMapperExt;
import com.esofthead.mycollab.module.crm.domain.Product;
import com.esofthead.mycollab.module.crm.domain.criteria.ProductSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ProductService;
import com.esofthead.mycollab.shared.audit.service.AuditLogService;

@Service
@Transactional
@Traceable(module = "Crm", type = "Product")
public class ProductServiceImpl extends DefaultService<Integer, Product, ProductSearchCriteria>
		implements ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private ProductMapperExt productMapperExt;

	@Autowired
	private AuditLogService auditLogService;

	@Override
	public ICrudGenericDAO<Integer, Product> getCrudMapper() {
		return productMapper;
	}

	@Override
	public ISearchableDAO<ProductSearchCriteria> getSearchMapper() {
		return productMapperExt;
	}

	@Override
	public int updateWithSession(Product record, String username) {
		Product oldValue = this.findByPrimaryKey(record.getId());
		String refid = "crm-product-" + record.getId();
		auditLogService.saveAuditLog(
				username, refid, (Object) oldValue,
				(Object) record);
		return super.updateWithSession(record, username);
	}
}
