package com.esofthead.db.sqldump.data;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import com.esofthead.db.sqldump.DataAdapter;
import com.esofthead.db.sqldump.INFORMATION_SCHEMA;
import com.esofthead.db.sqldump.data.parser.ConstraintParser;
import com.esofthead.db.sqldump.data.parser.InnoDBSysForeignParser;
import com.esofthead.db.sqldump.data.parser.TableParser;

public class Schema {
	private String schemaName;
	
	private Schema(String schemaName) {
		this.schemaName = schemaName;
	}
	
	final public List<Table> Tables = new LinkedList<Table>();
	final public List<Constraint> UniqueConstraints = new LinkedList<Constraint>();
	final public List<Constraint> ForeignKeyConstraints = new LinkedList<Constraint>();
	final private Hashtable<String, InnoDBSysForeign>  InnoDBSysForeigns = new Hashtable<String, InnoDBSysForeign>();
	
	/**
	 * @return the schemaName
	 */
	public String getSchemaName() {
		return schemaName;
	}
	
	private void loadTable() throws Exception {
		List<Object> lsObjects = DataAdapter.getData(
				INFORMATION_SCHEMA.TABLES.getTableListBySchema(schemaName),
				new TableParser());

		Tables.clear();
		for (Object obj : lsObjects) {
			Tables.add((Table) obj);
		}
		lsObjects.clear();
	}
	
	private void loadConstraint() throws Exception {
		List<Object> lsObjects = DataAdapter.getData(
				INFORMATION_SCHEMA.CONSTRAINTS.getConstraintListBySchema(schemaName),
				new ConstraintParser());
		
		UniqueConstraints.clear();
		ForeignKeyConstraints.clear();
		for (Object obj : lsObjects) {
			Constraint constraint = (Constraint)obj;
			if (constraint.isUniqueIndex()) {
				UniqueConstraints.add(constraint);
			} else {
				ForeignKeyConstraints.add(constraint);
			}
		}
		lsObjects.clear();
	}
	
	private void loadSysForeign() throws Exception {
		List<Object> lsObjects = DataAdapter.getData(
				INFORMATION_SCHEMA.INNODB_SYS_FOREIGN.getSysForeignBySchema(schemaName),
				new InnoDBSysForeignParser());
		
		InnoDBSysForeigns.clear();
		for (Object obj : lsObjects) {
			InnoDBSysForeign option = (InnoDBSysForeign)obj;
			InnoDBSysForeigns.put(option.getId(), option);
		}
	}
	
	private void loadSchema() throws Exception {
		loadTable();
		
		for (Table table : Tables) {
			table.loadColumn();
		}
		
		loadConstraint();
		loadSysForeign();
	}
	
	public String dumpSchema() {
		StringBuilder script = new StringBuilder();
		for (Table table : Tables) {
			script.append(table.serialTable());
			script.append("\r\n\r\n");
		}
		
		script.append("\r\n-- Add unique constraint -------------------------------------------\r\n\r\n\r\n");
		
		for (Constraint constraint : UniqueConstraints) {
			script.append(constraint.serialConstraint(-1));
			script.append("\r\n\r\n");
		}
		
		script.append("\r\n-- Add foreign key constraint -------------------------------------------\r\n\r\n\r\n");
		
		final String key = "%s/%s";
		for (Constraint constraint : ForeignKeyConstraints) {
			int constraintType = -1;
			InnoDBSysForeign match = InnoDBSysForeigns.get(String.format(key, schemaName, constraint.getConstraintName()));
			if (null != match)
				constraintType = match.getType();
			script.append(constraint.serialConstraint(constraintType));
			script.append("\r\n\r\n");
		}
		
		return script.toString();
	}
	
	public static final Schema loadSchema(String schemaName) throws Exception {
		Schema schema = new Schema(schemaName);
		schema.loadSchema();
		return schema;
	}
}
