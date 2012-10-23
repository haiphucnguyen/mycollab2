package com.esofthead.mycollab.module.crm.service.ibatis;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.PlatformManager;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.crm.dao.ProductMapperExt;
import com.esofthead.mycollab.module.crm.domain.Product;
import com.esofthead.mycollab.module.crm.domain.criteria.ProductSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ProductService;
import com.esofthead.mycollab.shared.audit.service.AuditLogService;

public class ProductServiceImpl extends DefaultCrudService<Product, Integer>
		implements ProductService {
	private ProductMapperExt productExtDAO;

	private AuditLogService auditLogService;

	public void setProductExtDAO(ProductMapperExt productExtDAO) {
		this.productExtDAO = productExtDAO;
	}

	public void setAuditLogService(AuditLogService auditLogService) {
		this.auditLogService = auditLogService;
	}

	@Override
	public int updateWithSession(Product record, String userSessionId) {
		Product oldValue = this.findByPrimaryKey(record.getId());
		String refid = "crm-product-" + record.getId();
		auditLogService.saveAuditLog(
				PlatformManager.getInstance().getSession(userSessionId)
						.getRemoteUser(), refid, (Object) oldValue,
				(Object) record);
		return super.updateWithSession(record, userSessionId);
	}

	@Override
	public List findPagableListByCriteria(ProductSearchCriteria criteria,
			int skipNum, int maxResult) {
		return productExtDAO.findPagableList(criteria, new RowBounds(skipNum,
				maxResult));
	}

	@Override
	public int getTotalCount(ProductSearchCriteria criteria) {
		return productExtDAO.getTotalCount(criteria);
	}
}
