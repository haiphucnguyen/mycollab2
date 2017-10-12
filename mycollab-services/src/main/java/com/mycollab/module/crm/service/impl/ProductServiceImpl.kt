/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.service.impl

import com.mycollab.db.persistence.ICrudGenericDAO
import com.mycollab.db.persistence.ISearchableDAO
import com.mycollab.db.persistence.service.DefaultService
import com.mycollab.module.crm.dao.ProductMapper
import com.mycollab.module.crm.dao.ProductMapperExt
import com.mycollab.module.crm.domain.Product
import com.mycollab.module.crm.domain.criteria.ProductSearchCriteria
import com.mycollab.module.crm.service.ProductService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductServiceImpl(private val productMapper: ProductMapper,
                         private val productMapperExt: ProductMapperExt) : DefaultService<Int, Product, ProductSearchCriteria>(), ProductService {

    override val crudMapper: ICrudGenericDAO<Int, Product>
        get() = productMapper as ICrudGenericDAO<Int, Product>

    override val searchMapper: ISearchableDAO<ProductSearchCriteria>
        get() = productMapperExt
}
