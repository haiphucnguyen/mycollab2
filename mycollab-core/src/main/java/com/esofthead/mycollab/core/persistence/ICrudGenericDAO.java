/**
 * Engroup - Enterprise Groupware Platform Copyright (C) 2007-2009 eSoftHead
 * Company <engroup@esofthead.com> http://www.esofthead.com
 *
 * Licensed under the GPL, Version 3.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 * http://www.gnu.org/licenses/gpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.esofthead.mycollab.core.persistence;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author haiphucnguyen
 *
 * @param <K>
 * @param <T>
 */
public interface ICrudGenericDAO<K extends Serializable, T> {

    /**
     * @param record
     */
    void insert(T record);

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * @param id
     * @return
     */
    T selectByPrimaryKey(K primaryKey);

    /**
     * @param id
     * @return
     */
    int deleteByPrimaryKey(K primaryKey);
    
    /**
     * 
     * @param value
     * @return 
     */
    int insertAndReturnKey(T value);
    
    void removeKeysWithSession(List keys);
    
    
}
