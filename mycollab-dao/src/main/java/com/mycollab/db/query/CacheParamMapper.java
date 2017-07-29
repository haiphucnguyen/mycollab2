package com.mycollab.db.query;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MyCollab Ltd
 * @since 5.3.1
 */
public class CacheParamMapper {
    private static Map<String, Map<String, ValueParam>> map = new ConcurrentHashMap<>();

    public static <P extends Param> P register(String type, Enum displayName, P param) {
        Map<String, ValueParam> valueParamMap = map.get(type);
        if (valueParamMap == null) {
            valueParamMap = new ConcurrentHashMap<>();
            map.put(type, valueParamMap);
        }
        valueParamMap.put(param.getId(), new ValueParam(displayName, param));
        return param;
    }

    public static ValueParam getValueParam(String type, String id) {
        Map<String, ValueParam> valueParamMap = map.get(type);
        return (valueParamMap != null) ? valueParamMap.get(id) : null;
    }

    public static class ValueParam {
        private Enum displayName;
        private Param param;

        ValueParam(Enum displayName, Param param) {
            this.displayName = displayName;
            this.param = param;
        }

        public Enum getDisplayName() {
            return displayName;
        }

        public Param getParam() {
            return param;
        }
    }
}
