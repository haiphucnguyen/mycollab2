/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.shell.view;


/**
 * 
 * @author haiphucnguyen
 */
public class FragmentNavigator {

	public static ShellUrlResolver shellUrlResolver = new ShellUrlResolver();

	public static void navigateByFragement(String fragement) {
		if (fragement != null && fragement.length() > 0) {
			String[] tokens = fragement.split("/");
			shellUrlResolver.handle(tokens);
		}
	}
}
