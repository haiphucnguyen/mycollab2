package com.esofthead.mycollab.services;

import java.util.HashMap;
import java.util.Map;

import org.rzo.yajsw.wrapper.WrappedProcess;
import org.rzo.yajsw.wrapper.WrappedProcessFactory;

public class WrappedService {

	@SuppressWarnings("rawtypes")
	public static void main(String... args) {

		System.setProperty("wrapper.config", "wrapper.conf");

		Map configuration = new HashMap();
		WrappedProcess wrappedProcess = (WrappedProcess) WrappedProcessFactory
				.createProcess(configuration, true);

		wrappedProcess.getLocalConfiguration().setProperty(
				"wrapper.app.parameter.1", "MyCollab");

		wrappedProcess.start();
		wrappedProcess.waitFor(10000);

		wrappedProcess.stop();

	}
}
