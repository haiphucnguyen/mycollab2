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
package com.esofthead.mycollab.common;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.db.query.*;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.3.1
 */
public class QueryAnalyzer {

    public static String toQueryParams(List<SearchFieldInfo> searchFieldInfos) {
        StringBuilder result = new StringBuilder();
        for (SearchFieldInfo searchFieldInfo : searchFieldInfos) {
            if (searchFieldInfo.getPrefixOper().equals(SearchField.OR)) {
                result.append("&or");
            }
            Param param = searchFieldInfo.getParam();
            if (param instanceof StringParam || param instanceof CompositionStringParam || param instanceof NumberParam) {
                result.append(String.format("&%s*%s*%s", param.getId(), searchFieldInfo.getCompareOper(),
                        searchFieldInfo.eval()));
            } else if (param instanceof StringListParam) {
                StringListParam stringListParam = (StringListParam) param;
                List<String> values = stringListParam.getValues();
                if (CollectionUtils.isNotEmpty(values)) {
                    for (String value : values) {
                        result.append(String.format("&%s*%s*%s", param.getId(), searchFieldInfo.getCompareOper(), value));
                    }
                }
            } else if (param instanceof PropertyListParam) {
                Collection values = (Collection) searchFieldInfo.eval();
                if (CollectionUtils.isNotEmpty(values)) {
                    for (Object value : values) {
                        result.append(String.format("&%s*%s*%s", param.getId(), searchFieldInfo.getCompareOper(), value));
                    }
                }
            } else if (param instanceof DateParam) {
                DateParam dateParam = (DateParam) param;
                Object value = searchFieldInfo.eval();
                if (value.getClass().isArray()) {
                    Date val1 = (Date) Array.get(value, 0);
                    Date val2 = (Date) Array.get(value, 1);
                    if (val1 != null && val2 != null) {
                        result.append(String.format("&%s*%s*(%s,%s)", param.getId(), searchFieldInfo.getCompareOper(),
                                DateTimeUtils.formatDate((Date) val1, "yyyy-MM-dd"),
                                DateTimeUtils.formatDate((Date) val2, "yyyy-MM-dd")));
                    }
                } else {
                    result.append(String.format("&%s*%s*%s", param.getId(), searchFieldInfo.getCompareOper(),
                            DateTimeUtils.formatDate((Date) value, "yyyy-MM-dd")));
                }
            }
        }
        if (result.length() > 0 && result.charAt(0) == '&') {
            result.deleteCharAt(0);
        }
        return result.toString();
    }

    public static <S extends SearchCriteria> S fromQueryParams(String query, String type, S searchCriteria) {
        return searchCriteria;
    }
}
