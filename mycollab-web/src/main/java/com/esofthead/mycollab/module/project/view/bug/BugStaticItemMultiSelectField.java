/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;


/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class BugStaticItemMultiSelectField extends MultiSelectComp {

	private String[] arrItemData;

	public BugStaticItemMultiSelectField(String[] arrItemData) {
		super("", "225px");
		this.arrItemData = arrItemData;
	}

	@Override
	protected void initData() {
		for (int i = 0; i < arrItemData.length; i++) {
			dataList.add(arrItemData[i]);
		}

	}
}
