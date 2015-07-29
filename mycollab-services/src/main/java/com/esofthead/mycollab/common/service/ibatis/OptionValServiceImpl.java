/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.common.service.ibatis;

import com.esofthead.mycollab.common.dao.OptionValMapper;
import com.esofthead.mycollab.common.domain.OptionVal;
import com.esofthead.mycollab.common.domain.OptionValExample;
import com.esofthead.mycollab.common.service.OptionValService;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
@Service
public class OptionValServiceImpl extends DefaultCrudService<Integer, OptionVal> implements OptionValService {
    @Autowired
    private OptionValMapper optionValMapper;

    @Override
    public ICrudGenericDAO<Integer, OptionVal> getCrudMapper() {
        return optionValMapper;
    }

    @Override
    public List<OptionVal> findOptionVals(String type, Integer projectId, Integer sAccountId) {
        OptionValExample ex = new OptionValExample();
        ex.createCriteria().andTypeEqualTo(type).andSaccountidEqualTo(sAccountId).andExtraidEqualTo(projectId);
        return optionValMapper.selectByExampleWithBLOBs(ex);
    }

    @Override
    public Integer saveWithSession(OptionVal record, String username) {
        String typeVal = record.getTypeval();
        OptionValExample ex = new OptionValExample();
        ex.createCriteria().andTypevalEqualTo(typeVal);
        if (optionValMapper.countByExample(ex) > 0) {
            throw new UserInvalidInputException("There is already column name " + typeVal);
        } else {
            return super.saveWithSession(record, username);
        }
    }
}
