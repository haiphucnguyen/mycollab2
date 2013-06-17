package com.esofthead.mycollab.common.dao;


public interface SaveSearchResultMapperExt extends SaveSearchResultMapper{
	public abstract String[] getListQueryName(String type);
	public abstract String getQueryTextByName(String name, String type);
}
