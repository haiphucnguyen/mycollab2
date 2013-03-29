/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.shell.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author haiphucnguyen
 */
public class FragmentNavigator {
	private static Logger log = LoggerFactory
			.getLogger(FragmentNavigator.class);

	public static ShellUrlResolver shellUrlResolver = new ShellUrlResolver();

	public FragmentNavigator() {

	}

	public static void navigateByFragement(String fragement) {
		if (fragement != null && fragement.length() > 0) {
			String[] tokens = fragement.split("/");
			shellUrlResolver.handle(tokens);
		}
	}
}
