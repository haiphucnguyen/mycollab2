package com.esofthead.db.sqldump.data;

public class InnoDBSysForeign {
	private String id;
	private String forName;
	private String refName;
	private String nCols;
	private int type;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the forName
	 */
	public String getForName() {
		return forName;
	}
	/**
	 * @param forName the forName to set
	 */
	public void setForName(String forName) {
		this.forName = forName;
	}
	/**
	 * @return the refName
	 */
	public String getRefName() {
		return refName;
	}
	/**
	 * @param refName the refName to set
	 */
	public void setRefName(String refName) {
		this.refName = refName;
	}
	/**
	 * @return the nCols
	 */
	public String getnCols() {
		return nCols;
	}
	/**
	 * @param nCols the nCols to set
	 */
	public void setnCols(String nCols) {
		this.nCols = nCols;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
}
