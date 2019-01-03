/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.ecm;

import com.mycollab.spring.AppContextUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.jackrabbit.core.persistence.PMContext;
import org.apache.jackrabbit.core.persistence.pool.BundleDbPersistenceManager;

/**
 * Customize db persistence of jackrabbit
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class BundleDbPersistenceManagerExt extends BundleDbPersistenceManager {

    /**
     * {@inheritDoc}
     */
    public void init(PMContext context) throws Exception {
        HikariDataSource dbConf = AppContextUtil.getSpringBean(HikariDataSource.class);
        setDriver(dbConf.getDriverClassName());
        setUser(dbConf.getUsername());
        setPassword(dbConf.getPassword());
        setUrl(dbConf.getJdbcUrl());

        if (getSchemaObjectPrefix() == null) {
            setSchemaObjectPrefix("ecm_p_workspace");
        }
        super.init(context);
    }
}
