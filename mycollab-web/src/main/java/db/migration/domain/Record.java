package db.migration.domain;

import java.util.HashMap;
import java.util.Map;

public class Record {
	private Map<String, Object> rs = new HashMap<String, Object>();

	public void put(String key, Object value) {
		rs.put(key, value);
	}

	public Boolean getBoolean(String key) {
		return (Boolean) rs.get(key);
	}

	public Integer getInt(String key) {
		return (Integer) rs.get(key);
	}

	public String getString(String key) {
		return (String) rs.get(key);
	}
}
