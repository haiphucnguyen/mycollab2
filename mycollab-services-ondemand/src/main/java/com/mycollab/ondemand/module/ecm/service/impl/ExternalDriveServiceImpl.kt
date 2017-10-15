package com.mycollab.ondemand.module.ecm.service.impl

import com.mycollab.db.persistence.ICrudGenericDAO
import com.mycollab.db.persistence.service.DefaultCrudService
import com.mycollab.module.ecm.dao.ExternalDriveMapper
import com.mycollab.module.ecm.domain.ExternalDrive
import com.mycollab.module.ecm.domain.ExternalDriveExample
import com.mycollab.module.ecm.service.ExternalDriveService
import org.springframework.stereotype.Service

@Service
class ExternalDriveServiceImpl(private val externalDriveMapper: ExternalDriveMapper) : DefaultCrudService<Int, ExternalDrive>(), ExternalDriveService {

    override val crudMapper: ICrudGenericDAO<Int, ExternalDrive>
        get() = externalDriveMapper as ICrudGenericDAO<Int, ExternalDrive>

    override fun getExternalDrivesOfUser(username: String): List<ExternalDrive> {
        val ex = ExternalDriveExample()
        ex.createCriteria().andOwnerEqualTo(username)
        return externalDriveMapper.selectByExample(ex)
    }
}
