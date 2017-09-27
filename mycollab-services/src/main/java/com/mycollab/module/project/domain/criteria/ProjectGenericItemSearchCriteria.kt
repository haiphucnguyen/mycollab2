package com.mycollab.module.project.domain.criteria

import com.mycollab.db.arguments.SearchCriteria
import com.mycollab.db.arguments.SetSearchField
import com.mycollab.db.arguments.StringSearchField

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class ProjectGenericItemSearchCriteria : SearchCriteria(){
    lateinit var prjKeys: SetSearchField<Int>
    lateinit var txtValue: StringSearchField
    lateinit var createdUsers: SetSearchField<String> 
    lateinit var types: SetSearchField<String> 
    lateinit var monitorProjectIds: SetSearchField<Int> 
    lateinit var tagNames: SetSearchField<String> 
}